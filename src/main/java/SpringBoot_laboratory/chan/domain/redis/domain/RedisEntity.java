package SpringBoot_laboratory.chan.domain.redis.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Getter
@RedisHash(value = "people", timeToLive = 120)
public class RedisEntity {

    @Id
    private String id;
    private String name;
    private Integer age;
    private LocalDateTime createdAt;

    public RedisEntity(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.createdAt = LocalDateTime.now();
    }
}
