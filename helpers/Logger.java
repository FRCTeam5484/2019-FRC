package frc.robot.helpers;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.*;

public class Logger {
	private BufferedWriter writer;
	private boolean logging = true;
	private final String loggerBoolean = "Logging";
	private static Logger instance;
	private String fileName = "log";
	private final String SDFileName = "File Name: ";
	DriverStation ds;
    private int max = 0;
    private String path;

    public static Logger getInstance() {
		if (instance == null) {
			instance = new Logger();
		}
		return instance;
	}

    private Logger() {
		this.ds = DriverStation.getInstance();
		SmartDashboard.putBoolean(this.loggerBoolean, this.logging);
		this.logging = SmartDashboard.getBoolean(this.loggerBoolean, false);
		SmartDashboard.putString(this.SDFileName, this.fileName);
		this.fileName = SmartDashboard.getString(SDFileName, null);
		File f = new File("/home/lvuser/logs");
		if (!f.exists()) {
			System.out.println("/logs did not exist!");
			System.out.println(f.mkdir());
		} else {
			System.out.println("/logs exists!");
		}

		File[] files = new File("/home/lvuser/logs").listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isFile()) {
					try {
						int index = Integer.parseInt(file.getName().split("_")[0]);
						if (index > max) {
							max = index;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			max = 0;
		}
	}

	public void openFile() {
		if (this.wantToLog() || this.ds.isFMSAttached()) {
			try {
				path = this.getPath();
				this.writer = new BufferedWriter(new FileWriter(path));
                //headers
                this.writer.write("MatchNumber,Time,FMSStatus,RobotStatus,Mode,BatteryVoltage,MatchTime\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String getPath() {
		this.fileName = SmartDashboard.getString(SDFileName, null);
		if (this.ds.isFMSAttached()) {
			return String.format("/home/lvuser/logs/%d_%s_%d_log.csv", ++this.max, this.ds.getAlliance().name(),
					this.ds.getLocation());
		} else if (this.fileName != null) {
			return String.format("/home/lvuser/logs/%d_%s.csv", ++this.max, this.fileName);
		} else {
			return String.format("/home/lvuser/logs/%d_log.csv", ++this.max);
		}
	}

	public void logAll() {
		if (this.wantToLog()) {
			try {
                String mode = "Disabled";
                if(ds.getInstance().isAutonomous()) {
                    mode = "Auto";
                }
                else if (ds.getInstance().isOperatorControl()){
                    mode = "TeleOp";
                }
                String status = "Disabled";
                if(ds.getInstance().isEnabled())
                {
                    status = "Enabled";
                }

                StringBuilder sb = new StringBuilder();
                sb.append(String.format("%f,", ds.getInstance().getMatchNumber()));
				sb.append(String.format("%f,", Timer.getFPGATimestamp()));
                sb.append(String.format("%f,", ds.getInstance().isFMSAttached()));
				sb.append(String.format("%f,", status));
                sb.append(String.format("%f,", mode));
				sb.append(String.format("%f,", ds.getInstance().getBatteryVoltage()));
                sb.append(String.format("%f,", ds.getInstance().getMatchTime()));
                
                this.writer.write(sb.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean wantToLog() {
		this.logging = SmartDashboard.getBoolean(this.loggerBoolean, false);
		return this.logging;
	}

	public void turnLoggingOn() {
		this.logging = true;
		SmartDashboard.putBoolean(this.loggerBoolean, this.logging);
	}

	public void turnLoggingOff() {
		this.logging = false;
		SmartDashboard.putBoolean(this.loggerBoolean, this.logging);
	}

	public void close() {
		if (this.wantToLog()) {
			if (this.writer != null) {
				try {
					this.writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}