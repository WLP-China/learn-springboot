package com.muqing.service.impl;

import com.github.pagehelper.PageHelper;
import com.muqing.mbg.mapper.PmsBrandMapper;
import com.muqing.mbg.model.PmsBrand;
import com.muqing.mbg.model.PmsBrandExample;
import com.muqing.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PmsBrandServiceImpl implements PmsBrandService {

    @Autowired
    private PmsBrandMapper brandMapper;

    @Override
    public List<PmsBrand> listAllBrand() {
        return brandMapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public int createBrand(PmsBrand brand) {
        return brandMapper.insertSelective(brand);
    }

    @Override
    public int updateBrand(Long id, PmsBrand brand) {
        brand.setId(id);
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public int deleteBrand(Long id) {
        return brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<PmsBrand> listBrand(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return brandMapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public PmsBrand getBrand(Long id) {
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
        PmsBrand pmsBrand = new PmsBrand();
        pmsBrand.setId((long) 101);
        pmsBrand.setName("101");

        brandMapper.updateByPrimaryKey(pmsBrand);

        int a = 1 / 0;

        pmsBrand.setId((long) 102);
        pmsBrand.setName("102");
        brandMapper.updateByPrimaryKey(pmsBrand);

    }
}
