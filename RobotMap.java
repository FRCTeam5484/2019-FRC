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
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

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
  public static MecanumDrive driveTrain;
  public static Ultrasonic distanceSensor;
  public static AHRS navX;

  // Sub Drive Train Devices
  public static WPI_TalonSRX subRightMotor;
  public static WPI_TalonSRX subLeftMotor;
  public static DifferentialDrive subDriveTrain;
  
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
    motorBackLeft  = new CANSparkMax(5, MotorType.kBrushless);    
    motorBackLeft.setInverted(true);
    motorBackRight  = new CANSparkMax(6, MotorType.kBrushless);    
    motorBackRight.setInverted(true);
    motorFrontLeft = new CANSparkMax(7, MotorType.kBrushless);    
    motorFrontRight = new CANSparkMax(8, MotorType.kBrushless); 
    driveTrain = new MecanumDrive(RobotMap.motorFrontLeft, RobotMap.motorBackLeft, RobotMap.motorFrontRight, RobotMap.motorBackRight);   

    distanceSensor = new Ultrasonic(1, 1);

    // Sub Drive Train Devices
    subRightMotor = new WPI_TalonSRX(6);
    subLeftMotor = new WPI_TalonSRX(7);
    subDriveTrain = new DifferentialDrive(subLeftMotor, subRightMotor);

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
