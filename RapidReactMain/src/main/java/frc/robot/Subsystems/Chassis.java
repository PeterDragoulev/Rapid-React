// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Chassis extends SubsystemBase {

    DifferentialDrive drive;

    public Chassis() {

        WPI_TalonSRX rightLead = new WPI_TalonSRX(RobotMap.CHASSIS_MOTOR_PORTS[0]);
        WPI_TalonSRX rightFollowOne = new WPI_TalonSRX(RobotMap.CHASSIS_MOTOR_PORTS[1]);
        WPI_TalonSRX rightFollowTwo = new WPI_TalonSRX(RobotMap.CHASSIS_MOTOR_PORTS[2]);

        WPI_TalonSRX leftLead = new WPI_TalonSRX(RobotMap.CHASSIS_MOTOR_PORTS[3]);
        WPI_TalonSRX leftFollowOne = new WPI_TalonSRX(RobotMap.CHASSIS_MOTOR_PORTS[4]);
        WPI_TalonSRX leftFollowTwo = new WPI_TalonSRX(RobotMap.CHASSIS_MOTOR_PORTS[5]);

        rightFollowOne.follow(rightLead);
        rightFollowTwo.follow(rightLead);

        leftFollowOne.follow(leftLead);
        leftFollowTwo.follow(leftLead);

        MotorControllerGroup right = new MotorControllerGroup(leftLead, leftFollowOne, leftFollowTwo);
        MotorControllerGroup left = new MotorControllerGroup(rightLead, rightFollowOne, rightFollowTwo);

        drive = new DifferentialDrive(left, right);
    }

    public void move(double speed, double turn) {
        drive.arcadeDrive(turn, speed);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
