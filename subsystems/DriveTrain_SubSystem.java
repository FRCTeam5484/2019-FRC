package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.drive.Vector2d;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.*;

public class DriveTrain_SubSystem extends Subsystem {
  public MecanumDrive mecanumDriveTrain = new MecanumDrive(
      RobotMap.driveTrain_frontLeft, 
      RobotMap.driveTrain_backLeft,
      RobotMap.driveTrain_frontRight,
      RobotMap.driveTrain_backRight
  );
  public Double driveSpeed = 1.0;

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new c_driveTrain_TeleOpMode());
  }  

  public void mecanumDrive() {
    Robot.vision.setPipeline(1);
    //System.out.println("x:" + Robot.oi.driverOne.leftStick.getX() + " y:" + Robot.oi.driverOne.leftStick.getY());
    mecanumDriveTrain.driveCartesian(
      Robot.oi.driverOne.leftStick.getX() * driveSpeed, 
      -Robot.oi.driverOne.leftStick.getY() * driveSpeed, 
      Robot.oi.driverOne.rightStick.getX() * driveSpeed, 
      0
    ); 
  }

  public void alignToTarget() {
    double Kp = -0.03;

    // Values range from 0.6 - 1.2

    //NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
    Robot.vision.setPipeline(0);
    
    double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);

    double speedModifier = 4;

    if(tv < 1.0)
    {
      System.out.println("I Can't See It");
      mecanumDriveTrain.driveCartesian(0.2, -Robot.oi.driverOne.leftStick.getY() * driveSpeed, 0, 0.0);
    }
    else
    {
      System.out.println("I See It: " + tx);
      mecanumDriveTrain.driveCartesian((tx/-26) * driveSpeed * speedModifier, -Robot.oi.driverOne.leftStick.getY() * driveSpeed, 0, 0.0);
    }
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