package com.ifun.controller;

import com.ifun.dto.QuestionDTO;
import com.ifun.exception.ServiceException;
import com.ifun.exception.enums.CoreExceptionEnum;
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
        Integer questionId;
        try {
            questionId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new ServiceException(CoreExceptionEnum.INVALID_INPUT);
        }
        QuestionDTO questionDTO = questionService.getById(questionId);
        //增加阅读数
        questionService.increaseView(questionId);
        model.addAttribute("question", questionDTO);
        return "question";
    }
}
