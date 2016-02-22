package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;
import org.usfirst.frc.team2415.robot.catapultcommands.*;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class CatapultSubsystem extends Subsystem {
	
	public enum Solenoids {	TOP_LEFT_FIRE, TOP_RIGHT_FIRE, BOT_LEFT_FIRE,
							BOT_RIGHT_FIRE, ALL_FIRE
	}
	
	
	private Solenoid[] solenoids;
	public boolean firing;
	public boolean isShooting;
	
	public CatapultSubsystem(){
		solenoids = new Solenoid[2];
		
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
    	solenoids[0].set(false);
    	solenoids[1].set(false);
    	firing = false;
    }
    
    public void close(int barrelID){
    	solenoids[barrelID].set(false);
    	firing = false;
    }
    
    public boolean getIsShooting() {
		return isShooting;
	}

	public void setIsShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}
    
    public void updateStatus() {
		SmartDashboard.putBoolean("Are The Solenoids Extended", firing);
		SmartDashboard.putBoolean("Am I Shooting Right Now", isShooting);
	}

    
}

