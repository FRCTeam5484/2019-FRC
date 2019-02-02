package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;
import frc.robot.Robot;

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
        //setDefaultCommand(new Lift_TeleopMode());
    }
    public void moveLift() {
    	double speedValue = -Robot.oi.getDriverTwoStickValue(1);
     	double currentPosition = RobotMap.liftPOT.get();

     	if(speedValue > .8 && RobotMap.liftTopLimit.get() || speedValue < -.8 && RobotMap.liftBottomLimit.get())
     	{
     		getPIDController().disable();
     		RobotMap.liftMotors.set(speedValue);
     	}
     	else if (currentPosition > 90)
     	{
     		getPIDController().disable();
     		RobotMap.liftMotors.set(0);
     	}
     	else
     	{
     		stopLift();
     	}
    }
    public void raiseLift() {
		RobotMap.liftMotors.set(1);
    }
    public void lowerLift() {
    	RobotMap.liftMotors.set(-1);
    }
    public void stopLift() {
    	RobotMap.liftMotors.set(0);
    }
    @Override
    protected double returnPIDInput() {
        return RobotMap.liftPOT.get();
    }
    @Override
    protected void usePIDOutput(double output) {
    	double reverseOutput = -output;
    	if(!RobotMap.liftTopLimit.get() && reverseOutput > 0 || !RobotMap.liftBottomLimit.get() && reverseOutput < 0)
    	{
    		stopLift();
    	}
    	else {
    		RobotMap.liftMotors.pidWrite(reverseOutput);
    	}
    }
}

