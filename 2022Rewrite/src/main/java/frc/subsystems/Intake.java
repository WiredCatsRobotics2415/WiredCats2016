package frc.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import frc.robot.RobotMap;

public class Intake {
    private Talon spintakeTalon;
    private Solenoid longPiston1, longPiston2, shortPiston1, shortPiston2;
    private boolean intakesRunning = false;
    
    public Intake() {
        spintakeTalon = new Talon(RobotMap.SPIN_INTAKE_TALON);
        longPiston1 = new Solenoid(PneumaticsModuleType.CTREPCM, RobotMap.LONG_SOLENOID[0]);
    	longPiston2 = new Solenoid(PneumaticsModuleType.CTREPCM, RobotMap.LONG_SOLENOID[1]);
    	shortPiston1 = new Solenoid(PneumaticsModuleType.CTREPCM, RobotMap.SHORT_SOLENOID[0]); //forward
    	shortPiston2 = new Solenoid(PneumaticsModuleType.CTREPCM, RobotMap.SHORT_SOLENOID[1]); //backwards
    }

    public void toggleIntake() {
        if (intakesRunning == true) {
            spintakeTalon.set(0.75);
            intakesRunning = true;
        } else {
            spintakeTalon.set(0);
            intakesRunning = false;
        }
    }

    public void deployIntake() { //VERIFY
        longPiston1.set(true);
    	longPiston2.set(false);
    	shortPiston1.set(false);
    	shortPiston2.set(true);
    }

    public void retractIntake() { //VERIFY
        longPiston1.set(false);
    	longPiston2.set(true);
    	shortPiston1.set(false);
    	shortPiston2.set(true);
    }
}
