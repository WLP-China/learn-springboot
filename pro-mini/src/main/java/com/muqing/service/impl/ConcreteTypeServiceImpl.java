package com.muqing.service.impl;

import com.muqing.dao.ConcreteTypeDao;
import com.muqing.model.ConcreteType;
import com.muqing.service.ConcreteTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by iFun on 2020/06/29
 */
@Service
public class ConcreteTypeServiceImpl implements ConcreteTypeService {

    @Autowired
    private ConcreteTypeDao concreteTypeDao;

    @Override
    public void saveOrUpdate(ConcreteType concreteType) {
        if (concreteType.getId() != null) {
            concreteTypeDao.update(concreteType);
        } else {
//            ConcreteType type = concreteTypeDao.getByTypeAndKey(concreteType.getType(), concreteType.getK());
//            if (type != null) {
//                throw new IllegalArgumentException("类型和key已存在");
//            }
            concreteTypeDao.save(concreteType);
        }
    }

    @Override
    public void delete(Long id) {
        concreteTypeDao.delete(id);
    }
}
