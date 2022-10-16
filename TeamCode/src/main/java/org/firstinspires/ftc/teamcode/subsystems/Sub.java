package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.canvas.Canvas;

public interface Sub {
    void update();

    default void stop() { }
}
