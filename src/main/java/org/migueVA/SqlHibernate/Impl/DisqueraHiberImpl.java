package org.migueVA.SqlHibernate.Impl;

import org.hibernate.Session;
import org.migueVA.Hibernate.HibernateUtil;
import org.migueVA.Model.Disquera;
import org.migueVA.SqlHibernate.GenericoSQL;

import java.util.List;

public class DisqueraHiberImpl implements GenericoSQL<Disquera> {

    private static DisqueraHiberImpl disqueraHiber;

    private DisqueraHiberImpl()
    {
    }

    public static DisqueraHiberImpl getInstance()
    {
        if(disqueraHiber==null)
        {
            disqueraHiber = new DisqueraHiberImpl();
        }
        return disqueraHiber;
    }

    @Override
    public List<Disquera> findAll()
    {
        Session session = HibernateUtil.getSession();
        List<Disquera> list = session
                .createQuery("FROM Disquera", Disquera.class)
                .getResultList();

        session.close();
        return list;
    }

    @Override
    public boolean save(Disquera disquera)
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.persist(disquera);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean update(Disquera disquera)
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.merge(disquera);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean delete(Disquera disquera)
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.remove(disquera);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public Disquera findById(Integer id)
    {
        Session session = HibernateUtil.getSession();
        Disquera disquera = session
                .get( Disquera.class, id );

        session.close();
        return disquera;
    }
}
