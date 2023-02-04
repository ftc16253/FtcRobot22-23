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
            sleep(100);

            //Drive Forward
            bot.moveForwardInches(57, .75);
            sleep(500);

            //raise linear slide
            bot.LinearSlide(1);
            sleep(1625);
            bot.LinearSlide(0);
            sleep(200);

            //Move right
            bot.moveSideInches(17,.5);
            sleep (1000);
            sleep(1000);

            //lower linear slide
            bot.LinearSlide(-1);
            sleep(200);
            bot.LinearSlide(0);
            sleep(200);

            //drop cone
            bot.grabber.setPosition(bot.grabberOpenPos);
            sleep(1000);

            //lower linear slide
            bot.LinearSlide(-.75);
            sleep(1000);
            bot.LinearSlide(0);
            sleep(200);

            //move to cone stack
            bot.moveSideInches(5,-1);
            sleep(200);

            //turn to face cones
            bot.turn(-0.75);
            sleep(630);
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
/*
            //turn to tall pole
            bot.turn(.75);
            sleep(100);
            bot.turn(0);
            sleep(100);
*/
            //move to tall pole
            bot.moveForwardInches(41, -.5);
            sleep(100);

            //move linear slide up
            bot.LinearSlide(0.75);
            sleep(1450);
            bot.LinearSlide(0);
            sleep(100);

            //turn cone over pole
            bot.turn(0.75);
            sleep(585);
            bot.turn(0);
            sleep(2000);

            /*bot.moveForwardInches(4,-0.5);
            sleep(500);*/

            //lower cone over pole
            bot.LinearSlide(-0.75);
            sleep(200);
            bot.LinearSlide(0);
            sleep(2000);

            //drop cone
            bot.grabber.setPosition(bot.grabberOpenPos);
            sleep(200);

            /*bot.moveForwardInches(4,0.5);
            sleep(200);*/

            //turn to stack
            bot.turn(-0.75);
            sleep(555);
            bot.turn(0);
            sleep(200);

            //bring linear slide down
            bot.LinearSlide(-0.75);
            sleep(800);
            bot.LinearSlide(0);
            sleep(100);

            /*//move to stack
            bot.moveForwardInches(40, .5);
            sleep(200);

            bot.grabber.setPosition(bot.grabberClosePos);
            sleep(200);
*/

            //bot.place = "THREE";
            if (bot.place == "ONE"){
                //Move left
                bot.moveForwardInches(42.5,.75);
                sleep (1000);

                /*//Move backwards a little
                bot.moveForwardInches(2, -.5);
                sleep(500);*/
            }
            if (bot.place == "TWO"){
                //Move left
                bot.moveForwardInches(13,.75);
                sleep (1000);
            }
            if (bot.place == "THREE"){
                //Move right
                bot.moveForwardInches(10,-.75);
                sleep (1000);
            }
            sleep(30000);
        }
    }
}

