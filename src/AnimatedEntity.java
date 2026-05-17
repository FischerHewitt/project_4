import processing.core.PImage;

import java.util.List;
import java.util.Optional;

import java.util.*;

public abstract class AnimatedEntity extends Entity{
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
        protected final double animationPeriod;

        /**
         * Creates a new Entity.
         *
         * @param id The id of the new entity.
         * @param position The position (x,y coordinate) of this new entity.
         * @param images The image list associated with this entity.
         * @param animationPeriod The animationPeriod (i.e., how long it takes to perform one animation).
         *                        Not all entities need this.
         */
        public AnimatedEntity(String id, Point position, List<PImage> images, double animationPeriod) {
            super(id, position, images);
            this.animationPeriod = animationPeriod;
        }

        public abstract void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore);

        public double getAnimationPeriod(){
            return this.animationPeriod;
        }

}
