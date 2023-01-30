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
            if (gamepad1.left_stick_x != 0){
                bot.moveSide(-gamepad1.left_stick_x * bot.robotSpeed);
            }
            if (gamepad1.left_stick_y == 0 && gamepad1.right_stick_x == 0 && gamepad1.left_stick_x == 0){
                bot.frontRightMec.setPower(0);
                bot.frontLeftMec.setPower(0);
                bot.backLeftMec.setPower(0);
                bot.backRightMec.setPower(0);
            }
           /* if (bot.Limit.isPressed() == true && gamepad2.left_stick_y < 0){
                bot.LinearSlide(0);
            }*/

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
                if (bot.Limit.isPressed()) {
                    bot.linearRight.setPower(0);
                    bot.linearLeft.setPower(0);
                } else {
                    bot.linearRight.setPower(gamepad2.left_stick_y);
                    bot.linearLeft.setPower(gamepad2.left_stick_y);
                }
            }
            if (gamepad2.left_stick_y < 0){
                bot.linearRight.setPower(gamepad2.left_stick_y);
                bot.linearLeft.setPower(gamepad2.left_stick_y);
            }
            else {
                bot.linearRight.setPower(0);
                bot.linearLeft.setPower(0);
            }



            /*if (bot.Limit.isPressed())
            {
                if (gamepad2.left_stick_y < 0){
                    bot.LinearSlide(0);
                }
                /*bot.linearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                bot.linearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }*/
            /*if (gamepad2.dpad_right = true)
            {
                bot.LinearSlideEnc(1,0);
            }
            else if (gamepad2.dpad_down = true)
            {
                bot.LinearSlideEnc(1, 12);
            }
            else if (gamepad2.dpad_left = true)
            {
                bot.LinearSlideEnc(1, 22);
            }
            else if (gamepad2.dpad_up = true)
            {
                bot.LinearSlideEnc(1, 32);
            }*/
        }
    }
}
