import processing.core.PImage;
import java.util.List;
/**
 Developers: Fischer Hewitt, Ayaan Kazerouni, Paris Kalathas
 Date: 05/14/2026
 Project 4: Cohesion
 Description: This project refactors a virtual world that has several different entities that perform different tasks
 and interact with each other. Trees can get chopped down, saplings can grow, faries search for stumps and make them grow,
 dudes move around collecting resources from plants. The world runs automatically and the entities interact with each other.


 * Represents an Obstacle entity.
 * Obstacle is a stationary animated entity that blocks movement
 * and prevents other entities from moving through its position.
 */
public class Obstacle extends AnimatedEntity{
    // Instance variables
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
     * Purpose: creates a new obstacle
     * Input: String id, Point position, List<PImage> images, double animationPeriod
     * Result: builds and returns a new obstacle
     * Output: Obstacle
     */
    public static Entity createObstacle(String id, Point position, double animationPeriod, List<PImage> images) {
        return new Obstacle(id, position, images, animationPeriod);
    }
    /*
    Purpose: constructs a new Obstacle object
    Input: String id, Point position, List<PImage> images, double animationPeriod
    Result: creates a new obstacle by calling the AnimatedEntity constructor to initialize the shared entity fields and animation period
    Output: void
     */
    public Obstacle(String id, Point position, List<PImage> images, double animationPeriod) {
        super(id, position, images, animationPeriod);
    }
    /*
    Purpose: schedules this obstacle's animation events
    Input: EventScheduler scheduler, WorldModel world, ImageStore imageStore
    Result: creates and schedules an animation action for this obstacle using its animation period
    Output: void
     */
    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this, Action.createAnimationAction(this, 0, world, imageStore), getAnimationPeriod());
    }

}
