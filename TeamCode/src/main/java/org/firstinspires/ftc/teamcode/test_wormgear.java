package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class test_wormgear extends LinearOpMode {
   DcMotor wormGear;

   @Override
    public void runOpMode() {
       wormGear = hardwareMap.get(DcMotor.class, "wormGear");

       waitForStart();

       while (opModeIsActive()) {
           wormGear.setPower(gamepad1.left_stick_y);
       }
    }
 }
