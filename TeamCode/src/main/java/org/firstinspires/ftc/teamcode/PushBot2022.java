package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

class PushBot2022 {
   /* Public OpMode members. */

   public Servo grabber;
   public DcMotor frontLeftMec, frontRightMec, backRightMec, backLeftMec;
   public DcMotor linearLeft, linearRight;
   public TouchSensor Limit;
   double spinDiameter = 1;
   double diameter = 3.89764; //3.77953;
   double spindleCircumference = 1.5 * 3.14159;
   double circumference = diameter * 3.14;
   double andyMark40Tics = 1120;
   double andyMark20Tics = 537.6;
   double yellowJacketTics = 463; //537.7;
   public static final String VUFORIA_KEY =
           "Afctxlz/////AAABmSWf4jOsTUHcsOYa/JfaZlRo+3yiPN8cCUH4BDLpIZ8FAt0tEVLJ/mxWUyd7f0gqd+a7JRTMYP9+A9s1nojOs9B1ZGOFsvr84RZnbVN8cGP7RFKNP4Mg0Pr/6vIUmHGFx/jrOrXz/YJXwVXvPpqr1uDm8xpBZOE4j+CtQcKW2Y2zjVWHWRTkmb6ve/R91k3jfjaH4PErbZMcvD7Xy5IesqSet3/pjeUXWSnlHmPwH7fgUcHSkAf0Fj2nLvZ7zmpT8vh9rSKri9XD3A64WBNRO+6+SGH/C/eS3mWLmdi5ZMbSK66WuvNhAPT0SHCzzqAlAf2P6asrrrAuw+aQ0B2HV0mPtGdNPe62djhu5Afa/rL+";
   double integral = 0;
   double lastError = 0;
   //double Distance;
   double grabberOpenPos = 0;
   double grabberClosePos = 0.36;
   //double totalRotations = Distance / circumference;
   //double rotationDistanceofWheel = (andyMark40Tics * totalRotations);
   double robotSpeed = 0.5;

   public static PIDCoefficients pidC = new PIDCoefficients(5, 1, 0);
   public PIDCoefficients pidGain = new PIDCoefficients(0, 0, 0);

   ElapsedTime PIDtimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

   public OpenCvCamera webcam;
   public ColorDetector detector = new ColorDetector();
   public double leftTotal, centerTotal;
   public String place;


   /* local OpMode members. */

   HardwareMap hwMap           =  null;
   private ElapsedTime period  = new ElapsedTime();

   /* Initialize standard Hardware interfaces */
   public void init(HardwareMap ahwMap, boolean isAuto) {
      // Save reference to Hardware map
      hwMap = ahwMap;

      int cameraMonitorViewId = ahwMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", ahwMap.appContext.getPackageName());
      webcam = OpenCvCameraFactory.getInstance().createWebcam(ahwMap.get(WebcamName.class, "Webcam 2021"), cameraMonitorViewId);
      webcam.openCameraDevice();
      if (isAuto) {
         webcam.setPipeline(detector);
         webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
      }


      //DcMotor frontLeftMec, frontRightMec, backRightMec, backLeftMec;
      frontLeftMec = hwMap.get(DcMotor.class, "frontLeftMec");
      frontRightMec = hwMap.get(DcMotor.class, "frontRightMec");
      backLeftMec = hwMap.get(DcMotor.class, "backLeftMec");
      backRightMec = hwMap.get(DcMotor.class, "backRightMec");

      frontLeftMec.setDirection(DcMotor.Direction.FORWARD);
      frontRightMec.setDirection(DcMotor.Direction.REVERSE);
      backLeftMec.setDirection(DcMotor.Direction.FORWARD);
      backRightMec.setDirection(DcMotor.Direction.REVERSE);

      frontRightMec.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
      frontLeftMec.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
      backRightMec.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
      backLeftMec.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

      linearLeft = hwMap.get(DcMotor.class, "linearLeft");
      linearRight = hwMap.get(DcMotor.class, "linearRight");
      linearLeft.setDirection(DcMotor.Direction.FORWARD);
      linearRight.setDirection(DcMotor.Direction.FORWARD);
      linearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
      linearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


      /*frontLeftMec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      frontRightMec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      backRightMec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      backLeftMec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

      frontLeftMec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      frontRightMec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      backRightMec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      backLeftMec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);*/

      //set power to zero
      frontLeftMec.setPower(0);
      frontRightMec.setPower(0);
      backLeftMec.setPower(0);
      backRightMec.setPower(0);
      linearLeft.setPower(0);
      linearRight.setPower(0);

      grabber=hwMap.get(Servo.class,"grabber");
      grabber.setPosition(grabberOpenPos);

      Limit=hwMap.get(TouchSensor.class, "Limit");
   }

   public void moveForward(double power) {
      frontLeftMec.setPower(-power);
      frontRightMec.setPower(-power);
      backLeftMec.setPower(-power);
      backRightMec.setPower(-power);
   }

   public void turn(double power) {
      frontRightMec.setPower(power);
      frontLeftMec.setPower(-power);
      backRightMec.setPower(power);
      backLeftMec.setPower(-power);
   }

   public void moveForwardInches(double Distance, double power){
      frontLeftMec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      frontRightMec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      backLeftMec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      backRightMec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

      frontLeftMec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      frontRightMec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      backLeftMec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      backRightMec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

      double totalRotations = Distance / circumference;
      double rotationDistanceofWheel = (yellowJacketTics * totalRotations);

      boolean runRobot = true;
      while (runRobot) {
         if (Math.abs(frontRightMec.getCurrentPosition()) > Math.abs(rotationDistanceofWheel)
                 || Math.abs(frontLeftMec.getCurrentPosition()) > Math.abs(rotationDistanceofWheel)
                 || Math.abs(backLeftMec.getCurrentPosition()) > Math.abs(rotationDistanceofWheel)
                 || Math.abs(backRightMec.getCurrentPosition()) > Math.abs(rotationDistanceofWheel)) {
            frontLeftMec.setPower(0);
            backLeftMec.setPower(0);
            frontRightMec.setPower(0);
            backRightMec.setPower(0);
            runRobot = false;
         } else {
            if (Distance > 0) {
               frontLeftMec.setPower(-power);
               frontRightMec.setPower(-power);
               backLeftMec.setPower(-power);
               backRightMec.setPower(-power);
            } else if (Distance < 0) {
               frontLeftMec.setPower(power);
               frontRightMec.setPower(power);
               backRightMec.setPower(power);
               backLeftMec.setPower(power);
            }
         }
      }
   }

   public void flank(double degrees, double power) {
      frontRightMec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      frontLeftMec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      backLeftMec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      backRightMec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      frontRightMec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      frontLeftMec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      backLeftMec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      backRightMec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      double ticsToDegrees = ((7*3.14)/360)*(yellowJacketTics*(diameter*3.14));
      double ticMove = degrees/ticsToDegrees;
      while (ticMove>frontRightMec.getCurrentPosition()||ticMove>backRightMec.getCurrentPosition()
              ||ticMove>Math.abs(backLeftMec.getCurrentPosition())||ticMove>Math.abs(frontLeftMec.getCurrentPosition())) {
         frontRightMec.setPower(power);
         frontLeftMec.setPower(-power);
         backRightMec.setPower(power);
         backLeftMec.setPower(-power);
      }
      frontLeftMec.setPower(0);
      frontRightMec.setPower(0);
      backLeftMec.setPower(0);
      backRightMec.setPower(0);
   }
   public void columnRight(double degrees, double power) {
      frontRightMec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      frontLeftMec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      backLeftMec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      backRightMec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      frontRightMec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      frontLeftMec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      backLeftMec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      backRightMec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      double ticsToDegrees = ((14*3.14)/360)*(yellowJacketTics/(diameter*3.14));
      double ticMove = degrees/ticsToDegrees;
      while (ticMove>frontRightMec.getCurrentPosition()||ticMove>backRightMec.getCurrentPosition()
              ||ticMove>Math.abs(backLeftMec.getCurrentPosition())||ticMove>Math.abs(frontLeftMec.getCurrentPosition())) {
         frontLeftMec.setPower(-power);
         backLeftMec.setPower(-power);
      }
      frontLeftMec.setPower(0);
      backLeftMec.setPower(0);
   }
   public void columnLeft(double degrees, double power) {
      frontRightMec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      frontLeftMec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      backLeftMec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      backRightMec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      frontRightMec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      frontLeftMec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      backLeftMec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      backRightMec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      double ticsToDegrees = ((14*3.14)/360)*(yellowJacketTics/(diameter*3.14));
      double ticMove = degrees/ticsToDegrees;
      while (ticMove>frontRightMec.getCurrentPosition()||ticMove>backRightMec.getCurrentPosition()
              ||ticMove>Math.abs(backLeftMec.getCurrentPosition())||ticMove>Math.abs(frontLeftMec.getCurrentPosition())) {
         frontRightMec.setPower(power);
         backRightMec.setPower(power);
      }
      frontRightMec.setPower(0);
      backRightMec.setPower(0);
   }

   public void moveSide(double power) {
      frontLeftMec.setPower(power);
      frontRightMec.setPower(-power);
      backLeftMec.setPower(-power);
      backRightMec.setPower(power);
   }

   public void moveSideInches(double Distance, double power){
      double totalRotations = Distance / circumference;
      double rotationDistanceofWheel = (yellowJacketTics * totalRotations);

      frontLeftMec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      frontRightMec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      backRightMec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      backLeftMec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

      frontLeftMec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      frontRightMec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      backLeftMec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      backRightMec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

      boolean runRobot = true;
      while (runRobot) {
         if (Math.abs(frontRightMec.getCurrentPosition()) > Math.abs(rotationDistanceofWheel)
                 || Math.abs(frontLeftMec.getCurrentPosition()) > Math.abs(rotationDistanceofWheel)
                 || Math.abs(backLeftMec.getCurrentPosition()) > Math.abs(rotationDistanceofWheel)
                 || Math.abs(backRightMec.getCurrentPosition()) > Math.abs(rotationDistanceofWheel)) {
            frontLeftMec.setPower(0);
            backLeftMec.setPower(0);
            frontRightMec.setPower(0);
            backRightMec.setPower(0);
            runRobot = false;
         } else {
            if (Distance > 0) {
               frontLeftMec.setPower(-power);
               frontRightMec.setPower(power);
               backLeftMec.setPower(power);
               backRightMec.setPower(-power);
            } else if (Distance < 0) {
               frontLeftMec.setPower(power);
               frontRightMec.setPower(-power);
               backLeftMec.setPower(-power);
               backRightMec.setPower(power);
            }
         }
      }
   }

   public void LinearSlide(double power){
      linearLeft.setPower(-power);
      linearRight.setPower(-power);
   }
   public void LinearSlideEnc(double power, int height){
      double totalRotations = height / spindleCircumference;
      double rotationDistanceofWheel = (yellowJacketTics * totalRotations);

      linearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      linearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

      boolean runRobot = true;
      while (runRobot) {
         if (linearRight.getCurrentPosition() <= rotationDistanceofWheel || linearLeft.getCurrentPosition() <= rotationDistanceofWheel) {
            if (Math.abs(linearRight.getCurrentPosition()) > Math.abs(rotationDistanceofWheel)
                    || Math.abs(linearLeft.getCurrentPosition()) > Math.abs(rotationDistanceofWheel)) {
               linearRight.setPower(0);
               linearLeft.setPower(0);
               runRobot = false;
            } else {
               if (height > 0) {
                  linearLeft.setPower(power);
                  linearRight.setPower(-power);
               } else if (height < 0) {
                  linearRight.setPower(power);
                  linearLeft.setPower(-power);
               }
            }
         } else {
            if (Math.abs(linearRight.getCurrentPosition()) < Math.abs(rotationDistanceofWheel)
                    || Math.abs(linearLeft.getCurrentPosition()) < Math.abs(rotationDistanceofWheel)) {
               linearRight.setPower(0);
               linearLeft.setPower(0);
               runRobot = false;
            } else {
               if (height < 0) {
                  linearLeft.setPower(power);
                  linearRight.setPower(-power);
               } else if (height > 0) {
                  linearRight.setPower(power);
                  linearLeft.setPower(-power);
               }
            }
         }
      }
   }
   public void PIDTot (double targetV){
      PIDfl(targetV);
      PIDfr(targetV);
      PIDbl(targetV);
      PIDbr(targetV);
   }
   public void PIDTurn (double targetV){
      PIDfl(targetV);
      PIDfr(-targetV);
      PIDbl(targetV);
      PIDbr(-targetV);
   }
   public void PIDX(double targetX) {
      PIDtimer.reset();


      //substitute camera value for getPower
      double currentX = Math.abs((frontLeftMec.getCurrentPosition() / yellowJacketTics) * circumference);
      double error = targetX - currentX;
/*
        bot.integral = error / bot.PIDtimer.time();
        double deltaError = error - bot.lastError;
        double derivative = deltaError * bot.PIDtimer.time();

        bot.pidGain.p = bot.pidC.p * error;

        double rcw = bot.pidGain.p + derivative + bot.integral;
*/
      while (Math.abs(error) > 2) {
            /*bot.frontLeftMec.setPower(rcw);
            bot.frontRightMec.setPower(rcw);
            bot.backLeftMec.setPower(rcw);
            bot.backRightMec.setPower(rcw);*/

         frontLeftMec.setPower(.03*error);
         frontRightMec.setPower(.03*error);
         backLeftMec.setPower(.03*error);
         backRightMec.setPower(.03*error);
            /*bot.integral = error / bot.PIDtimer.time();
            deltaError = error - bot.lastError;
            derivative = deltaError * bot.PIDtimer.time();

            bot.pidGain.p = bot.pidC.p * error;

            rcw = bot.pidGain.p + derivative + bot.integral;*/
         currentX = ((frontLeftMec.getCurrentPosition() / yellowJacketTics) * circumference);
         error = targetX - currentX;
      }
      frontLeftMec.setPower(0);
      frontRightMec.setPower(0);
      backLeftMec.setPower(0);
      backRightMec.setPower(0);

      //bot.lastError = error;
      currentX = ((frontLeftMec.getCurrentPosition() / yellowJacketTics) * circumference);
      error = targetX - currentX;

   }

   public void PIDfl(double targetV){
      PIDtimer.reset();

      double currentV = frontLeftMec.getPower();
      double error = targetV - currentV;

      integral += error * PIDtimer.time();
      double deltaError = error - lastError;
      double derivative = deltaError / PIDtimer.time();

      pidGain.p = pidC.p * error;
      pidGain.i = pidC.i * integral;
      pidGain.d = pidC.d * derivative;

      frontLeftMec.setPower(pidGain.p + pidGain.i + pidGain.d + targetV);
      lastError = error;
   }
   public void PIDfr(double targetV){
      PIDtimer.reset();

      double currentV = frontRightMec.getPower();
      double error = targetV - currentV;

      integral += error * PIDtimer.time();
      double deltaError = error - lastError;
      double derivative = deltaError / PIDtimer.time();

      pidGain.p = pidC.p * error;
      pidGain.i = pidC.i * integral;
      pidGain.d = pidC.d * derivative;

      frontRightMec.setPower(pidGain.p + pidGain.i + pidGain.d + targetV);
      lastError = error;
   }
   public void PIDbl(double targetV){
      PIDtimer.reset();

      double currentV = backLeftMec.getPower();
      double error = targetV - currentV;

      integral += error * PIDtimer.time();
      double deltaError = error - lastError;
      double derivative = deltaError / PIDtimer.time();

      pidGain.p = pidC.p * error;
      pidGain.i = pidC.i * integral;
      pidGain.d = pidC.d * derivative;

      backLeftMec.setPower(pidGain.p + pidGain.i + pidGain.d + targetV);
      lastError = error;
   }
   public void PIDbr(double targetV){
      PIDtimer.reset();

      double currentV = backRightMec.getPower();
      double error = targetV - currentV;

      integral += error * PIDtimer.time();
      double deltaError = error - lastError;
      double derivative = deltaError / PIDtimer.time();

      pidGain.p = pidC.p * error;
      pidGain.i = pidC.i * integral;
      pidGain.d = pidC.d * derivative;

      backRightMec.setPower(pidGain.p + pidGain.i + pidGain.d + targetV);
      lastError = error;
   }/*
    mRsContext = new RsContext();
    mRsContext.setDevicesChangedCallback(new DeviceListener() {
        @Override
        public void onDeviceAttach() {
            printMessage();
        }

        @Override
        public void onDeviceDetach() {
            printMessage();
        }
    }*/
}

