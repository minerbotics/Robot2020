/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;

public class Climber extends SubsystemBase {

  private final Spark climbMotor;
  /**
   * Creates a new Climber.
   */
  public Climber() {
    climbMotor = new Spark(ClimbConstants.kClimbMotorPort);
  }

  public void winch() {
    climbMotor.set(-1);
  }

  public void stop() {
    climbMotor.set(0);
  }

  public void reverse() {
    climbMotor.set(1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
