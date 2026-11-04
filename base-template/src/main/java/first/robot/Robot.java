// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package first.robot;

import org.littletonrobotics.junction.LogFileUtil;
import org.littletonrobotics.junction.LoggedOpModeRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;
import org.littletonrobotics.junction.wpilog.WPILOGReader;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;
import org.wpilib.framework.OpModeRobot;

/**
 * The methods in this class are called automatically as described in the
 * OpModeRobot documentation.
 * OpMode classes anywhere in the package (or sub-packages) where this class is
 * located are
 * automatically registered to display in the Driver Station. If you change the
 * name of this class
 * or the package after creating this project, you must also update the
 * Main.java file in the
 * project.
 */
public class Robot extends LoggedOpModeRobot {
  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  public Robot() {
    Logger.recordMetadata("ProjectName", "MyProject"); // Set a metadata value

    if (isReal()) {
      Logger.addDataReceiver(new WPILOGWriter()); // Log to a USB stick ("/U/logs")
      Logger.addDataReceiver(new NT4Publisher()); // Publish data to NetworkTables
    } else {
      setUseTiming(false); // Run as fast as possible
      String logPath = LogFileUtil.findReplayLog(); // Pull the replay log from AdvantageScope (or prompt the user)
      Logger.setReplaySource(new WPILOGReader(logPath)); // Read replay log
      Logger.addDataReceiver(new WPILOGWriter(LogFileUtil.addPathSuffix(logPath, "_sim"))); // Save outputs to a new log
    }

    Logger.start(); // Start logging! No more data receivers, replay sources, or metadata values may
                    // be added.

  }

  /** This function is called exactly once when the DS first connects. */
  @Override
  public void driverStationConnected() {
  }

  /**
   * This function is called periodically anytime when no opmode is selected,
   * including when the
   * Driver Station is disconnected.
   */
  @Override
  public void nonePeriodic() {
  }
}
