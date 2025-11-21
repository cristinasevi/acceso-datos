package acceso.datos.games.controller;

import acceso.datos.games.domain.Review;
import acceso.datos.games.domain.User;
import acceso.datos.games.dto.GameDto;
import acceso.datos.games.dto.ReviewInDto;
import acceso.datos.games.exception.ErrorResponse;
import acceso.datos.games.exception.GameNotFoundException;
import acceso.datos.games.exception.ReviewNotFoundException;
import acceso.datos.games.exception.UserNotFoundException;
import acceso.datos.games.service.GameService;
import acceso.datos.games.service.ReviewService;
import acceso.datos.games.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private GameService gameService;
    @Autowired
    private UserService userService;
    private final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAll() {
        logger.info("GET /reviews");
        List<Review> reviews = reviewService.findAll();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/games/{gameId}/reviews")
    public ResponseEntity<List<Review>> getGameReviews(@PathVariable long gameId) throws GameNotFoundException {
        GameDto gameDto = gameService.findById(gameId);
        List<Review> reviews = reviewService.findByGame(gameDto);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<Review> get(@PathVariable long id) throws ReviewNotFoundException {
        return null;
    }

    @PostMapping("/games/{gameId}/reviews")
    public ResponseEntity<Review> addReview(@Valid @RequestBody ReviewInDto reviewInDto, @PathVariable long gameId)
            throws GameNotFoundException, UserNotFoundException {
        // ToDo Añadir validación
        GameDto gameDto = gameService.findById(gameId);
        User user = userService.findById(reviewInDto.getUserId());
        Review review = reviewService.add(reviewInDto, gameDto, user);
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
        logger.error("The user does not exist", unfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    };

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
