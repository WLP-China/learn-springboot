package com.muqing.service;

import com.muqing.mbg.model.Brand;

import java.util.List;

/**
 * 品牌
 */
public interface BrandService {

    List<Brand> listAllBrand();

    int createBrand(Brand brand);

    int updateBrand(Long id, Brand brand);

    int deleteBrand(Long id);

    /**
     * @param pageNum  开始页数
     * @param pageSize 每页显示条数
     * @return
     */
    List<Brand> listBrand(int pageNum, int pageSize);

    Brand getBrand(Long id);

    public void testTransactional();
}
