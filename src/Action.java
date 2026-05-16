/**
 * An action that can be taken by a particular Entity.
 * There are two types of actions in this World:
 * - Activity actions: things like the Sapling growing up, or the DudeNotFull finding a
 *      Tree or Sapling to cut down, or the Fairy finding a Stump to turn into a Sapling.
 * - Animation actions: things like the Dude swinging his axe, or the Tree swaying, or
 *      the Fairy twinkling.
 */
public abstract class Action {
     protected Entity entity;
     protected WorldModel world;
     protected ImageStore imageStore;
     protected String type;

//    public Entity getEntity() {
//        return entity;
//    }

    public Action(){}


    public Action(String type, Entity entity, WorldModel world, ImageStore imageStore ) {
        this.entity = entity;
        this.world = world;
        this.imageStore = imageStore;
        this.type = type;
    }

    public static ACTIVITY createActivityAction(Entity entity, WorldModel world, ImageStore imageStore) {
        return new ACTIVITY(entity, world, imageStore);
    }

    public static ANIMATION createAnimationAction(Entity entity, int repeatCount, WorldModel world, ImageStore imageStore) {
        return new ANIMATION(entity, repeatCount, world, imageStore);
    }

    /**
     * Ask the EventScheduler to execute an animation action for this action's Entity. This entails
     * telling the Entity to cycle through its images (each animation is one step through its images).
     *
     * @param scheduler The scheduler that queues up events.
     */

    public abstract void executeAction(EventScheduler scheduler);
}
