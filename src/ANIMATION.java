/**
 Developers: Fischer Hewitt, Ayaan Kazerouni, Paris Kalathas
 Date: 05/14/2026
 Project 4: Cohesion
 Description: This project refactors a virtual world that has several different entities that perform different tasks
 and interact with each other. Trees can get chopped down, saplings can grow, faries search for stumps and make them grow,
 dudes move around collecting resources from plants. The world runs automatically and the entities interact with each other.


 * Represents an animation action.
 * ANIMATION causes an Entity to switch to its next image.
 * If the animation has more repeats left, it schedules another animation action.
 * - Animation actions: things like the Dude swinging his axe, or the Tree swaying, or
 *     the Fairy twinkling.
 */
public class ANIMATION extends Action {
    // instance variables
    private final int repeatCount;
    /**
     * Creates a new ANIMATION action.
     * @param entity The Entity that will be animated.
     * @param repeatCount The number of times this animation should repeat.
     * @param world The WorldModel where the animation happens.
     * @param imageStore The ImageStore that stores the images for entities.
     * Purpose: Constructs a new ANIMATION object.
     * Input: Entity entity, int repeatCount, WorldModel world, ImageStore imageStore.
     * Result: Creates an animation action for the given Entity and stores its repeat count.
     * Output: No return/constructor.
     */
    public ANIMATION(Entity entity, int repeatCount, WorldModel world, ImageStore imageStore) {
        super("ANIMATION", entity, world, imageStore);
        this.repeatCount = repeatCount;
    }
    /**
     * Ask the EventScheduler to execute an animation action for this action's Entity. This entails
     * telling the Entity to cycle through its images (each animation is one step through its images).
     *
     * @param scheduler The scheduler that queues up events.
     * Purpose: executes this animation action
     * Input: EventScheduler scheduler
     * Result: moves the Entity to its next image and schedules another animation if repeats remain
     * Output: void
     */
    public void executeAction(EventScheduler scheduler) {
        this.entity.nextImage();

        if (this.repeatCount != 1 && this.entity instanceof AnimatedEntity animatedEntity) {
            scheduler.scheduleEvent(this.entity, createAnimationAction(this.entity, Math.max(this.repeatCount - 1, 0), this.world, this.imageStore), animatedEntity.getAnimationPeriod());
        }
    }
}
