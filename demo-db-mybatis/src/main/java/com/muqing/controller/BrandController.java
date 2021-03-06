package com.muqing.controller;

import com.muqing.common.api.CommonPage;
import com.muqing.common.api.CommonResult;
import com.muqing.mbg.model.Brand;
import com.muqing.service.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌管理Controller
 */
@Controller
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    private static final Logger LOGGER = LoggerFactory.getLogger(BrandController.class);

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Brand>> getBrandList() {
        return CommonResult.success(brandService.listAllBrand());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createBrand(@RequestBody Brand brand) {
        CommonResult commonResult;
        int count = brandService.createBrand(brand);
        if (count == 1) {
            commonResult = CommonResult.success(brand);
            LOGGER.debug("createBrand success:{}", brand);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createBrand failed:{}", brand);
        }
        return commonResult;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody Brand brandDTO, BindingResult result) {
        CommonResult commonResult;
        int count = brandService.updateBrand(id, brandDTO);
        if (count == 1) {
            commonResult = CommonResult.success(brandDTO);
            LOGGER.debug("updateBrand success:{}", brandDTO);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateBrand failed:{}", brandDTO);
        }
        return commonResult;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        int count = brandService.deleteBrand(id);
        if (count == 1) {
            LOGGER.debug("deleteBrand success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("deleteBrand failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<Brand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        List<Brand> brandList = brandService.listBrand(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Brand> brand(@PathVariable("id") Long id) {
        return CommonResult.success(brandService.getBrand(id));
    }

    //测试事务
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public void testTransactional() {
        brandService.testTransactional();
    }
}
