import java.rmi.Naming;
import java.util.Arrays;
import java.rmi.RemoteException;



public class Client {        
    public static void main(String[] args) {
        try {
            System.out.println("Looking up remote objects as a client...");


            int n = 3;

            RemoteProcessInterface remoteProcessesClient[] = new RemoteProcessInterface[n];

            for(int i = 0; i < n; i++){             
                remoteProcessesClient[i] = (RemoteProcessInterface) Naming.lookup("rmi://localhost/RemoteProcessService" + i);
                System.out.println("Looked up RemoteProcessService" + i);
        }

            VectorClockInterface vectorClockClient = (VectorClockInterface) Naming.lookup("rmi://localhost/VectorClockService");
            System.out.println("Looked up vectorClockClient");
            System.out.println();

            simulate(remoteProcessesClient);
        } catch (Exception e) {
            System.err.println("Exception: " + e.toString());
            e.printStackTrace();
        }
    }


    public static void simulate(RemoteProcessInterface[] remoteProcessesClient) throws RemoteException{
     
        

        System.out.println("Running Lamport Vector Clock simulation ...");


        remoteProcessesClient[0].internalEvent();
        System.out.println();
        System.out.println("Process 0 clock is now "+ Arrays.toString(remoteProcessesClient[0].getVectorClock()));
        System.out.println("Process 1 clock is now "+ Arrays.toString(remoteProcessesClient[1].getVectorClock()));
        System.out.println("Process 2 clock is now "+ Arrays.toString(remoteProcessesClient[2].getVectorClock()));

        remoteProcessesClient[0].internalEvent();
        System.out.println();
        System.out.println("Process 0 clock is now "+ Arrays.toString(remoteProcessesClient[0].getVectorClock()));
        System.out.println("Process 1 clock is now "+ Arrays.toString(remoteProcessesClient[1].getVectorClock()));
        System.out.println("Process 2 clock is now "+ Arrays.toString(remoteProcessesClient[2].getVectorClock()));

        remoteProcessesClient[1].internalEvent();
        System.out.println();

        System.out.println("Process 0 clock is now "+ Arrays.toString(remoteProcessesClient[0].getVectorClock()));
        System.out.println("Process 1 clock is now "+ Arrays.toString(remoteProcessesClient[1].getVectorClock()));
        System.out.println("Process 2 clock is now "+ Arrays.toString(remoteProcessesClient[2].getVectorClock()));


        remoteProcessesClient[1].sendEvent(0, "this is a test message");
        System.out.println();
        System.out.println("Process 0 clock is now "+ Arrays.toString(remoteProcessesClient[0].getVectorClock()));
        System.out.println("Process 1 clock is now "+ Arrays.toString(remoteProcessesClient[1].getVectorClock()));
        System.out.println("Process 2 clock is now "+ Arrays.toString(remoteProcessesClient[2].getVectorClock()));


        remoteProcessesClient[0].sendEvent(2, "Hi wassup");
        System.out.println();
        System.out.println("Process 0 clock is now "+ Arrays.toString(remoteProcessesClient[0].getVectorClock()));
        System.out.println("Process 1 clock is now "+ Arrays.toString(remoteProcessesClient[1].getVectorClock()));
        System.out.println("Process 2 clock is now "+ Arrays.toString(remoteProcessesClient[2].getVectorClock()));

        
        remoteProcessesClient[2].sendEvent(1, "testing... 1 2 3...");
        System.out.println();
        System.out.println("Process 0 clock is now "+ Arrays.toString(remoteProcessesClient[0].getVectorClock()));
        System.out.println("Process 1 clock is now "+ Arrays.toString(remoteProcessesClient[1].getVectorClock()));
        System.out.println("Process 2 clock is now "+ Arrays.toString(remoteProcessesClient[2].getVectorClock()));

        System.out.println("Ending Lamport Vector Clock Simulation ...");
    }
}


