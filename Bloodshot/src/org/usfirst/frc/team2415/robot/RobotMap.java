package org.usfirst.frc.team2415.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    /*Practice Port Values*/
//	  public static final int LEFT_TALON_ZERO = 5;
//    public static final int LEFT_TALON_ONE = 3;
//    public static final int RIGHT_TALON_ZERO = 1;
//    public static final int RIGHT_TALON_ONE = 4;
//    
//    public static final int ROTATE_INTAKE_TALON = 2;
//    public static final int SPIN_INTAKE_TALON = 6;
//    
//    public static final int CATAPULT_SOLENOID_1 = 0;
//    public static final int CATAPULT_SOLENOID_2 = 1;
//    
//    public static final int[] FIRE_SOLENOIDS = {CATAPULT_SOLENOID_1, CATAPULT_SOLENOID_2};
//    
//    public static final int[] RIGHT_ENCODER = {3,4};
//    public static final int[] LEFT_ENCODER = {5,6};
//    
//    public static final int[] INTAKE_ENCODER = {0,1};
//    public static final int INTAKE_STOPPER = 2;
//    
//    public static final int PCM_ID = 20;
	
	/*Competition Port Values*/
	public static final int LEFT_TALON_ZERO = 5;
    public static final int LEFT_TALON_ONE = 3;
    public static final int RIGHT_TALON_ZERO = 1;
    public static final int RIGHT_TALON_ONE = 6;
    
    public static final int ROTATE_INTAKE_TALON = 2;
    public static final int SPIN_INTAKE_TALON = 4;
    
    public static final int CATAPULT_SOLENOID_1 = 0;
    public static final int CATAPULT_SOLENOID_2 = 1;
    public static final int CATAPULT_SOLENOID_3 = 2;
    public static final int CATAPULT_SOLENOID_4 = 3;
    
    public static final int[] FIRE_SOLENOIDS = {CATAPULT_SOLENOID_1, CATAPULT_SOLENOID_2, 
    											CATAPULT_SOLENOID_3, CATAPULT_SOLENOID_4};
    
    public static final int[] RIGHT_ENCODER = {0,1};
    public static final int[] LEFT_ENCODER = {2,3};
    
    public static final int IR_SENSOR = 0;
    
    public static final int[] INTAKE_ENCODER = {4,5};
    public static final int INTAKE_STOPPER = 6;
    
    public static final int PCM_ID = 20;
    
}
