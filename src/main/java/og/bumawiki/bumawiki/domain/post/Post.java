package og.bumawiki.bumawiki.domain.post;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import og.bumawiki.bumawiki.global.entity.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 32)
    private Long title;

    @Lob
    private Long content;

    private int view = 1;


    @Builder
    public Post(Long id, Long title, Long content, int view) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.view = view;
    }
}
