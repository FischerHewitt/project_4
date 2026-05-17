import processing.core.PImage;

import java.util.List;

public class Obstacle extends AnimatedEntity{
    public static final String OBSTACLE_KEY = "obstacle";
    public static final int OBSTACLE_ANIMATION_PERIOD_IDX = 0;
    public static final int OBSTACLE_NUM_PROPERTIES = 1;
    /**
     * Creates a new Obstacle.
     * @param id The new Obstacle's id.
     * @param position The Obstacle's x,y position in the World.
     * @param animationPeriod The time (seconds) taken for each animation.
     * @param images Images to use for the Obstacle.
     * @return a new Entity whose type is Obstacle.
     */
    public static Entity createObstacle(String id, Point position, double animationPeriod, List<PImage> images) {
        return new Obstacle(id, position, images, animationPeriod);
    }

    public Obstacle(String id, Point position, List<PImage> images, double animationPeriod) {
        super(id, position, images, animationPeriod);
    }

    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this, Action.createAnimationAction(this, 0, world, imageStore), getAnimationPeriod());
    }

}
