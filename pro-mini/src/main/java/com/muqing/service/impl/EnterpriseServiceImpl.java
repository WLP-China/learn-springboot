package com.muqing.service.impl;

import com.muqing.dao.EnterpriseDao;
import com.muqing.model.Enterprise;
import com.muqing.service.EnterpriseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by iFun on 2020/06/28
 */
@Service
public class EnterpriseServiceImpl implements EnterpriseService {
    private static final Logger log = LoggerFactory.getLogger(EnterpriseServiceImpl.class);

    @Autowired
    private EnterpriseDao enterpriseDao;

    @Override
    public void saveOrUpdate(Enterprise enterprise) {
        if (enterprise.getId() != null) {
            enterpriseDao.update(enterprise);
        } else {
            enterpriseDao.save(enterprise);
            log.debug("新增企业{}", enterprise.geteName());
        }
    }

    @Override
    public void delete(Long id) {
        enterpriseDao.delete(id);
        log.debug("删除企业id:{}", id);
    }
}
