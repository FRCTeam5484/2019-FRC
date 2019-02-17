/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class HatchPanel_SubSystem extends Subsystem {
  public Solenoid panelGrip = RobotMap.panelGrip;
  public Solenoid panelArm = RobotMap.panelArm;

  @Override
  public void initDefaultCommand() {
  }

  public void grabPanel() {
    panelGrip.set(true);
  }

  public void releasePanel() {
    panelGrip.set(false);
  }

  public void deployArm() {
    panelArm.set(true);
  }

  public void retractArm() {
    panelArm.set(false);
  }
}
