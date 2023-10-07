package org.firstinspires.ftc.teamcode.modules.opencv;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class PixelDetectionByShape {
    PixelDetectionPipeline pixelDetectionPipeline;
    Telemetry telemetry;


    public PixelDetectionByShape(OpenCvCamera camera, Telemetry telemetry) {
        this.telemetry = telemetry;
        telemetry.addLine("in PixelDetectionByShape ctor ");
        //telemetry.update();
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
                pixelDetectionPipeline = new PixelDetectionPipeline(telemetry);
                camera.setPipeline(pixelDetectionPipeline);
            }

            @Override
            public void onError(int errorCode) {
            }
        });

        while (pixelDetectionPipeline == null) {
        }
    }

    public double getCenterX() {
        return pixelDetectionPipeline.getCenterX();
    }

    public double getCenterY() {
        return pixelDetectionPipeline.getCenterY();
    }

    public boolean getFrameFlag() {
        return pixelDetectionPipeline.getFrameFlag();
    }


    static class PixelDetectionPipeline extends OpenCvPipeline {
        Telemetry telemetry;
        Mat grayImage = new Mat();
        Mat edgesImage = new Mat();
        Mat blurredImage = new Mat();

        Mat binaryImage = new Mat();
        Mat hierarchy = new Mat();
        private boolean processedFrame = false;
        public PixelDetectionPipeline(Telemetry telemetry) {
            this.telemetry = telemetry;
            telemetry.addLine("in PixelDetectionPipeline ctor");
            //telemetry.update();
        }


        private double centerX = 0.0;
        private double centerY = 0.0;

        private int printFrame = 0;
        private Mat oldFrame = null;

        public Mat processFrame_1(Mat frame) {

            if(oldFrame == null)
            {
                oldFrame = frame;
            }

            if(printFrame % 30 == 0) {
                //print
                telemetry.addData("printFrame ", printFrame);
                telemetry.update();
                oldFrame = frame;
                printFrame++;
            }
            else {
                printFrame++;
            }


            return oldFrame;
        }


        @Override
        public Mat processFrame(Mat frame) {
            long startTime = System.currentTimeMillis();

            processedFrame = false;
            //telemetry.addLine("in PixelDetectionPipeline processFrame start ");


            //telemetry.update();
            //telemetry.log();
//            Mat gray = new Mat();

            //Converting image to Grayscale
            Imgproc.cvtColor(frame, grayImage, Imgproc.COLOR_BGR2GRAY);
            //telemetry.addLine("in PixelDetectionPipeline processFrame after GrayScale ");
            long colorToGrayEndTime = System.currentTimeMillis();


            //Blurring Graysacled image
            Imgproc.medianBlur(grayImage, blurredImage, 5);
            //telemetry.addLine("in PixelDetectionPipeline processFrame after Blur ");
            long blurTime = System.currentTimeMillis();


            //Applying Gaussian threshold
            Imgproc.adaptiveThreshold(blurredImage, binaryImage, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 11, 2);
            //telemetry.addLine("in PixelDetectionPipeline processFrame after Gaussian ");
            long adapThreshTime = System.currentTimeMillis();

            //Mat edges = new Mat();

            //Applying Canny edge detection
            Imgproc.Canny(binaryImage, edgesImage, 175, 255);
            //telemetry.addLine("in PixelDetectionPipeline processFrame after Edge detection ");
            //telemetry.addData("in PixelDetectionPipeline processFrame Edge height ", edges.size().height);
            long edgeDetectTime = System.currentTimeMillis();



            //Finding the contours
            List<MatOfPoint> contours = new ArrayList<>();
            Imgproc.findContours(edgesImage, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
            //telemetry.addData("in PixelDetectionPipeline processFrame after find Contour size is  ", contours.size());
            long findContourTime = System.currentTimeMillis();

            List<MatOfPoint> hexContours = new ArrayList<>();


            //Looping through each contour to detect Hexagon shape
            int i=0;
            for(MatOfPoint aContour:contours) {
                //telemetry.addData("in for loop i is : ", i++);

                //draw
                Scalar scalar = new Scalar(0,128,150,255);
                //Imgproc.drawContours(gray, contours, -1, scalar, 5);


                //get perimeter of contour
                MatOfPoint2f newC = new MatOfPoint2f( aContour.toArray() );
                double perimeter = Imgproc.arcLength(newC, true);

                MatOfPoint2f approxShape = new MatOfPoint2f();
                Imgproc.approxPolyDP(newC, approxShape, 0.05, true);

                //telemetry.addData("approx size is ", approx.size().height);
                if(approxShape.size().height == 6) {
                    //found hexagon
                    //telemetry.addLine("FOUND HEXAGON");
                    hexContours.add(aContour);

                }
            }

            long detectHexTime = System.currentTimeMillis();

            //telemetry.addData("after for loop hexContours size is ", hexContours.size());
            //telemetry.addLine("now finding max index");

            //find max area contour from hexContours
            double maxVal = 0;
            int maxValIdx = 0;
            for (int contourIdx = 0; contourIdx < hexContours.size(); contourIdx++)
            {
                double contourArea = Imgproc.contourArea(hexContours.get(contourIdx));
                if (maxVal < contourArea)
                {
                    maxVal = contourArea;
                    maxValIdx = contourIdx;
                }
            }
            //telemetry.addData("maxValIdx is ", maxValIdx);

            //draw contour with index maxValIdx
            //telemetry.addLine("trying to draw contour with maxValIdx");
            if(hexContours.size() > 0) {
                //telemetry.addLine("drawing");
                //Imgproc.drawContours(frame, hexContours, maxValIdx, new Scalar(0,255,0), 5);
            } else {
                //telemetry.addLine("NOT drawing");
            }
            //telemetry.update();


            //Finding the center of the max hexagon
            /** TEMP COMMENT
             Moments M = Imgproc.moments(hexContours.get(maxValIdx));
             if (M.get_m00() != 0) {
             centerX = M.get_m10() / M.get_m00();
             centerY = M.get_m01() / M.get_m00();
             } else {
             // Handle the case where the contour has no area
             centerX = 0.0;
             centerY = 0.0;
             }
             */
            long computeHexCGTime = System.currentTimeMillis();

            // Return the processed frame (not really needed for your purpose)
            processedFrame = true;

            long totalElapsedTime = System.currentTimeMillis() - startTime;
            long elapsedColorToGrayTime = colorToGrayEndTime - startTime;
            long blurElapsedTime =  blurTime - colorToGrayEndTime;
            long  adpThreshElapsedTime = adapThreshTime - blurTime;
            long edgeDetectElapsedTime = edgeDetectTime - adapThreshTime;
            long findContourElapsedTime = findContourTime - edgeDetectTime;
            long detectHexElapsedTime = detectHexTime - findContourTime;
            long computeHexCGElapsedTime = computeHexCGTime - detectHexTime;

            telemetry.addData("Total Elapsed Time: ", totalElapsedTime);
            telemetry.addData("elapsedColorToGrayTime Elapsed Time: ", elapsedColorToGrayTime);
            telemetry.addData("blurElapsedTime Elapsed Time: ", blurElapsedTime);
            telemetry.addData("adpThreshElapsedTime Elapsed Time: ", adpThreshElapsedTime);
            telemetry.addData("edgeDetectElapsedTime Elapsed Time: ", edgeDetectElapsedTime);
            telemetry.addData("findContourElapsedTime Elapsed Time: ", findContourElapsedTime);
            telemetry.addData("detectHexElapsedTime Elapsed Time: ", detectHexElapsedTime);
            telemetry.addData("computeHexCGElapsedTime Elapsed Time: ", computeHexCGElapsedTime);



            telemetry.update();

            return frame;
        }

        public double getCenterX() {
            return centerX;
        }

        public double getCenterY() {
            return centerY;
        }

        public boolean getFrameFlag() {
            return processedFrame;
        }
    }
}
