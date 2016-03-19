package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;
import org.usfirst.frc.team2415.robot.intakecommands.PneumaticIntakeCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PneumaticIntakeSubsystem extends Subsystem {
    
    private DoubleSolenoid longPiston, shortPiston;
    private DigitalInput leftIR, rightIR;
    private CANTalon intakeMotor;
    public DoubleSolenoid.Value[] intakeState;

    public void PneumaticIntakeSubsystem(){
    	longPiston = new DoubleSolenoid(RobotMap.LONG_SOLENOID[0], RobotMap.LONG_SOLENOID[1]);
    	shortPiston = new DoubleSolenoid(RobotMap.SHORT_SOLENOID[0], RobotMap.SHORT_SOLENOID[1]);
    	intakeMotor = new CANTalon(RobotMap.SPIN_INTAKE_TALON);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new PneumaticIntakeCommand("Interior"));
    }
    
    public DoubleSolenoid.Value[] setIntakeState(String state){
    	switch (state){
    	case "Interior":
    		return new DoubleSolenoid.Value[]{Value.kReverse,Value.kReverse};
    	case "Intake":
    		return new DoubleSolenoid.Value[]{Value.kForward,Value.kReverse};
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
    
    public void setIntakeSpeed(double speed){
    	intakeMotor.set(speed);
    }
    
    public double getIntakeSpeed(){
    	return intakeMotor.get();
    }
    
    public boolean getIR(){
    	return (leftIR.get() || rightIR.get());
    }
    
    public double getIntakeCurrent(){
    	return intakeMotor.getOutputCurrent();
    }
    
    public void updateStatus(){
    	SmartDashboard.putString("Intake State", getIntakeState(intakeState));
    	SmartDashboard.putNumber("Intake Speed", getIntakeSpeed());
    	SmartDashboard.putNumber("Intake Current", getIntakeCurrent());
    }
}

