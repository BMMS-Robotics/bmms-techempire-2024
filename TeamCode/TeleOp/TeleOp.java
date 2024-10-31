package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class TeleOp2 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
        DcMotor extend = hardwareMap.dcMotor.get("linearSlide");
        //Servo arm = hardwareMap.get(Servo.class, "arm");
        Servo claw = hardwareMap.get(Servo.class, "claw");

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            //left_trigger, right_
            double y = gamepad1.left_stick_y; // Remember, Y stick value is (no longer) reversed
            double x = -gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = -gamepad1.right_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            //double clawPower = gamepad1.right_trigger - gamepad1.left_trigger; // Takes input values of triggers (assuming values of 0-1) and subtracts
            
            double extendPower = 0;
            double armPower = 0;
            if (gamepad1.dpad_up == true) {
                extendPower = 1;
            } else if (gamepad1.dpad_down == true) {
                extendPower = -1;
            } else {
                extendPower = 0;
            }
            double clawPower = 0;
            if (gamepad1.right_trigger == 1) {
                clawPower = 1;
            } else if (gamepad1.left_trigger == 1) {
                clawPower = -1;
            } else {
                clawPower = 0;
            }
            //double extendPower = gamepad1.dpad_up - gamepad1.dpad_down;
            
            //double armPower = gamepad1.y - gamepad1.a;
            if (gamepad1.y == true) {
                armPower = 1;
            } else if (gamepad1.a == true) {
                armPower = -1;
            } else {
                armPower = 0;
            }
            
            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);
            claw.setPosition(clawPower);
            //arm.setPower(armPower);
            extend.setPower(extendPower);
        }
    }
}
