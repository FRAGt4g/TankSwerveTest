// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  public static class Hardware {
    public WPI_TalonSRX lFrontMotor, lMiddleMotor, lBackMotor;
    public WPI_TalonSRX rFrontMotor, rMiddleMotor, rBackMotor;

    public Hardware(WPI_TalonSRX lFrontMotor, WPI_TalonSRX lMiddleMotor, WPI_TalonSRX lBackMotor, WPI_TalonSRX rFrontMotor, WPI_TalonSRX rMiddleMotor, WPI_TalonSRX rBackMotor) {
      this.lFrontMotor = lFrontMotor;
      this.lMiddleMotor = lMiddleMotor;
      this.lBackMotor = lBackMotor;

      this.rFrontMotor = rFrontMotor;
      this.rMiddleMotor = rMiddleMotor;
      this.rBackMotor = rBackMotor;
    }


  }

  public WPI_TalonSRX m_lFrontMotor, m_lMiddleMotor, m_lBackMotor;
  public WPI_TalonSRX m_rFrontMotor, m_rMiddleMotor, m_rBackMotor;
  private DifferentialDrive m_driveTrain;

  /** Creates a new TankSwerveSubsystem. */
  public DriveSubsystem(Hardware tankSwervHardware) {
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
      new WPI_TalonSRX(Constants.DriveHardware.LEFT_FRONT_MOTOR_PORT),
      new WPI_TalonSRX(Constants.DriveHardware.LEFT_MIDDLE_MOTOR_PORT),
      new WPI_TalonSRX(Constants.DriveHardware.LEFT_BACK_MOTOR_PORT),
      
      new WPI_TalonSRX(Constants.DriveHardware.RIGHT_FRONT_MOTOR_PORT),
      new WPI_TalonSRX(Constants.DriveHardware.RIGHT_MIDDLE_MOTOR_PORT),
      new WPI_TalonSRX(Constants.DriveHardware.RIGHT_BACK_MOTOR_PORT)
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
