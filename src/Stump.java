import processing.core.PImage;

import java.util.List;

public class Stump extends Entity{
    public static final String STUMP_KEY = "stump";
    public static final int STUMP_NUM_PROPERTIES = 0;

    /**
     * Creates a new Stump.
     * @param id The new Stump's id.
     * @param position The Stump's x,y position in the world.
     * @param images Images to use for the Stump.
     * @return a new Entity whose type is Stump.
     */
    public static Entity createStump(String id, Point position, List<PImage> images) {
        return new Stump(id, position, images);
    }

    public Stump(String id, Point position, List<PImage> images) {
        super(id, position, images);
    }

}
