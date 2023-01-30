package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class Auto_right extends LinearOpMode {
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
            sleep(100);

            //Drive Forward
            bot.moveForwardInches(55.5, .75);
            sleep(500);

            //raise linear slide
            bot.LinearSlide(1);
            sleep(1400);
            bot.LinearSlide(0);
            sleep(200);

            //Move left
            bot.moveSideInches(15,.5);
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
            sleep(800);
            bot.LinearSlide(0);
            sleep(200);
/*
            //move to cone stack
            bot.moveSideInches(10,1);
            sleep(200);

            //turn to face cones
            bot.turn(-0.75);
            sleep(625);
            bot.turn(0);
            sleep(100);

            //move to cone stack
            bot.moveForwardInches(30,.75);
            sleep(300);

            //move to grab cone
            bot.moveForwardInches(1.5,0.75);
            sleep(100);

            //grab cone
            bot.grabber.setPosition(bot.grabberClosePos);
            sleep(500);

            //raise cone up
            bot.LinearSlide(0.75);
            sleep(500);
            bot.LinearSlide(0);
            sleep(100);

            //turn to tall pole
            bot.turn(.75);
            sleep(100);
            bot.turn(0);
            sleep(100);

            //move to tall pole
            bot.moveForwardInches(41, -.5);
            sleep(100);

            //move linear slide up
            bot.LinearSlide(0.75);
            sleep(1150);
            bot.LinearSlide(0);
            sleep(100);

            //turn cone over pole
            bot.turn(0.75);
            sleep(550);
            bot.turn(0);
            sleep(500);

            /*bot.moveForwardInches(4,-0.5);
            sleep(500);

            //drop cone
            bot.grabber.setPosition(bot.grabberOpenPos);
            sleep(200);

            /*bot.moveForwardInches(4,0.5);
            sleep(200);

            //turn to stack
            bot.turn(-0.75);
            sleep(505);
            bot.turn(0);
            sleep(200);

            //bring linear slide down
            bot.LinearSlide(-0.75);
            sleep(800);
            bot.LinearSlide(0);
            sleep(100);
            bot.moveForwardInches(40, .5);
            sleep(100);
*/

            //bot.place = "THREE";
            if (bot.place == "ONE"){
                //Move left
                bot.moveSideInches(13,-.5);
                sleep (1000);
            }
            if (bot.place == "TWO"){
                //Move right
                bot.moveSideInches(13,.5);
                sleep (1000);
            }
            if (bot.place == "THREE"){
                //Move right
                bot.moveSideInches(45,.5);
                sleep (1000);

                //Move backwards a little
                bot.moveForwardInches(2, -.5);
                sleep(500);
            }
            sleep(30000);
        }
    }
}

