package cp.ftc.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import cp.ftc.HolonomicRobot;
import cp.ftc.Controller;

@TeleOp(name = "Holonomic Drive", group = "Drive")
public class HolonomicDrive extends OpMode {
    private HolonomicRobot robot;
    private boolean fieldCentric = true;
    private double upHeading;
    private double currentHeading;
    private Controller controller;
    private Telemetry.Item fieldCentricTelemetryItem;

    @Override
    public void init_loop() {
        telemetry.update();
        controller.update();
        if (controller.aOnce()) {
            fieldCentric = !fieldCentric;
        }
    }

    @Override
    public void init() {
        controller = new Controller(gamepad1);
        robot = new HolonomicRobot(hardwareMap, telemetry);
        robot.init();
        robot.setupPowerTelemetry();
        fieldCentricTelemetryItem = telemetry.addData("field centric [a]?", () -> {
           return fieldCentric ? "Yes" : "No.";
        });
    }

    @Override
    public void start() {
        telemetry.removeItem(fieldCentricTelemetryItem);
        telemetry.addData("Heading", () ->
            Math.toDegrees(currentHeading = robot.getCurrentHeading())
        );
        if (fieldCentric) {
            telemetry.addData("Up Heading", () ->
                 Math.toDegrees(upHeading)
            );
        }
        upHeading = robot.getCurrentHeading();
    }

    @Override
    public void stop() {
        super.stop();
        robot.setMotorPower(0.0f, 0.0f);
    }

    @Override
    public void loop() {
        controller.update();
        telemetry.update();
        float x = controller.leftStickX();
        float y = - controller.leftStickY();
        float rotation = controller.rightStickX();
        float angle = (float) Math.atan2(x, y);
        if (fieldCentric) {
            angle += (currentHeading - upHeading);
        }
        float speed = (float) Math.min(1.0, Math.sqrt(x * x + y * y));
        if (controller.leftBumper()) {
            speed /= 2.0f;
            rotation /= 2.0f;
        }
        if (controller.rightBumper()) {
            speed /= 2.0f;
            rotation /= 2.0f;
        }
        robot.drive(angle, speed, rotation);

        if (gamepad1.x) {
            robot.resetUpHeading();
        }
    }
}
