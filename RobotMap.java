package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Solenoid;

public class RobotMap {

  // Drive Train SubSystem
  public static CANSparkMax driveTrain_frontRight;
  public static CANSparkMax driveTrain_backRight;
  public static CANSparkMax driveTrain_frontLeft;
  public static CANSparkMax driveTrain_backLeft;

  // SubDrive Train SubSystem
  public static TalonSRX subDriveLeft;
  public static TalonSRX subDriveRight;

  // Lift SubSystem
  public static CANSparkMax liftMotor;
  public static CANEncoder liftMotorEncoder;
  public static AnalogPotentiometer liftPOT;
  public static double lift_bottomLimit = 0;
  public static double lift_topLimit = 100;

  // Cargo Bottom Intake SubSystem
  public static Solenoid intakeArms;
  public static VictorSPX intakeMotors;
  
  // Cargo Top Intake SubSystem
  public static TalonSRX topIntakeMotor;
  
  // Sub Drive SubSystem
  public static Compressor compressor;
  public static Solenoid panelArm;
  public static Solenoid panelGrip;

  // Climb SubSystem
  public static DoubleSolenoid frontSolenoid;
  public static DoubleSolenoid backSolenoid;

  // NavX
  public static AHRS navX;

  public static void init(){
    // Drive Train SubSystem
    driveTrain_frontRight = new CANSparkMax(2, MotorType.kBrushless);
    driveTrain_backRight = new CANSparkMax(3, MotorType.kBrushless);
    driveTrain_frontLeft = new CANSparkMax(5, MotorType.kBrushless);
    driveTrain_backLeft = new CANSparkMax(4, MotorType.kBrushless);

    // SubDrive Train SubSystem
    subDriveLeft = new TalonSRX(8);
    subDriveRight = new TalonSRX(7);

    // Lift SubSystem
    liftMotor = new CANSparkMax(10, MotorType.kBrushless);
    liftMotorEncoder = new CANEncoder(liftMotor);
    liftPOT = new AnalogPotentiometer(0);

    // Cargo Bottom Intake SubSystem
    intakeArms = new Solenoid(6);
    intakeMotors = new VictorSPX(6);
    panelArm = new Solenoid(5);
    panelGrip = new Solenoid(4);
    
    // Cargo Top Intake SubSystem
    topIntakeMotor = new TalonSRX(9);

    // Climb SubSystem
    frontSolenoid = new DoubleSolenoid(0, 1);
    backSolenoid = new DoubleSolenoid(2, 3);
    
    // Miscellaneous
    compressor = new Compressor(0);
    try {
      navX = new AHRS(SPI.Port.kMXP);
      navX.reset();
    }
    catch(RuntimeException ex)
    {
      DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
    }
  }
}