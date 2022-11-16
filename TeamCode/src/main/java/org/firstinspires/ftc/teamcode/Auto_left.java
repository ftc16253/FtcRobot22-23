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

            bot.moveForward(1);
            sleep(700);
            bot.moveForward(.25);
            sleep(150);
            bot.moveForward(0);
            bot.frontLeftMec.setPower(.5);
            bot.backLeftMec.setPower(.5);
            sleep(1250);
            bot.backLeftMec.setPower(0);
            bot.frontLeftMec.setPower(0);
            sleep(100);
            bot.frontLeftMec.setPower(-.5);
            bot.backLeftMec.setPower(-.5);
            sleep(1250);
            bot.backLeftMec.setPower(0);
            bot.frontLeftMec.setPower(0);
            sleep(1000);
            if (bot.place == "ONE"){
                bot.moveSide(1);
                sleep(500);
                bot.moveSide(0);
            }
            if (bot.place == "TWO"){

            }
            if (bot.place == "THREE"){
                bot.moveSide(-1);
                sleep(600);
                bot.moveSide(0);
            }
            //bot.PIDX(48);
            sleep(30000);
        }
    }
}

