package com.example.quiz.repository;

import com.example.quiz.model.ShortAnswerQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortAnswerQuizRepository extends JpaRepository<ShortAnswerQuiz, Long> {
    // 기본적인 CRUD 작업은 JpaRepository에서 제공됨
}
