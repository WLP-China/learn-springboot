package com.muqing.service;

import com.muqing.model.ConcreteType;

/**
 * Create by iFun on 2020/06/29
 */
public interface ConcreteTypeService {

    void saveOrUpdate(ConcreteType concreteType);

    void delete(Long id);
}
