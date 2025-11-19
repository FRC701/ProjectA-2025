// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Feeder extends SubsystemBase {
  private TalonFX mFeedermotor; 
  /** Creates a new Feeder. */
 
  public static FeederState mFeederState; 

  public enum FeederState {

    S_empty, S_full, S_feeding       
  }
   
  public Feeder() {
    mFeederState = FeederState.S_empty; 

    mFeedermotor = new TalonFX(0);
  }

  public void runFeederState(){
    switch (mFeederState) {

      case S_empty:
      spinFeederMotor();
      break;
     
      case S_full:
      stopFeedermotor();
      break;
     
      case S_feeding:
      StartFeeding();
      break;
    }
  }
  public void spinFeederMotor(){
    mFeedermotor.setVoltage(4.1);
  }
  public void stopFeedermotor(){
    mFeedermotor.setVoltage(0);
  }
  public void StartFeeding(){
    mFeedermotor.setVoltage(6.7);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}