package com.petweb.sponge.post.repository.post;

import com.petweb.sponge.post.domain.post.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository {

    Optional<Bookmark> findBookmark(Long postId, Long loginId);
    void save(Bookmark bookmark);

    void delete(Bookmark bookmark);

    List<Bookmark> findBookmarkList(Long loginId, int page);
}
