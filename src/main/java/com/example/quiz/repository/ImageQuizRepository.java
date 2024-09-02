package com.example.quiz.repository;

import com.example.quiz.model.ImageQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageQuizRepository extends JpaRepository<ImageQuiz, Long> {
}
