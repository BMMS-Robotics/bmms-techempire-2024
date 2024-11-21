package org.firstinspires.ftc.teamcode;

public class ClawController {

    Servo claw ;

    public ClawController(HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "claw");
    }

    public void open() {
        claw.setPosition(1); // i don't know if 1 is open or close (part of why it's helpful to encapsulate logic in classes using methods that are self describing)
    }

    public void close() {
        claw.setPosition(0);  // i don't know if 0 is open or close
    }
}
