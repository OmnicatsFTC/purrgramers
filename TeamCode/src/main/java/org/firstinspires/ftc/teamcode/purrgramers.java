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
left bumper - intake __ (in/out)
right bumper - intake __ (in/out)
left trigger - slower of the shooter speeds
right trigger - faster of the shooter speeds
joysticks - drive
a - ll stuff
b -
x -
y - ramp/indexer
*/

//needs this line so it will appear in the driver hub
@TeleOp(name = "purrgramers", group = "Robot")
public class purrgramers extends OpMode {

    //makes new classes (? i forgot the term for it but like creates a new instance or something)
    //for the mechanisms
    mecanum drive = new mecanum();
    intake intake = new intake();
    shooter shooter = new shooter();
    ramp indexer = new ramp();
    double forward, strafe, rotate;

    //initilizes the instances with hardwareMap (hwMap connects the code and the actual motors)
    @Override
    public void init(){
        drive.init(hardwareMap);
        shooter.init(hardwareMap);
        indexer.init(hardwareMap);
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
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;
        drive.fieldOriented(forward,strafe,rotate);

        double shooterButton1 = gamepad1.left_trigger;
        double shooterButton2 = gamepad2.right_trigger;
        shooter.shooterRun(shooterButton1, shooterButton2);

        boolean rampButton = gamepad1.y;
        indexer.indexerRun(rampButton);

        boolean intake1 = gamepad1.left_bumper;
        boolean intake2 = gamepad1.right_bumper;
        intake.intakeRun(intake1, intake2);

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
