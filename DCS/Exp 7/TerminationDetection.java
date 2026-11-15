import java.util.Scanner;

public class TerminationDetection {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Accept number of processes
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Invalid number of processes.");
            sc.close();
            return;
        }

        // Array to store process status
        boolean[] active = new boolean[n];

        int activeCount = 0;
        int passiveCount = 0;

        // Accept process status
        System.out.println("\nEnter process status:");
        System.out.println("1 = Active, 0 = Passive");

        for (int i = 0; i < n; i++) {

            System.out.print("Process P" + (i + 1) + ": ");
            int status = sc.nextInt();

            if (status != 0 && status != 1) {
                System.out.println("Invalid status. Enter 0 or 1.");
                sc.close();
                return;
            }

            if (status == 1) {
                active[i] = true;
                activeCount++;
            } else {
                active[i] = false;
                passiveCount++;
            }
        }

        // Accept messages in transit
        System.out.print("\nEnter number of messages in transit: ");
        int messages = sc.nextInt();

        if (messages < 0) {
            System.out.println("Invalid number of messages.");
            sc.close();
            return;
        }

        // Display process information
        System.out.println("\nProcess Status:");

        for (int i = 0; i < n; i++) {

            if (active[i]) {
                System.out.println("P" + (i + 1) + " is Active");
            } else {
                System.out.println("P" + (i + 1) + " is Passive");
            }
        }

        // Display counts
        System.out.println("\nActive Processes: " + activeCount);
        System.out.println("Passive Processes: " + passiveCount);
        System.out.println("Messages in Transit: " + messages);

        // Check termination conditions
        boolean allPassive = (activeCount == 0);
        boolean noMessages = (messages == 0);

        System.out.println("\nTermination Detection:");

        if (allPassive && noMessages) {

            System.out.println("All processes are passive.");
            System.out.println("No messages are in transit.");

            System.out.println(
                "\nDistributed System has TERMINATED."
            );

        } else {

            System.out.println("Termination conditions not satisfied.");

            if (!allPassive) {
                System.out.println("Some processes are still active.");
            }

            if (!noMessages) {
                System.out.println("Messages are still in transit.");
            }

            System.out.println(
                "\nDistributed System has NOT TERMINATED."
            );
        }

        sc.close();
    }
}