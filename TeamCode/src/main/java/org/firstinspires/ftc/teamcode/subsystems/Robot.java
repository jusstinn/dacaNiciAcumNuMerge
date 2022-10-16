package org.firstinspires.ftc.teamcode.subsystems;

import android.util.Log;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManagerNotifier;
import com.qualcomm.robotcore.util.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class Robot implements OpModeManagerNotifier.Notifications {
    public static final String TAG = "Robot";

    public FtcDashboard dashboard;

    private boolean started;
    private ExecutorService subsystemUpdateExecutor;
    private List<Sub> subsystems;

    private Runnable subsystemUpdateRunnable = () -> {
        while (!Thread.currentThread().isInterrupted()) {
            for (Sub subsystem : subsystems) {
                if (subsystem == null) continue;
                try {
                    subsystem.update();
                } catch (Throwable t) {
                    Log.w(TAG, "Subsystem update failed for " + subsystem.getClass().getSimpleName() + ": " + t.getMessage());
                }
            }
        }
    };

    public Carusel carusel;
    public DriveTrain drive;
    public Intake intake;
    public Outtake outtake;
    public Glider glider;
    public CapstoneArm capstoneArm;

    public Robot(OpMode opmode, boolean isAutonomous) {
        dashboard = FtcDashboard.getInstance();
        dashboard.setTelemetryTransmissionInterval(25);

        subsystems = new ArrayList<>();

        try {
            drive = new DriveTrain(opmode.hardwareMap, this, isAutonomous);
            subsystems.add(drive);
        } catch (Exception e) {
            Log.w(TAG, "skipping Drivetrain");
        }

        try {
            carusel = new Carusel(opmode.hardwareMap, this);
            subsystems.add(carusel);
        } catch (Exception e) {
            Log.w(TAG, "skipping Carusel");
        }

        try {
            intake = new Intake(opmode.hardwareMap, this);
            subsystems.add(intake);
        } catch (Exception e) {
            Log.w(TAG, "skipping Intake");
        }

        try {
            outtake = new Outtake(opmode.hardwareMap, this);
            subsystems.add(outtake);
        } catch (Exception e) {
            Log.w(TAG, "skipping Outtake");
        }

        try {
            glider = new Glider(opmode.hardwareMap, this);
            subsystems.add(glider);
        } catch (Exception e) {
            Log.w(TAG, "skipping Glider");
        }

        try {
            capstoneArm = new CapstoneArm(opmode.hardwareMap, this);
            subsystems.add(capstoneArm);
        } catch (Exception e) {
            Log.w(TAG, "skipping CapstoneArm");
        }

        subsystemUpdateExecutor = ThreadPool.newSingleThreadExecutor("subsystem update");
    }

    public void start() {
        if (!started) {
            subsystemUpdateExecutor.submit(subsystemUpdateRunnable);
            started = true;
        }
    }

    public void stop() {
        if (started && subsystemUpdateExecutor != null) {
            subsystemUpdateExecutor.shutdownNow();
            subsystemUpdateExecutor = null;

            for (Sub subsystem : subsystems) {
                subsystem.stop();
            }
        }
    }

    public void sleep(double seconds) {
        try {
            Thread.sleep(Math.round(seconds * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void onOpModePreInit(OpMode opMode) {

    }

    @Override
    public void onOpModePreStart(OpMode opMode) {

    }

    @Override
    public void onOpModePostStop(OpMode opMode) {
        stop();
    }
}


