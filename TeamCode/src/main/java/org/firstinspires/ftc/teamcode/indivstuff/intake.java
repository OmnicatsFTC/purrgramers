//vivian 9/26/26 intake!

package org.firstinspires.ftc.teamcode.indivstuff;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class intake {
    //makes the motor
    private DcMotor intakeMotor;


    //sets the motor direction, name, and sets the run mode (encoder)
    public void init(HardwareMap hwMap) {
        intakeMotor = hwMap.get(DcMotor.class, "intakeMotor");
        intakeMotor.setDirection(DcMotor.Direction.REVERSE);
        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    //method for actually running the code, sets the motor speed to different values
    //depending on which bumper was pressed
    public void intakeRun(){
            if (gamepad1.leftBumperWasPressed()){
                intakeMotor.setPower(1);
            } else if (gamepad1.rightBumperWasPressed()) {
                intakeMotor.setPower(-1);
            } else if (gamepad1.bWasPressed()) {
                intakeMotor.setPower(0);
            }
        }
    }

