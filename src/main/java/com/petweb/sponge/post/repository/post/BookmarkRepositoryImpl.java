package com.petweb.sponge.post.repository.post;

import com.petweb.sponge.post.domain.post.Bookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookmarkRepositoryImpl implements BookmarkRepository {

    private final BookmarkJpaRepository bookmarkJpaRepository;

    @Override
    public Optional<Bookmark> findBookmark(Long postId, Long loginId) {
        return bookmarkJpaRepository.findBookmark(postId,loginId).map(BookmarkEntity::toModel);
    }

    @Override
    public void save(Bookmark newBookmark) {
        bookmarkJpaRepository.save(BookmarkEntity.from(newBookmark));
    }

    @Override
    public void delete(Bookmark bookmark) {
        bookmarkJpaRepository.delete(BookmarkEntity.from(bookmark));
    }
}
