
package org.usfirst.frc.team2415.robot;

import org.usfirst.frc.team2415.robot.drivecommands.*;
import org.usfirst.frc.team2415.robot.intakecommands.*;
import org.usfirst.frc.team2415.robot.subsystems.*;

import com.kauailabs.nav6.frc.IMU;

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
	public static PIDIntakeSubsystem pidintakeSubsystem;
	
	public static WiredCatGamepad gamepad;
	public static WiredCatJoystick operator;
	
	private IMU imu;
	
	private float INTAKE_ANGLE = 15f;
	private float GROUND_ANGLE = 2f;
	private float VERTICAL_ANGLE = 36f;
	private float INTERIOR_ANGLE = 59f;
	
	//private Compressor compressor;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		
		gamepad = new WiredCatGamepad(0);
		operator = new WiredCatJoystick(1);
//		compressor = new Compressor(RobotMap.PCM_ID);
		
		driveSubsystem = new DriveSubsystem();
		pidintakeSubsystem = new PIDIntakeSubsystem(1,0,0,0);
		
		SmartDashboard.putData(Scheduler.getInstance());
		SmartDashboard.putData("Reset Encoders", new ResetEncodersCommand());
		SmartDashboard.putData("Reset Yaw", new ResetYawCommand());
		SmartDashboard.putData("Zero Intake", new ZeroingCommand());
		
		operator.buttons[1].whenPressed(new StopPIDCommand());
		operator.buttons[6].whenPressed(new MoveIntakeCommand(VERTICAL_ANGLE));
		operator.buttons[7].whenPressed(new MoveIntakeCommand(INTAKE_ANGLE));
		operator.buttons[7].whileHeld(new IntakeCommand());
		operator.buttons[8].whenPressed(new MoveIntakeCommand(GROUND_ANGLE));
		operator.buttons[2].whileHeld(new IntakeCommand());
//		operator.buttons[9].whileHeld(new ZeroingCommand());
		
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
        updateStatus();
        
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
    	Robot.pidintakeSubsystem.stopPID();

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
    	Robot.pidintakeSubsystem.updateStatus();
    }
}
