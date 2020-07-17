package virtual_robot.controller.robots.classes;

import com.qualcomm.hardware.bosch.BNO055IMUImpl;
import com.qualcomm.robotcore.hardware.DcMotorImpl;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.MotorType;
import com.qualcomm.robotcore.hardware.OdomDcMotor;

import com.qualcomm.robotcore.hardware.Odometery;
import com.qualcomm.robotcore.hardware.ServoImpl;

import virtual_robot.controller.BotConfig;

/**
 * A hacked version of MecanumBot that uses motor encoders to siphon localization data to the opmode.
 */
@BotConfig(name = "Mecanum Bot with Odometry", filename = "localized_mechanum_bot")
public class LocalizedMecanumBot extends MecanumBot {

    @Override
    protected void createHardwareMap() {
        //super.createHardwareMap();
        motorType = MotorType.Neverest40;
        hardwareMap = new HardwareMap();

        String[] distNames = new String[]{"front_distance", "left_distance", "back_distance", "right_distance"};
        for (String name: distNames) hardwareMap.put(name, controller.new DistanceSensorImpl());
        //hardwareMap.put("gyro_sensor", controller.new GyroSensorImpl());
        hardwareMap.put("imu", new BNO055IMUImpl(this, 10));
        hardwareMap.put("color_sensor", controller.new ColorSensorImpl());
        hardwareMap.put("back_servo", new ServoImpl());

        Odometery odometery = new Odometery(this);
        hardwareMap.put("back_right_motor", new OdomDcMotor(motorType, odometery, OdomDcMotor.DataType.X));
        hardwareMap.put("back_left_motor", new OdomDcMotor(motorType, odometery, OdomDcMotor.DataType.Y));
        hardwareMap.put("front_left_motor", new OdomDcMotor(motorType, odometery, OdomDcMotor.DataType.HEADING));
        hardwareMap.put("front_right_motor", new DcMotorImpl(motorType));

    }
}
