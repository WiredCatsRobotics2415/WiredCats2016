package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;
import org.usfirst.frc.team2415.robot.catapultcommands.RestingCommand;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class TempCatapultSubsystem extends Subsystem {
	
	public enum Solenoids {	TOP_LEFT_FIRE, TOP_RIGHT_FIRE, BOT_LEFT_FIRE,
							BOT_RIGHT_FIRE, ALL_FIRE
	}
	
	private int numOfSolenoids = RobotMap.FIRE_SOLENOIDS.length;
	private Solenoid[] solenoids;
	public boolean firing;
	
	public TempCatapultSubsystem(){
		solenoids = new Solenoid[numOfSolenoids];
		
		for(int i=0; i<solenoids.length; i++){
			solenoids[i] = new Solenoid(RobotMap.PCM_ID, RobotMap.FIRE_SOLENOIDS[i]);
		}
		closeAll();
	}
	
    public void initDefaultCommand() {
    	this.setDefaultCommand(new RestingCommand());
    }
    
    public void fireAll(){
    	for(int i=0; i<solenoids.length; i++){
    		solenoids[i].set(true);
    	}
    	firing = true;
    }
    
    public void fire(int barrelID){
    	solenoids[barrelID].set(true);
    	firing = true;
    }
    
    public void closeAll(){
    	for(int i=0; i<solenoids.length; i++){
    		solenoids[i].set(false);
    	}
    	firing = false;
    }
    
    public void close(int barrelID){
    	solenoids[barrelID].set(false);
    	firing = false;
    }
    
}

