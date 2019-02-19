/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;

public class cg_hatchPanel_ToggleGrip_Rumble extends CommandGroup {
  public cg_hatchPanel_ToggleGrip_Rumble() {
    if(RobotMap.panelGrip.get()) {
      addParallel(new c_RumbleController());
      addSequential(new c_hatchPanel_ToggleGrip());
    }
  }
}
