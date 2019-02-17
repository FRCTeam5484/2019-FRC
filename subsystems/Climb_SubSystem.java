/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Climb_SubSystem extends Subsystem {

  public enum Position {
    Front,
    Back
  }
  
  @Override
  public void initDefaultCommand() {
  }

  public void extendLift(Position position, boolean both) {
    if(both) {
      //Extend Both Solenoids
    }
    else {
      switch(position) {
        case Front:
          //Extend Front Solenoid
          break;
        case Back:
          //Extend Back Solenoid
          break;
      }
    }
  }

  public void retractLift(Position position, boolean both) {
    if(both) {
      //Retract Both Solenoids
    }
    else {
      switch(position) {
        case Front:
          //Retract Front Solenoid
          break;
        case Back:
          //Retract Back Solenoid
          break;
      }
    }
  }
}
