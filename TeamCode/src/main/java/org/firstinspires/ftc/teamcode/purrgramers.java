package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.indivstuff.intake;
import org.firstinspires.ftc.teamcode.indivstuff.mecanum;

//needs this line so it will appear in the driver hub
@TeleOp(name = "purrgramers", group = "Robot")
public class purrgramers extends OpMode {

    //makes new classes (? i forgot the term for it but like creates a new instance or something)
    //for the intake and mecanum
    mecanum drive = new mecanum();
    intake intake = new intake();
    //creates the variables for mecanum
    double forward, strafe, rotate;

    //initilizes the instances with hardwareMap (hwMap connects the code and the actual motors)
    @Override
    public void init(){
        drive.init(hardwareMap);
        intake.init(hardwareMap);
    }

    //runs the mecanum method with the forward, strafe, and rotate
    //varibles and also runs the intake method
    @Override
    public void loop() {
        forward = gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        drive.fieldOriented(forward,strafe,rotate);

        intake.intakeRun();

    }
//nothing else :)
}
