//import processing.core.PImage;
//
//import java.util.List;
//import java.util.Optional;
//
//import java.util.*;
//
//public abstract class AdvEntity extends Entity{
//    /**
//     * Creates a new entity.
//     * @param id The new entity's id.
//     * @param position The entity's x,y position in the world.
//     * @param images Images to use for the Stump.
//     * @return a new Entity whose type is Stump.
//     */
//
//    /**
//     * An entity that exists in the world. See EntityKind for the
//     * different kinds of entities that exist.
//     */
//    /*
//        Static variables: These do not need to made private. But you should move them to new classes if appropriate.
//
//        Variables whose names end in "IDX" are indices, i.e., positions. For example, DUDE_NUM_PROPERTIES indicates
//        that Dudes have 3 properties (in addition to their id, x position, y position). The DUDE_ACTION_PERIOD_IDX (0)
//        indicates that the Dude's action period is its first property, DUDE_ANIMATION_PERIOD_IDX (1) indicates that
//        the Dude's animation period is its second property, and so on.
//     */
//
//        // The Sapling's action and animation periods have to be in sync since it grows and gains health at same time.
//
//        // Instance variables
//        protected int imageIndex;
//        protected final int resourceLimit;
//        protected int resourceCount;
//        protected final double actionPeriod;
//        protected final double animationPeriod;
//        protected int health;
//        protected final int healthLimit;
//
//        /**
//         * Creates a new Entity.
//         *
//         * @param id The id of the new entity.
//         * @param position The position (x,y coordinate) of this new entity.
//         * @param images The image list associated with this entity.
//         * @param resourceLimit The resourceLimit for this entity. Not all entities need this.
//         * @param resourceCount The resourceCount for this entity. Not all entities need this.
//         * @param actionPeriod The actionPeriod for this entity (i.e., how long it takes to perform each activity action).
//         *                     Not all entities need this.
//         * @param animationPeriod The animationPeriod (i.e., how long it takes to perform one animation).
//         *                        Not all entities need this.
//         * @param health The entity's starting health. Not all entities need this.
//         * @param healthLimit The entity's upper health limit. Not all entities need this.
//         */
//        public AdvEntity(String id, Point position, List<PImage> images, int resourceLimit,
//                      int resourceCount, double actionPeriod, double animationPeriod, int health, int healthLimit) {
//            super(id, position, images);
//            this.resourceLimit = resourceLimit;
//            this.resourceCount = resourceCount;
//            this.actionPeriod = actionPeriod;
//            this.animationPeriod = animationPeriod;
//            this.health = health;
//            this.healthLimit = healthLimit;
//        }
//
//        public PImage getCurrentImage(){
//            return this.images.get(this.imageIndex % this.images.size());
//        }
//
//        public void nextImage() {
//            this.imageIndex = this.imageIndex + 1;
//        }
//
//        public abstract void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);
//
//        public abstract void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore);
//
//        /**
//         * Helper method for testing. Preserve this functionality while refactoring.
//         */
//        public String log(){
//            return this.id.isEmpty() ? null :
//                    String.format("%s %d %d %d", this.id, this.position.x, this.position.y, this.imageIndex);
//        }
//
//        public abstract double getAnimationPeriod();
//    }
