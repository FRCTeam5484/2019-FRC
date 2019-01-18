package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class RobotMap {
  // Pneumatics Devices
  public static Compressor onBoardCompressor;
  public static Solenoid intakeArms;
  public static Solenoid intakeCargo;
  public static Solenoid intakePanel;
  public static DoubleSolenoid climbBack;
  public static DoubleSolenoid climbFront;
  public static DigitalInput climbLevel2LimitFront;
  public static DigitalInput climbLevel2LimitBack;

  // Drive Train Devices
  public static CANSparkMax motorBackLeft;
  public static CANSparkMax motorBackRight;
  public static CANSparkMax motorFrontLeft;
  public static CANSparkMax motorFrontRight;
  public static Ultrasonic distanceSensor;
  public static AHRS navX;

  // Sub Drive Train Devices
  public static WPI_TalonSRX SubRightMotor;
  public static WPI_TalonSRX SubLeftMotor;
  
  // Lift Devices
  public static WPI_TalonSRX liftMotor1;
  public static WPI_TalonSRX liftMotor2;
  public static SpeedControllerGroup liftMotors;  
  public static DigitalInput liftBottomLimit;
  public static DigitalInput liftTopLimit;
  public static AnalogPotentiometer liftPOT;

  // PDP
  public static PowerDistributionPanel pdp;

  public static void init() {
    onBoardCompressor = new Compressor(0);
    intakeArms = new Solenoid(0);
    intakeCargo = new Solenoid(1);
    intakePanel = new Solenoid(2);
    climbBack = new DoubleSolenoid(3, 4);
    climbFront = new DoubleSolenoid(5, 6);
    climbLevel2LimitFront = new DigitalInput(0);
    climbLevel2LimitBack = new DigitalInput(1);

    // Drive Train Devices
    motorBackLeft = new CANSparkMax(1, MotorType.kBrushless);
    motorBackRight = new CANSparkMax(2, MotorType.kBrushless);
    motorFrontLeft = new CANSparkMax(3, MotorType.kBrushless);
    motorFrontRight = new CANSparkMax(4, MotorType.kBrushless);
    distanceSensor = new Ultrasonic(1, 1);

    // Sub Drive Train Devices
    SubRightMotor = new WPI_TalonSRX(6);
    SubLeftMotor = new WPI_TalonSRX(7);

    // Lift Devices
    liftMotor1 = new WPI_TalonSRX(5);
    liftMotor2 = new WPI_TalonSRX(6);
    liftMotors = new SpeedControllerGroup(liftMotor1, liftMotor2);
    try {
      //navX = new AHRS(SPI.Port.kMXP);
    }
    catch(RuntimeException ex)
    {
      DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
    }
    liftBottomLimit = new DigitalInput(2);
    liftTopLimit = new DigitalInput(3);
    liftPOT = new AnalogPotentiometer(1, 108, 2);

    // PDP
    pdp = new PowerDistributionPanel();
  }
}
