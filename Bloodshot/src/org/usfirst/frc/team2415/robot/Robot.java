
package org.usfirst.frc.team2415.robot;

import org.usfirst.frc.team2415.robot.autocommands.FireSequenceCommand;
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
import com.ni.vision.NIVision;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;

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
	public static final float GROUND_ANGLE = 160-8f;
	public static final float VERTICAL_ANGLE = 160-100f;
	public static final float INTERIOR_ANGLE = 160-140f;
	
	public static boolean singlePlayerMode = true;
	
	private Compressor compressor;
	
	private USBCamera cam;
	private NIVision.Image frame;
	
	private final int CAM_W = 640, CAM_H = 480;
	private final int[] focus = {(int)((8.1/13.7)*CAM_W), (int)((2/10.2)*CAM_H)};
	
	private final NIVision.Point vert1 = new NIVision.Point(focus[0], focus[1] - 30),
								 vert2 = new NIVision.Point(focus[0], focus[1] + 30),
								 hori1 = new NIVision.Point(focus[0] - 30, focus[1]),
								 hori2 = new NIVision.Point(focus[0] + 30, focus[1]);
	
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
		operator.buttons[1].whenPressed(new FireSequenceCommand());
		operator.buttons[1].whenInactive(new RestingCommand());
		
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		cam = new USBCamera("cam1");
		cam.openCamera();
		cam.startCapture();
//		cam.setExposureManual(25);
//		cam.setExposureHoldCurrent();
//		cam.setWhiteBalanceManual(25);
//		cam.setWhiteBalanceHoldCurrent();
		cam.setBrightness(30);
		cam.setSize(CAM_W, CAM_H);
		cam.setFPS(30);
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateStatus();
		cam.getImage(frame);
		CameraServer.getInstance().setImage(frame);
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
		cam.getImage(frame);
		CameraServer.getInstance().setImage(frame);
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
		cam.getImage(frame);
		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_VALUE, vert1, vert2, 0.0f);
		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_VALUE, hori1, hori2, 0.0f);
		CameraServer.getInstance().setImage(frame);
        
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
