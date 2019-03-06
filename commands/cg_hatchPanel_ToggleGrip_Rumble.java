
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
