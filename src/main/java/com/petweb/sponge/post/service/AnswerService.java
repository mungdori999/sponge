package com.petweb.sponge.post.service;

import com.petweb.sponge.exception.error.*;
import com.petweb.sponge.post.controller.response.answer.*;
import com.petweb.sponge.post.domain.answer.AdoptAnswer;
import com.petweb.sponge.post.domain.answer.Answer;
import com.petweb.sponge.post.domain.answer.AnswerLike;
import com.petweb.sponge.post.domain.post.Post;
import com.petweb.sponge.post.dto.answer.AdoptAnswerDTO;
import com.petweb.sponge.post.dto.answer.AnswerCreate;
import com.petweb.sponge.post.dto.answer.AnswerUpdate;
import com.petweb.sponge.post.repository.answer.AdoptAnswerRepository;
import com.petweb.sponge.post.repository.answer.AnswerLikeRepository;
import com.petweb.sponge.post.repository.answer.AnswerRepository;
import com.petweb.sponge.post.repository.post.PostRepository;
import com.petweb.sponge.trainer.domain.Trainer;
import com.petweb.sponge.trainer.repository.TrainerRepository;
import com.petweb.sponge.user.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final AnswerRepository answerRepository;
    private final AdoptAnswerRepository adoptAnswerRepository;
    private final AnswerLikeRepository answerLikeRepository;
    /**
     * 훈련사 답변 조회
     *
     * @param postId
     * @return
     */
    @Transactional(readOnly = true)
    public List<AnswerDetailsListResponse> findAnswerList(Long postId) {
        List<Answer> answerList = answerRepository.findListByPostId(postId);
        List<Long> trainerIdList = answerList.stream().map((Answer::getTrainerId)).collect(Collectors.toList());
        List<Trainer> trainerList = trainerRepository.findShortByIdList(trainerIdList);
        Optional<AdoptAnswer> adoptAnswer = adoptAnswerRepository.findByPostId(postId);

        Map<Long, Trainer> trainerMap = trainerList.stream()
                .collect(Collectors.toMap(Trainer::getId, Function.identity()));
        return answerList.stream()
                .map(answer -> {
                    Trainer trainer = trainerMap.get(answer.getTrainerId());
                    boolean adoptCheck = adoptAnswer.map(adoptAnswerPresent ->
                            Objects.equals(adoptAnswerPresent.getTrainerId(), trainer.getId())
                    ).orElse(false);
                    return AnswerDetailsListResponse.from(AnswerResponse.from(answer), TrainerShortResponse.from(trainer), adoptCheck);
                })
                .collect(Collectors.toList());

    }

    /**
     * 내가 쓴 답변 조회
     * @param loginId
     * @param page
     */
    @Transactional(readOnly = true)
    public List<AnswerBasicListResponse> findMyInfo(Long loginId, int page) {
        List<Answer> answerList = answerRepository.findListByTrainerId(loginId, page);
        List<AdoptAnswer> adoptAnswerList = adoptAnswerRepository.findListByTrainerId(loginId);
        Set<Long> adoptAnswerIds = adoptAnswerList.stream()
                .map(AdoptAnswer::getId)
                .collect(Collectors.toSet());

        return answerList.stream().map(answer ->{
            boolean isAdopted = adoptAnswerIds.contains(answer.getId());
            return AnswerBasicListResponse.from(AnswerResponse.from(answer), isAdopted);
        }).collect(Collectors.toList());
    }


    /**
     * 훈련사 답변 저장
     *
     * @param loginId
     * @param answerCreate
     */
    @Transactional
    public void create(Long loginId, AnswerCreate answerCreate) {
        Post post = postRepository.findById(answerCreate.getPostId()).orElseThrow(
                NotFoundPost::new);
        Trainer trainer = trainerRepository.findById(loginId).orElseThrow(
                NotFoundTrainer::new);
        Answer answer = Answer.from(trainer.getId(), post.getId(), answerCreate);
        answer = answerRepository.save(answer);

        post.increaseAnswerCount();
        postRepository.save(post);
    }

    /**
     * 훈련사 답변내용 수정
     *
     * @param id
     * @param answerUpdate
     * @param loginId
     */
    @Transactional
    public void update(Long id, AnswerUpdate answerUpdate, Long loginId) {
        Answer answer = answerRepository.findById(id).orElseThrow(
                NotFoundAnswer::new);
        answer.checkTrainer(loginId);
        answer = answer.update(answerUpdate);
        answerRepository.save(answer);
    }

    /**
     * 훈련사 답변 삭제
     *
     * @param loginId
     * @param id
     */
    @Transactional
    public void delete(Long loginId, Long id) {
        Answer answer = answerRepository.findById(id).orElseThrow(
                NotFoundAnswer::new);
        answer.checkTrainer(loginId);
        Optional<AdoptAnswer> adoptAnswer = adoptAnswerRepository.findByAnswerId(answer.getId());
        Post post = postRepository.findShortById(answer.getPostId()).orElseThrow(
                NotFoundPost::new);

        //게시글 훈련사 답변 감소
        post.decreaseAnswerCount();
        postRepository.save(post);

        //채택되어있다면 카운트 감소
        if (adoptAnswer.isPresent()) {
            Trainer trainer = trainerRepository.findShortById(adoptAnswer.get().getTrainerId()).orElseThrow(
                    NotFoundTrainer::new);
            trainer.decreaseAdoptCount();
            trainerRepository.save(trainer);
        }
        answerRepository.delete(answer);
    }

    /**
     * 답변 채택 저장
     *
     * @param adoptAnswerDTO
     * @param loginId
     */
    @Transactional
    public void saveAdoptAnswer(AdoptAnswerDTO adoptAnswerDTO, Long loginId) {
//        Answer answer = answerRepository.findAnswer(adoptAnswerDTO.getAnswerId()).orElseThrow(
//                NotFoundAnswer::new);
//        UserEntity userEntity = userRepository.findById(loginId).orElseThrow(
//                NotFoundUser::new);
//        // 문제행동 글을쓴 유저인지 아닌지 체크
//        if (!answer.isPostWriteUser(userEntity.getId())) {
//            throw new LoginIdError();
//        }
//
//        AdoptAnswer adoptAnswer = AdoptAnswer.builder()
//                .answer(answer)
//                .trainer(answer.getTrainer())
//                .userEntity(userEntity)
//                .build();
//        adoptAnswerRepository.save(adoptAnswer);
//        // 답변채택수 증가
//        answer.getTrainer().increaseAdoptCount();
    }

    /**
     * 훈련사 답변 추천 조회
     *
     * @param loginId
     * @param answerId
     * @return
     */
    @Transactional(readOnly = true)
    public AnswerCheckResponse findCheck(Long loginId, Long answerId) {
        Optional<AnswerLike> answerLike = answerLikeRepository.findByAnswerId(answerId, loginId);
        return AnswerCheckResponse.from(answerLike);
    }

    /**
     * 훈련사 답변 추천
     *
     * @param answerId
     * @param loginId
     */
    @Transactional
    public void updateLike(Long loginId, Long answerId) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(NotFoundAnswer::new);
        Optional<AnswerLike> answerLike = answerLikeRepository.findByAnswerId(answerId, loginId);


        /**
         * 추천이 이미 있다면 추천을 삭제 추천수 -1
         * 추천이 없다면 추천을 저장 추천수 +1
         */
        if (answerLike.isPresent()) {
            answer.decreaseLikeCount();
            answerLikeRepository.delete(answerLike.get());
            answerRepository.save(answer);
        }
        else{
            AnswerLike newAnswerLike = AnswerLike.from(answerId, loginId);
            answer.increaseLikeCount();
            answerLikeRepository.save(newAnswerLike);
            answerRepository.save(answer);
        }
    }



}
