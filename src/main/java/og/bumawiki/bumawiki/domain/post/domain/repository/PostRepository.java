package og.bumawiki.bumawiki.domain.post.domain.repository;

import og.bumawiki.bumawiki.domain.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.title = :title")
    Page<Post> findByTitle(Pageable pageable);

    @NotNull
    @Query("select p from Post p order by p.view desc , p.createdTime asc")
    Page<Post> findByViewDesc(@NotNull Pageable pageable);

    @NotNull
    @Query("select p from Post p")
    Page<Post> findAll(@NotNull Pageable pageable);
}
