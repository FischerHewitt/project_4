import processing.core.PImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 Developers: Fischer Hewitt, Ayaan Kazerouni, Paris Kalathas
 Date: 05/14/2026
 Project 4: Cohesion
 Description: This project refactors a virtual world that has several different entities that perform different tasks
 and interact with each other. Trees can get chopped down, saplings can grow, faries search for stumps and make them grow,
 dudes move around collecting resources from plants. The world runs automatically and the entities interact with each other.


 * Represents a Dude that has already collected enough resources.
 * DudeFull searches for a House, moves toward it, and transforms back
 * into DudeNotFull after reaching the House.
 */
public class DudeFull extends Dude{
    /**
     * Creates a new DudeFull.
     * @param id The id of the new DudeFull.
     * @param position The position (x,y coordinate) of this new DudeFull.
     * @param images The image list associated with this DudeFull.
     * @param resourceLimit The max amount of resources this Dude can carry.
     * @param actionPeriod The actionPeriod, or how long it takes to perform each activity.
     * @param animationPeriod The animationPeriod, or how long it takes to perform one animation.
     * Purpose: Constructs a new DudeFull object.
     * Input: String id, Point position, List<PImage> images, int resourceLimit, double actionPeriod, double animationPeriod.
     * Result: Creates a new DudeFull Creates a new DudeFull by calling the Dude constructor.
     * Output: No return/constructor.
     */
    public DudeFull(String id, Point position, List<PImage> images, int resourceLimit, double actionPeriod, double animationPeriod) {
        super(id, position, images, animationPeriod, resourceLimit, actionPeriod);
    }
    /*
    Purpose: Creates a new DudeFull object.
    Input: String id, Point position, int resourceLimit, double actionPeriod, double animationPeriod, List<PImage> images,
    Result: Creates a new DudeFull
    Output: DudeFull
     */
    public static Entity createDudeFull(String id, Point position, double actionPeriod, double animationPeriod, int resourceLimit, List<PImage> images) {
        return new DudeFull(id, position, images, resourceLimit, actionPeriod, animationPeriod);
    }

    /*
    Purpose: performs the DudeFull's activity
    Input: WorldModel world, ImageStore imageStore, EventScheduler scheduler
    Result: finds the nearest House, moves toward it, and transforms into DudeNotFull if it reaches the House
    Output: void
  */
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> fullTarget = world.findNearest(this.position, new ArrayList<>(List.of(House.class)));

        if (fullTarget.isPresent() && this.moveToFull(world, fullTarget.get(), scheduler)) {
            this.transformFull(world, scheduler, imageStore);
        } else {
            scheduler.scheduleEvent(this, Action.createActivityAction(this, world, imageStore), this.actionPeriod);
        }
    }

    /*
    Purpose: moves the DudeFull toward a target House
    Input: WorldModel world, Entity target, EventScheduler scheduler
    Result: returns true if the DudeFull is next to the House; otherwise moves one step closer
    Output: boolean
 */
    public boolean moveToFull(WorldModel world, Entity target, EventScheduler scheduler) {
        // return true if close to a house
        if (this.position.adjacent(target.position)) {
            return true;
        } else {
            // move toward the house
            Point nextPos = this.nextPositionDude(world, target.position);

            if (!this.position.equals(nextPos)) {
                world.moveEntity(scheduler, this, nextPos);
            }
            return false;
        }
    }

    /*
    Purpose: transforms a DudeFull back into a DudeNotFull
    Input: WorldModel world, EventScheduler scheduler, ImageStore imageStore
    Result: removes the DudeFull, creates a DudeNotFull in the same position, adds it to the world, and schedules its actions
    Output: void
 */
    public void transformFull(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        // creates dude not full
        ActiveEntity dude = (ActiveEntity) DudeNotFull.createDudeNotFull(this.id, this.position, this.actionPeriod, this.animationPeriod, this.resourceLimit, this.images);
        // removes dude full from the world
        world.removeEntity(scheduler, this);
        //adds dude not full
        world.addEntity(dude);
        dude.scheduleActions(scheduler, world, imageStore);
    }


}
