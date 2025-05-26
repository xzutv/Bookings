package se.yrgo.dataaccess;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import se.yrgo.domain.Staff;

@Repository
@Transactional
public class StaffDaoJpaimpl implements StaffDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Staff newStaff) {
        em.persist(newStaff);
    }

    @Override
    public void update(Staff updateStaff) {
        em.merge(updateStaff);
    }

    @Override
    public void delete(Staff deleteStaff) {
        Staff managed = em.merge(deleteStaff);
    }

    @Override
    public List<Staff> allStaff() {
        return em.createQuery("SELECT s FROM Staff s", Staff.class).getResultList();
    }

    @Override
    public Staff getById(int staffId) {
       return em.find(Staff.class, staffId);
    }

}
