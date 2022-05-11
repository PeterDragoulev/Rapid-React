package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
    // Change the following to actual camera heights
    final double LIMELIGHT_MOUNT_A = 16.258;
    final double LIMELIGHT_LENS_H = 82;

    final double GOAL_HEIGHT = 264.16; // Actual height of the goal is 264.16

    NetworkTable table;

    public Limelight() {
        table = NetworkTableInstance.getDefault().getTable("limelight");
    }

    // Returns the horizontal offset from crosshair to target
    public double getHorizontalOffset() {
        NetworkTableEntry horizontalOffset = table.getEntry("tx"); // tx is the code for the limelight that returns
                                                                   // offset
        return horizontalOffset.getDouble(0.0);
    }

    // Returns the vertical offset from crosshair to target
    public double getVerticalOffset() {
        NetworkTableEntry verticalOffset = table.getEntry("ty");
        return verticalOffset.getDouble(0.0);
    }

    // Returns the percentage of screen that the target fills
    public double getScreenArea() {
        NetworkTableEntry screenArea = table.getEntry("ta");
        return screenArea.getDouble(0.0);
    }

    // Method that checks if there is a valid target
    public boolean getValidTarget() {
        return !(this.getHorizontalOffset() == 0 && this.getVerticalOffset() == 0);
    }

    // Calculates the distance between the tape and the robot
    public double getDistance() {
        double angleToGoalRadians = (LIMELIGHT_MOUNT_A + this.getVerticalOffset()) * (Math.PI / 180d);
        return (GOAL_HEIGHT - LIMELIGHT_LENS_H) / Math.tan(angleToGoalRadians);
    }
}