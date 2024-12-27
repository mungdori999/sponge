package com.petweb.sponge.post.service;

import com.petweb.sponge.exception.error.*;
import com.petweb.sponge.post.controller.response.answer.AnswerListResponse;
import com.petweb.sponge.post.controller.response.answer.AnswerResponse;
import com.petweb.sponge.post.controller.response.answer.TrainerShortResponse;
import com.petweb.sponge.post.domain.answer.AdoptAnswer;
import com.petweb.sponge.post.domain.answer.Answer;
import com.petweb.sponge.post.domain.post.Post;
import com.petweb.sponge.post.dto.answer.AdoptAnswerDTO;
import com.petweb.sponge.post.dto.answer.AnswerCreate;
import com.petweb.sponge.post.dto.answer.AnswerUpdateDTO;
import com.petweb.sponge.post.repository.answer.AdoptAnswerRepository;
import com.petweb.sponge.post.repository.answer.AnswerRepository;
import com.petweb.sponge.post.repository.post.PostRepository;
import com.petweb.sponge.trainer.domain.Trainer;
import com.petweb.sponge.trainer.repository.TrainerRepository;
import com.petweb.sponge.user.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
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

    /**
     * 훈련사 답변 조회
     *
     * @param postId
     * @return
     */
    @Transactional(readOnly = true)
    public List<AnswerListResponse> findAnswerList(Long postId) {
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
                    return AnswerListResponse.from(AnswerResponse.from(answer), TrainerShortResponse.from(trainer), adoptCheck);
                })
                .collect(Collectors.toList());

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
     * @param answerId
     * @param answerUpdateDTO
     * @param loginId
     */
    @Transactional
    public void updateAnswer(Long answerId, AnswerUpdateDTO answerUpdateDTO, Long loginId) {
//        AnswerEntity answerEntity = answerRepository.findById(answerId).orElseThrow(
//                NotFoundAnswer::new);
//        if (!loginId.equals(answerEntity.getTrainerEntity().getId())) {
//            throw new LoginIdError();
//        }
//        answerEntity.setContent(answerUpdateDTO.getContent());
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
     * 훈련사 답변 추천
     *
     * @param answerId
     * @param loginId
     */
    @Transactional
    public void updateLikeCount(Long answerId, Long loginId) {
//        Optional<AnswerRecommend> recommend = answerRecommendRepository.findRecommend(answerId, loginId);
//        Answer answer = answerRepository.findAnswer(answerId).orElseThrow(
//                NotFoundAnswer::new);
//        UserEntity userEntity = userRepository.findById(loginId).orElseThrow(
//                NotFoundUser::new);
//
//        /**
//         * 추천이 이미 있다면 추천을 삭제 추천수 -1
//         * 추천이 없다면 추천을 저장 추천수 +1
//         */
//        if (recommend.isPresent()) {
//            answer.decreaseLikeCount();
//            answerRecommendRepository.delete(recommend.get());
//        } else {
//            AnswerRecommend answerRecommend = AnswerRecommend.builder()
//                    .answer(answer)
//                    .userEntity(userEntity)
//                    .build();
//            answer.increaseLikeCount();
//            answerRecommendRepository.save(answerRecommend);
//        }
    }


}
