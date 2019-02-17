package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.drive.Vector2d;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.*;
import frc.robot.subsystems.VisionSubsystem.LEDState;

public class DriveTrain_SubSystem extends Subsystem {
  public MecanumDrive mecanumDriveTrain = new MecanumDrive(
      RobotMap.driveTrain_frontLeft, 
      RobotMap.driveTrain_backLeft,
      RobotMap.driveTrain_frontRight,
      RobotMap.driveTrain_backRight
  );
  public Double driveSpeed = 0.7;

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new c_driveTrain_TeleOpMode());
  }  

  public void mecanumDrive() {
    mecanumDriveTrain.driveCartesian(
      Robot.oi.getDriverOneStickValue(0) * driveSpeed, 
      -Robot.oi.getDriverOneStickValue(1) * driveSpeed, 
      Robot.oi.getDriverOneStickValue(4) * driveSpeed, 
      0
    );
  }

  public void driveToTarget(double initialAngle) {
    // Turn the Limelight LED On
    Robot.vision.setLED(LEDState.On);

    double kFORWARD_SPEED = 0.4; // Default Forward Speed 
    double kSTRAFE_SPEED = 0.6; // Default Strafing Speed
    double kTURN_SPEED = 1.5; // Default Turning Speed

    // Read Limelight Values Periodically
    double x = Robot.vision.getOffset().x;
    double y = Robot.vision.getOffset().y;
    double area = Robot.vision.getArea();

    // Current Angle within the Scope of 360 Degrees
    double currentAngle = Robot.vision.roundToCircle(RobotMap.navX.getAngle());
    // If the Angle is Negative, make it positive
    if(Robot.vision.roundToCircle(RobotMap.navX.getAngle()) < 0)
      currentAngle = Robot.vision.roundToCircle(RobotMap.navX.getAngle()) + 360;
    
    // Desired Angle
    double desiredAngle = Robot.vision.snapToAngle(
      initialAngle, // Initial Angle from NavX
      new double[]{0, 65, 90, 115, 180, 245, 270, 295, 360}) // Reference Angles for Snapping
      ;
    // Distance between the Current Angle and the Desired Angle
    double dist = 
      Robot.vision.dist2D(Robot.vision.angleTo2D(currentAngle), // Current Angle as a point in 2D Space
      Robot.vision.angleTo2D(desiredAngle) // Desired Angle as a point in 2D Space
      );

    // Turn to Desired Angle
    if(Math.abs(dist) > 0.05) // If the distance between the Current and Desired angle is > 0.05 keep turning
    {
      turn(kTURN_SPEED * dist * Robot.vision.getTurnDirection(currentAngle, desiredAngle));
    }
    else // Center on Target and Drive Forward
    { 
      if(area > 0) // If a Target is Visible
      { 
        if(area > 0.1) 
        { 
          driveStraight(new Vector2d(0, 1), kFORWARD_SPEED); // If the Area is greater than 0.1 Drive forward
        }
        else if(x > 3 || x < -3) // If the Target is not centered, Strafe toward it
        { 
          if(x > 3) // Target is Right of center
          {
            driveStraight(new Vector2d(-1, 0), kSTRAFE_SPEED);
          }
          else if (x < -3) // Target is Left of center
          {
            driveStraight(new Vector2d(1, 0), kSTRAFE_SPEED);
          }
        }
      }
    }

    System.out.println("Initial: " + Robot.vision.roundToCircle(initialAngle));
    System.out.println("Current: " + currentAngle);
    System.out.println("Desired: " + desiredAngle);
    System.out.println("Turn: " + Robot.vision.getTurnDirection(currentAngle, desiredAngle));
  }

  public void driveStraight(Vector2d dir, double speed) {
    //Normalize the Vector
    double magnitude = Math.sqrt(Math.pow(dir.x, 2) + Math.pow(dir.y, 2));
    dir = new Vector2d(dir.x / magnitude, dir.y / magnitude);
    //Drive in the specified Direction
    mecanumDriveTrain.driveCartesian(
      dir.x * speed, 
      -dir.y * speed, 
      0, 
      0
    );
  }

  public void turn(double speed) {
    RobotMap.driveTrain.driveCartesian(
      0, 
      0, 
      speed, 
      0
    );
  }

  public void stopMotors() {
    mecanumDriveTrain.driveCartesian(0, 0, 0);
  }
}