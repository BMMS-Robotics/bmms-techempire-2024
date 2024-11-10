package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class TeleOp2 extends LinearOpMode {
    
    private LinearArmController slideController;
    
    @Override
    public void runOpMode() throws InterruptedException {
        
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
        DcMotor extend = hardwareMap.dcMotor.get("linearSlide");
        slideController = new LinearArmController(extend);
        extend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Servo arm = hardwareMap.get(Servo.class, "arm");
        Servo claw = hardwareMap.get(Servo.class, "claw");
        

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        double desiredArmPos = 1;
        double slowMo = 1;
        
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            //left_trigger, right_
            
            //POTENTIAL add slow-mo toggle
            double y = gamepad1.right_stick_y / slowMo; // Remember, Y stick value is (no longer) reversed
            double x = -gamepad1.right_stick_x * 1.1 / slowMo; // Counteract imperfect strafing
            double rx = -gamepad1.left_stick_x / slowMo;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;
            
            //arm.setPower(0.5);

            //double clawPower = gamepad1.right_trigger - gamepad1.left_trigger; // Takes input values of triggers (assuming values of 0-1) and subtracts
            
            //double extendPower = 0;
            double armPower = 0;
            
            
            if (gamepad1.dpad_up == true) {
                //extendPower = -1;
                slideController.extendArm();
                
            } else if (gamepad1.dpad_down == true) {
                //extendPower = 1;
                
            }
            
            //Get the claw
            if (gamepad1.right_trigger == 1) {
                claw.setPosition(0);
            } else if (gamepad1.left_trigger == 1) {
                claw.setPosition(1);
            }
            
            //Slowmo mode, maybe switch to triggers later?
            if (gamepad1.x == true) {
                slowMo = 10;
            }
            if (gamepad1.b == true) {
                slowMo = 2.5;
            }
            
            if (gamepad1.y == true) {
                desiredArmPos += 0.01;
                sleep(25);
            } else if (gamepad1.a == true) {
                desiredArmPos -= 0.01;
                sleep(25);
            }
            //Limits to prevent claw from slamming against floor or robot
            if (desiredArmPos > 0.9) {
                desiredArmPos = 0.9;
            }
            if (desiredArmPos < 0.6) {
                desiredArmPos = 0.6;
            }
            
            
            
            
            
            
            
            
            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);
            //claw.setPosition(clawPower);
            arm.setPosition(desiredArmPos);
            //extend.setPower(extendPower);
            slideController.stopIfReached();
            //telemetry.addData("A", gamepad1.a);
            telemetry.addData("Arm Target Position", LinearArmController.TARGET_POSITION_TICKS);
            telemetry.addData("Arm Position", extend.getCurrentPosition());
            telemetry.addData("Arm Motor Power", extend.getPower());
            telemetry.addData("Arm Busy", extend.isBusy());
            
            telemetry.update();
            
        }
        
        
    }
    

    
}
