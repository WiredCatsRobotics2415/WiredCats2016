package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.PID;
import org.usfirst.frc.team2415.robot.RobotMap;
import org.usfirst.frc.team2415.robot.intakecommands.IntakeCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//public PID pid = new PID(.05,0,.001);
	public PID pid = new PID(.03,0,0);
	
	private CANTalon IntakeMotor;
	private CANTalon SpinMotor;
	
	private Encoder intakeEncoder;
	
	private DigitalInput intakeButton;

	public double intakeError;
	public double intakeOutput;
	public static double intakeSetpoint;
	
	private double DEG_MATH = 1;	//DEG_MATH is an undecided constant that
//									turns encoder values into intake angles

	public IntakeSubsystem() {
		IntakeMotor = new CANTalon(RobotMap.ROTATE_INTAKE_TALON);
		SpinMotor = new CANTalon(RobotMap.SPIN_INTAKE_TALON);
		
		intakeEncoder = new Encoder(RobotMap.INTAKE_ENCODER[0],RobotMap.INTAKE_ENCODER[1]);
		intakeButton = new DigitalInput(RobotMap.INTAKE_STOPPER);
	}
	
    public void initDefaultCommand() {
    }
    
    public void setIntakeMotor(double intakeMotor){
    	IntakeMotor.set(-intakeMotor);
    }
    public void setSpinMotor(double spinMotor){
    	SpinMotor.set(spinMotor);
    }
    
    public void stopIntakeMotor(){
    	IntakeMotor.set(0);
    }
    public void stopSpinMotor(){
    	SpinMotor.set(0);
    }
    
    public double getAngle(){
    	return -intakeEncoder.get();
    }
    
    public double getVal(){
    	return intakeEncoder.get();
    }
    
    public void resetEncoder(){
    	intakeEncoder.reset();
    }
    
    public boolean getButton(){
    	return !intakeButton.get();
    }
    
    public double getInputVoltage(){
    	return SpinMotor.getBusVoltage();
    }
    
    public double getOutputVoltage(){
    	return SpinMotor.getOutputVoltage();
    }
    
    public void updateStatus(){
    	SmartDashboard.putNumber("Intake Encoder", getAngle()); //(intakeEncoder.get()*360)/128 for degrees
    	SmartDashboard.putNumber("PID Error Value", intakeError);
    	SmartDashboard.putNumber("PID Output Value", intakeOutput);
    	SmartDashboard.putNumber("PID Setpoint", intakeSetpoint);
    	SmartDashboard.putBoolean("Is Intake Button Pressed?", getButton());
    }
}

