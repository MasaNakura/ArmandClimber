// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ClimberArms;
import frc.robot.subsystems.ClimberArms.State;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class TraverseClimber extends CommandBase {
  private ClimberArms climber = new ClimberArms();
  private int traverseCounter = 0;
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
  public void initialize() {
    climber.rotateMotor();
    if (climber.getState(climber.getP1()) != State.RETRACT) {
      climber.toggleState(climber.getP1());
    }
    if (climber.getState(climber.getP2()) != State.EXTEND) {
      climber.toggleState(climber.getP2());
    }
  }


  /*
  1: 
  P1: Retracted
  P2: Extended
  Hits first bar/(Hits Limit switch 1): 
    Retract P2
  2: 
  P1: Retracted
  P2: Retracted
  Hits second bar (Hits Limit switch 2): 
    Extend P1
    Retract P1
  3: 
  P1: Retracted
  P2: Retracted
  Hits Traveral Bar (Hits Limit switch 1): 
    Extend P2
  Stop Motor. 


  */
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    while (traverseCounter < 3) {
      if (!climber.getLimitSwitch1().get()) {
        traverseCounter++;    
        climber.toggleState(climber.getP2());
      } 
      if (!climber.getLimitSwitch2().get()) {
        traverseCounter++;
        climber.toggleState(climber.getP1());
        Timer time = new Timer();
        time.start();
        time.delay(0.5);
        climber.toggleState(climber.getP1());
      }
    }
    climber.stopMotor();
    /*climber.rotateTimedMotor(TIME1);
      climber.toggleState(climber.getP2());
      climber.toggleState(climber.getP2());
      climber.rotateTimedMotor(TIME2);
      climber.toggleState(climber.getP1());
     */
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
