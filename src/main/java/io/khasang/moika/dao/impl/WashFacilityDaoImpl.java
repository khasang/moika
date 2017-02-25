package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.WashFacilityDao;
import io.khasang.moika.entity.WashBox;
import io.khasang.moika.entity.WashFacility;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("washFacilityDao")
public class WashFacilityDaoImpl implements WashFacilityDao{

    private  SessionFactory sessionFactory;

    public WashFacilityDaoImpl() {
    }

    @Autowired
    public WashFacilityDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createWashFacility(WashFacility washFacility) {
        sessionFactory.getCurrentSession().save(washFacility);
    }

    @Override
    public void updateWashFacility(WashFacility washFacility) {
        sessionFactory.getCurrentSession().update(washFacility);
    }

    @Override
    public void deleteWashFacility(WashFacility washFacility) {
        sessionFactory.getCurrentSession().delete(washFacility);
    }

    @Override
    public WashFacility getWashFacility(int id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(WashFacility.class);
        criteria.add(Restrictions.eq("id_fclt", id));
        return (WashFacility) criteria.uniqueResult();
    }

    @Override
    public WashFacility getWashFacility(String name) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(WashFacility.class);
        criteria.add(Restrictions.eq("name", name));
        return (WashFacility) criteria.uniqueResult();
    }

    @Override
    public List<WashFacility> getWashFacilitiesOnNet(int idNet) {
        return null;
    }

    @Override
    public List<WashFacility> getAllWashFacilities() {
        return  sessionFactory.getCurrentSession().createQuery("from wash_facility wf").list();
    }

    @Override
    public List<WashBox> getWashBoxesOnFacility(WashFacility washFacility) {
        return null;
    }

    @Override
    public List<WashBox> getWashBoxesOnFacility(int idFacility) {
        return null;
    }
}
