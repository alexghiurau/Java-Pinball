import java.awt.*;
import java.util.*;

/**
 * Class to demonstrate functionality of the Pinball machine
 * 
 * @author Alexandru Ghiurau UP786633 
 * @version 24.03.2017
 */
public class Demo
{
    private Machine machine;
    
    /**
     * Constructor for objects of class Demo 
     * This initiates the machine
     */
    public Demo()
    {
        machine = new Machine();
    }
    
    /**
     * Starts the demo for this project.
     */
    public void startDemo()
    {
        machine.startDemo();
    }
}
