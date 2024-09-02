package com.example.quiz.controller;

import com.example.quiz.model.Quiz;
import com.example.quiz.service.QuizService;
import com.example.quiz.util.JsonUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private JsonUtility jsonUtility;

    @GetMapping("/quiz")
    public String quiz(Model model) {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        List<List<String>> optionsList = new ArrayList<>();

        for (Quiz quiz : quizzes) {
            try {
                List<String> options = jsonUtility.parse(quiz.getOptions());
                optionsList.add(options);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                optionsList.add(new ArrayList<>()); // 에러 발생 시 빈 리스트 추가
            }
        }

        model.addAttribute("quiz", quizzes);
        model.addAttribute("optionsList", optionsList);
        return "quiz";
    }

    @PostMapping("/submit")
    public String submit(@RequestParam Map<String, String> allAnswers, Model model) {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        int correctCount = 0;

        for (int i = 0; i < quizzes.size(); i++) {
            String userAnswer = allAnswers.get("answers[" + i + "]");
            String correctAnswer = quizzes.get(i).getCorrectAnswer();
            if (userAnswer != null && userAnswer.equals(correctAnswer)) {
                correctCount++;
            }
        }

        model.addAttribute("correctCount", correctCount);
        model.addAttribute("totalQuestions", quizzes.size());
        return "result";
    }

    // 새로운 퀴즈를 추가하는 폼을 보여줌
    @GetMapping("/quiz/new")
    public String showAddQuizForm(Model model) {
        model.addAttribute("quiz", new Quiz());
        return "quiz-form";
    }

    // 퀴즈 추가 요청 처리
    @PostMapping("/quiz/add")
    public String addQuiz(@ModelAttribute Quiz quiz, @RequestParam("option1") String option1,
                          @RequestParam("option2") String option2, @RequestParam("option3") String option3,
                          @RequestParam("option4") String option4) {
        List<String> options = List.of(option1, option2, option3, option4);
        try {
            String optionsJson = jsonUtility.toJson(options);
            quiz.setOptions(optionsJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "redirect:/quiz/new?error";
        }

        quizService.addQuiz(quiz);
        return "redirect:/quiz"; // 퀴즈 목록 페이지로 리디렉션
    }
}
