// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.TraverseClimber;
import frc.robot.subsystems.ClimberArms;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  // This commented code is for the command that does traverse climbing. 
  // private final TraverseClimber climb = new TraverseClimber(new ClimberArms());
  
  private final ClimberArms climber = new ClimberArms();
  private final TraverseClimber climb = new TraverseClimber(climber);
  private final XboxController driveController = new XboxController(5);
  private JoystickButton xboxButtonA = new JoystickButton(driveController, 1); // A Button
  private JoystickButton xboxButtonB = new JoystickButton(driveController, 2); // B Button
  private JoystickButton xboxRightBumper = new JoystickButton(driveController, 6); // Right Bumper

  public enum State {
    EXTEND, RETRACT;
  }

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    xboxRightBumper.whileHeld( () -> {climber.rotateMotor();});
    //xboxRightBumper.whilePressed(rotateTimedMotor(2.0));
    //xboxButtonA.whenPressed(releasePiston(piston));
    xboxButtonA.whenPressed( () -> {climber.toggleState(climber.getP1());});
    //xboxButtonB.whenPressed(releasePiston(piston2));
    xboxButtonB.whenPressed( () -> {climber.toggleState(climber.getP2());});
    //xboxButtonY.whenPressed(contractPiston);


    // The full command of climbing. Turn Motor CCW -> Release P2 -> Contract P2 -> Turn Motor CCW -> Release P1
    //xboxButtonA.whenPressed(climber);

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }

}
