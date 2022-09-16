package cp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Holonomic Drive")
public class HolonomicDrive extends OpMode {
    private HolonomicRobot robot;

    @Override
    public void init() {
        robot = new HolonomicRobot(hardwareMap, telemetry);
        robot.init();
    }

    @Override
    public void stop() {
        super.stop();
        robot.setMotorPower(0.0f, 0.0f);
    }

    @Override
    public void loop() {
        float x = gamepad1.left_stick_x;
        float y = - gamepad1.left_stick_y;
        float rotation = gamepad1.right_stick_x;
        float angle = (float) Math.atan2(x, y);
        float speed = (float) Math.min(1.0, Math.sqrt(x * x + y * y));
        robot.drive(angle, speed, rotation);

        if (gamepad1.x) {
            robot.resetUpHeading();
        }
    }
}
