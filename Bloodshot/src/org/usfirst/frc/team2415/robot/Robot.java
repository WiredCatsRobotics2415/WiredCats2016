
package org.usfirst.frc.team2415.robot;

import org.usfirst.frc.team2415.robot.autocommands.MindlessTraverseCommand;
import org.usfirst.frc.team2415.robot.autocommands.PixyAlignCommand;
import org.usfirst.frc.team2415.robot.autocommands.SimpleObstacles.LowBarAutonomous;
import org.usfirst.frc.team2415.robot.autocommands.SimpleObstacles.MoatCommand;
import org.usfirst.frc.team2415.robot.autocommands.SimpleObstacles.RampartsCommand;
import org.usfirst.frc.team2415.robot.autocommands.SimpleObstacles.RockWallCommand;
import org.usfirst.frc.team2415.robot.autocommands.SimpleObstacles.RoughTerrainCommand;
import org.usfirst.frc.team2415.robot.catapultcommands.FireCatapultCommand;
import org.usfirst.frc.team2415.robot.catapultcommands.RestingCommand;
import org.usfirst.frc.team2415.robot.drivecommands.BreakCommand;
import org.usfirst.frc.team2415.robot.drivecommands.ResetDriveEncodersCommand;
import org.usfirst.frc.team2415.robot.drivecommands.ResetYawCommand;
import org.usfirst.frc.team2415.robot.flashlightcommands.GetUnlitCommand;
import org.usfirst.frc.team2415.robot.intakecommands.IntakeAndFlipCommand;
import org.usfirst.frc.team2415.robot.intakecommands.IntakeCommand;
import org.usfirst.frc.team2415.robot.intakecommands.TogglePivotStateCommand;
import org.usfirst.frc.team2415.robot.subsystems.CatapultSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.HangerSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.IntakingSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.OpticSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.PivotSubsystem;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
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
	public static HangerSubsystem hangerSubsystem;
	public static PivotSubsystem pivotSubsystem;
	public static IntakingSubsystem intakingSubsystem;
	public static OpticSubsystem opticSubsystem;
	
	public static WiredCatGamepad gamepad;
	public static WiredCatJoystick operator;
	
	public static boolean singleJoystickMode = false;
	public static boolean singleGamepadMode = true;
	
	private Compressor compressor;
	
//	private ImgServer imgServer;
	
    public void robotInit() {
		
		gamepad = new WiredCatGamepad(0);
		operator = new WiredCatJoystick(1);
		compressor = new Compressor(RobotMap.PCM_ID);
		
		driveSubsystem = new DriveSubsystem();
		catapultSubsystem = new CatapultSubsystem();
		//hangerSubsystem = new HangerSubsystem();
		pivotSubsystem = new PivotSubsystem();
		intakingSubsystem = new IntakingSubsystem();
		opticSubsystem = new OpticSubsystem();
		
		autoPosChooser = new SendableChooser();
		autoPosChooser.addObject("2 Pos", true);
		autoPosChooser.addObject("3 Pos", true);
		autoPosChooser.addObject("4 Pos", true);
		autoPosChooser.addObject("5 Pos", true);
		
		autoTypeChooser = new SendableChooser();
		autoTypeChooser.addDefault("Low Bar (needed testing)", new LowBarAutonomous());
		autoTypeChooser.addObject("Rough Terrain", new RoughTerrainCommand());
		autoTypeChooser.addObject("Moat", new MoatCommand());
		autoTypeChooser.addObject("Ramparts (needed testing)", new RampartsCommand());
		autoTypeChooser.addObject("Rock Wall", new RockWallCommand());
		SmartDashboard.putData("Auto Obstacle Type", autoTypeChooser);
		
		SmartDashboard.putData(Scheduler.getInstance());
		
		Robot.driveSubsystem.resetEncoders();
		
		SmartDashboard.putData("Reset Drive Encoders", new ResetDriveEncodersCommand());
		SmartDashboard.putData("Reset Yaw", new ResetYawCommand());
		
		operator.buttons[5].whileHeld(new IntakeCommand(false));
		operator.buttons[5].whenPressed(new TogglePivotStateCommand(PivotSubsystem.INTERIOR));
		operator.buttons[3].whenPressed(new TogglePivotStateCommand(PivotSubsystem.GROUND));
		operator.buttons[6].whileHeld(new IntakeCommand(false));
		operator.buttons[6].whenPressed(new TogglePivotStateCommand(PivotSubsystem.OUTTAKE));
		operator.buttons[7].whenPressed(new IntakeAndFlipCommand());
		if(singleJoystickMode){
//			operator.buttons[1].whileHeld(new PixyAlignCommand());
			operator.buttons[1].whenReleased(new FireCatapultCommand());
			operator.buttons[1].whenInactive(new RestingCommand());
		} else if(singleGamepadMode){
			gamepad.y_button.whileHeld(new IntakeCommand(false));
			gamepad.y_button.whenPressed(new TogglePivotStateCommand(PivotSubsystem.INTERIOR));
			gamepad.b_button.whenPressed(new TogglePivotStateCommand(PivotSubsystem.GROUND));
			gamepad.x_button.whileHeld(new IntakeCommand(false));
			gamepad.x_button.whenPressed(new TogglePivotStateCommand(PivotSubsystem.OUTTAKE));
			gamepad.a_button.whenPressed(new IntakeAndFlipCommand());
			gamepad.rightBumper.whileHeld(new GetUnlitCommand());
			gamepad.leftBumper.whenPressed(new FireCatapultCommand());
			gamepad.leftBumper.whenInactive(new RestingCommand());
		} else {
			gamepad.a_button.whileHeld(new PixyAlignCommand());
			gamepad.leftBumper.whenPressed(new FireCatapultCommand());
			gamepad.leftBumper.whenInactive(new RestingCommand());
		}
		operator.buttons[2].whenPressed(new TogglePivotStateCommand(PivotSubsystem.OUTTAKE));
		operator.buttons[4].whenPressed(new GetUnlitCommand());
		
		gamepad.b_button.whileHeld(new BreakCommand());
		
		
		
		
//		imgServer = new ImgServer("cam0", 2415);
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateStatus();
//		imgServer.showImg();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	driveSubsystem.resetYaw();
    	autoCommand = new MindlessTraverseCommand();
//    	autoCommand = new PixyAutoCommand();
//    	autoCommand = new LowBarAutonomous();
//    	autoCommand = new WaitCommand(14, driveSubsystem);
    	autoCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
//		imgServer.showImg();
		updateStatus();
		
    }

    public void teleopInit() {
    	
    }

    public void disabledInit(){

    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateStatus();
//		imgServer.showImg();
        //imgServer.teleopShowImg();
    }
 
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public void updateStatus() {
    	Robot.driveSubsystem.updateStatus();
//    	Robot.catapultSubsystem.updateStatus();
//    	Robot.intakingSubsystem.updateStatus();
//    	Robot.pivotSubsystem.updateStatus();
//    	Robot.opticSubsystem.updateStatus();
    	
    	SmartDashboard.putBoolean("Single Player Mode?", singleJoystickMode);
    }
}
