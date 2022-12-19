package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Drive2022 extends LinearOpMode {
    PushBot2022 bot = new PushBot2022();
    public void runOpMode() {
        bot.init(hardwareMap, false);

        waitForStart();

        while (opModeIsActive()){
            if (bot.robotSpeed == .5 && gamepad1.right_bumper == true){
                bot.robotSpeed = 0.75;
                sleep(200);
            }
            if (bot.robotSpeed == .75 && gamepad1.right_bumper == true){
                bot.robotSpeed = 1;
                sleep(200);
            }
            if (bot.robotSpeed == 1 && gamepad1.left_bumper == true){
                bot.robotSpeed = 0.75;
                sleep(200);
            }
            if (bot.robotSpeed == 0.75 && gamepad1.left_bumper == true){
                bot.robotSpeed = 0.5;
                sleep(200);
            }

            if (gamepad1.left_stick_y != 0){
                // bot.PIDTot(gamepad1.right_stick_y);
                bot.moveForward(-gamepad1.left_stick_y * bot.robotSpeed);
            }
            if (gamepad1.right_stick_x != 0){
                //bot.PIDTurn(gamepad1.right_stick_x);
                bot.turn(gamepad1.right_stick_x * bot.robotSpeed);
            }
            /*if (gamepad1.right_stick_x > 0 && gamepad1.right_stick_y > 0) {
                //move forward diagonally to the right
               // bot.PIDfr(.9);
               // bot.PIDbl(.9);
                bot.frontRightMec.setPower(1);
                bot.backLeftMec.setPower(1);
            }else if (gamepad1.right_stick_x > 0 && gamepad1.right_stick_y < 0) {
                //move backward diagonally to the right
               // bot.PIDfl(-.9);
               // bot.PIDbr(-.9);
                bot.frontLeftMec.setPower(-1);
                bot.backRightMec.setPower(-1);
            }else if (gamepad1.right_stick_x < 0 && gamepad1.right_stick_y > 0) {
                //move forward diagonally to the left
              //  bot.PIDfl(.9);
               // bot.PIDbr(.9);
                bot.frontLeftMec.setPower(1);
                bot.backRightMec.setPower(1);
            }else if (gamepad1.right_stick_x < 0 && gamepad1.right_stick_y < 0) {
                //move backward diagonally to the left
               // bot.PIDfr(-.9);
               // bot.PIDbl(-.9);
                bot.frontRightMec.setPower(-1);
                bot.backLeftMec.setPower(-1);
            }else */
            if (gamepad1.left_stick_x != 0){
                bot.moveSide(-gamepad1.left_stick_x * bot.robotSpeed);
            }
            if (gamepad1.left_stick_y == 0 && gamepad1.right_stick_x == 0 && gamepad1.left_stick_x == 0){
                bot.frontRightMec.setPower(0);
                bot.frontLeftMec.setPower(0);
                bot.backLeftMec.setPower(0);
                bot.backRightMec.setPower(0);
            }

            //Open grabber
            if (gamepad2.a){
                bot.grabber.setPosition(bot.grabberOpenPos);
            }
            //Close grabber
            if (gamepad2.b) {
                bot.grabber.setPosition(bot.grabberClosePos);
            }

            //Move linear slide up and down
            if (gamepad2.left_stick_y > 0){
                bot.linearRight.setPower(gamepad2.left_stick_y);
                bot.linearLeft.setPower(gamepad2.left_stick_y);
            }
            if (gamepad2.left_stick_y < 0){
                bot.linearRight.setPower(gamepad2.left_stick_y / 2);
                bot.linearLeft.setPower(gamepad2.left_stick_y / 2);
            }
            else {
                bot.linearRight.setPower(0);
                bot.linearLeft.setPower(0);
            }
        }
    }
}
