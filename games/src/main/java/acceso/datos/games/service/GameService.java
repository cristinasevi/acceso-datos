package acceso.datos.games.service;

import acceso.datos.games.domain.Game;
import acceso.datos.games.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public void add(Game game) {
        gameRepository.save(game);
    }

    public void delete(Game game) {

    }

    public List<Game> findAll() {
        List<Game> allGames = gameRepository.findAll();
        return allGames;
    }

    public Game findById(long id) {
        return null;
    }

    public void modify(Game game) {

    }
}
