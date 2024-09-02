package com.example.quiz.controller;

import com.example.quiz.model.ImageQuiz;
import com.example.quiz.service.ImageQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/image-quiz")
public class ImageQuizController {

    @Autowired
    private ImageQuizService imageQuizService;

    private List<ImageQuiz> quizzes;
    private int currentQuestionIndex = 0;
    private int correctAnswersCount = 0;

    @GetMapping
    public String startQuiz(Model model) {
        quizzes = imageQuizService.getAllImageQuizzes();
        currentQuestionIndex = 0;
        correctAnswersCount = 0;  // 퀴즈 시작 시 정답 수 초기화
        model.addAttribute("quiz", quizzes.get(currentQuestionIndex));
        return "image-quiz";
    }

    @PostMapping("/submit")
    public String submitAnswer(@RequestParam String answer, Model model) {
        ImageQuiz currentQuiz = quizzes.get(currentQuestionIndex);
        boolean isCorrect = answer.equalsIgnoreCase(currentQuiz.getCorrectAnswer());

        if (isCorrect) {
            correctAnswersCount++;
        }

        currentQuestionIndex++;

        if (currentQuestionIndex >= quizzes.size()) {
            // 퀴즈 완료 후 결과 페이지로 리다이렉트
            model.addAttribute("correctCount", correctAnswersCount);
            model.addAttribute("totalQuestions", quizzes.size());
            quizzes = null; // Clear quizzes to reset for next session
            currentQuestionIndex = 0; // Reset index
            correctAnswersCount = 0;  // Reset correct answers count
            return "image-quiz-result";
        } else {
            // 다음 문제로 진행
            model.addAttribute("quiz", quizzes.get(currentQuestionIndex));
            model.addAttribute("isCorrect", isCorrect);
            return "image-quiz";
        }
    }

    // 새 이미지 퀴즈 추가 폼을 보여주는 메서드
    @GetMapping("/new")
    public String showAddQuizForm(Model model) {
        model.addAttribute("imageQuiz", new ImageQuiz());
        return "image-quiz-form";
    }

    // 새 이미지 퀴즈 추가를 처리하는 메서드
    @PostMapping("/add")
    public String addQuiz(@ModelAttribute ImageQuiz imageQuiz) {
        imageQuizService.addImageQuiz(imageQuiz);
        return "redirect:/image-quiz"; // 퀴즈 목록 페이지로 리디렉션
    }
}
