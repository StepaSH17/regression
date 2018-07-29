package regression;

public class Dim2Regression 
    {
        public static double Multi2LinearRegression(double[] xVals, double[] zVals, double[] yVals)
            {
                double corxy=MathS.GetCorrelation(xVals, yVals);
                double corxz=MathS.GetCorrelation(xVals, zVals);
                double corzy=MathS.GetCorrelation(zVals, yVals);
                
                double sdy=MathS.GetStandardDeviation(yVals);
                double sdx=MathS.GetStandardDeviation(xVals);
                double sdz=MathS.GetStandardDeviation(zVals);
                
                double b1=((corxy-corzy*corxz)/1-(corxz*corxz))*(sdy/sdx);
                double b2=((corzy-corxy*corxz)/1-(corxz*corxz))*(sdy/sdz);
                double hinterception = MathS.GetArrMean(yVals) - b1 * MathS.GetArrMean(xVals) - b2 * MathS.GetArrMean(zVals);
                
                System.out.println("-----");
                System.out.print("slopex: ");
                System.out.println(b1);
                System.out.print("slopez: ");
                System.out.println(b2);
                System.out.print("interception: ");
                System.out.println(hinterception);
                
                return 1;
            }
    }
