public class ACTIVITY extends Action{

    /**
     * An action that can be taken by a particular Entity.
     * There are two types of actions in this World:
     * - Activity actions: things like the Sapling growing up, or the DudeNotFull finding a
     *      Tree or Sapling to cut down, or the Fairy finding a Stump to turn into a Sapling.
     * - Animation actions: things like the Dude swinging his axe, or the Tree swaying, or
     *      the Fairy twinkling.
     */

        private final WorldModel world;
        private final ImageStore imageStore;

        public ACTIVITY(Entity entity, WorldModel world, ImageStore imageStore) {
            super("ACTIVITY", entity, world, imageStore);
            this.world = world;
            this.imageStore = imageStore;
        }


    /**
     * Ask the EventScheduler to execute an activity action for this action's Entity.
     * This entails telling the Entity to execute its activity.
     *
     * @param scheduler The scheduler that queues up events.
     */
    // fix later: should be able to combine
    public void executeAction(EventScheduler scheduler) {
        if (this.entity instanceof AnimatedEntity animatedEntity){
            animatedEntity.executeActivity(this.world, this.imageStore, scheduler);
        }
    }




}
