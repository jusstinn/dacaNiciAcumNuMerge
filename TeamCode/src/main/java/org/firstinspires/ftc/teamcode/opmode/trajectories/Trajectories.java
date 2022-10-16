package org.firstinspires.ftc.teamcode.opmode.trajectories;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;

import static org.firstinspires.ftc.teamcode.subsystems.DriveConstants.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Trajectories {
    public static final Pose2d START_POSE = new Pose2d(-12, -64, Math.toRadians(-90));

    private static Pose2d getTrajectorySequenceEndPos(List<Trajectory> trajectories) {
        if (trajectories.size() == 0) {
            return START_POSE;
        } else {
            return trajectories.get(trajectories.size() - 1).end();
        }
    }

    private static TrajectoryBuilder makeTrajectoryBuilder(List<Trajectory> trajectories, double startHeading, TrajectoryVelocityConstraint velocityConstraint, TrajectoryAccelerationConstraint accelerationConstraint) {
        return new TrajectoryBuilder(getTrajectorySequenceEndPos(trajectories), startHeading, velocityConstraint, accelerationConstraint);
    }

    private static TrajectoryBuilder makeTrajectoryBuilder(List<Trajectory> trajectories, double startHeading) {
        return new TrajectoryBuilder(getTrajectorySequenceEndPos(trajectories), startHeading, BASE_VEL_CONSTRAINT, BASE_ACCEL_CONSTRAINT);
    }

    public static List<Trajectory> getTrajectories() {
        List<Trajectory> trajectories = new ArrayList<>();

        trajectories.add(makeTrajectoryBuilder(trajectories, Math.toRadians(-90))
                .lineToLinearHeading(new Pose2d(-12, -40, Math.toRadians(-90)))
                .build()
        );

        trajectories.add(makeTrajectoryBuilder(trajectories, Math.toRadians(-90))
                .lineToSplineHeading(new Pose2d(-70, -36, Math.toRadians(90.1)))
                .build()
        );

        trajectories.add(makeTrajectoryBuilder(trajectories, Math.toRadians(90))
                .lineToLinearHeading(new Pose2d(-70, -60, Math.toRadians(90)))
                .build()
        );

        trajectories.add(makeTrajectoryBuilder(trajectories, Math.toRadians(90))
                .lineToLinearHeading(new Pose2d(-70, -45, Math.toRadians(90)))
                .build()
        );

        trajectories.add(makeTrajectoryBuilder(trajectories, Math.toRadians(90))
                .lineToSplineHeading(new Pose2d(-12, -72, Math.toRadians(0)))
                .build()
        );

        trajectories.add(makeTrajectoryBuilder(trajectories, Math.toRadians(0))
                .lineToLinearHeading(new Pose2d(48, -70, Math.toRadians(0)))
                .build()
        );

        return trajectories;
    }
}
