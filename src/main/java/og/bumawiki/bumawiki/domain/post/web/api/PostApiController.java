package og.bumawiki.bumawiki.domain.post.web.api;

import lombok.RequiredArgsConstructor;
import og.bumawiki.bumawiki.domain.post.domain.type.PostType;
import og.bumawiki.bumawiki.domain.post.service.PostService;
import og.bumawiki.bumawiki.domain.post.web.dto.request.PostCreateRequestDto;
import og.bumawiki.bumawiki.domain.post.web.dto.response.PostResponseDto;
import og.bumawiki.bumawiki.global.generic.Result;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostApiController {

    private final PostService postService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Long create(@RequestBody PostCreateRequestDto requestDto){
        return postService.create(requestDto);
    }

    @GetMapping("/find/title")
    @ResponseStatus(HttpStatus.OK)
    public Result<List<PostResponseDto>> findByTitle(@RequestParam String title,
                                                     @PageableDefault(size = 10) Pageable pageable){
        List<PostResponseDto> post = postService.findByTitle(title, pageable);
        return new Result<>(post.size(), post);
    }

    @GetMapping("/fnd/viewDesc")
    @ResponseStatus(HttpStatus.OK)
    public Result<List<PostResponseDto>> findByViewDesc(@PageableDefault(size = 10) Pageable pageable){
        List<PostResponseDto> post = postService.findByViewDesc(pageable);

        return new Result<>(post.size(), post);
    }

    @GetMapping("/find/All")
    @ResponseStatus(HttpStatus.OK)
    public Result<List<PostResponseDto>> findAll(@PageableDefault(size = 10) Pageable pageable){
        List<PostResponseDto> posts = postService.findAll(pageable);

        return new Result<>(posts.size(), posts);
    }

    @GetMapping("find/postType/{type}")
    @ResponseStatus(HttpStatus.OK)
    public Result<List<PostResponseDto>> findByType(@PathVariable PostType type, @PageableDefault(size = 10) Pageable pageable){
        List<PostResponseDto> posts = postService.findByType(type, pageable);
        return new Result<>(posts.size(), posts);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponseDto update(@PathVariable Long id,@RequestBody PostCreateRequestDto postCreateRequestDto){
        return postService.update(id, postCreateRequestDto);
    }

}
