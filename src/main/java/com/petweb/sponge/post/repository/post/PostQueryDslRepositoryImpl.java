package com.petweb.sponge.post.repository.post;

import com.petweb.sponge.utils.ProblemTypeCode;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.petweb.sponge.post.domain.QProblemType.*;
import static com.petweb.sponge.post.domain.post.QBookmark.*;
import static com.petweb.sponge.post.repository.post.QPostCategoryEntity.*;
import static com.petweb.sponge.post.repository.post.QPostEntity.*;
import static com.petweb.sponge.post.repository.post.QPostFileEntity.*;
import static com.petweb.sponge.post.repository.post.QTagEntity.*;
import static com.petweb.sponge.user.repository.QUserEntity.*;

public class PostQueryDslRepositoryImpl implements PostQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public PostQueryDslRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<PostEntity> findPostById(Long id) {
        PostEntity post = queryFactory
                .selectFrom(postEntity)
                .where(postEntity.id.eq(id))
                .fetchOne();
        List<PostFileEntity> postFileEntityList = queryFactory
                .selectFrom(postFileEntity)
                .where(postFileEntity.postEntity.id.eq(id))
                .fetch();
        List<TagEntity> tagEntityList = queryFactory
                .selectFrom(tagEntity)
                .where(tagEntity.postEntity.id.eq(id))
                .fetch();
        List<PostCategoryEntity> postCategoryEntityList = queryFactory
                .selectFrom(postCategoryEntity)
                .leftJoin(postCategoryEntity.problemType, problemType).fetchJoin()
                .where(postCategoryEntity.postEntity.id.eq(id))
                .fetch();
        if (post != null) {
            post.addPostFileList(postFileEntityList);
            post.addTagList(tagEntityList);
            post.addPostCategoryList(postCategoryEntityList);
        }
        return Optional.ofNullable(post);
    }

    private static final int PAGE_SIZE = 10;  // 페이지당 항목 수

    @Override
    public List<PostEntity> findListByCode(Long problemTypeCode, int page) {
        // 페이지 번호와 페이지 크기를 계산
        int offset = page * PAGE_SIZE;
        if (Objects.equals(problemTypeCode, ProblemTypeCode.ALL.getCode())) {
            //전체 조회
            return queryFactory
                    .select(postEntity)
                    .from(postEntity)
                    .leftJoin(postEntity.postCategoryEntityList, postCategoryEntity).fetchJoin()
                    .orderBy(postEntity.createdAt.desc()) //최신순 정렬
                    .offset(offset)
                    .limit(PAGE_SIZE)
                    .fetch();
        } else {
            // 카테고리에 해당하는 글 ID 조회
            List<Long> problemPostIds = queryFactory
                    .select(postCategoryEntity.postEntity.id)
                    .from(postCategoryEntity)
                    .where(postCategoryEntity.problemType.code.eq(problemTypeCode))
                    .orderBy(postCategoryEntity.postEntity.createdAt.desc())  // 최신순으로 정렬
                    .offset(offset)
                    .limit(PAGE_SIZE)
                    .fetch();
            if (problemPostIds.isEmpty()) {
                return new ArrayList<>();  // 결과가 없으면 빈 리스트 반환
            }
            return queryFactory
                    .selectDistinct(postEntity)
                    .from(postEntity)
                    .leftJoin(postEntity.postCategoryEntityList, postCategoryEntity).fetchJoin()
                    .where(postEntity.id.in(problemPostIds))  // IN 절 사용
                    .fetch();
        }
    }

//    @Override
//    public List<PostEntity> searchPostByKeyword(String keyword, int page) {
//        // 페이지 번호와 페이지 크기를 계산
//        int offset = page * PAGE_SIZE;
//        List<PostEntity> postEntities = queryFactory
//                .selectFrom(problemPost)
//                .leftJoin(problemPost.tags, tag).fetchJoin()
//                .where(problemPost.title.containsIgnoreCase(keyword) // 제목에서 검색
//                        .or(problemPost.content.containsIgnoreCase(keyword))
//                        .or(tag.hashtag.containsIgnoreCase(keyword))) // 내용에서 검색
//                .orderBy(problemPost.createdAt.desc()) // 최신순 정렬
//                .offset(offset)
//                .limit(PAGE_SIZE)
//                .fetch();
//        List<Long> problemPostIds = postEntities.stream().map(PostEntity::getId).collect(Collectors.toList());
//
//        // 포스트 카테고리 조인
//        List<PostCategoryEntity> postCategoryEntityList = queryFactory
//                .selectFrom(postCategory)
//                .where(postCategory.problemPost.id.in(problemPostIds))
//                .fetch();
//        postEntities.forEach(post -> post.setPostCategories(postCategoryEntityList.stream().filter(
//                category -> Objects.equals(post.getId(), category.getPostEntity().getId())
//        ).toList()));
//        return postEntities;
//    }
//
//    @Override
//    public List<PostEntity> findAllPostByBookmark(Long loginId) {
//
//        return queryFactory
//                .select(bookmark.problemPost)
//                .from(bookmark)
//                .leftJoin(bookmark.problemPost.postCategories, postCategory).fetchJoin()
//                .where(bookmark.userEntity.id.eq(loginId))
//                .fetch();
//
//    }
//
//    @Override
//    public void deletePost(Long problemPostId) {
//        //카테고리별 글 삭제
//        queryFactory
//                .delete(postCategory)
//                .where(postCategory.problemPost.id.eq(problemPostId))
//                .execute();
//        //게시글 추천 삭제
//        queryFactory
//                .delete(postRecommend)
//                .where(postRecommend.problemPost.id.eq(problemPostId))
//                .execute();
//        //북마크 삭제
//        queryFactory
//                .delete(bookmark)
//                .where(bookmark.problemPost.id.eq(problemPostId))
//                .execute();
//        //해시태그 삭제
//        queryFactory
//                .delete(tag)
//                .where(tag.problemPost.id.eq(problemPostId))
//                .execute();
//        //이미지 삭제
//        queryFactory
//                .delete(postFile)
//                .where(postFile.problemPost.id.eq(problemPostId))
//                .execute();
//        //게시글 삭제
//        queryFactory
//                .delete(problemPost)
//                .where(problemPost.id.eq(problemPostId))
//                .execute();
//    }
//
//    @Override
//    public void initProblemPost(Long problemPostId) {
//        //카테고리별 글 삭제
//        queryFactory
//                .delete(postCategory)
//                .where(postCategory.problemPost.id.eq(problemPostId))
//                .execute();
//        //해시태그 삭제
//        queryFactory
//                .delete(tag)
//                .where(tag.problemPost.id.eq(problemPostId))
//                .execute();
//        //이미지 삭제
//        queryFactory
//                .delete(postFile)
//                .where(postFile.problemPost.id.eq(problemPostId))
//                .execute();
//    }
}
