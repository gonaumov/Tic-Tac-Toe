package tictactoe;

public enum Messages {
    ASK_COORDINATES("Enter the coordinates: "),
    ERROR_ONLY_NUMBERS("You should enter numbers!"),
    ERROR_INCORRECT_RANGE("Coordinates should be from 1 to 3!"),
    ERROR_OCCUPIED_CELL("This cell is occupied! Choose another one!"),
    NOT_FINISHED("Game not finished"),
    DRAW("Draw"),
    WINNER_X("X wins"),
    WINNER_O("O wins"),
    IMPOSSIBLE("Impossible");
    String message;
    Messages(String message) {
        this.message = message;
    }
}
