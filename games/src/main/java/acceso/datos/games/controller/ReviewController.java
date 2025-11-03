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
        // User user = userService.findById(reviewInDto.getUserId());
        reviewService.add(reviewInDto, game, null);
        return null;
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
        ErrorResponse errorResponse = new ErrorResponse(404, "not-found", "The review does not exist");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    };
}
