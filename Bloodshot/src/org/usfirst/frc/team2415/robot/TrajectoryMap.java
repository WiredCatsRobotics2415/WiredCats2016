package org.usfirst.frc.team2415.robot;

import java.awt.Color;

import org.usfirst.frc.team2415.robot.utilities.FalconPathPlanner;
import org.usfirst.frc.team2415.robot.utilities.FalconLinePlot;

public class TrajectoryMap {
	
	public static void main(String[] args){
		/***Poof Example***/

		double[][] CheesyPath = new double[][]{
		    {1, 1},
		    {5, 1},
		    {9, 12},
		    {12, 9},
		    {15, 6},
		    {19, 12}
		}; 
		
		//Lets create a bank image
        

        //force graph to show 1/2 field dimensions of 24.8ft x 27 feet
        double fieldWidth = 32.0;


        //lets add field markers to help visual
        //http://www.usfirst.org/sites/default/files/uploadedFiles/Robotics_Programs/FRC/Game_and_Season__Info/2014/fe-00037_RevB.pdf
        //Goal line
        double[][] goalLine = new double[][] {{26.5,0}, {26.5, fieldWidth}};

        //Low Goals roughly 33 inch x 33 inch and 24.6 ft apart (inside to inside)
        double[][] leftLowGoal = new double[][]{
                {26.5, fieldWidth/2 + 24.6/2},
                {26.5, (fieldWidth)/2 + 24.6/2 + 2.75},
                {26.5 - 2.75, fieldWidth/2 + 24.6/2 + 2.75},
                {26.5 - 2.75, fieldWidth/2 + 24.6/2},
                {26.5, fieldWidth/2 + 24.6/2},
        };

        double[][] rightLowGoal = new double[][]{
                {26.5, fieldWidth/2 - 24.6/2},
                {26.5, fieldWidth/2 - 24.6/2 - 2.75},
                {26.5 - 2.75, fieldWidth/2 - 24.6/2 - 2.75},
                {26.5 - 2.75, fieldWidth/2 - 24.6/2},
                {26.5, fieldWidth/2 - 24.6/2},
        };


        //Auto Line
        double[][] autoLine = new double[][] {{26.5-18,0}, {26.5-18, fieldWidth}};
        
        

        double totalTime = 10; //seconds
        double timeStep = 0.2; //period of control loop on Rio, seconds
        double robotTrackWidth = 2; //distance between left and right wheels, feet

        final FalconPathPlanner path = new FalconPathPlanner(CheesyPath);
        path.calculate(totalTime, timeStep, robotTrackWidth);

        //waypoint path

        //add all other paths


        //Velocity
        
        FalconLinePlot fig3 = new FalconLinePlot(new double[][] {{0,0}});
        fig3.yGridOn();
        fig3.xGridOn();
        fig3.setYLabel("Y (feet)");
        fig3.setXLabel("X (feet)");
        fig3.setTitle("Top Down View of FRC Field (30ft x 27ft) \n shows global position of robot path, along with left and right wheel trajectories");
        fig3.setXTic(0, 27, 1);
        fig3.setYTic(0, fieldWidth, 1);
        fig3.addData(goalLine, Color.black);
        fig3.addData(leftLowGoal, Color.black);
        fig3.addData(rightLowGoal, Color.black);
        fig3.addData(autoLine, Color.black);
        fig3.addData(path.nodeOnlyPath,Color.blue,Color.green);
        fig3.addData(path.smoothPath, Color.red, Color.blue);
        fig3.addData(path.leftPath, Color.cyan);
        fig3.addData(path.rightPath, Color.magenta);
        
        FalconLinePlot fig5 = new FalconLinePlot(new double[][] {{0,0}});
        fig5.yGridOn();
        fig5.xGridOn();
        fig5.setYLabel("Y (feet)");
        fig5.setXLabel("X (feet)");
        fig5.setTitle("Top Down View of FRC Field (30ft x 27ft) \n shows global position of robot path, along with left and right wheel trajectories");
        fig5.setXTic(0, 27, 1);
        fig5.setYTic(0, fieldWidth, 1);
        fig5.addData(goalLine, Color.black);
        fig5.addData(leftLowGoal, Color.black);
        fig5.addData(rightLowGoal, Color.black);
        fig5.addData(autoLine, Color.black);
        fig5.addData(path.nodeOnlyPath,Color.blue,Color.green);
        fig5.addData(path.smoothPath, Color.red, Color.blue);
        fig5.addData(path.leftPath, Color.cyan);
        fig5.addData(path.rightPath, Color.magenta);
        

        FalconLinePlot fig4 = new FalconLinePlot(path.smoothCenterVelocity,null,Color.blue);
        fig4.yGridOn();
        fig4.xGridOn();
        fig4.setYLabel("Velocity (ft/sec)");
        fig4.setXLabel("time (seconds)");
        fig4.setTitle("Velocity Profile for Left and Right Wheels \n Left = Cyan, Right = Magenta");
        fig4.addData(path.smoothRightVelocity, Color.magenta);
        fig4.addData(path.smoothLeftVelocity, Color.cyan);
        

        for(double step[] : path.smoothCenterVelocity){
        	System.out.println(step[1]);
        }
        
	}

}
