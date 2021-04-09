package com.learn.spring.base.chapter27.dao;

import com.learn.spring.base.chapter27.entity.Actor;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ActorRepository {
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void savaActor(Actor actor){
        this.sessionFactory.getCurrentSession()
                .save(actor);
    }

    @Transactional
    public List<Actor> getAll(){
        Query query =this.sessionFactory.getCurrentSession()
                .createQuery("from Actor");
    return query.list();
    }
}
