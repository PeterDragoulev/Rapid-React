// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ShooterToggleCommand extends CommandBase {
  /** Creates a new ShooterToggleCommand. */
  public ShooterToggleCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.SHOOTER);
    addRequirements(Robot.MAGAZINE);
  }

  double time = 0;
  final double SPEED = 1d; 

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time = Robot.TIMER.get();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.SHOOTER.shoot(SPEED);
    if (Robot.TIMER.get() >= (time + 2.5)) {
      Robot.MAGAZINE.set(1);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.SHOOTER.shoot(0);
    Robot.MAGAZINE.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Robot.TIMER.get() >= (time + 5)) {
      return true;
    }
    return false;
  }
}
