// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Magazine extends SubsystemBase {
  /** Creates a new Magazine. */
  
  Spark magazine; // we don't know the motorcontroller

  public Magazine() {
    magazine = new Spark(RobotMap.MAGAZINE);
  }

  public void set(double speed){
    magazine.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
