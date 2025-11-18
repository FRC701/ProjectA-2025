// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import java.lang.reflect.Parameter;
import javax.security.auth.login.Configuration.Parameters;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.LauncherSubsystem.LauncherState;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AccelerateCommand extends InstantCommand {

private LauncherSubsystem m_subsystem;

  public AccelerateCommand(LauncherSubsystem subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_subsystem.mLauncherstate = LauncherState.S_accelerate;
  }
}
