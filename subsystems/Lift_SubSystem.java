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
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.c_lift_TeleOp;

/**
 * Add your docs here.
 */
public class Lift_SubSystem extends PIDSubsystem {
  public static final double Floor = 101;
	public static final double Switch = 75;
	public static final double Portal = 75;
	public static final double MidScale = 52;
	public static final double HighScale = 41;
  public static final double TopScale = 36;
  
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
    double speedValue = -Robot.oi.getDriverTwoStickValue(1);
    double currentPosition = liftPOT.get();

    //Directional Pad Input
    switch(Robot.oi.driverTwo.getPOV()) {
      case 0:
        System.out.println("Driver Two: D-Pad Up"); // High Modifer
        moveToPosition(HighScale);
        break;
      case 90:
        System.out.println("Driver Two: D-Pad Right"); // Mid Modifier
        moveToPosition(MidScale);
        break;
      case 180:
        System.out.println("Driver Two: D-Pad Down"); // Low Modifier
        moveToPosition(Switch);
        break;
      /*case 270:
        System.out.println("Driver Two: D-Pad Left"); // Top Cargo
        moveToPosition();
        break;*/
    }

    if(speedValue > .8 && !isTopLimitReached() || speedValue < -.8 && !isBottomLimitReached())
    {
      getPIDController().disable();
      liftMotor.set(speedValue);
    }
    else if (currentPosition > 90)
    {
      getPIDController().disable();
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