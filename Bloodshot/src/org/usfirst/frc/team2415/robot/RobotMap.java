package org.usfirst.frc.team2415.robot;

import edu.wpi.first.wpilibj.Preferences;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final int LEFT_TALON_BACK = 1;
    public static final int LEFT_TALON_FRONT = 2;
    public static final int RIGHT_TALON_BACK = 3;
    public static final int RIGHT_TALON_FRONT = 4;
    
    public static final int PIVOT_INTAKE_TALON = 6;
    public static final int SPIN_INTAKE_TALON = 5;
    
    public static final int CATAPULT_SOLENOID_1 = 2;
    public static final int CATAPULT_SOLENOID_2 = 3;
    
    public static final int[] LONG_SOLENOID = {1,2};
    public static final int[] SHORT_SOLENOID = {5,6};
    
    public static final int[] FIRE_SOLENOIDS = {CATAPULT_SOLENOID_1, CATAPULT_SOLENOID_2};
    
    public static final int[] LEFT_ENCODER = {4,5};
    public static final int[] INTAKE_ENCODER = {0,1};
    
    public static final int[] RIGHT_ENCODER = {2,3};
    public static final int INTAKE_IR_LEFT = 6;
    public static final int INTAKE_IR_RIGHT = 7;
    
    public static final int PCM_ID = 20;
}
