package org.firstinspires.ftc.teamcode;

public class MainActivity {
    private native void sayHello();

    static {
        System.loadLibrary("ftcrobotcontroller");
    }

    public static void main(String[] args) {

        new MainActivity().sayHello();
    }
}
