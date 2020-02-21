/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.Climb;
import frc.robot.commands.Eject;
import frc.robot.commands.Harvest;
import frc.robot.commands.ResetClimb;
import frc.robot.commands.StopClimb;
import frc.robot.commands.StopHarvest;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain m_driveTrain;
  private final Climber m_climber;
  private final Intake m_intake;

  private final ArcadeDrive m_driveCommand;
  private final Climb m_climbCommand;
  private final StopClimb m_stopClimbCommand;
  private final ResetClimb m_resetClimbCommand;
  private final Harvest m_harvestCommand;
  private final StopHarvest m_stopHarvestCommand;
  private final Eject m_ejectCommand;
 
  XboxController m_driverController;

  JoystickButton m_climbButton;
  JoystickButton m_resetClimbButton;
  JoystickButton m_harvestButton;
  JoystickButton m_ejectButton;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_driveTrain = new DriveTrain();
    m_climber = new Climber();
    m_intake = new Intake();
    m_driverController = new XboxController(Constants.IOConstants.kControllerPort);

    m_driveCommand = new ArcadeDrive(m_driveTrain, m_driverController);
    m_driveTrain.setDefaultCommand(m_driveCommand);

    m_climbCommand = new Climb(m_climber);
    m_stopClimbCommand = new StopClimb(m_climber);
    m_resetClimbCommand = new ResetClimb(m_climber);

    m_harvestCommand = new Harvest(m_intake);
    m_stopHarvestCommand = new StopHarvest(m_intake);
    m_ejectCommand = new Eject(m_intake);

    m_climbButton = new JoystickButton(m_driverController, Constants.IOConstants.kAButton);
    m_resetClimbButton = new JoystickButton(m_driverController, Constants.IOConstants.kBButton);
    m_harvestButton = new JoystickButton(m_driverController, Constants.IOConstants.kRBButton);
    m_ejectButton = new JoystickButton(m_driverController, Constants.IOConstants.kLBButton);

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    m_climbButton.whenPressed(m_climbCommand);
    m_climbButton.whenReleased(m_stopClimbCommand);
    m_resetClimbButton.whenPressed(m_resetClimbCommand);
    m_resetClimbButton.whenReleased(m_stopClimbCommand);

    m_harvestButton.whenPressed(m_harvestCommand);
    m_harvestButton.whenReleased(m_stopHarvestCommand);
    m_ejectButton.whenPressed(m_ejectCommand);
    m_ejectButton.whenReleased(m_stopHarvestCommand);
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_driveCommand;
  }
}
