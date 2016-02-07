package org.usfirst.frc.team2415.robot.pneumaticintakecommands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TogglePneumaticIntakeCommand extends Command {
	
	/* TODO: make a variable operator to hold a WiredCatJoystick
	 * hint 1: you're gonna have to import WiredCatJoystick
	 * hint 2: if needed check the IntakeCommand, you'll see what I'm talking about
	 */

    public TogglePneumaticIntakeCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	/* TODO: make it require the PneumaticIntakeSubsystem and initialize operator
    	 * hint 1: it has to require the instance of PneumaticIntakeSubstance
    	 *		   created in the Robot class
    	 * hint 2: again, if need be look at IntakeCommand and 
    	 * 		   you'll see what I'm talking about
    	 */
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	/* TODO: if the state of the intake is extended, make it contract
    	 * 		 if the state of the intake is contracted, make it extend
    	 */
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*TODO: if button 10 is pressed set the spin motor to -0.5
    	 * 		if button 11 is pressed set the spin motor to 0.5
    	 */
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
