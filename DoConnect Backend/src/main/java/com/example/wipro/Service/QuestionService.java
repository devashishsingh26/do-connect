package com.example.wipro.Service;

import com.example.wipro.Entity.Answer;
import com.example.wipro.Entity.Question;
import com.example.wipro.Entity.Signup;
import com.example.wipro.Exception.QuestionExistsException;
import com.example.wipro.Exception.UserNotFoundException;
import com.example.wipro.Repository.AnswerRepository;
import com.example.wipro.Repository.QuestionRepository;
import com.example.wipro.Repository.SignupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private SignupRepository signupRepository;
    public Question askQuestion(Question question,Long userId) throws UserNotFoundException {
        Optional<Signup> byId = signupRepository.findById(userId);
        if(byId.isEmpty())
            throw new UserNotFoundException();
        Signup user = byId.get();
        question.setUsername(user.getUsername());
        Question question1 = questionRepository.save(question);
        user.getQuestionList().add(question1);
        signupRepository.save(user);

        return question1;
    }

    public Question updateQuestion(Question question, Long questionId) throws QuestionExistsException {
        Optional<Question> byId = questionRepository.findById(questionId);
        if(byId.isEmpty())
            throw new QuestionExistsException();
        Question question1 = byId.get();
        if(Objects.nonNull(question.getQuestion()))
            question1.setQuestion(question.getQuestion());

        return questionRepository.save(question1);

    }

    public String deleteQuestion(Long questionId) throws QuestionExistsException {
        Optional<Question> byId = questionRepository.findById(questionId);
        if(byId.isEmpty())
            throw new QuestionExistsException();
        Question question1 = byId.get();
        question1.getAnswerList()
        .forEach(answer -> answerRepository.deleteById(answer.getId()));
        questionRepository.deleteById(questionId);

        return "deleted successfully";
    }

    public Question updateQuestionApproval(Long questionId) throws QuestionExistsException {
        Optional<Question> byId = questionRepository.findById(questionId);
        if(byId.isEmpty())
            throw new QuestionExistsException();
        Question question1 = byId.get();
        question1.setApproved(true);

        return questionRepository.save(question1);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
}
