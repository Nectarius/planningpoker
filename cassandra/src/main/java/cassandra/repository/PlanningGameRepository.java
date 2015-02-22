package cassandra.repository;

import cassandra.entity.PlanningGame;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.MapId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by adelfiri on 2/22/15.//,
 */
public interface PlanningGameRepository extends CassandraRepository<PlanningGame>  {



}
