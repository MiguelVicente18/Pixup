package org.migueVA.SqlHibernate.Impl;

import org.hibernate.Session;
import org.migueVA.Hibernate.HibernateUtil;
import org.migueVA.Model.Estado;
import org.migueVA.Model.Municipio;
import org.migueVA.SqlHibernate.GenericoSQL;

import java.util.List;

public class MunicipioHiberImpl implements GenericoSQL<Municipio> {
    private static MunicipioHiberImpl municipioHiber;

    private MunicipioHiberImpl() {
    }

    public static MunicipioHiberImpl getInstance(){
        if(municipioHiber == null){
            municipioHiber = new MunicipioHiberImpl();
        }
        return municipioHiber;
    }

    @Override
    public List<Municipio> findAll() {
        Session session = HibernateUtil.getSession();
        if(session == null){
            System.out.println(" *** Error de Conexión *** ");
            return null;
        }
        List<Municipio> list = session.createQuery("FROM MUNICIPIO",Municipio.class).getResultList();
        session.close();
        return list;
    }

    @Override
    public boolean save(Municipio municipio) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.persist(municipio);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean update(Municipio municipio) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.merge(municipio);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean delete(Municipio municipio) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.remove(municipio);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public Municipio findById(Integer id) {
        Session session = HibernateUtil.getSession();

        Municipio municipio = session
                .get( Municipio.class, id );

        session.close();
        return municipio;
    }


}
