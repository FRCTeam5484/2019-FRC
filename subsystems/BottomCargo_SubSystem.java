package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.c_bottomCargo_TeleOp;
import frc.robot.Robot;

public class BottomCargo_SubSystem extends Subsystem {
  public TalonSRX intakeMotors = RobotMap.intakeMotors;
  public Solenoid intakeArms = RobotMap.intakeArms;
  
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new c_bottomCargo_TeleOp());
  }

  public void deployArms(){
    intakeArms.set(true);
  }
  public void retractArms(){
    intakeArms.set(false);
  }
  public void stopMotors() {
    intakeMotors.set(ControlMode.PercentOutput, 0);
  }
  public void grabBall(){
    intakeMotors.set(ControlMode.PercentOutput, -.5);
  }
  public void ejectBall(){
    intakeMotors.set(ControlMode.PercentOutput, .5);
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
