package og.bumawiki.bumawiki.domain.post.service;

import lombok.RequiredArgsConstructor;
import og.bumawiki.bumawiki.domain.post.domain.Post;
import og.bumawiki.bumawiki.domain.post.domain.repository.PostRepository;
import og.bumawiki.bumawiki.domain.post.web.dto.request.PostCreateRequestDto;
import og.bumawiki.bumawiki.domain.post.web.dto.response.PostResponseDto;
import og.bumawiki.bumawiki.domain.user.repository.UserRepository;
import og.bumawiki.bumawiki.global.config.security.SecurityUtil;
import og.bumawiki.bumawiki.global.exception.CustomException;
import og.bumawiki.bumawiki.global.exception.ErrorCode;
import okhttp3.OkHttpClient;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long create(PostCreateRequestDto request){
        Post post = postRepository.save(request.toEntity());

        return post.getId();
    }

    public List<PostResponseDto> findByTitle(String title, Pageable pageable){
        return postRepository.findByTitle(title, pageable)
                .stream()
                .map(PostResponseDto :: new)
                .collect(Collectors.toList());
    }

    public List<PostResponseDto> findByViewDesc(Pageable pageable){
        return postRepository.findByViewDesc(pageable)
                .stream()
                .map(PostResponseDto :: new)
                .collect(Collectors.toList());
    }

    public List<PostResponseDto> findAll(Pageable pageable){
        return postRepository.findAll(pageable)
                .stream()
                .map(PostResponseDto :: new)
                .collect(Collectors.toList());
    }

    @Transactional
    public PostResponseDto update(Long id, PostCreateRequestDto request){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        userRepository.findByEmail(SecurityUtil.getLoginUserName())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_LOGIN));

        post.update(request.getTitle(), request.getContent());

        return new PostResponseDto(post);
    }
}
