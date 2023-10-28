package org.firstinspires.ftc.teamcode.modules.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.modules.statemachines.ClawSM;
import org.firstinspires.ftc.teamcode.modules.statemachines.DepositSM;

public class Claw extends Subsystem {

    public ClawSM statemachine;

    public Servo servo;

    public Claw(HardwareMap hardwareMap) {

        servo = hardwareMap.get(Servo.class, "servo");

        statemachine = new ClawSM(this);
    }

    public void setLiftPosition(Servo.Direction pos) {
        servo.setDirection(pos);
    }

    @Override
    public void update() {
        double servo_position = servo.getPosition();
        Servo.Direction servo_direction = servo.getDirection();

        statemachine.update();
    }
}
