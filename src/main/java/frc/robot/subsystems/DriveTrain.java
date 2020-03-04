/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveTrain extends SubsystemBase {

  private final WPI_VictorSPX front_left;
  private final WPI_VictorSPX back_left;
  private final WPI_VictorSPX front_right;
  private final WPI_VictorSPX back_right;

  private final DifferentialDrive m_drive;

  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    super();
    front_left = new WPI_VictorSPX(DriveConstants.kLeftFrontMotorPort); 
    back_left = new WPI_VictorSPX(DriveConstants.kLeftBackMotorPort);
    front_right = new WPI_VictorSPX(DriveConstants.kRightFrontMotorPort);
    back_right = new WPI_VictorSPX(DriveConstants.kRightBackMotorPort);

    back_left.follow(front_left);
    back_left.setInverted(false);
    back_right.follow(front_right);

    m_drive = new DifferentialDrive(front_left, front_right);
  }
  
  public void xboxDrive(XboxController controller) {
    m_drive.arcadeDrive(-controller.getY(Hand.kLeft), controller.getX(Hand.kLeft));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void arcadeDrive(double forward, double rotation) {
    rotation = rotation * 0.875;
    m_drive.arcadeDrive(forward, rotation, true);
  }
}
