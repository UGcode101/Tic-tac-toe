package com.UG.gameTics.tictactoe;

public class Game {
    private String[][] board = new String[3][3]; // "X", "O", or null
    private String currentPlayer = "X"; // "X" or "O"
    private String winner = null;

    public Game() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = null;
            }
        }
    }

    public String[][] getBoard() {
        return board;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public String getWinner() {
        return winner;
    }

    public boolean playMove(int x, int y) {
        if (board[x][y] == null && winner == null) {
            board[x][y] = currentPlayer;
            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
            winner = checkWinner();
            return true;
        }
        return false;
    }

    private String checkWinner() {
        // Rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != null && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                return board[i][0];
            }
        }
        // Columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] != null && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) {
                return board[0][i];
            }
        }
        // Diagonals
        if (board[0][0] != null && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            return board[0][0];
        }
        if (board[0][2] != null && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
            return board[0][2];
        }
        return null;
    }

    public boolean isGameOver() {
        return winner != null || isBoardFull();
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }
}
