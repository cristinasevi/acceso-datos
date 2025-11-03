package acceso.datos.games.service;

import acceso.datos.games.domain.Game;
import acceso.datos.games.domain.Review;
import acceso.datos.games.domain.User;
import acceso.datos.games.dto.ReviewInDto;
import acceso.datos.games.exception.ReviewNotFoundException;
import acceso.datos.games.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Review add(ReviewInDto reviewInDto, Game game, User user) {
        // ToDo Usar ModelMapper
        Review review = new Review();
        review.setGame(game);
        review.setUser(user);
        modelMapper.map(reviewInDto, review);

        return reviewRepository.save(review);
    }

    public void delete(long id) throws ReviewNotFoundException {

    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review findById(long id) throws ReviewNotFoundException {
        return null;
    }

    public Review modify(long id, Review review) throws ReviewNotFoundException {
        return null;
    }
}
