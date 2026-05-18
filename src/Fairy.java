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


 * Represents a Fairy entity.
 * Fairy searches for Stumps, moves toward them, removes them,
 * and replaces them with Saplings.
 */
public class Fairy extends ActiveEntity {
    //instance variables
    public static final String FAIRY_KEY = "fairy";
    public static final int FAIRY_ANIMATION_PERIOD_IDX = 0;
    public static final int FAIRY_ACTION_PERIOD_IDX = 1;
    public static final int FAIRY_NUM_PROPERTIES = 2;
    /**
     * Creates a new Fairy.
     * @param id The id of the new Fairy.
     * @param position The position (x,y coordinate) of this new Fairy.
     * @param actionPeriod The actionPeriod, or how long it takes to perform each activity.
     * @param animationPeriod The animationPeriod, or how long it takes to perform one animation.
     * @param images The image list associated with this Fairy.
     * Purpose: Creates a new Fairy object.
     * Input: String id, Point position, double actionPeriod, double animationPeriod, List<PImage> images.
     * Result: Returns a new Fairy
     * Output: Fairy
     */
    public static Entity createFairy(String id, Point position, double actionPeriod, double animationPeriod, List<PImage> images) {
        return new Fairy(id, position, images, animationPeriod, actionPeriod);
    }
    /*
     Purpose: Constructs a new Fairy object.
     Input: String id, Point position, List<PImage> images, double animationPeriod, double actionPeriod.
     Result: Creates a new Fairy by calling the ActiveEntity constructor.
     Output: No return/constructor.
     */
    public Fairy(String id, Point position, List<PImage> images, double animationPeriod, double actionPeriod) {
        super(id, position, images, animationPeriod, actionPeriod);
    }
    /*
    Purpose: performs Fairy's activity
    Input: WorldModel world, ImageStore imageStore, EventScheduler scheduler
    Result: finds the nearest Stump, moves toward it, removes it, and creates a Sapling in its place
    Output: void
 */
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        // find nearest stump
        Optional<Entity> fairyTarget = world.findNearest(this.position, new ArrayList<>(List.of(Stump.class)));
        if (fairyTarget.isPresent()) {
            // move toward stump
            Point tgtPos = fairyTarget.get().position;

            if (this.moveToFairy(world, fairyTarget.get(), scheduler)) {
                // if stump found, turn into sapling
                AnimatedEntity sapling = (AnimatedEntity) Sapling.createSapling(Sapling.SAPLING_KEY + "_" + fairyTarget.get().id, tgtPos, imageStore.getImageList(Sapling.SAPLING_KEY));
                // add sapling to the world
                world.addEntity(sapling);
                sapling.scheduleActions(scheduler, world, imageStore);
            }
        }
        scheduler.scheduleEvent(this, Action.createActivityAction(this, world, imageStore), this.actionPeriod);
    }
    /*
    Purpose: moves Fairy toward a stump
    Input: WorldModel world, Entity target, EventScheduler scheduler
    Result: removes the Stump if the Fairy is next to it; otherwise moves the Fairy one step closer
    Output: boolean
 */
    public boolean moveToFairy(WorldModel world, Entity target, EventScheduler scheduler) {
        if (this.position.adjacent(target.position)) {
            // remove stump
            world.removeEntity(scheduler, target);
            return true;
        } else {
            // find a point closer to the stump
            Point nextPos = this.nextPositionFairy(world, target.position);

            if (!this.position.equals(nextPos)) {
                // move toward the stump
                world.moveEntity(scheduler, this, nextPos);
            }
            return false;
        }
    }
    /*
    Purpose: determines the next position for the Fairy when moving toward a target
    Input: WorldModel world, Point destPos
    Result: returns the next open position horizontally or vertically toward the destination, or stays in place if blocked
    Output: Point
    */
    public Point nextPositionFairy(WorldModel world, Point destPos) {
        int horiz = Integer.signum(destPos.x - this.position.x);
        Point newPos = new Point(this.position.x + horiz, this.position.y);
        // try to move horizontally
        if (horiz == 0 || world.isOccupied(newPos)) {
            int vert = Integer.signum(destPos.y - this.position.y);
            newPos = new Point(this.position.x, this.position.y + vert);
            // try to move vertically
            if (vert == 0 || world.isOccupied(newPos)) {
                // Stay in place if you can not move
                newPos = this.position;
            }
        }
        return newPos;
    }
}
