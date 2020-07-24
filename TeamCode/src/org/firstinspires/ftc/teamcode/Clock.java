package org.firstinspires.ftc.teamcode;

public interface Clock {
    /**
     * Returns time in milliseconds since last call to reset
     * */
    long getCurrentTime();
    void reset();
}
