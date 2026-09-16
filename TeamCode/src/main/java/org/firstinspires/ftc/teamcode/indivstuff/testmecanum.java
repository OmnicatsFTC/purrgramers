//vivian 9/9/26 mecanum drive
//https://www.youtube.com/watch?v=sFCO4du5IZk&t=353s
//^^ video guide

package org.firstinspires.ftc.teamcode.indivstuff;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class testmecanum {
    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private IMU imu;


    public void init(HardwareMap hwMap){
        //motors
        frontLeft = hwMap.get(DcMotor.class, "frontLeftDrive");
        frontRight = hwMap.get(DcMotor.class, "frontRightDrive");
        backLeft = hwMap.get(DcMotor.class, "backLeftDrive");
        backRight = hwMap.get(DcMotor.class, "backRightDrive");
        //motors
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        //motors
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //imu
        imu = hwMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot revOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.RIGHT);

        imu.initialize(new IMU.Parameters(revOrientation));
    }
    //robot oriented first, will be corrected to field oriented after
    public void drive(double forward, double strafe, double rotate){
        double frontLeftPower = forward + strafe + rotate;
        double backLeftPower = forward - strafe + rotate;
        double frontRightPower = forward - strafe - rotate;
        double backRightPower = forward + strafe - rotate;

        double maxPower = 1.0;

        maxPower = Math.max(maxPower, Math.abs(frontLeftPower));
        maxPower = Math.max(maxPower, Math.abs(backLeftPower));
        maxPower = Math.max(maxPower, Math.abs(frontRightPower));
        maxPower = Math.max(maxPower, Math.abs(backRightPower));

        frontLeft.setPower(frontLeftPower/maxPower);
        backLeft.setPower(backLeftPower/maxPower);
        frontRight.setPower(frontRightPower/maxPower);
        backRight.setPower(backRightPower/maxPower);
    }

    public void fieldOriented(double forward, double strafe, double rotate) {
        double theta = Math.atan2(forward, strafe);
        double r = Math.hypot(strafe, forward);

        theta = AngleUnit.normalizeRadians(theta - imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));

        double newForward = r * Math.sin(theta);
        double newStrafe = r * Math.cos(theta);

        this.drive(newForward, newStrafe, rotate);
    }
}
