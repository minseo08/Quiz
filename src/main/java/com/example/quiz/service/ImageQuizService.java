package com.example.quiz.service;

import com.example.quiz.model.ImageQuiz;
import com.example.quiz.repository.ImageQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageQuizService {

    @Autowired
    private ImageQuizRepository imageQuizRepository;

    public List<ImageQuiz> getAllImageQuizzes() {
        return imageQuizRepository.findAll();
    }

    public ImageQuiz getImageQuizById(Long id) {
        return imageQuizRepository.findById(id).orElse(null);
    }

    public void addImageQuiz(ImageQuiz imageQuiz) {
        imageQuizRepository.save(imageQuiz);
    }
}
