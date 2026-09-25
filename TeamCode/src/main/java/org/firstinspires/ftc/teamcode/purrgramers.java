package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.indivstuff.mecanum;
import org.firstinspires.ftc.teamcode.indivstuff.mecanum;

@TeleOp(name = "purrgramers", group = "Robot")
public class purrgramers extends OpMode {

    mecanum drive = new mecanum();
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
