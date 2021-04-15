package Render;

import static java.lang.Math.*;

public class ActionLimiter {
    private boolean active;
    private int actionDurationMillisMin;

    public ActionLimiter(int maxActionsPerSecond){
        active = true;
        actionDurationMillisMin = (int)round(1000.0/maxActionsPerSecond);
        //this.limitable = limitable;
    }

    public void setActive(boolean newValue){active = newValue;}

    public void waitToNextAction(long actionDurationMillis){
        if(actionDurationMillis < actionDurationMillisMin && active) {
            try {
                Thread.sleep(actionDurationMillisMin - actionDurationMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
