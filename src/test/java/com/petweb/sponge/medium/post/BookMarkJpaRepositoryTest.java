package com.petweb.sponge.medium.post;

import com.petweb.sponge.post.repository.post.BookmarkEntity;
import com.petweb.sponge.post.repository.post.BookmarkJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest(showSql = false)
@Sql("/sql/post-repository-test-data.sql")
@Sql("/sql/bookmark-repository-test-data.sql")
public class BookMarkJpaRepositoryTest {


    @Autowired
    private BookmarkJpaRepository bookmarkJpaRepository;
    private static int PAGE_SIZE = 10;


    @Test
    public void findBookmark는_postId와_로그인아이디로_북마크가있는지_찾는다() {
        // given
        Long postId = 1L;
        Long userId = 1L;

        // when
        Optional<BookmarkEntity> bookmark = bookmarkJpaRepository.findBookmark(postId, userId);

        // then
        assertThat(bookmark).isPresent(); // Optional이 비어있지 않은지 확인
        bookmark.ifPresent(b -> {
            assertThat(b.getPostId()).isEqualTo(postId);
            assertThat(b.getUserId()).isEqualTo(userId);
        });
    }

    @Test
    public void findBookmarkList는_로그인아이디에_속한_BookmarkEntity를_모두찾는다() {
        // given
        Long userId = 1L;
        int page = 0;
        int offset = page * PAGE_SIZE;

        // when
        List<BookmarkEntity> bookmarkList = bookmarkJpaRepository.findBookmarkList(userId, PAGE_SIZE, offset);

        // then
        assertThat(bookmarkList).isNotNull();
        assertThat(bookmarkList).isNotEmpty();
        assertThat(bookmarkList).hasSizeLessThanOrEqualTo(PAGE_SIZE);
        assertThat(bookmarkList).allMatch(bookmark -> bookmark.getUserId().equals(userId));

    }

    @Test
    public void deleteByPostId는_postId로_관련된_북마크를_다삭제한다() {

        // given
        Long postId = 1L;

        // when
        bookmarkJpaRepository.deleteByPostId(postId);

        // then
        List<BookmarkEntity> bookmarkList = bookmarkJpaRepository.findAll();
        assertThat(bookmarkList).hasSize(2);
        assertThat(bookmarkList).allMatch(bookmarkEntity -> bookmarkEntity.getPostId().equals(2L));


    }


}
