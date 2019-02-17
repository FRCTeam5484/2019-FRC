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
  public static final double Low = 101;
	public static final double Low_Modifier = 75;
	public static final double Mid = 75;
	public static final double Mid_Modifier = 52;
	public static final double High = 41;
  public static final double High_Modifier = 36;
  
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
    getPIDController().disable();
    double speedValue = -Robot.oi.getDriverTwoStickValue(1);
    double currentPosition = liftPOT.get();

    //Directional Pad Input
    switch(Robot.oi.driverTwo.getPOV()) {
      case 0:
        System.out.println("Driver Two: D-Pad Up"); // High Modifer
        moveToPosition(High_Modifier);
        break;
      case 90:
        System.out.println("Driver Two: D-Pad Right"); // Mid Modifier
        moveToPosition(Mid_Modifier);
        break;
      case 180:
        System.out.println("Driver Two: D-Pad Down"); // Low Modifier
        moveToPosition(Low_Modifier);
        break;
      /*case 270:
        System.out.println("Driver Two: D-Pad Left"); // Top Cargo
        moveToPosition();
        break;*/
    }

    if(speedValue > .8 && !isTopLimitReached() || speedValue < -.8 && !isBottomLimitReached())
    {
      liftMotor.set(speedValue);
    }
    else if (currentPosition > 90)
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
    if(RobotMap.liftMotorEncoder.getPosition() >= RobotMap.lift_topLimit) {
      return true;
    }
    else {
      return false;
    }
  }
  public static boolean isBottomLimitReached()
  {
    if(RobotMap.liftMotorEncoder.getPosition() <= RobotMap.lift_bottomLimit) {
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