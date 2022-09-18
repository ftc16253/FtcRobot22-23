package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class Auto2022 extends LinearOpMode {
   PushBot2022 bot = new PushBot2022();
   int t=0;

   @Override
   public void runOpMode(){
      bot.init(hardwareMap, true);

      waitForStart();

      while (opModeIsActive()){
         while (t < 1) {
            bot.place = bot.detector.position;
            bot.centerTotal = bot.detector.centerTotal;
            telemetry.addData("position = ", bot.place);
            telemetry.addData("centerTotal = ", bot.centerTotal);
            telemetry.update();
            t = t + 1;
         }
         //bot.PIDX(48);
         sleep(30000);
      }
   }
}

