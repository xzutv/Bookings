package se.yrgo.services.activities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.yrgo.dataaccess.ActivityDao;
import se.yrgo.domain.Activity;

@Service("activityService")
public class ActivityServiceProductionImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Override
    public void newActivity(Activity newActivity) {
        activityDao.create(newActivity);
    }

    @Override
    public void updateActivity(Activity updateActivity) {
        activityDao.update(updateActivity);
    }

    @Override
    public void deleteActivity(Activity oldActivity) {
        activityDao.delete(oldActivity);
    }

    @Override
    public List<Activity> getAllActivities() {
        return activityDao.allActivities();
    }

    @Override
    public Activity findActivityById(int activityId) {
        return activityDao.allActivities().stream()
                .filter(a -> a.getId() == activityId)
                .findFirst()
                .orElse(null);
    }
} 
