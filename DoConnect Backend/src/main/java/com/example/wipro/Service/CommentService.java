package com.example.wipro.Service;

import com.example.wipro.Entity.Answer;
import com.example.wipro.Entity.Comment;
import com.example.wipro.Repository.AnswerRepository;
import com.example.wipro.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private CommentRepository commentRepository;

    public Comment postComment(Long answerId, Comment comment) {
        Optional<Answer> byId = answerRepository.findById(answerId);
        Answer answer = byId.get();
        Comment comment1 = commentRepository.save(comment);
        answer.getCommentList().add(comment1);
        answerRepository.save(answer);
        return comment1;

    }
}
