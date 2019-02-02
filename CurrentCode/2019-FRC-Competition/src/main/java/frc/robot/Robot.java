package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.*;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {
  public static ClimbSubSystem climb;
  public static DriveTrainSubsystem drive;
  public static IntakeSubsystem intake;
  public static LiftSubsystem lift;
  public static PneumaticsSubsystem pneumatics;
  public static SubDriveTrainSubsystem subDrive;
  public static Vision vision;
  public static OI oi;

  Command choosenAutoCommand;
  SendableChooser<Command> autoChooser = new SendableChooser<>();
  
  @Override
  public void robotInit() {
    RobotMap.init();
    climb = new ClimbSubSystem();
    drive = new DriveTrainSubsystem();
    intake = new IntakeSubsystem();
    lift = new LiftSubsystem();
    pneumatics = new PneumaticsSubsystem();
    subDrive = new SubDriveTrainSubsystem();
    vision = new Vision();
    
    //Make sure this is always intialized last
    oi = new OI();
    
    vision.LEDOn();
   
    //UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		//camera.setVideoMode(PixelFormat.kMJPEG, 320, 240, 10);

    //autoChooser.setDefaultOption("Default Auto", new ExampleCommand());
    // autoChooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", autoChooser);
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    choosenAutoCommand = autoChooser.getSelected();

    if (choosenAutoCommand != null) {
      choosenAutoCommand.start();
    }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    System.out.println("NavX Gyro: " + RobotMap.navX.getAngle());
  }

  @Override
  public void teleopInit() {
    if (choosenAutoCommand != null) {
      choosenAutoCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}