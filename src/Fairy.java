import processing.core.PImage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Fairy extends ActiveEntity {
    public static final String FAIRY_KEY = "fairy";
    public static final int FAIRY_ANIMATION_PERIOD_IDX = 0;
    public static final int FAIRY_ACTION_PERIOD_IDX = 1;
    public static final int FAIRY_NUM_PROPERTIES = 2;
    /**
     * Creates a new Fairy.
     * @param id The Fairy's id
     * @param position The Fairy's x,y location in the World.
     * @param actionPeriod The time (seconds) taken for each activity (turning a Stump into a Sapling).
     * @param animationPeriod The time (seconds) taken for each animation.
     * @param images Images to use for the Fairy.
     * @return a new Entity whose type is Fairy.
     */
    public static Entity createFairy(String id, Point position, double actionPeriod, double animationPeriod, List<PImage> images) {
        return new Fairy(id, position, images, animationPeriod,0, 0, actionPeriod, 0, 0);
    }

    public Fairy(String id, Point position, List<PImage> images, double animationPeriod, int resourceLimit, int resourceCount, double actionPeriod, int health, int healthLimit) {
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
        Optional<Entity> fairyTarget = world.findNearest(this.position, new ArrayList<>(List.of(Stump.class)));

        if (fairyTarget.isPresent()) {
            Point tgtPos = fairyTarget.get().position;

            if (this.moveToFairy(world, fairyTarget.get(), scheduler)) {

                AnimatedEntity sapling = (AnimatedEntity) Sapling.createSapling(Sapling.SAPLING_KEY + "_" + fairyTarget.get().id, tgtPos, imageStore.getImageList(Sapling.SAPLING_KEY), 0);

                world.addEntity(sapling);
                sapling.scheduleActions(scheduler, world, imageStore);
            }
        }

        scheduler.scheduleEvent(this, Action.createActivityAction(this, world, imageStore), this.actionPeriod);
    }

    public boolean moveToFairy(WorldModel world, Entity target, EventScheduler scheduler) {
        if (this.position.adjacent(target.position)) {
            world.removeEntity(scheduler, target);
            return true;
        } else {
            Point nextPos = this.nextPositionFairy(world, target.position);

            if (!this.position.equals(nextPos)) {
                world.moveEntity(scheduler, this, nextPos);
            }
            return false;
        }
    }

    public Point nextPositionFairy(WorldModel world, Point destPos) {
        int horiz = Integer.signum(destPos.x - this.position.x);
        Point newPos = new Point(this.position.x + horiz, this.position.y);

        if (horiz == 0 || world.isOccupied(newPos)) {
            int vert = Integer.signum(destPos.y - this.position.y);
            newPos = new Point(this.position.x, this.position.y + vert);

            if (vert == 0 || world.isOccupied(newPos)) {
                newPos = this.position;
            }
        }

        return newPos;
    }


}
