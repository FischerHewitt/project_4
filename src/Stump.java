import processing.core.PImage;
import java.util.List;
/*
 Developers: Fischer Hewitt, Ayaan Kazerouni, Paris Kalathas
 Date: 05/14/2026
 Project 4: Cohesion
 Description: This project refactors a virtual world that has several different entities that perform different tasks
 and interact with each other. Trees can get chopped down, saplings can grow, faries search for stumps and make them grow,
 dudes move around collecting resources from plants. The world runs automatically and the entities interact with each other.

 Represents a Stump entity.
 Stump is created when a Tree or Sapling loses all of its health.
 Fairy searches for Stumps and replaces them with Saplings.
 */
public class Stump extends Entity{
    // instance Variables
    public static final String STUMP_KEY = "stump";
    public static final int STUMP_NUM_PROPERTIES = 0;

    /**
     * Creates a new Stump.
     * @param id The new Stump's id.
     * @param position The Stump's x,y position in the world.
     * @param images Images to use for the Stump.
     * @return a new Entity whose type is Stump.
     * * Purpose: creates a new stump
     * Input: String id, Point position, List<PImage> images
     * Result: builds and returns a new stump at the given position with the given images
     * Return: stump
     */
    public static Entity createStump(String id, Point position, List<PImage> images) {
        return new Stump(id, position, images);
    }
    /*
    Purpose: construct a new Stump Object
    Inputs: String id, Point position, List<PImage> images
    Result: creates a new Stump by Calling the Entity constructor to initialize the shared Entity fields
    Output: void
     */
    public Stump(String id, Point position, List<PImage> images) {
        super(id, position, images);
    }

}
