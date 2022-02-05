// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ClimberArms;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class TraverseClimber extends CommandBase {
   private ClimberArms climber = new ClimberArms();
   private static final double TIME1 = 0.0; // time it takes to rotate the motor from stage 1 to 2
   private static final double TIME2 = 0.0; // time it takes to rotate the motor from stage 2 to 3

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TraverseClimber(ClimberArms climber) {
    this.climber = climber;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      climber.rotateTimedMotor(TIME1);
      climber.toggleState(climber.getP2());
      climber.toggleState(climber.getP2());
      climber.rotateTimedMotor(TIME2);
      climber.toggleState(climber.getP1());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
