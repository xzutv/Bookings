package se.yrgo.dataaccess;

import java.util.*;

import org.springframework.dao.*;

import se.yrgo.domain.*;

public interface ActivityDao {
    public void create(Activity newActivity);
    public List<Activity> allActivities();
    public void update(Activity updateActivity);
    public void delete(Activity deleteActivity);

}
