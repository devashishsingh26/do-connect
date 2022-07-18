package com.example.wipro.Controller;

import com.example.wipro.Entity.Question;
import com.example.wipro.Exception.QuestionExistsException;
import com.example.wipro.Exception.UserNotFoundException;
import com.example.wipro.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/questions/{userId}")
    public Question askQuestion(@RequestBody Question question, @PathVariable("userId") Long userId) throws UserNotFoundException {
        return this.questionService.askQuestion(question,userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/questions/{questionId}")
    public Question updateQuestion(@RequestBody Question question,@PathVariable("questionId") Long questionId ) throws QuestionExistsException {
        return this.questionService.updateQuestion(question,questionId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/questions/{questionId}")
    public String deleteQuestion(@PathVariable("questionId") Long questionId) throws QuestionExistsException {
        return this.questionService.deleteQuestion(questionId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/questions-approve/{questionId}")
    public Question updateQuestionApproval(@PathVariable("questionId") Long questionId ) throws QuestionExistsException {
        return this.questionService.updateQuestionApproval(questionId);
    }

    @GetMapping("questions")
    public List<Question> getAllQuestions() {
        return this.questionService.getAllQuestions();
    }




}
