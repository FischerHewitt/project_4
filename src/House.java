import processing.core.PImage;
import java.util.List;
/*
 Developers: Fischer Hewitt, Ayaan Kazerouni, Paris Kalathas
 Date: 05/14/2026
 Project 4: Cohesion
 Description: This project refactors a virtual world that has several different entities that perform different tasks
 and interact with each other. Trees can get chopped down, saplings can grow, faries search for stumps and make them grow,
 dudes move around collecting resources from plants. The world runs automatically and the entities interact with each other.

 Represents a House entity.
 House is a stationary entity that DudeFull searches for
 when dropping off collected resources.
 */
public class House extends Entity{
    //instance variables
    public static final String HOUSE_KEY = "house";
    public static final int HOUSE_NUM_PROPERTIES = 0;
    /**
     * Creates a new House.
     * @param id The new House's id.
     * @param position The new House's position (x,y coordinate) in the World.
     * @param images Images to use for House.
     * @return a new Entity whose type is House.
     * Purpose: creates a new house
     * Input: String id, Point position, List<PImage> images
     * Result: builds and returns a new house at the given position with the given images
     * Return: house
     */
    public static Entity createHouse(String id, Point position, List<PImage> images) {
        return new House(id, position, images);
    }

    /*
    Purpose: construct a new House Object
    Inputs: String id, Point position, List<PImage> images
    Result: creates a new house by Calling the Entity constructor to initialize the shared Entity fields
    Output: void
     */
    public House(String id, Point position, List<PImage> images) {
        super(id, position, images);
    }

}
