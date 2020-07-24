package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorImpl;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import virtual_robot.util.loc.Localizer;
import virtual_robot.util.loc.SimulatorLoc;

public class FTCUtil {
    public static HardwareMap hardwareMap;
    public static Telemetry telemetry;
    public static GridLogger gridLogger;

    public static Localizer getLocalizer(DcMotor xMotor, DcMotor yMotor, DcMotor headingMotor){
        //todo add IRL localizer

        return new SimulatorLoc((DcMotorImpl)xMotor, (DcMotorImpl)yMotor, (DcMotorImpl)headingMotor);
    }
}
