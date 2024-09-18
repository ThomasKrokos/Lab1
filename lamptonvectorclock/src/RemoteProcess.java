import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;


public class RemoteProcess extends UnicastRemoteObject implements RemoteProcessInterface {
    private VectorClock localClock;
    private int localID;
    
    public RemoteProcess(int n, int processID) throws RemoteException{
        super();
        localClock = new VectorClock(n);
        localID = processID;
    }

    
    public int[] getVectorClock() throws RemoteException{
        return localClock.getClock();
    }
    public void sendEvent(int targetID, String data) throws RemoteException{
        try {
            System.out.println("Process "+localID+" sending message to Process "+targetID);
            localClock.increment(localID);
            int[] currentClock = localClock.getClock();
            RemoteProcessInterface targetRemoteProcess = (RemoteProcessInterface) Naming.lookup("rmi://localhost/RemoteProcessService" + targetID);
            System.out.println("Process "+localID+" is sending Message: \'" + data + "\'");
            targetRemoteProcess.receiveEvent(currentClock, data);
        } catch (Exception e) {
            System.err.println("Exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void receiveEvent(int[] vectorClock, String data) throws RemoteException{
        localClock.update(vectorClock, localID);
        System.out.println("Message: \'"+ data + "\' received by Process "+localID);
    }

    public void internalEvent() throws RemoteException{ // simulate local event
        System.out.println("Local event occured in Process "+localID);
        localClock.increment(localID);
    }
    
}
