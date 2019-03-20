package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Climb_SubSystem extends Subsystem {

  public enum Position {
    Front("F"),
    Back("B"),
    Both("FB");
    
    private String value;
    Position(final String value){
      this.value = value;
    }
    public String getValue(){
      return value;
    }
    @Override
    public String toString(){
      return this.getValue();
    }
  }
  
  @Override
  public void initDefaultCommand() {
  }

  public void extendClimb(String position) {
    //System.out.println(position);
    switch(position) {
      case "F":
        RobotMap.frontSolenoid.set(DoubleSolenoid.Value.kReverse);
        break;
      case "B":
        RobotMap.backSolenoid.set(DoubleSolenoid.Value.kReverse);
        break;
      case "FB":
        RobotMap.frontSolenoid.set(DoubleSolenoid.Value.kReverse);
        RobotMap.backSolenoid.set(DoubleSolenoid.Value.kReverse);
        break;
    }
  }

  public void retractClimb(String position) {
    switch(position) {
      case "F":
        RobotMap.frontSolenoid.set(DoubleSolenoid.Value.kForward);
        break;
      case "B":
        RobotMap.backSolenoid.set(DoubleSolenoid.Value.kForward);
        break;
      case "FB":
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
  public void stopClimb(String position) {
    switch(position) {
      case "F":
        RobotMap.frontSolenoid.set(DoubleSolenoid.Value.kOff);
        break;
      case "B":
        RobotMap.backSolenoid.set(DoubleSolenoid.Value.kOff);
        break;
      case "FB":
        RobotMap.frontSolenoid.set(DoubleSolenoid.Value.kOff);
        RobotMap.backSolenoid.set(DoubleSolenoid.Value.kOff);
        break;
    }
  }
}
