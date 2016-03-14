package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.PID;
import org.usfirst.frc.team2415.robot.RobotMap;
import org.usfirst.frc.team2415.robot.intakecommands.IntakeCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//public PID pid = new PID(.05,0,.001);
	public PID pid = new PID(.05, 0,0);//new PID(.05,.01, 0);
	
	private CANTalon PivotMotor;
	private CANTalon SpinMotor;
	
	private Encoder intakeEncoder;
	
	private DigitalInput leftIR, rightIR;

	public double intakeError;
	public double intakeOutput;
	public static double intakeSetpoint;
	
	private double DEG_MATH = 1;	//DEG_MATH is an undecided constant that
//									turns encoder values into intake angles

	public IntakeSubsystem() {
		PivotMotor = new CANTalon(RobotMap.PIVOT_INTAKE_TALON);
		SpinMotor = new CANTalon(RobotMap.SPIN_INTAKE_TALON);
		
		intakeEncoder = new Encoder(RobotMap.INTAKE_ENCODER[0],RobotMap.INTAKE_ENCODER[1]);
		leftIR = new DigitalInput(RobotMap.INTAKE_IR_LEFT);
		rightIR = new DigitalInput(RobotMap.INTAKE_IR_RIGHT);
		
		LiveWindow.addActuator("Intake Subsystem", "Pivot Motor", PivotMotor);
		LiveWindow.addActuator("Intake Subsystem", "Spin Motor", SpinMotor);
		LiveWindow.addSensor("Intake Subsystem", "Pivot Encoder", intakeEncoder);
		LiveWindow.addSensor("Intake Subsystem", "Left IR Sensor", leftIR);
		LiveWindow.addSensor("Intake Subsystem", "Right IR Sensor", rightIR);
	}
	
    public void initDefaultCommand() {
    }
    
    public void setIntakeMotor(double intakeMotor){
    	PivotMotor.set(intakeMotor);
    }
    public void setSpinMotor(double spinMotor){
    	SpinMotor.set(spinMotor);
    }
    
    public void stopIntakeMotor(){
    	PivotMotor.set(0);
    }
    public void stopSpinMotor(){
    	SpinMotor.set(0);
    }
    
    public double getAngle(){
    	return intakeEncoder.get();
    }
    
    public void resetEncoder(){
    	intakeEncoder.reset();
    }
    
    public boolean getIR(){
    	return (leftIR.get() || rightIR.get());
    }
    
    public double getIntakeCurrent(){
    	return PivotMotor.getOutputCurrent();
    }
    
    public void updateStatus(){
    	SmartDashboard.putNumber("Intake Angle", (intakeEncoder.get()*360)/128);
    	SmartDashboard.putNumber("Intake Encoder", getAngle());
    	SmartDashboard.putNumber("PID Error Value", intakeError);
    	SmartDashboard.putNumber("PID Output Value", intakeOutput);
    	SmartDashboard.putNumber("PID Setpoint", intakeSetpoint);
    	SmartDashboard.putNumber("Intake Talon", PivotMotor.get());
		SmartDashboard.putNumber("Pivot Talon Current", PivotMotor.getOutputCurrent());
    	SmartDashboard.putBoolean("Is Intake Button Pressed?", getIR());
    }
}

