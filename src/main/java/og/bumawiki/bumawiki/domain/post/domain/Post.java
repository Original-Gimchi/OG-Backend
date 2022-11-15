package og.bumawiki.bumawiki.domain.post.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import og.bumawiki.bumawiki.domain.post.domain.type.PostType;
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

    @Enumerated(EnumType.STRING)
    private PostType postType;

    private int view = 1;


    @Builder
    public Post(String title, String content, int view, PostType postType) {
        this.title = title;
        this.content = content;
        this.view = view;
        this.postType = postType;
    }

    public void update(String title, String content, PostType postType){
        this.title = title;
        this.content = content;
        this.postType = postType;
    }
}
