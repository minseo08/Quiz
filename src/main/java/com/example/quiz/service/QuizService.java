package com.example.quiz.service;

import com.example.quiz.model.Quiz;
import com.example.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    // 새로운 퀴즈 추가
    public Quiz addQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    // 모든 퀴즈 가져오기
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    // ID로 특정 퀴즈 가져오기
    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id);
    }

    // 퀴즈 삭제
    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }
}
