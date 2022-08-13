package cp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Tank Drive")
public class TankDrive extends OpMode {
    private DcMotor lf, rf, lb, rb;

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
        lf.setPower(0.0);
        rf.setPower(0.0);
        lb.setPower(0.0);
        rb.setPower(0.0);
    }

    @Override
    public void init() {
        lf = hardwareMap.dcMotor.get("lf");
        rf = hardwareMap.dcMotor.get("rf");
        lb = hardwareMap.dcMotor.get("lb");
        rb = hardwareMap.dcMotor.get("rb");
    }

    @Override
    public void loop() {
        lf.setPower(gamepad1.left_stick_y);
        lb.setPower(gamepad1.left_stick_y);
        rf.setPower(gamepad1.right_stick_y);
        rb.setPower(gamepad1.right_stick_y);
    }
}
