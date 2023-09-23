package org.firstinspires.ftc.teamcode.teleop;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.modules.drive.SampleMecanumDrive;

@TeleOp(name = "PID_Ball_Control", group = "16481-Centerstage")
public class PID_Ball_Control extends LinearOpMode{
    //define sensors and servo here
    //also variables
    private DistanceSensor distance_sensor_range;
    private Servo balance_servo;

    double kp = 0;
    double ki = 0;
    double kd = 0;
    double error; //you can declare the vaoidable here but assigning it has to happen in a classs/functioon

    @Override
    public void runOpMode() throws InterruptedException{
        //runs once after init
        error = 8;
        while (opModeInInit()) { //runs continusly after the once part
            //stuff goes here hehehehehehe
        }
        //runs when start is pressed
        //runs once between loops

        while (!isStopRequested()) { //runs contiuosly after start is pressed until stop
            //also stuff goes here hehehehehehe
        }



        
    }


}
