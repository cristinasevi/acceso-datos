package acceso.datos.games.repository;

import acceso.datos.games.domain.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ReviewRepository extends CrudRepository<Review, Long> {

    List<Review> findAll();
}
