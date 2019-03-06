package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.Vector2d;

import java.util.Arrays;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Vision_SubSystem extends Subsystem {

  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new vision_Default());
  }

  //------------------------------------------\\
  //0 - Default Pipeline Setting
  // 1 - Force Off
  // 2 - Force Blink
  // 3 - Force On
  //------------------------------------------\\
  
  public enum LEDState {
    Default,
    On,
    Off,
    Blink
  }

  public void setLED(LEDState ledState) {
    int ledStatus = 0;
    switch(ledState) {
      case Default:
        ledStatus = 0;
        break;
      case On:
        ledStatus = 3;
        break;
      case Off:
        ledStatus = 1;
        break;
      case Blink:
        ledStatus = 2;
        break;
    }
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(ledStatus);
  }
  public boolean getTarget() {
    double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    if(tv < 1.0) {
      return false;
    } 
    else {
      return true;
    }
  }
  public double getArea () {
    double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    return ta;
  }
  public Vector2d getOffset () {
    double x = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    double y = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    
    return new Vector2d(x, y);
  }
  public double getPipeline() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").getDouble(0);
  }
  public void setPipeline(int pipeline) {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(pipeline);
  }
  public double roundToCircle(double initialAngle) {;
    return initialAngle % 361;
  }
  public double snapToAngle(double currentAngle, double[] angles) {
    //Ranges Calculated from Inital Angle to Preset Angles
    double[] distances = new double[angles.length];
    //Calculating Distances between Angles
    for(int i = 0; i < angles.length; i++) {
      distances[i] = dist2D(angleTo2D(currentAngle), angleTo2D(angles[i]));
    }
    //Sorting Distances Least to Greatest
    double[] sortedDistances = Arrays.copyOf(distances, distances.length);
    Arrays.sort(sortedDistances);
    //Shortest Distance (Closest Angle)
    double shortestDistance = sortedDistances[0];
    //Get the Index of the shortest Distance
    int index = 0;
    for(index = 0; index < distances.length; index++) {
      if(distances[index] == shortestDistance) {
        break;
      }
    }
    //Return Closest Angle to Initial Angle
    return angles[index];
  }
  public double getTurnDirection (double currentAngle, double desiredAngle) {
    if(currentAngle != desiredAngle) {
      double difference = currentAngle - desiredAngle;
      return (difference / Math.abs(difference)) * -1;
    }
    if (currentAngle < desiredAngle / 3) {
      return 1;
    }
    else {
      return 0;
    }
  }
  public Vector2d angleTo2D (double angle) {
    double x = Math.cos(Math.toRadians(angle));
    double y = Math.sin(Math.toRadians(angle));
    return new Vector2d(x, y);
  }
  public double dist2D (Vector2d a, Vector2d b) {
    double dist = Math.sqrt(Math.pow((b.x - a.x), 2) + Math.pow((b.y - a.y), 2));
    return dist;
  }
  public Vector2d difference (Vector2d a, Vector2d b) {
    Vector2d diff = new Vector2d(b.x - a.x, b.y - a.y);
    return diff;
  }
}
