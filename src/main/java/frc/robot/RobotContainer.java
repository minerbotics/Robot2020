/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.Climb;
import frc.robot.commands.DumpAndRun;
import frc.robot.commands.Eject;
import frc.robot.commands.Harvest;
import frc.robot.commands.LowerArm;
import frc.robot.commands.RaiseArm;
import frc.robot.commands.ResetClimb;
import frc.robot.commands.RunAndDump;
import frc.robot.commands.RunAndDumpAndRun;
import frc.robot.commands.RunBackward;
import frc.robot.commands.RunForward;
import frc.robot.commands.StopArm;
import frc.robot.commands.StopClimb;
import frc.robot.commands.StopHarvest;
import frc.robot.commands.ZagLeftAndDump;
import frc.robot.commands.ZagRightAndDump;
import frc.robot.subsystems.Arm;
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
  // The robot's subsystems
  private final DriveTrain m_driveTrain;
  private final Climber m_climber;
  private final Intake m_intake;
  private final Arm m_arm;

  // The robot's commands
  private final ArcadeDrive m_driveCommand;
  private final Climb m_climbCommand;
  private final StopClimb m_stopClimbCommand;
  private final ResetClimb m_resetClimbCommand;
  private final Harvest m_harvestCommand;
  private final StopHarvest m_stopHarvestCommand;
  private final Eject m_ejectCommand;
  private final RaiseArm m_raiseArmCommand;
  private final StopArm m_stopArmCommand;
  private final LowerArm m_lowerArmCommand;
  private final RunAndDump m_runAndDumpCommand;
  private final DumpAndRun m_dumpAndRunCommand;
  private final ZagLeftAndDump m_zagLeftAndDumpCommand;
  private final ZagRightAndDump m_zagRightAndDumpCommand;
  private final RunForward m_runForwardCommand;
  private final RunBackward m_runBackwardCommand;
  private final RunAndDumpAndRun m_runAndDumpAndRunCommand;
 
  // The Xbox controller
  XboxController m_driverController;

  //The command buttons
  JoystickButton m_climbButton;
  JoystickButton m_resetClimbButton;
  JoystickButton m_harvestButton;
  JoystickButton m_ejectButton;
  JoystickButton m_raiseArmButton;
  JoystickButton m_lowerArmButton;

  public static SendableChooser<Command> m_chooser;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_driveTrain = new DriveTrain();
    m_climber = new Climber();
    m_intake = new Intake();
    m_arm = new Arm();
    m_driverController = new XboxController(Constants.IOConstants.kControllerPort);

    m_driveCommand = new ArcadeDrive(m_driveTrain, m_driverController);
    m_driveTrain.setDefaultCommand(m_driveCommand);

    m_climbCommand = new Climb(m_climber);
    m_stopClimbCommand = new StopClimb(m_climber);
    m_resetClimbCommand = new ResetClimb(m_climber);

    m_harvestCommand = new Harvest(m_intake);
    m_stopHarvestCommand = new StopHarvest(m_intake);
    m_ejectCommand = new Eject(m_intake);

    m_raiseArmCommand = new RaiseArm(m_arm);
    m_stopArmCommand = new StopArm(m_arm);
    m_lowerArmCommand = new LowerArm(m_arm);

    m_runAndDumpCommand = new RunAndDump(m_driveTrain, m_arm, m_intake);
    m_dumpAndRunCommand = new DumpAndRun(m_driveTrain, m_arm, m_intake);
    m_zagLeftAndDumpCommand = new ZagLeftAndDump(m_driveTrain, m_arm, m_intake);
    m_zagRightAndDumpCommand = new ZagRightAndDump(m_driveTrain, m_arm, m_intake);
    m_runForwardCommand = new RunForward(m_driveTrain, m_arm);
    m_runBackwardCommand = new RunBackward(m_driveTrain, m_arm);
    m_runAndDumpAndRunCommand = new RunAndDumpAndRun(m_driveTrain, m_arm, m_intake);

    m_climbButton = new JoystickButton(m_driverController, Constants.IOConstants.kAButton);
    m_resetClimbButton = new JoystickButton(m_driverController, Constants.IOConstants.kBButton);
    m_harvestButton = new JoystickButton(m_driverController, Constants.IOConstants.kRBButton);
    m_ejectButton = new JoystickButton(m_driverController, Constants.IOConstants.kLBButton);
    m_raiseArmButton = new JoystickButton(m_driverController, Constants.IOConstants.kYButton);
    m_lowerArmButton = new JoystickButton(m_driverController, Constants.IOConstants.kXButton);

    m_chooser = new SendableChooser<Command>();
    m_chooser.setDefaultOption("RunAndDump", m_runAndDumpCommand);
    m_chooser.setDefaultOption("RunAndDumpAndRun", m_runAndDumpAndRunCommand);
    m_chooser.addOption("DumpAndRun", m_dumpAndRunCommand);
    m_chooser.addOption("ZagLeftAndRun", m_zagLeftAndDumpCommand);
    m_chooser.addOption("ZagRightAndRun", m_zagRightAndDumpCommand);
    m_chooser.addOption("RunForward", m_runForwardCommand);
    m_chooser.addOption("RunBackward", m_runBackwardCommand);

    Shuffleboard.getTab("ComboBox Chooser").add(m_chooser);

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

    m_raiseArmButton.whenPressed(m_raiseArmCommand);
    m_raiseArmButton.whenReleased(m_stopArmCommand);
    m_lowerArmButton.whenPressed(m_lowerArmCommand);
    m_lowerArmButton.whenReleased(m_stopArmCommand);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_chooser.getSelected();
  }
}
