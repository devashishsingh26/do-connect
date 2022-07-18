package com.example.wipro.Service;

import com.example.wipro.Entity.Answer;
import com.example.wipro.Entity.Question;
import com.example.wipro.Entity.Signup;
import com.example.wipro.Exception.AnswerExistsException;
import com.example.wipro.Exception.QuestionExistsException;
import com.example.wipro.Exception.UserNotFoundException;
import com.example.wipro.Repository.AnswerRepository;
import com.example.wipro.Repository.QuestionRepository;
import com.example.wipro.Repository.SignupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private SignupRepository signupRepository;

    public Answer postAnswer(Answer answer, Long questionId, String username) throws QuestionExistsException, UserNotFoundException {
        Optional<Question> byId = questionRepository.findById(questionId);
        Signup byUsername = signupRepository.getByUsername(username);
        if(byUsername == null)
            throw new UserNotFoundException();
        if(byId.isEmpty())
            throw new QuestionExistsException();
        Question question = byId.get();
        answer.setUsername(username);
        Answer answer1 = answerRepository.save(answer);
        question.getAnswerList().add(answer1);
        questionRepository.save(question);
        return answer1;
    }

    public Answer updateAnswer(Answer answer, Long answerId) throws AnswerExistsException {
        Optional<Answer> byId = answerRepository.findById(answerId);
        if(byId.isEmpty())
            throw new AnswerExistsException();
        Answer answer1 = byId.get();
        if(Objects.nonNull(answer.getAnswer()))
            answer1.setAnswer(answer.getAnswer());
        return answerRepository.save(answer1);

    }

    public String deleteAnswer(Long answerId) throws AnswerExistsException {
        Optional<Answer> byId = answerRepository.findById(answerId);
        if(byId.isEmpty())
            throw new AnswerExistsException();
        Answer answer1 = byId.get();
        answerRepository.deleteById(answerId);
        return "deleted successfully";
    }

    public Answer updateAnswerApproval(Long answerId) throws AnswerExistsException {
        Optional<Answer> byId = answerRepository.findById(answerId);
        if(byId.isEmpty())
            throw new AnswerExistsException();
        Answer answer1 = byId.get();
        answer1.setApproved(true);
        return answerRepository.save(answer1);
    }
}
