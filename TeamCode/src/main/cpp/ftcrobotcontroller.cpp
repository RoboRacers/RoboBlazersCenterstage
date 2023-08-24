// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("ftcrobotcontroller");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("ftcrobotcontroller")
//      }
//    }

#include <jni.h>        // JNI header provided by JDK
#include <stdio.h>      // C Standard IO Header


extern "C"
JNIEXPORT void JNICALL
Java_org_firstinspires_ftc_teamcode_cppTest_sayHello(JNIEnv *env, jobject thiz) {
    printf("Hello JNI!");
}