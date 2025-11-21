// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;



public class TheIntake extends SubsystemBase {
  private TalonFX IntakeMotor;
  public static IntakeState mIntakeState; 
  
 
 public enum IntakeState {
    S_full, S_empty
  }

 
  /** Creates a new TheIntake. */
  
  public TheIntake() {
    IntakeMotor = new TalonFX(Constants.TheIntake.kIntakeMotor);
    mIntakeState = IntakeState.S_empty;
  

  }

  public void runIntakeState() {
    switch (mIntakeState) {
      case S_empty:
      spinIntakeMotor();
      break;
      case S_full:
      stopIntakeMotor();
      break;
    }
  }

  public void spinIntakeMotor() {
    IntakeMotor.setVoltage(5);
  }

  public void stopIntakeMotor() {
    IntakeMotor.setVoltage(0);
  }

 
  @Override
  public void periodic() {
    runIntakeState();
    // This method will be called once per scheduler run
  }
}
