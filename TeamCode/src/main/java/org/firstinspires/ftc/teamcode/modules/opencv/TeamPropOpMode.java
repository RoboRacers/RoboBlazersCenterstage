//package org.firstinspires.ftc.teamcode.modules.opencv;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.openftc.easyopencv.OpenCvCamera;
//import org.openftc.easyopencv.OpenCvCameraFactory;
//import org.openftc.easyopencv.OpenCvCameraRotation;
//import org.openftc.easyopencv.OpenCvPipeline;
//import org.openftc.easyopencv.OpenCvWebcam;
//
//@TeleOp(name="TeamProp Detect OpMode", group="Linear Opmode")
//public class TeamPropOpMode extends LinearOpMode {
//    private OpenCvCamera camera;
//    private TeamPropPipeline myPropDetection;
//
//    @Override
//    public void runOpMode() {
//        // Initialize the camera
//        int cameraMonitorViewId = hardwareMap.appContext.getResources()
//                .getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//
//        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap
//                .get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
//
//        myPropDetection = new TeamPropPipeline();
//
//        camera.setPipeline(myPropDetection);
//        camera.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
//
//        waitForStart();
//
//        while (opModeIsActive()) {
//            String direction = myPropDetection.getDirection(); // Access the direction
//
//            telemetry.addData("Direction", direction);
//            telemetry.update();
//        }
//
//        camera.closeCameraDevice();
//    }
//}