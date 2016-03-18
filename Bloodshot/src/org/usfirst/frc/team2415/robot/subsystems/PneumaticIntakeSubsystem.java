package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PneumaticIntakeSubsystem extends Subsystem {
    
    private Solenoid interior, exterior;
    private DigitalInput leftIR, rightIR;
    private CANTalon intakeMotor;
    public boolean[] intakeState;

    public void PneumaticIntakeSubsystem(){
    	interior = new Solenoid(RobotMap.INTAKE_SOLENOID_1);
    	exterior = new Solenoid(RobotMap.INTAKE_SOLENOID_2);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public boolean[] setIntakeState(String state){
    	switch (state){
    	case "Interior":
    		return new boolean[]{false,false};
    	case "Intake":
    		return new boolean[]{true,false};
    	case "Ground":
    		return new boolean[]{true,true};
    	default:
    		return new boolean[]{false,false};
    	}
    }
    
    public String getIntakeState(boolean[] intakeState){
    	if(this.intakeState.equals(new boolean[]{false,false})) {
    		return "Interior";
   		} else if(this.intakeState.equals(new boolean[]{true,false})) {
   			return "Intake";
   		} else if(this.intakeState.equals(new boolean[]{true,true})) {
   			return "Interior";
   		} else {
   			return "ERROR";
    	}
    }
    
    public void setPivot(boolean[] intakeState){
    	interior.set(intakeState[0]);
    	interior.set(intakeState[1]);
    }
    
    public void setIntakeSpeed(double speed){
    	intakeMotor.set(speed);
    }
    
    public void getIntakeSpeed(){
    	intakeMotor.get();
    }
    
    public boolean getIR(){
    	return (leftIR.get() || rightIR.get());
    }
    
    public double getIntakeCurrent(){
    	return intakeMotor.getOutputCurrent();
    }
}

