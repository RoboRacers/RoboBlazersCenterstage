package org.firstinspires.ftc.teamcode.modules.RobotParts;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class Arm {

    DcMotorEx armMotor;
    Servo linkMotor;
    Servo clawMotor;

    public Arm(HardwareMap hardwareMap) {
        armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");
    }


    public void moveArmForward(double power){
        if (power > 0){
            return;
        } else if (power <= 0) {
            armMotor.setPower(power);
        }
    }

    public void moveArmBackward(double power){
        if (power < 0){
            return;
        } else if (power >= 0) {
            armMotor.setPower(power);
        }
    }

    public void moveLinkPickUp(){
        linkMotor.setPosition(0);
    }
    public void moveLinkDrop(){
        linkMotor.setPosition(1);
    }
    public void clawOpen(){
        clawMotor.setPosition(0.75);
    }
    public void clawClose(){
        clawMotor.setPosition(0.25);
    }

}
