// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.subsystems.Catapult;
import frc.subsystems.Drivetrain;
import frc.subsystems.Intake;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  Catapult catapult;
  Drivetrain drive;
  OI oi;
  Compressor compressor;
  Intake intake;
  PowerDistribution pdp;
  
  @Override
  public void robotInit() {
    catapult = new Catapult();
    drive = new Drivetrain();
    oi = new OI();
    intake = new Intake();

    compressor = new Compressor(PneumaticsModuleType.CTREPCM);
    pdp = new PowerDistribution();
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  //new controller map:
  //right trigger/bumper: catapult fire/close
  //left trigger/bumper: intakes deploy/retract
  //bottom button on right side: toggle intake
  //both joysticks or one joystick: acarde drive
  @Override
  public void teleopPeriodic() {
    System.out.println("E");
    drive.drive(oi.getThrottle(), oi.getRotation());
    if(oi.getShooterButton(4)){
        catapult.fire();
        System.out.println("Catapult fire");
    } else if(oi.getShooterButton(2)){
        catapult.close();
        System.out.println("Catapult close");
    }

    if (oi.getShooterButton(3)) {
      intake.deployIntake();
      System.out.println("Intake deployed");
    }
    if (oi.getShooterButton(1)) {
      intake.retractIntake();
      System.out.println("Intake retracted");
    }

    if (oi.getShooterButton(7)) {
      intake.toggleIntake();
      System.out.println("Intake toggled");
    }
  }
}
