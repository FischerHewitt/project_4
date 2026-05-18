import processing.core.PImage;
import java.util.List;
/**
 Developers: Fischer Hewitt, Ayaan Kazerouni, Paris Kalathas
 Date: 05/14/2026
 Project 4: Cohesion
 Description: This project refactors a virtual world that has several different entities that perform different tasks
 and interact with each other. Trees can get chopped down, saplings can grow, faries search for stumps and make them grow,
 dudes move around collecting resources from plants. The world runs automatically and the entities interact with each other.


 * Represents an entity that has animation behavior.
 * Animated entities have an animation period and can schedule animation-related events.
 */
public abstract class AnimatedEntity extends Entity{
        // Instance variables
        protected final double animationPeriod;
        /**
         * Creates a new AnimatedEntity
         * @param id The id of the new entity.
         * @param position The position (x,y coordinate) of this new entity.
         * @param images The image list associated with this entity.
         * @param animationPeriod The animationPeriod (i.e., how long it takes to perform one animation).
         *                        Not all entities need this.
         * Purpose: Constructs a new Animated Entity
         * Input: String id, Point position, List<PImage> images, double animationPeriod.
         * Result: Initializes the shared Entity fields and stores the animation period.
         * Output: No return value because this is a constructor.
         */
        public AnimatedEntity(String id, Point position, List<PImage> images, double animationPeriod) {
            super(id, position, images);
            this.animationPeriod = animationPeriod;
        }
        /*
         Purpose: Schedules this animated entity's future events.
         Input: EventScheduler scheduler, WorldModel world, ImageStore imageStore.
         Result: Subclasses decide which events to schedule, such as animation events or activity events.
         Output: void.
         */
        public abstract void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore);

        /*
        Purpose: gets this entity's animation period
        Input: none
        Result: returns the animationPeriod
        Output: double
         */
        public double getAnimationPeriod(){
            return this.animationPeriod;
        }

}
