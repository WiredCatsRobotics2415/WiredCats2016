package org.usfirst.frc.team2415.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    /*Practice Port Values (looking from the back to the intake)*/

    public static int LEFT_TALON_BACK = Robot.prefs.getInt("LeftBackTalon", 1);
    public static int LEFT_TALON_FRONT = Robot.prefs.getInt("LeftFrontTalon", 2);
    public static int RIGHT_TALON_BACK = Robot.prefs.getInt("RightBackTalon", 3);
    public static int RIGHT_TALON_FRONT = Robot.prefs.getInt("RightFrontTalon", 4);

    public static int PIVOT_INTAKE_TALON = Robot.prefs.getInt("PivotIntakeTalon", 6);
    public static int SPIN_INTAKE_TALON = Robot.prefs.getInt("SpinIntakeTalon", 5);
    
    public static int CATAPULT_SOLENOID_1 = Robot.prefs.getInt("CatpultSolenoid1", 7);
    public static int CATAPULT_SOLENOID_2 = Robot.prefs.getInt("CatapultSolenoid2", 0);

    public static final int[] LONG_SOLENOID = {Robot.prefs.getInt("LongSolenoid1", 5), 
    										   Robot.prefs.getInt("LongSolenoid1", 6)};
    public static final int[] SHORT_SOLENOID = {Robot.prefs.getInt("ShortSolenoid1", 2), 
    										   Robot.prefs.getInt("ShortSolenoid1", 4)};
    
    public static final int[] CATAPULT_SOLENOIDS = {CATAPULT_SOLENOID_1, CATAPULT_SOLENOID_2};
    
    public static final int[] LEFT_ENCODER = {Robot.prefs.getInt("LeftEncoder1", 4),
			  								  Robot.prefs.getInt("LeftEncoder2", 5)};
    public static final int[] RIGHT_ENCODER = {Robot.prefs.getInt("RightEncoder1", 2),
			  							  	   Robot.prefs.getInt("RightEncoder2", 3)};
    public static final int[] INTAKE_IR = {Robot.prefs.getInt("IntakeIR1", 6),
		  	   							   Robot.prefs.getInt("IntakeIR2", 1)};
    
	public static final int WINCH_MOTOR = 0;
	public static final int[] WINCH_LOCK = null;
    
    public static int LIGHTSWITCH_PORT = Robot.prefs.getInt("LightswitchPort", 0);
    
    public static int PIXYCAM_ANALOG = Robot.prefs.getInt("PixyCamAnalog", 0);
    public static int PIXYCAM_DIGITAL = Robot.prefs.getInt("PixyCamDigital", 0);
    
    public static int PCM_ID = Robot.prefs.getInt("PCMID", 20);
    
}
