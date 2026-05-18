import processing.core.PImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
/**
 Developers: Fischer Hewitt, Ayaan Kazerouni, Paris Kalathas
 Date: 05/14/2026
 Project 4: Cohesion
 Description: This project refactors a virtual world that has several different entities that perform different tasks
 and interact with each other. Trees can get chopped down, saplings can grow, faries search for stumps and make them grow,
 dudes move around collecting resources from plants. The world runs automatically and the entities interact with each other.


 * Represents a Dude that has not reached its resource limit.
 * DudeNotFull searches for Trees or Saplings, collects resources from them,
 * and transforms into DudeFull when its resource count reaches the resource limit.
 */
public class DudeNotFull extends Dude{
    // instance variables
    protected int resourceCount;

    /**
     * Creates a new DudeNotFull.
     * @param id The Dude's id.
     * @param position The Dude's x,y position in the World.
     * @param actionPeriod The time (seconds) taken for each activity (finding and chopping down a tree or sapling.)
     * @param animationPeriod The time (seconds) taken for each animation.
     * @param resourceLimit The amount of wood this Dude can carry before becoming a DudeFull.
     * @param images Images to use for the Dude.
     * @return a new Entity whose type is DudeNotFull.
     * Purpose: creates a new DudeNotFull object.
     * Input: String id, Point position, List<PImage> images, double animationPeriod, int resourceLimit, int resourceCount, double actionPeriod.
     * Result: Creates a new DudeNotFull
     * Output: new DudeNotFull
     */
    public static Entity createDudeNotFull(String id, Point position, double actionPeriod, double animationPeriod, int resourceLimit, List<PImage> images) {
        return new DudeNotFull(id, position, images, animationPeriod, resourceLimit, 0, actionPeriod);
    }
    /**

     * Creates a new DudeNotFull.
     * @param id The Dude's id.
     * @param position The Dude's x,y position in the World.
     * @param actionPeriod The time (seconds) taken for each activity (finding and chopping down a tree or sapling.)
     * @param animationPeriod The time (seconds) taken for each animation.
     * @param resourceLimit The amount of wood this Dude can carry before becoming a DudeFull.
     * @param resourceCount The resourceCount for this entity.
     * @param images Images to use for the Dude.
     Purpose: Constructs a new DudeNotFull object.
     Input: String id, Point position, List<PImage> images, double animationPeriod, int resourceLimit, int resourceCount, double actionPeriod.
     Result: Creates a new DudeNotFull by calling the Dude constructor.
     Output: no return/constructor
     */
    public DudeNotFull(String id, Point position, List<PImage> images, double animationPeriod, int resourceLimit, int resourceCount, double actionPeriod) {
        super(id, position, images, animationPeriod, resourceLimit, actionPeriod);
        this.resourceCount = resourceCount;
    }
    /*
    Purpose: performs DudeNotFull's activity
    Input: WorldModel world, ImageStore imageStore, EventScheduler scheduler
    Result: finds the nearest Tree or Sapling, moves toward it, collects resources, and transforms if the resource limit is reached
    Output: void
    */
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        // move toward tree or sapling
        Optional<Entity> target = world.findNearest(this.position, new ArrayList<>(Arrays.asList(Tree.class, Sapling.class)));
        // transform entity
        if (target.isEmpty() || !this.moveToNotFull(world, (Plant) target.get(), scheduler) || !this.transformNotFull(world, scheduler, imageStore)) {
            scheduler.scheduleEvent(this, Action.createActivityAction(this, world, imageStore), this.actionPeriod);
        }
    }
    /*
    Purpose: transforms a DudeNotFull into a DudeFull when the resource count reaches the resource limit
    Input: WorldModel world, EventScheduler scheduler, ImageStore imageStore
    Result: removes the DudeNotFull, creates a DudeFull in the same position, adds it to the world, and schedules its actions
    Output: boolean
    */
    public boolean transformNotFull(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (this.resourceCount >= this.resourceLimit) {
            // create DudeFull
            AnimatedEntity dude = (AnimatedEntity) DudeFull.createDudeFull(this.id, this.position, this.actionPeriod, this.animationPeriod, this.resourceLimit, this.images);
            // remove dude not full
            world.removeEntity(scheduler, this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(dude);
            dude.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }
    /*
    Purpose: moves the DudeNotFull toward a target Plant, or attacks the target
    Input: WorldModel world, Plant target, EventScheduler scheduler
    Result: if adjacent to the Plant, increases resourceCount and lowers the Plant's health; otherwise moves one step closer
    Output: boolean
    */
    public boolean moveToNotFull(WorldModel world, Plant target, EventScheduler scheduler) {
        // attacks the target
        if (this.position.adjacent(target.position)) {
            this.resourceCount += 1;
            target.health--;
            return true;
        } else {
            // moves towards target
            Point nextPos = this.nextPositionDude(world, target.position);

            if (!this.position.equals(nextPos)) {
                world.moveEntity(scheduler, this, nextPos);
            }
            return false;
        }
    }
}
