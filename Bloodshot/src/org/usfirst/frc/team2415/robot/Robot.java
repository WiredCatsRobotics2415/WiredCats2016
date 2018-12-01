
package org.usfirst.frc.team2415.robot;

import org.usfirst.frc.team2415.robot.catapultcommands.FireCatapultCommand;
import org.usfirst.frc.team2415.robot.catapultcommands.RestingCommand;
import org.usfirst.frc.team2415.robot.flashlightcommands.MalmbergSwitch;
import org.usfirst.frc.team2415.robot.intakecommands.TogglePivotStateCommand;
import org.usfirst.frc.team2415.robot.subsystems.CatapultSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.IntakingSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.OpticSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.PivotSubsystem;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static SendableChooser autoPosChooser, autoTypeChooser;
	public static Command autoCommand;
	
	public static DriveSubsystem driveSubsystem;
	public static CatapultSubsystem catapultSubsystem;
	public static PivotSubsystem pivotSubsystem;
	public static IntakingSubsystem intakingSubsystem;
	public static OpticSubsystem opticSubsystem;
	
	public static WiredCatGamepad gamepad;
	public static WiredCatJoystick operator;
	
	private Compressor compressor;
	
//	private ImgServer imgServer;
	
    public void robotInit() {
		
		gamepad = new WiredCatGamepad(0);
		operator = new WiredCatJoystick(1);
		compressor = new Compressor(RobotMap.PCM_ID);
		
		driveSubsystem = new DriveSubsystem();
		catapultSubsystem = new CatapultSubsystem();
		pivotSubsystem = new PivotSubsystem();
		intakingSubsystem = new IntakingSubsystem();
		opticSubsystem = new OpticSubsystem();
		
		SmartDashboard.putData(Scheduler.getInstance());
		
		Robot.driveSubsystem.resetEncoders();
		
//		SmartDashboard.putData("Reset Drive Encoders", new ResetDriveEncodersCommand());
//		SmartDashboard.putData("Reset Yaw", new ResetYawCommand());

		gamepad.leftBumper.whenPressed(new FireCatapultCommand());
		gamepad.leftBumper.whenInactive(new RestingCommand());
		gamepad.a_button.whenPressed(new TogglePivotStateCommand(PivotSubsystem.INTAKE));
		gamepad.b_button.whenPressed(new TogglePivotStateCommand(PivotSubsystem.INTERIOR));
		gamepad.y_button.whenPressed(new MalmbergSwitch());

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
//		updateStatus();
//		imgServer.showImg();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
//    	autoCommand = new PixyAutoCommand();
//    	autoCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
//		imgServer.showImg();
//		updateStatus();
		
    }

    public void teleopInit() {
    	
    }

    public void disabledInit(){

    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
//        updateStatus();
//		imgServer.showImg();
        //imgServer.teleopShowImg();
    }
 
    public void testPeriodic() {
    }
    
    public void updateStatus() {
//    	Robot.driveSubsystem.updateStatus();
//    	Robot.catapultSubsystem.updateStatus();
//    	Robot.intakingSubsystem.updateStatus();
//    	Robot.pivotSubsystem.updateStatus();
    }
}
