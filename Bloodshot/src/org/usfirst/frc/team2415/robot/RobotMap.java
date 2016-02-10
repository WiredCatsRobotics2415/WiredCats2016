package org.usfirst.frc.team2415.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static final int LEFT_TALON_ZERO = 4;
    public static final int LEFT_TALON_ONE = 3;
    public static final int RIGHT_TALON_ZERO = 1;
    public static final int RIGHT_TALON_ONE = 2;
    
    public static final int ROTATE_INTAKE_TALON = 5;
    public static final int SPIN_INTAKE_TALON = 6;
    
    public static final int[] RIGHT_ENCODER = {0,1};
    public static final int[] LEFT_ENCODER = {2,3};
    
    public static final int[] INTAKE_ENCODER = {4,5};
    public static final int INTAKE_STOPPER = 6;
    
    public static final int INTAKE_SOLENOID = 666;
    public static final int OUTTAKE_SOLENOID = 666666;
    
    public static final int SPIN_INTAKE_TALON2 = 666666666;
    
    /* TODO: make 3 integers that are public static and final:
     * 		 - 2 that correspond to the ports on the PCM (pneumatic control module) 
     * 		   that the solenoids are hooked up to
     * 		 - 1 that corresponds to the port the spin motor is hooked up to
     * hint 1: if you have any questions about the port numbers, 
     * 		   ask Lara, she'll help you out
     */
    
    public static final int PCM_ID = 20;
    
}
