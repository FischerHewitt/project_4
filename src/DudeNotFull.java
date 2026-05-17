import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DudeNotFull extends Dude{

    /**
     * Creates a new DudeNotFull.
     * @param id The Dude's id.
     * @param position The Dude's x,y position in the World.
     * @param actionPeriod The time (seconds) taken for each activity (finding and chopping down a tree or sapling.)
     * @param animationPeriod The time (seconds) taken for each animation.
     * @param resourceLimit The amount of wood this Dude can carry before becoming a DudeFull.
     * @param images Images to use for the Dude.
     * @return a new Entity whose type is DudeNotFull.
     */
    public static Entity createDudeNotFull(String id, Point position, double actionPeriod, double animationPeriod, int resourceLimit, List<PImage> images) {
        return new DudeNotFull(id, position, images, animationPeriod, resourceLimit, 0, actionPeriod, 0, 0);
    }
    public DudeNotFull(String id, Point position, List<PImage> images, double animationPeriod, int resourceLimit, int resourceCount, double actionPeriod, int health, int healthLimit) {
        super(id, position, images, animationPeriod, resourceLimit, resourceCount, actionPeriod, health, healthLimit);
    }

    public double getAnimationPeriod() {
        return  this.animationPeriod;
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> target = world.findNearest(this.position, new ArrayList<>(Arrays.asList(Tree.class, Sapling.class)));

        if (target.isEmpty() || !this.moveToNotFull(world, (ActiveEntity) target.get(), scheduler) || !this.transformNotFull(world, scheduler, imageStore)) {
            scheduler.scheduleEvent(this, Action.createActivityAction(this, world, imageStore), this.actionPeriod);
        }
    }

    public boolean transformNotFull(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (this.resourceCount >= this.resourceLimit) {
            AnimatedEntity dude = (AnimatedEntity) DudeFull.createDudeFull(this.id, this.position, this.actionPeriod, this.animationPeriod, this.resourceLimit, this.images);

            world.removeEntity(scheduler, this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(dude);
            dude.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }

    public boolean moveToNotFull(WorldModel world, ActiveEntity target, EventScheduler scheduler) {
        if (this.position.adjacent(target.position)) {
            this.resourceCount += 1;
            target.health--;
            return true;
        } else {
            Point nextPos = this.nextPositionDude(world, target.position);

            if (!this.position.equals(nextPos)) {
                world.moveEntity(scheduler, this, nextPos);
            }
            return false;
        }
    }

}
