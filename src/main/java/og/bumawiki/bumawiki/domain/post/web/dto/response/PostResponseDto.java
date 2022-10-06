package og.bumawiki.bumawiki.domain.post.web.dto.response;

import lombok.Data;
import og.bumawiki.bumawiki.domain.post.domain.Post;

@Data
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private int view;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.view = post.getView();
    }
}
