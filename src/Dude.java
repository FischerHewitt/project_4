import processing.core.PImage;

import java.util.List;

public abstract class Dude extends ActiveEntity{
    public static final String DUDE_KEY = "dude";
    public static final int DUDE_ACTION_PERIOD_IDX = 0;
    public static final int DUDE_ANIMATION_PERIOD_IDX = 1;
    public static final int DUDE_RESOURCE_LIMIT_IDX = 2;
    public static final int DUDE_NUM_PROPERTIES = 3;

    public Dude(String id, Point position, List<PImage> images, double animationPeriod, int resourceLimit, int resourceCount, double actionPeriod, int health, int healthLimit) {
        super(id, position, images, animationPeriod, resourceLimit, resourceCount, actionPeriod, health, healthLimit);
    }

    public abstract void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);

    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this, Action.createActivityAction(this, world, imageStore), this.actionPeriod);
        scheduler.scheduleEvent(this, Action.createAnimationAction(this, 0, world, imageStore), getAnimationPeriod());
    }

    public abstract double getAnimationPeriod();

    public Point nextPositionDude(WorldModel world, Point destPos) {
        int horiz = Integer.signum(destPos.x - this.position.x);
        Point newPos = new Point(this.position.x + horiz, this.position.y);

        if (horiz == 0 || world.isOccupied(newPos) && !world.getOccupancyCell(newPos).getClass().getSimpleName().equals("Stump")) {
            int vert = Integer.signum(destPos.y - this.position.y);
            newPos = new Point(this.position.x, this.position.y + vert);

            if (vert == 0 || world.isOccupied(newPos) && !world.getOccupancyCell(newPos).getClass().getSimpleName().equals("Stump")) {
                newPos = this.position;
            }
        }

        return newPos;
    }

}
