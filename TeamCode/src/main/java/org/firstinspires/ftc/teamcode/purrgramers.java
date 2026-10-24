package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.indivstuff.ramp;
import org.firstinspires.ftc.teamcode.indivstuff.intake;
import org.firstinspires.ftc.teamcode.indivstuff.mecanum;
import org.firstinspires.ftc.teamcode.indivstuff.shooter;

/*
left bumper - intake out
right bumper - intake in
left trigger -
joysticks - drive
a - ll stuff NONONONO IGNORE
b - shooter trigger
x - servo
y -
*/

//needs this line so it will appear in the driver hub
@TeleOp(name = "purrgramers", group = "Robot")
public class purrgramers extends OpMode {

    //makes new classes (? i forgot the term for it but like creates a new instance or something)
    //for the mechanisms
    mecanum drive = new mecanum();
    intake intake = new intake();
    shooter shooter = new shooter();
    double forward, strafe, rotate;

    //initilizes the instances with hardwareMap (hwMap connects the code and the actual motors)
    @Override
    public void init(){
        drive.init(hardwareMap);
        shooter.init(hardwareMap);
        intake.init(hardwareMap);
        //shooter init
        //shooter = hardwareMap.get(DcMotor.class, "shooter");
        //shooter.setDirection(DcMotorSimple.Direction.FORWARD);
        //shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    //runs the mecanum method with the forward, strafe, and rotate
    //varibles and also runs the intake method
    @Override
    public void loop() {
        forward = gamepad1.left_stick_y;
        strafe = -gamepad1.left_stick_x;
        rotate = -gamepad1.right_stick_x;//km switched postive to negative to match hardware, might need to change
        //drive.fieldOriented(forward,strafe,rotate);
        drive.drive(forward,strafe,rotate);


        boolean shooterButton1 = gamepad1.b;
        shooter.shooterRun(shooterButton1);

        boolean servo = gamepad1.x;
        boolean intake1 = gamepad1.left_bumper;
        boolean intake2 = gamepad1.right_bumper;
        intake.intakeRun(intake1, intake2, servo);

        //shooter code bc the gamepad won't work if in a separate file for some reason?
        /*
        if (gamepad1.leftTriggerWasPressed()){
            shooter.setPower(0.15);
        } else if(gamepad1.rightTriggerWasPressed()){
            shooter.setPower(.53);
        } else if (gamepad1.leftTriggerWasReleased()){
            shooter.setPower(0);
        } else if (gamepad1.rightTriggerWasReleased()){
            shooter.setPower(0);
        }
         */


    }
//nothing else :)
}
