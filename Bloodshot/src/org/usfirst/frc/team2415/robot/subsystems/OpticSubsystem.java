package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;
import org.usfirst.frc.team2415.robot.flashlightcommands.GetLitCommand;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogOutput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class OpticSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private Relay lightSwitch;
	private boolean isLit;
	public AnalogInput pixyCam;
	private DigitalInput pixyCamBool;
	
	public OpticSubsystem(){
    	lightSwitch = new Relay(RobotMap.LIGHTSWITCH_PORT, Relay.Direction.kForward);
    	pixyCam = new AnalogInput(RobotMap.PIXYCAM_ANALOG);
    	pixyCamBool = new DigitalInput(RobotMap.PIXYCAM_DIGITAL);
    	itsNotLit();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new GetLitCommand());
    }
    
    public void malmbergSwitch(){
    	if (isLit) {
    		itsNotLit();
    	} else {
    		itsLit();
    	}
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
    
    public double camVoltage(){
    	return pixyCam.getVoltage();
    }
    //Checks the digital output of the pixy cam to see if there even is an image that fits within the color parameters
    public boolean camBool(){
    	return pixyCamBool.get();
    }
}

