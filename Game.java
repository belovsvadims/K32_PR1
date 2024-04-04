// 1.uzd
import java.util.Random;
import java.util.Scanner;

public class Game {
        static int playerScore;
        static int computerScore;
        static int bank;
        static int chosenNumber;
        static int algorithm; // Which algorithm to use. 1 - Minmax, other - AlphaBeta

    public static void main(String[] args) {
        startGame();
    }

    public static void startGame() {
        Scanner sc = new Scanner(System.in);
        playerScore = 0;
        computerScore = 0;
        bank = 0;
        int[] randomNumbers = new int[5];

        for (int i = 0; i < 5; i++) {
            randomNumbers[i] = generateRandomNumber();
        }

        for (int i = 0; i < 5; i++) {
            System.out.print((i+1) + ". " + randomNumbers[i] + " ");
        }

        System.out.println("\nChoose a number 1-5: ");
        int numberChoice = sc.nextInt();
        chosenNumber = randomNumbers[numberChoice - 1];
        System.out.println(chosenNumber);

        System.out.println("\nWho starts the game: 1 - player, other int - computer");
        int firstMove = sc.nextInt();

        System.out.println("\n1 - Minmax, other int - AlphaBeta");
        algorithm = sc.nextInt();

        if (firstMove == 1) {
            playerMove();
        }
        else {
            if (algorithm == 1) {
                // function from Minmax that returns best divisor
                computerMove(MinMaxAlgorithm.findBestMove());
            }
            else {
                // function from AlphaBeta that returns best divisor
                computerMove(AlphaBetaAlgorithm.findBestMove());
            }
        }

        sc.close();
    }

    public static void playerMove() {
        Scanner sc = new Scanner(System.in);
        int divisor;

        System.out.println("Divide " + chosenNumber + " by 2 or 3?");
        do {
            divisor = sc.nextInt();
        }
        while (chosenNumber % divisor != 0);

        chosenNumber /= divisor;

        if (chosenNumber % 2 == 0 && (chosenNumber % 10 != 0 || chosenNumber % 10 != 5)) {
            playerScore += 1;
        }
        else if(chosenNumber % 10 == 0 || chosenNumber % 10 == 5) {
            bank += 1;
        }
        else {
            playerScore -= 1;
        }


        System.out.println(chosenNumber);

        if ((chosenNumber % 2 == 0 || chosenNumber % 3 == 0) && chosenNumber != 2) {
            if (algorithm == 1) {
                // function from Minmax that returns best divisor
                computerMove(MinMaxAlgorithm.findBestMove());
            }
            else {
                // function from AlphaBeta that returns best divisor
                computerMove(AlphaBetaAlgorithm.findBestMove());
            }
        }
        else if (chosenNumber == 2) {
            playerScore += bank;
            endGame();
        }
        else {
            endGame();
        }

        sc.close();
    }

    public static void computerMove(int divisor) {
        chosenNumber /= divisor;

        if (chosenNumber % 2 == 0 && (chosenNumber % 10 != 0 || chosenNumber % 10 != 5)) {
            computerScore += 1;
        }
        else if (chosenNumber % 10 == 0 || chosenNumber % 10 == 5) {
            bank += 1;
        }
        else {
            computerScore -= 1;
        }

        System.out.println(chosenNumber);

        if ((chosenNumber % 2 == 0 || chosenNumber % 3 == 0) && chosenNumber != 2) {
            playerMove();
        }
        else if (chosenNumber == 2) {
            computerScore += bank;
            endGame();
        }
        else {
            endGame();
        }
    }

    public static void endGame() {
        Scanner sc = new Scanner(System.in);

        System.out.println("playerScore: " + playerScore);
        System.out.println("computerScore: " + playerScore);

        if (playerScore > computerScore) {
            System.out.println("Player wins!");
        }
        else if (playerScore < computerScore) {
            System.out.println("Computer wins!");
        }
        else {
            System.out.println("Draw!");
        }

        System.out.println("Play again? 1 - yes");
        int playAgain = sc.nextInt();
        if (playAgain == 1) {
            startGame();
        }
        else {
            System.exit(0);
        }

        sc.close();
    }

    public static int generateRandomNumber() {
        Random random = new Random();
        int randomNumber;

        do {
            randomNumber = random.nextInt(10001) + 10000;
        }
        while (randomNumber % 2 != 0 || randomNumber % 3 != 0);

        return randomNumber;
    }

    public static int getCurrentNumber() {
        return chosenNumber;
    }
}