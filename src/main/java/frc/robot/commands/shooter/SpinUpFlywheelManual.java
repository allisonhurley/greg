/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lightning.util.LightningMath;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

public class SpinUpFlywheelManual extends CommandBase {

    Shooter shooter;
    double speed;

    /**
     * Creates a new Fire.
     */
    public SpinUpFlywheelManual(Shooter shooter, double speed) {
        this.shooter = shooter;
        this.speed = speed;

        addRequirements(shooter);
    }

    // Called when command starts
    @Override
    public void initialize() {
        shooter.setShooterVelocity(speed);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
//        return false;
        return LightningMath.epsilonEqual(shooter.getFlywheelMotor1Velocity(), shooter.motor1setpoint, Constants.FLYWHEEL_EPSILON) &&
               LightningMath.epsilonEqual(shooter.getFlywheelMotor2Velocity(), shooter.motor2setpoint, Constants.FLYWHEEL_EPSILON) &&
               LightningMath.epsilonEqual(shooter.getFlywheelMotor3Velocity(), shooter.motor3setpoint, Constants.FLYWHEEL_EPSILON);
    }
}
