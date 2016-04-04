package org.usfirst.frc.team2415.robot.autocommands;

import java.util.ArrayList;
import java.util.Iterator;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team2415.robot.PID;
import org.usfirst.frc.team2415.robot.Robot;

import com.ni.vision.NIVision;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FindTowerAutonomousCommand extends Command {
	
	//these are the color scalars that limit what colors the processing looks for
	//it goes from around gray to around lime green
	public static final Scalar LOWER_BOUNDS = new Scalar(60,120,0),
			UPPER_BOUNDS = new Scalar(190,255,60);
	
	public static final Scalar BLACK = new Scalar(0,0,0);
	
	public static Mat matOriginal, matHSV, matThresh, clusters, matHeirarchy, structElem;
	
	public static double distance;
	
	public static boolean IS_RUNNING = true;
	
	public static final Size RESIZE = new Size(320,240);
	
	public static final double CENTERX = 160; //X value of the center of the screen
	public static final double CENTERY = 120; //Y value of the center of the screen
	
	public static final double SHOOTER_X = 235; //This is the X coordinate of the camera we will be able to shoot at
	public static final double SHOOTER_Y = 93; //This is the Y coordinate of the camera we will be able to shoot at
	
	public static final double VERTICAL_FOV = 34.3; //Vertical FOV of the cam
	public static final double HORIZONTAL_FOV = 61; //Horizontal FOV of the cam
	
	public static final double CAMERA_ANGLE = 20; //Value at which the camera is angled
		
//	the height to the center of the goal in first stronghold is 97 inches	
	public static final int TOP_TARGET_HEIGHT = 97;
//	the physical height of the camera lens
	public static final int TOP_CAMERA_HEIGHT = 7;	//FILL THIS IN WE DON'T KNOW YET

	public static double targetX, targetY;
	
	public static boolean turnDone = false;
	public static boolean isDone = false;
	public static boolean stage;
	
	PID pid;
	
    public FindTowerAutonomousCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	pid = new PID(.1, 0, 0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	matOriginal = new Mat();
		matHSV = new Mat();
		matThresh = new Mat();
		clusters = new Mat();
		matHeirarchy = new Mat();
		
		structElem = Imgproc.getStructuringElement(Imgproc.CV_SHAPE_ELLIPSE, new Size(5,5));
		
		double distance, forwardDist, azimuth, distX;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//save the image to a file
    	
    	//targetCam.getImage(img);
    	
//		This thing takes time to open
    	
    	ArrayList<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		double distance, forwardDist, azimuth, distX, distY;
//		frame counter
		int FrameCount = 0;
		long before = System.currentTimeMillis();
//		only run for the specified time

			contours.clear();
			
//			open the file
			matOriginal = Highgui.imread("/home/lvuser/idontknow.jpg", Highgui.CV_LOAD_IMAGE_ANYCOLOR);
			
//			captures from a static file for testing
//			matOriginal = Imgcodecs.imread("someFile.png");

			//changes from BGR to HSV; easier to deal with
//			Imgproc.cvtColor(matOriginal,matHSV,Imgproc.COLOR_RGB2HSV);			
			Core.inRange(matOriginal, LOWER_BOUNDS, UPPER_BOUNDS, matThresh);
			
			Imgproc.dilate(matThresh, matThresh, structElem);
			Imgproc.dilate(matThresh, matThresh, structElem);
			Imgproc.dilate(matThresh, matThresh, structElem);
			Imgproc.erode(matThresh, matThresh, structElem);
			Imgproc.erode(matThresh, matThresh, structElem);
			//Imgproc.erode(matThresh, matThresh, structElem);

			Highgui.imwrite("/home/lvuser/bin.jpg", matThresh);

			
			Imgproc.findContours(matThresh, contours, matHeirarchy, Imgproc.RETR_EXTERNAL, 
					Imgproc.CHAIN_APPROX_SIMPLE);
			System.out.println("we have the contours");
			System.out.println(contours.size());
//			make sure the contours that are detected are at least 20x20 
//			pixels with an area of 400 and an aspect ration greater then 1
			for (Iterator<MatOfPoint> iterator = contours.iterator(); iterator.hasNext();) {
				MatOfPoint matOfPoint = (MatOfPoint) iterator.next();
				Rect rec = Imgproc.boundingRect(matOfPoint);
				
					if(rec.height < 25 || rec.width < 25){
						iterator.remove();
					continue;
					}
					
					float aspect = (float)rec.width/(float)rec.height;
					if(aspect < 1){
						iterator.remove();
						System.out.println("removed again nerd " + contours.size());
					}
				}
				for(MatOfPoint mop : contours){
					Rect rec = Imgproc.boundingRect(mop);
//					Imgproc.rectangle(matOriginal, rec.br(), rec.tl(), BLACK);
			}
//			if there is only 1 target, then we have found the target we want
			System.out.println(contours.size());
			if(contours.size() == 1){
								
				Rect rec = Imgproc.boundingRect(contours.get(0));
				
				targetY = rec.br().y + rec.height / 2;
				//targetY is the Y coordinate of the center of the goal
				targetX = rec.tl().x + rec.width / 2;
				//targetX is the X coordinate of the center of the goal
				
				System.out.println("Bottom Right Y: " + rec.br().y + ", " + "Bottom Right X: " + rec.br().x);
				//targetY = ((2 * (targetY / matOriginal.height())) - 1);
				
//				angle to target...would not rely on this
				
				
//				drawing info on target
				Point center = new Point(rec.br().x-rec.width / 2 - 15,rec.br().y - rec.height / 2);
				Point centerw = new Point(rec.br().x-rec.width / 2 - 15,rec.br().y - rec.height / 2 - 20);
		        
				distX = targetX - SHOOTER_X;
				distY = targetY - SHOOTER_Y;
				
//				drawing info on target
				System.out.println("Desired X: " + targetX + ", " + "Desired Y: " + targetY);
				System.out.println("DistY: " + distY + ", " + "DistX: " + distX);
			
				if(Math.abs(distX) <= 5 && Math.abs(distY) <= 6){
					System.out.println("VICTORY");
					isDone = true;
				}
				else if(Math.abs(distY) > 6){// && Math.abs(distX) <= 5){
					//if (stage == true) {Robot.driveSubsystem.setMotors(0, 0);}
			        stage = false;
			        System.out.println("moving");
				}
				else if(Math.abs(distY) <= 6){
					stage = true;
				}
				else if(Math.abs(distX) <= 5){
					stage = false;
				}
				else if(Math.abs(distX) > 5){// && Math.abs(distY) <= 2){
					if (stage == false) {Robot.driveSubsystem.setMotors(0, 0);
						long curr = System.currentTimeMillis();
						while ((System.currentTimeMillis()-curr)/1000 < .25){}
					}
	        		stage = true;
	        		System.out.println("turning");
				}
				
				/*
				else if(Math.abs(distX) > 5 && Math.abs(distY) > 2){
					if (stage == false) {Robot.driveSubsystem.setMotors(0, 0);}
					stage = true;
			        System.out.println("turning");
				}
				*/
				

//		        double turnPower = pid.pidOut(distX);
//		        double straightPower = pid.pidOut(distY);
//		        if(Math.abs(turnPower) > .4) turnPower = ((turnPower > 0) ? 1:-1) * .4;
//		        if(Math.abs(straightPower) > .2) straightPower = ((straightPower > 0) ? 1:-1) * .2;
//		        if(stage){
//		        	Robot.driveSubsystem.setMotors(turnPower, turnPower);
//		        }
//		        else if(!stage){
//		        	Robot.driveSubsystem.setMotors(straightPower, -straightPower);
//		        }
//		        
		    
			}
			
//			output an image for debugging
//			Highgui.imwrite("output.png", matOriginal);
		
	}
    	

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
