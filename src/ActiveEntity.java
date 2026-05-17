import processing.core.PImage;

import java.util.List;
import processing.core.PImage;

import java.util.List;
import java.util.Optional;

import java.util.*;
public abstract class ActiveEntity extends AnimatedEntity{
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
        protected final int resourceLimit;
        protected int resourceCount;
        protected final double actionPeriod;
        protected int health;
        protected final int healthLimit;

        /**
         * Creates a new Entity.
         *
         * @param resourceLimit The resourceLimit for this entity. Not all entities need this.
         * @param resourceCount The resourceCount for this entity. Not all entities need this.
         * @param actionPeriod The actionPeriod for this entity (i.e., how long it takes to perform each activity action).
         *                     Not all entities need this.
         * @param animationPeriod The animationPeriod (i.e., how long it takes to perform one animation).
         *                        Not all entities need this.
         * @param health The entity's starting health. Not all entities need this.
         * @param healthLimit The entity's upper health limit. Not all entities need this.
         */
        public ActiveEntity(String id, Point position, List<PImage> images, double animationPeriod, int resourceLimit,
                              int resourceCount, double actionPeriod, int health, int healthLimit) {
            super(id, position, images, animationPeriod);
            this.resourceLimit = resourceLimit;
            this.resourceCount = resourceCount;
            this.actionPeriod = actionPeriod;
            this.health = health;
            this.healthLimit = healthLimit;
        }

        public PImage getCurrentImage(){
            return this.images.get(this.imageIndex % this.images.size());
        }

        public void nextImage() {
            this.imageIndex = this.imageIndex + 1;
        }

        public abstract void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);

        public abstract void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore);

        public int getHealth() {
            return health;
        }
}
