package virtual_robot.util.loc;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorImpl;

public abstract class Localizer {
    protected DcMotorImpl xMotor, yMotor, headingMotor;

    protected Localizer(DcMotorImpl xMotor, DcMotorImpl yMotor, DcMotorImpl headingMotor) {
        this.xMotor = xMotor;
        this.yMotor = yMotor;
        this.headingMotor = headingMotor;
    }

    public abstract double getX();
    public abstract double getY();
    public abstract double getHeading();
}
