import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteProcessInterface extends Remote {

    int[] getVectorClock() throws RemoteException;
    void sendEvent(int targetID, String data) throws RemoteException;
    void receiveEvent(int[] vectorClock, String data) throws RemoteException;
    void internalEvent() throws RemoteException;

}