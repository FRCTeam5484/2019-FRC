/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class c_hatchPanel_ToggleGrip extends Command {
  public c_hatchPanel_ToggleGrip() {
    requires(Robot.hatchPanel);
  }

  @Override
  protected void initialize() {
    Timer time = new Timer();
    time.start();
    /*while(time.get() < 1)
    {
      if(RobotMap.panelGrip.get() == false) {
        Robot.oi.driverOne.setRumble(RumbleType.kLeftRumble, 1);
        Robot.oi.driverOne.setRumble(RumbleType.kRightRumble, 1);
      }
      Robot.oi.driverOne.setRumble(RumbleType.kLeftRumble, 0);
      Robot.oi.driverOne.setRumble(RumbleType.kRightRumble, 0);
    }*/
  }

  @Override
  protected void execute() {
    RobotMap.panelGrip.set(!RobotMap.panelGrip.get());
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
