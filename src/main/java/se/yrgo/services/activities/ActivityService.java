package se.yrgo.services.activities;

import java.util.*;

import se.yrgo.dataaccess.*;
import se.yrgo.domain.*;

public interface ActivityService {

    public void newActivity(Activity newActivity);

    public void updateActivity(Activity updateActivity);

    public void deleteActivity(Activity oldActivity);

    public List<Activity> getAllActivities();

    public Activity findActivityById(int activityId);
    
}
