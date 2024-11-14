package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class LinearSlideController {
    public static /*final*/ int TARGET_POSITION_TICKS = 0; // For 4 feet with a specific ticks-per-foot calculation
    int POSITION_TOLERANCE = 10;

    private DcMotor slideMotor;
    
    int MAX_EXTEND_HEIGHT = 3850;

    public LinearSlideController(DcMotor motor) {
        this.slideMotor = motor;

        // Reset encoder to start at zero
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Set motor direction if needed
        slideMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set motor to RUN_TO_POSITION mode for position control
        slideMotor.setTargetPosition(TARGET_POSITION_TICKS);
        

        // Set power for the motor to start moving towards the target
        //slideMotor.setPower(0);
    }

    public void extendSlide() {
        // Start moving the arm to the target position
        slideMotor.setTargetPosition(TARGET_POSITION_TICKS);
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if (slideMotor.getCurrentPosition() != MAX_EXTEND_HEIGHT && slideMotor.getPower() == 0) {
            slideMotor.setPower(1); // Full power to reach target
        }
        //Maybe use abs value for better tolerance both up and down
        if (slideMotor.getCurrentPosition() <= POSITION_TOLERANCE && TARGET_POSITION_TICKS == 0 && slideMotor.getPower() != 1) { //Check if we're at/less than 0 and we want to be at 0. If so, turn off motor to reduce strain.
            slideMotor.setPower(0);
        }
    }
    

    public void stopIfReached() {
        // Check if the arm has reached its target position
        if (!slideMotor.isBusy()) {
            // If the motor is no longer busy, stop the motor
            slideMotor.setPower(0);
            //slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Reset encoder if needed
        }
        if (slideMotor.getCurrentPosition() > MAX_EXTEND_HEIGHT) {
            slideMotor.setPower(0);
        }
        if (slideMotor.getCurrentPosition() < 0) {
            slideMotor.setPower(0);
        }
    }
}



// main code

