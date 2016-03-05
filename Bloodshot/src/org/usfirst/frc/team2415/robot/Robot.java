
package org.usfirst.frc.team2415.robot;

import org.usfirst.frc.team2415.robot.autocommands.TurnCommand;
import org.usfirst.frc.team2415.robot.catapultcommands.FireCatapultCommand0250msec;
import org.usfirst.frc.team2415.robot.catapultcommands.FireCatapultCommand0500msec;
import org.usfirst.frc.team2415.robot.catapultcommands.FireCatapultCommand0750msec;
import org.usfirst.frc.team2415.robot.catapultcommands.FireCatapultCommand1000msec;
import org.usfirst.frc.team2415.robot.catapultcommands.RestingCommand;
import org.usfirst.frc.team2415.robot.drivecommands.ResetDriveEncodersCommand;
import org.usfirst.frc.team2415.robot.drivecommands.ResetYawCommand;
import org.usfirst.frc.team2415.robot.intakecommands.IntakeCommand;
import org.usfirst.frc.team2415.robot.intakecommands.ResetIntakeEncodersCommand;
import org.usfirst.frc.team2415.robot.intakecommands.ZeroIntakeCommand;
import org.usfirst.frc.team2415.robot.subsystems.CatapultSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.IntakeSubsystem;

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
	
	public static WiredCatGamepad gamepad;
	public static WiredCatJoystick operator;
	
	private IMU imu;
	
	//in degrees
	public static final float INTAKE_ANGLE = 31f;
	public static final float GROUND_ANGLE = 20f;
	public static final float VERTICAL_ANGLE = 100f;
	public static final float INTERIOR_ANGLE = 140f;
	
	private Compressor compressor;
	
	private ImgServer imgServer;
	
	private TurnCommand auto;
	
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
		
		SmartDashboard.putData(Scheduler.getInstance());
		
		Robot.intakeSubsystem.resetEncoder();
		Robot.driveSubsystem.resetEncoders();
		
		SmartDashboard.putData("Reset Drive Encoders", new ResetDriveEncodersCommand());
		SmartDashboard.putData("Reset Intake Encoders", new ResetIntakeEncodersCommand());
		SmartDashboard.putData("Reset Yaw", new ResetYawCommand());
		
		SmartDashboard.putData("0.25 Second Shot", new FireCatapultCommand0250msec());
		SmartDashboard.putData("0.5 Second Shot", new FireCatapultCommand0500msec());
		SmartDashboard.putData("0.75 Second Shot", new FireCatapultCommand0750msec());
		SmartDashboard.putData("1 Second Shot", new FireCatapultCommand1000msec());

		operator.buttons[11].whenActive(new ZeroIntakeCommand());
		operator.buttons[9].whileHeld(new IntakeCommand(VERTICAL_ANGLE, 0, false));
		operator.buttons[6].whileHeld(new IntakeCommand(INTAKE_ANGLE, 0, false));
		operator.buttons[6].whenInactive(new IntakeCommand(VERTICAL_ANGLE, 0, false));
		operator.buttons[3].whileHeld(new IntakeCommand(GROUND_ANGLE, 0, false));
		operator.buttons[3].whenInactive(new IntakeCommand(VERTICAL_ANGLE, 0, false));
		operator.buttons[7].whileHeld(new IntakeCommand(INTAKE_ANGLE, 0, true));
		operator.buttons[7].whenInactive(new IntakeCommand(VERTICAL_ANGLE, 0, false));
		operator.buttons[2].whileHeld(new IntakeCommand(INTERIOR_ANGLE, 1, true));
		operator.buttons[2].whenInactive(new IntakeCommand(VERTICAL_ANGLE, 0, false));
		operator.buttons[1].whenPressed(new FireCatapultCommand0250msec());
		operator.buttons[1].whenInactive(new RestingCommand());
		
		imgServer = new ImgServer("cam1", 2415);
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateStatus();
		imgServer.showImg();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	driveSubsystem.resetYaw();
    	auto = new TurnCommand(90);
    	auto.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
		imgServer.showImg();
		//imgServer.sendImg();
		
		System.out.println(driveSubsystem.getLeftEncoder() + ",\t" + driveSubsystem.getRightEncoder() + ",\t" + driveSubsystem.getYaw());
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
        imgServer.teleopShowImg();
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
