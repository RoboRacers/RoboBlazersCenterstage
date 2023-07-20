package org.firstinspires.ftc.teamcode.test;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "Potmeter Test", group = "new")
public class PotmeterTest extends LinearOpMode {

    AnalogInput potmeter;

    @Override
    public void runOpMode() throws InterruptedException {

        potmeter = hardwareMap.get(AnalogInput.class, "potmeter1");

        while (opModeInInit()) {

        }

        double angle = 0;

        double x = 1;

        while (!isStopRequested()) {

            x = potmeter.getVoltage();

            angle = (x*81.8);


            telemetry.addData("Potentiomter Angle", angle);
            telemetry.addData("Voltage", x);
            telemetry.update();
        }
    }
}
