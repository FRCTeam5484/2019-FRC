package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import frc.robot.RobotMap;
import frc.robot.Robot;
import frc.robot.commands.*;

public class LiftSubsystem extends PIDSubsystem{
    public static final double Floor = 90;
    public static final double HumanFeedPanel = 90;
    public static final double HumanFeedCargo = 90;
    public static final double CargoShipPanel = 90;
    public static final double CargoShipCargo = 90;
    public static final double RocketPanelLow = 90;
    public static final double RocketPanelMid = 90;
    public static final double RocketPanelHigh = 90;
    public static final double RocketCargoLow = 90;
    public static final double RocketCargoMid = 90;
    public static final double RocketCargoHigh = 90;

    public LiftSubsystem(){
        super("Lift", 0.1, 0.0, 0.0);
        setAbsoluteTolerance(3);
        getPIDController().setContinuous(false);
    }
    public void initDefaultCommand() {
        setDefaultCommand(new c_lift_TeleOp());
    }
    public void moveLift() {
    	double speedValue = -Robot.oi.getDriverTwoStickValue(1);
     	double currentPosition = RobotMap.liftPOT.get();

     	if(speedValue > .8 && Robot.lift.isTopLimitReached() || speedValue < -.8 && Robot.lift.isBottomLimitReached())
     	{
            System.out.println(RobotMap.liftPOT.get());
     		getPIDController().disable();
     		RobotMap.liftMotor.set(speedValue);
     	}
     	else if (currentPosition > 90)
     	{
     		getPIDController().disable();
     		RobotMap.liftMotor.set(0);
     	}
     	else
     	{
     		stopLift();
     	}
    }
    public void raiseLift() {
		RobotMap.liftMotor.set(1);
    }
    public void lowerLift() {
    	RobotMap.liftMotor.set(-1);
    }
    public void stopLift() {
    	RobotMap.liftMotor.set(0);
    }
    @Override
    protected double returnPIDInput() {
        return RobotMap.liftPOT.get();
    }
    @Override
    protected void usePIDOutput(double output) {
    	double reverseOutput = -output;
    	if(!Robot.lift.isTopLimitReached() && reverseOutput > 0 || !Robot.lift.isBottomLimitReached() && reverseOutput < 0)
    	{
    		stopLift();
    	}
    	else {
    		RobotMap.liftMotor.pidWrite(reverseOutput);
    	}
    }
}

