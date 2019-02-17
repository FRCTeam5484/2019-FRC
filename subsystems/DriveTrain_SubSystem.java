package frc.robot.subsystems;

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

  public void driveStraight(Vector2d dir, double speed) {
    //Normalize the Vector so that it has a length of 1
    dir = Vector2d.normalize(dir);
    //Drive in the specified Direction
    mecanumDriveTrain.driveCartesian(
      dir.x * speed, 
      -dir.y * speed, 
      0, 
      0
    );
  }

  public void stopMotors() {
    mecanumDriveTrain.driveCartesian(0, 0, 0);
  }
}