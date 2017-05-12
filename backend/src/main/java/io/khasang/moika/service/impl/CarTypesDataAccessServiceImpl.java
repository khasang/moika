package io.khasang.moika.service.impl;

import io.khasang.moika.dao.CarTypeDao;
import io.khasang.moika.service.CarTypesDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "carTypesDataAccessService")
@Transactional
public class CarTypesDataAccessServiceImpl extends  ATypeDataAccessServiceImpl implements CarTypesDataAccessService {
    final CarTypeDao carTypeDao;


    @Autowired()
    public CarTypesDataAccessServiceImpl(CarTypeDao carTypeDao) {
        this.carTypeDao = carTypeDao;
        setTypeDao(this.carTypeDao);
    }


}
