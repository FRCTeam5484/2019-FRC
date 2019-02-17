/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.c_lift_TeleOp;

public class Lift_SubSystem extends PIDSubsystem {
  public static final double Ground = 0.98;
  public static final double PanelMid = 0.64;
  public static final double PanelHigh = 0.45;
  public static final double BallLow = 0.71;
	public static final double BallMidHigh = 0.45;
  public static final double BallCargo = 0.58;
  public static final double BallHuman = 0.935;
  
  private final AnalogPotentiometer liftPOT = RobotMap.liftPOT;	
  public static final SpeedController liftMotor = RobotMap.liftMotor;

  public Lift_SubSystem () {
    super("Lift_SubSystem", 0.1, 0.0, 0.0);
    setAbsoluteTolerance(3);
    getPIDController().setContinuous(false);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new c_lift_TeleOp());
  }

  public void teleOpLift() {
    System.out.println(String.valueOf(RobotMap.liftPOT.get()));
    getPIDController().disable();
    double speedValue = -Robot.oi.getDriverTwoStickValue(1) * .7;
    double currentPosition = liftPOT.get();

    //Directional Pad Input
    switch(Robot.oi.driverTwo.getPOV()) {
      case 0:
        //System.out.println("Driver Two: D-Pad Up"); // High Modifer
        //moveToPosition(High_Modifier);
        break;
      case 90:
        //System.out.println("Driver Two: D-Pad Right"); // Mid Modifier
        //moveToPosition(Mid_Modifier);
        break;
      case 180:
        //System.out.println("Driver Two: D-Pad Down"); // Low Modifier
        //moveToPosition(Low_Modifier);
        break;
      /*case 270:
        System.out.println("Driver Two: D-Pad Left"); // Top Cargo
        moveToPosition();
        break;*/
    }

    if(speedValue > .3 && !isTopLimitReached() || speedValue < -.3 && !isBottomLimitReached())
    {
      liftMotor.set(speedValue);
    }
    else if (RobotMap.liftPOT.get() < PanelHigh)
    {
      liftMotor.set(0);
    }
    else
    {
      stopLift();
    }
  }

  public void moveToPosition(double desiredPosition) {
    Robot.lift.enable();
   	Robot.lift.setSetpoint(desiredPosition);
  }

  public void raiseLift() {
    RobotMap.liftMotor.set(0.5);
  }

  public void lowerLift() {
    RobotMap.liftMotor.set(-0.5);
  }

  public void stopLift() {
    liftMotor.set(0);
  }

  public static boolean isTopLimitReached()
  {
    if(RobotMap.liftPOT.get() <= PanelHigh) {
      return true;
    }
    else {
      return false;
    }
  }
  public static boolean isBottomLimitReached()
  {
    if(RobotMap.liftPOT.get() >= Ground) {
      return true;
    }
    else {
      return false;
    }
  }
  @Override
  protected double returnPIDInput() {
      return liftPOT.get();
  }
  @Override
  protected void usePIDOutput(double output) {
    double reverseOutput = -output;
    if(isTopLimitReached() && reverseOutput > 0 || isBottomLimitReached() && reverseOutput < 0)
    {
      stopLift();
    }
    else {
      liftMotor.pidWrite(reverseOutput);
    }
  }
}