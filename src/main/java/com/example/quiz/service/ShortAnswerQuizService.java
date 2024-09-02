package com.example.quiz.service;

import com.example.quiz.model.ShortAnswerQuiz;
import com.example.quiz.repository.ShortAnswerQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShortAnswerQuizService {

    @Autowired
    private ShortAnswerQuizRepository repository;

    public ShortAnswerQuiz addQuiz(ShortAnswerQuiz quiz) {
        return repository.save(quiz);
    }

    public List<ShortAnswerQuiz> getAllQuizzes() {
        return repository.findAll();
    }
}
