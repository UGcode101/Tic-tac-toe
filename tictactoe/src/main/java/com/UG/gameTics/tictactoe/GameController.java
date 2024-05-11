package com.UG.gameTics.tictactoe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tictactoe")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping("/new")
    public String newGame(@RequestParam String gameId, Model model) {
        gameService.createGame(gameId);
        return "redirect:/tictactoe/play?gameId=" + gameId;
    }

    @PostMapping("/move")
    public String playMove(@RequestParam String gameId, @RequestParam int x, @RequestParam int y) {
        gameService.playMove(gameId, x, y);
        return "redirect:/tictactoe/play?gameId=" + gameId;
    }

    @GetMapping("/winner")
    public String checkWinner(@RequestParam String gameId, Model model) {
        String winner = gameService.getWinner(gameId);
        model.addAttribute("winner", winner != null ? winner : "No one (It's a draw!)");
        return "winner";
    }

    @GetMapping("/play")
    public String playGame(@RequestParam String gameId, Model model) {
        Game game = gameService.getGame(gameId);
        model.addAttribute("gameId", gameId);
        model.addAttribute("game", game);
        if (game.getWinner() != null) {
            return "redirect:/tictactoe/winner?gameId=" + gameId;
        } else if (game.isDraw()) {
            return "redirect:/tictactoe/draw?gameId=" + gameId; // Redirects to the draw page if the game is a draw
        }
        return "play";
    }

    @GetMapping("/draw")
    public String drawGame(@RequestParam String gameId, Model model) {
        model.addAttribute("gameId", gameId);
        return "draw"; // Return the draw template
    }

}
