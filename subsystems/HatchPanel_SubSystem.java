package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.c_hatchPanel_DeployArm;
import frc.robot.commands.c_hatchPanel_RetractArm;

public class HatchPanel_SubSystem extends Subsystem {
  public Solenoid panelGrip = RobotMap.panelGrip;
  public Solenoid panelArm = RobotMap.panelArm;

  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new c_hatchPanel_RetractArm());
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