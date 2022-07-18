package com.example.wipro.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answer;

    private String username;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_fk",referencedColumnName = "id")
    private List<Comment> commentList = new ArrayList<>();

    private boolean approved = false;

}
