package java_rmi_server;

import java.util.HashMap;
import java.util.Map;

class Lock {
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	int clientId;
	boolean locked;
}

public class StringObj {
	private static final String EMPTY_STR = "";
	
	private String stringVal;
	
	private Map<Integer, Lock> clientReadLocks; 
	private Lock writeLock; 

	public StringObj(String stringVal) {
		this.stringVal = stringVal;
		clientReadLocks = new HashMap<>();
		writeLock = null;
	}

	public StringObj() {
		clientReadLocks = new HashMap<>();
		writeLock = null;
		this.stringVal = EMPTY_STR;
	}

	public String getStringVal() {
		return stringVal;
	}

	public void setStringVal(String stringVal) {
		this.stringVal = stringVal;
	}

	public synchronized Lock getReadLock(int clientId) {
		
		/* Client alrady holds readLock on this element */
		if(clientReadLocks.containsKey(clientId)) {
			return clientReadLocks.get(clientId);
		}
		
		if (writeLock == null || !writeLock.isLocked()) {
			
			Lock newReadLock = new Lock();
			newReadLock.clientId = clientId;
			clientReadLocks.put(clientId, newReadLock);
			return newReadLock;
		}
		return null;
	}

	public synchronized Lock getWriteLock(int clientId) {
		if (clientReadLocks.size() == 0) {
			
			if (writeLock == null) {
				setWriteLockForClient(clientId);
				return this.writeLock;
			}
			
			if (writeLock.isLocked() && writeLock.getClientId() == clientId) {
				writeLock.clientId = clientId;
				return this.writeLock;
			} else if (writeLock.isLocked()) {
				return null;
			} 

		}
		return null;
	}
	
	private void setWriteLockForClient(int clientId) {
		writeLock = new Lock();
		writeLock.setLocked(true);
		writeLock.setClientId(clientId);
	}
	
	public void releaseLocks(int clientId) {
		releaseRead(clientId);
		releaseWrite(clientId);
	}
	
	public void releaseRead(int clientId) {
		if(clientReadLocks.containsKey(clientId)) {
			clientReadLocks.remove(clientId);
		}
	}
	
	public void releaseWrite(int clientId) {
		if(writeLock != null && writeLock.getClientId() == clientId) {
			writeLock = null;
		}
	}
}
