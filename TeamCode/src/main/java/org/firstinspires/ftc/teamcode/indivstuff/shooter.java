//shooter code, not used in the main code yet
//issues with the gamepad

package org.firstinspires.ftc.teamcode.indivstuff;

//2500 and 3500 for shooter speeds


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.ftccommon.external.OnCreate;

public class shooter {
    private DcMotorEx shooter;
    private DcMotorEx mainIndexer;
    private CRServo smallIndexer;

    public void init(HardwareMap hwMap){
        shooter = hwMap.get(DcMotorEx.class, "shooter");
        shooter.setDirection(DcMotorSimple.Direction.FORWARD);
        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //sets up indexer dc motor
        mainIndexer = hwMap.get(DcMotorEx.class, "rampMotor");
        mainIndexer.setDirection(DcMotorSimple.Direction.FORWARD);
        mainIndexer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //sets up the servo
        smallIndexer = hwMap.get(CRServo.class, "rampServo");
        smallIndexer.setDirection(CRServo.Direction.FORWARD);
    }

    public void shooterRun(double button1, double button2, boolean button){
        if (button1 > 0){
            shooter.setVelocity(1850);
        } else if(button1 <= 0){
            shooter.setVelocity(0);
        }

        if(button == true){
            smallIndexer.setPower(1);
            mainIndexer.setVelocity(-2000);
        } else if (button == false) {
            smallIndexer.setPower(0);
            mainIndexer.setPower(0);
        }
    }

}
