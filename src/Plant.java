import processing.core.PImage;
import java.util.List;
/**
 Developers: Fischer Hewitt, Ayaan Kazerouni, Paris Kalathas
 Date: 05/14/2026
 Project 4: Cohesion
 Description: This project refactors a virtual world that has several different entities that perform different tasks
 and interact with each other. Trees can get chopped down, saplings can grow, faries search for stumps and make them grow,
 dudes move around collecting resources from plants. The world runs automatically and the entities interact with each other.


 * Represents a Plant entity.
 * Plant is an abstract parent class for plants (ex tree, sapling).
 * Plants have health, activity, and transform.
 */
public abstract class Plant extends ActiveEntity{

    // Instance variables
    protected int health;
    /**
     * Creates a new Plant.
     * @param id The id of the new Plant.
     * @param position The position (x,y coordinate) of this new Plant.
     * @param images The image list associated with this Plant.
     * @param animationPeriod The animationPeriod, or how long it takes to perform one animation.
     * @param actionPeriod The actionPeriod, or how long it takes to perform each activity.
     * @param health The starting health of this Plant.
     * Purpose: Constructs a new Plant object.
     * Input: String id, Point position, List<PImage> images, double animationPeriod, double actionPeriod, int health.
     * Result: Initializes the shared ActiveEntity fields and stores the Plant's health.
     * Output: No return/constructor.
     */
    public Plant(String id, Point position, List<PImage> images, double animationPeriod, double actionPeriod, int health) {
        super(id, position, images, animationPeriod, actionPeriod);
        this.health = health;
    }
    /*
    Purpose: performs the Plant's activity
    Input: WorldModel world, ImageStore imageStore, EventScheduler scheduler
    Result: runs the specific activity behavior for the Plant subclass
    Output: void
 */
    public abstract void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);

    protected boolean transformToStump(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (this.health <= 0) {
            Entity stump = Stump.createStump(
                    Stump.STUMP_KEY + "_" + this.id,
                    this.position,
                    imageStore.getImageList(Stump.STUMP_KEY)
            );

            world.removeEntity(scheduler, this);
            world.addEntity(stump);

            return true;
        }

        return false;
    }
    /*
    Purpose: transforms the Plant when its health or growth condition is met
    Input: WorldModel world, EventScheduler scheduler, ImageStore imageStore
    Result: lets the specific Plant transform into another entity
    Output: boolean
    */
    public abstract boolean transformPlant(WorldModel world, EventScheduler scheduler, ImageStore imageStore);
    /*
    Purpose: gets the current health of the Plant
    Input: none
    Result: returns the Plant's current health
    Output: int
 */
    public int getHealth() {
        return health;
    }
}
