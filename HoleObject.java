import java.awt.*;
import java.util.*;

/**
 * HoleObject, a subclass of PinballObject. This is a hole object 
 * that does not move and is of static color.
 * 
 * @author Alexandru Ghiurau UP786633   
 * @version 24.03.2017
 * 
 */

public class HoleObject extends PinballObject
{
    /**
     * Constructor for objects of class HoleObject
     * 
     * @param xPos  the horizontal coordinate of the 
     * @param yPos  the vertical coordinate of the object
     * @param objectRadius  the radius (in pixels) of the object
     * @param theMachine  the machine this object is in
     */
    public HoleObject(int xPos, int yPos, int objectRadius, Machine theMachine)
    {
        super(xPos, yPos, 0, 0, Color.BLACK, objectRadius, theMachine);
    }
    
    /**
     * This method does not actually move the opbject but rather make
     * it display on the screen and prevents jagged edges due to 
     * collision. The objects are static.
     */
    public void move()
    {
         // remove from universe at the current position
        machine.erase(this);

        // draw again at new position
        machine.draw(this);
    
    }
}