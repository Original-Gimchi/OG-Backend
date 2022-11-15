package og.bumawiki.bumawiki.domain.post.service;

import lombok.RequiredArgsConstructor;
import og.bumawiki.bumawiki.domain.post.domain.Post;
import og.bumawiki.bumawiki.domain.post.domain.repository.PostRepository;
import og.bumawiki.bumawiki.domain.post.domain.type.PostType;
import og.bumawiki.bumawiki.domain.post.web.dto.request.PostCreateRequestDto;
import og.bumawiki.bumawiki.domain.post.web.dto.response.PostResponseDto;
import og.bumawiki.bumawiki.domain.user.repository.UserRepository;
import og.bumawiki.bumawiki.global.config.security.SecurityUtil;
import og.bumawiki.bumawiki.global.exception.CustomException;
import og.bumawiki.bumawiki.global.exception.ErrorCode;
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

    public List<PostResponseDto> findByType(PostType postType, Pageable pageable) {
        switch(postType){
            case STUDENT: return postRepository.findStudent(pageable).stream().map(PostResponseDto :: new).collect(Collectors.toList());
            case TEACHER: return postRepository.findTeacher(pageable).stream().map(PostResponseDto :: new).collect(Collectors.toList());
            case ACCIDENT: return postRepository.findAccident(pageable).stream().map(PostResponseDto :: new).collect(Collectors.toList());
            case CLUB: return postRepository.findClub(pageable).stream().map(PostResponseDto :: new).collect(Collectors.toList());
            default : return null;
        }
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

        post.update(request.getTitle(), request.getContent(), request.toEntity().getPostType());

        return new PostResponseDto(post);
    }
}
