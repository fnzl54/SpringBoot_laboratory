package SpringBoot_laboratory.chan;

import SpringBoot_laboratory.chan.domain.redis.domain.Entity;
import SpringBoot_laboratory.chan.domain.redis.domain.RedisRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChanApplicationTests {

	@Autowired
	private RedisRepository repository;

	@Test
	void test() {
		Entity person = new Entity("Park", 20);

		// 저장
		repository.save(person);

		// `keyspace:id` 값을 가져옴
		System.out.println(repository.findById(person.getId()).get().getAge());

		// Person Entity 의 @RedisHash 에 정의되어 있는 keyspace (people) 에 속한 키의 갯수를 구함
		System.out.println(repository.count());

		// 삭제
//		repository.delete(person);
	}

}
