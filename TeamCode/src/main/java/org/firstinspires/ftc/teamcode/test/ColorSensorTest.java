package org.firstinspires.ftc.teamcode.test;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Color Sensor test", group = "new")
public class ColorSensorTest extends LinearOpMode {


    RevColorSensorV3 colorSensor;

    @Override
    public void runOpMode() throws InterruptedException {

        colorSensor = hardwareMap.get(RevColorSensorV3.class, "color");

        while (opModeInInit()) {

        }

        while (!isStopRequested()) {

            telemetry.addData("Red", colorSensor.red());
            telemetry.addData("Green", colorSensor.green());
            telemetry.addData("Blue", colorSensor.blue());
            telemetry.update();
        }
    }
}
