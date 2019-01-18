package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.Robot;
import frc.robot.commands.*;

public class SubDriveTrainSubsystem extends Subsystem {

  @Override
  public void initDefaultCommand() {

  }
  public void MoveLeftDrive(double power){
    RobotMap.SubLeftMotor.set(power);
  }
  public void MoveRightDrive(double power){
    RobotMap.SubRightMotor.set(power);
  }
}
