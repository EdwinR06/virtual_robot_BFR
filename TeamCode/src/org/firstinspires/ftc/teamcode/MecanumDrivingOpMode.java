package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorImpl;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.OdomDcMotor;

import virtual_robot.controller.VirtualBot;
import virtual_robot.controller.VirtualGamePadController;

@TeleOp(name = "mecanum driving opmode", group = "ftc16072")
public class MecanumDrivingOpMode extends OpMode {

    private DcMotorImpl frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;

    // Code to run ONCE when the driver hits INIT
    @Override
    public void init() {
        frontLeftMotor = (DcMotorImpl) hardwareMap.get(DcMotor.class, "front_left_motor");
        frontRightMotor =(DcMotorImpl) hardwareMap.get(DcMotor.class, "front_right_motor");
        backLeftMotor = (DcMotorImpl) hardwareMap.get(DcMotor.class, "back_left_motor");
        backRightMotor = (DcMotorImpl) hardwareMap.get(DcMotor.class, "back_right_motor");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

    }

    // Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
    @Override
    public void loop() {
        double drive = -gamepad1.left_stick_y;
        double turn = gamepad1.left_stick_x;

        frontLeftMotor.setPower(drive + turn);
        backLeftMotor.setPower(drive + turn);

        frontRightMotor.setPower(drive - turn);
        backRightMotor.setPower(drive - turn);

        telemetry.addData("x", backRightMotor.getActualPosition());
        telemetry.addData("y", backLeftMotor.getActualPosition());
        telemetry.addData("heading", frontLeftMotor.getActualPosition());

        telemetry.update();

    }
}
