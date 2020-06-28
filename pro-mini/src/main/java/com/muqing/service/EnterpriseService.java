package com.muqing.service;

import com.muqing.model.Enterprise;

/**
 * Create by iFun on 2020/06/28
 */
public interface EnterpriseService {
    void saveOrUpdate(Enterprise enterprise);

    void delete(Long id);
}
