//vivian 9/26/26 intake!

package org.firstinspires.ftc.teamcode.indivstuff;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class intake {
    //makes the motor
    private DcMotorEx intakeMotor;


    //sets the motor direction, name, and sets the run mode (encoder)
    public void init(HardwareMap hwMap) {
        intakeMotor = hwMap.get(DcMotorEx.class, "intakeMotor");
        intakeMotor.setDirection(DcMotor.Direction.REVERSE);
        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    //method for actually running the code, sets the motor speed to different values
    //(either negative or postive for both directions, we don't know which is which yet)
    //depending on which bumper was pressed
    public void intakeRun(boolean button1, boolean button2){
            if (button1){
                intakeMotor.setVelocity(1000);
            } else if (button2) {
                intakeMotor.setVelocity(-1000);
            } else if (!button1) {
                intakeMotor.setVelocity(0);
            } else if (!button2) {
                intakeMotor.setVelocity(0);
            }
        }
    }

