package org.firstinspires.ftc.teamcode.modules.opencv;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class PixelDetectionByAnglesPipeline {
    PixelDetectionPipeline pixelDetectionPipeline;
    Telemetry telemetry;

    public PixelDetectionByAnglesPipeline(OpenCvCamera camera, Telemetry telemetry) {
        this.telemetry = telemetry;
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
                pixelDetectionPipeline = new PixelDetectionPipeline();
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

    static class PixelDetectionPipeline extends OpenCvPipeline {
        private double centerX = 0.0;
        private double centerY = 0.0;

        private static ArrayList<Double> xCords = new ArrayList<>();
        private static ArrayList<Double> yCords = new ArrayList<>();

        @Override
        public Mat processFrame(Mat frame) {
            Mat grayImage = new Mat();
            Imgproc.cvtColor(frame, grayImage, Imgproc.COLOR_RGB2GRAY);

            // Apply Gaussian blur to the grayscale image
            Mat blurredImage = new Mat();
            Imgproc.medianBlur(grayImage, blurredImage, 19);

            // Apply a threshold to create a binary image
            Mat thresholdImage = new Mat();
            Imgproc.threshold(blurredImage, thresholdImage, 150, 300, Imgproc.THRESH_BINARY);

            Imgproc.medianBlur(thresholdImage, thresholdImage, 19);

            // Find contours in the binary + blurred image
            Imgproc.Canny(thresholdImage, thresholdImage, 175, 255);

            Mat hierarchy = new Mat();
            List<MatOfPoint> contours = new ArrayList<>();
            Imgproc.findContours(thresholdImage, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
            List<MatOfPoint> AllContours = new ArrayList<>();
            Mat imageWithContours = new Mat();
            frame.copyTo(imageWithContours);

            for (MatOfPoint contour : contours) {
                // Draw the contours on the image
                Imgproc.drawContours(imageWithContours, contours, contours.indexOf(contour), new Scalar(0, 255, 0), 2);

                AllContours.add(contour);
            }

            double maxVal = 0;
            int maxValIdx = -1;

            // Find the largest contour
            for (int contourIdx = 0; contourIdx < AllContours.size(); contourIdx++) {
                double contourArea = Imgproc.contourArea(AllContours.get(contourIdx));
                if (contourArea > maxVal) {
                    maxVal = contourArea;
                    maxValIdx = contourIdx;
                }
            }

            if (maxValIdx >= 0) {
                // Draw the largest contour on the original image
                Mat resultImage = new Mat();
                frame.copyTo(resultImage);
                Imgproc.drawContours(resultImage, contours, maxValIdx, new Scalar(0, 0, 255), 5);

                MatOfPoint2f approxCurve = new MatOfPoint2f();
                MatOfPoint2f contour2f = new MatOfPoint2f(AllContours.get(maxValIdx).toArray());
                double epsilon = 0.02 * Imgproc.arcLength(contour2f, true);
                Imgproc.approxPolyDP(contour2f, approxCurve, epsilon, true);

                // Determine the number of vertices in the polygon
                int numVertices = approxCurve.toList().size();

                List<Point> points = approxCurve.toList();

                Mat noiseRemovedMaxContourWithCoordinates = frame.clone();

                for (Point point : points) {
                    double x = point.x;
                    xCords.add(x);
                    double y = point.y;
                    yCords.add(y);

                    Imgproc.circle(noiseRemovedMaxContourWithCoordinates, point, 5, new Scalar(0, 255, 255), 5);
                }

                for (int i = 0; i < xCords.size(); i++) {
                    if (i == 0) {
                        double angle = getAngle(xCords.size() - 1, i, i + 1);
                        if (angle > 80 && angle < 100) {
                            points.remove(i);
                            i--; // Decrement i since we removed an element
                        }
                    } else if (i == xCords.size() - 1) {
                        double angle = getAngle(i - 1, i, 0);
                        if (angle > 80 && angle < 100) {
                            points.remove(i);
                            i--; // Decrement i since we removed an element
                        }
                    } else {
                        double angle = getAngle(i - 1, i, i + 1);
                        if (angle > 80 && angle < 100) {
                            points.remove(i);
                            i--; // Decrement i since we removed an element
                        }
                    }
                }

                Point hexCenter = findCenter(points);

                centerX = hexCenter.x;
                centerY = hexCenter.y;

                Imgproc.circle(frame, hexCenter, 2, new Scalar(0, 255, 0), 10);
            }

            return frame;
        }

        public Point findCenter(List<Point> points) {
            if (points.isEmpty()) {
                return null; // Return null if the list is empty
            }

            double m00 = 0;
            double m10 = 0;
            double m01 = 0;

            for (Point point : points) {
                m00 += 1; // Increment the area
                m10 += point.x; // Increment the sum of x coordinates
                m01 += point.y; // Increment the sum of y coordinates
            }

            double centerX = m10 / m00;
            double centerY = m01 / m00;

            return new Point(centerX, centerY);
        }

        public double getAngle(int cord1, int cord2, int cord3) {
            if (xCords.size() < 3) {
                return 0; // Return 0 if there are not enough points
            }

            double x1 = xCords.get(cord1);
            double y1 = yCords.get(cord1);
            double x3 = xCords.get(cord3);
            double y3 = yCords.get(cord3);
            double x2 = xCords.get(cord2);
            double y2 = yCords.get(cord2);

            double M1 = -((y2 - y1) / (x2 - x1));
            double M2 = -((y3 - y2) / (x3 - x2));

            double angle = Math.abs(Math.toDegrees(Math.atan(Math.abs((M2 - M1) / (1 + M1 * M2)))));
            return angle;
        }

        public double getCenterX() {
            return centerX;
        }

        public double getCenterY() {
            return centerY;
        }
    }
}
