package com.UG.gameTics.tictactoe;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class GameService {
    private final Map<String, Game> games = new HashMap<>();

    public Game createGame(String gameId) {
        Game game = new Game();
        games.put(gameId, game);
        return game;
    }

    public Game getGame(String gameId) {
        return games.get(gameId);
    }

    public boolean playMove(String gameId, int x, int y) {
        Game game = games.get(gameId);
        return game != null && game.playMove(x, y);
    }

    public String getWinner(String gameId) {
        Game game = games.get(gameId);
        return game != null ? game.getWinner() : null;
    }

    public boolean isGameOver(String gameId) {
        Game game = games.get(gameId);
        return game != null && game.isGameOver();
    }
}

