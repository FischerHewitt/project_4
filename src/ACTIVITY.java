/**
 Developers: Fischer Hewitt, Ayaan Kazerouni, Paris Kalathas
 Date: 05/14/2026
 Project 4: Cohesion
 Description: This project refactors a virtual world that has several different entities that perform different tasks
 and interact with each other. Trees can get chopped down, saplings can grow, faries search for stumps and make them grow,
 dudes move around collecting resources from plants. The world runs automatically and the entities interact with each other.


 * Represents an activity action.
 * ACTIVITY tells an ActiveEntity to do the behavior,
 * such as moving, transforming, collecting resources, or growing.
 *  - Activity actions: things like the Sapling growing up, or the DudeNotFull finding a
 *      Tree or Sapling to cut down, or the Fairy finding a Stump to turn into a Sapling.
 */
public class ACTIVITY extends Action{
    /**
     * Creates a new ACTIVITY action.
     * @param entity The Entity that will perform the activity.
     * @param world The WorldModel where the activity happens.
     * @param imageStore The ImageStore that stores the images for entities.
     * Purpose: Constructs a new ACTIVITY object.
     * Input: Entity entity, WorldModel world, ImageStore imageStore.
     * Result: Creates an activity action for the given Entity.
     * Output: No return/constructor.
     */
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
     * Purpose: executes this activity action
     * Input: EventScheduler scheduler
     * Result: tells the Entity to perform its activity if it is an ActiveEntity
     * Output: void
     */
    public void executeAction(EventScheduler scheduler) {
        if (this.entity instanceof ActiveEntity activeEntity){
            activeEntity.executeActivity(this.world, this.imageStore, scheduler);
        }
    }
}
