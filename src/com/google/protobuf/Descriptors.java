package com.google.protobuf;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.logging.Logger;

public final class Descriptors {
  private static final Logger logger = Logger.getLogger(Descriptors.class.getName());
  
  public static final class FileDescriptor extends GenericDescriptor {
    private DescriptorProtos.FileDescriptorProto proto;
    
    private final Descriptor[] messageTypes;
    
    private final EnumDescriptor[] enumTypes;
    
    private final ServiceDescriptor[] services;
    
    private final FieldDescriptor[] extensions;
    
    private final FileDescriptor[] dependencies;
    
    private final FileDescriptor[] publicDependencies;
    
    private final DescriptorPool pool;
    
    public DescriptorProtos.FileDescriptorProto toProto() {
      return this.proto;
    }
    
    public String getName() {
      return this.proto.getName();
    }
    
    public FileDescriptor getFile() {
      return this;
    }
    
    public String getFullName() {
      return this.proto.getName();
    }
    
    public String getPackage() {
      return this.proto.getPackage();
    }
    
    public DescriptorProtos.FileOptions getOptions() {
      return this.proto.getOptions();
    }
    
    public List<Descriptor> getMessageTypes() {
      return Collections.unmodifiableList(Arrays.asList(this.messageTypes));
    }
    
    public List<EnumDescriptor> getEnumTypes() {
      return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
    }
    
    public List<ServiceDescriptor> getServices() {
      return Collections.unmodifiableList(Arrays.asList(this.services));
    }
    
    public List<FieldDescriptor> getExtensions() {
      return Collections.unmodifiableList(Arrays.asList(this.extensions));
    }
    
    public List<FileDescriptor> getDependencies() {
      return Collections.unmodifiableList(Arrays.asList(this.dependencies));
    }
    
    public List<FileDescriptor> getPublicDependencies() {
      return Collections.unmodifiableList(Arrays.asList(this.publicDependencies));
    }
    
    @Deprecated
    public static interface InternalDescriptorAssigner {
      ExtensionRegistry assignDescriptors(FileDescriptor param2FileDescriptor);
    }
    
    public enum Syntax {
      UNKNOWN("unknown"),
      PROTO2("proto2"),
      PROTO3("proto3");
      
      private final String name;
      
      Syntax(String name) {
        this.name = name;
      }
    }
    
    public Syntax getSyntax() {
      if (Syntax.PROTO3.name.equals(this.proto.getSyntax()))
        return Syntax.PROTO3; 
      return Syntax.PROTO2;
    }
    
    public Descriptor findMessageTypeByName(String name) {
      if (name.indexOf('.') != -1)
        return null; 
      String packageName = getPackage();
      if (!packageName.isEmpty())
        name = packageName + '.' + name; 
      GenericDescriptor result = this.pool.findSymbol(name);
      if (result != null && result instanceof Descriptor && result.getFile() == this)
        return (Descriptor)result;
      return null;
    }
    
    public EnumDescriptor findEnumTypeByName(String name) {
      if (name.indexOf('.') != -1)
        return null; 
      String packageName = getPackage();
      if (!packageName.isEmpty())
        name = packageName + '.' + name; 
      GenericDescriptor result = this.pool.findSymbol(name);
      if (result != null && result instanceof EnumDescriptor && result.getFile() == this)
        return (EnumDescriptor)result;
      return null;
    }
    
    public ServiceDescriptor findServiceByName(String name) {
      if (name.indexOf('.') != -1)
        return null; 
      String packageName = getPackage();
      if (!packageName.isEmpty())
        name = packageName + '.' + name; 
      GenericDescriptor result = this.pool.findSymbol(name);
      if (result != null && result instanceof ServiceDescriptor && result.getFile() == this)
        return (ServiceDescriptor)result;
      return null;
    }
    
    public FieldDescriptor findExtensionByName(String name) {
      if (name.indexOf('.') != -1)
        return null; 
      String packageName = getPackage();
      if (!packageName.isEmpty())
        name = packageName + '.' + name; 
      GenericDescriptor result = this.pool.findSymbol(name);
      if (result != null && result instanceof FieldDescriptor && result.getFile() == this)
        return (FieldDescriptor)result;
      return null;
    }
    
    public static FileDescriptor buildFrom(DescriptorProtos.FileDescriptorProto proto, FileDescriptor[] dependencies) throws DescriptorValidationException {
      return buildFrom(proto, dependencies, false);
    }
    
    public static FileDescriptor buildFrom(DescriptorProtos.FileDescriptorProto proto, FileDescriptor[] dependencies, boolean allowUnknownDependencies) throws DescriptorValidationException {
      DescriptorPool pool = new DescriptorPool(dependencies, allowUnknownDependencies);
      FileDescriptor result = new FileDescriptor(proto, dependencies, pool, allowUnknownDependencies);
      result.crossLink();
      return result;
    }
    
    private static byte[] latin1Cat(String[] strings) {
      if (strings.length == 1)
        return strings[0].getBytes(Internal.ISO_8859_1); 
      StringBuilder descriptorData = new StringBuilder();
      for (String part : strings)
        descriptorData.append(part); 
      return descriptorData.toString().getBytes(Internal.ISO_8859_1);
    }
    
    private static FileDescriptor[] findDescriptors(Class<?> descriptorOuterClass, String[] dependencyClassNames, String[] dependencyFileNames) {
      List<FileDescriptor> descriptors = new ArrayList<>();
      for (int i = 0; i < dependencyClassNames.length; i++) {
        try {
          Class<?> clazz = descriptorOuterClass.getClassLoader().loadClass(dependencyClassNames[i]);
          descriptors.add((FileDescriptor)clazz.getField("descriptor").get(null));
        } catch (Exception e) {
          Descriptors.logger.warning("Descriptors for \"" + dependencyFileNames[i] + "\" can not be found.");
        } 
      } 
      return descriptors.<FileDescriptor>toArray(new FileDescriptor[0]);
    }
    
    @Deprecated
    public static void internalBuildGeneratedFileFrom(String[] descriptorDataParts, FileDescriptor[] dependencies, InternalDescriptorAssigner descriptorAssigner) {
      DescriptorProtos.FileDescriptorProto proto;
      FileDescriptor result;
      byte[] descriptorBytes = latin1Cat(descriptorDataParts);
      try {
        proto = DescriptorProtos.FileDescriptorProto.parseFrom(descriptorBytes);
      } catch (InvalidProtocolBufferException e) {
        throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
      } 
      try {
        result = buildFrom(proto, dependencies, true);
      } catch (DescriptorValidationException e) {
        throw new IllegalArgumentException("Invalid embedded descriptor for \"" + proto
            .getName() + "\".", e);
      } 
      ExtensionRegistry registry = descriptorAssigner.assignDescriptors(result);
      if (registry != null) {
        try {
          proto = DescriptorProtos.FileDescriptorProto.parseFrom(descriptorBytes, registry);
        } catch (InvalidProtocolBufferException e) {
          throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
        } 
        result.setProto(proto);
      } 
    }
    
    public static FileDescriptor internalBuildGeneratedFileFrom(String[] descriptorDataParts, FileDescriptor[] dependencies) {
      DescriptorProtos.FileDescriptorProto proto;
      byte[] descriptorBytes = latin1Cat(descriptorDataParts);
      try {
        proto = DescriptorProtos.FileDescriptorProto.parseFrom(descriptorBytes);
      } catch (InvalidProtocolBufferException e) {
        throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
      } 
      try {
        return buildFrom(proto, dependencies, true);
      } catch (DescriptorValidationException e) {
        throw new IllegalArgumentException("Invalid embedded descriptor for \"" + proto
            .getName() + "\".", e);
      } 
    }
    
    @Deprecated
    public static void internalBuildGeneratedFileFrom(String[] descriptorDataParts, Class<?> descriptorOuterClass, String[] dependencyClassNames, String[] dependencyFileNames, InternalDescriptorAssigner descriptorAssigner) {
      FileDescriptor[] dependencies = findDescriptors(descriptorOuterClass, dependencyClassNames, dependencyFileNames);
      internalBuildGeneratedFileFrom(descriptorDataParts, dependencies, descriptorAssigner);
    }
    
    public static FileDescriptor internalBuildGeneratedFileFrom(String[] descriptorDataParts, Class<?> descriptorOuterClass, String[] dependencyClassNames, String[] dependencyFileNames) {
      FileDescriptor[] dependencies = findDescriptors(descriptorOuterClass, dependencyClassNames, dependencyFileNames);
      return internalBuildGeneratedFileFrom(descriptorDataParts, dependencies);
    }
    
    public static void internalUpdateFileDescriptor(FileDescriptor descriptor, ExtensionRegistry registry) {
      DescriptorProtos.FileDescriptorProto proto;
      ByteString bytes = descriptor.proto.toByteString();
      try {
        proto = DescriptorProtos.FileDescriptorProto.parseFrom(bytes, registry);
      } catch (InvalidProtocolBufferException e) {
        throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
      } 
      descriptor.setProto(proto);
    }
    
    private FileDescriptor(DescriptorProtos.FileDescriptorProto proto, FileDescriptor[] dependencies, DescriptorPool pool, boolean allowUnknownDependencies) throws DescriptorValidationException {
      this.pool = pool;
      this.proto = proto;
      this.dependencies = (FileDescriptor[])dependencies.clone();
      HashMap<String, FileDescriptor> nameToFileMap = new HashMap<>();
      for (FileDescriptor file : dependencies)
        nameToFileMap.put(file.getName(), file); 
      List<FileDescriptor> publicDependencies = new ArrayList<>();
      int i;
      for (i = 0; i < proto.getPublicDependencyCount(); i++) {
        int index = proto.getPublicDependency(i);
        if (index < 0 || index >= proto.getDependencyCount())
          throw new DescriptorValidationException(this, "Invalid public dependency index.");
        String name = proto.getDependency(index);
        FileDescriptor file = nameToFileMap.get(name);
        if (file == null) {
          if (!allowUnknownDependencies)
            throw new DescriptorValidationException(this, "Invalid public dependency: " + name);
        } else {
          publicDependencies.add(file);
        } 
      } 
      this.publicDependencies = new FileDescriptor[publicDependencies.size()];
      publicDependencies.toArray(this.publicDependencies);
      pool.addPackage(getPackage(), this);
      this.messageTypes = new Descriptor[proto.getMessageTypeCount()];
      for (i = 0; i < proto.getMessageTypeCount(); i++)
        this.messageTypes[i] = new Descriptor(proto.getMessageType(i), this, null, i);
      this.enumTypes = new EnumDescriptor[proto.getEnumTypeCount()];
      for (i = 0; i < proto.getEnumTypeCount(); i++)
        this.enumTypes[i] = new EnumDescriptor(proto.getEnumType(i), this, null, i);
      this.services = new ServiceDescriptor[proto.getServiceCount()];
      for (i = 0; i < proto.getServiceCount(); i++)
        this.services[i] = new ServiceDescriptor(proto.getService(i), this, i);
      this.extensions = new FieldDescriptor[proto.getExtensionCount()];
      for (i = 0; i < proto.getExtensionCount(); i++)
        this.extensions[i] = new FieldDescriptor(proto.getExtension(i), this, null, i, true);
    }
    
    FileDescriptor(String packageName, Descriptor message) throws DescriptorValidationException {
      this.pool = new DescriptorPool(new FileDescriptor[0], true);
      this
        
        .proto = DescriptorProtos.FileDescriptorProto.newBuilder().setName(message.getFullName() + ".placeholder.proto").setPackage(packageName).addMessageType(message.toProto()).build();
      this.dependencies = new FileDescriptor[0];
      this.publicDependencies = new FileDescriptor[0];
      this.messageTypes = new Descriptor[] { message };
      this.enumTypes = new EnumDescriptor[0];
      this.services = new ServiceDescriptor[0];
      this.extensions = new FieldDescriptor[0];
      this.pool.addPackage(packageName, this);
      this.pool.addSymbol(message);
    }
    
    private void crossLink() throws DescriptorValidationException {
      for (Descriptor messageType : this.messageTypes)
        messageType.crossLink(); 
      for (ServiceDescriptor service : this.services)
        service.crossLink(); 
      for (FieldDescriptor extension : this.extensions)
        extension.crossLink(); 
    }
    
    private void setProto(DescriptorProtos.FileDescriptorProto proto) {
      this.proto = proto;
      int i;
      for (i = 0; i < this.messageTypes.length; i++)
        this.messageTypes[i].setProto(proto.getMessageType(i)); 
      for (i = 0; i < this.enumTypes.length; i++)
        this.enumTypes[i].setProto(proto.getEnumType(i)); 
      for (i = 0; i < this.services.length; i++)
        this.services[i].setProto(proto.getService(i)); 
      for (i = 0; i < this.extensions.length; i++)
        this.extensions[i].setProto(proto.getExtension(i)); 
    }
    
    boolean supportsUnknownEnumValue() {
      return (getSyntax() == Syntax.PROTO3);
    }
  }
  
  public static final class Descriptor extends GenericDescriptor {
    private final int index;
    
    private DescriptorProtos.DescriptorProto proto;
    
    private final String fullName;
    
    private final FileDescriptor file;
    
    private final Descriptor containingType;
    
    private final Descriptor[] nestedTypes;
    
    private final EnumDescriptor[] enumTypes;
    
    private final FieldDescriptor[] fields;
    
    private final FieldDescriptor[] extensions;
    
    private final OneofDescriptor[] oneofs;
    
    public int getIndex() {
      return this.index;
    }
    
    public DescriptorProtos.DescriptorProto toProto() {
      return this.proto;
    }
    
    public String getName() {
      return this.proto.getName();
    }
    
    public String getFullName() {
      return this.fullName;
    }
    
    public FileDescriptor getFile() {
      return this.file;
    }
    
    public Descriptor getContainingType() {
      return this.containingType;
    }
    
    public DescriptorProtos.MessageOptions getOptions() {
      return this.proto.getOptions();
    }
    
    public List<FieldDescriptor> getFields() {
      return Collections.unmodifiableList(Arrays.asList(this.fields));
    }
    
    public List<OneofDescriptor> getOneofs() {
      return Collections.unmodifiableList(Arrays.asList(this.oneofs));
    }
    
    public List<FieldDescriptor> getExtensions() {
      return Collections.unmodifiableList(Arrays.asList(this.extensions));
    }
    
    public List<Descriptor> getNestedTypes() {
      return Collections.unmodifiableList(Arrays.asList(this.nestedTypes));
    }
    
    public List<EnumDescriptor> getEnumTypes() {
      return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
    }
    
    public boolean isExtensionNumber(int number) {
      for (DescriptorProtos.DescriptorProto.ExtensionRange range : this.proto.getExtensionRangeList()) {
        if (range.getStart() <= number && number < range.getEnd())
          return true; 
      } 
      return false;
    }
    
    public boolean isReservedNumber(int number) {
      for (DescriptorProtos.DescriptorProto.ReservedRange range : this.proto.getReservedRangeList()) {
        if (range.getStart() <= number && number < range.getEnd())
          return true; 
      } 
      return false;
    }
    
    public boolean isReservedName(String name) {
      Internal.checkNotNull(name);
      for (String reservedName : this.proto.getReservedNameList()) {
        if (reservedName.equals(name))
          return true; 
      } 
      return false;
    }
    
    public boolean isExtendable() {
      return (this.proto.getExtensionRangeList().size() != 0);
    }
    
    public FieldDescriptor findFieldByName(String name) {
      GenericDescriptor result = this.file.pool.findSymbol(this.fullName + '.' + name);
      if (result != null && result instanceof FieldDescriptor)
        return (FieldDescriptor)result;
      return null;
    }
    
    public FieldDescriptor findFieldByNumber(int number) {
      return (FieldDescriptor)this.file.pool.fieldsByNumber.get(new DescriptorPool.DescriptorIntPair(this, number));
    }
    
    public Descriptor findNestedTypeByName(String name) {
      GenericDescriptor result = this.file.pool.findSymbol(this.fullName + '.' + name);
      if (result != null && result instanceof Descriptor)
        return (Descriptor)result; 
      return null;
    }
    
    public EnumDescriptor findEnumTypeByName(String name) {
      GenericDescriptor result = this.file.pool.findSymbol(this.fullName + '.' + name);
      if (result != null && result instanceof EnumDescriptor)
        return (EnumDescriptor)result;
      return null;
    }
    
    Descriptor(String fullname) throws DescriptorValidationException {
      String name = fullname;
      String packageName = "";
      int pos = fullname.lastIndexOf('.');
      if (pos != -1) {
        name = fullname.substring(pos + 1);
        packageName = fullname.substring(0, pos);
      } 
      this.index = 0;
      this
        
        .proto = DescriptorProtos.DescriptorProto.newBuilder().setName(name).addExtensionRange(DescriptorProtos.DescriptorProto.ExtensionRange.newBuilder().setStart(1).setEnd(536870912).build()).build();
      this.fullName = fullname;
      this.containingType = null;
      this.nestedTypes = new Descriptor[0];
      this.enumTypes = new EnumDescriptor[0];
      this.fields = new FieldDescriptor[0];
      this.extensions = new FieldDescriptor[0];
      this.oneofs = new OneofDescriptor[0];
      this.file = new FileDescriptor(packageName, this);
    }
    
    private Descriptor(DescriptorProtos.DescriptorProto proto, FileDescriptor file, Descriptor parent, int index) throws DescriptorValidationException {
      this.index = index;
      this.proto = proto;
      this.fullName = Descriptors.computeFullName(file, parent, proto.getName());
      this.file = file;
      this.containingType = parent;
      this.oneofs = new OneofDescriptor[proto.getOneofDeclCount()];
      int i;
      for (i = 0; i < proto.getOneofDeclCount(); i++)
        this.oneofs[i] = new OneofDescriptor(proto.getOneofDecl(i), file, this, i);
      this.nestedTypes = new Descriptor[proto.getNestedTypeCount()];
      for (i = 0; i < proto.getNestedTypeCount(); i++)
        this.nestedTypes[i] = new Descriptor(proto.getNestedType(i), file, this, i); 
      this.enumTypes = new EnumDescriptor[proto.getEnumTypeCount()];
      for (i = 0; i < proto.getEnumTypeCount(); i++)
        this.enumTypes[i] = new EnumDescriptor(proto.getEnumType(i), file, this, i);
      this.fields = new FieldDescriptor[proto.getFieldCount()];
      for (i = 0; i < proto.getFieldCount(); i++)
        this.fields[i] = new FieldDescriptor(proto.getField(i), file, this, i, false);
      this.extensions = new FieldDescriptor[proto.getExtensionCount()];
      for (i = 0; i < proto.getExtensionCount(); i++)
        this.extensions[i] = new FieldDescriptor(proto.getExtension(i), file, this, i, true);
      for (i = 0; i < proto.getOneofDeclCount(); i++) {
        (this.oneofs[i]).fields = new FieldDescriptor[this.oneofs[i].getFieldCount()];
        (this.oneofs[i]).fieldCount = 0;
      } 
      for (i = 0; i < proto.getFieldCount(); i++) {
        OneofDescriptor oneofDescriptor = this.fields[i].getContainingOneof();
        if (oneofDescriptor != null)
          oneofDescriptor.fields[oneofDescriptor.fieldCount++] = this.fields[i]; 
      } 
      file.pool.addSymbol(this);
    }
    
    private void crossLink() throws DescriptorValidationException {
      for (Descriptor nestedType : this.nestedTypes)
        nestedType.crossLink(); 
      for (FieldDescriptor field : this.fields)
        field.crossLink(); 
      for (FieldDescriptor extension : this.extensions)
        extension.crossLink(); 
    }
    
    private void setProto(DescriptorProtos.DescriptorProto proto) {
      this.proto = proto;
      int i;
      for (i = 0; i < this.nestedTypes.length; i++)
        this.nestedTypes[i].setProto(proto.getNestedType(i)); 
      for (i = 0; i < this.oneofs.length; i++)
        this.oneofs[i].setProto(proto.getOneofDecl(i)); 
      for (i = 0; i < this.enumTypes.length; i++)
        this.enumTypes[i].setProto(proto.getEnumType(i)); 
      for (i = 0; i < this.fields.length; i++)
        this.fields[i].setProto(proto.getField(i)); 
      for (i = 0; i < this.extensions.length; i++)
        this.extensions[i].setProto(proto.getExtension(i)); 
    }
  }
  
  public static final class FieldDescriptor extends GenericDescriptor implements Comparable<FieldDescriptor>, FieldSet.FieldDescriptorLite<FieldDescriptor> {
    public int getIndex() {
      return this.index;
    }
    
    public DescriptorProtos.FieldDescriptorProto toProto() {
      return this.proto;
    }
    
    public String getName() {
      return this.proto.getName();
    }
    
    public int getNumber() {
      return this.proto.getNumber();
    }
    
    public String getFullName() {
      return this.fullName;
    }
    
    public String getJsonName() {
      return this.jsonName;
    }
    
    public JavaType getJavaType() {
      return this.type.getJavaType();
    }
    
    public WireFormat.JavaType getLiteJavaType() {
      return getLiteType().getJavaType();
    }
    
    public FileDescriptor getFile() {
      return this.file;
    }
    
    public Type getType() {
      return this.type;
    }
    
    public WireFormat.FieldType getLiteType() {
      return table[this.type.ordinal()];
    }
    
    public boolean needsUtf8Check() {
      if (this.type != Type.STRING)
        return false; 
      if (getContainingType().getOptions().getMapEntry())
        return true; 
      if (getFile().getSyntax() == FileDescriptor.Syntax.PROTO3)
        return true; 
      return getFile().getOptions().getJavaStringCheckUtf8();
    }
    
    public boolean isMapField() {
      return (getType() == Type.MESSAGE && 
        isRepeated() && 
        getMessageType().getOptions().getMapEntry());
    }
    
    private static final WireFormat.FieldType[] table = WireFormat.FieldType.values();
    
    private final int index;
    
    private DescriptorProtos.FieldDescriptorProto proto;
    
    private final String fullName;
    
    private final String jsonName;
    
    private final FileDescriptor file;
    
    private final Descriptor extensionScope;
    
    private Type type;
    
    private Descriptor containingType;
    
    private Descriptor messageType;
    
    private OneofDescriptor containingOneof;
    
    private EnumDescriptor enumType;
    
    private Object defaultValue;
    
    public boolean isRequired() {
      return (this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_REQUIRED);
    }
    
    public boolean isOptional() {
      return (this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_OPTIONAL);
    }
    
    public boolean isRepeated() {
      return (this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_REPEATED);
    }
    
    public boolean isPacked() {
      if (!isPackable())
        return false; 
      if (getFile().getSyntax() == FileDescriptor.Syntax.PROTO2)
        return getOptions().getPacked(); 
      return (!getOptions().hasPacked() || getOptions().getPacked());
    }
    
    public boolean isPackable() {
      return (isRepeated() && getLiteType().isPackable());
    }
    
    public boolean hasDefaultValue() {
      return this.proto.hasDefaultValue();
    }
    
    public Object getDefaultValue() {
      if (getJavaType() == JavaType.MESSAGE)
        throw new UnsupportedOperationException("FieldDescriptor.getDefaultValue() called on an embedded message field."); 
      return this.defaultValue;
    }
    
    public DescriptorProtos.FieldOptions getOptions() {
      return this.proto.getOptions();
    }
    
    public boolean isExtension() {
      return this.proto.hasExtendee();
    }
    
    public Descriptor getContainingType() {
      return this.containingType;
    }
    
    public OneofDescriptor getContainingOneof() {
      return this.containingOneof;
    }
    
    public Descriptor getExtensionScope() {
      if (!isExtension())
        throw new UnsupportedOperationException(
            String.format("This field is not an extension. (%s)", new Object[] { this.fullName })); 
      return this.extensionScope;
    }
    
    public Descriptor getMessageType() {
      if (getJavaType() != JavaType.MESSAGE)
        throw new UnsupportedOperationException(
            String.format("This field is not of message type. (%s)", new Object[] { this.fullName })); 
      return this.messageType;
    }
    
    public EnumDescriptor getEnumType() {
      if (getJavaType() != JavaType.ENUM)
        throw new UnsupportedOperationException(
            String.format("This field is not of enum type. (%s)", new Object[] { this.fullName })); 
      return this.enumType;
    }
    
    public int compareTo(FieldDescriptor other) {
      if (other.containingType != this.containingType)
        throw new IllegalArgumentException("FieldDescriptors can only be compared to other FieldDescriptors for fields of the same message type."); 
      return getNumber() - other.getNumber();
    }
    
    public String toString() {
      return getFullName();
    }
    
    public enum Type {
      DOUBLE((String) JavaType.DOUBLE),
      FLOAT((String) JavaType.FLOAT),
      INT64((String) JavaType.LONG),
      UINT64((String) JavaType.LONG),
      INT32((String) JavaType.INT),
      FIXED64((String) JavaType.LONG),
      FIXED32((String) JavaType.INT),
      BOOL((String) JavaType.BOOLEAN),
      STRING((String) JavaType.STRING),
      GROUP((String) JavaType.MESSAGE),
      MESSAGE((String) JavaType.MESSAGE),
      BYTES((String) JavaType.BYTE_STRING),
      UINT32((String) JavaType.INT),
      ENUM((String) JavaType.ENUM),
      SFIXED32((String) JavaType.INT),
      SFIXED64((String) JavaType.LONG),
      SINT32((String) JavaType.INT),
      SINT64((String) JavaType.LONG);
      
      private JavaType javaType;
      
      Type(JavaType javaType) {
        this.javaType = javaType;
      }
      
      public DescriptorProtos.FieldDescriptorProto.Type toProto() {
        return DescriptorProtos.FieldDescriptorProto.Type.forNumber(ordinal() + 1);
      }
      
      public JavaType getJavaType() {
        return this.javaType;
      }
    }
    
    static {
      if ((Type.values()).length != (DescriptorProtos.FieldDescriptorProto.Type.values()).length)
        throw new RuntimeException("descriptor.proto has a new declared type but Descriptors.java wasn't updated."); 
    }
    
    public enum JavaType {
      INT((String)Integer.valueOf(0)),
      LONG((String)Long.valueOf(0L)),
      FLOAT((String)Float.valueOf(0.0F)),
      DOUBLE((String)Double.valueOf(0.0D)),
      BOOLEAN((String)Boolean.valueOf(false)),
      STRING(""),
      BYTE_STRING((String)ByteString.EMPTY),
      ENUM(null),
      MESSAGE(null);
      
      private final Object defaultDefault;
      
      JavaType(Object defaultDefault) {
        this.defaultDefault = defaultDefault;
      }
    }
    
    private static String fieldNameToJsonName(String name) {
      int length = name.length();
      StringBuilder result = new StringBuilder(length);
      boolean isNextUpperCase = false;
      for (int i = 0; i < length; i++) {
        char ch = name.charAt(i);
        if (ch == '_') {
          isNextUpperCase = true;
        } else if (isNextUpperCase) {
          if ('a' <= ch && ch <= 'z')
            ch = (char)(ch - 97 + 65); 
          result.append(ch);
          isNextUpperCase = false;
        } else {
          result.append(ch);
        } 
      } 
      return result.toString();
    }
    
    private FieldDescriptor(DescriptorProtos.FieldDescriptorProto proto, FileDescriptor file, Descriptor parent, int index, boolean isExtension) throws DescriptorValidationException {
      this.index = index;
      this.proto = proto;
      this.fullName = Descriptors.computeFullName(file, parent, proto.getName());
      this.file = file;
      if (proto.hasJsonName()) {
        this.jsonName = proto.getJsonName();
      } else {
        this.jsonName = fieldNameToJsonName(proto.getName());
      } 
      if (proto.hasType())
        this.type = Type.valueOf(proto.getType()); 
      if (getNumber() <= 0)
        throw new DescriptorValidationException(this, "Field numbers must be positive integers.");
      if (isExtension) {
        if (!proto.hasExtendee())
          throw new DescriptorValidationException(this, "FieldDescriptorProto.extendee not set for extension field.");
        this.containingType = null;
        if (parent != null) {
          this.extensionScope = parent;
        } else {
          this.extensionScope = null;
        } 
        if (proto.hasOneofIndex())
          throw new DescriptorValidationException(this, "FieldDescriptorProto.oneof_index set for extension field.");
        this.containingOneof = null;
      } else {
        if (proto.hasExtendee())
          throw new DescriptorValidationException(this, "FieldDescriptorProto.extendee set for non-extension field.");
        this.containingType = parent;
        if (proto.hasOneofIndex()) {
          if (proto.getOneofIndex() < 0 || proto
            .getOneofIndex() >= parent.toProto().getOneofDeclCount())
            throw new DescriptorValidationException(this, "FieldDescriptorProto.oneof_index is out of range for type " + parent
                
                .getName()); 
          this.containingOneof = parent.getOneofs().get(proto.getOneofIndex());
          this.containingOneof.fieldCount++;
        } else {
          this.containingOneof = null;
        } 
        this.extensionScope = null;
      } 
      file.pool.addSymbol(this);
    }
    
    private void crossLink() throws DescriptorValidationException {
      if (this.proto.hasExtendee()) {
        GenericDescriptor extendee = this.file.pool.lookupSymbol(this.proto
            .getExtendee(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
        if (!(extendee instanceof Descriptor))
          throw new DescriptorValidationException(this, '"' + this.proto
              .getExtendee() + "\" is not a message type."); 
        this.containingType = (Descriptor)extendee;
        if (!getContainingType().isExtensionNumber(getNumber()))
          throw new DescriptorValidationException(this, '"' +
              
              getContainingType().getFullName() + "\" does not declare " + 
              
              getNumber() + " as an extension number."); 
      } 
      if (this.proto.hasTypeName()) {
        GenericDescriptor typeDescriptor = this.file.pool.lookupSymbol(this.proto
            .getTypeName(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
        if (!this.proto.hasType())
          if (typeDescriptor instanceof Descriptor) {
            this.type = Type.MESSAGE;
          } else if (typeDescriptor instanceof EnumDescriptor) {
            this.type = Type.ENUM;
          } else {
            throw new DescriptorValidationException(this, '"' + this.proto
                .getTypeName() + "\" is not a type.");
          }  
        if (getJavaType() == JavaType.MESSAGE) {
          if (!(typeDescriptor instanceof Descriptor))
            throw new DescriptorValidationException(this, '"' + this.proto
                .getTypeName() + "\" is not a message type."); 
          this.messageType = (Descriptor)typeDescriptor;
          if (this.proto.hasDefaultValue())
            throw new DescriptorValidationException(this, "Messages can't have default values.");
        } else if (getJavaType() == JavaType.ENUM) {
          if (!(typeDescriptor instanceof EnumDescriptor))
            throw new DescriptorValidationException(this, '"' + this.proto
                .getTypeName() + "\" is not an enum type."); 
          this.enumType = (EnumDescriptor)typeDescriptor;
        } else {
          throw new DescriptorValidationException(this, "Field with primitive type has type_name.");
        } 
      } else if (getJavaType() == JavaType.MESSAGE || getJavaType() == JavaType.ENUM) {
        throw new DescriptorValidationException(this, "Field with message or enum type missing type_name.");
      } 
      if (this.proto.getOptions().getPacked() && !isPackable())
        throw new DescriptorValidationException(this, "[packed = true] can only be specified for repeated primitive fields.");
      if (this.proto.hasDefaultValue()) {
        if (isRepeated())
          throw new DescriptorValidationException(this, "Repeated fields cannot have default values.");
        try {
          switch (getType()) {
            case ENUM:
            case MESSAGE:
            case null:
              this.defaultValue = Integer.valueOf(TextFormat.parseInt32(this.proto.getDefaultValue()));
              break;
            case null:
            case null:
              this.defaultValue = Integer.valueOf(TextFormat.parseUInt32(this.proto.getDefaultValue()));
              break;
            case null:
            case null:
            case null:
              this.defaultValue = Long.valueOf(TextFormat.parseInt64(this.proto.getDefaultValue()));
              break;
            case null:
            case null:
              this.defaultValue = Long.valueOf(TextFormat.parseUInt64(this.proto.getDefaultValue()));
              break;
            case null:
              if (this.proto.getDefaultValue().equals("inf")) {
                this.defaultValue = Float.valueOf(Float.POSITIVE_INFINITY);
                break;
              } 
              if (this.proto.getDefaultValue().equals("-inf")) {
                this.defaultValue = Float.valueOf(Float.NEGATIVE_INFINITY);
                break;
              } 
              if (this.proto.getDefaultValue().equals("nan")) {
                this.defaultValue = Float.valueOf(Float.NaN);
                break;
              } 
              this.defaultValue = Float.valueOf(this.proto.getDefaultValue());
              break;
            case null:
              if (this.proto.getDefaultValue().equals("inf")) {
                this.defaultValue = Double.valueOf(Double.POSITIVE_INFINITY);
                break;
              } 
              if (this.proto.getDefaultValue().equals("-inf")) {
                this.defaultValue = Double.valueOf(Double.NEGATIVE_INFINITY);
                break;
              } 
              if (this.proto.getDefaultValue().equals("nan")) {
                this.defaultValue = Double.valueOf(Double.NaN);
                break;
              } 
              this.defaultValue = Double.valueOf(this.proto.getDefaultValue());
              break;
            case null:
              this.defaultValue = Boolean.valueOf(this.proto.getDefaultValue());
              break;
            case null:
              this.defaultValue = this.proto.getDefaultValue();
              break;
            case null:
              try {
                this.defaultValue = TextFormat.unescapeBytes(this.proto.getDefaultValue());
              } catch (InvalidEscapeSequenceException e) {
                throw new DescriptorValidationException(this, "Couldn't parse default value: " + e
                    .getMessage(), e);
              } 
              break;
            case null:
              this.defaultValue = this.enumType.findValueByName(this.proto.getDefaultValue());
              if (this.defaultValue == null)
                throw new DescriptorValidationException(this, "Unknown enum default value: \"" + this.proto
                    .getDefaultValue() + '"'); 
              break;
            case null:
            case null:
              throw new DescriptorValidationException(this, "Message type had default value.");
          } 
        } catch (NumberFormatException e) {
          throw new DescriptorValidationException(this, "Could not parse default value: \"" + this.proto
              .getDefaultValue() + '"', e);
        } 
      } else if (isRepeated()) {
        this.defaultValue = Collections.emptyList();
      } else {
        switch (getJavaType()) {
          case ENUM:
            this.defaultValue = this.enumType.getValues().get(0);
            break;
          case MESSAGE:
            this.defaultValue = null;
            break;
          default:
            this.defaultValue = (getJavaType()).defaultDefault;
            break;
        } 
      } 
      if (!isExtension())
        this.file.pool.addFieldByNumber(this); 
      if (this.containingType != null && this.containingType.getOptions().getMessageSetWireFormat())
        if (isExtension()) {
          if (!isOptional() || getType() != Type.MESSAGE)
            throw new DescriptorValidationException(this, "Extensions of MessageSets must be optional messages.");
        } else {
          throw new DescriptorValidationException(this, "MessageSets cannot have fields, only extensions.");
        }  
    }
    
    private void setProto(DescriptorProtos.FieldDescriptorProto proto) {
      this.proto = proto;
    }
    
    public MessageLite.Builder internalMergeFrom(MessageLite.Builder to, MessageLite from) {
      return ((Message.Builder)to).mergeFrom((Message)from);
    }
  }
  
  public static final class EnumDescriptor extends GenericDescriptor implements Internal.EnumLiteMap<EnumValueDescriptor> {
    private final int index;
    
    private DescriptorProtos.EnumDescriptorProto proto;
    
    private final String fullName;
    
    private final FileDescriptor file;
    
    private final Descriptor containingType;
    
    private EnumValueDescriptor[] values;
    
    public int getIndex() {
      return this.index;
    }
    
    public DescriptorProtos.EnumDescriptorProto toProto() {
      return this.proto;
    }
    
    public String getName() {
      return this.proto.getName();
    }
    
    public String getFullName() {
      return this.fullName;
    }
    
    public FileDescriptor getFile() {
      return this.file;
    }
    
    public Descriptor getContainingType() {
      return this.containingType;
    }
    
    public DescriptorProtos.EnumOptions getOptions() {
      return this.proto.getOptions();
    }
    
    public List<EnumValueDescriptor> getValues() {
      return Collections.unmodifiableList(Arrays.asList(this.values));
    }
    
    public EnumValueDescriptor findValueByName(String name) {
      GenericDescriptor result = this.file.pool.findSymbol(this.fullName + '.' + name);
      if (result != null && result instanceof EnumValueDescriptor)
        return (EnumValueDescriptor)result;
      return null;
    }
    
    public EnumValueDescriptor findValueByNumber(int number) {
      return (EnumValueDescriptor)this.file.pool.enumValuesByNumber.get(new DescriptorPool.DescriptorIntPair(this, number));
    }
    
    public EnumValueDescriptor findValueByNumberCreatingIfUnknown(int number) {
      EnumValueDescriptor result = findValueByNumber(number);
      if (result != null)
        return result; 
      synchronized (this) {
        Integer key = new Integer(number);
        WeakReference<EnumValueDescriptor> reference = this.unknownValues.get(key);
        if (reference != null)
          result = reference.get(); 
        if (result == null) {
          result = new EnumValueDescriptor(this.file, this, key);
          this.unknownValues.put(key, new WeakReference<>(result));
        } 
      } 
      return result;
    }
    
    int getUnknownEnumValueDescriptorCount() {
      return this.unknownValues.size();
    }
    
    private final WeakHashMap<Integer, WeakReference<EnumValueDescriptor>> unknownValues = new WeakHashMap<>();
    
    private EnumDescriptor(DescriptorProtos.EnumDescriptorProto proto, FileDescriptor file, Descriptor parent, int index) throws DescriptorValidationException {
      this.index = index;
      this.proto = proto;
      this.fullName = Descriptors.computeFullName(file, parent, proto.getName());
      this.file = file;
      this.containingType = parent;
      if (proto.getValueCount() == 0)
        throw new DescriptorValidationException(this, "Enums must contain at least one value.");
      this.values = new EnumValueDescriptor[proto.getValueCount()];
      for (int i = 0; i < proto.getValueCount(); i++)
        this.values[i] = new EnumValueDescriptor(proto.getValue(i), file, this, i);
      file.pool.addSymbol(this);
    }
    
    private void setProto(DescriptorProtos.EnumDescriptorProto proto) {
      this.proto = proto;
      for (int i = 0; i < this.values.length; i++)
        this.values[i].setProto(proto.getValue(i)); 
    }
  }
  
  public static final class EnumValueDescriptor extends GenericDescriptor implements Internal.EnumLite {
    private final int index;
    
    private DescriptorProtos.EnumValueDescriptorProto proto;
    
    private final String fullName;
    
    private final FileDescriptor file;
    
    private final EnumDescriptor type;
    
    public int getIndex() {
      return this.index;
    }
    
    public DescriptorProtos.EnumValueDescriptorProto toProto() {
      return this.proto;
    }
    
    public String getName() {
      return this.proto.getName();
    }
    
    public int getNumber() {
      return this.proto.getNumber();
    }
    
    public String toString() {
      return this.proto.getName();
    }
    
    public String getFullName() {
      return this.fullName;
    }
    
    public FileDescriptor getFile() {
      return this.file;
    }
    
    public EnumDescriptor getType() {
      return this.type;
    }
    
    public DescriptorProtos.EnumValueOptions getOptions() {
      return this.proto.getOptions();
    }
    
    private EnumValueDescriptor(DescriptorProtos.EnumValueDescriptorProto proto, FileDescriptor file, EnumDescriptor parent, int index) throws DescriptorValidationException {
      this.index = index;
      this.proto = proto;
      this.file = file;
      this.type = parent;
      this.fullName = parent.getFullName() + '.' + proto.getName();
      file.pool.addSymbol(this);
      file.pool.addEnumValueByNumber(this);
    }
    
    private EnumValueDescriptor(FileDescriptor file, EnumDescriptor parent, Integer number) {
      String name = "UNKNOWN_ENUM_VALUE_" + parent.getName() + "_" + number;
      DescriptorProtos.EnumValueDescriptorProto proto = DescriptorProtos.EnumValueDescriptorProto.newBuilder().setName(name).setNumber(number.intValue()).build();
      this.index = -1;
      this.proto = proto;
      this.file = file;
      this.type = parent;
      this.fullName = parent.getFullName() + '.' + proto.getName();
    }
    
    private void setProto(DescriptorProtos.EnumValueDescriptorProto proto) {
      this.proto = proto;
    }
  }
  
  public static final class ServiceDescriptor extends GenericDescriptor {
    private final int index;
    
    private DescriptorProtos.ServiceDescriptorProto proto;
    
    private final String fullName;
    
    private final FileDescriptor file;
    
    private MethodDescriptor[] methods;
    
    public int getIndex() {
      return this.index;
    }
    
    public DescriptorProtos.ServiceDescriptorProto toProto() {
      return this.proto;
    }
    
    public String getName() {
      return this.proto.getName();
    }
    
    public String getFullName() {
      return this.fullName;
    }
    
    public FileDescriptor getFile() {
      return this.file;
    }
    
    public DescriptorProtos.ServiceOptions getOptions() {
      return this.proto.getOptions();
    }
    
    public List<MethodDescriptor> getMethods() {
      return Collections.unmodifiableList(Arrays.asList(this.methods));
    }
    
    public MethodDescriptor findMethodByName(String name) {
      GenericDescriptor result = this.file.pool.findSymbol(this.fullName + '.' + name);
      if (result != null && result instanceof MethodDescriptor)
        return (MethodDescriptor)result;
      return null;
    }
    
    private ServiceDescriptor(DescriptorProtos.ServiceDescriptorProto proto, FileDescriptor file, int index) throws DescriptorValidationException {
      this.index = index;
      this.proto = proto;
      this.fullName = Descriptors.computeFullName(file, null, proto.getName());
      this.file = file;
      this.methods = new MethodDescriptor[proto.getMethodCount()];
      for (int i = 0; i < proto.getMethodCount(); i++)
        this.methods[i] = new MethodDescriptor(proto.getMethod(i), file, this, i);
      file.pool.addSymbol(this);
    }
    
    private void crossLink() throws DescriptorValidationException {
      for (MethodDescriptor method : this.methods)
        method.crossLink(); 
    }
    
    private void setProto(DescriptorProtos.ServiceDescriptorProto proto) {
      this.proto = proto;
      for (int i = 0; i < this.methods.length; i++)
        this.methods[i].setProto(proto.getMethod(i)); 
    }
  }
  
  public static final class MethodDescriptor extends GenericDescriptor {
    private final int index;
    
    private DescriptorProtos.MethodDescriptorProto proto;
    
    private final String fullName;
    
    private final FileDescriptor file;
    
    private final ServiceDescriptor service;
    
    private Descriptor inputType;
    
    private Descriptor outputType;
    
    public int getIndex() {
      return this.index;
    }
    
    public DescriptorProtos.MethodDescriptorProto toProto() {
      return this.proto;
    }
    
    public String getName() {
      return this.proto.getName();
    }
    
    public String getFullName() {
      return this.fullName;
    }
    
    public FileDescriptor getFile() {
      return this.file;
    }
    
    public ServiceDescriptor getService() {
      return this.service;
    }
    
    public Descriptor getInputType() {
      return this.inputType;
    }
    
    public Descriptor getOutputType() {
      return this.outputType;
    }
    
    public boolean isClientStreaming() {
      return this.proto.getClientStreaming();
    }
    
    public boolean isServerStreaming() {
      return this.proto.getServerStreaming();
    }
    
    public DescriptorProtos.MethodOptions getOptions() {
      return this.proto.getOptions();
    }
    
    private MethodDescriptor(DescriptorProtos.MethodDescriptorProto proto, FileDescriptor file, ServiceDescriptor parent, int index) throws DescriptorValidationException {
      this.index = index;
      this.proto = proto;
      this.file = file;
      this.service = parent;
      this.fullName = parent.getFullName() + '.' + proto.getName();
      file.pool.addSymbol(this);
    }
    
    private void crossLink() throws DescriptorValidationException {
      GenericDescriptor input = this.file.pool.lookupSymbol(this.proto
          .getInputType(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
      if (!(input instanceof Descriptor))
        throw new DescriptorValidationException(this, '"' + this.proto
            .getInputType() + "\" is not a message type."); 
      this.inputType = (Descriptor)input;
      GenericDescriptor output = this.file.pool.lookupSymbol(this.proto
          .getOutputType(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
      if (!(output instanceof Descriptor))
        throw new DescriptorValidationException(this, '"' + this.proto
            .getOutputType() + "\" is not a message type."); 
      this.outputType = (Descriptor)output;
    }
    
    private void setProto(DescriptorProtos.MethodDescriptorProto proto) {
      this.proto = proto;
    }
  }
  
  private static String computeFullName(FileDescriptor file, Descriptor parent, String name) {
    if (parent != null)
      return parent.getFullName() + '.' + name; 
    String packageName = file.getPackage();
    if (!packageName.isEmpty())
      return packageName + '.' + name; 
    return name;
  }
  
  public static abstract class GenericDescriptor {
    public abstract FileDescriptor getFile();
    
    public abstract String getFullName();
    
    public abstract String getName();
    
    public abstract Message toProto();
    
    private GenericDescriptor() {}
  }
  
  public static class DescriptorValidationException extends Exception {
    private static final long serialVersionUID = 5750205775490483148L;
    
    private final String name;
    
    private final Message proto;
    
    private final String description;
    
    public String getProblemSymbolName() {
      return this.name;
    }
    
    public Message getProblemProto() {
      return this.proto;
    }
    
    public String getDescription() {
      return this.description;
    }
    
    private DescriptorValidationException(GenericDescriptor problemDescriptor, String description) {
      super(problemDescriptor.getFullName() + ": " + description);
      this.name = problemDescriptor.getFullName();
      this.proto = problemDescriptor.toProto();
      this.description = description;
    }
    
    private DescriptorValidationException(GenericDescriptor problemDescriptor, String description, Throwable cause) {
      this(problemDescriptor, description);
      initCause(cause);
    }
    
    private DescriptorValidationException(FileDescriptor problemDescriptor, String description) {
      super(problemDescriptor.getName() + ": " + description);
      this.name = problemDescriptor.getName();
      this.proto = problemDescriptor.toProto();
      this.description = description;
    }
  }
  
  private static final class DescriptorPool {
    private final Set<FileDescriptor> dependencies;
    
    private boolean allowUnknownDependencies;
    
    private final Map<String, GenericDescriptor> descriptorsByName;
    
    private final Map<DescriptorIntPair, FieldDescriptor> fieldsByNumber;
    
    private final Map<DescriptorIntPair, EnumValueDescriptor> enumValuesByNumber;
    
    enum SearchFilter {
      TYPES_ONLY, AGGREGATES_ONLY, ALL_SYMBOLS;
    }
    
    DescriptorPool(FileDescriptor[] dependencies, boolean allowUnknownDependencies) {
      this.descriptorsByName = new HashMap<>();
      this.fieldsByNumber = new HashMap<>();
      this.enumValuesByNumber = new HashMap<>();
      this.dependencies = new HashSet<>();
      this.allowUnknownDependencies = allowUnknownDependencies;
      for (int i = 0; i < dependencies.length; i++) {
        this.dependencies.add(dependencies[i]);
        importPublicDependencies(dependencies[i]);
      } 
      for (FileDescriptor dependency : this.dependencies) {
        try {
          addPackage(dependency.getPackage(), dependency);
        } catch (DescriptorValidationException e) {
          throw new AssertionError(e);
        } 
      } 
    }
    
    private void importPublicDependencies(FileDescriptor file) {
      for (FileDescriptor dependency : file.getPublicDependencies()) {
        if (this.dependencies.add(dependency))
          importPublicDependencies(dependency); 
      } 
    }
    
    GenericDescriptor findSymbol(String fullName) {
      return findSymbol(fullName, SearchFilter.ALL_SYMBOLS);
    }
    
    GenericDescriptor findSymbol(String fullName, SearchFilter filter) {
      GenericDescriptor result = this.descriptorsByName.get(fullName);
      if (result != null && (
        filter == SearchFilter.ALL_SYMBOLS || (filter == SearchFilter.TYPES_ONLY && 
        isType(result)) || (filter == SearchFilter.AGGREGATES_ONLY && 
        isAggregate(result))))
        return result; 
      for (FileDescriptor dependency : this.dependencies) {
        result = dependency.pool.descriptorsByName.get(fullName);
        if (result != null && (
          filter == SearchFilter.ALL_SYMBOLS || (filter == SearchFilter.TYPES_ONLY && 
          isType(result)) || (filter == SearchFilter.AGGREGATES_ONLY && 
          isAggregate(result))))
          return result; 
      } 
      return null;
    }
    
    boolean isType(GenericDescriptor descriptor) {
      return (descriptor instanceof Descriptor || descriptor instanceof EnumDescriptor);
    }
    
    boolean isAggregate(GenericDescriptor descriptor) {
      return (descriptor instanceof Descriptor || descriptor instanceof EnumDescriptor || descriptor instanceof PackageDescriptor || descriptor instanceof ServiceDescriptor);
    }
    
    GenericDescriptor lookupSymbol(String name, GenericDescriptor relativeTo, SearchFilter filter) throws DescriptorValidationException {
      GenericDescriptor result;
      String fullname;
      if (name.startsWith(".")) {
        fullname = name.substring(1);
        result = findSymbol(fullname, filter);
      } else {
        String firstPart;
        int firstPartLength = name.indexOf('.');
        if (firstPartLength == -1) {
          firstPart = name;
        } else {
          firstPart = name.substring(0, firstPartLength);
        } 
        StringBuilder scopeToTry = new StringBuilder(relativeTo.getFullName());
        while (true) {
          int dotpos = scopeToTry.lastIndexOf(".");
          if (dotpos == -1) {
            fullname = name;
            GenericDescriptor genericDescriptor = findSymbol(name, filter);
            break;
          } 
          scopeToTry.setLength(dotpos + 1);
          scopeToTry.append(firstPart);
          result = findSymbol(scopeToTry.toString(), SearchFilter.AGGREGATES_ONLY);
          if (result != null) {
            if (firstPartLength != -1) {
              scopeToTry.setLength(dotpos + 1);
              scopeToTry.append(name);
              result = findSymbol(scopeToTry.toString(), filter);
            } 
            fullname = scopeToTry.toString();
            break;
          } 
          scopeToTry.setLength(dotpos);
        } 
      } 
      if (result == null) {
        if (this.allowUnknownDependencies && filter == SearchFilter.TYPES_ONLY) {
          Descriptors.logger.warning("The descriptor for message type \"" + name + "\" can not be found and a placeholder is created for it");
          result = new Descriptor(fullname);
          this.dependencies.add(result.getFile());
          return result;
        } 
        throw new DescriptorValidationException(relativeTo, '"' + name + "\" is not defined.");
      } 
      return result;
    }
    
    void addSymbol(GenericDescriptor descriptor) throws DescriptorValidationException {
      validateSymbolName(descriptor);
      String fullName = descriptor.getFullName();
      GenericDescriptor old = this.descriptorsByName.put(fullName, descriptor);
      if (old != null) {
        this.descriptorsByName.put(fullName, old);
        if (descriptor.getFile() == old.getFile()) {
          int dotpos = fullName.lastIndexOf('.');
          if (dotpos == -1)
            throw new DescriptorValidationException(descriptor, '"' + fullName + "\" is already defined.");
          throw new DescriptorValidationException(descriptor, '"' + fullName
              
              .substring(dotpos + 1) + "\" is already defined in \"" + fullName
              
              .substring(0, dotpos) + "\".");
        } 
        throw new DescriptorValidationException(descriptor, '"' + fullName + "\" is already defined in file \"" + old
            
            .getFile().getName() + "\".");
      } 
    }
    
    private static final class PackageDescriptor extends GenericDescriptor {
      private final String name;
      
      private final String fullName;
      
      private final FileDescriptor file;
      
      public Message toProto() {
        return this.file.toProto();
      }
      
      public String getName() {
        return this.name;
      }
      
      public String getFullName() {
        return this.fullName;
      }
      
      public FileDescriptor getFile() {
        return this.file;
      }
      
      PackageDescriptor(String name, String fullName, FileDescriptor file) {
        this.file = file;
        this.fullName = fullName;
        this.name = name;
      }
    }
    
    void addPackage(String fullName, FileDescriptor file) throws DescriptorValidationException {
      String name;
      int dotpos = fullName.lastIndexOf('.');
      if (dotpos == -1) {
        name = fullName;
      } else {
        addPackage(fullName.substring(0, dotpos), file);
        name = fullName.substring(dotpos + 1);
      } 
      GenericDescriptor old = this.descriptorsByName.put(fullName, new PackageDescriptor(name, fullName, file));
      if (old != null) {
        this.descriptorsByName.put(fullName, old);
        if (!(old instanceof PackageDescriptor))
          throw new DescriptorValidationException(file, '"' + name + "\" is already defined (as something other than a package) in file \"" + old
              
              .getFile().getName() + "\"."); 
      } 
    }
    
    private static final class DescriptorIntPair {
      private final GenericDescriptor descriptor;
      
      private final int number;
      
      DescriptorIntPair(GenericDescriptor descriptor, int number) {
        this.descriptor = descriptor;
        this.number = number;
      }
      
      public int hashCode() {
        return this.descriptor.hashCode() * 65535 + this.number;
      }
      
      public boolean equals(Object obj) {
        if (!(obj instanceof DescriptorIntPair))
          return false; 
        DescriptorIntPair other = (DescriptorIntPair)obj;
        return (this.descriptor == other.descriptor && this.number == other.number);
      }
    }
    
    void addFieldByNumber(FieldDescriptor field) throws DescriptorValidationException {
      DescriptorIntPair key = new DescriptorIntPair(field.getContainingType(), field.getNumber());
      FieldDescriptor old = this.fieldsByNumber.put(key, field);
      if (old != null) {
        this.fieldsByNumber.put(key, old);
        throw new DescriptorValidationException(field, "Field number " + field
            
            .getNumber() + " has already been used in \"" + field
            
            .getContainingType().getFullName() + "\" by field \"" + old
            
            .getName() + "\".");
      } 
    }
    
    void addEnumValueByNumber(EnumValueDescriptor value) {
      DescriptorIntPair key = new DescriptorIntPair(value.getType(), value.getNumber());
      EnumValueDescriptor old = this.enumValuesByNumber.put(key, value);
      if (old != null)
        this.enumValuesByNumber.put(key, old); 
    }
    
    static void validateSymbolName(GenericDescriptor descriptor) throws DescriptorValidationException {
      String name = descriptor.getName();
      if (name.length() == 0)
        throw new DescriptorValidationException(descriptor, "Missing name.");
      for (int i = 0; i < name.length(); ) {
        char c = name.charAt(i);
        if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || c == '_' || ('0' <= c && c <= '9' && i > 0)) {
          i++;
          continue;
        } 
        throw new DescriptorValidationException(descriptor, '"' + name + "\" is not a valid identifier.");
      } 
    }
  }
  
  public static final class OneofDescriptor extends GenericDescriptor {
    private final int index;
    
    private DescriptorProtos.OneofDescriptorProto proto;
    
    private final String fullName;
    
    private final FileDescriptor file;
    
    private Descriptor containingType;
    
    private int fieldCount;
    
    private FieldDescriptor[] fields;
    
    public int getIndex() {
      return this.index;
    }
    
    public String getName() {
      return this.proto.getName();
    }
    
    public FileDescriptor getFile() {
      return this.file;
    }
    
    public String getFullName() {
      return this.fullName;
    }
    
    public Descriptor getContainingType() {
      return this.containingType;
    }
    
    public int getFieldCount() {
      return this.fieldCount;
    }
    
    public DescriptorProtos.OneofOptions getOptions() {
      return this.proto.getOptions();
    }
    
    public List<FieldDescriptor> getFields() {
      return Collections.unmodifiableList(Arrays.asList(this.fields));
    }
    
    public FieldDescriptor getField(int index) {
      return this.fields[index];
    }
    
    public DescriptorProtos.OneofDescriptorProto toProto() {
      return this.proto;
    }
    
    private void setProto(DescriptorProtos.OneofDescriptorProto proto) {
      this.proto = proto;
    }
    
    private OneofDescriptor(DescriptorProtos.OneofDescriptorProto proto, FileDescriptor file, Descriptor parent, int index) throws DescriptorValidationException {
      this.proto = proto;
      this.fullName = Descriptors.computeFullName(file, parent, proto.getName());
      this.file = file;
      this.index = index;
      this.containingType = parent;
      this.fieldCount = 0;
    }
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\google\protobuf\Descriptors.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */