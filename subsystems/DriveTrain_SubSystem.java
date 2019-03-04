package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.drive.Vector2d;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.*;
import frc.robot.subsystems.Vision_SubSystem.LEDState;

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
    /*if(Robot.oi.actionBox.getRawButton(1))
    {
      driveStraight(new Vector2d(0, -1), 0.5);
    }
    else if(Robot.oi.actionBox.getRawButton(2))
    {
      driveStraight(new Vector2d(1, 0), 0.5);
    }
    else if(Robot.oi.actionBox.getRawButton(4))
    {
      driveStraight(new Vector2d(0, 1), 0.5);
    }
    else if(Robot.oi.actionBox.getRawButton(3))
    {
      driveStraight(new Vector2d(-1, 0), 0.5);
    }*/
    
    // double currentAngle = RobotMap.navX.getAngle();
    // double desiredAngle = Robot.vision.snapToAngle(
    //   RobotMap.navX.getAngle(), 
    //   new double[]{0, 65, 90, 115, 180, 245, 270, 295, 360}
    //   );

    // System.out.println("Current: " + currentAngle);
    // System.out.println("Snapped: " + desiredAngle);
    // System.out.println("Turn: " + Robot.vision.getTurnDirection(currentAngle, desiredAngle));
    //Robot.vision.setLED(LEDState.On);

    // Normal Camera View
    // NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(1);
    Robot.vision.setPipeline(1);

    mecanumDriveTrain.driveCartesian(
      Robot.oi.getDriverOneStickValue(0) * driveSpeed, 
      -Robot.oi.getDriverOneStickValue(1) * driveSpeed, 
      Robot.oi.getDriverOneStickValue(4) * driveSpeed, 
      0
    ); 
  }

  public void driveToTarget(double initialAngle) {
    //Robot.vision.setLED(LEDState.On);
    boolean m_LimelightHasValidTarget = false;
    double m_LimelightDriveCommand = 0.0;
    double m_LimelightStrafeCommand = 0.0;

    // Tracking View
     NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
    //Robot.vision.setPipeline(0);

    final double STRAFE_K = 0.05;
    final double DRIVE_K = 0.1;
    final double DESIRED_TARGET_AREA = 15.0;
    final double MAX_DRIVE = 0.2;
    final double OFFSET_K = 6;

    // double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    // double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    // double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    // double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    double x = Robot.vision.getOffset().x;
    double y = Robot.vision.getOffset().y;
    double area = Robot.vision.getArea();

    if (Robot.vision.getTarget() == false)
    {
      m_LimelightHasValidTarget = false;
      m_LimelightDriveCommand = 0.0;
      m_LimelightStrafeCommand = 0.0;
      return;
    }

    m_LimelightHasValidTarget = true;
    // double steer_cmd = (tx - OFFSET_K) * STRAFE_K;
    double steer_cmd = (x - OFFSET_K) * STRAFE_K;
    // if(ta > 21)
    if(area > 21)
    {
      steer_cmd = 0;
    }
    else
    {
      m_LimelightStrafeCommand = steer_cmd;
    }

    // double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;
    double drive_cmd = (DESIRED_TARGET_AREA - area) * DRIVE_K;
    
    if(drive_cmd < 0.05)
    {
      drive_cmd = 0;
    }
    else if (drive_cmd > MAX_DRIVE)
    {
      drive_cmd = MAX_DRIVE;
    }
    
    // Drive and Strafe
    if (m_LimelightHasValidTarget)
    {
      mecanumDriveTrain.driveCartesian(m_LimelightStrafeCommand, m_LimelightDriveCommand, 0, 0.0);
    }
    else
    {
      mecanumDriveTrain.driveCartesian(0, 0,0, 0.0);
    }

    // // Turn the Limelight LED On
    // Robot.vision.setLED(LEDState.On);

    // // Read Limelight Values Periodically
    // double x = Robot.vision.getOffset().x;
    // double y = Robot.vision.getOffset().y;
    // double area = Robot.vision.getArea();
    // boolean validTarget = Robot.vision.getTarget();

    // double kFORWARD_SPEED = 0.15; // Default Forward Speed
    // double kSTRAFE_SPEED = 0.15; // Default Strafing Speed
    // //double kTURN_SPEED = 0.4; // Default Turning Speed

    // // Current Angle within the Scope of 360 Degrees
    // double currentAngle = Robot.vision.roundToCircle(RobotMap.navX.getAngle());
    // // If the Angle is Negative, make it positive
    // if(Robot.vision.roundToCircle(RobotMap.navX.getAngle()) < 0)
    //   currentAngle = Robot.vision.roundToCircle(RobotMap.navX.getAngle()) + 360;
    
    // // Desired Angle
    // double desiredAngle = Robot.vision.snapToAngle(
    //   initialAngle, // Initial Angle from NavX
    //   new double[]{0, 65, 90, 115, 180, 245, 270, 295, 360}) // Reference Angles for Snapping
    //   ;
    // // Distance between the Current Angle and the Desired Angle
    // double dist = 
    //   Robot.vision.dist2D(Robot.vision.angleTo2D(currentAngle), // Current Angle as a point in 2D Space
    //   Robot.vision.angleTo2D(desiredAngle) // Desired Angle as a point in 2D Space
    //   );

    // //Vector2d xOffset = new Vector2d(6, 4);
    // Vector2d xOffset = new Vector2d(18, 16);

    // // // // Turn to Desired Angle
    // // if(Math.abs(dist) > 0.05) // If the distance between the Current and Desired angle is > 0.05 keep turning
    // // {
    // //   turn(kTURN_SPEED * Robot.vision.getTurnDirection(currentAngle, desiredAngle));
    // // }
    // // else // Center on Target and Drive Forward
    // // { 
    //   if(validTarget) // If a Target is Visible
    //   { 
    //     if(x > xOffset.x) // Target is Right of center
    //     {
    //       driveStraight(new Vector2d(1, 0), kSTRAFE_SPEED);
    //     }
    //     else if (x < xOffset.y) // Target is Left of center
    //     {
    //       driveStraight(new Vector2d(-1, 0), kSTRAFE_SPEED);
    //     }
    //     else {
    //       driveStraight(new Vector2d(0, 1), kFORWARD_SPEED);
    //     }
    //   }
    //   else {
    //     driveStraight(new Vector2d(0, 1), kFORWARD_SPEED);
    //   }
    // // }

    // System.out.println("Has Target: " + validTarget);
    // System.out.println("X: " + x);
    // System.out.println("Y: " + y);
    // System.out.println("Area: " + area);

    // System.out.println("Initial: " + Robot.vision.roundToCircle(initialAngle));
    // System.out.println("Current: " + currentAngle);
    // System.out.println("Desired: " + desiredAngle);
    // System.out.println("Turn: " + Robot.vision.getTurnDirection(currentAngle, desiredAngle));
  }

  public void driveStraight(Vector2d dir, double speed) {
    //Normalize the Vector
    double magnitude = Math.sqrt(Math.pow(dir.x, 2) + Math.pow(dir.y, 2));
    dir = new Vector2d(dir.x / magnitude, dir.y / magnitude);
    //Drive in the specified Direction
    mecanumDriveTrain.driveCartesian(
      dir.x * speed, 
      dir.y * speed, 
      0, 
      0
    );
  }

  public void turn(double speed) {
    mecanumDriveTrain.driveCartesian(
      0, 
      0, 
      speed, 
      0
    );
  }

  public void stopMotors() {
    mecanumDriveTrain.driveCartesian(0, 0, 0);
  }

  public void testForward()
  {
    Timer time = new Timer();
    time.start();
    while(time.get() < 2)
    {
      RobotMap.driveTrain_frontLeft.set(.3);
      RobotMap.driveTrain_backLeft.set(.3);
      RobotMap.driveTrain_frontRight.set(-.3);
      RobotMap.driveTrain_backRight.set(-.3);
    }
  }
  public void testBack()
  {
    Timer time = new Timer();
    time.start();
    while(time.get() < 2)
    {
      RobotMap.driveTrain_frontLeft.set(-.3);
      RobotMap.driveTrain_backLeft.set(-.3);
      RobotMap.driveTrain_frontRight.set(.3);
      RobotMap.driveTrain_backRight.set(.3);
    }
  }
  public void testLeft()
  {
    Timer time = new Timer();
    time.start();
    while(time.get() < 2)
    {
      RobotMap.driveTrain_frontLeft.set(-.3);
      RobotMap.driveTrain_backLeft.set(.3);
      RobotMap.driveTrain_frontRight.set(-.3);
      RobotMap.driveTrain_backRight.set(.3);
    }
  }
  public void testRight()
  {
    Timer time = new Timer();
    time.start();
    while(time.get() < 2)
    {
      RobotMap.driveTrain_frontLeft.set(.3);
      RobotMap.driveTrain_backLeft.set(-.3);
      RobotMap.driveTrain_frontRight.set(.3);
      RobotMap.driveTrain_backRight.set(-.3);
    }
  }
}