package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.Robot;
import org.usfirst.frc.team2415.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PivotSubsystem extends Subsystem {
    
    private Solenoid longPiston1, longPiston2, shortPiston1, shortPiston2;
    public boolean[] intakeState;
    
    public static final byte INTERIOR = 0;
    public static final byte INTAKE = 1;
    public static final byte OUTTAKE = 2;
    public static final byte GROUND = 3;

    public PivotSubsystem(){
    	longPiston1 = new Solenoid(RobotMap.PCM_ID, RobotMap.LONG_SOLENOID[0]);
    	longPiston2 = new Solenoid(RobotMap.PCM_ID, RobotMap.LONG_SOLENOID[1]);
    	shortPiston1 = new Solenoid(RobotMap.PCM_ID, RobotMap.SHORT_SOLENOID[0]); //forward
    	shortPiston2 = new Solenoid(RobotMap.PCM_ID, RobotMap.SHORT_SOLENOID[1]); //backwards
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setPivot(byte state){
    	if(state == this.INTAKE){
    		longPiston1.set(true);
    		longPiston2.set(false);
    		shortPiston1.set(false);
    		shortPiston2.set(true);
    	}
    	if(state == this.GROUND){
    		longPiston1.set(true);
    		longPiston2.set(false);
    		shortPiston1.set(true);
    		shortPiston2.set(false);
    	}
    	if(state == this.INTERIOR){
    		longPiston1.set(false);
    		longPiston2.set(true);
    		shortPiston1.set(false);
    		shortPiston2.set(true);
    	}
    	if(state == this.OUTTAKE){
    		longPiston1.set(false);
    		longPiston2.set(true);
    		shortPiston1.set(true);
    		shortPiston2.set(false);
    	}
    }
    
    public void updateStatus(){
//    	SmartDashboard.putString("Intake State", getIntakeState());
    	SmartDashboard.putBoolean("Forward Short Piston", shortPiston1.get());
    	SmartDashboard.putBoolean("Backward Short Piston", shortPiston2.get());
    }
}
