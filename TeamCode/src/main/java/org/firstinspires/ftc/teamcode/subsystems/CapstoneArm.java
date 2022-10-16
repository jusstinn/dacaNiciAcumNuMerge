package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class CapstoneArm implements Sub{
    // TODO: get correct positions
    public static double INITIAL_POSITION = 0;
    public static double UP_POSITION = 0.7;

    public enum ArmState{
        INITIAL,
        UP
    }

    public ArmState armState;

    public Servo armServo;

    public CapstoneArm(HardwareMap hardwareMap, Robot robot) {
        armServo = hardwareMap.get(Servo.class, "armServo");

        armState = ArmState.INITIAL;
    }

    @Override
    public void update() {
        switch (armState){
            case INITIAL:
                armServo.setPosition(INITIAL_POSITION);
                break;
            case UP:
                armServo.setPosition(UP_POSITION);
                break;
        }
    }
}
