package com.mysql.cj;

public class ClientPreparedQuery extends AbstractPreparedQuery<ClientPreparedQueryBindings> {
  public ClientPreparedQuery(NativeSession sess) {
    super(sess);
  }
  
  protected long[] computeMaxParameterSetSizeAndBatchSize(int numBatchedArgs) {
    long sizeOfEntireBatch = 1L;
    long maxSizeOfParameterSet = 0L;
    if (this.session.getServerSession().supportsQueryAttributes()) {
      sizeOfEntireBatch += 10L;
      sizeOfEntireBatch += ((this.queryAttributesBindings.getCount() + 7) / 8 + 1);
      for (int j = 0; j < this.queryAttributesBindings.getCount(); j++) {
        QueryAttributesBindValue queryAttribute = this.queryAttributesBindings.getAttributeValue(j);
        sizeOfEntireBatch += (2 + queryAttribute.getName().length()) + queryAttribute.getBoundLength();
      } 
    } 
    for (int i = 0; i < numBatchedArgs; i++) {
      ClientPreparedQueryBindings qBindings = this.batchedArgs.get(i);
      ClientPreparedQueryBindValue[] arrayOfClientPreparedQueryBindValue = qBindings.getBindValues();
      long sizeOfParameterSet = 0L;
      for (int j = 0; j < arrayOfClientPreparedQueryBindValue.length; j++) {
        if (!arrayOfClientPreparedQueryBindValue[j].isNull()) {
          if (arrayOfClientPreparedQueryBindValue[j].isStream()) {
            long streamLength = arrayOfClientPreparedQueryBindValue[j].getStreamLength();
            if (streamLength != -1L) {
              sizeOfParameterSet += streamLength * 2L;
            } else {
              int paramLength = (qBindings.getBindValues()[j].getByteValue()).length;
              sizeOfParameterSet += paramLength;
            } 
          } else {
            sizeOfParameterSet += (qBindings.getBindValues()[j].getByteValue()).length;
          } 
        } else {
          sizeOfParameterSet += 4L;
        } 
      } 
      if (this.parseInfo.getValuesClause() != null) {
        sizeOfParameterSet += (this.parseInfo.getValuesClause().length() + 1);
      } else {
        sizeOfParameterSet += (this.originalSql.length() + 1);
      } 
      sizeOfEntireBatch += sizeOfParameterSet;
      if (sizeOfParameterSet > maxSizeOfParameterSet)
        maxSizeOfParameterSet = sizeOfParameterSet; 
    } 
    return new long[] { maxSizeOfParameterSet, sizeOfEntireBatch };
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\ClientPreparedQuery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */