package com.mysql.cj.protocol.a;

import com.mysql.cj.protocol.ServerSessionStateController;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class NativeServerSessionStateController implements ServerSessionStateController {
  public static final long SERVER_SESSION_STATE_CHANGED = 16384L;
  
  private NativeServerSessionStateChanges sessionStateChanges;
  
  private List<WeakReference<SessionStateChangesListener>> listeners;
  
  public void setSessionStateChanges(ServerSessionStateChanges changes) {
    this.sessionStateChanges = (NativeServerSessionStateChanges)changes;
    if (this.listeners != null)
      for (WeakReference<SessionStateChangesListener> wr : this.listeners) {
        SessionStateChangesListener l = wr.get();
        if (l != null) {
          l.handleSessionStateChanges(changes);
          continue;
        } 
        this.listeners.remove(wr);
      }  
  }
  
  public NativeServerSessionStateChanges getSessionStateChanges() {
    return this.sessionStateChanges;
  }
  
  public void addSessionStateChangesListener(SessionStateChangesListener l) {
    if (this.listeners == null)
      this.listeners = new ArrayList<>(); 
    for (WeakReference<SessionStateChangesListener> wr : this.listeners) {
      if (l.equals(wr.get()))
        return; 
    } 
    this.listeners.add(new WeakReference<>(l));
  }
  
  public void removeSessionStateChangesListener(SessionStateChangesListener listener) {
    if (this.listeners != null)
      for (WeakReference<SessionStateChangesListener> wr : this.listeners) {
        SessionStateChangesListener l = wr.get();
        if (l == null || l.equals(listener)) {
          this.listeners.remove(wr);
          break;
        } 
      }  
  }
  
  public static class NativeServerSessionStateChanges implements ServerSessionStateChanges {
    private List<SessionStateChange> sessionStateChanges = new ArrayList<>();
    
    public List<SessionStateChange> getSessionStateChangesList() {
      return this.sessionStateChanges;
    }
    
    public NativeServerSessionStateChanges init(NativePacketPayload buf, String encoding) {
      int totalLen = (int)buf.readInteger(NativeConstants.IntegerDataType.INT_LENENC);
      int start = buf.getPosition();
      int end = start + totalLen;
      while (totalLen > 0 && end > start) {
        int type = (int)buf.readInteger(NativeConstants.IntegerDataType.INT1);
        NativePacketPayload b = new NativePacketPayload(buf.readBytes(NativeConstants.StringSelfDataType.STRING_LENENC));
        switch (type) {
          case 0:
            this.sessionStateChanges.add((new SessionStateChange(type))
                .addValue(b.readString(NativeConstants.StringSelfDataType.STRING_LENENC, encoding))
                .addValue(b.readString(NativeConstants.StringSelfDataType.STRING_LENENC, encoding)));
            break;
          case 1:
          case 4:
          case 5:
            this.sessionStateChanges.add((new SessionStateChange(type)).addValue(b.readString(NativeConstants.StringSelfDataType.STRING_LENENC, encoding)));
            break;
          case 3:
            b.readInteger(NativeConstants.IntegerDataType.INT1);
            this.sessionStateChanges.add((new SessionStateChange(type)).addValue(b.readString(NativeConstants.StringSelfDataType.STRING_LENENC, encoding)));
            break;
          default:
            this.sessionStateChanges
              .add((new SessionStateChange(type)).addValue(b.readString(NativeConstants.StringLengthDataType.STRING_FIXED, encoding, b.getPayloadLength())));
            break;
        } 
        start = buf.getPosition();
      } 
      return this;
    }
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\protocol\a\NativeServerSessionStateController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */