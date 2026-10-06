//vivian limelight code 9/27/26
//tutorial from brogan m. pratt (two videos on apriltags & lls)

package org.firstinspires.ftc.teamcode.indivstuff;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@Disabled
public class limelight extends OpMode {
    private Limelight3A limelight;
    private IMU imu;

    private final mecanum drive = new mecanum();

    double kP = 0.001;
    double error = 0;
    double lastError = 0;
    double goalx = 0;
    double angleTolerance = 0.2;
    double kd = 0.0001;
    double curTime = 0;
    double lastTime = 0;

    //driving setup
    double forward;
    double strafe;
    double rotate;

    double[] stepSizes = {1.0, 0.1, 0.001, 0.0001};
    int stepIndex = 2;


    @Override
    public void init(){
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(8); //apriltag #11 pipeline
        imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot revHubOrientationOnRobot = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT);
        imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));

        drive.init(hardwareMap);
    }

    @Override
    public void loop() {
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        limelight.updateRobotOrientation(orientation.getYaw());
        LLResult llResult = limelight.getLatestResult();
        if (llResult != null && llResult.isValid()) {
            Pose3D botPose = llResult.getBotpose_MT2();
            telemetry.addData("Tx", llResult.getTx());
            telemetry.addData("Ty", llResult.getTy());
            telemetry.addData("Ta", llResult.getTa());

            forward = gamepad1.left_stick_y;
            strafe = gamepad1.left_stick_x;
            rotate = gamepad1.right_stick_x;

            llResult.getTx();

            if (Math.abs(error)<angleTolerance){
                rotate = 0;
            } else {
                double pTerm = error * kP;

                curTime = getRuntime();
                double dT = curTime - lastTime;
                double dTerm = ((error - lastError)/dT) * kd;

                rotate = Range.clip(pTerm + dTerm, -0.4, 0.4);
                lastError = error;
                lastTime = curTime;

            }

        } else {
            lastTime = getRuntime();
            lastError = 0;
        }
    }

    public void start(){
        limelight.start();
            getRuntime();
            curTime = getRuntime();
    }

}
