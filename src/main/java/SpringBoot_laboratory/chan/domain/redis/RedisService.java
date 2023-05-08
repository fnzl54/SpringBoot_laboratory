package SpringBoot_laboratory.chan.domain.redis;

import SpringBoot_laboratory.chan.domain.redis.domain.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    private final PostRepository postRepository;
    public void addLikesCntToRedis(Long problemId){
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        String key = "problemId::" + problemId;
        String hashkey = "likes";
        if(hashOperations.get(key,hashkey) == null){
            hashOperations.put(key,hashkey,postRepository.getById(problemId));
            hashOperations.increment(key,hashkey,1L);
            System.out.println(hashOperations.get(key,hashkey));
        }else
        {hashOperations.increment(key,hashkey,1L);
            System.out.println(hashOperations.get(key, hashkey));}
    }
}
