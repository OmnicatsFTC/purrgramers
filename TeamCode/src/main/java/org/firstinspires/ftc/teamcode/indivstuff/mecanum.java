//vivian 9/9/26 mecanum drive
//https://www.youtube.com/watch?v=sFCO4du5IZk&t=353s
//^^ video guide

package org.firstinspires.ftc.teamcode.indivstuff;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

//creates the motors and the imu
//the imu is like a sensor for a bunch of stuff
public class mecanum {
    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private IMU imu;

    public void init(HardwareMap hwMap){
        //names the motors
        frontLeft = hwMap.get(DcMotor.class, "frontLeftDrive");
        frontRight = hwMap.get(DcMotor.class, "frontRightDrive");
        backLeft = hwMap.get(DcMotor.class, "backLeftDrive");
        backRight = hwMap.get(DcMotor.class, "backRightDrive");
        //sets the motor directions bc they aren't all facing forwards
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        //runs the motors using the encoder(built into the motor so like yeah)
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //names the imu
        imu = hwMap.get(IMU.class, "imu");

        //sets the direction of the robot using the control hub
        RevHubOrientationOnRobot revOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.RIGHT);

        //uses the robot direction to set up the imu
        imu.initialize(new IMU.Parameters(revOrientation));
    }
    //robot oriented first, will be corrected to field oriented after
    public void drive(double forward, double strafe, double rotate){
        //sets the power for each of the motors
        double frontLeftPower = forward + strafe + rotate;
        double backLeftPower = forward - strafe + rotate;
        double frontRightPower = forward - strafe - rotate;
        double backRightPower = forward + strafe - rotate;

        double maxPower = 1.0;
        double maxSpeed = 1.0;

        //calculates the max power so all the motors have the same power
        maxPower = Math.max(maxPower, Math.abs(frontLeftPower));
        maxPower = Math.max(maxPower, Math.abs(backLeftPower));
        maxPower = Math.max(maxPower, Math.abs(frontRightPower));
        maxPower = Math.max(maxPower, Math.abs(backRightPower));

        //sets the motor power to each of their respective powers
        frontLeft.setPower(maxSpeed*(frontLeftPower/maxPower));
        backLeft.setPower(maxSpeed*(backLeftPower/maxPower));
        frontRight.setPower(maxSpeed*(frontRightPower/maxPower));
        backRight.setPower(maxSpeed*(backRightPower/maxPower));
    }

    //fancy math stuff to make the robot drive
    public void fieldOriented(double forward, double strafe, double rotate) {
        double theta = Math.atan2(forward, strafe);
        double r = Math.hypot(strafe, forward);

        theta = AngleUnit.normalizeRadians(theta - imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));

        double newForward = r * Math.sin(theta);
        double newStrafe = r * Math.cos(theta);

        //drives the robot
        this.drive(newForward, newStrafe, rotate);
    }
}
