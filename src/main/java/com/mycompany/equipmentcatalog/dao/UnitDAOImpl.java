
package com.mycompany.equipmentcatalog.dao;

import com.mycompany.equipmentcatalog.model.Unit;
import com.mycompany.equipmentcatalog.utils.HibernateSessionFactoryUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class UnitDAOImpl implements UnitDAO{
    @Override
    public Unit findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Unit.class, id);
    }
    
    @Override
   // public void save(Unit unit) {
    public void add(Unit unit) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.save(unit);
            tx1.commit();
        }
    }
    @Override
    public void update(Unit unit) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.update(unit);
            tx1.commit();
        }
    }
    @Override
    public void delete(Unit unit) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.delete(unit);
            tx1.commit();
        }
    }
    @Override
    public List<Unit> findAll() {
        List<Unit> units = (List<Unit>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Unit").list();
        return units;
    }
    
    @Override
    public Long countUnits() {
        return (Long) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("SELECT count(*) From Unit").getSingleResult();
    }
}