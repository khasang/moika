package io.khasang.moika.service.impl;

import io.khasang.moika.dao.ServiceGroupDao;
import io.khasang.moika.dao.ServiceTypeDao;
import io.khasang.moika.service.MoikaServiceGroupService;
import io.khasang.moika.service.MoikaServiceTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "moikaServiceGroupServiceImpl")
@Transactional
public class MoikaServiceGroupServiceImpl extends  ATypeDataAccessServiceImpl implements MoikaServiceGroupService {
    final ServiceGroupDao serviceGroupDao;

    @Autowired()
    public MoikaServiceGroupServiceImpl(ServiceGroupDao serviceGroupDao) {
        this.serviceGroupDao = serviceGroupDao;
        setTypeDao( this.serviceGroupDao);
    }


}