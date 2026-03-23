package acceso.datos.games.controller;

import acceso.datos.games.domain.Game;
import acceso.datos.games.domain.GameV2;
import acceso.datos.games.dto.GameDto;
import acceso.datos.games.dto.GameOutDto;
import acceso.datos.games.exception.ErrorResponse;
import acceso.datos.games.exception.GameNotFoundException;
import acceso.datos.games.service.GameService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")

public class GameController {

    @Autowired
    private GameService gameService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/v1/games")
    public ResponseEntity<List<GameOutDto>> getAll(@RequestParam(value = "category", defaultValue = "") String category) {
        List<GameOutDto> gamesOutDto = gameService.findAll(category);
        return ResponseEntity.ok(gamesOutDto);
    }

    @GetMapping("/v1/games/{id}")
    public ResponseEntity<GameDto> get(@PathVariable long id) throws GameNotFoundException {
        GameDto gameDto = gameService.findById(id);
        return ResponseEntity.ok(gameDto);
    }

    @PostMapping("/v1/games")
    public ResponseEntity<Game> addGameV1(@Valid @RequestBody Game game) {
        // ToDo Comprobar que no exista ya un juego con el mismo nombre
        Game newGame = gameService.add(game);
        return new ResponseEntity<>(newGame, HttpStatus.CREATED); // return ResponseEntity.status(HttpStatus.CREATED).body(newGame);
    }

    @PostMapping("/v2/games")
    public ResponseEntity<GameV2> addGameV2(@Valid @RequestBody GameV2 game) {
        // ToDo Comprobar que no exista ya un juego con el mismo nombre
        GameV2 newGame = gameService.addV2(game);
        return new ResponseEntity<>(newGame, HttpStatus.CREATED); // return ResponseEntity.status(HttpStatus.CREATED).body(newGame);
    }

    @PutMapping("/v1/games/{id}")
    public ResponseEntity<Game> modifyGame(@PathVariable long id, @RequestBody Game game) throws GameNotFoundException {
        Game newGame = gameService.modify(id, game);
        return ResponseEntity.ok(newGame); // return new ResponseEntity<>(newGame, HttpStatus.OK);
    }

    @DeleteMapping("/v1/games/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable long id) throws GameNotFoundException {
        gameService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(GameNotFoundException gnfe) {
        ErrorResponse errorResponse = ErrorResponse.notFound("The game does not exist");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    };

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException manve) {
        Map<String,String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName,message);
        });

        ErrorResponse errorResponse = ErrorResponse.validationError(errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
