package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TikTakToeGame game = new TikTakToeGame();
        System.out.println(game.getState());
        System.out.print(Messages.ASK_COORDINATES.message);
        while (true) {
            boolean result;
            do {
                String input = scanner.nextLine();
                result = game.validateUserInput(input);
            } while (!result);
            game.updateCoordinates();
            System.out.println(game.getState());
            if (game.getResult() != Messages.NOT_FINISHED) {
                break;
            }
            System.out.print(Messages.ASK_COORDINATES.message);
        }
        System.out.println(game.getStatusAsString());
    }
}
