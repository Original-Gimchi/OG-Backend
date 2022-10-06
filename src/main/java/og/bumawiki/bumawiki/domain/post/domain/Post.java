package og.bumawiki.bumawiki.domain.post.domain;

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
    private String title;

    @Lob
    private String content;

    private int view = 1;


    @Builder
    public Post(String title, String content, int view) {
        this.title = title;
        this.content = content;
        this.view = view;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
