
package org.usfirst.frc.team2415.robot;

import org.usfirst.frc.team2415.robot.autocommands.TurnCommand;
import org.usfirst.frc.team2415.robot.catapultcommands.FireCatapultCommand;
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
	public static final float INTAKE_ANGLE = 160-31f;
	public static final float GROUND_ANGLE = 160-3f;
	public static final float VERTICAL_ANGLE = 160-100f;
	public static final float INTERIOR_ANGLE = 160-140f;
	
	public static boolean singlePlayerMode = false;
	
	private Compressor compressor;
	
	private ImgServer imgServer;
	
	private TurnCommand auto;
	
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

		operator.buttons[11].whileHeld(new ZeroIntakeCommand());
		operator.buttons[9].whileHeld(new IntakeCommand(-VERTICAL_ANGLE, 0));
		operator.buttons[6].whileHeld(new IntakeCommand(-INTAKE_ANGLE, 0));
		operator.buttons[6].whenInactive(new IntakeCommand(-VERTICAL_ANGLE, 0));
		operator.buttons[3].whileHeld(new IntakeCommand(-GROUND_ANGLE, 0));
		operator.buttons[3].whenInactive(new IntakeCommand(-VERTICAL_ANGLE, 0));
		operator.buttons[7].whileHeld(new IntakeCommand(-INTAKE_ANGLE, 0));
		operator.buttons[7].whenInactive(new IntakeCommand(-VERTICAL_ANGLE, 0));
		operator.buttons[2].whileHeld(new IntakeCommand(-INTERIOR_ANGLE, 1));
		operator.buttons[2].whenInactive(new IntakeCommand(-VERTICAL_ANGLE, 0));
		operator.buttons[1].whenPressed(new FireCatapultCommand());
		operator.buttons[1].whenInactive(new RestingCommand());
		
		//imgServer = new ImgServer("cam1", 2415);
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateStatus();
		//imgServer.showImg();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	driveSubsystem.resetYaw();
    	auto = new TurnCommand(90);
    	auto.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
		imgServer.showImg();
		//imgServer.sendImg();
		updateStatus();
    }

    public void teleopInit() {
    	
    }

    public void disabledInit(){

    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateStatus();
        imgServer.teleopShowImg();
    }
 
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public void updateStatus() {
    	Robot.driveSubsystem.updateStatus();
    	Robot.intakeSubsystem.updateStatus();
    	Robot.catapultSubsystem.updateStatus();
    }
}
