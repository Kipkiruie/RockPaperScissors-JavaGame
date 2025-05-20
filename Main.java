import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents the Rock-Paper-Scissors game.
 * This enhanced version includes score tracking, improved input validation,
 * and a more modular structure.
 */
public class EnhancedRockPaperScissors {

    /**
     * Enum representing the possible moves in Rock-Paper-Scissors.
     */
    private enum Move {
        ROCK, PAPER, SCISSORS;

        /**
         * Determines if this move beats the other move.
         * @param other The opponent's move.
         * @return true if this move beats the other, false otherwise.
         */
        public boolean beats(Move other) {
            switch (this) {
                case ROCK:
                    return other == SCISSORS;
                case PAPER:
                    return other == ROCK;
                case SCISSORS:
                    return other == PAPER;
                default:
                    return false; // Should not happen
            }
        }

        /**
         * Gets a move based on an integer input (1-indexed).
         * @param choice The integer choice (1 for ROCK, 2 for PAPER, 3 for SCISSORS).
         * @return The corresponding Move, or null if invalid.
         */
        public static Move fromInt(int choice) {
            switch (choice) {
                case 1: return ROCK;
                case 2: return PAPER;
                case 3: return SCISSORS;
                default: return null;
            }
        }
    }

    /**
     * Enum representing the result of a single round.
     */
    private enum RoundResult {
        PLAYER_WINS, COMPUTER_WINS, TIE
    }

    private static final Random random = new Random();
    private static int playerWins = 0;
    private static int computerWins = 0;
    private static int ties = 0;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            displayWelcomeMessage();

            boolean playAgain = true;
            while (playAgain) {
                Move playerMove = getUserMove(scanner);
                Move computerMove = getComputerMove();

                System.out.println("You chose: " + playerMove);
                System.out.println("Computer chose: " + computerMove);

                RoundResult result = determineWinner(playerMove, computerMove);
                displayRoundResult(result);
                updateScores(result);

                playAgain = askToPlayAgain(scanner);
                System.out.println(); // Add a blank line for better separation between rounds
            }

            displayGameSummary();
            System.out.println("Thanks for playing Rock-Paper-Scissors! Goodbye!");
        }
    }

    /**
     * Displays the welcome message to the player.
     */
    private static void displayWelcomeMessage() {
        System.out.println("***********************************************");
        System.out.println(" Welcome to the Enhanced Rock-Paper-Scissors Game!");
        System.out.println("***********************************************");
        System.out.println("You'll be playing against the computer.");
        System.out.println("Good luck!\n");
    }

    /**
     * Prompts the user for their move and validates the input.
     * @param scanner The Scanner object for reading user input.
     * @return The Move chosen by the player.
     */
    private static Move getUserMove(Scanner scanner) {
        Move playerMove = null;
        while (playerMove == null) {
            System.out.print("Enter your choice (1: Rock, 2: Paper, 3: Scissors): ");
            try {
                int userChoiceInt = scanner.nextInt();
                playerMove = Move.fromInt(userChoiceInt);
                if (playerMove == null) {
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (1, 2, or 3).");
                scanner.next(); // Consume the invalid token
            }
        }
        return playerMove;
    }

    /**
     * Generates a random move for the computer.
     * @return The Move chosen by the computer.
     */
    private static Move getComputerMove() {
        return Move.values()[random.nextInt(Move.values().length)];
    }

    /**
     * Determines the winner of the round based on player and computer moves.
     * @param playerMove The player's move.
     * @param computerMove The computer's move.
     * @return The RoundResult indicating the outcome.
     */
    private static RoundResult determineWinner(Move playerMove, Move computerMove) {
        if (playerMove == computerMove) {
            return RoundResult.TIE;
        } else if (playerMove.beats(computerMove)) {
            return RoundResult.PLAYER_WINS;
        } else {
            return RoundResult.COMPUTER_WINS;
        }
    }

    /**
     * Displays the result of the current round.
     * @param result The RoundResult of the current round.
     */
    private static void displayRoundResult(RoundResult result) {
        switch (result) {
            case PLAYER_WINS:
                System.out.println(">>> You win this round! <<<");
                break;
            case COMPUTER_WINS:
                System.out.println(">>> Computer wins this round! <<<");
                break;
            case TIE:
                System.out.println(">>> It's a tie this round! <<<");
                break;
        }
    }

    /**
     * Updates the game scores based on the round result.
     * @param result The RoundResult of the current round.
     */
    private static void updateScores(RoundResult result) {
        switch (result) {
            case PLAYER_WINS:
                playerWins++;
                break;
            case COMPUTER_WINS:
                computerWins++;
                break;
            case TIE:
                ties++;
                break;
        }
    }

    /**
     * Asks the player if they want to play another round.
     * Handles various forms of "yes" and "no" inputs.
     * @param scanner The Scanner object for reading user input.
     * @return true if the player wants to play again, false otherwise.
     */
    private static boolean askToPlayAgain(Scanner scanner) {
        while (true) {
            System.out.print("Do you want to play again? (yes/no or y/n): ");
            String response = scanner.next().toLowerCase();
            if (response.equals("yes") || response.equals("y")) {
                return true;
            } else if (response.equals("no") || response.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid response. Please type 'yes', 'no', 'y', or 'n'.");
            }
        }
    }

    /**
     * Displays the final game statistics.
     */
    private static void displayGameSummary() {
        System.out.println("\n***********************************************");
        System.out.println("                 Game Summary");
        System.out.println("***********************************************");
        System.out.println("Player Wins: " + playerWins);
        System.out.println("Computer Wins: " + computerWins);
        System.out.println("Ties: " + ties);
        System.out.println("***********************************************");
    }
}
