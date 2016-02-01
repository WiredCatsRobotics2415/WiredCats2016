package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDIntakeSubsystem extends PIDSubsystem {

    // Initialize your subsystem here

	private CANTalon IntakeMotor;
	private CANTalon SpinMotor;
	private Encoder IntakeEncoder;
	private final double ENCODER_TO_DEG = 1;
	
	
	
    public PIDIntakeSubsystem(double p, double i, double d, double f) {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super("Intake Subsystem", p, i, d, f);
    	
    	IntakeMotor = new CANTalon(RobotMap.ROTATE_INTAKE_TALON);
    	SpinMotor = new CANTalon(RobotMap.SPIN_INTAKE_TALON);
    	
    	setAbsoluteTolerance(0.05);
    	enable();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return IntakeEncoder.get() * ENCODER_TO_DEG;
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	IntakeMotor.pidWrite(output);
    }
    
    public void changeSetpoint(float desiredAngle){
    	setSetpoint(desiredAngle);
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
    	return IntakeEncoder.get();
    }
    
    public double getIntakeVoltage(){
    	return IntakeMotor.getOutputVoltage();
    }
    
    public void zeroEncoder(){
    	IntakeEncoder.reset();
    }
    
    public void updateStatus(){
    	SmartDashboard.putNumber("Intake Motor Speed", SpinMotor.get());
    	if(SpinMotor.get() < 0)SmartDashboard.putString("Intake Status:", "INTAKING");
    	if(SpinMotor.get() > 0)SmartDashboard.putString("Intake Status:", "OUTTAKING");
    	if(SpinMotor.get() == 0)SmartDashboard.putString("Intake Status:", "STASIES");
    	SmartDashboard.putNumber("Current Setpoint", getSetpoint());
    	SmartDashboard.putNumber("Current Angle", IntakeEncoder.get());
    }
    
    
}
