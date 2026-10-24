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

    public void init(HardwareMap hwMap){
        shooter = hwMap.get(DcMotorEx.class, "shooter");
        shooter.setDirection(DcMotorSimple.Direction.FORWARD);
        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    boolean shooterRun = false;
    boolean lastPressed = false;
    public void shooterRun(boolean button1){
        boolean shootertrue = button1;

        if (shootertrue && !lastPressed) {
            shootertrue = !shootertrue;
        }
        lastPressed = shootertrue;

        if (shooterRun) {
            shooter.setVelocity(1850);
        } else {
            shooter.setVelocity(0);
        }
        }

    }


