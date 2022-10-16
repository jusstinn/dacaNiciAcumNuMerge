package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Outtake implements Sub {
    public static double COLLECTING_POSITION = 0.25;
    public static double UP_POSITION = 0.825;
    public static double SCORING_POSITION = 1;

    public enum  DroperState {
        COLLECTING,
        UP,
        SCORING
    }

    public DroperState dropperState;

    public Servo dropperServo;

    public Outtake(HardwareMap hardwareMap, Robot robot){
        dropperServo = hardwareMap.get(Servo.class, "dropperServo");

        dropperState = DroperState.COLLECTING;
    }

    @Override
    public void update() {
        switch (dropperState) {
            case COLLECTING:
                dropperServo.setPosition(COLLECTING_POSITION);
                break;
            case UP:
                dropperServo.setPosition(UP_POSITION);
                break;
            case SCORING:
                dropperServo.setPosition(SCORING_POSITION);
                break;
        }
    }
}

