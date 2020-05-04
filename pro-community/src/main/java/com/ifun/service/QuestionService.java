package com.ifun.service;

import com.ifun.common.api.ResultCode;
import com.ifun.dao.QuestionDao;
import com.ifun.dto.PaginationDTO;
import com.ifun.dto.QuestionDTO;
import com.ifun.common.exception.ServiceException;
import com.ifun.enums.exception.QuestionExceptionEnum;
import com.ifun.mbg.mapper.QuestionMapper;
import com.ifun.mbg.mapper.UserMapper;
import com.ifun.mbg.model.Question;
import com.ifun.mbg.model.QuestionExample;
import com.ifun.mbg.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired(required = false)
    private QuestionMapper questionMapper;
    @Autowired(required = false)
    private QuestionDao questionDao;

    public PaginationDTO list(Integer page, Integer size) {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(new QuestionExample(), new RowBounds(size * (page - 1), size));
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        Integer totalCount = questionMapper.countByExample(new QuestionExample());

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);
        paginationDTO.setData(questionDTOList);

        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(example, new RowBounds(size * (page - 1), size));
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount = questionMapper.countByExample(questionExample);

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);
        paginationDTO.setData(questionDTOList);

        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question==null) {
            throw new ServiceException(QuestionExceptionEnum.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdaye(Question question) {
        if (question.getId() == null) {
            //插入
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            int insert = questionMapper.insert(question);
            if (insert!=1) {
                throw new ServiceException(QuestionExceptionEnum.QUESTION_SAVE_FAIL);
            }
        } else {
            //更新
            Question dbQuestion = questionMapper.selectByPrimaryKey(question.getId());
            if (dbQuestion == null) {
                //数据库无此条信息
                throw new ServiceException(QuestionExceptionEnum.QUESTION_NOT_FOUND);
            }
            if (!dbQuestion.getCreator().equals(question.getCreator())) {
                //与数据库中创建者不一致
                throw new ServiceException(ResultCode.FORBIDDEN);
            }
            Question updateQuestion = new Question();
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            updateQuestion.setGmtModified(System.currentTimeMillis());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(dbQuestion.getId());
            int update = questionMapper.updateByExampleSelective(updateQuestion, example);
            if (update!=1) {
                throw new ServiceException(QuestionExceptionEnum.QUESTION_NOT_FOUND);
            }
        }
    }

    public void increaseView(Integer questionId) {
        questionDao.increaseViewCount(questionId);
    }
}
