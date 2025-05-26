package se.yrgo.init;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.stereotype.*;

import se.yrgo.dataaccess.*;
import se.yrgo.domain.*;

@Component
public class DataInitializer implements CommandLineRunner{

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private StaffDao staffDao;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running DataInitializer...");

        Customer c = new Customer("Berit", "berit@gmail.com", "08-99900099");
        Customer c1 = new Customer("Bertil", "bertil@gmail.com", "0738888888");
        Customer c2 = new Customer("Farhad", "farhad@gmail.com", "08-999922323");
        Customer c3 = new Customer("Mariosa", "mariosa@gmail.com", "07009923232");


        Staff s = new Staff("Damir", "damir@gmail.com", "078882392323");
        Staff s1 = new Staff("Mario", "mario@gmail.com", "073423232323");
        Staff s2 = new Staff("Haimen", "haimen@gmail.com", "073888232923");

        customerDao.create(c);
        customerDao.create(c1);
        customerDao.create(c2);
        customerDao.create(c3);

        staffDao.create(s);
        staffDao.create(s1);
        staffDao.create(s2);

        System.out.println("Data har skapats! ");

    }
    
}
