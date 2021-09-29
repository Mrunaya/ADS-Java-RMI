package Server;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RMIInterface extends Remote {
	
	public String getArrayElement(int i) throws RemoteException; 

}