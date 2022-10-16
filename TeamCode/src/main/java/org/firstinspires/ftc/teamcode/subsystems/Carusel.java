package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class Carusel implements Sub {

    public enum WheelState {
        RIGHT,
        LEFT,
        STOPPED,
    }

    public WheelState wheelState;

    public CRServo wheelServo;

    public Carusel(HardwareMap hardwareMap, Robot robot) {
        wheelServo = hardwareMap.get(CRServo.class, "wheelServo");

        wheelState = WheelState.STOPPED;
    }

    @Override
    public void update() {
        switch (wheelState) {
            case STOPPED:
                wheelServo.setPower(0);
                break;
            case RIGHT:
                wheelServo.setPower(-1);
                break;
            case LEFT:
                wheelServo.setPower(1);
                break;
        }
    }
}
