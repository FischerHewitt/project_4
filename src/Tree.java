import processing.core.PImage;
import java.util.List;
/**
 Developers: Fischer Hewitt, Ayaan Kazerouni, Paris Kalathas
 Date: 05/14/2026
 Project 4: Cohesion
 Description: This project refactors a virtual world that has several different entities that perform different tasks
 and interact with each other. Trees can get chopped down, saplings can grow, faries search for stumps and make them grow,
 dudes move around collecting resources from plants. The world runs automatically and the entities interact with each other.


 * Represents a Tree entity.
 * Tree loses health when a DudeNotFull collects from it.
 * It transforms into a Stump when its health reaches zero.
 */
public class Tree extends Plant {
    // instance variables
    public static final String TREE_KEY = "tree";
    public static final int TREE_ANIMATION_PERIOD_IDX = 0;
    public static final int TREE_ACTION_PERIOD_IDX = 1;
    public static final int TREE_HEALTH_IDX = 2;
    public static final int TREE_NUM_PROPERTIES = 3;
    public static final double TREE_ANIMATION_MAX = 0.600;
    public static final double TREE_ANIMATION_MIN = 0.050;
    public static final double TREE_ACTION_MAX = 1.400;
    public static final double TREE_ACTION_MIN = 1.000;
    public static final int TREE_HEALTH_MAX = 3;
    public static final int TREE_HEALTH_MIN = 1;

    /**
     * Creates a new Tree.
     * @param id The id of the new Tree.
     * @param position The position (x,y coordinate) of this new Tree.
     * @param actionPeriod The actionPeriod, or how long it takes to perform each activity.
     * @param animationPeriod The animationPeriod, or how long it takes to perform one animation.
     * @param health The starting health of this Tree.
     * @param images The image list associated with this Tree.
     * Purpose: Creates a new Tree object.
     * Input: String id, Point position, double actionPeriod, double animationPeriod, int health, List<PImage> images.
     * Result: Returns a new Tree
     * Output: Tree
     */
    public static Entity createTree(String id, Point position, double actionPeriod, double animationPeriod, int health, List<PImage> images) {
        return new Tree(id, position, images, actionPeriod, animationPeriod, health);
    }
    /**
     * Creates a new Tree.
     * @param id The id of the new Tree.
     * @param position The position (x,y coordinate) of this new Tree.
     * @param images The image list associated with this Tree.
     * @param actionPeriod The actionPeriod, or how long it takes to perform each activity.
     * @param animationPeriod The animationPeriod, or how long it takes to perform one animation.
     * @param health The starting health of this Tree.
     * Purpose: Constructs a new Tree object.
     * Input: String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod, int health.
     * Result: creates tree using plant constructor
     * Output: No return value because this is a constructor.
     */
    public Tree(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod, int health) {
        super(id, position, images, animationPeriod, actionPeriod, health);
    }

    /*
    Purpose: performs Tree's activity
    Input: WorldModel world, ImageStore imageStore, EventScheduler scheduler
    Result: transforms the Tree into a Stump if its health is zero or less; otherwise schedules its next activity
    Output: void
    */
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {

        if (!this.transformPlant(world, scheduler, imageStore)) {

            scheduler.scheduleEvent(this, Action.createActivityAction(this, world, imageStore), this.actionPeriod);
        }
    }
    /*
    Purpose: transforms the Tree when its health reaches zero
    Input: WorldModel world, EventScheduler scheduler, ImageStore imageStore
    Result: turns the Tree into a Stump if its health is zero or less
    Output: boolean
 */
    public boolean transformPlant(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
            return this.transformToStump(world, scheduler, imageStore);
    }
}
