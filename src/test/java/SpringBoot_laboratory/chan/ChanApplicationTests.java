package SpringBoot_laboratory.chan;

import SpringBoot_laboratory.chan.domain.redis.domain.Entity;
import SpringBoot_laboratory.chan.domain.redis.domain.RedisRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.*;

@SpringBootTest
class ChanApplicationTests {

	@Autowired
	private RedisRepository repository;
	@Autowired
	private StringRedisTemplate redisTemplate;

	@Test
	void redis레포지토리테스트() {
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

	@Test
	public void RedisTemplate테스트_String() {
		final String key = "sabarada";

		final ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();

		stringStringValueOperations.set(key, "1"); // redis set 명령어
		final String result_1 = stringStringValueOperations.get(key); // redis get 명령어

		System.out.println("result_1 = " + result_1);

		stringStringValueOperations.increment(key); // redis incr 명령어
		final String result_2 = stringStringValueOperations.get(key);

		System.out.println("result_2 = " + result_2);
	}

	public void RedisTemplate테스트_Set() {
		final String key = "sabarada";
		final SetOperations<String, String> stringStringSetOperations = redisTemplate.opsForSet();

		stringStringSetOperations.add(key, "H");
		stringStringSetOperations.add(key, "e");
		stringStringSetOperations.add(key, "l");
		stringStringSetOperations.add(key, "l");
		stringStringSetOperations.add(key, "o");

		Set<String> sabarada = stringStringSetOperations.members(key);

		System.out.println("members = " + Arrays.toString(sabarada.toArray()));

		Long size = stringStringSetOperations.size(key);

		System.out.println("size = " + size);

		Cursor<String> cursor = stringStringSetOperations.scan(key, ScanOptions.scanOptions().match("*").count(3).build());

		while(cursor.hasNext()) {
			System.out.println("cursor = " + cursor.next());
		}
	}


}
