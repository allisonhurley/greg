package frc.lightning.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;

import java.util.Objects;

public interface LightningDrivetrain extends Subsystem {
    public class DriveCommand {
        public double leftCommand;
        public double rightCommand;

        public DriveCommand(double left, double right) {
            leftCommand = left;
            rightCommand = right;
        }

        @Override
        public String toString() {
            return "DriveCommand{" +
                   "leftCommand=" + leftCommand +
                   ", rightCommand=" + rightCommand +
                   '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DriveCommand that = (DriveCommand) o;
            return Double.compare(that.leftCommand, leftCommand) == 0 &&
                   Double.compare(that.rightCommand, rightCommand) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(leftCommand, rightCommand);
        }
    }

    public default void init() {
    }

    public void setPower(double left, double right);

    public default void setPower(DriveCommand cmd) {
        setPower(cmd.leftCommand, cmd.rightCommand);
    }

    public void setVelocity(double left, double right);

    public default void setVelocity(DriveCommand cmd) {
        setVelocity(cmd.leftCommand, cmd.rightCommand);
    }

    public void resetDistance();

    public double getLeftDistance();

    public double getRightDistance();

    public double getLeftVelocity();

    public double getRightVelocity();

    public default void stop() {
        setPower(0, 0);
    }

    public void brake();

    public void coast();

    public default double getDistance() {
        return (getLeftDistance() + getRightDistance()) / 2;
    }

    public default double getVelocity() {
        return (getRightVelocity() + getLeftVelocity()) / 2;
    }
}

