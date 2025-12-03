// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.security.Key;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Timer;
//imports n stuff^

public class LauncherSubsystem extends SubsystemBase {
  private Timer mTimer;
  /** Creates a new LauncherNeutralSubsystem. */
  private TalonFX TopMotor;
  private TalonFX BottomMotor;
  //creating motors

  //temporary string
  public String launcherString = "69";

public enum LauncherState {
  S_neutral, S_accelerate, S_Launch
  //creating states ^
}
  public static LauncherState mLauncherstate;

  public LauncherSubsystem() {
    //creating object ^'
    mTimer = new Timer();
    TopMotor = new TalonFX(Constants.LauncherConstants.KTopMotor);
    BottomMotor = new TalonFX(Constants.LauncherConstants.KBottomMotor);
    //Identifing object ^
    mLauncherstate = LauncherState.S_neutral;
    //Setting state ^
    TopMotor.setControl(new Follower(BottomMotor.getDeviceID(), true));
    // Makes it so the rotation of the top motor is mirrored by the bottom motor
  }
  
  public void neutral() {
    TopMotor.setVoltage(3);
    //state action for neutral
  }

  public void accelerate() {
    TopMotor.setVoltage(8);
    //state action for accelerate
  }
  public void Launch() {
    TopMotor.setVoltage(8);
    if(!mTimer.isRunning()) {
      mTimer.start();
    }
    if(mTimer.hasElapsed(2)) {
        mTimer.stop();
        mTimer.reset();
        mLauncherstate = LauncherState.S_neutral;
    }
  }


  public void RunLauncherState() {
    switch (mLauncherstate) {
      case S_neutral:
        neutral();
        break;
      case S_accelerate:
        accelerate();
        break;
      case S_Launch:
        Launch();
        break;
        // ^ making it so based on state it changes action 
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    RunLauncherState();
    SmartDashboard.putString("LauncherState", launcherString);
    //making so state gets changed ^
  }
}
