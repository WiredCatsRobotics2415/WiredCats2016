package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;
import org.usfirst.frc.team2415.robot.flashlightcommands.GetLitCommand;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DankSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private Relay lightSwitch;
	private boolean isLit;
	
	
	public DankSubsystem(){
    	lightSwitch = new Relay(RobotMap.LIGHTSWITCH_PORT, Relay.Direction.kForward);
    	itsNotLit();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new GetLitCommand());
    }
    
    public void itsLit(){
    	lightSwitch.set(Value.kOn);
    	isLit = true;
    }
    
    public void itsNotLit(){
    	lightSwitch.set(Value.kOff);
    	isLit = false;
    }
    
    public boolean isLit(){
    	return isLit;
    }
}

