package acceso.datos.games.repository;

import acceso.datos.games.domain.GameV2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface GameRepositoryV2 extends CrudRepository<GameV2, Long> {

}
