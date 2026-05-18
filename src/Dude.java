import processing.core.PImage;
import java.util.List;
/**
 Developers: Fischer Hewitt, Ayaan Kazerouni, Paris Kalathas
 Date: 05/14/2026
 Project 4: Cohesion
 Description: This project refactors a virtual world that has several different entities that perform different tasks
 and interact with each other. Trees can get chopped down, saplings can grow, faries search for stumps and make them grow,
 dudes move around collecting resources from plants. The world runs automatically and the entities interact with each other.


 * Represents a Dude entity.
 * Dude is an abstract parent class for DudeNotFull and DudeFull.
 * Dudes can move around the world, animate, and perform scheduled activities.
 */
public abstract class Dude extends ActiveEntity{
    // Instance Variables
    public static final String DUDE_KEY = "dude";
    public static final int DUDE_ACTION_PERIOD_IDX = 0;
    public static final int DUDE_ANIMATION_PERIOD_IDX = 1;
    public static final int DUDE_RESOURCE_LIMIT_IDX = 2;
    public static final int DUDE_NUM_PROPERTIES = 3;
    protected final int resourceLimit;
    /**
     * * Creates a new Dude.
     * @param id The new Dude's id.
     * @param position The Dude's x,y position in the World.
     * @param animationPeriod The time (seconds) taken for each animation.
     * @param images Images to use for the Dude.
     * @param resourceLimit The resourceLimit for this entity.
     * @return a new Entity whose type is Dude.
     * Purpose: Construct a new dude object
     * Input: String id, Point position, List<PImage> images, double animationPeriod, int resourceLimit, int resourceCount, double actionPeriod
     * Result: creates a new dude by calling the ActiveEntity constructor, and stores resourceLimit and resourceCount
     * Output: no return/constructor
     * */
    public Dude(String id, Point position, List<PImage> images, double animationPeriod, int resourceLimit, double actionPeriod) {
        super(id, position, images, animationPeriod, actionPeriod);
        this.resourceLimit = resourceLimit;
    }
    /*
    Purpose: finds the next position for the dude to move toward a destination
    Input: WorldModel world, Point destPos
    Result: checks horizontal movement first, then vertical movement if needed, and avoids occupied spaces unless the occupied space is a Stump
    Output: Point
     */
    public Point nextPositionDude(WorldModel world, Point destPos) {
        int horiz = Integer.signum(destPos.x - this.position.x);
        Point newPos = new Point(this.position.x + horiz, this.position.y);
        // checks if it can move horizantally
        if (horiz == 0 || world.isOccupied(newPos) && !(world.getOccupancyCell(newPos) instanceof Stump)) {
            int vert = Integer.signum(destPos.y - this.position.y);
            newPos = new Point(this.position.x, this.position.y + vert);
            // checks if it can move vertically
            if (vert == 0 || world.isOccupied(newPos) && !(world.getOccupancyCell(newPos) instanceof Stump)) {
                newPos = this.position;
            }
        }
        return newPos;
    }

    /*
    Purpose: performs Dude's activity
    Input: WorldModel world, ImageStore imageStore, EventScheduler scheduler
    Result: the dude executes its respective activity by moving towards its respective target
    Output: void
    */
    public abstract void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);
}
