package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class Auto_left_enc extends LinearOpMode {
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

            bot.grabber.setPosition(bot.grabberClosePos);
            sleep(1000);

            //Drive Forward
            bot.moveForwardInches(57.5, .75);
            sleep(500);

            //raise linear slide
            bot.LinearSlide(1);
            sleep(1500);
            bot.LinearSlide(0);
            sleep(200);

            //Move right
            bot.moveSideInches(13,.5);
            sleep (1000);

            //lower linear slide a little
            bot.LinearSlide(-0.25);
            sleep(200);
            bot.LinearSlide(0);
            sleep(1000);

            //drop cone
            bot.grabber.setPosition(bot.grabberOpenPos);
            sleep(1000);

            //lower linear slide
            bot.LinearSlide(-.75);
            sleep(1250);
            bot.LinearSlide(0);
            sleep(200);

            //bot.place = "THREE";
            if (bot.place == "ONE"){
                //Move left
                bot.moveSideInches(45,-.5);
                sleep (1000);

                //Move backwards a little
                bot.moveForwardInches(2, -.5);
                sleep(500);
            }
            if (bot.place == "TWO"){
                //Move left
                bot.moveSideInches(13,-.5);
                sleep (1000);
            }
            if (bot.place == "THREE"){
                //Move right
                bot.moveSideInches(13,.5);
                sleep (1000);
            }

            sleep(30000);
        }
    }
}
