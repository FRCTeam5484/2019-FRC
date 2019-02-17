/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climb_SubSystem extends Subsystem {

  public enum Position {
    Front,
    Back,
    All
  }
  
  @Override
  public void initDefaultCommand() {
  }

  public void extendClimb(Position position) {
    switch(position) {
      case Front:
        // Extend Front Solenoid
        break;
      case Back:
        // Extend Back Solenoid
        break;
      case All:
        //Extend Both Solenoids
        break;
    }
  }

  public void retractClimb(Position position) {
    switch(position) {
      case Front:
        // Retract Front Solenoid
        break;
      case Back:
        // Retract Back Solenoid
        break;
      case All:
        // Retract Both Solenoids
        break;
    }
  }
}
