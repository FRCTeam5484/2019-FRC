package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.teleOp_Mode;

public class DriveTrainSubsystem extends Subsystem {
  
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new teleOp_Mode());
  }
  public void TeleOpDrive(){
    //driveTrain.driveCartesian(ySpeed, xSpeed, zRotation, gyroAngle);
    RobotMap.driveTrain.driveCartesian(OI.driverOne.getRawAxis(1), OI.driverOne.getRawAxis(0), OI.driverOne.getRawAxis(4));
  }
}