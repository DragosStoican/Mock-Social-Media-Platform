package uk.ac.york.eng2.assessment.y1234.trending.repositories;
/* protected region Imports on begin */
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import uk.ac.york.eng2.assessment.y1234.trending.domain.*;
import uk.ac.york.eng2.assessment.y1234.trending.dto.*;
/* protected region Imports end */

@Repository
public interface TrendRepository extends CrudRepository<Trend, Long> {
	
	/* protected region Custom repository methods on begin */
	boolean existsByHour(String hour);
	/* protected region Custom repository methods end */
	
}