package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.indivstuff.testmecanum;

public class purrgramers extends OpMode {

    testmecanum drive = new testmecanum();
    double forward, strafe, rotate;

    @Override
    public void init(){
        drive.init(hardwareMap);

    }

    @Override
    public void loop() {
        forward = gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        drive.fieldOriented(forward,strafe,rotate);

    }

}
