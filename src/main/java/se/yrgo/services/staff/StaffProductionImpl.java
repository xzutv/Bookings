package se.yrgo.services.staff;

import java.util.*;

import javax.transaction.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import se.yrgo.dataaccess.*;
import se.yrgo.domain.*;

@Service("staffService")
@Transactional
public class StaffProductionImpl implements StaffService{

    @Autowired
    private StaffDao staffDao;

    @Override
    public void newStaff(Staff newStaff) {
        staffDao.create(newStaff);
    }

    @Override
    public void updateStaff(Staff updateStaff) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateStaff'");
    }

    @Override
    public void deleteStaff(Staff oldStaff) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteStaff'");
    }

    @Override
    public List<Staff> getAllStaff() {
        return staffDao.allStaff();
    }

    @Override
    public Staff findStaffById(int staffId) {
        return staffDao.getById(staffId);
    }
    
}
