package com.petweb.sponge.post.dto.post;

import lombok.Getter;

import java.util.List;

@Getter
public class PostCreate {

    private Long petId;
    private String title;
    private String content;
    private String duration;
    private List<Long> categoryCodeList;
    private List<String> hashTagList;
    private List<String> fileUrlList;
}
