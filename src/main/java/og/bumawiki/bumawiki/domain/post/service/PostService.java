package og.bumawiki.bumawiki.domain.post.service;

import lombok.RequiredArgsConstructor;
import og.bumawiki.bumawiki.domain.post.domain.Post;
import og.bumawiki.bumawiki.domain.post.domain.repository.PostRepository;
import og.bumawiki.bumawiki.domain.post.web.request.PostCreateRequestDto;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final OkHttpClient okHttpClient;

    @Transactional
    public Long createPost(PostCreateRequestDto request){
        Post post = postRepository.save(request.toEntity());

        return post.getId();
    }


}
