package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@TeleOp(name = "TestCamera")
public class TestCamera extends LinearOpMode {
   public static final String VUFORIA_KEY =
           "Afctxlz/////AAABmSWf4jOsTUHcsOYa/JfaZlRo+3yiPN8cCUH4BDLpIZ8FAt0tEVLJ/mxWUyd7f0gqd+a7JRTMYP9+A9s1nojOs9B1ZGOFsvr84RZnbVN8cGP7RFKNP4Mg0Pr/6vIUmHGFx/jrOrXz/YJXwVXvPpqr1uDm8xpBZOE4j+CtQcKW2Y2zjVWHWRTkmb6ve/R91k3jfjaH4PErbZMcvD7Xy5IesqSet3/pjeUXWSnlHmPwH7fgUcHSkAf0Fj2nLvZ7zmpT8vh9rSKri9XD3A64WBNRO+6+SGH/C/eS3mWLmdi5ZMbSK66WuvNhAPT0SHCzzqAlAf2P6asrrrAuw+aQ0B2HV0mPtGdNPe62djhu5Afa/rL+";
   //private OpenCvCamera webcam;
   private ColorDetector detector = new ColorDetector();
   private String position;

   @Override
   public void runOpMode() {
      //int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
      //webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 2021"), cameraMonitorViewId);
      int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
      WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 2021");
      OpenCvCamera camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
      //OpenCvCamera camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName);
      Mat ringInput = new Mat();

      /** Wait for the game to begin */
      telemetry.addData(">", "Press Play to start op mode");
      telemetry.update();

      //webcam.openCameraDevice();
      //webcam.setPipeline(detector);
      //webcam.startStreaming(320,240, OpenCvCameraRotation.UPRIGHT);

      camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
      {
         @Override
         public void onOpened()
         {
            // Usually this is where you'll want to start streaming from the camera (see section 4)
            //camera.openCameraDevice();
            camera.setPipeline(detector);
            camera.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
         }
         @Override
         public void onError(int errorCode)
         {
            /*
             * This will be called if the camera could not be opened
             */
         }
      });


      waitForStart();

      if (opModeIsActive()) {
         while (opModeIsActive()) {
            //detector.processFrame(ringInput);
            position = detector.position;
            telemetry.addData("Position = ", position);
            telemetry.addData("centerTotal = ", detector.centerTotal);
            telemetry.update();
         }
      }
   }
}
