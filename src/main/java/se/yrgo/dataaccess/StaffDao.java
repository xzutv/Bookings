package se.yrgo.dataaccess;

import java.util.*;

import org.springframework.dao.*;

import se.yrgo.domain.*;

public interface StaffDao {
    public void create(Staff newStaff);
    public void update(Staff updateStaff);
    public void delete(Staff deleteStaff);
    public List<Staff> allStaff();
    public Staff getById(int staffId);

}
