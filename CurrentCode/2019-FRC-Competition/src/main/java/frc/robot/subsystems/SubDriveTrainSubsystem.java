package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.commands.*;

public class SubDriveTrainSubsystem extends Subsystem {

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new teleOp_Mode_SubDrive());
  }
  public void hdrive(){
    //RobotMap.subDriveTrain.tankDrive(OI.driverOne.getRawAxis(1), OI.driverOne.getRawAxis(5));
  }
}
