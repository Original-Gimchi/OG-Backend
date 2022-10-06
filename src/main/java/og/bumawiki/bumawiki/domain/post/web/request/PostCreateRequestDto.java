package og.bumawiki.bumawiki.domain.post.web.request;

import lombok.Builder;
import lombok.Data;
import og.bumawiki.bumawiki.domain.post.domain.Post;

@Data
public class PostCreateRequestDto {

    private Long title;

    private Long content;

    private int view = 1;

    @Builder
    public PostCreateRequestDto(Long title, Long content, int view) {
        this.title = title;
        this.content = content;
        this.view = view;
    }

    public Post toEntity(){
         return Post.builder()
                .title(title)
                .content(content)
                .view(view)
                .build();
    }
}
