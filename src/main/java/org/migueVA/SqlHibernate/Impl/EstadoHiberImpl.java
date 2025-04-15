package org.migueVA.SqlHibernate.Impl;

import org.hibernate.Session;
import org.migueVA.Hibernate.HibernateUtil;
import org.migueVA.Model.Estado;
import org.migueVA.SqlHibernate.GenericoSQL;

import java.util.List;

public class EstadoHiberImpl implements GenericoSQL<Estado> {

    private static EstadoHiberImpl estadoHiber;

    private EstadoHiberImpl() {
    }

    public static EstadoHiberImpl getInstance(){
        if(estadoHiber == null){
            estadoHiber = new EstadoHiberImpl();
        }
        return estadoHiber;
    }

    @Override
    public List<Estado> findAll() {
        Session session = HibernateUtil.getSession();
        if(session == null){
            System.out.println(" *** Error de Conexión *** ");
            return null;
        }
    List<Estado> list = session.createQuery("FROM ESTADO", Estado.class).getResultList();
    session.close();
    return list;
    }

    @Override
    public boolean save(Estado estado) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.persist(estado);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean update(Estado estado) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.merge(estado);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean delete(Estado estado) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.remove(estado);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public Estado findById(Integer id) {
        Session session = HibernateUtil.getSession();

        Estado estado = session
                .get( Estado.class, id );

        session.close();
        return estado;
    }
}
