package org.firstinspires.ftc.teamcode.test;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "Distance Sensor test", group = "new")
public class DistanceSensorTest extends LinearOpMode {

    Rev2mDistanceSensor range;

    @Override
    public void runOpMode() throws InterruptedException {

        range = hardwareMap.get(Rev2mDistanceSensor.class, "range");

        while (opModeInInit()) {

        }

        double distance = 0;

        while (!isStopRequested()) {

            distance = range.getDistance(DistanceUnit.MM);

            telemetry.addData("Distance sensor range", distance);
            telemetry.update();
        }
    }
}
