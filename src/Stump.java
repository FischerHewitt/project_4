import processing.core.PImage;

import java.util.List;

public class Stump extends Entity{
    public static final String STUMP_KEY = "stump";
    public static final int STUMP_NUM_PROPERTIES = 0;

    /**
     * Creates a new Stump.
     * @param id The new Stump's id.
     * @param position The Stump's x,y position in the world.
     * @param images Images to use for the Stump.
     * @return a new Entity whose type is Stump.
     */
    public static Entity createStump(String id, Point position, List<PImage> images) {
        return new Stump(id, position, images, 0, 0, 0, 0, 0, 0);
    }

    public Stump(String id, Point position, List<PImage> images, int resourceLimit, int resourceCount, double actionPeriod, double animationPeriod, int health, int healthLimit) {
        super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod, health, healthLimit);
    }

    // fix later: wont need
    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {

    }

    // fix later: wont need
    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {

    }

    // fix later: wont need
    public double getAnimationPeriod() {
        return 0;
    }

}
