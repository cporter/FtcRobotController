package cp;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class HolonomicRobot extends Robot {
    public enum DirectionMode {
        FIELD_RELATIVE, ABSOLUTE
    }

    private DirectionMode directionMode = DirectionMode.ABSOLUTE;
    private float upHeading;

    public void resetUpHeading() {
        upHeading = getCurrentHeading();
    }

    public void setDirectionMode(DirectionMode directionMode) {
        this.directionMode = directionMode;
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void start() {
        super.start();
        resetUpHeading();
    }

    public HolonomicRobot(HardwareMap hardwareMap, Telemetry telemetry) {
        super(hardwareMap, telemetry);
    }

    public void drive(float angleRadians, float speed, float rotation) {
        if (directionMode == DirectionMode.FIELD_RELATIVE) {
            angleRadians -= upHeading;
        }
        float s = (float) Math.sin(angleRadians + Math.PI / 4.0);
        float c = (float) Math.cos(angleRadians + Math.PI / 4.0);
        float scale = absmax(s, c);
        s /= scale;
        c /= scale;

        setMotorPower(
                (s * speed) + rotation,
                (c * speed) - rotation,
                (c * speed) + rotation,
                (s * speed) - rotation
        );
    }
}
