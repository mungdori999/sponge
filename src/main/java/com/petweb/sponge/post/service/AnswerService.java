package com.petweb.sponge.post.service;

import com.petweb.sponge.exception.error.*;
import com.petweb.sponge.post.controller.response.AnswerResponse;
import com.petweb.sponge.post.domain.answer.AdoptAnswer;
import com.petweb.sponge.post.domain.answer.Answer;
import com.petweb.sponge.post.domain.post.Post;
import com.petweb.sponge.post.repository.answer.AnswerEntity;
import com.petweb.sponge.post.dto.answer.AdoptAnswerDTO;
import com.petweb.sponge.post.dto.answer.AnswerCreate;
import com.petweb.sponge.post.dto.answer.AnswerDetailDTO;
import com.petweb.sponge.post.dto.answer.AnswerUpdateDTO;
import com.petweb.sponge.post.repository.answer.AdoptAnswerRepository;
import com.petweb.sponge.post.repository.answer.AnswerRecommendRepository;
import com.petweb.sponge.post.repository.answer.AnswerRepository;
import com.petweb.sponge.post.repository.post.PostRepository;
import com.petweb.sponge.trainer.domain.Trainer;
import com.petweb.sponge.trainer.repository.TrainerRepository;
import com.petweb.sponge.user.service.port.UserRepository;
import com.petweb.sponge.utils.AuthorizationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
     * @param problemPostId
     * @return
     */
    @Transactional(readOnly = true)
    public List<AnswerDetailDTO> findAnswerList(Long problemPostId) {
//        List<Answer> answerList = answerRepository.findAllAnswerWithTrainer(problemPostId);
//        return toDetailDto(answerList);
        return  null;
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
     * @param answerId
     * @param loginId
     */
    @Transactional
    public void deleteAnswer(Long answerId, Long loginId) {
//        Optional<AdoptAnswer> adoptAnswer = adoptAnswerRepository.findAdoptAnswer(answerId, loginId);
//        AnswerEntity answerEntity = answerRepository.findAnswer(answerId).orElseThrow(
//                NotFoundAnswer::new);
//        /**
//         * T: 객체 (answer)에게 메시지를 전달하는 방식을 사용해보는건 어떨까요?
//         * get 메서드가 체이닝되어 있어 가독성이 떨어지고 응집도가 낮아보입니다.
//         * 디미터의 법칙: https://mangkyu.tistory.com/147
//         */
//        if (!answerEntity.isWriteTrainer(loginId)) {
//            throw new NotFoundTrainer();
//        }
//        /**
//         * 답변과 관련하여 채택이 있다면 채택과 같이 답변삭제, 채택수 -1
//         * 답변과 관련하여 채택이 없다면 채택만 삭제, 채택수는 그대로
//         */
//        if (adoptAnswer.isPresent()) {
////            TrainerEntity trainerEntity = adoptAnswer.get().getTrainerEntity();
////            trainerEntity.decreaseAdoptCount();
////            answer.getPostEntity().decreaseAnswerCount();
//        }
//        answerRepository.deleteAnswer(answerId, loginId);
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
