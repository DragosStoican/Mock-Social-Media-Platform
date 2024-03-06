package uk.ac.york.eng2.assessment.y1234.subscription.repositories;
/* protected region Imports on begin */
import java.util.Optional;

import javax.validation.constraints.NotNull;

import io.micronaut.data.annotation.Join;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import uk.ac.york.eng2.assessment.y1234.subscription.domain.*;
import uk.ac.york.eng2.assessment.y1234.subscription.dto.*;

/* protected region Imports end */

@Repository
public interface VideoRepository extends CrudRepository<Video, Long> {
	
	/* protected region Custom repository methods on begin */
	@Join(value = "user", type = Join.Type.LEFT_FETCH)
	@Join(value = "hashtags", type = Join.Type.LEFT_FETCH)
	@Join(value = "viewedBy", type = Join.Type.LEFT_FETCH)
	@Override
	Optional<Video> findById(@NotNull Long id);
	/* protected region Custom repository methods end */
	
}