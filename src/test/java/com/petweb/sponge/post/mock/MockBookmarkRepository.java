package com.petweb.sponge.post.mock;

import com.petweb.sponge.post.domain.post.Bookmark;
import com.petweb.sponge.post.repository.post.BookmarkRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class MockBookmarkRepository implements BookmarkRepository {
    private final AtomicLong autoGeneratedId = new AtomicLong(0);
    private final List<Bookmark> data = new CopyOnWriteArrayList<>();

    @Override
    public Optional<Bookmark> findBookmark(Long postId, Long loginId) {
        return data.stream()
                .filter(bookmark -> bookmark.getPostId().equals(postId) && bookmark.getUserId().equals(loginId))
                .findFirst();
    }

    @Override
    public void save(Bookmark bookmark) {
        Bookmark newBookmark = Bookmark.builder()
                .id(autoGeneratedId.incrementAndGet())
                .postId(bookmark.getPostId())
                .userId(bookmark.getUserId())
                .build();
        data.add(newBookmark);
    }

    @Override
    public void delete(Bookmark bookmark) {
        data.remove(bookmark);
    }

    @Override
    public List<Bookmark> findBookmarkList(Long loginId, int page) {
        return null;
    }
}
