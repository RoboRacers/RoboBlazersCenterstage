package org.firstinspires.ftc.teamcode.modules.opencv;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvWebcam;

@TeleOp(name="Pixel Detect by Shape OpMode", group="Linear Opmode")
public class PixelDetectByShapeOpmode extends LinearOpMode {

    @Override
    public void runOpMode() {
        OpenCvWebcam camera;

        int cameraMonitorViewId = hardwareMap.appContext.getResources().
                getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.
                get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        telemetry.addData("here in PixelDetectByShapeOpmode:", 1);
        //telemetry.update();
        PixelDetectionByShape myPixelDetectionByShape = new PixelDetectionByShape(camera, telemetry);

        telemetry.addLine("here in PixelDetectByShapeOpmode after creating myPixelDetectionByShape");

        double pixelCenterX = 0.0;
        double pixelCenterY = 0.0;

        waitForStart();
        telemetry.addLine("after wait for start");

        // Run until the end of the match (driver presses STOP;
        int i = 0;
        while (opModeIsActive()) {
            // telemetry.addData("here in while............:", i++);
            //telemetry.update();
            pixelCenterX = myPixelDetectionByShape.getCenterX();
            pixelCenterY = myPixelDetectionByShape.getCenterY();

            int DisplayCenterX = 320;
            int DisplayCenterY = 240;

            String directionX = "left";
            String directionY = "up";

            if (0 < pixelCenterX && pixelCenterX < DisplayCenterX) {
                directionX = "left";
            } else  {
                directionX = "right";
            }

            if (0 < pixelCenterY && pixelCenterY < DisplayCenterY) {
                directionY = "up";
            } else  {
                directionY = "down";
            }

            // telemetry.addData(directionX, directionY);

            //   telemetry.addData("Pixel Center X = ", pixelCenterX);
            // telemetry.addData("Pixel Center Y = ", pixelCenterY);
            //telemetry.update();
        }
        //telemetry.update();
    }
}
