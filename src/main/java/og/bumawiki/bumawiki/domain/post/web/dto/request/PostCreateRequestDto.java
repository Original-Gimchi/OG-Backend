package og.bumawiki.bumawiki.domain.post.web.dto.request;

import lombok.Builder;
import lombok.Data;
import og.bumawiki.bumawiki.domain.post.domain.Post;

@Data
public class PostCreateRequestDto {

    private String title;

    private String content;

    @Builder
    public PostCreateRequestDto(String title, String content, int view) {
        this.title = title;
        this.content = content;
    }

    public Post toEntity(){
         return Post.builder()
                .title(title)
                .content(content)
                .build();
    }
}
