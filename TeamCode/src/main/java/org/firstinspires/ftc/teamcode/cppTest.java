package org.firstinspires.ftc.teamcode;

public class cppTest {

    private native void sayHello();

    static {
        System.loadLibrary("ftcrobotcontroller");
    }

    public static void main(String[] args) {
        new cppTest().sayHello();
    }
}
