//vivian 9/26/26 intake!

package org.firstinspires.ftc.teamcode.indivstuff;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class intake {
    //makes the motor
    private DcMotorEx intakeMotor;
    private DcMotorEx mainIndexer;
    private CRServo smallIndexer;


    //sets the motor direction, name, and sets the run mode (encoder)
    public void init(HardwareMap hwMap) {
        intakeMotor = hwMap.get(DcMotorEx.class, "intakeMotor");
        intakeMotor.setDirection(DcMotor.Direction.REVERSE);
        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //sets up indexer dc motor
        mainIndexer = hwMap.get(DcMotorEx.class, "rampMotor");
        mainIndexer.setDirection(DcMotorSimple.Direction.FORWARD);
        mainIndexer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //sets up the servo
        smallIndexer = hwMap.get(CRServo.class, "rampServo");
        smallIndexer.setDirection(CRServo.Direction.FORWARD);
    }
    //method for actually running the code, sets the motor speed to different values
    //(either negative or postive for both directions, we don't know which is which yet)
    //depending on which bumper was pressed
    public void intakeRun(boolean button1, boolean button2, boolean button3){
            if (button2){ //normal intake and indexer
                intakeMotor.setVelocity(-1500);
                mainIndexer.setVelocity(-2000);
            } else if (!button1){ //stops
                intakeMotor.setVelocity(0);
                mainIndexer.setVelocity(0);
            } else if (button1){ //outtake and out indexer
                intakeMotor.setVelocity(1500);
                mainIndexer.setVelocity(2000);
            } else if (!button2) { //stops
                intakeMotor.setVelocity(0);
                mainIndexer.setVelocity(0);
            } else if (button3){ //servo go
                smallIndexer.setPower(1);
            } else if (!button3){ //servo stop
                smallIndexer.setPower(0);
            }
    }
    }


