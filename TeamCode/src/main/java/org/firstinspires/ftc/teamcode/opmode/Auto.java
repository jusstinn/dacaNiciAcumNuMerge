package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.opmode.trajectories.Trajectories;
import org.firstinspires.ftc.teamcode.subsystems.Carusel;
import org.firstinspires.ftc.teamcode.subsystems.Glider;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;
import org.firstinspires.ftc.teamcode.subsystems.Robot;

import java.util.List;

@Autonomous
public class Auto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = new Robot(this, true);
        robot.drive.setPoseEstimate(Trajectories.START_POSE);

        List<Trajectory> trajectories = Trajectories.getTrajectories();

        while (!opModeIsActive() && !isStopRequested()) {
            // mare smecherie
        }

        robot.start();

        robot.drive.followTrajectory(trajectories.get(0)); // cica sa mearga in fata

        robot.glider.sliderState = Glider.SliderState.EXTEND;
        robot.sleep(0.6);
        robot.glider.sliderState = Glider.SliderState.IDLE;
        robot.outtake.dropperState = Outtake.DroperState.SCORING;
        robot.sleep(2);
        robot.outtake.dropperState = Outtake.DroperState.COLLECTING;
        robot.sleep(1.5);
        robot.glider.sliderState = Glider.SliderState.RETRACT;
        robot.sleep(1);
        robot.glider.sliderState = Glider.SliderState.IDLE;


        robot.drive.followTrajectory(trajectories.get(1)); // sa duce la stanga

        robot.drive.followTrajectory(trajectories.get(2)); // sa duce in carusel

        robot.drive.setMotorPowers(-0.1,-0.1, -0.1, -0.1);
        robot.carusel.wheelState = Carusel.WheelState.RIGHT;
        robot.sleep(2.5);
        robot.carusel.wheelState = Carusel.WheelState.STOPPED;
        robot.drive.setMotorPowers(0, 0, 0, 0);

        robot.drive.followTrajectory(trajectories.get(3)); // sa departeaza

        robot.drive.followTrajectory(trajectories.get(4)); // sa duce de unde a plecat

        robot.drive.followTrajectory(trajectories.get(5)); // si sa parkeaza

        robot.stop();
    }
}
