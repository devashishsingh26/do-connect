package com.example.wipro.Controller;

import com.example.wipro.Entity.Comment;
import com.example.wipro.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")

@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @PostMapping("/comment/{answerId}")
    public Comment postComment(@RequestBody Comment comment, @PathVariable("answerId") Long answerId) {
        return this.commentService.postComment(answerId,comment);
    }
}
