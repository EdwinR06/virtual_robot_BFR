package com.qualcomm.robotcore.hardware;

/**
 * DcMotorImpl subclass that 'smuggles' odometry stats through motor encoders
 * dumb stupid hack that kinds works tho
 * @author Alex Appleby
 */
public class OdomDcMotor extends DcMotorImpl{
    private final Odometery odometery;
    private final DataType dataType;

    public enum DataType{
        X,
        Y,
        HEADING
    }

    public OdomDcMotor(MotorType motorType, Odometery odometery, DataType dataType) {
        super(motorType);
        this.odometery = odometery;
        this.dataType = dataType;
    }

    @Override
    public synchronized double getActualPosition() {
        switch (dataType){
            case X:
                return odometery.getX();
            case Y:
                return odometery.getY();
            case HEADING:
                return odometery.getHeading();
        }
        return 0.0; //to compile
    }
}
