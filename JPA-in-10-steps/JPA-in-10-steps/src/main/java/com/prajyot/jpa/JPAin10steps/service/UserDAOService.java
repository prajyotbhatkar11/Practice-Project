package com.prajyot.jpa.JPAin10steps.service;

import com.prajyot.jpa.JPAin10steps.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDAOService {

    private EntityManager entityManager;

    @PersistenceContext
    public void inset(User user){
        entityManager.persist(user);

    }
}
