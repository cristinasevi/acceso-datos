package acceso.datos.games.repository;

import acceso.datos.games.domain.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface GameRepository extends CrudRepository<Game, Long> {

    List<Game> findAll();
    List<Game> findByCategory(String category);
}
