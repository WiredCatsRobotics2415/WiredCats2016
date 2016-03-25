package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class HangerSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Solenoid lock, unlock;
	private CANTalon winchMotor;
	
	public HangerSubsystem(){
		winchMotor = new CANTalon(RobotMap.WINCH_MOTOR);
		lock = new Solenoid(RobotMap.PCM_ID, RobotMap.WINCH_LOCK[0]);
		unlock = new Solenoid(RobotMap.PCM_ID, RobotMap.WINCH_LOCK[1]);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void winch(){
    	lock.set(false);
    	unlock.set(true);
    	winchMotor.set(1);
    }
    
    public void stop(){
    	lock.set(true);
    	unlock.set(false);
    	winchMotor.set(0);
    }
}

