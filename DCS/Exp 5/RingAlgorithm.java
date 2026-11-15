import java.util.Scanner;

public class RingAlgorithm {

    static int n;
    static boolean[] active;

    static void election(int initiator) {

        System.out.println("\nProcess P" + initiator
                + " starts an election.");

        int[] electionList = new int[n];
        int count = 0;

        int current = initiator;

        System.out.println("\nElection message travels around the ring:");

        do {

            if (active[current]) {

                electionList[count] = current;
                count++;

                System.out.println("Process P" + current
                        + " participates in election.");
            } else {

                System.out.println("Process P" + current
                        + " is failed. Skipping...");
            }

            current = (current % n) + 1;

        } while (current != initiator);

        // Find process with highest ID
        int coordinator = electionList[0];

        for (int i = 1; i < count; i++) {

            if (electionList[i] > coordinator) {
                coordinator = electionList[i];
            }
        }

        System.out.println("\nElection message completed.");

        System.out.println("Participating processes:");

        for (int i = 0; i < count; i++) {
            System.out.print("P" + electionList[i] + " ");
        }

        System.out.println("\n\nP" + coordinator
                + " is the new Coordinator.");

        System.out.println("\nCoordinator message circulated to all active processes.");
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