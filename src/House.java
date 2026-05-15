import processing.core.PImage;

import java.util.List;

public class House extends Entity{
    public static final String HOUSE_KEY = "house";
    public static final int HOUSE_NUM_PROPERTIES = 0;
    /**
     * Creates a new House.
     * @param id The new House's id.
     * @param position The new House's position (x,y coordinate) in the World.
     * @param images Images to use for House.
     * @return a new Entity whose type is House.
     */
    public static Entity createHouse(String id, Point position, List<PImage> images) {
        return new House(id, position, images, 0, 0, 0, 0, 0, 0);
    }


    public House(String id, Point position, List<PImage> images, int resourceLimit, int resourceCount, double actionPeriod, double animationPeriod, int health, int healthLimit) {
        super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod, health, healthLimit);
    }

    // fix later: wont need
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {

    }
    // fix later: wont need
    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {

    }
    // fix later wont need
    public double getAnimationPeriod() {
        return 0;
    }


}
