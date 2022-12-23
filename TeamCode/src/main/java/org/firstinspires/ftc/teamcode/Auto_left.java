package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class Auto_left extends LinearOpMode {
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
            bot.moveForward(.75);
            sleep(1220);
            //At the last tile in the zone
            bot.moveForward(0);
            sleep(500);
            //raise linear slide
            bot.LinearSlide(1);
            sleep(1500);
            bot.LinearSlide(0);
            sleep(200);
            //Move in front of ground station
            bot.moveSide(-1);
            sleep(375);
            bot.moveSide(0);
            sleep(2000);
            bot.LinearSlide(-0.25);
            sleep(200);
            bot.LinearSlide(0);
            sleep(1000);
            //drop cone
            bot.grabber.setPosition(bot.grabberOpenPos);
            sleep(1000);
            bot.LinearSlide(-.75);
            sleep(1250);
            bot.LinearSlide(0);
            sleep(200);
            if (bot.place == "ONE"){
                bot.moveSide(1);
                sleep(800);
                bot.moveSide(0);
                sleep(200);
                bot.moveForward(-.25);
                sleep(500);
                bot.moveForward(0);
            }
            if (bot.place == "TWO"){
                bot.moveSide(1);
                sleep(300);
                bot.moveSide(0);

            }
            if (bot.place == "THREE"){
                bot.moveSide(-1);
                sleep(400);
                bot.moveSide(0);
            }
            //bot.PIDX(48);
            sleep(30000);
        }
    }
}

