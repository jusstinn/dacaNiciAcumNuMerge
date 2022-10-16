package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class Intake implements Sub {
    public static double INTAKE_IDLE_POWER = 0;
    public static double INTAKE_COLLECT_POWER = 0.7;
    public static double INTAKE_REVERSE_POWER = -0.5;

    public enum IntakeState {
        COLLECT,
        IDLE,
        REVERSE
    }

    public DcMotor motorIntake;

    public IntakeState intakeState;

    public Intake(HardwareMap hardwareMap, Robot robot) {
        motorIntake = hardwareMap.get(DcMotor.class, "intakeMotor");

//        motorIntake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorIntake.setDirection(DcMotorSimple.Direction.REVERSE);

        intakeState = IntakeState.IDLE;
    }

    @Override
    public void update() {
        switch (intakeState) {
            case IDLE:
                motorIntake.setPower(INTAKE_IDLE_POWER);
                break;
            case COLLECT:
                motorIntake.setPower(INTAKE_COLLECT_POWER);
                break;
            case REVERSE:
                motorIntake.setPower(INTAKE_REVERSE_POWER);
                break;
        }
    }
}
