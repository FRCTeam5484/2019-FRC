/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.Vector2d;
import frc.robot.Robot;
import frc.robot.commands.vision_Default;

import java.awt.List;
import java.util.Arrays;
import java.util.Collections;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Add your docs here.
 */
public class Vision extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new vision_Default());
  }

  //------------------------------------------\\
  //0 - Default Pipeline Setting
  // 1 - Force Off
  // 2 - Force Blink
  // 3 - Force On
  //------------------------------------------\\

  public void LEDOff () {
    
    int ledStatus = 1;

    //Set LED State
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(ledStatus);
  }
  public void LEDOn () {
    // 1 - Force Off
    // 2 - Force Blink
    // 3 - Force On
    int ledStatus = 3;

    //Set LED State
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(ledStatus);
  }

  public double getArea () {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ta = table.getEntry("ta");

    double area = ta.getDouble(0.0);
    
    return area;
  }
  public Vector2d getOffset () {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");

    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    
    return new Vector2d(x, y);
  }
  public double RoundToCircle(double initialAngle) {
    initialAngle %= 360;
    return initialAngle;
    
  }
  public double FlippedCircle(double angle) {
    if(angle > 180) {
      return angle - 360;
    }
    else {
      return angle;
    }
  }
  public double SnapToAngle(double currentAngle, double[] angles) {
    //Preset Angles
    
    //{0, 65, 90, 115, 180, 205, 270, 295};
    
    //Ranges Calculated from Inital Angle to Preset Angles
    double[] ranges = new double[angles.length];
    //Calculating Ranges
    for(int i = 0; i < angles.length; i++) {
      double r = Math.abs(angles[i] - currentAngle);
      ranges[i] = r;
    }
    //Sorting Ranges Least to Greatest
    double[] sortedRanges = new double[ranges.length];
    for (int i = 0; i < ranges.length; i++)
      sortedRanges[i] = ranges[i];
    Arrays.sort(sortedRanges);
    //Lowest Range (Closest Angle)
    double range = sortedRanges[0];
    //Get the Index of the lowest Range
    int index = 0;
    for(index = 0; index < ranges.length; index++) {
      if(ranges[index] == range) {
        break;
      }
    }
    //Return Closest Angle to Initial Angle
    return angles[index];
  }
  public Vector2d AngleTo2D (double angle) {
    double x = Math.cos(Math.toRadians(angle));
    double y = Math.sin(Math.toRadians(angle));
    return new Vector2d(x, y);
  }
  public double Dist2D (Vector2d a, Vector2d b) {
    double dist = Math.sqrt(Math.pow((b.x - a.x), 2) + Math.pow((b.y - a.y), 2));
    return dist;
  }
}
