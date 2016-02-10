package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 */
public class PneumaticIntakeSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private CANTalon SpinMotor2;
	private Solenoid intakeSolenoid;
	private Solenoid outtakeSolenoid;
	private boolean intakeState;
	
	/*TODO: make 4 private variables:
	 * 2 of type Solenoid--one that extends and one that contracts 
	 * 1 of type CANTalon for the spin motor
	 * 1 of type boolean that will determine the state of the intake
	 * hint 1: you're probably gonna need to import the Solenoid and CANTalon classes
	 */
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	SpinMotor2 = new CANTalon(RobotMap.SPIN_INTAKE_TALON2);
    	intakeSolenoid = new Solenoid(RobotMap.PCM_ID, RobotMap.INTAKE_SOLENOID);
    	outtakeSolenoid = new Solenoid(RobotMap.PCM_ID, RobotMap.OUTTAKE_SOLENOID);
    	
    	/* TODO: initialize the solenoids and cantalons here and make the intake contract
    	 * hint 1: if you need help initializing solenoids, go here:
    	 * 	http://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/Solenoid.html
    	 * 	and look at the constructor with two inputs
    	 * hint 2: remember, port values always come from RobotMap 
    	 */
    }
    
    public void extend(){
    	/*TODO: make the extend solenoid on and the contract solenoid off 
    	 * 		and set the intake state boolean to true
    	 */
    	intakeSolenoid.set(true);
    	outtakeSolenoid.set(false);
    	intakeState = true;
    }
    
    public void contract(){
    	/*TODO: make the contract solenoid off and the extend solenoid on
    	 * 		and set the intake state boolean to false
    	 */
    	intakeSolenoid.set(false);
    	outtakeSolenoid.set(true);
    	intakeState = false;
    }
    
    public void setSpinMotor(double spinMotor){
    	/*TODO: make the function intake a double and set the speed of the
    	 * 		spin motor to this double
    	 */
       	SpinMotor2.set(spinMotor);
    }
    
    public boolean isExtended(){
    	//TODO: make this function return the state of the intake motor
    	return intakeState;
    }
    
}

