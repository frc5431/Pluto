/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.List;

import frc.robot.components.*;
import frc.team5431.titan.core.robot.Component;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends frc.team5431.titan.core.robot.Robot<Robot> {
  public static enum Mode{
    DISABLED, AUTO, TELEOP, TEST
  }

  private Mode mode = Mode.DISABLED;

  private Climber climber;
  private Drivebase drivebase;
  private Elevator elevator;
  private Intake intake;
  private Teleop teleop;
  private Vision vision;
  private Dashboard dashboard;

  private List<Component<Robot>> components = List.of();

  @Override
  public void robotInit() {
    climber = new Climber();
    drivebase = new Drivebase();
    elevator = new Elevator();
    intake = new Intake();
    teleop = new Teleop();
    vision = new Vision();

    components = List.of(climber, drivebase, elevator, intake, teleop, vision, dashboard);
  }

  @Override
  public void robotPeriodic() {
    super.robotPeriodic();
    components.forEach((com)->com.tick(this));
  }

  @Override
  public void autonomousInit() {
    super.autonomousInit();
    mode = Mode.AUTO;
    components.forEach((com)->com.init(this));
  }

  @Override
  public void autonomousPeriodic() {
    super.autonomousPeriodic();
    components.forEach((com)->com.periodic(this));
  }

  @Override
  public void teleopInit() {
    super.teleopInit();
    mode = Mode.TELEOP;
    components.forEach((com)->com.init(this));
  }

  @Override
  public void teleopPeriodic() {
    super.teleopPeriodic();
    components.forEach((com)->com.periodic(this));
  }

  @Override
  public void testInit() {
    super.testInit();
    mode = Mode.TEST;
    components.forEach((com)->com.init(this));
  }
  @Override
  public void testPeriodic() {
    super.testPeriodic();
    components.forEach((com)->com.periodic(this));
  }

  @Override
  public List<Component<Robot>> getComponents() {
    return components;
  }

  /**
   * @return the mode
   */
  public Mode getMode() {
    return mode;
  }

  /**
   * @return the drivebase
   */
  public Drivebase getDrivebase() {
    return drivebase;
  }

  /**
   * @return the dashboard
   */
  public Dashboard getDashboard() {
    return dashboard;
  }

  /**
   * @return the teleop
   */
  public Teleop getTeleop() {
    return teleop;
  }
}
