package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private CANTalon IntakeMotor;
	private CANTalon SpinMotor;
	
	private Encoder intakeEncoder;
	
	private double DEG_MATH = 0;	//DEG_MATH is an undecided constant that
//									turns encoder values into intake angles

	public IntakeSubsystem() {
		IntakeMotor = new CANTalon(RobotMap.ROTATE_INTAKE_TALON);
		SpinMotor = new CANTalon(RobotMap.SPIN_INTAKE_TALON);
		
		intakeEncoder = new Encoder(RobotMap.INTAKE_ENCODER[0],RobotMap.INTAKE_ENCODER[1]);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setIntakeMotor(double intakeMotor){
    	IntakeMotor.set(intakeMotor);
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
    	return intakeEncoder.get() * DEG_MATH;
    }
}

