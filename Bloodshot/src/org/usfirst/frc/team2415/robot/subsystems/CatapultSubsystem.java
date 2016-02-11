package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 */
public class CatapultSubsystem extends Subsystem {
	
	private Solenoid Solenoid0;
	private Solenoid Solenoid1;
	private Solenoid Solenoid2;
	private Solenoid Solenoid3;
  
    public void initDefaultCommand() {
    	
    	Solenoid0 = new Solenoid(RobotMap.PCM_ID, RobotMap.CATAPULT_SOLENOID0);
    	Solenoid1 = new Solenoid(RobotMap.PCM_ID, RobotMap.CATAPULT_SOLENOID1);
    	Solenoid2 = new Solenoid(RobotMap.PCM_ID, RobotMap.CATAPULT_SOLENOID2);
    	Solenoid3 = new Solenoid(RobotMap.PCM_ID, RobotMap.CATAPULT_SOLENOID3);
       
    	
    }
    
    public void extendCatapult() {
    	
    	Solenoid0.set(true);
    	Solenoid1.set(true);
    	Solenoid2.set(true);
    	Solenoid3.set(true);
    	
    }
    
    public void retractCatapult() {
    	
    	Solenoid0.set(false);
    	Solenoid1.set(false);
    	Solenoid2.set(false);
    	Solenoid3.set(false);
    	
    }
    
    public boolean isExtended() {
    	
    	return Solenoid0.get();
    	
    }
}

