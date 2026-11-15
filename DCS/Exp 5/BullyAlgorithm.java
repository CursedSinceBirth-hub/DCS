import java.util.Scanner;

public class BullyAlgorithm {

    static int n;
    static boolean[] active;

    static void election(int initiator) {

        System.out.println("\nProcess P" + initiator
                + " starts an election.");

        boolean higherProcessResponded = false;

        // Send election messages to higher processes
        for (int i = initiator + 1; i <= n; i++) {

            if (active[i]) {

                System.out.println("Election message sent to P" + i);

                System.out.println("P" + i
                        + " responds to P" + initiator);

                higherProcessResponded = true;
            }
        }

        if (!higherProcessResponded) {

            System.out.println("\nP" + initiator
                    + " becomes the new Coordinator.");

            return;
        }

        // Higher process starts its own election
        for (int i = initiator + 1; i <= n; i++) {

            if (active[i]) {
                election(i);
                return;
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        n = sc.nextInt();

        active = new boolean[n + 1];

        // Initially all processes are active
        for (int i = 1; i <= n; i++) {
            active[i] = true;
        }

        System.out.println("Enter failed process ID (0 for none): ");
        int failed = sc.nextInt();

        if (failed >= 1 && failed <= n) {
            active[failed] = false;
            System.out.println("Process P" + failed + " has failed.");
        }

        System.out.print("Enter process ID starting election: ");
        int initiator = sc.nextInt();

        if (initiator < 1 || initiator > n || !active[initiator]) {
            System.out.println("Invalid or inactive initiator.");
        } else {
            election(initiator);
        }

        sc.close();
    }
}