package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class LinearArmController {
    public static /*final*/ int TARGET_POSITION_TICKS = 0; // For 4 feet with a specific ticks-per-foot calculation

    private DcMotor armMotor;
    
    int MAX_EXTEND_HEIGHT = 3850;

    public LinearArmController(DcMotor motor) {
        this.armMotor = motor;

        // Reset encoder to start at zero
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Set motor direction if needed
        armMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set motor to RUN_TO_POSITION mode for position control
        armMotor.setTargetPosition(TARGET_POSITION_TICKS);
        

        // Set power for the motor to start moving towards the target
        //armMotor.setPower(0);
    }

    public void extendArm() {
        // Start moving the arm to the target position
        armMotor.setTargetPosition(TARGET_POSITION_TICKS);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if (armMotor.getCurrentPosition() < MAX_EXTEND_HEIGHT) {
            armMotor.setPower(1); // Full power to reach target
        }
    }
    

    public void stopIfReached() {
        // Check if the arm has reached its target position
        if (!armMotor.isBusy()) {
            // If the motor is no longer busy, stop the motor
            armMotor.setPower(0);
            //armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Reset encoder if needed
        }
        if (armMotor.getCurrentPosition() > MAX_EXTEND_HEIGHT) {
            armMotor.setPower(0);
        }
        if (armMotor.getCurrentPosition() < 0) {
            armMotor.setPower(0);
        }
    }
}



// main code

