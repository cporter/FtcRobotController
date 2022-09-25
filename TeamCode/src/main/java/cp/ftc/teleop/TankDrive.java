package cp.ftc.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import cp.Robot;

@TeleOp(name = "Tank Drive", group = "Drive")
public class TankDrive extends OpMode {
    private Robot robot;

    @Override
    public void stop() {
        super.stop();
        robot.setMotorPower(0.0f, 0.0f);
    }

    @Override
    public void init() {
        robot = new Robot(hardwareMap, telemetry);
        robot.init();
    }

    @Override
    public void loop() {
        robot.setMotorPower(- gamepad1.left_stick_y, - gamepad1.right_stick_y);
    }
}
