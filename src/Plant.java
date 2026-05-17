import processing.core.PImage;

import java.util.List;

public abstract class Plant extends ActiveEntity{
    /**
     * Creates a new entity.
     */

    /**
     * An entity that exists in the world. See EntityKind for the
     * different kinds of entities that exist.
     */
/*
    Static variables: These do not need to made private. But you should move them to new classes if appropriate.

    Variables whose names end in "IDX" are indices, i.e., positions. For example, DUDE_NUM_PROPERTIES indicates
    that Dudes have 3 properties (in addition to their id, x position, y position). The DUDE_ACTION_PERIOD_IDX (0)
    indicates that the Dude's action period is its first property, DUDE_ANIMATION_PERIOD_IDX (1) indicates that
    the Dude's animation period is its second property, and so on.
 */

    // The Sapling's action and animation periods have to be in sync since it grows and gains health at same time.

    // Instance variables
    protected int health;

    /**
     * Creates a new Entity.
     *
     * @param actionPeriod The actionPeriod for this entity (i.e., how long it takes to perform each activity action).
     *                     Not all entities need this.
     * @param animationPeriod The animationPeriod (i.e., how long it takes to perform one animation).
     *                        Not all entities need this.
     * @param health The entity's starting health. Not all entities need this.
     *
     */
    public Plant(String id, Point position, List<PImage> images, double animationPeriod, double actionPeriod, int health) {
        super(id, position, images, animationPeriod, actionPeriod);
        this.health = health;
    }

    public abstract void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);


    public abstract boolean transformPlant(WorldModel world, EventScheduler scheduler, ImageStore imageStore);

    public int getHealth() {
        return health;
    }
}
