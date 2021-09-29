package Client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Server.RMIInterface;

public class ClientRequest {

    
    public static void main(String[] args) {

        try {
            Registry registry = LocateRegistry.getRegistry(8080);
            RMIInterface stub = (RMIInterface) registry.lookup("rmi");
            String charcater = stub.getArrayElement(2);
            System.out.println("element at 2nd ocation is: " + charcater);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}