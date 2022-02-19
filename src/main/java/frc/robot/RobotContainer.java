// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.AddressableLEDs;
import frc.robot.subsystems.MotorBoardSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  Joystick j = new Joystick(0);
  public AddressableLEDs m_AddressableLEDs = new AddressableLEDs();

  private final MotorBoardSubsystem m_motorBoard = new MotorBoardSubsystem();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_motorBoard.setDefaultCommand(
      new InstantCommand(() -> setPowers(-j.getRawAxis(1), -j.getRawAxis(5), (j.getRawAxis(3)-j.getRawAxis(2)))
      , m_motorBoard, m_AddressableLEDs));
    // Configure the button bindings
    configureButtonBindings();
  }

  private void setPowers(double s0, double s1, double t0) {
    m_motorBoard.spark0.set(s0);
    m_motorBoard.spark1.set(s1);
    m_motorBoard.talon0.set(t0);
    m_AddressableLEDs.update();
  }
  

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(j, Button.kA.value)
      .whenPressed(new InstantCommand(() -> m_motorBoard.solenoid0.toggle()));
    new JoystickButton(j, Button.kY.value)
      .whenPressed(new InstantCommand(() -> m_motorBoard.solenoid1.toggle()));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
