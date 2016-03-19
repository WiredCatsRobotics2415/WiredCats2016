
package org.usfirst.frc.team2415.robot;

import org.usfirst.frc.team2415.robot.autocommands.PortcullisAutonomous;
import org.usfirst.frc.team2415.robot.autocommands.RoughTerrainAutoCommand;
import org.usfirst.frc.team2415.robot.catapultcommands.FireCatapultCloseCommand;
import org.usfirst.frc.team2415.robot.catapultcommands.FireCatapultCommand;
import org.usfirst.frc.team2415.robot.catapultcommands.RestingCommand;
import org.usfirst.frc.team2415.robot.drivecommands.BreakCommand;
import org.usfirst.frc.team2415.robot.drivecommands.ResetDriveEncodersCommand;
import org.usfirst.frc.team2415.robot.drivecommands.ResetYawCommand;
import org.usfirst.frc.team2415.robot.intakecommands.IntakeCommand;
import org.usfirst.frc.team2415.robot.intakecommands.PneumaticIntakeCommand;
import org.usfirst.frc.team2415.robot.intakecommands.ResetIntakeEncodersCommand;
import org.usfirst.frc.team2415.robot.intakecommands.ZeroIntakeCommand;
import org.usfirst.frc.team2415.robot.subsystems.CatapultSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team2415.robot.subsystems.PneumaticIntakeSubsystem;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
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
	
	Preferences prefs;

	public static SendableChooser autoPosChooser, autoTypeChooser;
	public static Command autoCommand;
	
	public static DriveSubsystem driveSubsystem;
	public static IntakeSubsystem intakeSubsystem;
	public static CatapultSubsystem catapultSubsystem;
	public static PneumaticIntakeSubsystem pneumaticIntakeSubsystem;
	
	public static WiredCatGamepad gamepad;
	public static WiredCatJoystick operator;
	
	//in degrees
	public static float INTAKE_ANGLE;
	public static float GROUND_ANGLE;
	public static float VERTICAL_ANGLE;
	public static float INTERIOR_ANGLE;
	public static float HIGH_GOAL_ANGLE;
	
	//EDIT AT COMPETITON!!!!
	public static double LOW_BAR_ANGLE = 0;
	public static double DEFENSE_1_ANGLE = 0;
	public static double DEFENSE_2_ANGLE = 0;
	public static double DEFENSE_3_ANGLE = 0;
	public static double DEFENSE_4_ANGLE = 0;
	
	public static boolean singlePlayerMode = false;
	
	private Compressor compressor;
	
	private ImgServer imgServer;
	
    public void robotInit() {
		prefs = Preferences.getInstance();
		
		INTAKE_ANGLE = prefs.getFloat("Intake Angle", -134f);
		GROUND_ANGLE = prefs.getFloat("Ground Angle", -150f);
		VERTICAL_ANGLE = prefs.getFloat("Vertical Angle", -20f);
		INTERIOR_ANGLE = prefs.getFloat("Interior Angle", -10f);
		
		LOW_BAR_ANGLE = prefs.getDouble("Low Bar Angle", 0);
		DEFENSE_1_ANGLE = prefs.getDouble("Defense 1 Angle", 0);
		DEFENSE_2_ANGLE = prefs.getDouble("Defense 2 Angle", 0);
		DEFENSE_3_ANGLE = prefs.getDouble("Defense 3 Angle", 0);
		DEFENSE_4_ANGLE = prefs.getDouble("Defense 4 Angle", 0);
		
		gamepad = new WiredCatGamepad(0);
		operator = new WiredCatJoystick(1);
		compressor = new Compressor(RobotMap.PCM_ID);
		
		driveSubsystem = new DriveSubsystem();
//		intakeSubsystem = new IntakeSubsystem();
		catapultSubsystem = new CatapultSubsystem();
		pneumaticIntakeSubsystem = new PneumaticIntakeSubsystem();
		
		autoPosChooser = new SendableChooser();
		autoPosChooser.addDefault("Low Bar", LOW_BAR_ANGLE);
		autoPosChooser.addObject("Defense 1", DEFENSE_1_ANGLE);
		autoPosChooser.addObject("Defense 2", DEFENSE_2_ANGLE);
		autoPosChooser.addObject("Defense 3", DEFENSE_3_ANGLE);
		autoPosChooser.addObject("Defense 4", DEFENSE_4_ANGLE);
		SmartDashboard.putData("Auto Position Chooser", autoPosChooser);
		
		autoTypeChooser = new SendableChooser();
		autoTypeChooser.addDefault("Rough Terrain", new RoughTerrainAutoCommand((double)autoPosChooser.getSelected()));
		autoTypeChooser.addObject("Portcullis", new PortcullisAutonomous((double)autoPosChooser.getSelected()));
		SmartDashboard.putData("Auto Obstacle Type", autoTypeChooser);
		
		SmartDashboard.putData(Scheduler.getInstance());
		
//		Robot.intakeSubsystem.resetEncoder();
		Robot.driveSubsystem.resetEncoders();
//    	pneumaticIntakeSubsystem.setIntakeState("Interior");
		
		SmartDashboard.putData("Reset Drive Encoders", new ResetDriveEncodersCommand());
//		SmartDashboard.putData("Reset Intake Encoders", new ResetIntakeEncodersCommand());
		SmartDashboard.putData("Reset Yaw", new ResetYawCommand());

//		operator.buttons[5].whileHeld(new ZeroIntakeCommand());
//		operator.buttons[9].whileHeld(new IntakeCommand(HIGH_GOAL_ANGLE, 0));
//		operator.buttons[6].whileHeld(new IntakeCommand(VERTICAL_ANGLE, 0));
//		operator.buttons[6].whenInactive(new IntakeCommand(INTERIOR_ANGLE, 0));
//		operator.buttons[3].whileHeld(new IntakeCommand(GROUND_ANGLE, 0));
//		operator.buttons[3].whenInactive(new IntakeCommand(INTERIOR_ANGLE, 0));
//		operator.buttons[7].whileHeld(new IntakeCommand(INTAKE_ANGLE, 0));
//		operator.buttons[7].whenInactive(new IntakeCommand(INTERIOR_ANGLE, 0));
//		operator.buttons[2].whileHeld(new IntakeCommand(INTERIOR_ANGLE, .6));
//		operator.buttons[2].whenInactive(new IntakeCommand(INTERIOR_ANGLE, 0));
//		operator.buttons[4].whenPressed(new FireCatapultCloseCommand());
//		operator.buttons[4].whenInactive(new RestingCommand());
//		operator.buttons[1].whenPressed(new FireCatapultCommand());
//		operator.buttons[1].whenInactive(new RestingCommand());
		
		operator.buttons[2].whileHeld(new PneumaticIntakeCommand("Interior"));
		operator.buttons[3].whileHeld(new PneumaticIntakeCommand("Ground"));
		operator.buttons[6].whileHeld(new PneumaticIntakeCommand("Interior"));
		operator.buttons[7].whileHeld(new PneumaticIntakeCommand("Interior"));
		
		gamepad.leftBumper.whileHeld(new BreakCommand());
		
		//imgServer = new ImgServer("cam0", 2415);
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateStatus();
		//imgServer.showImg();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	pneumaticIntakeSubsystem.setIntakeState("Interior");
    	driveSubsystem.resetYaw();
    	autoCommand = (Command)autoTypeChooser.getSelected();
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
//    	Robot.intakeSubsystem.updateStatus();
    	Robot.catapultSubsystem.updateStatus();
    	Robot.pneumaticIntakeSubsystem.updateStatus();
    }
}
