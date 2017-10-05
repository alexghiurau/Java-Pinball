import java.awt.*;
import java.util.*;

/**
 * PinballThree, a subclass of PinballObject. This object will
 * move and bounce off other objects which will trigger its 
 * direction and speed to change.
 * 
 * Movement can be initiated by repeated calls to the "move" method.
 * 
 * @author Alexandru Ghiurau UP786633
 * @version 24.03.2017
 */

public class PinballThree extends PinballObject
{
    /**
     * Constructor for objects of class PinballThree
     * 
     * @param xPos  the horizontal coordinate of the 
     * @param yPos  the vertical coordinate of the object
     * @param xVel  the horizontal speed of the object
     * @param yVel  the vertical speed of the object
     * @param objectRadius  the radius (in pixels) of the object
     * @param theMachine  the machine this object is in
     */
    public PinballThree(int xPos, int yPos, int xVel, int yVel, int objectRadius, Machine theMachine)
    {
        super(xPos, yPos, xVel, yVel, Color.RED, objectRadius, theMachine);
    }
    
    /**
     * Move this object according to its position and speed and redraw.
     **/
    public void move()
    {   
        // remove from universe at the current position
        machine.erase(this);
        
        // compute new position
        currentYLocation += speedYTravel;
        currentXLocation += speedXTravel;
        
        // check if it has hit the left wall
        if(currentXLocation <= (leftWallPosition + radius)) 
        {
            currentXLocation = leftWallPosition + radius;
            points += 1;
            speedXTravel = -speedXTravel;
            speedYTravel = -speedYTravel;
        }
        // check if it has hit the right wall
        else if(currentXLocation >= (rightWallPosition - radius)) 
        {
            currentXLocation = rightWallPosition - radius;
            points += 1;
            speedXTravel = -speedXTravel;
            speedYTravel = -speedYTravel;
        }
        //check if it has hit the top wall
        else if(currentYLocation <= (topWallPosition + radius))
        {
            currentYLocation = topWallPosition + radius;
            points += 1;
            speedYTravel = -speedYTravel;
            speedXTravel = -speedXTravel;
        }
        // check if it has hit the bottom wall
        else if (currentYLocation >= (bottomWallPosition - radius))
        {
            if (currentXLocation >= bottomLeftWallPosition && currentXLocation <= bottomRightWallPosition)
            {
                machine.setState();
                machine.calculateTotalPointsT();
                machine.calculateTotalPointsZ();
                machine.drawTotalPoints();
                machine.erase(this);
            }
            else
            {
                currentYLocation = bottomWallPosition - radius;
                points += 1;
                speedYTravel = -speedYTravel;
                speedXTravel = -speedXTravel;
            }
        }
        
        // draw again at new position
        machine.draw(this);
        machine.drawPoints(this);
    }
    
    /**
     * This method tests wether an object has collided with
     * another object such as a pinball, bumper or hole.
     * Depending on the type of object, the pinball will have
     * some of its properties changed.
     * 
     * @param pinballsThree  These are the PinballThree objects taken from their ArrayList
     * @param pinballsZero  These are the PinballThree objects taken from their ArrayList
     * @param bumpers  These are the BumperObject objects taken from their ArrayList
     * @param holes  These are the HoleObject objects taken from their ArrayList
     */
    public void collisionTest(ArrayList<PinballThree> pinballsThree, ArrayList<PinballZero> pinballsZero, ArrayList<BumperObject> bumpers, ArrayList<HoleObject> holes)
    {
        for (PinballThree other : pinballsThree)
        {
            if (currentXLocation != other.currentXLocation && currentYLocation != other.currentYLocation)
            {
                int diffX = currentXLocation - other.currentXLocation;
                int diffY = currentYLocation - other.currentYLocation;
                double sqrd = Math.pow(diffX, 2) + Math.pow(diffY, 2);
                double diff = Math.sqrt(sqrd);
            
                if (diff <= (radius + other.radius))
                {
                    points += 5;
                    machine.drawPoints(this);
                    speedXTravel = speedXTravel + 1;
                    speedXTravel = -speedXTravel;
                    speedYTravel = -speedYTravel;
                }
            }
        }
        
        for (PinballZero other : pinballsZero)
        {
            if (currentXLocation != other.currentXLocation && currentYLocation != other.currentYLocation)
            {
                int diffX = currentXLocation - other.currentXLocation;
                int diffY = currentYLocation - other.currentYLocation;
                double sqrd = Math.pow(diffX, 2) + Math.pow(diffY, 2);
                double diff = Math.sqrt(sqrd);
            
                if (diff <= (radius + other.radius))
                {
                    points += 5;
                    machine.drawPoints(this);
                    speedXTravel = speedXTravel + 1;
                    speedXTravel = -speedXTravel;
                    speedYTravel = -speedYTravel;
                }
            }
        }
        
        for (BumperObject other : bumpers)
        {
            if (currentXLocation != other.currentXLocation && currentYLocation != other.currentYLocation)
            {
                int diffX = currentXLocation - other.currentXLocation;
                int diffY = currentYLocation - other.currentYLocation;
                double sqrd = Math.pow(diffX, 2) + Math.pow(diffY, 2);
                double diff = Math.sqrt(sqrd);
            
                if (diff <= (radius + other.radius))
                {
                    points += 2;
                    machine.drawPoints(this);
                    speedXTravel = -speedXTravel;
                    speedYTravel = -speedYTravel;
                }
            }
        }
        
        for (HoleObject other : holes)
        {
            if (currentXLocation != other.currentXLocation && currentYLocation != other.currentYLocation)
            {
                int diffX = currentXLocation - other.currentXLocation;
                int diffY = currentYLocation - other.currentYLocation;
                double sqrd = Math.pow(diffX, 2) + Math.pow(diffY, 2);
                double diff = Math.sqrt(sqrd);
            
                if (diff <= (radius + other.radius))
                {
                    points -= points;
                    machine.drawPoints(this);
                }
            }
        }
    }
}