package uk.ac.york.eng2.assessment.y1234.video.repositories;
/* protected region Imports on begin */
import java.util.Optional;

import javax.validation.constraints.NotNull;

import io.micronaut.data.annotation.Join;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import uk.ac.york.eng2.assessment.y1234.video.domain.*;
import uk.ac.york.eng2.assessment.y1234.video.dto.*;

/* protected region Imports end */

@Repository
public interface HashtagRepository extends CrudRepository<Hashtag, Long> {
	
	/* protected region Custom repository methods on begin */
	Optional<Hashtag> findByName(String name);
	
	@Join(value = "videos", type = Join.Type.LEFT_FETCH)
	@Override
	Optional<Hashtag> findById(@NotNull Long id);
	
	Optional<HashtagDTO> findOne(Long id);
	/* protected region Custom repository methods end */
	
}