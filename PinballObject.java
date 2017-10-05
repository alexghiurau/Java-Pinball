import java.awt.*;
import java.util.*;

/**
 * An object that exists in the pinball. The object can either be static or
 * move and bounce by hitting other objects. This is the main superclass that
 * has the HoleObject, BumperObject, PinballThree and PinballZero classes as 
 * subclasses.
 * 
 * Movement can be initiated by repeated calls to the "move" method.
 * 
 * @author Alexandru Ghiurau UP786633   
 * @version 24.03.2017
 * 
 */

public class PinballObject
{
    protected int currentXLocation;
    protected int currentYLocation;
    protected int speedXTravel;
    protected int speedYTravel;
    private Color colour;
    protected int radius;
    protected Machine machine;
    protected final int leftWallPosition;
    protected final int rightWallPosition;
    protected final int topWallPosition;
    protected final int bottomWallPosition;
    protected final int bottomLeftWallPosition;
    protected final int bottomRightWallPosition;
    protected final int gapWidth;
    protected ArrayList<Color> colorList;
    protected int points;

    /**
     * Constructor for objects of class BumperObject
     * 
     * @param xPos  the horizontal coordinate of the object
     * @param yPos  the vertical coordinate of the object
     * @param xVel  the horizontal speed of the object
     * @param yVel  the vertical speed of the object
     * @param objectRadius  the radius (in pixels) of the object
     * @param objectColor  the color of the object
     * @param theMachine  the machine this object is in
     */
    public PinballObject(int xPos, int yPos, int xVel, int yVel, Color objectColor, int objectRadius, Machine theMachine)
    {
        currentXLocation = xPos;
        currentYLocation = yPos;
        speedXTravel = xVel;
        speedYTravel = yVel;
        colour = objectColor;
        radius = objectRadius;
        machine = theMachine;
        leftWallPosition = machine.getLeftWall();
        rightWallPosition = machine.getRightWall();
        topWallPosition = machine.getTopWall();
        bottomWallPosition = machine.getBottomWall();
        bottomLeftWallPosition = machine.getBottomLeftWall();
        bottomRightWallPosition = machine.getBottomRightWall();
        gapWidth = machine.getGapWidth();
        colorList = new ArrayList<Color>();
        colorList.add(Color.CYAN);
        colorList.add(Color.MAGENTA);
        colorList.add(Color.YELLOW);
        points = 0;
    }


    /**
     * Move this object according to its position and speed and redraw.
     **/
    public void move()
    {
        // randomizer for the changing colour functionality
        Random rand = new Random();
        
        // remove from universe at the current position
        machine.erase(this);
        
        // compute new position
        currentYLocation += speedYTravel;
        currentXLocation += speedXTravel;
        
        // check if it has hit the left wall
        if(currentXLocation <= (leftWallPosition + radius)) 
        {
            currentXLocation = leftWallPosition + radius;
            speedXTravel = -speedXTravel;
        }
        // check if it has hit the right wall
        else if(currentXLocation >= (rightWallPosition - radius)) 
        {
            currentXLocation = rightWallPosition - radius;
            speedXTravel = -speedXTravel;
        }
        //check if it has hit the top wall
        else if(currentYLocation <= (topWallPosition + radius))
        {
            currentYLocation = topWallPosition + radius;
            speedYTravel = -speedYTravel;
        }
        // check if it has hit the bottom wall
        else if (currentYLocation >= (bottomWallPosition - radius))
        {
            if (currentXLocation >= bottomLeftWallPosition && currentXLocation <= bottomRightWallPosition)
            {
                //do stuff
            }
            else
            {
                currentYLocation = bottomWallPosition - radius;
                speedYTravel = -speedYTravel;
            }
        }

        // draw again at new position
        machine.draw(this);
    }
    
    /**
     * Return the horizontal position of this object
     * @return      Integer value of x-axis position of this object
     */
    public int getXPosition()
    {
        return currentXLocation;
    }
    
    /**
     * Return the vertical position of this object
     * @return      Integer value of y-axis position of this object
     */
    public int getYPosition()
    {
        return currentYLocation;
    }
    
    /**
     * Return the radius of this object
     * @return      Integer value of the radius of this object
     */
    public int getRadius()
    {
        return radius;
    }
    
    /**
     * Return the diameter of this object
     * @return      Integer value the diameter of this object
     */
    public int getDiameter()
    {
        return 2*radius;
    }
    
    /**
     * Return the colour of this object
     * @return      Color value representing the colour of this object
     */
    public Color getColor()
    {
        return colour;
    }
    
    /**
     * Sets the colour for a PinballObject
     * @param newColor  the color to be set on the object
     */
    public void setColor(Color newColor)
    {
        colour = newColor;
    }
    
    /**
     * Returns the number of points as an integer
     * @return      Integer value of the number of points of this object
     */
    public int getPoints()
    {
        return points;
    }
    
    /**
     * Returns the number of points as a String
     * @return      String value of the number of points of this object
     */
    public String getPointsString()
    {
        points = points;
        String pointsString = String.format("%d", points);
        return pointsString;
    }
}
