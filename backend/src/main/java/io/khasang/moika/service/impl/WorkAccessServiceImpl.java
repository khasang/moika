package io.khasang.moika.service.impl;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.WorkDao;
import io.khasang.moika.entity.Work;
import io.khasang.moika.service.WorkAccessService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component("WorkAccessServiceImpl")
@Transactional
public class WorkAccessServiceImpl implements WorkAccessService {
    @Qualifier("workDaoImpl")
    @Autowired
    WorkDao workDao;

    public WorkAccessServiceImpl() {
    }

    @Override
    public Work create(Work entity) throws MoikaDaoException {
        return workDao.create(entity);
    }

    @Override
    public Work get(long id) throws MoikaDaoException {
        return workDao.get(id);
    }

    @Override
    public Work get(int id) throws MoikaDaoException {
        return workDao.get(id);
    }

    @Override
    public Work update(Work entity) throws MoikaDaoException {
        return workDao.update(entity);
    }

    @Override
    public Work update(long id, Map<String, Object> fieldValueMap) throws MoikaDaoException {
        return workDao.update(id, fieldValueMap);
    }

    @Override
    public Work delete(Work entity) throws MoikaDaoException {
        return workDao.delete(entity);
    }

    @Override
    public List<Work> getAll() throws MoikaDaoException {
        return workDao.getAll();
    }

    @Override
    public Session getCurrentSession() {
        return workDao.getCurrentSession();
    }

    @Override
    public List<Work> getWorksByIdOrder(long idOrder) {
        return workDao.getWorksByIdOrder(idOrder);
    }

    @Override
    public Work getWorkInBox(long idWashBox) {
        return workDao.getWorkInBox(idWashBox);
    }

    @Override
    public Work getWorkInBox(long idWashBox, Date workDateAndTime) {
        return workDao.getWorkInBox( idWashBox,  workDateAndTime);
    }

    @Override
    public List<Work> getWorksInFacility(int idFclt) {
        return workDao.getWorksInFacility( idFclt);
    }

    @Override
    public List<Work> getWorksInFacility(int idFclt, Date workDateAndTime) {
        return workDao.getWorksInFacility( idFclt,  workDateAndTime);
    }
}
