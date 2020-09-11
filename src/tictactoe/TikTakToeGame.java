package tictactoe;

import java.util.Scanner;

public class TikTakToeGame {
    private final char[][] state = {
            {'_', '_', '_'},
            {'_', '_', '_'},
            {'_', '_', '_'}
    };

    private int column;
    private int row;
    private Messages result = Messages.NOT_FINISHED;
    private char activePlayer = 'X';

    private void showErrorMessage(Messages message) {
        System.out.printf(message.message + "%n" + Messages.ASK_COORDINATES.message);
    }

    public boolean validateUserInput(String input) {
        Scanner scanner = new Scanner(input).useDelimiter(" ");

        int column;
        int row;

        if (scanner.hasNextInt()) {
            row = scanner.nextInt();
        } else {
            this.showErrorMessage(Messages.ERROR_ONLY_NUMBERS);
            return false;
        }

        if (scanner.hasNextInt()) {
            column = scanner.nextInt();
        } else {
            this.showErrorMessage(Messages.ERROR_ONLY_NUMBERS);
            return false;
        }

        if (!(1 <= column && column <= 3)) {
            this.showErrorMessage(Messages.ERROR_INCORRECT_RANGE);
            return false;
        }

        if (!(1 <= row && row <= 3)) {
            this.showErrorMessage(Messages.ERROR_INCORRECT_RANGE);
            return false;
        }

        int[] coords = this.convertIndexes(column, row);

        if (this.state[coords[0]][coords[1]] != '_') {
            this.showErrorMessage(Messages.ERROR_OCCUPIED_CELL);
            return false;
        }

        this.row = coords[0];
        this.column = coords[1];
        return true;
    }

    protected void updateCoordinates() {
        this.state[this.row][this.column] = this.activePlayer;
        this.updateStatus();
        this.activePlayer = this.activePlayer == 'X' ? 'O' : 'X';
    }

    protected int[] convertIndexes(int column, int row) {
        int[][][] map = {
                {},
                {{0}, {2, 0}, {1, 0}, {0, 0}},
                {{0}, {2, 1}, {1, 1}, {0, 1}},
                {{0}, {2, 2}, {1, 2}, {0, 2}}
        };
        return map[row][column];
    }

    public String getState() {
        StringBuilder out = new StringBuilder();
        out.append("---------\n");
        for (char[] chars : this.state) {
            StringBuilder row = new StringBuilder();
            row.append("| ");
            for (char aChar : chars) {
                row.append(aChar).append(" ");
            }
            row.append("|");
            out.append(row).append("\n");
        }
        out.append("---------");
        return out.toString();
    }

    public Messages getResult() {
        return this.result;
    }

    private void updateStatus() {
        int countOfX = 0;
        int countOfO = 0;
        int emptyCount = 0;
        boolean xWins = false;
        boolean oWins = false;
        for (int i = 0; i < this.state.length; i++) {
            if (i == 0) {
                boolean equalsFirstDiagonal = this.state[i][0] == this.state[1][1] &&
                        this.state[i][0] == this.state[2][2];
                boolean equalsSecondDiagonal = this.state[i][2] == this.state[1][1] &&
                        this.state[2][0] == this.state[i][2];
                if ((equalsFirstDiagonal || equalsSecondDiagonal) && this.state[1][1] == 'X') {
                    xWins = true;
                } else if ((equalsFirstDiagonal || equalsSecondDiagonal) && this.state[1][1] == 'O') {
                    oWins = true;
                }
            }

            boolean equalHorizontal = this.state[i][0] == this.state[i][1] && this.state[i][0] == this.state[i][2];

            if (equalHorizontal && this.state[i][0] == 'X') {
                xWins = true;
            } else if (equalHorizontal && this.state[i][0] == 'O') {
                oWins = true;
            }

            for (int j = 0; j < this.state[i].length; j++) {
                char current = this.state[i][j];
                if ('X' == current) {
                    countOfX++;
                } else if ('O' == current) {
                    countOfO++;
                } else {
                    emptyCount++;
                }
                if (i == 0) {
                    boolean equalVertical = this.state[i][j] == this.state[1][j] &&
                            this.state[i][j] == this.state[2][j];
                    if (equalVertical && 'X' == current) {
                        xWins = true;
                    } else if (equalVertical && 'O' == current) {
                        oWins = true;
                    }
                }
            }
        }

        if (oWins && xWins || Math.abs(countOfO - countOfX) >= 2) {
            this.result = Messages.IMPOSSIBLE;
        } else if (oWins) {
            this.result = Messages.WINNER_O;
        } else if (xWins) {
            this.result = Messages.WINNER_X;
        } else if (emptyCount == 0) {
            this.result = Messages.DRAW;
        } else {
            this.result = Messages.NOT_FINISHED;
        }
    }

    public String getStatusAsString() {
        return this.result.message;
    }
}
