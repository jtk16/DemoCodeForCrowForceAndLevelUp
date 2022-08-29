package org.firstinspires.ftc.teamcode.OpModes.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * IMPORTANT:
 * every teleop you write needs to be marked with this "annotation" otherwise it will not show up on
 * the phone.
 * the name parameter is of course how the program will appear on the menu, op modes with the same
 * "group" will appear next to each other in the menu
 */

@TeleOp(name="demo teleop", group="tutorial")
//this may extend any type of OpMode, however, the code will differentiate slightly.
public class ExampleMecanumTeleOp extends OpMode {
    /**
     * Here is where you declare the variables that you want to be accessible from ANYWHERE in the file.
     * this will include motors, servos, positions, powers, or any setting that you want
     * to be readily accessible
     */
    public DcMotor front_left_motor;
    public DcMotor front_right_motor;
    public DcMotor back_left_motor;
    public DcMotor back_right_motor;


    //These will be used in loop() to create a servo that toggles open and closed when a is pressed
    public Servo some_latch;
    public boolean wasAHeldLastFrame = false;
    public boolean latchServoLocked = false;

    public double latchServoLockedPos = 0.75;
    public double latchServoDisengagedPos = 0;

    //the percentage that the drive train will run at, used in loop()
    public double speedMultiply = 0.75;

    /**
     * the code in this method runs ONCE when the driver clicks "init" use this method to declare variables
     * dependant on sensors or too set servos or any systems into the correct position, if you do so the
     * components that move WILL NEED TO BE LABELED with a "warning: moves on initialization" sticker placed
     * physically on the robot
     *
     * THIS IS WHERE YOU DECLARE ALL OF YOUR HARDWARE COMPONENTS
     */
    @Override
    public void init() {
        // hardwareMap is how you get the hardware components declared in the code
        // for most hardware components there will be a method for the component called
        // hardwareMap.COMPOMENT_NAME.get("CONFIG_NAME") as seen below, however,
        // sometimes, usually in the case of sensors, you will need to do
        // hardwareMap.get(COMPONENT_NAME.class, "CONFIG_NAME");
        front_left_motor = hardwareMap.dcMotor.get("flm");
        front_right_motor = hardwareMap.dcMotor.get("frm");
        back_left_motor = hardwareMap.dcMotor.get("blm");
        back_right_motor = hardwareMap.dcMotor.get("brm");

        some_latch = hardwareMap.servo.get("servo_name");

        // you may need to or want to flip the directions of two of your motors, if you wish to do so
        // the code is:
        // front_right_motor.setDirection(DcMotorSimple.Direction.REVERSE);

        // you can also change how a motor behaves when it is recieving a power of zero
        // front_right_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // front_right_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        // brake can make AUTONOMOUS more accurate but either work. i find float to be more
        // comfortable when driving.
    }

    /**
     * this code will run ONCE when the driver clicks "start" this method is often used similar to init()
     * for chunks of code that depend on the robot already being initialized. components that are moved in this method
     * do NOT need to be labeled with a warning sticker.
     */
    @Override
    public void start() {

    }

    /**
     * code written in this section will run ONCE when the opMode ends, I have yet to find a use for this method.
     */
    @Override
    public void stop() {

    }

    /**
     * MOST IMPORTANT METHOD
     * the code written in this section will be run once EVERY FRAME once the driver clicks the start button.
     * Most of the code will be written in this section
     */
    @Override
    public void loop() {
        // this section is dependant on the orientations of your motors and drive train
        // so it will require tuning. this is the code for my current program.
        // when orienting your mecanum drive train, the front two wheels should be directed forward
        // and away form each other looking like \/
        // on the back half, they will be facing forwards and inwards like this /\

        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;

        double c = gamepad1.right_stick_x;

        // unfortunatley, I don't have a concise explanation for how this works, if this doesn't work
        // for your robot check the diagram on this website:
        // https://en.wikipedia.org/wiki/Mecanum_wheel#/media/File:Mecanum_wheel_control_principle.svg
        // if it is VERY much not working, check that your wheels match the orientation of the wheels
        // that are described above or shown in the image

        front_right_motor.setPower(Range.clip(y+x+c, -1, 1) * speedMultiply);
        back_right_motor.setPower(Range.clip(y-x+c, -1, 1) * speedMultiply);
        front_left_motor.setPower(Range.clip(-y+x+c, -1, 1) * speedMultiply);
        back_left_motor.setPower(Range.clip(-y-x+c, -1, 1) * speedMultiply);


        /**
         * BELOW IS THE CODE FOR HOW TO MAKE A SERVO TOGGLE STATES WHEN YOU CLICK A BUTTON
         */

        // The reason that we use toggles is so the state of a servo doesnt rapidly switch back and forth
        // when we hold a button down, it is very difficult to hold down a button for exactly one frame
        // so we use toggles to make the state change on the first frame the button was pressed only

        // first we make sure that the button was NOT held LAST frame, but it is being held THIS frame
        // if these conditions are met, the state of the toggle will switch
        if (!wasAHeldLastFrame && gamepad1.a) {
            latchServoLocked = !latchServoLocked;
        }

        // THIS SECTION OF CODE MUST BE WRITTEN AFTER THE FIRST SECTION, otherwise, wasAHeldLastFrame
        // will always hold a value of "true" when A is pressed, therefore, the toggle will never switch states
        // the code for this section is self explanatory
        if (gamepad1.a) {
            wasAHeldLastFrame = true;
        }
        else {
            wasAHeldLastFrame = false;
        }

    }


}
