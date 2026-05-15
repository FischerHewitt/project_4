public class ANIMATION extends Action {

    /**
     * An action that can be taken by a particular Entity.
     * There are two types of actions in this World:
     * - Activity actions: things like the Sapling growing up, or the DudeNotFull finding a
     *      Tree or Sapling to cut down, or the Fairy finding a Stump to turn into a Sapling.
     * - Animation actions: things like the Dude swinging his axe, or the Tree swaying, or
     *      the Fairy twinkling.
     */



        private final int repeatCount;

        public ANIMATION(Entity entity, int repeatCount, WorldModel world, ImageStore imageStore) {
            super("ANIMATION", entity, world, imageStore);
            this.repeatCount = repeatCount;
        }

        /**
         * Ask the EventScheduler to execute an animation action for this action's Entity. This entails
         * telling the Entity to cycle through its images (each animation is one step through its images).
         *
         * @param scheduler The scheduler that queues up events.
         */
        public void executeAction(EventScheduler scheduler) {
            this.entity.nextImage();

            if (this.repeatCount != 1) {
                scheduler.scheduleEvent(this.entity, createAnimationAction(this.entity, Math.max(this.repeatCount - 1, 0), this.world, this.imageStore), this.entity.getAnimationPeriod());
            }
        }





}
