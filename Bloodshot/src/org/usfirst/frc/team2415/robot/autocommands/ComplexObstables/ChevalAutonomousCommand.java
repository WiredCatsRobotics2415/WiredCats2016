package org.usfirst.frc.team2415.robot.autocommands.ComplexObstables;

import org.usfirst.frc.team2415.robot.Robot;
import org.usfirst.frc.team2415.robot.autocommands.MindlessTraverseCommand;
import org.usfirst.frc.team2415.robot.autocommands.StraightDriveCommand;
import org.usfirst.frc.team2415.robot.autocommands.WaitCommand;
import org.usfirst.frc.team2415.robot.intakecommands.TogglePivotStateCommand;
import org.usfirst.frc.team2415.robot.subsystems.PivotSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ChevalAutonomousCommand extends CommandGroup {
    
    public  ChevalAutonomousCommand() {
    	addSequential(new MindlessTraverseCommand(.2, 1));
        addParallel(new TogglePivotStateCommand(PivotSubsystem.GROUND));
        addSequential(new StraightDriveCommand(-.25, .2));
        addSequential(new WaitCommand(.25, Robot.driveSubsystem));
    	addSequential(new TogglePivotStateCommand(PivotSubsystem.GROUND));
        addSequential(new WaitCommand(.25, Robot.driveSubsystem));
        addSequential(new MindlessTraverseCommand(.2, 1.5));
    }
}
