package com.muqing.controller;

import com.muqing.common.api.CommonResult;
import com.muqing.dao.ArticleDao;
import com.muqing.dao.DepartmentDao;
import com.muqing.dao.DoctorDao;
import com.muqing.dao.NurseDao;
import com.muqing.dto.DepartmentDTO;
import com.muqing.dto.PaginationDTO;
import com.muqing.model.Article;
import com.muqing.model.Department;
import com.muqing.model.Doctor;
import com.muqing.model.Nurse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by iFun on 2020/07/16
 */
@RestController
@RequestMapping("/web")
public class WebSiteController {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private NurseDao nurseDao;

    /**
     * 文章列表
     *
     * @param type     文章分类
     * @param title    文章标题
     * @param pagenum  当前页
     * @param pagesize 步长
     * @return
     */
    @GetMapping("/article")
    public CommonResult<PaginationDTO<Article>> listArticle(@RequestParam(name = "type", required = false) String type,
                                                            @RequestParam(name = "title", required = false) String title,
                                                            @RequestParam(name = "pagenum", defaultValue = "1") Integer pagenum,
                                                            @RequestParam(name = "pagesize", defaultValue = "5") Integer pagesize) {
        //SELECT .. FROM `t_article` WHERE status=1 ORDER BY isTop DESC,publishTime DESC,createTime DESC
        Map<String, Object> params = new HashMap<>();
        params.put("type", type);
        params.put("title", title);
        params.put("status", "1");
        params.put("orderBy", " ORDER BY isTop DESC,publishTime DESC,createTime DESC ");

        List<Article> list = articleDao.list(params, pagesize * (pagenum - 1), pagesize);
        if (list == null) {
            return CommonResult.failed();
        }
        int totalCount = articleDao.count(params);
        Integer totalPage;
        if (totalCount % pagesize == 0) {
            totalPage = totalCount / pagesize;
        } else {
            totalPage = totalCount / pagesize + 1;
        }

        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setData(list);
        paginationDTO.setTotalCount(totalCount);
        paginationDTO.setTotalPage(totalPage);
        paginationDTO.setCurrentPage(pagenum);
        return CommonResult.success(paginationDTO);
    }

    /**
     * 文章详情
     *
     * @param id 文章ID
     * @return
     */
    @GetMapping("/article/{id}")
    public Article getArticle(@PathVariable Long id) {
        return articleDao.getById(id);
    }

    /**
     * 科室列表
     *
     * @param name     科室名称
     * @param pagenum  当前页
     * @param pagesize 步长
     * @return
     */
    @GetMapping("/department")
    public CommonResult<PaginationDTO<Department>> listDepartment(@RequestParam(name = "name", required = false) String name,
                                                                  @RequestParam(name = "pagenum", defaultValue = "1") Integer pagenum,
                                                                  @RequestParam(name = "pagesize", defaultValue = "100") Integer pagesize) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("orderBy", " ORDER BY isTop DESC,sort ASC");

        List<Department> list = departmentDao.list(params, pagesize * (pagenum - 1), pagesize);
        if (list == null) {
            return CommonResult.failed();
        }
        int totalCount = departmentDao.count(params);
        Integer totalPage;
        if (totalCount % pagesize == 0) {
            totalPage = totalCount / pagesize;
        } else {
            totalPage = totalCount / pagesize + 1;
        }

        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setData(list);
        paginationDTO.setTotalCount(totalCount);
        paginationDTO.setTotalPage(totalPage);
        paginationDTO.setCurrentPage(pagenum);
        return CommonResult.success(paginationDTO);
    }

    /**
     * 科室详情
     *
     * @param id 科室ID
     * @return
     */
    @GetMapping("/department/{id}")
    public DepartmentDTO getDepartment(@PathVariable Long id) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        Department department = departmentDao.getById(id);
        List<Doctor> doctors = doctorDao.listByDeparimentId(id);

        BeanUtils.copyProperties(department, departmentDTO);
        departmentDTO.setDoctors(doctors);

        return departmentDTO;
    }

    /**
     * 医生专家列表
     *
     * @param name     医生姓名
     * @param pagenum  当前页
     * @param pagesize 步长
     * @return
     */
    @GetMapping("/doctor")
    public CommonResult<PaginationDTO<Doctor>> listDoctor(@RequestParam(name = "name", required = false) String name,
                                                          @RequestParam(name = "pagenum", defaultValue = "1") Integer pagenum,
                                                          @RequestParam(name = "pagesize", defaultValue = "100") Integer pagesize) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("orderBy", " ORDER BY sort ASC");

        List<Doctor> list = doctorDao.list(params, pagesize * (pagenum - 1), pagesize);
        if (list == null) {
            return CommonResult.failed();
        }
        int totalCount = doctorDao.count(params);
        Integer totalPage;
        if (totalCount % pagesize == 0) {
            totalPage = totalCount / pagesize;
        } else {
            totalPage = totalCount / pagesize + 1;
        }

        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setData(list);
        paginationDTO.setTotalCount(totalCount);
        paginationDTO.setTotalPage(totalPage);
        paginationDTO.setCurrentPage(pagenum);
        return CommonResult.success(paginationDTO);
    }

    /**
     * 医生详情
     *
     * @param id 医生ID
     * @return
     */
    @GetMapping("/doctor/{id}")
    public Doctor getDoctor(@PathVariable Long id) {
        return doctorDao.getById(id);
    }

    /**
     * 护士列表
     *
     * @param name     姓名
     * @param pagenum  当前页
     * @param pagesize 步长
     * @return
     */
    @GetMapping("/nurse")
    public CommonResult<PaginationDTO<Nurse>> listNurse(@RequestParam(name = "name", required = false) String name,
                                                        @RequestParam(name = "pagenum", defaultValue = "1") Integer pagenum,
                                                        @RequestParam(name = "pagesize", defaultValue = "100") Integer pagesize) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("orderBy", " ORDER BY sort ASC");

        List<Nurse> list = nurseDao.list(params, pagesize * (pagenum - 1), pagesize);
        if (list == null) {
            return CommonResult.failed();
        }
        int totalCount = nurseDao.count(params);
        Integer totalPage;
        if (totalCount % pagesize == 0) {
            totalPage = totalCount / pagesize;
        } else {
            totalPage = totalCount / pagesize + 1;
        }

        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setData(list);
        paginationDTO.setTotalCount(totalCount);
        paginationDTO.setTotalPage(totalPage);
        paginationDTO.setCurrentPage(pagenum);
        return CommonResult.success(paginationDTO);
    }

    /**
     * 护士详情
     *
     * @param id 护士ID
     * @return
     */
    @GetMapping("/nurse/{id}")
    public Nurse getNurse(@PathVariable Long id) {
        return nurseDao.getById(id);
    }
}