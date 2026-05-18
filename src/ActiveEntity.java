import processing.core.PImage;
import java.util.List;
import java.util.*;
/**
 Developers: Fischer Hewitt, Ayaan Kazerouni, Paris Kalathas
 Date: 05/14/2026
 Project 4: Cohesion
 Description: This project refactors a virtual world that has several different entities that perform different tasks
 and interact with each other. Trees can get chopped down, saplings can grow, faries search for stumps and make them grow,
 dudes move around collecting resources from plants. The world runs automatically and the entities interact with each other.


 * Represents an entity that has active behavior.
 * Active entities have to execute an activity period and can schedule active-related events.
 */
public abstract class ActiveEntity extends AnimatedEntity{

    // Instance variables
    protected final double actionPeriod;

    /**
     * Creates a new Entity.
     ** Creates a new AnimatedEntity
     * @param id The id of the new entity.
     * @param position The position (x,y coordinate) of this new entity.
     * @param images The image list associated with this entity.
     * @param animationPeriod The animationPeriod (i.e., how long it takes to perform one animation).
     * @param actionPeriod The actionPeriod for this entity (i.e., how long it takes to perform each activity action).
     *
     * Purpose: constructs a new ActiveEntity object
     * Input: String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod
     * Result: creates a new active entity by calling the AnimatedEntity constructor and assigning the action period
     * Output: no return/constructor
     */
    public ActiveEntity(String id, Point position, List<PImage> images, double animationPeriod, double actionPeriod) {
        super(id, position, images, animationPeriod);
        this.actionPeriod = actionPeriod;
    }
    /*
    Purpose: schedules this active entity's activity and animation events
    Input: EventScheduler scheduler, WorldModel world, ImageStore imageStore
    Result: creates and schedules an activity action using the action period, then creates and schedules an animation action using the animation period
    Output: void
     */
    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this, Action.createActivityAction(this, world, imageStore), this.actionPeriod);
        scheduler.scheduleEvent(this, Action.createAnimationAction(this, 0, world, imageStore), getAnimationPeriod());
    }
    /*
    Purpose: performs this active entity's main activity behavior
    Input: WorldModel world, ImageStore imageStore, EventScheduler scheduler
    Result: subclasses decide what activity to perform, such as moving, transforming, or interacting with another entity
    Output: void
     */
    public abstract void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);

}
