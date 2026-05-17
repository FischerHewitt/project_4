import processing.core.PImage;

import java.util.List;

/**
 * @param resourceLimit The resourceLimit for this entity. Not all entities need this.
 * @param resourceCount The resourceCount for this entity. Not all entities need this.
 * */
public abstract class Dude extends ActiveEntity{
    public static final String DUDE_KEY = "dude";
    public static final int DUDE_ACTION_PERIOD_IDX = 0;
    public static final int DUDE_ANIMATION_PERIOD_IDX = 1;
    public static final int DUDE_RESOURCE_LIMIT_IDX = 2;
    public static final int DUDE_NUM_PROPERTIES = 3;

    protected final int resourceLimit;
    protected int resourceCount;

    public Dude(String id, Point position, List<PImage> images, double animationPeriod, int resourceLimit, int resourceCount, double actionPeriod) {
        super(id, position, images, animationPeriod, actionPeriod);
        this.resourceLimit = resourceLimit;
        this.resourceCount = resourceCount;
    }

    public abstract void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);

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
