package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class TeleOp2 extends LinearOpMode {

    boolean SINGLE_PLAYER_MODE = false;

    GamepadHub gamepadHub;
    private LinearSlideController slideController;
    private ClawController clawController;
    
    @Override
    public void runOpMode() throws InterruptedException {
        // if single player mode, all actions are mapped onto a single gamepad.
        // otherwise, the actions are spread out between the two gamepads
        if (SINGLE_PLAYER_MODE) {
            gamepadHub = new GamepadHub(gamepad1);
        } else {
            gamepadHub = new GamepadHub(gamepad1, gamepad2);
        }

        driveController = new DriveController(hardwareMap);
        clawController = new ClawController(hardwareMap);

        DcMotor extend = hardwareMap.dcMotor.get("linearSlide");
        slideController = new LinearSlideController(extend);
        extend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Servo arm = hardwareMap.get(Servo.class, "arm");

        double desiredArmPos = 1;

        
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            // CONTROL DRIVING
            if (gamepadHub.slowDownButtonPressed()) {
                driveController.slowDown();
            } else if (gamepadHub.speedUpButtonPressed()) {
                driveController.speedUp();
            }
            driveController.drive(controllerHub.getDriveX(), controllerHub.getDriveY(), controllerHub.getDriveRX());

            // CONTROL CLAW
            if (gamepadHub.isOpenClawButtonPressed()) {
                claw.open();
            } else if (gamepadHub.isCloseClawButtonPressed()) {
                claw.close();
            }


            // CONTROL ARM
            // TODO: Refactor this too

            //arm.setPower(0.5);

            //double clawPower = gamepad1.right_trigger - gamepad1.left_trigger; // Takes input values of triggers (assuming values of 0-1) and subtracts
            
            //double extendPower = 0;
            double armPower = 0;
            
            
            if (gamepad2.dpad_up == true) {
                //extendPower = -1;
                slideController.TARGET_POSITION_TICKS += 100;
                sleep(25);
                //slideController.extendArm();
                
            } else if (gamepad2.dpad_down == true) {
                //extendPower = 1;
                slideController.TARGET_POSITION_TICKS -= 100;
                sleep(25);
                // slideController.retractArm();
                
            }
            if (slideController.TARGET_POSITION_TICKS > slideController.MAX_EXTEND_HEIGHT) {
                slideController.TARGET_POSITION_TICKS = slideController.MAX_EXTEND_HEIGHT;
            }
            if (slideController.TARGET_POSITION_TICKS < 0) {
                slideController.TARGET_POSITION_TICKS = 0;
            }
            
            if (gamepad2.y == true) {
                desiredArmPos += 0.025;
                sleep(25);
            } else if (gamepad2.a == true) {
                desiredArmPos -= 0.025;
                sleep(25);
            }
            //Limits to prevent claw from slamming against floor or robot
            if (desiredArmPos > 0.9) {
                desiredArmPos = 0.9;
            }
            if (desiredArmPos < 0.5) {
                desiredArmPos = 0.5;
            }

            //claw.setPosition(clawPower);
            arm.setPosition(desiredArmPos);
            //extend.setPower(extendPower);
            slideController.stopIfReached();
            //telemetry.addData("A", gamepad1.a);
            telemetry.addData("Slide Target Position", LinearSlideController.TARGET_POSITION_TICKS);
            telemetry.addData("Slide Position", extend.getCurrentPosition());
            telemetry.addData("Slide Motor Power", extend.getPower());
            telemetry.addData("Slide Busy", extend.isBusy());
            slideController.extendSlide();
            telemetry.update();
            
        }
        
        
    }
    

    
}
