
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class PneumaticsSubsystem extends Subsystem {
  public void initDefaultCommand() {

  }
  public void startCompressor() {
    RobotMap.compressor.setClosedLoopControl(true);
  }
  
  public void stopCompressor() {
    RobotMap.compressor.setClosedLoopControl(false);
  }
}
