package acceso.datos.games.service;

import acceso.datos.games.domain.Game;
import acceso.datos.games.dto.GameDto;
import acceso.datos.games.dto.GameOutDto;
import acceso.datos.games.exception.GameNotFoundException;
import acceso.datos.games.repository.GameRepository;
import acceso.datos.games.util.DateUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Game add(Game game) {
        return gameRepository.save(game);
    }

    public void delete(long id) throws GameNotFoundException {
        Game game = gameRepository.findById(id)
                .orElseThrow(GameNotFoundException::new);

        gameRepository.delete(game);
    }

    public List<GameOutDto> findAll(String category) {
        List<Game> games;

        if(!category.isEmpty()) {
            games = gameRepository.findByCategory(category);
        } else {
            games = gameRepository.findAll();
        }

        return modelMapper.map(games, new TypeToken<List<GameOutDto>>() {}.getType());
    }

    public GameDto findById(long id) throws GameNotFoundException {
        Game game = gameRepository.findById(id)
                .orElseThrow(GameNotFoundException::new);

        GameDto gameDto = modelMapper.map(game, GameDto.class);
        gameDto.setDaysToRelease(DateUtil.getDaysBetweenDates(LocalDate.now(), gameDto.getReleaseDate()));
        return gameDto;
    }

    public Game modify(long id, Game game) throws GameNotFoundException {
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(GameNotFoundException::new); // .orElseThrow(() -> new GameNotFoundException());

        modelMapper.map(game, existingGame);
        existingGame.setId(id);

        return gameRepository.save(existingGame);
    }
}
