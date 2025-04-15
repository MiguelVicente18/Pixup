package org.migueVA.SqlHibernate.Impl;

import org.hibernate.Session;
import org.migueVA.Hibernate.HibernateUtil;
import org.migueVA.Model.Artista;
import org.migueVA.Model.Colonia;
import org.migueVA.SqlHibernate.GenericoSQL;

import java.util.List;

public class ArtistaHiberImpl implements GenericoSQL<Artista> {
    private static ArtistaHiberImpl artistaHiber;

    private ArtistaHiberImpl() {
    }

    public static ArtistaHiberImpl getInstance(){
        if(artistaHiber == null){
            artistaHiber = new ArtistaHiberImpl();
        }
        return artistaHiber;
    }

    @Override
    public List<Artista> findAll() {
        Session session = HibernateUtil.getSession();
        if(session == null){
            System.out.println(" *** Error de Conexión *** ");
            return null;
        }
        List<Artista> list = session.createQuery("FROM Artista", Artista.class).getResultList();
        session.close();
        return list;
    }

    @Override
    public boolean save(Artista artista) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.persist(artista);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean update(Artista artista) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.merge(artista);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean delete(Artista artista) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.remove(artista);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public Artista findById(Integer id) {
        Session session = HibernateUtil.getSession();

        Artista artista  = session
                .get( Artista.class, id );

        session.close();
        return artista;
    }


}
