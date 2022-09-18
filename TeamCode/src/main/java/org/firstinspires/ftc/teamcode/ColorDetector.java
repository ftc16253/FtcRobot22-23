package org.firstinspires.ftc.teamcode;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class ColorDetector extends OpenCvPipeline {
    private Mat workingMatrix = new Mat();
    public String position = "";
    public double leftTotal, centerTotal, rightTotal;
    public ColorDetector(){

    }

    public final  Mat processFrame(Mat input){
        input.copyTo(workingMatrix);
        if (workingMatrix.empty()){
            return input;
        }
        Imgproc.cvtColor(workingMatrix, workingMatrix, Imgproc.COLOR_RGB2YCrCb);

        //Mat matCenter = workingMatrix.submat(75, 140, 100, 140);
        Mat matCenter = workingMatrix.submat(80, 135, 105, 135);

        Imgproc.rectangle(workingMatrix, new Rect(105, 80, 30, 55), new Scalar(0,255,0));
        //Imgproc.rectangle(workingMatrix, new Rect(100,75, 40, 65), new Scalar(0,255,0));
        //Imgproc.rectangle(workingMatrix, new Rect(205,100, 50, 50), new Scalar(0,255,0));
        //Imgproc.rectangle(workingMatrix, new Rect(150,120, 40, 30), new Scalar(0,255,0));


        //leftTotal = Core.sumElems(matLeft).val[2];
        centerTotal = Core.sumElems(matCenter).val[2];
        //rightTotal = Core.sumElems(matRight).val[2];

        /*
        if (leftTotal < centerTotal){
            //left is Duck
            position = "LEFT";
        } else {
            if (leftTotal - centerTotal > 10000.0){
                //center is Duck
                position = "CENTER";
            } else {
                //right is Duck
                position = "RIGHT";
            }
        }
*/

        if (centerTotal > 250000) {
            //Blue
            position = "ONE";
        } else if (centerTotal > 150000){
            //Green
            position = "THREE";
        } else {
            //Red or yellow
            position = "TWO";
        }

        return workingMatrix;
    }

}
