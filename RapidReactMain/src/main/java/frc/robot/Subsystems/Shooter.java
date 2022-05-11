// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */

  // Instances for turret
  Encoder turretEncoder;
  WPI_VictorSPX turret;
  final double GEAR_ROTATIONS_PER_PULSE_TURRET = 1d / (1024 * 197.5d); // DO NOT CHANGE THIS
  final double TURRET_SPEED = 0.1; // Need to change this

  // Instances for Shooter
  PWMSparkMax shooters[];

  public Shooter() {
    // For turret
    turret = new WPI_VictorSPX(RobotMap.TURRET_PORTS[0]);
    turretEncoder = new Encoder(RobotMap.TURRET_PORTS[1], RobotMap.TURRET_PORTS[2]);
    turretEncoder.setDistancePerPulse(GEAR_ROTATIONS_PER_PULSE_TURRET);

    // For shooter
    shooters[0] = new PWMSparkMax(RobotMap.SHOOTER[0]);
    shooters[1] = new PWMSparkMax(RobotMap.SHOOTER[1]);
    shooters[1].setInverted(true);
  }

  public void shoot(double speed) {
    if (hasAimed()) {
      shooters[0].set(speed);
      shooters[1].set(speed);
    } else {
      aim();
    }
  }

  public void aim() {
    if (Robot.LIMELIGHT.getHorizontalOffset() > 10) {
      moveTurret(-TURRET_SPEED);
    } else if (Robot.LIMELIGHT.getHorizontalOffset() < -10) {
      moveTurret(TURRET_SPEED);
    }
  }

  public double getTurretRotations() {
    return turretEncoder.getDistance();
  }

  public void moveTurret(double speed) {
    turret.set(speed);
  }

  public void resetTurret() {// Resets the position of the turret to default
    double rotations = this.getTurretRotations();
    if (rotations > 0.1) {
      moveTurret(TURRET_SPEED);
    } else if (rotations < 0.1) {
      moveTurret(-TURRET_SPEED);
    } else {
      moveTurret(0);
    }
  }

  public boolean hasAimed() {
    if (Robot.LIMELIGHT.getHorizontalOffset() > 10 || Robot.LIMELIGHT.getHorizontalOffset() < -10) {
      return false;
    } else {
      return true;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
