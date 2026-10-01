//shooter code, not used in the main code yet
//issues with the gamepad

package org.firstinspires.ftc.teamcode.indivstuff;

//2500 and 3500 for shooter speeds


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.ftccommon.external.OnCreate;

public class shooter {
    private DcMotor shooter;

    public void init(HardwareMap hwMap){
        shooter = hwMap.get(DcMotor.class, "shooter");
        shooter.setDirection(DcMotorSimple.Direction.FORWARD);
        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void shooterRun(){
        if (gamepad1.leftTriggerWasPressed()){
            shooter.setPower(0.41);
        } else if(gamepad1.rightTriggerWasPressed()){
            shooter.setPower(.53);
        }
    }

}
