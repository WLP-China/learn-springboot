package com.muqing.service.impl;

import com.github.pagehelper.PageHelper;
import com.muqing.mbg.mapper.BrandMapper;
import com.muqing.mbg.model.Brand;
import com.muqing.mbg.model.BrandExample;
import com.muqing.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> listAllBrand() {
        return brandMapper.selectByExample(new BrandExample());
    }

    @Override
    public int createBrand(Brand brand) {
        return brandMapper.insertSelective(brand);
    }

    @Override
    public int updateBrand(Long id, Brand brand) {
        brand.setId(id);
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public int deleteBrand(Long id) {
        return brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Brand> listBrand(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return brandMapper.selectByExample(new BrandExample());
    }

    @Override
    public Brand getBrand(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 声明式事务
     * <p>
     * 在springboot中已经默认对jpa、jdbc、mybatis开启了事事务，引入它们依赖的时候，事物就默认开启
     */
    @Override
    @Transactional
    public void testTransactional() {
        Brand pmsBrand = new Brand();
        pmsBrand.setId((long) 101);
        pmsBrand.setName("101");

        brandMapper.updateByPrimaryKey(pmsBrand);

        int a = 1 / 0;

        pmsBrand.setId((long) 102);
        pmsBrand.setName("102");
        brandMapper.updateByPrimaryKey(pmsBrand);

    }
}
