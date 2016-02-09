package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SensorSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Ultrasonic ultra;

    public void SensorSubsystem(){
    	ultra = new Ultrasonic(RobotMap.ULTRASONIC[0], RobotMap.ULTRASONIC[1]);
    	
    	ultra.setAutomaticMode(true);
    }
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	public double getUltraInInches(){
		return ultra.getRangeInches();
	}
	
	public boolean withinRange(double distance){
		if(getUltraInInches() <= distance) return true;
		return false;
	}
	
	public void updateStatus(){
		SmartDashboard.putNumber("Ultrasonic in Inches", getUltraInInches());
		SmartDashboard.putBoolean("10 Inch Range", withinRange(10));
		SmartDashboard.putBoolean("20 Inch Range", withinRange(20));
	}
    
    
}

