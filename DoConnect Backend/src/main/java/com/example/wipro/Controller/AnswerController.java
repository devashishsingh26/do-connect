package com.example.wipro.Controller;

import com.example.wipro.Entity.Answer;
import com.example.wipro.Exception.AnswerExistsException;
import com.example.wipro.Exception.QuestionExistsException;
import com.example.wipro.Exception.UserNotFoundException;
import com.example.wipro.Service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping("answer/{questionId}/{username}")
    public Answer postAnswer(@RequestBody Answer answer, @PathVariable("questionId") Long questionId,
                             @PathVariable("username") String username) throws QuestionExistsException, UserNotFoundException {
        return this.answerService.postAnswer(answer,questionId,username);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("answer/{answerId}")
    public Answer updateAnswer(@RequestBody Answer answer,@PathVariable("answerId") Long answerId) throws AnswerExistsException {
        return this.answerService.updateAnswer(answer,answerId);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("answer/{answerId}")
    public String deleteAnswer(@PathVariable("answerId") Long answerId) throws AnswerExistsException {
        return this.answerService.deleteAnswer(answerId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("answer-approve/{answerId}")
    public Answer updateAnswerApproval(@PathVariable("answerId") Long answerId) throws AnswerExistsException {
        return this.answerService.updateAnswerApproval(answerId);
    }


}
