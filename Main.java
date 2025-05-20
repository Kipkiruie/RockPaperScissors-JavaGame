import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Display a welcome message
        System.out.println("***********************************************");
        System.out.println("Welcome to the Rock-Paper-Scissors Game!");
        System.out.println("***********************************************");

        // Declare variables
        String[] choices = {"Rock", "Paper", "Scissors"};
        boolean playAgain = true;

        // Game loop
        while (playAgain) {
            // Get user's choice
            System.out.print("Enter your choice (1: Rock, 2: Paper, 3: Scissors): ");
            int userChoice = scanner.nextInt();

            // Validate user's choice
            if (userChoice < 1 || userChoice > 3) {
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                continue;
            }

            // Get random choice for the computer
            int computerChoice = random.nextInt(3) + 1;

            // Display choices
            System.out.println("You chose: " + choices[userChoice - 1]);
            System.out.println("Computer chose: " + choices[computerChoice - 1]);

            // Check win conditions
            if (userChoice == computerChoice) {
                System.out.println("It's a tie!");
            } else if ((userChoice == 1 && computerChoice == 3) ||
                    (userChoice == 2 && computerChoice == 1) ||
                    (userChoice == 3 && computerChoice == 2)) {
                System.out.println("You win!");
            } else {
                System.out.println("You lose!");
            }

            // Ask if the player wants to play again
            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");
        }

        // Goodbye message
        System.out.println("Thanks for playing Rock-Paper-Scissors! Goodbye!");

        scanner.close();
    }
}

