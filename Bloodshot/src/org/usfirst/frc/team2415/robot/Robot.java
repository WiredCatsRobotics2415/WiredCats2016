
package org.usfirst.frc.team2415.robot;

import org.usfirst.frc.team2415.robot.autocommands.SimpleObstacles.RockWallCommand;
import org.usfirst.frc.team2415.robot.catapultcommands.FireCatapultCloseCommand;
import org.usfirst.frc.team2415.robot.catapultcommands.FireCatapultCommand;
import org.usfirst.frc.team2415.robot.catapultcommands.RestingCommand;
import org.usfirst.frc.team2415.robot.drivecommands.ResetDriveEncodersCommand;
import org.usfirst.frc.team2415.robot.drivecommands.ResetYawCommand;
<<<<<<< HEAD
import org.usfirst.frc.team2415.robot.intakecommands.IntakeCommand;
import org.usfirst.frc.team2415.robot.intakecommands.TogglePivotStateCommand;
import org.usfirst.frc.team2415.robot.subsystems.CatapultSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.IntakingSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.PivotSubsystem;
=======
import org.usfirst.frc.team2415.robot.subsystems.CatapultSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.DriveSubsystem;
>>>>>>> ImprovedRoughTerrainAuto

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
	public static PivotSubsystem pivotSubsystem;
	public static IntakingSubsystem intakingSubsystem;
	
	public static WiredCatGamepad gamepad;
	public static WiredCatJoystick operator;
	
	//EDIT AT COMPETITON!!!!
	public static final double LOW_BAR_ANGLE = 0;
	public static final double DEFENSE_1_ANGLE = 0;
	public static final double DEFENSE_2_ANGLE = 0;
	public static final double DEFENSE_3_ANGLE = 0;
	public static final double DEFENSE_4_ANGLE = 0;
	
	public static boolean singlePlayerMode = true;
	
	private Compressor compressor;
	
	private ImgServer imgServer;
	
    public void robotInit() {
		
		gamepad = new WiredCatGamepad(0);
		operator = new WiredCatJoystick(1);
		compressor = new Compressor(RobotMap.PCM_ID);
		
		driveSubsystem = new DriveSubsystem();
		catapultSubsystem = new CatapultSubsystem();
		pivotSubsystem = new PivotSubsystem();
		intakingSubsystem = new IntakingSubsystem();
		
		autoPosChooser = new SendableChooser();
		autoPosChooser.addDefault("Low Bar", LOW_BAR_ANGLE);
		autoPosChooser.addObject("Defense 1", DEFENSE_1_ANGLE);
		autoPosChooser.addObject("Defense 2", DEFENSE_2_ANGLE);
		autoPosChooser.addObject("Defense 3", DEFENSE_3_ANGLE);
		autoPosChooser.addObject("Defense 4", DEFENSE_4_ANGLE);
		SmartDashboard.putData("Auto Position Chooser", autoPosChooser);
		
		autoTypeChooser = new SendableChooser();
		SmartDashboard.putData("Auto Obstacle Type", autoTypeChooser);
		
		SmartDashboard.putData(Scheduler.getInstance());
		
		Robot.driveSubsystem.resetEncoders();
		
		SmartDashboard.putData("Reset Drive Encoders", new ResetDriveEncodersCommand());
		SmartDashboard.putData("Reset Yaw", new ResetYawCommand());
<<<<<<< HEAD

		operator.buttons[2].whileHeld(new IntakeCommand());
		operator.buttons[2].whenPressed(new TogglePivotStateCommand(PivotSubsystem.INTERIOR));
		operator.buttons[3].whenPressed(new TogglePivotStateCommand(PivotSubsystem.GROUND));
		operator.buttons[6].whileHeld(new IntakeCommand());
		operator.buttons[6].whenPressed(new TogglePivotStateCommand(PivotSubsystem.OUTTAKE));
		operator.buttons[7].whenPressed(new TogglePivotStateCommand(PivotSubsystem.INTAKE));
		operator.buttons[7].whileHeld(new IntakeCommand());
		
=======
>>>>>>> ImprovedRoughTerrainAuto
		operator.buttons[4].whenPressed(new FireCatapultCloseCommand());
		operator.buttons[4].whenInactive(new RestingCommand());
		operator.buttons[1].whenPressed(new FireCatapultCommand());
		operator.buttons[1].whenInactive(new RestingCommand());
		//gamepad.leftBumper.whileHeld(new BreakCommand());
		
		//imgServer = new ImgServer("cam0", 2415);
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateStatus();
		//imgServer.showImg();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	driveSubsystem.resetYaw();
    	autoCommand = new RockWallCommand();//(Command)autoTypeChooser.getSelected();
    	autoCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
		//imgServer.showImg();
		updateStatus();
		
    }

    public void teleopInit() {
    	
    }

    public void disabledInit(){

    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateStatus();
		//imgServer.showImg();
        //imgServer.teleopShowImg();
    }
 
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public void updateStatus() {
    	Robot.driveSubsystem.updateStatus();
    	Robot.catapultSubsystem.updateStatus();
    	Robot.intakingSubsystem.updateStatus();
    	Robot.pivotSubsystem.updateStatus();
    }
}
