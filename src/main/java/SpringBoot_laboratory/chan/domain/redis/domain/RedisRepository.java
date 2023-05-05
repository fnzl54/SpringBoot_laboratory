package SpringBoot_laboratory.chan.domain.redis.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisRepository extends CrudRepository<Entity, String> {
}
