package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PivotSubsystem extends Subsystem {
    
    private DoubleSolenoid longPiston, shortPiston;
    public DoubleSolenoid.Value[] intakeState;

    public PivotSubsystem(){
    	longPiston = new DoubleSolenoid(RobotMap.LONG_SOLENOID[0], RobotMap.LONG_SOLENOID[1]);
    	shortPiston = new DoubleSolenoid(RobotMap.SHORT_SOLENOID[0], RobotMap.SHORT_SOLENOID[1]);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public DoubleSolenoid.Value[] setIntakeState(String state){
    	switch (state){
    	case "Interior":
    		return new DoubleSolenoid.Value[]{Value.kReverse,Value.kReverse};
    	case "Intake":
    		return new DoubleSolenoid.Value[]{Value.kForward,Value.kReverse};
    	case "Outake":
    		return new DoubleSolenoid.Value[]{Value.kReverse,Value.kForward};
    	case "Ground":
    		return new DoubleSolenoid.Value[]{Value.kForward,Value.kForward};
    	default:
    		return new DoubleSolenoid.Value[]{Value.kOff,Value.kOff};
    	}
    }
    
    public String getIntakeState(DoubleSolenoid.Value[] intakeState){
    	if(this.intakeState.equals(new DoubleSolenoid.Value[]{Value.kReverse,Value.kReverse})) {
    		return "Interior";
   		} else if(this.intakeState.equals(new DoubleSolenoid.Value[]{Value.kForward,Value.kReverse})) {
   			return "Intake";
   		} else if(this.intakeState.equals(new DoubleSolenoid.Value[]{Value.kReverse,Value.kForward})) {
   			return "Outake";
   		} else if(this.intakeState.equals(new DoubleSolenoid.Value[]{Value.kForward,Value.kForward})) {
   			return "Ground";
   		} else {
   			return "ERROR";
    	}
    }
    
    public void setPivot(DoubleSolenoid.Value[] intakeState){
    	longPiston.set(intakeState[0]);
    	shortPiston.set(intakeState[1]);
    }
    
    public void updateStatus(){
    	SmartDashboard.putString("Intake State", getIntakeState(intakeState));
    }
}
