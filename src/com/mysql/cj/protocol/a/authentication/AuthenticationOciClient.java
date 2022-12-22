package com.mysql.cj.protocol.a.authentication;

import com.mysql.cj.Messages;
import com.mysql.cj.callback.MysqlCallback;
import com.mysql.cj.callback.MysqlCallbackHandler;
import com.mysql.cj.callback.UsernameCallback;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.RSAException;
import com.mysql.cj.protocol.AuthenticationPlugin;
import com.mysql.cj.protocol.ExportControlled;
import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.Protocol;
import com.mysql.cj.protocol.a.NativeConstants;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.util.StringUtils;
import com.oracle.bmc.ConfigFileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.interfaces.RSAPrivateKey;
import java.util.Base64;
import java.util.List;

public class AuthenticationOciClient implements AuthenticationPlugin<NativePacketPayload> {
  public static String PLUGIN_NAME = "authentication_oci_client";
  
  private String sourceOfAuthData = PLUGIN_NAME;
  
  protected Protocol<NativePacketPayload> protocol = null;
  
  private MysqlCallbackHandler usernameCallbackHandler = null;
  
  private String fingerprint = null;
  
  private RSAPrivateKey privateKey = null;
  
  public void init(Protocol<NativePacketPayload> prot, MysqlCallbackHandler cbh) {
    this.protocol = prot;
    this.usernameCallbackHandler = cbh;
  }
  
  public void reset() {
    this.fingerprint = null;
    this.privateKey = null;
  }
  
  public void destroy() {
    reset();
  }
  
  public String getProtocolPluginName() {
    return PLUGIN_NAME;
  }
  
  public boolean requiresConfidentiality() {
    return false;
  }
  
  public boolean isReusable() {
    return false;
  }
  
  public void setAuthenticationParameters(String user, String password) {
    if (user == null && this.usernameCallbackHandler != null)
      this.usernameCallbackHandler.handle((MysqlCallback)new UsernameCallback(System.getProperty("user.name"))); 
  }
  
  public void setSourceOfAuthData(String sourceOfAuthData) {
    this.sourceOfAuthData = sourceOfAuthData;
  }
  
  public boolean nextAuthenticationStep(NativePacketPayload fromServer, List<NativePacketPayload> toServer) {
    toServer.clear();
    if (!this.sourceOfAuthData.equals(PLUGIN_NAME) || fromServer.getPayloadLength() == 0) {
      toServer.add(new NativePacketPayload(0));
      return true;
    } 
    initializePrivateKey();
    byte[] nonce = fromServer.readBytes(NativeConstants.StringSelfDataType.STRING_EOF);
    byte[] signature = ExportControlled.sign(nonce, this.privateKey);
    if (signature == null)
      signature = new byte[0]; 
    String payload = String.format("{\"fingerprint\":\"%s\", \"signature\":\"%s\"}", new Object[] { this.fingerprint, Base64.getEncoder().encodeToString(signature) });
    toServer.add(new NativePacketPayload(payload.getBytes(Charset.defaultCharset())));
    return true;
  }
  
  private void initializePrivateKey() {
    ConfigFileReader.ConfigFile configFile;
    if (this.privateKey != null)
      return; 
    try {
      String configFilePath = this.protocol.getPropertySet().getStringProperty(PropertyKey.ociConfigFile.getKeyName()).getStringValue();
      if (StringUtils.isNullOrEmpty(configFilePath)) {
        configFile = ConfigFileReader.parseDefault();
      } else if (Files.exists(Paths.get(configFilePath, new String[0]), new java.nio.file.LinkOption[0])) {
        configFile = ConfigFileReader.parse(configFilePath);
      } else {
        throw ExceptionFactory.createException("configuration file does not exist");
      } 
    } catch (NoClassDefFoundError e) {
      throw ExceptionFactory.createException(Messages.getString("AuthenticationOciClientPlugin.SdkNotFound"), e);
    } catch (IOException e) {
      throw ExceptionFactory.createException(Messages.getString("AuthenticationOciClientPlugin.OciConfigFileError"), e);
    } 
    this.fingerprint = configFile.get("fingerprint");
    if (StringUtils.isNullOrEmpty(this.fingerprint))
      throw ExceptionFactory.createException(Messages.getString("AuthenticationOciClientPlugin.OciConfigFileMissingEntry")); 
    String keyFilePath = configFile.get("key_file");
    if (StringUtils.isNullOrEmpty(keyFilePath))
      throw ExceptionFactory.createException(Messages.getString("AuthenticationOciClientPlugin.OciConfigFileMissingEntry")); 
    try {
      String key = new String(Files.readAllBytes(Paths.get(keyFilePath, new String[0])), Charset.defaultCharset());
      this.privateKey = ExportControlled.decodeRSAPrivateKey(key);
    } catch (IOException e) {
      throw ExceptionFactory.createException(Messages.getString("AuthenticationOciClientPlugin.PrivateKeyNotFound"), e);
    } catch (RSAException|IllegalArgumentException e) {
      throw ExceptionFactory.createException(Messages.getString("AuthenticationOciClientPlugin.PrivateKeyNotValid"), e);
    } 
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\protocol\a\authentication\AuthenticationOciClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */