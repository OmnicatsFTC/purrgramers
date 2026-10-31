package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auton shoot", group="Linear Opmode")
public class Auton_noPredo extends LinearOpMode {

    // Declare drive motors
    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightBack = null;

    private DcMotor shooter = null;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        // Initialize the hardware variables.
        leftFront  = hardwareMap.get(DcMotor.class, "front_left_drive");
        rightFront = hardwareMap.get(DcMotor.class, "front_right_drive");
        leftBack   = hardwareMap.get(DcMotor.class, "back_left_drive");
        rightBack  = hardwareMap.get(DcMotor.class, "back_right_drive");

        // Depending on your motor/gear configuration, reverse the left or right side.
        // Usually, mecanum setups require reversing one side. Adjust as needed for your build.
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.FORWARD);

        // Send telemetry message to signify robot waiting
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (Driver presses START)
        waitForStart();
        runtime.reset();

        // --- AUTONOMOUS SEQUENCE ---

        // Move forward at 30% power for 1.5 seconds (1500 milliseconds)
        double power = 0.3;
        leftFront.setPower(power);
        rightFront.setPower(power);
        leftBack.setPower(power);
        rightBack.setPower(power);

        sleep(2550); // Run for 2.55 seconds

        // turn right
        leftFront.setPower(0);
        rightFront.setPower(0.5);
        leftBack.setPower(0);
        rightBack.setPower(0.5);

        sleep(1000); // Run for 1 second

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }
}
