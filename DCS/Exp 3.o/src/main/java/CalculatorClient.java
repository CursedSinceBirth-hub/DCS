import org.omg.CORBA.ORB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class CalculatorClient {

    public static void main(String[] args) {

        try {
            // Create ORB
            ORB orb = ORB.init(args, null);

            // Read server object reference from file
            BufferedReader reader =
                    new BufferedReader(new FileReader("calculator.ior"));

            String ior = reader.readLine();
            reader.close();

            // Convert IOR string into CORBA object
            org.omg.CORBA.Object ref = orb.string_to_object(ior);

            // Get Calculator reference
            Calculator calculator = CalculatorHelper.narrow(ref);

            // Take input from user
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter first number: ");
            int a = sc.nextInt();

            System.out.print("Enter second number: ");
            int b = sc.nextInt();

            // Call remote method
            int result = calculator.add(a, b);

            System.out.println("Addition Result: " + result);

            sc.close();

        } catch (Exception e) {
            System.out.println("Client Error: " + e);
            e.printStackTrace();
        }
    }
}