package com.muqing.service;

import com.muqing.mbg.model.PmsBrand;

import java.util.List;

/**
 * 品牌
 */
public interface PmsBrandService {

    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(Long id);

    /**
     *
     * @param pageNum 开始页数
     * @param pageSize 每页显示条数
     * @return
     */
    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}
