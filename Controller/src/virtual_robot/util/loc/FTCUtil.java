package virtual_robot.util.loc;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorImpl;

public class FTCUtil {

    public static Localizer getLocalizer(DcMotor xMotor, DcMotor yMotor, DcMotor headingMotor){
        //todo add IRL localizer
        
        return new SimulatorLoc((DcMotorImpl)xMotor, (DcMotorImpl)yMotor, (DcMotorImpl)headingMotor);
    }
}
