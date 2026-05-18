import java.util.*;
import processing.core.PImage;
/**
 Developers: Fischer Hewitt, Ayaan Kazerouni, Paris Kalathas
 Date: 05/14/2026
 Project 4: Cohesion
 Description: This project refactors a virtual world that has several different entities that perform different tasks
 and interact with each other. Trees can get chopped down, saplings can grow, faries search for stumps and make them grow,
 dudes move around collecting resources from plants. The world runs automatically and the entities interact with each other.


 * An entity that exists in the world.
 * House
 * Stump
 * DudeFull
 * DudeNotFull
 * Fairy
 * House
 * Obstacle
 */
public abstract class Entity {
    /*
        Static variables: These do not need to made private. But you should move them to new classes if appropriate.

        Variables whose names end in "IDX" are indices, i.e., positions. For example, DUDE_NUM_PROPERTIES indicates
        that Dudes have 3 properties (in addition to their id, x position, y position). The DUDE_ACTION_PERIOD_IDX (0)
        indicates that the Dude's action period is its first property, DUDE_ANIMATION_PERIOD_IDX (1) indicates that
        the Dude's animation period is its second property, and so on.
     */

    // Instance variables
    protected final String id;
    protected Point position;
    protected final List<PImage> images;
    protected int imageIndex;


    /**
     * Creates a new Entity.
     *
     * @param id The id of the new entity.
     * @param position The position (x,y coordinate) of this new entity.
     * @param images The image list associated with this entity.
     */
    public Entity(String id, Point position, List<PImage> images) {
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;

    }
    /*
    Purpose: finds the entity in a list that is closest to a given position
    input: list<Entity> entities, Point pos
    result: if the list is empty, return optional, else, compare the distance from each entity and keep track of the closest one
    output: Optional.empty(), Optional.of(nearest)
     */
    public static Optional<Entity> nearestEntity(List<Entity> entities, Point pos) {
        if (entities.isEmpty()) {
            return Optional.empty();
        } else {
            Entity nearest = entities.getFirst();
            int nearestDistance = nearest.position.distanceSquared(pos);

            for (Entity other : entities) {
                int otherDistance = other.position.distanceSquared(pos);

                if (otherDistance < nearestDistance) {
                    nearest = other;
                    nearestDistance = otherDistance;
                }
            }

            return Optional.of(nearest);
        }
    }
    /*
    purpose: get the entity's id
    Input: none
    Result: returns the entity ID
    output: string
     */
    public String getId() {
        return id;
    }
    /*
    Purpose: get the entity current position
    Input: None
    Result: returns the position
    output: Point
     */
    public Point getPosition() {
        return position;
    }
    /*
    purpose: changes the entity's position
    Input: Point position
    result: set the entity position to the new position
    output: void
     */
    public void setPosition(Point position) {
        this.position = position;
    }
    /*
    Purpose: gets the image that should be currently displayed for the entity
    Input: None
    result: use the current image index to choose the get the correct animation image
    output: PIMage
     */
    public PImage getCurrentImage(){
        return this.images.get(this.imageIndex % this.images.size());
    }
    /*
    Purpose: to set the entity's animation image to the next one
    Input: null
    result: increases the image index by 1
    output: void
     */
    public void nextImage() {
        this.imageIndex = this.imageIndex + 1;
    }

    /**
     * Helper method for testing. Preserve this functionality while refactoring.
     * Purpose: creates a string representation of the entity
     * Input: Null
     * Result: if the entity id is empty, it returns null, otherwise it creates a string containing
     * output: string, null
     */
    public String log(){
        return this.id.isEmpty() ? null :
                String.format("%s %d %d %d", this.id, this.position.x, this.position.y, this.imageIndex);
    }
}
