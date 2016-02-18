
package org.usfirst.frc.team2415.robot;

import org.usfirst.frc.team2415.robot.catapultcommands.*;
import org.usfirst.frc.team2415.robot.drivecommands.*;
import org.usfirst.frc.team2415.robot.intakecommands.*;
import org.usfirst.frc.team2415.robot.subsystems.*;

import com.kauailabs.nav6.frc.IMU;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	
	public static DriveSubsystem driveSubsystem;
	public static IntakeSubsystem intakeSubsystem;
	public static CatapultSubsystem catapultSubsystem;
	public static TempCatapultSubsystem launcherSubsystem;
	
	public static WiredCatGamepad gamepad;
	public static WiredCatJoystick operator;
	
	private IMU imu;
	
	//in degrees
	private float INTAKE_ANGLE = 40f;
	private float GROUND_ANGLE = 3f;
	private float VERTICAL_ANGLE = 80f;
	private float INTERIOR_ANGLE = 160f;
	
	private Compressor compressor;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		
		gamepad = new WiredCatGamepad(0);
		operator = new WiredCatJoystick(1);
		compressor = new Compressor(RobotMap.PCM_ID);
		
		driveSubsystem = new DriveSubsystem();
		intakeSubsystem = new IntakeSubsystem();
		catapultSubsystem = new CatapultSubsystem();
		launcherSubsystem = new TempCatapultSubsystem();
		
		SmartDashboard.putData(Scheduler.getInstance());
		SmartDashboard.putData("Reset Encoders", new ResetDriveEncodersCommand());
		SmartDashboard.putData("Reset Yaw", new ResetYawCommand());
		
		Robot.intakeSubsystem.resetEncoder();
		
		operator.buttons[9].whenPressed(new IntakeCommand(VERTICAL_ANGLE, 0, false));
		operator.buttons[6].whenPressed(new IntakeCommand(INTAKE_ANGLE, 0, false));
		operator.buttons[8].whenPressed(new IntakeCommand(GROUND_ANGLE, 0, false));
		operator.buttons[7].whenPressed(new IntakeCommand(INTAKE_ANGLE, 0, true));
		operator.buttons[1].whileHeld(new FireBarrels1And2Command());
		operator.buttons[1].whenInactive(new CancelCommand());
		
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateStatus();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	
    	
    	
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateStatus();
        
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public void updateStatus() {
    	Robot.driveSubsystem.updateStatus();
    	Robot.intakeSubsystem.updateStatus();
    	Robot.catapultSubsystem.updateStatus();
    }
}
