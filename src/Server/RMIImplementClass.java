package Server;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIImplementClass implements RMIInterface {

	protected RMIImplementClass() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getArrayElement(int i) throws RemoteException {
		// TODO Auto-generated method stub
		 String[] characters = {"a", "b", "c", "d"};
		System.out.println();
			return characters[i];
		
		
	}

}