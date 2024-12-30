package com.petweb.sponge.post.controller.response.post;

import com.petweb.sponge.post.domain.post.PostLike;
import com.petweb.sponge.post.domain.post.Bookmark;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@Getter
@Builder
public class CheckResponse {

    private boolean likeCheck;
    private boolean bookmarkCheck;

    public static CheckResponse from(Optional<PostLike> like, Optional<Bookmark> bookmark) {
        boolean likeFlag = false;
        boolean bookmarkFlag = false;

        if (like.isPresent()) likeFlag = true;
        if (bookmark.isPresent()) bookmarkFlag = true;
        return CheckResponse.builder()
                .likeCheck(likeFlag)
                .bookmarkCheck(bookmarkFlag)
                .build();

    }
}
