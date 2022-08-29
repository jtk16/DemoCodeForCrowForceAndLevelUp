package org.firstinspires.ftc.teamcode.OpModes.Autos;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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

    /**
     * Here is where nearly all of the code will be
     * it runs ONCE when someone clicks START on the
     * driver station
     */
    @Override
    public void runOpMode() throws InterruptedException {
        //
    }


    //this code is a nearly direct copy from the ExampleMecanumTeleOp file
    public void setMotorPowers(double x, double y, double c, double speedMultiply) {
        front_right_motor.setPower(Range.clip(y+x+c, -1, 1) * speedMultiply);
        back_right_motor.setPower(Range.clip(y-x+c, -1, 1) * speedMultiply);
        front_left_motor.setPower(Range.clip(-y+x+c, -1, 1) * speedMultiply);
        back_left_motor.setPower(Range.clip(-y-x+c, -1, 1) * speedMultiply);
    }
}
