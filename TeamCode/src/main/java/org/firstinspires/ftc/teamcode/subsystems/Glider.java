package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class Glider implements Sub {
    public static double IDLE_POWER = 0.1;

    public static double EXTEND_POWER = 0.8;
    public static double EXTEND_POWER_SLOW = 0.5;
    public static double RETRACT_POWER = -0.5;

    public enum SliderState {
        IDLE,
        EXTEND,
        RETRACT
    }

    public DcMotor slide;

    public SliderState sliderState;

    public Glider(HardwareMap hardwareMap, Robot robot) {
        slide = hardwareMap.get(DcMotor.class, "slideMotor");

        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        sliderState = SliderState.IDLE;
    }

    public double getCurrentPosition() {
        return slide.getCurrentPosition();
    }

    @Override
    public void update() {
        switch (sliderState) {
            case IDLE:
                slide.setPower(IDLE_POWER);
                break;
            case EXTEND:
                slide.setPower(EXTEND_POWER);
                break;
            case RETRACT:
                slide.setPower(RETRACT_POWER);
                break;
        }
    }
}
