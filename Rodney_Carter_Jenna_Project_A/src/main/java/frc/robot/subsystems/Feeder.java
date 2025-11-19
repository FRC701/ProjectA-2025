// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.ForwardLimitValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Feeder extends SubsystemBase {
  private TalonFX mFeedermotor; 
  private TalonFXConfiguration mTalonFXConfig;
  /** Creates a new Feeder. */
 
  public static FeederState mFeederState; 

  public enum FeederState {

    S_empty, S_full, S_feeding       
  }
   
  public Feeder() {
    mFeederState = FeederState.S_empty; 

    mFeedermotor = new TalonFX(0);

    //Applys/Creates connection to the banner sensor
    mTalonFXConfig = new TalonFXConfiguration();
    mTalonFXConfig.HardwareLimitSwitch.ForwardLimitEnable = false;
    mFeedermotor.getConfigurator().apply(mTalonFXConfig);
    var fx_cfg = new MotorOutputConfigs();
    fx_cfg.NeutralMode = NeutralModeValue.Brake;
    mFeedermotor.getConfigurator().apply(fx_cfg);
  }

  //For the banner sensor, returns true if empty, returns false if full
  public boolean revLimitStatus() {
    return (mFeedermotor.getForwardLimit().getValue() == ForwardLimitValue.ClosedToGround);
  }

  public void runFeederState(){
    
    switch (mFeederState) {

      case S_empty:
        if(revLimitStatus()){
          spinFeederMotor();
        }else{
          mFeederState = FeederState.S_full;
        }
        break;
     
      case S_full:
        stopFeedermotor();
        break;
     
      case S_feeding:
        if(!revLimitStatus()){
          spinFeederMotor();
        }else{
          mFeederState = FeederState.S_empty;
        }
        break;
    }
  }

  public void spinFeederMotor(){
    mFeedermotor.setVoltage(4.1);
  }

  public void stopFeedermotor(){
    mFeedermotor.setVoltage(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    runFeederState();
  }
}