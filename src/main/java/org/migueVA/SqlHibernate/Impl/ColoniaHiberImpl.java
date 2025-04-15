package org.migueVA.SqlHibernate.Impl;

import org.hibernate.Session;
import org.migueVA.Hibernate.HibernateUtil;
import org.migueVA.Model.Colonia;
import org.migueVA.Model.Estado;
import org.migueVA.SqlHibernate.GenericoSQL;

import java.util.List;

public class ColoniaHiberImpl implements GenericoSQL<Colonia> {

    private static ColoniaHiberImpl coloniaHiber;

    private ColoniaHiberImpl() {
    }

    public static ColoniaHiberImpl getInstance(){
        if(coloniaHiber == null){
            coloniaHiber = new ColoniaHiberImpl();
        }
        return coloniaHiber;
    }

    @Override
    public List<Colonia> findAll() {
        Session session = HibernateUtil.getSession();
        if(session == null){
            System.out.println(" *** Error de Conexión *** ");
            return null;
        }
        List<Colonia> list = session.createQuery("FROM COLONIA", Colonia.class).getResultList();
        session.close();
        return list;
    }

    @Override
    public boolean save(Colonia colonia) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.persist(colonia);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean update(Colonia colonia) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.merge(colonia);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean delete(Colonia colonia) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.remove(colonia);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public Colonia findById(Integer id) {
        Session session = HibernateUtil.getSession();

        Colonia colonia = session
                .get( Colonia.class, id );

        session.close();
        return colonia;
    }
}
