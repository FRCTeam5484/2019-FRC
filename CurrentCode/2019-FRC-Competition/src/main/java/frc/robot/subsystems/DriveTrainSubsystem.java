package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.teleOp_Mode;

import java.awt.SystemTray;

import com.kauailabs.navx.frc.AHRS;

public class DriveTrainSubsystem extends Subsystem {
  
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new teleOp_Mode());
  }
  public void TeleOpDrive(){
    Vision vision = Robot.vision;
    //driveTrain.driveCartesian(ySpeed, xSpeed, zRotation, gyroAngle);
    //RobotMap.driveTrain.driveCartesian(OI.driverOne.getRawAxis(4), OI.driverOne.getRawAxis(1), OI.driverOne.getRawAxis(0));
    RobotMap.driveTrain.driveCartesian(-OI.driverOne.getRawAxis(0)*.7, OI.driverOne.getRawAxis(1)*.7, -OI.driverOne.getRawAxis(4)*.7, 0);
    //System.out.println("X: " + RobotMap.navX.getDisplacementX() * 100);
    //System.out.println("Y: " + RobotMap.navX.getDisplacementY() * 100);
    double currentAngle = vision.RoundToCircle(RobotMap.navX.getAngle());

    System.out.println("X: " + vision.AngleTo2D(currentAngle).x + " Y: " + vision.AngleTo2D(currentAngle).y);
    //System.out.println("Angle: " + Robot.vision.SnapToAngle(RobotMap.navX.getAngle()));
    LEDOn(); 
  }
  public void DriveStraight(Vector2d dir, double speed) {
    

    RobotMap.driveTrain.driveCartesian(
      dir.x * speed * .7, 
      -dir.y * speed * .7, 
      0, 
      0
    );
  }
  public void Turn(double speed) {
    RobotMap.driveTrain.driveCartesian(
      0, 
      0, 
      speed * .7, 
      0
    );
  }
  public void DriveToTarget(double initialAngle) {
    LEDOn();

    //read values periodically
    double x = Robot.vision.getOffset().x;
    double y = Robot.vision.getOffset().y;
    double area = Robot.vision.getArea();

    /*if(RobotMap.distanceSensor.getRangeInches() < 35)
    {
      DriveStraight(new Vector2d(0, 1), 0.2);
    }*/

    double desiredAngle = Robot.vision.SnapToAngle(Robot.vision.RoundToCircle(
      initialAngle), 
      new double[]{0, 65, 90, 115, 180, 245, 270, 295});
    double currentAngle = Robot.vision.RoundToCircle(RobotMap.navX.getAngle());

    double dist = Robot.vision.Dist2D(Robot.vision.AngleTo2D(currentAngle), Robot.vision.AngleTo2D(desiredAngle));
    
    //double xDifference = Robot.vision.AngleTo2D(desiredAngle).x - Robot.vision.AngleTo2D(currentAngle).x;
    double range = desiredAngle - currentAngle;   

    double turnSpeed = 0.2;

    // TURN TO ANGLE
    if(Math.abs(dist) > 3) // LEFT OF ANGLE
    {
      if(dist > 0) {
        Turn(-turnSpeed);
      }
      else if (dist < 0) {
        Turn(turnSpeed);
      }
    }
    else { // CENTER TO TARGET AND DRIVE FORWARD
      if(area > 0) 
      {
        if(area > 0.1) 
        {
          DriveStraight(new Vector2d(0, 1), 0.4);
        }
        if(x > 3 || x < -3) 
        {
          if(x > 3) 
          {
            DriveStraight(new Vector2d(-1, 0), 0.6);
          }
          else if (x < -3) 
          {
            DriveStraight(new Vector2d(1, 0), 0.6);
          }
        }
      }
    }

    //TeleOpDrive();
    System.out.println("Current: " + currentAngle);
    System.out.println("Desired: " + desiredAngle);
    System.out.println(" X: " + x + " Y: " + x +" Area: " + area);
    System.out.println("Range: " + range);
  }

  void LEDOff () {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");

    //read values periodically
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);

    //0 - Default Pipeline Setting
    // 1 - Force Off
    // 2 - Force Blink
    // 3 - Force On
    int ledStatus = 1;

    //Set LED State
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(ledStatus);
  }

  void LEDOn () {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");

    //read values periodically
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);

    // 1 - Force Off
    // 2 - Force Blink
    // 3 - Force On
    int ledStatus = 3;

    //Set LED State
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(ledStatus);
  }
}