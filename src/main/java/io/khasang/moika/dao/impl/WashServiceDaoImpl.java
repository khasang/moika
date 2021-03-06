package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.WashServiceDao;
import io.khasang.moika.entity.WashService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("washServiceDao")
public class WashServiceDaoImpl extends AllServiceFieldsDaoImpl<WashService> implements WashServiceDao {

    public WashServiceDaoImpl() {
    }


}
