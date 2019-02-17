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
      Robot.oi.getDriverOneStickValue(0) * Robot.driveTrain.driveSpeed, 
      -Robot.oi.getDriverOneStickValue(1) * Robot.driveTrain.driveSpeed, 
      Robot.oi.getDriverOneStickValue(4) * Robot.driveTrain.driveSpeed, 
      0
    );
  }

  public void driveStraight(Vector2d dir, double speed) {
    mecanumDriveTrain.driveCartesian(
      dir.x * speed * driveSpeed, 
      -dir.y * speed * driveSpeed, 
      0, 
      0
    );
  }

  public void stopMotors() {
    mecanumDriveTrain.driveCartesian(0, 0, 0);
  }
}