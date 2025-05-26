package se.yrgo.dataaccess;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import se.yrgo.domain.Activity;
import se.yrgo.services.activities.*;

@Repository
@Transactional
public class ActivityDaoJpaImpl implements ActivityDao{

	@PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void create(Activity newActivity){
        em.persist(newActivity);
    }

    @Override
    public List<Activity> allActivities(){
        return em.createQuery("SELECT a FROM Activity a" , Activity.class).getResultList();
    }

    @Override
    public void update(Activity updateActivity) {
        em.merge(updateActivity);
    }

    @Override
    public void delete(Activity deleteActivity) {
        Activity activityToDelete = em.merge(deleteActivity);
        em.remove(activityToDelete);
    }





    
}
