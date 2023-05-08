package SpringBoot_laboratory.chan.domain.redis.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class PostEntity {
    @Id
    @GeneratedValue
    @Column(name = "post_id", nullable = false)
    private Long id;

    @Column(name = "post_like", nullable = false)
    private int like;
}
