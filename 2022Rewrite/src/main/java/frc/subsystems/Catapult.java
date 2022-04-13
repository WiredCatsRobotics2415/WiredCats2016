package frc.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.RobotMap;

public class Catapult {
    Solenoid[] solenoids;

    public Catapult() {
        solenoids = new Solenoid[2];
        solenoids[0] = new Solenoid(PneumaticsModuleType.CTREPCM, RobotMap.CATAPULT_SOLENOID_1);
        solenoids[1] = new Solenoid(PneumaticsModuleType.CTREPCM, RobotMap.CATAPULT_SOLENOID_2);
    }

    public void fire() {
        for (Solenoid solenoid : solenoids){
            solenoid.set(true);
        }
    }
    
    public void close() {
        for (Solenoid solenoid : solenoids){
            solenoid.set(false);
        }
    }
    
    public void fireOne(int shooter) {
        solenoids[shooter].set(true);
    }
    
    public void closeOne(int shooter) {
        solenoids[shooter].set(false);
    }
}
