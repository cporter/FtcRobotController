package cp;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class Robot {
    private HardwareMap hardwareMap;
    private Telemetry telemetry;
    private DcMotor lf, lb, rf, rb;
    private BNO055IMU imu;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        init();
    }

    public void setupEncoderTelemetry() {
        telemetry.addData("lf:encoder", () -> {
           return lf.getCurrentPosition();
        });
        telemetry.addData("lb:encoder", () -> {
            return lb.getCurrentPosition();
        });
        telemetry.addData("rf:encoder", () -> {
            return rf.getCurrentPosition();
        });
        telemetry.addData("rb:encoder", () -> {
           return rb.getCurrentPosition();
        });
    }

    public void setupPowerTelemetry () {
        telemetry.addData("lf power", () -> lf.getPower());
        telemetry.addData("lb power", () -> lb.getPower());
        telemetry.addData("rf power", () -> rf.getPower());
        telemetry.addData("rb power", () -> rb.getPower());
    }

    public void setMotorMode(DcMotor.RunMode mode) {
        lf.setMode(mode);
        rf.setMode(mode);
        lb.setMode(mode);
        rb.setMode(mode);
    }

    public void resetEncoders() {
        setMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setMotorPower(float left, float right) {
        setMotorPower(left, right, left, right);
    }

    protected float absmax(float... xs) {
        float ret = 0.0f;
        for(float x : xs) {
            ret = Math.max(ret, Math.abs(x));
        }
        return ret;
    }

    public void setMotorPower(float _lf, float _rf, float _lb, float _rb) {
        float scale = absmax(1.0f, _lf, _rf, _lb, _rb);
        lf.setPower(_lf / scale);
        rf.setPower(_rf / scale);
        lb.setPower(_lb / scale);
        rb.setPower(_rb / scale);
    }

    public float getCurrentHeading() {
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
        return angles.firstAngle;
    }

    public void start() {}

    public void init() {
        lf = hardwareMap.dcMotor.get("lf");
        rf = hardwareMap.dcMotor.get("rf");
        lb = hardwareMap.dcMotor.get("lb");
        rb = hardwareMap.dcMotor.get("rb");

        setMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);

        lf.setDirection(DcMotorSimple.Direction.REVERSE);
        lb.setDirection(DcMotorSimple.Direction.REVERSE);

        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.RADIANS;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
    }
}
