/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class SubDrive_SubSystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  TalonSRX subDriveLeft = RobotMap.subDriveLeft;
  TalonSRX subDriveRight = RobotMap.subDriveRight;

  @Override
  public void initDefaultCommand() {
  }

  public void teleOpDrive() {
    subDriveLeft.set(ControlMode.PercentOutput, -Robot.oi.driverTwo.getTriggerAxis(Hand.kLeft));
    subDriveRight.set(ControlMode.PercentOutput, -Robot.oi.driverTwo.getTriggerAxis(Hand.kRight));
  }
}