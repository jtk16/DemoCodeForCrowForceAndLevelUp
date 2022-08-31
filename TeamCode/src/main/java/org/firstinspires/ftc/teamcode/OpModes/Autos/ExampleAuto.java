package org.firstinspires.ftc.teamcode.OpModes.Autos;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;


/**
 * IMPORTANT:
 * every auto you write needs to be marked with this "annotation" otherwise it will not show up on
 * the phone.
 * the name parameter is of course how the program will appear on the menu, op modes with the same
 * "group" will appear next to each other in the menu
 *
 * IT IS ASSUMED THAT YOU UNDERSTAND THE CODE WRITTEN IN ExampleMecanumTeleOp PREVIOUS TO
 * READING OR MODIFYING THIS CODE.
 *
 * this is a simple autonomous, as was the TeleOp, so there are many ways to make your programs more efficient
 * and complex
 *
 * IF YOU NEED HELP OR HAVE ANY QUESTIONS MESSAGE JACK (1zze#9694) ON DISCORD WITH ANY QUESTIONS
 */
// I believe it is simplest to extend a LinearOpMode, however, you can do this with any type of OpMode
public class ExampleAuto extends LinearOpMode {
    /**
     * Here is where you declare the variables that you want to be accessible from ANYWHERE in the file.
     * this will include motors, servos, positions, powers, or any setting that you want
     * to be readily accessible
     */
    public DcMotor front_left_motor;
    public DcMotor front_right_motor;
    public DcMotor back_left_motor;
    public DcMotor back_right_motor;

    public ColorSensor sensor;

    /**
     * Here is where nearly all of the code will be
     * it runs ONCE when someone clicks START on the
     * driver station
     */
    @Override
    public void runOpMode() throws InterruptedException {
        //this section of the method is where we declare and initialize all of our hardware and sensors
        front_left_motor = hardwareMap.dcMotor.get("flm");
        front_right_motor = hardwareMap.dcMotor.get("frm");
        back_left_motor = hardwareMap.dcMotor.get("blm");
        back_right_motor = hardwareMap.dcMotor.get("brm");

        sensor = hardwareMap.colorSensor.get("sensor");

        // once everything is initialized, call the waitForStart() method
        // the code will continue, of course, when start is clicked
        waitForStart();

        // we can now begin to program our autonomous
        // there are ways to program an autonomous where you move the bot
        // set distances, however, that takes a lot of math, if you want me
        // to teach you, you can of course reach out.
        // for the sake of convenience ill use set time instead of set distance.
        // to wait a set amount of time, use the sleep(millis) method to wait
        // however many specified milliseconds

        //drive forwards for 2 seconds at 75% power
        setMotorPowers(0, 1, 0, 0.75);
        sleep(2000);

        //start turning clockwise at 40% power for 1 second
        setMotorPowers(0, 0, 1, 0.4);
        sleep(1000);

        double threshold = 50;
        //move forward UNTIL the robot detects some amount of red;
        while (sensor.red() < threshold) {
            setMotorPowers(0, 1, 0, 0.5);
        }

        //remain still until the auto is done
        setMotorPowers(0, 0, 0, 0);
    }


    // this code is a nearly direct copy from the ExampleMecanumTeleOp file
    // the parameters do depend on your robot but this is what worked for mine.
    public void setMotorPowers(double x, double y, double c, double speedMultiply) {
        front_right_motor.setPower(Range.clip(y+x+c, -1, 1) * speedMultiply);
        back_right_motor.setPower(Range.clip(y-x+c, -1, 1) * speedMultiply);
        front_left_motor.setPower(Range.clip(-y+x+c, -1, 1) * speedMultiply);
        back_left_motor.setPower(Range.clip(-y-x+c, -1, 1) * speedMultiply);
    }
}
