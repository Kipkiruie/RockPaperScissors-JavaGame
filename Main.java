import java.util.*;

/**
 * A Rock-Paper-Scissors game with an object-oriented structure and enhanced features.
 */
public class Main {

    // Enum representing possible moves
    enum Move {
        ROCK, PAPER, SCISSORS;

        /**
         * Determines if this move beats another move.
         *
         * @param other the move to compare against
         * @return true if this move beats the other move, false otherwise
         */
        public boolean beats(Move other) {
            return (this == ROCK && other == SCISSORS) ||
                   (this == PAPER && other == ROCK) ||
                   (this == SCISSORS && other == PAPER);
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            displayWelcomeMessage();

            // Track scores
            int playerWins = 0;
            int computerWins = 0;
            int ties = 0;

            boolean playAgain = true;

            // Main game loop
            while (playAgain) {
                Move playerMove = getUserMove(scanner);
                Move computerMove = getComputerMove();

                System.out.println("You chose: " + playerMove);
                System.out.println("Computer chose: " + computerMove);

                // Determine the outcome of the round
                String result = determineWinner(playerMove, computerMove);
                displayRoundResult(result);

                // Update scores
                switch (result) {
                    case "win" -> playerWins++;
                    case "lose" -> computerWins++;
                    case "tie" -> ties++;
                }

                // Ask if the player wants to play again
                playAgain = askToPlayAgain(scanner);
            }

            // Display game summary
            displayGameSummary(playerWins, computerWins, ties);
        }
    }

    /**
     * Displays a welcome message.
     */
    private static void displayWelcomeMessage() {
        System.out.println("***********************************************");
        System.out.println("Welcome to the Enhanced Rock-Paper-Scissors Game!");
        System.out.println("***********************************************");
    }

    /**
     * Gets the user's move.
     *
     * @param scanner the Scanner object for input
     * @return the user's move as a Move enum
     */
    private static Move getUserMove(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Enter your choice (1: Rock, 2: Paper, 3: Scissors): ");
                int choice = scanner.nextInt();
                return switch (choice) {
                    case 1 -> Move.ROCK;
                    case 2 -> Move.PAPER;
                    case 3 -> Move.SCISSORS;
                    default -> throw new IllegalArgumentException("Invalid input. Please enter 1, 2, or 3.");
                };
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (1, 2, or 3).");
                scanner.next(); // Clear the invalid input
            }
        }
    }

    /**
     * Gets the computer's random move.
     *
     * @return the computer's move as a Move enum
     */
    private static Move getComputerMove() {
        Move[] moves = Move.values();
        return moves[new Random().nextInt(moves.length)];
    }

    /**
     * Determines the winner of the round.
     *
     * @param playerMove   the player's move
     * @param computerMove the computer's move
     * @return "win", "lose", or "tie" based on the outcome
     */
    private static String determineWinner(Move playerMove, Move computerMove) {
        if (playerMove == computerMove) {
            return "tie";
        } else if (playerMove.beats(computerMove)) {
            return "win";
        } else {
            return "lose";
        }
    }

    /**
     * Displays the result of a round.
     *
     * @param result the result of the round ("win", "lose", or "tie")
     */
    private static void displayRoundResult(String result) {
        switch (result) {
            case "win" -> System.out.println("You win this round!");
            case "lose" -> System.out.println("You lose this round!");
            case "tie" -> System.out.println("This round is a tie!");
        }
    }

    /**
     * Asks the player if they want to play again.
     *
     * @param scanner the Scanner object for input
     * @return true if the player wants to play again, false otherwise
     */
    private static boolean askToPlayAgain(Scanner scanner) {
        while (true) {
            System.out.println("Do you want to play again? (y/n): ");
            String response = scanner.next().trim().toLowerCase();
            if (response.equals("y") || response.equals("yes")) {
                return true;
            } else if (response.equals("n") || response.equals("no")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        }
    }

    /**
     * Displays a summary of the game results.
     *
     * @param playerWins   the number of rounds the player won
     * @param computerWins the number of rounds the computer won
     * @param ties         the number of rounds that were ties
     */
    private static void displayGameSummary(int playerWins, int computerWins, int ties) {
        System.out.println("\nGame Summary:");
        System.out.println("Player Wins: " + playerWins);
        System.out.println("Computer Wins: " + computerWins);
        System.out.println("Ties: " + ties);
        System.out.println("Thanks for playing Rock-Paper-Scissors! Goodbye!");
    }
}
