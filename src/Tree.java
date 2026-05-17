import processing.core.PImage;

import java.util.List;

public class Tree extends ActiveEntity{
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
     * @param id The new Tree's id.
     * @param position The Tree's x,y position in the World.
     * @param actionPeriod The time (seconds) taken for each activity (checking its health).
     * @param animationPeriod The time (seconds) taken for each animation.
     * @param health The Tree's starting health.
     * @param images Images to use for the Tree.
     * @return a new Entity whose type is Tree.
     */

    public static Entity createTree(String id, Point position, double actionPeriod, double animationPeriod, int health, List<PImage> images) {
        return new Tree(id, position, images, 0, 0, actionPeriod, animationPeriod, health, 0);
    }

    public Tree(String id, Point position, List<PImage> images, int resourceLimit, int resourceCount, double actionPeriod, double animationPeriod, int health, int healthLimit) {
        super(id, position, images, animationPeriod, resourceLimit, resourceCount, actionPeriod, health, healthLimit);
    }

    public double getAnimationPeriod() {
        return  this.animationPeriod;
    }

    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this, Action.createActivityAction(this, world, imageStore), this.actionPeriod);
        scheduler.scheduleEvent(this, Action.createAnimationAction(this, 0, world, imageStore), getAnimationPeriod());
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {

        if (!this.transformPlant(world, scheduler, imageStore)) {

            scheduler.scheduleEvent(this, Action.createActivityAction(this, world, imageStore), this.actionPeriod);
        }
    }

    //fix later: delete this.kind
    public boolean transformPlant(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (this.getClass().getSimpleName().equals("Tree")){
            return this.transformTree(world, scheduler, imageStore);
        } else {
            throw new UnsupportedOperationException(String.format("transformPlant not supported for %s", this));
        }
    }

    public boolean transformTree(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (this.health <= 0) {
            Entity stump = Stump.createStump(Stump.STUMP_KEY + "_" + this.id, this.position, imageStore.getImageList(Stump.STUMP_KEY));

            world.removeEntity(scheduler, this);

            world.addEntity(stump);

            return true;
        }

        return false;
    }
}
