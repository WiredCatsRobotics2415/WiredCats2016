package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private CANTalon IntakeMotor;
	private CANTalon SpinMotor;

	public IntakeSubsystem() {
		IntakeMotor = new CANTalon(RobotMap.ROTATE_INTAKE_TALON);
		SpinMotor = new CANTalon(RobotMap.SPIN_INTAKE_TALON);
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
    
    public void updateStatus(){
    	SmartDashboard.putNumber("Intake Motor Speed", SpinMotor.get());
    	if(SpinMotor.get() < 0)SmartDashboard.putString("Intake Status:", "INTAKING");
    	if(SpinMotor.get() > 0)SmartDashboard.putString("Intake Status:", "OUTTAKING");
    	if(SpinMotor.get() == 0)SmartDashboard.putString("Intake Status:", "STASIS");
    }
}

