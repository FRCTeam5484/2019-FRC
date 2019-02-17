package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.c_bottomCargo_TeleOp;
import frc.robot.Robot;

public class BottomCargo_SubSystem extends Subsystem {
  
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new c_bottomCargo_TeleOp());
  }

  public void deployArms(){
    RobotMap.intakeArms.set(true);
  }
  public void retractArms(){
    RobotMap.intakeArms.set(false);
  }
  public void stopMotors() {
    RobotMap.intakeMotors.set(ControlMode.PercentOutput, 0);
  }
  public void grabBall(){
    RobotMap.intakeMotors.set(ControlMode.PercentOutput, -.5);
  }
  public void ejectBall(){
    RobotMap.intakeMotors.set(ControlMode.PercentOutput, .5);
  }
  public void teleOpIntake() {
    if(Robot.oi.driverOne.getTriggerAxis(Hand.kRight) > 0.25)
    {
      grabBall();
    } 
    else if(Robot.oi.driverOne.getTriggerAxis(Hand.kLeft) > 0.25)
    {
      ejectBall();
    }
    else {
      stopMotors();
    }
  }
}
