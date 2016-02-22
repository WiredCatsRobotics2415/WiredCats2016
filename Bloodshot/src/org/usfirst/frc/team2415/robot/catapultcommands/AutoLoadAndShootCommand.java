package org.usfirst.frc.team2415.robot.catapultcommands;

import org.usfirst.frc.team2415.robot.Robot;
import org.usfirst.frc.team2415.robot.intakecommands.IntakeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLoadAndShootCommand extends CommandGroup {
    
	
	
    public  AutoLoadAndShootCommand() {
    	requires(Robot.catapultSubsystem);
    	requires(Robot.driveSubsystem);
    	requires(Robot.intakeSubsystem);
    	
    	// Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	Robot.catapultSubsystem.setIsShooting(true);      //indicates the shooting process is starting
    	addSequential(new IntakeCommand(Robot.INTAKE_ANGLE, 0, false));   //lowers intake to prepare anus
    	addSequential(new TimeoutCommand(.65));			  //waits a bit
    	addSequential(new IntakeCommand(Robot.VERTICAL_ANGLE, 1, true));   //spits the ball into the shooter & blasts anus
    	addSequential(new TimeoutCommand(1));		      //waits a bit
    	addSequential(new IntakeCommand(Robot.GROUND_ANGLE, 0, false));   //puts the intake at ground hight
    	addSequential(new TimeoutCommand(.65));			  //waits a bit
    	addSequential(new FireCatapultCommandTest1());	  //shoots
    	addSequential(new TimeoutCommand(1));			  //waits a bit
    	addSequential(new IntakeCommand(Robot.VERTICAL_ANGLE, 0, false));  //sets intake at vertical
    	Robot.catapultSubsystem.setIsShooting(false);	  //indicates the shooting process is over
    	
        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    }
}
