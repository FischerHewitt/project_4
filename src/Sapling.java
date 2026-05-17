import processing.core.PImage;

import java.util.List;

public class Sapling extends Plant{
    public static final double SAPLING_ACTION_ANIMATION_PERIOD = 1.000;
    public static final int SAPLING_HEALTH_LIMIT = 5;
    public static final String SAPLING_KEY = "sapling";
    public static final int SAPLING_HEALTH_IDX = 0;
    public static final int SAPLING_NUM_PROPERTIES = 1;
    /**
     * Creates a new Sapling.
     * @param id The new Sapling's id.
     * @param position The Sapling's x,y position in the World.
     * @param images Images to use for the Sapling.
     * @param health The Sapling's starting health.
     *               Note that the Sapling also has an upper health limit, after which it will turn into a tree.
     *               Like the Tree, the Sapling also has an action and animation period, but those are not parameters
     *               since they need to be kept in sync with each other. The Sapling's activity is to grow.
     * @return a new Entity whose type is Sapling.
     */
    public static Entity createSapling(String id, Point position, List<PImage> images, int health) {
        return new Sapling(id, position, images, 0, 0, SAPLING_ACTION_ANIMATION_PERIOD, SAPLING_ACTION_ANIMATION_PERIOD, 0, SAPLING_HEALTH_LIMIT);
    }

    public Sapling(String id, Point position, List<PImage> images, int resourceLimit, int resourceCount, double actionPeriod, double animationPeriod, int health, int healthLimit) {
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
        this.health++;
        if (!this.transformPlant(world, scheduler, imageStore)) {
            scheduler.scheduleEvent(this, Action.createActivityAction(this, world, imageStore), this.actionPeriod);
        }
    }

    // fix later: delete this.kind
    public boolean transformPlant(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (this.getClass().getSimpleName().equals("Sapling")) {
            return this.transformSapling(world, scheduler, imageStore);
        } else {
            throw new UnsupportedOperationException(String.format("transformPlant not supported for %s", this));
        }
    }

    public boolean transformSapling(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (this.health <= 0) {
            Entity stump = Stump.createStump(Stump.STUMP_KEY + "_" + this.id, this.position, imageStore.getImageList(Stump.STUMP_KEY));

            world.removeEntity(scheduler, this);

            world.addEntity(stump);

            return true;
        } else if (this.health >= this.healthLimit) {
            AnimatedEntity tree = (AnimatedEntity) Tree.createTree(Tree.TREE_KEY + "_" + this.id, this.position, Functions.getNumFromRange(Tree.TREE_ACTION_MAX, Tree.TREE_ACTION_MIN), Functions.getNumFromRange(Tree.TREE_ANIMATION_MAX, Tree.TREE_ANIMATION_MIN), Functions.getIntFromRange(Tree.TREE_HEALTH_MAX, Tree.TREE_HEALTH_MIN), imageStore.getImageList(Tree.TREE_KEY));

            world.removeEntity(scheduler, this);

            world.addEntity(tree);
            tree.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }
}
