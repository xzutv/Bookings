package se.yrgo.services.staff;

import java.util.*;

import se.yrgo.dataaccess.*;
import se.yrgo.domain.*;

public interface StaffService {

    public void newStaff(Staff newStaff);

    public void updateStaff(Staff updateStaff);

    public void deleteStaff(Staff oldStaff);

    public List<Staff> getStaffList();

    public Staff findStaffById(int staffId);

}
