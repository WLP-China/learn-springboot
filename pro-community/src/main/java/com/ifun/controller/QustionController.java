package com.ifun.controller;

import com.ifun.dto.QuestionDTO;
import com.ifun.exception.CustomizeErrorCode;
import com.ifun.exception.CustomizeException;
import com.ifun.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QustionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") String id,
                           Model model) {
        Integer questionId = null;
        try {
            questionId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new CustomizeException(CustomizeErrorCode.INVALID_INPUT);
        }
        QuestionDTO questionDTO = questionService.getById(questionId);
        model.addAttribute("question", questionDTO);

        return "question";
    }
}
