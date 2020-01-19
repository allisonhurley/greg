/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotConstants;
import frc.robot.robots.GregContainer;

public class Indexer extends SubsystemBase {
    DigitalInput[] pcSensors;
    private final double pwrThreshold = 0.05;

    // Components
    /**
     * Creates a new Indexer.
     */
    public Indexer(DigitalInput[] sensors) {
        pcSensors = sensors;
    }

    public static Indexer create() {
        DigitalInput[] pcSensors = new DigitalInput[5];
        for (var i = 0; i < pcSensors.length; ++i) {
            pcSensors[i] = new DigitalInput(RobotConstants.firstPCSensor + i);
        }
        return new Indexer(pcSensors);
    }

    @Override
    public void periodic() {
        updateBallCount();
    }

    public void stop() {
        setPower(0);
    }

    public void feed() {

    }

    public void setPower(double pwr) {

    }

    /**
     * if ball passes through beam break and indexer moving forward, then add ball
     * if ball passes through beam break and indexer moving backward, then bye bye ball
     * else do nothing - ideling
     */
    private void updateBallCount() {
        if(getBeamBreak() && (getPower() > pwrThreshold)) GregContainer.setPowerCellCapacity(GregContainer.getPowerCellCapacity() + 1);
        else if (getBeamBreak() && (getPower() < -pwrThreshold)) GregContainer.setPowerCellCapacity(GregContainer.getPowerCellCapacity() - 1);
    }

    private boolean getBeamBreak() {
        return false;
    }

    public double getPower() {
        return 0d;
    }

    public void safetyOn() {}

    public void safetyOff() {}

    public int getPowerCellCount() {
        return 0;
    }
}
