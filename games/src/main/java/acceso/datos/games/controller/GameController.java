package acceso.datos.games.controller;

import acceso.datos.games.domain.Game;
import acceso.datos.games.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/games")
    public List<Game> getAll() {
        List<Game> allGames = gameService.findAll();
        return allGames;
    }

    @PostMapping("/games")
    public void addGame(@RequestBody Game game) {
        gameService.add(game);
    }
}
