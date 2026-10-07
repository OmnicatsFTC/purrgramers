package org.firstinspires.ftc.teamcode.indivstuff;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ramp {
    private DcMotor mainIndexer;
    private CRServo smallIndexer;

    public void init(HardwareMap hwMap){
        //sets up indexer dc motor
        mainIndexer = hwMap.get(DcMotor.class, "rampMotor");
        mainIndexer.setDirection(DcMotorSimple.Direction.FORWARD);
        mainIndexer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //sets up the servo
        smallIndexer = hwMap.get(CRServo.class, "rampServo");
        smallIndexer.setDirection(CRServo.Direction.FORWARD);
    }
    public void indexerRun(boolean button){
        if(button == true){
            smallIndexer.setPower(1);
            mainIndexer.setPower(1);
        } else if (button == false) {
            smallIndexer.setPower(0);
            mainIndexer.setPower(0);
        }
    }
}
