/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Climb_SubSystem extends Subsystem {

  public enum Position {
    Front,
    Back,
    Both
  }
  
  @Override
  public void initDefaultCommand() {
  }

  public void extendClimb(Position position) {
    //exampleDouble.set(DoubleSolenoid.Value.kOff);
    //exampleDouble.set(DoubleSolenoid.Value.kForward);
    //exampleDouble.set(DoubleSolenoid.Value.kReverse);
    System.out.println("Extend: " +
      String.valueOf(position) + " " +
      String.valueOf(RobotMap.frontSolenoid.get()) + " " +
      String.valueOf(RobotMap.backSolenoid.get()));
    switch(position) {
      case Front:
        RobotMap.frontSolenoid.set(DoubleSolenoid.Value.kReverse);
        break;
      case Back:
        RobotMap.backSolenoid.set(DoubleSolenoid.Value.kReverse);
        break;
      case Both:
        RobotMap.frontSolenoid.set(DoubleSolenoid.Value.kReverse);
        RobotMap.backSolenoid.set(DoubleSolenoid.Value.kReverse);
        break;
    }
  }

  public void retractClimb(Position position) {
    System.out.println("Retract: " +
      String.valueOf(position) + 
      String.valueOf(RobotMap.frontSolenoid.get()) + 
      String.valueOf(RobotMap.backSolenoid.get()));
    switch(position) {
      case Front:
        RobotMap.frontSolenoid.set(DoubleSolenoid.Value.kForward);
        break;
      case Back:
        RobotMap.backSolenoid.set(DoubleSolenoid.Value.kForward);
        break;
      case Both:
        RobotMap.frontSolenoid.set(DoubleSolenoid.Value.kForward);
        RobotMap.backSolenoid.set(DoubleSolenoid.Value.kForward);
        break;
    }
  }
  public void extendClimbFront() {
    RobotMap.frontSolenoid.set(DoubleSolenoid.Value.kReverse);
  }
  public void extendClimbBack() {
    RobotMap.backSolenoid.set(DoubleSolenoid.Value.kReverse);
  }
  public void retractClimbFront() {
    RobotMap.frontSolenoid.set(DoubleSolenoid.Value.kForward);
  }
  public void retractClimbBack() {
    RobotMap.backSolenoid.set(DoubleSolenoid.Value.kForward);
  }
  public void stopClimb(Position position) {
    switch(position) {
      case Front:
        RobotMap.frontSolenoid.set(DoubleSolenoid.Value.kOff);
        break;
      case Back:
        RobotMap.backSolenoid.set(DoubleSolenoid.Value.kOff);
        break;
      case Both:
        RobotMap.frontSolenoid.set(DoubleSolenoid.Value.kOff);
        RobotMap.backSolenoid.set(DoubleSolenoid.Value.kOff);
        break;
    }
  }
}
