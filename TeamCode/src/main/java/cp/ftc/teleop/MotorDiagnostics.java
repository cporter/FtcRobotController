package cp.ftc.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import cp.HolonomicRobot;

@TeleOp(name = "MotorDiagnostic", group = "Diagnostic")
public class MotorDiagnostics extends OpMode {
    HolonomicRobot robot;

    @Override
    public void init() {
        robot = new HolonomicRobot(hardwareMap, telemetry);
        robot.setupEncoderTelemetry();
    }

    @Override
    public void loop() {
        telemetry.update();
        float lf = 0, rf = 0, lb = 0, rb = 0;
        if (gamepad1.a) {
            lf = gamepad1.left_stick_y;
        } else if (gamepad1.b) {
            rf = gamepad1.left_stick_y;
        } else if (gamepad1.x) {
            lb = gamepad1.left_stick_y;
        } else if (gamepad1.y) {
            rb = gamepad1.left_stick_y;
        }

        robot.setMotorPower(lf, rf, lb, rb);

        if (gamepad1.left_bumper) {
            robot.resetEncoders();
        }
    }
}
