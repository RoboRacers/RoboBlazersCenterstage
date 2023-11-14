package org.firstinspires.ftc.teamcode.modules.RobotParts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class Arm {

    DcMotorEx armMotor;
    Servo linkMotor;
    Servo clawMotor;
    Servo droneLauncher;

    Telemetry mytelemetry;


    public Arm(HardwareMap hardwareMap, Telemetry telemetry) {
        armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");
        linkMotor = hardwareMap.get(Servo.class, "linkMotor");
        clawMotor = hardwareMap.get(Servo.class, "clawMotor");
        armMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        droneLauncher = hardwareMap.get(Servo.class, "droneMotor");
        mytelemetry = telemetry;
    }


    public void moveArmForward(double power){
        if (power > 0){
            return;
        } else if (power <= 0) {
            armMotor.setPower(power);
        }
        mytelemetry.addData("Current position of ARM is ",armMotor.getCurrentPosition() );
        mytelemetry.update();
    }

    public void moveArmBackward(double power){
        if (power < 0){
            return;
        } else if (power >= 0) {
            armMotor.setPower(power);
        }
        mytelemetry.addData("Current position of ARM is ",armMotor.getCurrentPosition() );
        mytelemetry.update();
    }

    public void moveLinkPickUp(){

        linkMotor.setPosition(1);
    }
    public void moveLinkDrop(){

        linkMotor.setPosition(0.575);
    }
    public void clawOpen(){

        clawMotor.setPosition(0.4);
    }
    public void clawClose(){
        clawMotor.setPosition(1);
    }

    public void launch(){
        droneLauncher.setPosition(1);
    }

}
