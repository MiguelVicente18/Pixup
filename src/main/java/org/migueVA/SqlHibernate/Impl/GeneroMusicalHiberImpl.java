package org.migueVA.SqlHibernate.Impl;

import org.hibernate.Session;
import org.migueVA.Hibernate.HibernateUtil;
import org.migueVA.Model.GeneroMusical;
import org.migueVA.SqlHibernate.GenericoSQL;

import java.util.List;

public class GeneroMusicalHiberImpl implements GenericoSQL<GeneroMusical> {

    private static GeneroMusicalHiberImpl generoMusicalHiber;

    private GeneroMusicalHiberImpl()
    {
    }

    public static GeneroMusicalHiberImpl getInstance()
    {
        if(generoMusicalHiber==null)
        {
            generoMusicalHiber = new GeneroMusicalHiberImpl();
        }
        return generoMusicalHiber;
    }

    @Override
    public List<GeneroMusical> findAll()
    {
        Session session = HibernateUtil.getSession();
        List<GeneroMusical> list = session
                .createQuery("FROM Genero_Musical", GeneroMusical.class)
                .getResultList();

        session.close();
        return list;
    }

    @Override
    public boolean save(GeneroMusical generoMusical)
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.persist(generoMusical);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean update(GeneroMusical generoMusical)
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.merge(generoMusical);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean delete(GeneroMusical generoMusical)
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.remove(generoMusical);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public GeneroMusical findById(Integer id)
    {
        Session session = HibernateUtil.getSession();
        GeneroMusical generoMusical = session
                .get( GeneroMusical.class, id );

        session.close();
        return generoMusical;
    }
}
