/**
 Developers: Fischer Hewitt, Ayaan Kazerouni, Paris Kalathas
 Date: 05/14/2026
 Project 4: Cohesion
 Description: This project refactors a virtual world that has several different entities that perform different tasks
 and interact with each other. Trees can get chopped down, saplings can grow, faries search for stumps and make them grow,
 dudes move around collecting resources from plants. The world runs automatically and the entities interact with each other.

 * An action that can be taken by a particular Entity.
 * There are two types of actions in this World:
 * - Activity actions: things like the Sapling growing up, or the DudeNotFull finding a
 *      Tree or Sapling to cut down, or the Fairy finding a Stump to turn into a Sapling.
 * - Animation actions: things like the Dude swinging his axe, or the Tree swaying, or
 *      the Fairy twinkling.
 */
public abstract class Action {
    // instance variables
     protected Entity entity;
     protected WorldModel world;
     protected ImageStore imageStore;
     protected String type;
    /**
     * Creates a new Action.
     * @param type The type of the new Action.
     * @param entity The Entity that this Action belongs to.
     * @param world The WorldModel where this Action happens.
     * @param imageStore The ImageStore that stores images for entities.
     * Purpose: Constructs a new Action object.
     * Input: String type, Entity entity, WorldModel world, ImageStore imageStore.
     * Result: creates a new action
     * Output: No return/constructor.
     */
    public Action(String type, Entity entity, WorldModel world, ImageStore imageStore ) {
        this.entity = entity;
        this.world = world;
        this.imageStore = imageStore;
        this.type = type;
    }
    /*
    Purpose: creates a new ACTIVITY action
    Input: Entity entity, WorldModel world, ImageStore imageStore
    Result: returns an ACTIVITY action for the given Entity
    Output: ACTIVITY
    */
    public static ACTIVITY createActivityAction(Entity entity, WorldModel world, ImageStore imageStore) {
        return new ACTIVITY(entity, world, imageStore);
    }
    /*
    Purpose: creates a new ANIMATION action
    Input: Entity entity, int repeatCount, WorldModel world, ImageStore imageStore
    Result: returns an ANIMATION action for the given Entity and repeat count
    Output: ANIMATION
    */
    public static ANIMATION createAnimationAction(Entity entity, int repeatCount, WorldModel world, ImageStore imageStore) {
        return new ANIMATION(entity, repeatCount, world, imageStore);
    }

    /**
     * Ask the EventScheduler to execute an animation action for this action's Entity. This entails
     * telling the Entity to cycle through its images (each animation is one step through its images).
     *
     * @param scheduler The scheduler that queues up events.
     * Purpose: executes this action
     * Input: EventScheduler scheduler
     * Result: runs the specific behavior for the action
     * Output: void
     */

    public abstract void executeAction(EventScheduler scheduler);
    /*
    Purpose: gets the Entity attached to this Action
    Input: none
    Result: returns the Entity that this Action belongs to
    Output: Entity
    */
    public Entity getEntity() {
        return entity;
    }
}
