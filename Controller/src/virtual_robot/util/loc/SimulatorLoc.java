package virtual_robot.util.loc;

import com.qualcomm.robotcore.hardware.DcMotorImpl;

public class SimulatorLoc extends Localizer {
    public SimulatorLoc(DcMotorImpl xMotor, DcMotorImpl yMotor, DcMotorImpl headingMotor) {
        super(xMotor, yMotor, headingMotor);
    }

    @Override
    public double getX() {
        return xMotor.getActualPosition();
    }

    @Override
    public double getY() {
        return yMotor.getActualPosition();
    }

    @Override
    public double getHeading() {
        return headingMotor.getActualPosition();
    }
}
