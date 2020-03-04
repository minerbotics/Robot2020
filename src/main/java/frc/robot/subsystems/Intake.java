/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

  private final Spark intakeMotor;
  /**
   * Creates a new ExampleSubsystem.
   */
  public Intake() {

    intakeMotor = new Spark(Constants.IntakeConstants.kIntakeMotorPort);
  }

  public void harvest() {
    intakeMotor.set(-1);
  }

  public void stop() {
    intakeMotor.set(0);
  }

  public void eject() {
    intakeMotor.set(0.75);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
