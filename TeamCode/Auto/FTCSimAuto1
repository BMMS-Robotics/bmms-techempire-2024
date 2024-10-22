
/***********************************************************************
*                                                                      *
* OnbotJava Editor is still : beta! Please inform us of any bugs       |
* on our discord channel! https://discord.gg/e7nVjMM                   *
* Only BLOCKS code is submitted when in Arena                          *
*                                                                      *
***********************************************************************/


public class MyFIRSTJavaOpMode extends LinearOpMode {
    DcMotor backLeftDrive;
    DcMotor backRightDrive;
    DcMotor frontLeftDrive;
    DcMotor frontRightDrive;
    DcMotor armTilt;
    DcMotor armExtend;
    DcMotor claw;
    BNO055IMU imu;

@Override
    public void runOpMode() {
      backLeftDrive = hardwareMap.get(DcMotor.class, "backLeftDrive");
      backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive");
      frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");
      frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");
      armTilt = hardwareMap.get(DcMotor.class, "armTilt");
      armExtend = hardwareMap.get(DcMotor.class, "armExtend");
      claw = hardwareMap.get(DcMotor.class, "claw");
      imu = hardwareMap.get(BNO055IMU.class, "imu");
      frontRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
      backRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);

      // Put initialization blocks here
      claw.setPower(-1);
      sleep(100);
      Forward(1000, 1);
      armTilt.setPower(1);
      sleep(500);
      armTilt.setPower(0);
      sleep(200);
      claw.setPower(1);
      sleep(500);
      Backward(500, 1);
      Left(2250, 1);
      Forward(1750, 1);
      Left(1500, 1);
      Right(500, 1);
      Forward(500, 1);
      Left(750, 1);
      Backward(2000, 1);
      
      
      
    }
    
    public void Forward(int time, int power) {
      frontLeftDrive.setPower(-power);
      backLeftDrive.setPower(-power);
      frontRightDrive.setPower(-power);
      backRightDrive.setPower(-power);
      sleep(time);
      frontLeftDrive.setPower(0);
      backLeftDrive.setPower(0);
      frontRightDrive.setPower(0);
      backRightDrive.setPower(0);
    }
    
    public void Backward(int time, int power) {
      frontLeftDrive.setPower(power);
      backLeftDrive.setPower(power);
      frontRightDrive.setPower(power);
      backRightDrive.setPower(power);
      sleep(time);
      frontLeftDrive.setPower(0);
      backLeftDrive.setPower(0);
      frontRightDrive.setPower(0);
      backRightDrive.setPower(0);
    }
    
    public void Left(int time, int power) {
      frontLeftDrive.setPower(power);
      backLeftDrive.setPower(-power);
      frontRightDrive.setPower(-power);
      backRightDrive.setPower(power);
      sleep(time);
      frontLeftDrive.setPower(0);
      backLeftDrive.setPower(0);
      frontRightDrive.setPower(0);
      backRightDrive.setPower(0);
    }
    
    public void Right(int time, int power) {
      frontLeftDrive.setPower(-power);
      backLeftDrive.setPower(power);
      frontRightDrive.setPower(power);
      backRightDrive.setPower(-power);
      sleep(time);
      frontLeftDrive.setPower(0);
      backLeftDrive.setPower(0);
      frontRightDrive.setPower(0);
      backRightDrive.setPower(0);
    }
    
    public void diagonalFL(int time, int power) {
      frontLeftDrive.setPower(0);
      backLeftDrive.setPower(-power);
      frontRightDrive.setPower(-power);
      backRightDrive.setPower(0);
      sleep(time);
      frontLeftDrive.setPower(0);
      backLeftDrive.setPower(0);
      frontRightDrive.setPower(0);
      backRightDrive.setPower(0);
    }
    
    public void diagonalFR(int time, int power) {
      frontLeftDrive.setPower(-power);
      backLeftDrive.setPower(0);
      frontRightDrive.setPower(0);
      backRightDrive.setPower(-power);
      sleep(time);
      frontLeftDrive.setPower(0);
      backLeftDrive.setPower(0);
      frontRightDrive.setPower(0);
      backRightDrive.setPower(0);
    }
    
    public void diagonalBL(int time, int power) {
      frontLeftDrive.setPower(power);
      backLeftDrive.setPower(0);
      frontRightDrive.setPower(0);
      backRightDrive.setPower(power);
      sleep(time);
      frontLeftDrive.setPower(0);
      backLeftDrive.setPower(0);
      frontRightDrive.setPower(0);
      backRightDrive.setPower(0);
    }
    
    public void diagonalBR(int time, int power) {
      frontLeftDrive.setPower(0);
      backLeftDrive.setPower(power);
      frontRightDrive.setPower(power);
      backRightDrive.setPower(0);
      sleep(time);
      frontLeftDrive.setPower(0);
      backLeftDrive.setPower(0);
      frontRightDrive.setPower(0);
      backRightDrive.setPower(0);
    }
    
    public void turnLeft(int time, int power) {
      frontLeftDrive.setPower(power);
      backLeftDrive.setPower(power);
      frontRightDrive.setPower(-power);
      backRightDrive.setPower(-power);
      sleep(time);
      frontLeftDrive.setPower(0);
      backLeftDrive.setPower(0);
      frontRightDrive.setPower(0);
      backRightDrive.setPower(0);
    }
    public void turnRight(int time, int power) {
      frontLeftDrive.setPower(-power);
      backLeftDrive.setPower(-power);
      frontRightDrive.setPower(power);
      backRightDrive.setPower(power);
      sleep(time);
      frontLeftDrive.setPower(0);
      backLeftDrive.setPower(0);
      frontRightDrive.setPower(0);
      backRightDrive.setPower(0);
    }
}
