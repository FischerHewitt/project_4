import processing.core.PImage;
import java.util.List;
/**
 Developers: Fischer Hewitt, Ayaan Kazerouni, Paris Kalathas
 Date: 05/14/2026
 Project 4: Cohesion
 Description: This project refactors a virtual world that has several different entities that perform different tasks
 and interact with each other. Trees can get chopped down, saplings can grow, faries search for stumps and make them grow,
 dudes move around collecting resources from plants. The world runs automatically and the entities interact with each other.


 * Represents a Sapling entity.
 * Sapling grows over time by increasing its health.
 * It can transform into a Tree when fully grown or into a Stump if its health reaches zero.
 */
public class Sapling extends Plant{
    // The Sapling's action and animation periods have to be in sync since it grows and gains health at same time.
    // instance variables
    public static final double SAPLING_ACTION_ANIMATION_PERIOD = 1.000;
    public static final int SAPLING_HEALTH_LIMIT = 5;
    public static final String SAPLING_KEY = "sapling";
    public static final int SAPLING_HEALTH_IDX = 0;
    public static final int SAPLING_NUM_PROPERTIES = 1;
    protected final int healthLimit;
    /**
     * Creates a new Sapling.
     * @param id The id of the new Sapling.
     * @param position The position (x,y coordinate) of this new Sapling.
     * @param images The image list associated with this Sapling
     * Purpose: Creates a new Sapling object.
     * Input: String id, Point position, List<PImage> images.
     * Result: Returns a new Sapling
     * Output: Sapling
     */
    public static Entity createSapling(String id, Point position, List<PImage> images) {
        return new Sapling(id, position, images, SAPLING_ACTION_ANIMATION_PERIOD, SAPLING_ACTION_ANIMATION_PERIOD, 0, SAPLING_HEALTH_LIMIT);
    }
    /**
     * Creates a new Sapling.
     * @param id The id of the new Sapling.
     * @param position The position (x,y coordinate) of this new Sapling.
     * @param images The image list associated with this Sapling.
     * @param actionPeriod The actionPeriod, or how long it takes to perform each activity.
     * @param animationPeriod The animationPeriod, or how long it takes to perform one animation.
     * @param health The starting health of this Sapling.
     * @param healthLimit The max health this Sapling can reach before transforming.
     * Purpose: Constructs a new Sapling object.
     * Input: String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod, int health, int healthLimit.
     * Result: creates sapling using plant constructor and stores the Sapling's health limit.
     * Output: No return/constructor.
     */
    public Sapling(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod, int health, int healthLimit) {
        super(id, position, images, animationPeriod, actionPeriod, health);
        this.healthLimit = healthLimit;
    }
    /*
    Purpose: performs Sapling's activity
    Input: WorldModel world, ImageStore imageStore, EventScheduler scheduler
    Result: increases the Sapling's health, transforms it if needed, or schedules its next activity
    Output: void
    */
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        this.health++;
        if (!this.transformPlant(world, scheduler, imageStore)) {
            scheduler.scheduleEvent(this, Action.createActivityAction(this, world, imageStore), this.actionPeriod);
        }
    }
    /*
    Purpose: transforms the Sapling
    Input: WorldModel world, EventScheduler scheduler, ImageStore imageStore
    Result: turns the Sapling into a Stump if health is zero or less, turns it into a Tree if health reaches the health limit, or does nothing otherwise
    Output: boolean
    */
    public boolean transformPlant(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (transformToStump(world, scheduler, imageStore)) {
            return true;
        } else if (this.health >= this.healthLimit) {
            // creates a tree
            AnimatedEntity tree = (AnimatedEntity) Tree.createTree(Tree.TREE_KEY + "_" + this.id, this.position, Functions.getNumFromRange(Tree.TREE_ACTION_MAX, Tree.TREE_ACTION_MIN), Functions.getNumFromRange(Tree.TREE_ANIMATION_MAX, Tree.TREE_ANIMATION_MIN), Functions.getIntFromRange(Tree.TREE_HEALTH_MAX, Tree.TREE_HEALTH_MIN), imageStore.getImageList(Tree.TREE_KEY));
            // removes the current sapling
            world.removeEntity(scheduler, this);
            //adds a tree
            world.addEntity(tree);
            tree.scheduleActions(scheduler, world, imageStore);
            return true;
        }
        return false;
    }

}
