// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;


public class Intake extends SubsystemBase {
  /** Creates a new Intake. */
  DoubleSolenoid solenoid;
  PWMSparkMax intake;
  Value state;

  public Intake() {
    //For pistons
    solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RobotMap.INTAKE[0], RobotMap.INTAKE[1]);
    state = Value.kOff; //3 possible states: kOff, kForward, kReverse
    solenoid.set(state);

    //For intake motor
    intake = new PWMSparkMax(RobotMap.INTAKE[2]);
  }

  public void intake(){
    intake.set(1);
  }

  public Value getSolenoidState(){
    return solenoid.get();
  }

  public void togglePneumatics(){
    state = (state == Value.kForward) ? Value.kReverse : Value.kForward;
    solenoid.set(state);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
