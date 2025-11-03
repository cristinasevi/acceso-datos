package acceso.datos.games.controller;

import acceso.datos.games.domain.Game;
import acceso.datos.games.domain.User;
import acceso.datos.games.exception.ErrorResponse;
import acceso.datos.games.exception.UserNotFoundException;
import acceso.datos.games.service.GameService;
import acceso.datos.games.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll() {
        return null;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> get(@PathVariable long id) throws UserNotFoundException {
        return null;
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        // ToDo Añadir validación
        // ToDo Comprobar que no exista ya un juego con el mismo username
        return null;
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> modifyUser(@PathVariable long id, @RequestBody User user) throws UserNotFoundException {
        return null;
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) throws UserNotFoundException {
        return null;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(UserNotFoundException unfe) {
        ErrorResponse errorResponse = new ErrorResponse(404, "not-found", "The user does not exist");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    };
}
