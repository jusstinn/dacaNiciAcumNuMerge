package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.CapstoneArm;
import org.firstinspires.ftc.teamcode.subsystems.Carusel;
import org.firstinspires.ftc.teamcode.subsystems.Glider;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;
import org.firstinspires.ftc.teamcode.subsystems.Robot;
import org.firstinspires.ftc.teamcode.util.StickyGamepad;

@Config
@TeleOp
public class TeleOP extends OpMode {
    Robot robot;

    enum DriveMode {
        FAST,
        SLOW
    }

    StickyGamepad stickyGamepad1;
    StickyGamepad stickyGamepad2;

    MultipleTelemetry telemetry;

    DriveMode driveMode;

    @Override
    public void init() {
        telemetry = new MultipleTelemetry(super.telemetry, FtcDashboard.getInstance().getTelemetry());

        robot = new Robot(this, false);

        stickyGamepad1 = new StickyGamepad(gamepad1);
        stickyGamepad2 = new StickyGamepad(gamepad2);

        driveMode = DriveMode.FAST;

        telemetry.log().add("Ready!");
    }

    @Override
    public void start() {
        robot.start();
    }

    @Override
    public void stop() {
        robot.stop();
    }

    @Override
    public void loop() {
        stickyGamepad1.update();
        stickyGamepad2.update();

        // Driver 1 controls -----------------------------------------------------------------------
        if (!robot.drive.isBusy()) {
            switch (driveMode) {
                // TODO: tune this to liking
                case FAST:
                    robot.drive.setMotorPowersFromGamepad(gamepad1, 0.7);
                    break;
                case SLOW:
                    robot.drive.setMotorPowersFromGamepad(gamepad1, 0.4);
                    break;
            }
        }

        if (stickyGamepad1.right_bumper) {
            driveMode = DriveMode.FAST;
        }
        if (stickyGamepad1.left_bumper) {
            driveMode = DriveMode.SLOW;
        }

        if (gamepad1.right_trigger > 0.1) {
            robot.carusel.wheelState = Carusel.WheelState.LEFT;
        } else if (gamepad1.left_trigger > 0.1) {
            robot.carusel.wheelState = Carusel.WheelState.RIGHT;
        } else {
            robot.carusel.wheelState = Carusel.WheelState.STOPPED;
        }
        // -----------------------------------------------------------------------------------------

        // Driver 2 controls -----------------------------------------------------------------------
        if (stickyGamepad2.a) {
            if (robot.intake.intakeState == Intake.IntakeState.IDLE) {
                robot.intake.intakeState = Intake.IntakeState.COLLECT;
            } else {
                robot.intake.intakeState = Intake.IntakeState.IDLE;
            }
        }
        if (gamepad2.right_trigger > 0.1) {
            robot.intake.intakeState = Intake.IntakeState.REVERSE;
        } else if (robot.intake.intakeState != Intake.IntakeState.COLLECT) {
            robot.intake.intakeState = Intake.IntakeState.IDLE;
            robot.intake.motorIntake.setPower(0);
        }

        if (gamepad2.right_bumper) {
            robot.glider.sliderState = Glider.SliderState.EXTEND;
        } else if (gamepad2.left_bumper) {
            robot.glider.sliderState = Glider.SliderState.RETRACT;
        } else {
            robot.glider.sliderState = Glider.SliderState.IDLE;
        }

        if (stickyGamepad2.dpad_up){
            if (robot.outtake.dropperState == Outtake.DroperState.COLLECTING) {
                robot.outtake.dropperState = Outtake.DroperState.UP;
            } else if (robot.outtake.dropperState == Outtake.DroperState.UP) {
                robot.outtake.dropperState = Outtake.DroperState.SCORING;
            }
        }
        if (stickyGamepad2.dpad_down) {
            robot.outtake.dropperState = Outtake.DroperState.COLLECTING;
        }

        if (stickyGamepad2.x){
            robot.capstoneArm.armState = CapstoneArm.ArmState.INITIAL;
        }
        if (stickyGamepad2.b){
            robot.capstoneArm.armState = CapstoneArm.ArmState.UP;
        }
        // -----------------------------------------------------------------------------------------

//        telemetry.addData("Pose estimate", robot.drive.getPoseEstimate());
//        telemetry.addData("Glider Height", robot.glider.getCurrentPosition());
//        telemetry.addData("Intake state", robot.intake.intakeState);
//        telemetry.addData("Outtake servo state", robot.outtake.dropperState);
//        telemetry.update();
    }
}
