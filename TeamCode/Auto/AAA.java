package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class AAA extends LinearOpMode {
    // DcMotor backLeftMotor;
    // DcMotor backRightMotor;
    // DcMotor frontLeftMotor;
    // DcMotor frontRightMotor;
    // DcMotor armTilt;
    // DcMotor armExtend;
    // DcMotor claw;
    
    BNO055IMU imu;
    @Override
    public void runOpMode() throws InterruptedException {
        
        // // Declare our motors
        // // Make sure your ID's match your configuration
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
        //frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        //backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            frontLeftMotor.setPower(-1);
            backLeftMotor.setPower(-1);
            frontRightMotor.setPower(-1);
            backRightMotor.setPower(-1);
            sleep(100);
            
            frontLeftMotor.setPower(1);
            backLeftMotor.setPower(1);
            frontRightMotor.setPower(1);
            backRightMotor.setPower(1);
        }
    }
    
    
}
