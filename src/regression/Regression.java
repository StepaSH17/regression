package regression;

import java.util.*;
import java.lang.*;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Regression
    {  
        public static double[] xVals = new double [1000];
        public static double[] yVals = new double [1000];
        public static double[] zVals = new double [1000];

        public static void main(String args[])
            {
                for (int i=1; i<100; i++)
                    {   
                        Random rand = new Random();
                        xVals[i]=rand.nextInt(100) + 1;
                        yVals[i]=rand.nextInt(100) + 1;
                        zVals[i]=rand.nextInt(100) + 1;
                    }//creating massives of grades 
                
                SimpleRegression.SimpleLinearRegression(xVals,yVals); //simple linear regression numbers
                
                SwingUtilities.invokeLater(() -> 
                    {
                        SimpleRPlot example = new SimpleRPlot("Scatter plot");
                        example.setSize(800, 400);
                        example.setLocationRelativeTo(null);
                        example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                        example.setVisible(true);
                    }); //graphplot of 2 linearregression and line of best fit for XY
                
                Dim2Regression.Multi2LinearRegression(xVals,yVals,zVals); //linear regression from 2 variables 
                
                MultiVariableRegression.MultVarCoeff();  //multivariable linear regression coefficents
            }
    }