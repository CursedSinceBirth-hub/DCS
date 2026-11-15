import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.io.FileWriter;
import java.io.PrintWriter;

class CalculatorImpl extends CalculatorPOA {

    public int add(int a, int b) {
        System.out.println("Addition request received: " + a + " + " + b);
        return a + b;
    }
}

public class CalculatorServer {

    public static void main(String[] args) {

        try {
            // Create ORB
            ORB orb = ORB.init(args, null);

            // Get Root POA
            POA rootPOA = POAHelper.narrow(
                    orb.resolve_initial_references("RootPOA")
            );

            // Create servant object
            CalculatorImpl calculator = new CalculatorImpl();

            // Activate servant
            rootPOA.activate_object(calculator);

            // Convert servant reference into CORBA object reference
            org.omg.CORBA.Object ref =
                    rootPOA.servant_to_reference(calculator);

            Calculator href = CalculatorHelper.narrow(ref);

            // Convert object reference into IOR string
            String ior = orb.object_to_string(href);

            // Save IOR in a file for the client
            PrintWriter writer =
                    new PrintWriter(new FileWriter("calculator.ior"));

            writer.println(ior);
            writer.close();

            // Activate POA manager
            rootPOA.the_POAManager().activate();

            System.out.println("CORBA Server is ready...");
            System.out.println("Waiting for client requests...");

            // Keep server running
            orb.run();

        } catch (Exception e) {
            System.out.println("Server Error: " + e);
            e.printStackTrace();
        }
    }
}