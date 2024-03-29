package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.shuffleboard.*;
import frc.robot.commands.c_driveTrain_TeleOpMode;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {
  public static DriveTrain_SubSystem driveTrain;
  public static BottomCargo_SubSystem bottomCargo;
  public static HatchPanel_SubSystem hatchPanel;
  public static TopCargo_SubSystem topCargo;
  public static Lift_SubSystem lift;
  public static Climb_SubSystem climb;
  public static SubDrive_SubSystem subDrive;
  public static Vision_SubSystem vision;
  public static OI oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    RobotMap.init();
    
    //Instantiate Subsystems
    bottomCargo = new BottomCargo_SubSystem();
    topCargo = new TopCargo_SubSystem();
    driveTrain = new DriveTrain_SubSystem();
    lift = new Lift_SubSystem(); //NEEDS POT
    climb = new Climb_SubSystem();
    subDrive = new SubDrive_SubSystem(); //NOT DONE
    hatchPanel = new HatchPanel_SubSystem();
    vision = new Vision_SubSystem(); //NOT DONE

    //Instantiate Operator Interface
    oi = new OI(); //NOT DONE
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
    m_autonomousCommand = new c_driveTrain_TeleOpMode();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
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