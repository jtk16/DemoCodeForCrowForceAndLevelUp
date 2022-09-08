package org.firstinspires.ftc.teamcode.OpModes.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;


/**
 * IT IS ASSUMED THAT YOU HAVE READ AND UNDERSTOOD THE EXAMPLEAUTO
 * AUTO BEFORE PROCEEDING.
 */

public class ComputerVisionAuto extends LinearOpMode {
    String CAMERA_CONFIG_NAME = "Cam";
    OpenCvCamera camera;



    @Override
    public void runOpMode() throws InterruptedException {
        // I don't completely understand how this line works but I know that it does.
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        // defines the camera by passing through the camera on hardware map into a wrapper class from EasyOpenCV
        // that will make reading data easier.
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, CAMERA_CONFIG_NAME), cameraMonitorViewId);

        //set the pipeline that the camera will run
        camera.setPipeline(new ComputerVisionPipeline());

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            // This method of course runs when the camera is opened.
            @Override
            public void onOpened() {

                // This is what actually starts running the pipeline, NOT THE onOpened() METHOD ITSELF.
                camera.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }
            public void onError(int errorCode) {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });
        /**
         * IF YOU WISH TO PREVIEW THE COMPUTER VISION CODE DO NOT CLICK START
         * instead, initialize the auto as normal and then wait a second
         * click the three dots in the top right and click camera stream
         * once you get a picture, click it to refresh it
         */


        waitForStart();

    }

    // This is a method that I use to make transferring info from the pipeline to the auto easier.
    public static class CameraData {
        public static String infoFromThePipeline = "";
    }
}
