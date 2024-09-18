import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;


public class Server {
    
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("RMI registry started.");

            int n = 3;
            VectorClockInterface clientClock = new VectorClock(n);
            Naming.rebind("VectorClockService", clientClock);
            
            RemoteProcessInterface[] remoteProcesses = new RemoteProcess[n];
            for(int i = 0; i < n; i++){
                remoteProcesses[i] = new RemoteProcess(n,i);
                Naming.rebind("RemoteProcessService"+i, remoteProcesses[i]);
                System.out.println("Registered RemoteProcessService" + i);

            }

            System.out.println("Remote objects registered in the RMI registry.");

        } catch (Exception e) {
            System.err.println("Exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
