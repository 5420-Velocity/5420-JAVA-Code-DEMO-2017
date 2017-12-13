package org.usfirst.frc.team5420.robot;

import edu.wpi.first.wpilibj.SpeedController;

public class MecDrive {
	private boolean isSetup = false;
	private SpeedController leftFront;
	private SpeedController rightFront;
	private SpeedController leftRear;
	private SpeedController rightRear;
	
	public MecDrive(){
		
	}

	/*
	 * Mec Drive Init
	 * 
	 * @param inFL Left Front Controller
	 * @param inRL Right Front Controller
	 * @param inLR Left Rear Controller
	 * @param inRR Right Rear Controller
	 */
	public MecDrive( SpeedController inLF, SpeedController inRF, SpeedController inLR, SpeedController inRR ){
		
		this.isSetup = true;
	}
	
	public boolean is_setup(){
		
		return this.isSetup;
	}

	public void drive(){
		
	}
	
	public void drive( int Power, int Turn, int Crab ){
		
	}
	
	public void forward(int Speed){
		
	}
}
