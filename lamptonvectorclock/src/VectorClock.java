import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class VectorClock extends UnicastRemoteObject  implements VectorClockInterface {
    private int[] localClock;

    public VectorClock(int n) throws RemoteException{
        super();
        localClock = new int[n];
    }

    public int[] getClock() throws RemoteException{
        return localClock;
    }
    public void increment(int processId) throws RemoteException{
        System.out.println("Incrementing process " + processId);
        localClock[processId]++;
    }

    public void update(int[] remoteClock, int processID) throws RemoteException{
        for(int i = 0; i<localClock.length; i++){
            // if(i == 0){
            //     System.out.println("V1Process 1 should update index 0 here "+localClock[i]);
            // }
        

            localClock[i] = Math.max(localClock[i], remoteClock[i]);
            // if(i == 0){
            //     System.out.println("V2Process 1 should update index 0 here "+localClock[i]);
            // }
        }
        this.increment(processID);
        // System.out.println();
        // System.out.println();
        // System.out.println();
        // System.out.println();
    }  
}   
