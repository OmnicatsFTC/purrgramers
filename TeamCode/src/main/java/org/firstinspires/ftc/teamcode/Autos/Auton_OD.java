package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.indivstuff.shooter;
import org.firstinspires.ftc.teamcode.indivstuff.ramp;
@Autonomous(name="Auto with OD", group="Linear Opmode")
public class Auton_OD extends LinearOpMode{

        shooter shooter = new shooter();

        ramp ramp = new ramp();

    public class shoot implements Action {

    }
}
