
package org.usfirst.frc.team5420.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Talon;

//import edu.wpi.first.wpilibj.TalonSRX;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5420.robot.subsystems.ExampleSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;
	protected Joystick joystick1, joystick2;
	protected ADXRS450_Gyro gyroSensor;
	
	protected Encoder encoderLeft;
	protected Encoder encoderRight;
	protected MecDrive MyDrive;
	protected Talon motorFL, motorBL, motorBR, motorFR;
	public static CameraLines lines = null;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		System.out.println("Robot");
		lines = new CameraLines(); // Start the Camera with Lines Server
		oi = new OI();
		
		joystick1 = new Joystick(0);
		
		encoderRight = new Encoder(6, 7);
		encoderRight = new Encoder(9, 8);
		
		motorFL = new Talon(3);
		motorBL = new Talon(4);
		motorBR = new Talon(1);
		motorFR = new Talon(2);
		
		MyDrive = new MecDrive(motorFL, motorBL, motorBR, motorFR);
		MyDrive.gyroSensor = null;
		MyDrive.invert(true);
		MyDrive.setDeadband(0.2); // The Zone to Ignore
		MyDrive.enableDeadband();
		
		SmartDashboard.putBoolean("InitComplete", true);
	}
	
	@Override
	public void robotPeriodic(){
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		MyDrive.stop();
		joystick1.setRumble(GenericHID.RumbleType.kLeftRumble, 0 );
		joystick1.setRumble(GenericHID.RumbleType.kRightRumble, 0 );
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		//joystick1.getRawAxis(0)
		doMecDrive( 0, (-joystick1.getRawAxis(1)), joystick1.getRawAxis(4) );
		
		joystick1.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
		joystick1.setRumble(GenericHID.RumbleType.kRightRumble, 0.4);
		
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		
	}

	public void doDrive(double frValue, double lrValue){
		MyDrive.drive(frValue, lrValue, 0);
	}
	
	public double dead(double value) {
	    if(Math.abs(value) < 0.2) return 0;
	    else return value;
	}
	
	public void doMecDrive(double frValue, double lrValue, double rValue){
		MyDrive.drive(frValue, rValue, lrValue);
	}
	
	public double deadband(double joystick) {
	    if(joystick < 0.2 || (joystick)*-1 > 0.2) return 0;
	    else return joystick;
	}
	public double deadband(double joystick, double dead) {
	    if(joystick < dead || (joystick)*1 > dead) return 0;
	    else return joystick;
	}
	
}
