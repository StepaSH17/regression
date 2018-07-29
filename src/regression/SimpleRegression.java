package regression;

import java.util.Random;

public class SimpleRegression 
    {
        public static double slopex, interception;
        public static double SimpleLinearRegression(double[] xVals, double[] yVals)
            {               
                slopex = MathS.GetSlope(xVals, yVals);
                double conf1=slopex-(1.96*Math.sqrt(MathS.Getvarb1(xVals,yVals)));
                double conf2=slopex+(1.96*Math.sqrt(MathS.Getvarb1(xVals,yVals)));
                interception = MathS.GetArrMean(yVals) - slopex * MathS.GetArrMean(xVals);
                
                System.out.print("slope: ");
                System.out.println(slopex);
                System.out.print("interception: ");
                System.out.println(interception); 
                System.out.print("confidencel: ");
                System.out.println(conf1);
                System.out.print("confidencer: ");
                System.out.println(conf2);
                
                return 1;
            }    
    }
