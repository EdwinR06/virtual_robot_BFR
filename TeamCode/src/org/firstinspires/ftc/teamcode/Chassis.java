package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorImpl;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.MotorType;

import virtual_robot.util.loc.Localizer;
import virtual_robot.util.loc.SimulatorLoc;

public class Chassis {
    private DriveWheel frontLeft;
    private DriveWheel frontRight;
    private DriveWheel backLeft;
    private DriveWheel backRight;
    private static final double LOOK_AHEAD_DISTANCE = 12;

    public Chassis() {
        FTCUtil.telemetry.addData("Status", "Initialized");
        frontLeft = new DriveWheel("front_left_motor", DcMotor.Direction.FORWARD);
        frontRight = new DriveWheel("front_right_motor", DcMotor.Direction.FORWARD);
        backLeft = new DriveWheel("back_left_motor", DcMotor.Direction.REVERSE);
        backRight = new DriveWheel("back_right_motor", DcMotor.Direction.REVERSE);
    }

    /*static class DriveCommand{
        public Vector driveVector;
        public double turnOutput;

        public DriveCommand(Vector driveVector, double turnOutput) {
            this.driveVector = driveVector;
            this.turnOutput = turnOutput;
        }
    }*/
    private void driveLocalVector(Vector v) {
        frontLeft.setPower(v.x - v.y);
        backLeft.setPower(v.x + v.y);

        frontRight.setPower(v.x + v.y);
        backRight.setPower(v.x - v.y);
    }

    public void driveTowardsPoint(Point target, double power) {
        //Position robotPosition = odometrySystem.getState().position;
        DcMotorImpl xMotor = FTCUtil.hardwareMap.get(DcMotorImpl.class, "back_right_motor");
        DcMotorImpl yMotor = FTCUtil.hardwareMap.get(DcMotorImpl.class, "back_left_motor");
        DcMotorImpl headingMotor = FTCUtil.hardwareMap.get(DcMotorImpl.class, "front_left_motor");
        SimulatorLoc current = new SimulatorLoc(xMotor, yMotor, headingMotor);

        Vector v = getDriveTowardsPointCommands(target, power, new Point(current.getX(), current.getY()), current.getHeading());

        driveLocalVector(v);
    }

    Vector getDriveTowardsPointCommands(Point target, double power, Point current, double heading) {
        double dx = target.x - current.x;
        double dy = target.y - current.y;
        double globalAngleAlongPoint = Math.atan2(dy, dx);

        double angle = globalAngleAlongPoint - heading;
        //Could refactor to angle method in a utilities class
        while (angle > Math.PI) {
            angle -= (2 * Math.PI);
        }
        while (angle < -Math.PI) {
            angle += (2 * Math.PI);
        }
        double localAngleToPoint = angle;

        Vector v = Vector.makeUnitVector(localAngleToPoint);
        v.scale(power);

        return v;
    }

    public void drive(double inches, double motorPower) {
        setTargetDistance(inches);
        setPowers(motorPower);
    }

    public void followPath(Path path, SimulatorLoc current) {
        //todo change to alex's subclass that contains heading as well
        Point currentPosition = new Point(current.getX(), current.getY());
        double power;
        double heading = current.getHeading();
        Path.TargetPoint target = path.targetPoint(currentPosition, LOOK_AHEAD_DISTANCE);
        power = target.power;
        driveTowardsPoint(target.point, power);

        currentPosition = target.point;
    }

    private void stopMotors() {
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
    }

    private void setTargetDistance(double inches) {
        frontLeft.setTargetDistance(inches);
        frontRight.setTargetDistance(inches);
        backLeft.setTargetDistance(inches);
        backRight.setTargetDistance(inches);
    }

    private void setPowers(double motorPower) {
        frontLeft.setPower(motorPower);
        frontRight.setPower(motorPower);
        backLeft.setPower(motorPower);
        backRight.setPower(motorPower);
    }

    /*public void strafe(double distance) {
        double strafe;
        if (distance > 0) {
            strafe = 1;
        } else {
            strafe = -1;
        }
        double turn = 0;
        double drive = 0;

        while () {
            frontLeft.setPower(drive + strafe + turn);
            backLeft.setPower(drive - strafe + turn);
            frontRight.setPower(drive - strafe - turn);
            backRight.setPower(drive + strafe - turn);
        }
    }

    public void turn(double distance) {
        double drive = 0;
        double strafe = 0;
        double turn = 1;
        while () {
            frontLeft.setPower(drive + strafe + turn);
            backLeft.setPower(drive - strafe + turn);
            frontRight.setPower(drive - strafe - turn);
            backRight.setPower(drive + strafe - turn);
        }
    }*/

    public void followPath() {
        frontLeft.setPower(.5);
        backLeft.setPower(.5);
        frontRight.setPower(.5);
        backRight.setPower(.5);
    }
}
