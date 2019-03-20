package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.c_bottomCargo_TeleOp;
import frc.robot.Robot;

public class BottomCargo_SubSystem extends Subsystem {
  public VictorSPX intakeMotor1 = RobotMap.intakeMotor1;
  public VictorSPX intakeMotor2 = RobotMap.intakeMotor2;
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
    intakeMotor1.set(ControlMode.PercentOutput, 0);
    intakeMotor2.set(ControlMode.PercentOutput, 0);
  }
  public void grabBall(){
    intakeMotor1.set(ControlMode.PercentOutput, -.5);
    intakeMotor2.set(ControlMode.PercentOutput, -.5);
  }
  public void ejectBall(){
    intakeMotor1.set(ControlMode.PercentOutput, .5);
    intakeMotor2.set(ControlMode.PercentOutput, .5);
  }
  public void teleOpIntake() {
    if(Robot.oi.driverOne.rightTrigger.get())
    {
      grabBall();
    } 
    else if(Robot.oi.driverOne.leftTrigger.get())
    {
      ejectBall();
    }
    else {
      stopMotors();
    }
  }
}
