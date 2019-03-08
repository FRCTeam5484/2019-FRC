package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.c_lift_TeleOp;

public class Lift_SubSystem extends PIDSubsystem {
  private static double Ground = 0.98;
  private static double PanelHigh = 0.45;
  
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

  public void moveLift() {
      double speedValue = -Robot.oi.driverTwo.leftStick.getY();
      double currentPosition = liftPOT.get();
      if(speedValue > .3 && !isTopLimitReached() || speedValue < -.3 && !isBottomLimitReached())
      {
        System.out.println("Function: Manual - Speed: " + speedValue + " POT: " + RobotMap.liftPOT.get());
          getPIDController().disable();
          liftMotor.set(speedValue);
      }
      else if (currentPosition > 0.44)
      {
        System.out.println("Function: Stop - Speed: " + speedValue + " POT: " + RobotMap.liftPOT.get());
          getPIDController().disable();
          liftMotor.set(0);
      }
      else
      {
        System.out.println("Function: Stop - Speed: " + speedValue + " POT: " + RobotMap.liftPOT.get());
          stopLift();
      }
  }

  public void raiseLift() {
    RobotMap.liftMotor.set(0.5);
  }

  public void lowerLift() {
    RobotMap.liftMotor.set(-0.2);
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