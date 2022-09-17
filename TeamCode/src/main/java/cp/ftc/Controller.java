package cp.ftc;

import com.qualcomm.robotcore.hardware.Gamepad;

public class Controller {
    private Gamepad gamepad;
    private int x, y, a, b, dpad_up, dpad_down, dpad_left, dpad_right, r_bumper, l_bumper;

    public Controller(Gamepad _gamepad) {
        gamepad = _gamepad;
    }

    private static int incOrZero(final boolean flag, final int current) {
        return flag ? 1 + current : 0;
    }

    public void update() {
        a = incOrZero(gamepad.a, a);
        b = incOrZero(gamepad.b, b);
        x = incOrZero(gamepad.x, x);
        y = incOrZero(gamepad.y, y);
        dpad_up = incOrZero(gamepad.dpad_up, dpad_up);
        dpad_down = incOrZero(gamepad.dpad_down, dpad_down);
        dpad_left = incOrZero(gamepad.dpad_left, dpad_left);
        dpad_right = incOrZero(gamepad.dpad_right, dpad_right);
        r_bumper = incOrZero(gamepad.right_bumper, r_bumper);
        l_bumper = incOrZero(gamepad.left_bumper, l_bumper);
    }

    public boolean a() { return 0 < a; }
    public boolean b() { return 0 < b; }
    public boolean x() { return 0 < x; }
    public boolean y() { return 0 < y; }
    public boolean dpadUp() { return 0 < dpad_up; }
    public boolean dpadDown() { return 0 < dpad_down; }
    public boolean dpadLeft() { return 0 < dpad_left; }
    public boolean dpadRight() { return 0 < dpad_right; }
    public boolean rightBumper() { return 0 < r_bumper; }
    public boolean leftBumper() { return 0 < l_bumper; }
    
    public boolean aOnce() { return 1 == a; }
    public boolean bOnce() { return 1 == b; }
    public boolean xOnce() { return 1 == x; }
    public boolean yOnce() { return 1 == y; }
    public boolean dpadUpOnce() { return 1 == dpad_up; }
    public boolean dpadDownOnce() { return 1 == dpad_down; }
    public boolean dpadLeftOnce() { return 1 == dpad_left; }
    public boolean dpadRightOnce() { return 1 == dpad_right; }
    public boolean rightBumperOnce() { return 1 == r_bumper; }
    public boolean leftBumperOnce() { return 1 == l_bumper; }

    public float leftStickX() { return gamepad.left_stick_x; }
    public float leftStickY() { return gamepad.left_stick_y; }
    public float rightStickX() { return gamepad.right_stick_x; }
    public float rightStickY() { return gamepad.right_stick_y; }
    public float leftTrigger() { return gamepad.left_trigger; }
    public float rightTrigger() { return gamepad.right_trigger; }
}
