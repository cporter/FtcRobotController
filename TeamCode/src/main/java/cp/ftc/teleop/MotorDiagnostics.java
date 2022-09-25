package cp.ftc.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import cp.HolonomicRobot;
import cp.ftc.Controller;

@TeleOp(name = "MotorDiagnostic", group = "Diagnostic")
public class MotorDiagnostics extends OpMode {
    HolonomicRobot robot;
    Controller controller;

    @Override
    public void init() {
        robot = new HolonomicRobot(hardwareMap, telemetry);
        robot.setupEncoderTelemetry();
        robot.setMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
        controller = new Controller(gamepad1);
    }

    @Override
    public void loop() {
        controller.update();
        telemetry.update();
        float lf = 0, rf = 0, lb = 0, rb = 0;
        if (controller.a()) {
            lf = controller.leftStickY();
        } else if (controller.b()) {
            rf = controller.leftStickY();
        } else if (controller.x()) {
            lb = controller.leftStickY();
        } else if (controller.y()) {
            rb = controller.leftStickY();
        } else if (controller.dpadUp()) {
            lf = lb = rf = rb = .5f;
        } else if (controller.dpadDown()) {
            lf = lb = rf = rb = -.5f;
        }

        robot.setMotorPower(lf, rf, lb, rb);

        if (controller.leftBumperOnce()) {
            robot.resetEncoders();
        }
    }
}
