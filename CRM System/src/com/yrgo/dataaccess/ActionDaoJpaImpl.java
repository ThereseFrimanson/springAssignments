package com.yrgo.dataaccess;

import com.yrgo.domain.Action;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ActionDaoJpaImpl implements ActionDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Action newAction) {
        em.persist(newAction);
    }

    @Override
    public List<Action> getIncompleteActions(String userId) {
    return em.createQuery("SELECT a FROM Action a WHERE a.owningUser = :userId AND a.complete = false", Action.class)
            .setParameter("userId", userId)
            .getResultList();
    }

    @Override
    public void update(Action actionToUpdate) throws RecordNotFoundException {
        Action action = em.find(Action.class, actionToUpdate.getActionId());
        if (action == null) {
            throw new RecordNotFoundException();
        }
        em.merge(actionToUpdate);
    }


    @Override
    public void delete(Action oldAction) throws RecordNotFoundException {
        Action managedAction = em.find(Action.class, oldAction.getActionId());
        if(managedAction == null) {
            throw new RecordNotFoundException();
        }
        em.remove(managedAction);
    }




}
