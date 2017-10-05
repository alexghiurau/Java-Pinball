import java.awt.*;
import java.util.*;

/**
 * A pinball machine, this class uses the Canvas class in order
 * to create the machine. Its methods allows for objects to be
 * drawn to canvas.
 * 
 * @author Alexandru Ghiurau UP786633
 * @version 24.03.2017
 */
public class Machine
{
    private Canvas machine;
    private int topEdge = 0;
    private int leftEdge = 0;
    private int bottomEdge;
    private int rightEdge;
    private int lengthToGap;        // the distance between the edge of the machine and the start of the gap
    private int gapWidth = 70;
    private boolean state;
    private String totalPoints;
    private int calculatePointsT = 0;
    private int calculatePointsZ = 0;
    private ArrayList<PinballThree> pintList;
    private ArrayList<PinballZero> pinzList;
    private ArrayList<BumperObject> bumpList;
    private ArrayList<HoleObject> holeList;
    
    /**
     * Constructor for the Machine class
     * 
     * Create a machine with default name and size
     */
    public Machine()
    {
        machine = new Canvas("Pinball Demo", 600, 500);
        rightEdge = 600;
        bottomEdge = 500;
        lengthToGap = (rightEdge / 2) - gapWidth;
        drawMachine();
        state = true;
        pintList = new ArrayList<PinballThree>();
        pinzList = new ArrayList<PinballZero>();
        bumpList = new ArrayList<BumperObject>();
        holeList = new ArrayList<HoleObject>();
    }
    
    /**
     *  Constructor for the Machine class
     *  
     *  Create a machine with given name and size
     *  @param name The name to give the machine
     *  @param rightEdge The maximum x coordinate
     *  @param bottomEdge The maximum y coordinate
     */
     public Machine(String name, int rightEdge, int bottomEdge)
    {
        machine = new Canvas(name, rightEdge, bottomEdge);
        this.rightEdge = rightEdge;
        this.bottomEdge = bottomEdge;
        lengthToGap = (rightEdge / 2) - gapWidth;
        drawMachine();
    }

    /**
     * Erase a PinballObject from the view of the pinball machine
     * 
     * @param pinballObj The object to be erased
     */
    public void erase(PinballObject pinballObj)
    {
        machine.eraseCircle(pinballObj.getXPosition() - pinballObj.getRadius(), pinballObj.getYPosition()- pinballObj.getRadius(), pinballObj.getDiameter());
    }
    
    /**
    * Draw an PinballObject at its current position onto the view of the pinball machine
    * 
    * @param pinballObj The object to be drawn
    */
    public void draw(PinballObject pinballObj)
    {
        machine.setForegroundColor(pinballObj.getColor());
        machine.fillCircle(pinballObj.getXPosition() - pinballObj.getRadius(), pinballObj.getYPosition() - pinballObj.getRadius(), pinballObj.getDiameter());
    }
    
    /**
    * Draw the edge of the pinball machine 
    */
    public void drawMachine()
    {
        machine.setForegroundColor(Color.DARK_GRAY);
        
        machine.fillRectangle(0, 0, rightEdge, 10);  // top edge
        machine.fillRectangle(0, 0, 10, bottomEdge); // left edge
        machine.fillRectangle(rightEdge - 10, 0, 10, bottomEdge); // right edge
        
        machine.fillRectangle(0, bottomEdge - 10, lengthToGap, 10); // left-hand side of bottom edge
        machine.fillRectangle(rightEdge - lengthToGap, bottomEdge - 10, rightEdge, 10);     // right-hand side of bottom edge
    }
    
    /**
     * Draw the number of points each PinballObject has
     * 
     * @param pinballObj  The object for which the points account for
     */
    public void drawPoints(PinballObject pinballObj)
    {
        machine.setForegroundColor(Color.BLACK);
        machine.drawString(pinballObj.getPointsString(), pinballObj.getXPosition() - 5, pinballObj.getYPosition() + 4);
    }
    
    /**
     * Draw the total number of points on the machine 
     * inside a light gray rectangle
     */
    public void drawTotalPoints()
    {
        machine.setForegroundColor(Color.LIGHT_GRAY);
        machine.fillRectangle(240,30,120,30);
        machine.setForegroundColor(Color.BLACK);
        machine.drawString("FINAL SCORE: " + this.calculateTotalPoints(), 250, 50);
    }
    
    /**
     * Return the edge of the left-hand wall
     * @return      Integer value representing the left-hand position of the wall
     */
    public int getLeftWall()
    {
        return leftEdge + 10;
    }
    
    /**
     * Return the edge of the right-hand wall
     * @return      Integer value representing the right-hand position of the wall
     */
    public int getRightWall()
    {
        return rightEdge - 10;
    }
    
    /**
     * Return the edge of the top wall
     * @return      Integer value representing the top position of the wall
     */
    public int getTopWall()
    {
        return topEdge + 10;
    }
    
    /**
     * Return the edge of the bottom wall
     * @return      Integer value representing the bottom position of the wall
     */
    public int getBottomWall()
    {
        return bottomEdge - 10;
    }
    
    /**
     * Return the edge of the bottom-left part of the wall
     * @return      Integer value representing the bottom-left position of the wall
     */
    public int getBottomLeftWall()
    {
        return (rightEdge / 2) - gapWidth;
    }
    
    /**
     * Return the edge of the bottom-right part of the wall
     * @return      Integer value representing the bottom-right position of the wall
     */
    public int getBottomRightWall()
    {
        return (rightEdge / 2) + gapWidth;
    }
    
    /**
     * Returns the width of the gap for the pinball machine
     * @return      Integer value of the gap width
     */
    public int getGapWidth()
    {
        return gapWidth;
    }
    
    /**
     * Introduces a small delay in ball movement, for smooth running
     */
    public void pauseMachine()
    {
        machine.wait(50);
    }
    
    /**
     * Resets the machine back to initial view, with no pinballs
     */
    public void resetMachine()
    {
        machine.erase();
        drawMachine();
    }
    
    /**
     * Starts the demo by resetting the machine, creating the
     * PinballObject objects, adding them to ArrayLists and
     * moving them using a while loop which depends on the
     * state variable in this class.
     */
    public void startDemo()
    {
        this.resetMachine();
        PinballThree pint1 = new PinballThree(340, 150, 4, 4, 20, this);
        pintList.add(pint1);
        PinballThree pint2 = new PinballThree(270, 230, -4, -4, 20, this);
        pintList.add(pint2);
        PinballThree pint3 = new PinballThree(410, 260, 4, 4, 20, this);
        pintList.add(pint3);
        PinballZero pinz1 = new PinballZero(80, 170, 4, 4, 20, this);
        pinzList.add(pinz1);
        PinballZero pinz2 = new PinballZero(170, 260, -4, -4, 20, this);
        pinzList.add(pinz2);
        PinballZero pinz3 = new PinballZero(530, 350, 4, 4, 20, this);
        pinzList.add(pinz3);
        BumperObject bump1 = new BumperObject(150, 380, 20, this);
        bumpList.add(bump1);
        BumperObject bump2 = new BumperObject(450, 130, 30, this);
        bumpList.add(bump2);
        HoleObject hole1 = new HoleObject(500, 380, 30, this);
        holeList.add(hole1);
        HoleObject hole2 = new HoleObject(150, 100, 20, this);
        holeList.add(hole2);
        
        while (state)
        {
            this.pauseMachine();
            pint1.collisionTest(pintList, pinzList, bumpList, holeList);
            pint1.move();
            pint2.collisionTest(pintList, pinzList, bumpList, holeList);
            pint2.move();
            pint3.collisionTest(pintList, pinzList, bumpList, holeList);
            pint3.move();
            pinz1.collisionTest(pintList, pinzList, bumpList, holeList);
            pinz1.move();
            pinz2.collisionTest(pintList, pinzList, bumpList, holeList);
            pinz2.move();
            pinz3.collisionTest(pintList, pinzList, bumpList, holeList);
            pinz3.move();
            bump1.move();
            bump2.move();
            hole1.move();
            hole2.move();
        }
    }
    
    /**
     * Changes the state of the pinball session to false which
     * stop the while loop from moving PinballObject objects
     */
    public void setState()
    {
        state = false;
    }
    
    /**
     * Calculates and returns the number of points obtained by
     * PinballThree objects
     * @return      number of points obtained by PinballThree objects
     */
    public int calculateTotalPointsT()
    {
        for (PinballThree pint : pintList)
        {
            calculatePointsT = calculatePointsT + pint.getPoints();
        }
        return calculatePointsT;
    }
    
    /**
     * Calculates and returns the number of points obtained by
     * PinballZero objects
     * @return      number of points obtained by PinballZero objects
     */
    public int calculateTotalPointsZ()
    {   for (PinballZero pinz : pinzList)
        {
            calculatePointsZ = calculatePointsZ + pinz.getPoints();
        }
        return calculatePointsZ;
    }   
    
    /**
     * Calculates and returns the total amount of points obrained
     * by all PinballObject objects
     * @return      String representing the total number of points
     */
    public String calculateTotalPoints()
    {
        int tot = calculatePointsT + calculatePointsZ;
        totalPoints = String.format("%d", tot);
        return totalPoints;
    }
}