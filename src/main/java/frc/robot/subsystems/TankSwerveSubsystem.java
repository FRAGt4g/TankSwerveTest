// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TankSwerveSubsystem extends SubsystemBase {
  public static class Hardware {
    public CANSparkMax lFrontMotor, lMiddleMotor, lBackMotor;
    public CANSparkMax rFrontMotor, rMiddleMotor, rBackMotor;

    public Hardware(CANSparkMax lFrontMotor, CANSparkMax lMiddleMotor, CANSparkMax lBackMotor, CANSparkMax rFrontMotor, CANSparkMax rMiddleMotor, CANSparkMax rBackMotor) {
      this.lFrontMotor = lFrontMotor;
      this.lMiddleMotor = lMiddleMotor;
      this.lBackMotor = lBackMotor;

      this.rFrontMotor = rFrontMotor;
      this.rMiddleMotor = rMiddleMotor;
      this.rBackMotor = rBackMotor;
    }


  }

  public CANSparkMax m_lFrontMotor, m_lMiddleMotor, m_lBackMotor;
  public CANSparkMax m_rFrontMotor, m_rMiddleMotor, m_rBackMotor;
  private DifferentialDrive m_driveTrain;

  /** Creates a new TankSwerveSubsystem. */
  public TankSwerveSubsystem(Hardware tankSwervHardware) {
    m_lFrontMotor = tankSwervHardware.lFrontMotor;
    m_lMiddleMotor = tankSwervHardware.lMiddleMotor;
    m_lBackMotor = tankSwervHardware.lBackMotor;

    m_rFrontMotor = tankSwervHardware.rFrontMotor;
    m_rMiddleMotor = tankSwervHardware.rMiddleMotor;
    m_rBackMotor = tankSwervHardware.rBackMotor;

    m_driveTrain = new DifferentialDrive(m_lFrontMotor, m_rFrontMotor);

    m_rFrontMotor.setInverted(true);
    m_rMiddleMotor.setInverted(true);
    m_rBackMotor.setInverted(true);

    m_lMiddleMotor.follow(m_lFrontMotor);
    m_lBackMotor.follow(m_lFrontMotor);

    m_rMiddleMotor.follow(m_rFrontMotor);
    m_rBackMotor.follow(m_rFrontMotor);
  }

  public static Hardware initHardware() {
    return new Hardware(
      new CANSparkMax(Constants.DriveHardware.LEFT_FRONT_MOTOR_PORT, MotorType.kBrushless),
      new CANSparkMax(Constants.DriveHardware.LEFT_MIDDLE_MOTOR_PORT, MotorType.kBrushless),
      new CANSparkMax(Constants.DriveHardware.LEFT_BACK_MOTOR_PORT, MotorType.kBrushless),
      
      new CANSparkMax(Constants.DriveHardware.RIGHT_FRONT_MOTOR_PORT, MotorType.kBrushless),
      new CANSparkMax(Constants.DriveHardware.RIGHT_MIDDLE_MOTOR_PORT, MotorType.kBrushless),
      new CANSparkMax(Constants.DriveHardware.RIGHT_BACK_MOTOR_PORT, MotorType.kBrushless)
      );
  }

  public void teleop(double speed, double turn) {
    m_driveTrain.arcadeDrive(speed, turn);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
