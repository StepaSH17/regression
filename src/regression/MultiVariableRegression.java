package regression;

import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

public class MultiVariableRegression 
    {
        public static double[][] xx = {{1,3,34,3},{4,45,44,5},{433,23,43,5},{432,452,34,5},{14,23,444,45},{3,435,23,23},{324,3,34,6},{45,64,343,25}};
        static double[] yy = {233,43,56,56,534,23,45,78};
    
        public static void MultVarCoeff()
            {
                OLSMultipleLinearRegression ols = new OLSMultipleLinearRegression();
                ols.newSampleData(yy, xx);
                double[] result = ols.estimateRegressionParameters();
                
                System.out.println("-----");
                    for (int i=0;i<result.length;i++)
                        {
                            System.out.println(result[i]);
                        }
            }
    }
