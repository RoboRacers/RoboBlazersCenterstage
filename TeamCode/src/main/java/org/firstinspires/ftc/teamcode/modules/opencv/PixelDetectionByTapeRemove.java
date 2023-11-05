package org.firstinspires.ftc.teamcode.modules.opencv;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class PixelDetectionByTapeRemove {
    PixelDetectionPipeline pixelDetectionPipeline;
    Telemetry telemetry;


    public PixelDetectionByTapeRemove(OpenCvCamera camera, Telemetry telemetry) {
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
        Mat tmpImage = new Mat();
        private boolean processedFrame = false;
        public PixelDetectionPipeline(Telemetry telemetry) {
            this.telemetry = telemetry;
            telemetry.addLine("in PropDetectionPipeline ctor");
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


        private Mat preProcessFrame(Mat frame)
        {
            //Converting image to Grayscale
            Imgproc.cvtColor(frame, grayImage, Imgproc.COLOR_BGR2GRAY);

            //Blurring Graysacled image
            Imgproc.medianBlur(grayImage,blurredImage,19);

            // Apply a threshold to create a binary image
            Mat thresholdImage = new Mat();
            Imgproc.threshold(blurredImage, thresholdImage, 150, 300, Imgproc.THRESH_BINARY);

            // Find contours in the binary + blurred image
            Imgproc.Canny(thresholdImage, edgesImage, 175, 255);

            return edgesImage;
        }

        private List<MatOfPoint> getContours(Mat image)
        {
            Mat hierarchy = new Mat();
            List<MatOfPoint> contours = new ArrayList<>();
            Imgproc.findContours(image, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

            List<MatOfPoint> AllContours = new ArrayList<>();

            telemetry.addLine("before for loop");
            telemetry.addData("Contour Size: " , contours.size());
            telemetry.update();
            for (MatOfPoint contour : contours) {
                // Draw the contours on the image

                telemetry.addLine("before draw");

                Imgproc.drawContours(image, contours, contours.indexOf(contour), new Scalar(0, 255, 0), 2);
                telemetry.addLine("after draw");
                // Approximate the contour to a polygon
                MatOfPoint2f approxCurve = new MatOfPoint2f();
                MatOfPoint2f contour2f = new MatOfPoint2f(contour.toArray());
                double epsilon = 0.02 * Imgproc.arcLength(contour2f, true);
                Imgproc.approxPolyDP(contour2f, approxCurve, epsilon, true);

                // Determine the number of vertices in the polygon
                int numVertices = approxCurve.toList().size();

                telemetry.addData("numVertices = " , numVertices);
                telemetry.update();

                AllContours.add(contour);
            }

            return AllContours;
        }

        private int getHexagonContourIndex(Mat image, List<MatOfPoint> AllContours)
        {
            double maxVal = 0;
            int maxValIdx = 0;
            for (int contourIdx = 0; contourIdx < AllContours.size(); contourIdx++)
            {
                double contourArea = Imgproc.contourArea(AllContours.get(contourIdx));
                telemetry.addData("contourIdx = " , contourIdx);
                telemetry.addData("contourArea = " , contourArea);
                if (contourArea > maxVal)
                {
                    maxVal = contourArea;
                    maxValIdx = contourIdx;
                }
            }


            // Save the image with drawn hex contours
            //Mat maxHexContour = frame.clone();
            //Imgproc.drawContours(frame, AllContours, maxValIdx, new Scalar(255, 0, 255), 5);

            return maxValIdx;
        }

        @Override
        public Mat processFrame(Mat frame) {
            long startTime = System.currentTimeMillis();
            telemetry.addLine("IN PROCESS FRAME");

            long colorToGrayEndTime = System.currentTimeMillis();

            edgesImage = preProcessFrame(frame);

            List<MatOfPoint> AllContours = getContours(edgesImage);

            int maxValIdx = getHexagonContourIndex(edgesImage, AllContours);

/*


            // Approximate the contour to a polygon
            MatOfPoint2f approxCurve = new MatOfPoint2f();
            MatOfPoint2f contour2f = new MatOfPoint2f(AllContours.get(maxValIdx).toArray());
            double epsilon = 0.02 * Imgproc.arcLength(contour2f, true);
            Imgproc.approxPolyDP(contour2f, approxCurve, epsilon, true);

            // Determine the number of vertices in the polygon
            int numVertices = approxCurve.toList().size();

            List<Point> points = approxCurve.toList();

            Point pointWithMaxY = null;
            double maxY = 0;

            for (Point point : points) {
                double x = point.x;
                double y = point.y;
                telemetry.addLine("X: " + x + ", Y: " + y);
                Imgproc.circle(frame, point, 5, new Scalar(0, 255, 255), 5);
                if (y > maxY) {
                    maxY = y;
                    pointWithMaxY = point;
                }
            }

            List<Point> filteredPoints = new ArrayList<>(points); // Create a new list
            filteredPoints.remove(pointWithMaxY);

            pointWithMaxY = null;
            maxY = 0;

            for (Point point : filteredPoints) {
                double y = point.y;
                if (y > maxY) {
                    maxY = y;
                    pointWithMaxY = point;
                }
            }

            filteredPoints.remove(pointWithMaxY);
            telemetry.addData("filteredPoints = " , filteredPoints);

            for (Point point : filteredPoints) {
                Imgproc.circle(frame, point, 5, new Scalar(255, 0, 0), 3);
            }


            double centerX = 0;
            double centerY = 0;

            for (Point point : filteredPoints) {
                centerX += point.x;
                centerY += point.y;
            }

            centerX /= points.size();
            centerY /= points.size();

            telemetry.addLine("Center X: " +  centerX + ", Center Y: " + centerY);


//        double centerX;
//        double centerY;
//
//        Moments M = Imgproc.moments(AllContours.get(maxValIdx));
//        if (M.get_m00() != 0) {
//            centerX = M.get_m10() / M.get_m00();
//            centerY = M.get_m01() / M.get_m00();
//        } else {
//            // Handle the case where the contour has no area
//            centerX = 0.0;
//            centerY = 0.0;
//        }

            Point center = new Point(centerX,centerY);


            int width = frame.cols();
            int height = frame.rows();

            telemetry.addData("width = ", width);
            telemetry.addData("height = " , height);

            int imageWidthCenter = width/2;
            int imageHeightCenter = height/2;

            Point centerCordinate = new Point(imageWidthCenter,imageHeightCenter);


            Imgproc.circle(frame, center, 5, new Scalar(0,0,255), 10);
            Imgproc.circle(frame, centerCordinate, 5, new Scalar(255,0,0),10);



            Imgproc.line(frame, centerCordinate, center, new Scalar(0,255,255),10);

            telemetry.update();*/
            return edgesImage;
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
