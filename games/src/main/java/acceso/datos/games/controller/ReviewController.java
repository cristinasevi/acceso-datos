package acceso.datos.games.controller;

import acceso.datos.games.domain.Game;
import acceso.datos.games.domain.Review;
import acceso.datos.games.domain.User;
import acceso.datos.games.dto.ReviewInDto;
import acceso.datos.games.exception.ErrorResponse;
import acceso.datos.games.exception.GameNotFoundException;
import acceso.datos.games.exception.ReviewNotFoundException;
import acceso.datos.games.exception.UserNotFoundException;
import acceso.datos.games.service.GameService;
import acceso.datos.games.service.ReviewService;
import acceso.datos.games.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private GameService gameService;
    @Autowired
    private UserService userService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAll() {
        List<Review> reviews = reviewService.findAll();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<Review> get(@PathVariable long id) throws ReviewNotFoundException {
        return null;
    }

    @PostMapping("/reviews")
    public ResponseEntity<Review> addReview(@RequestBody ReviewInDto reviewInDto)
            throws GameNotFoundException, UserNotFoundException {
        // ToDo Añadir validación
        Game game = gameService.findById(reviewInDto.getGameId());
        User user = userService.findById(reviewInDto.getUserId());
        Review review = reviewService.add(reviewInDto, game, user);
        // ToDo Devolver un objeto ReviewOutDto
        return ResponseEntity.ok(review);
    }

    @PutMapping("/reviews/{id}")
    public ResponseEntity<Review> modifyReview(@PathVariable long id, @RequestBody Review review) throws ReviewNotFoundException {
        return null;
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable long id) throws ReviewNotFoundException {
        return null;
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(ReviewNotFoundException rnfe) {
        ErrorResponse errorResponse = ErrorResponse.notFound("The review does not exist");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    };

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(UserNotFoundException unfe) {
        ErrorResponse errorResponse = ErrorResponse.notFound("The user does not exist");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    };
}
