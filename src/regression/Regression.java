package regression;

import java.util.*;
import java.lang.*;
import java.awt.Color;
import java.awt.BasicStroke;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.MultipleLinearRegression;
import org.jfree.data.xy.MatrixSeries;
import org.jfree.data.xy.MatrixSeriesCollection;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

public class Regression extends ApplicationFrame
    {  
        public static double[] xVals = new double [1000];
        public static double[] yVals = new double [1000];
        public static double[] zVals = new double [1000];
        public static double standardDevX, standardDevY, standardDevZ, correlationx, correlationz, slopex, slopez, interception;
        private static double[][] xx = {{1,3,34,3},{4,45,44,5},{433,23,43,5},{432,452,34,5},{14,23,444,45},{3,435,23,23},{324,3,34,6},{45,64,343,25}};
        static double[] yy = {233,43,56,56,534,23,45,78};
        static double[] bb;

        public static void main(String args[])
            {
                SimpleLinearRegression(xVals,yVals);
                Multi2LinearRegression(xVals,yVals,zVals);
                SwingUtilities.invokeLater(() -> {
                    Regression example = new Regression("Scatter plot");
                    example.setSize(800, 400);
                    example.setLocationRelativeTo(null);
                    example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    example.setVisible(true);
                });
                OLSMultipleLinearRegression ols = new OLSMultipleLinearRegression();
                ols.newSampleData(yy, xx);
                double[] result = ols.estimateRegressionParameters();
                for (int i=0;i<result.length;i++)
                    {
                        System.out.println(result[i]);
                    }
            }

        private static double GetArrMean(double[] arr)
            {
                double sum = 0;
                int len = arr.length;
                for(int i =0; i < arr.length; i++)
                    {
                         sum+= arr[i];
                    }
                return sum / len;
            }

        private static double Sum(double[] array)
            {
                double sumarr = 0;
                for(int i =0; i < array.length; i++)
                    {
                         sumarr+= array[i];
                    }
                return sumarr;
            }

        private static double GetSlope(double[] X, double[] Y)
            {
                double[] xy = new double[X.length];
                for (int i = 0; i < X.length; i++)
                    {
                        xy[i] = X[i] * Y[i];
                    }

                double[] xPowed = new double[X.length];

                for (int i = 0; i < X.length; i++)
                    {
                        xPowed[i] = X[i]*X[i];
                    }

                return (X.length*Sum(xy)-Sum(X)*Sum(Y))/(X.length*Sum(xPowed) - Sum(X)*Sum(X) ) ;
            }
        private static double Getvarb1(double[] X, double[] Y)
            {
                double sumx=0;
                for (int i=0; i<X.length; i++)
                    {
                        sumx+=(X[i]-GetArrMean(X))*(X[i]-GetArrMean(X));
                    }
                double sumy=0;
                for (int i=0; i<X.length; i++)
                    {
                        sumy+=(Y[i]-GetArrMean(Y))*(Y[i]-GetArrMean(Y));
                    }
                return (sumy/Y.length)/sumx;
            }
        private static double GetStandardDeviation(double[] arr)
            {
                double mean = GetArrMean(arr);
                double[] devs = new double[arr.length]; 
                for (int i = 0; i < arr.length; i++)
                    {
                        devs[i] = Math.pow(arr[i] - mean, 2);
                    }
                return Math.sqrt(Sum(devs) / (devs.length - 1));
            }

        private static double GetCorrelation(double[] X, double[] Y)
            {
                double XMean = Sum(X) / X.length;
                double YMean = Sum(Y) / Y.length;
                double[] x = new double[X.length];
                double[] y = new double[Y.length];

                for (int i = 0; i < X.length; i++)
                {
                    x[i] = X[i] - XMean;
                    y[i] = Y[i] - YMean;
                }

                double[] xy = new double[X.length];
                for (int i = 0; i < X.length; i++)
                {
                    xy[i] = x[i] * y[i];
                }

                double[] xPowed = new double[X.length];
                double[] yPowed = new double[Y.length];

                for (int i = 0; i < X.length; i++)
                {
                    xPowed[i] = x[i]*x[i];
                    yPowed[i] = y[i]*y[i];
                }

                return Sum(xy) / Math.sqrt(Sum(xPowed) * Sum(yPowed));
            }

        public static double SimpleLinearRegression(double[] xVals, double[] yVals)
            {
                for (int i=1; i<100; i++)
                {
                    Random rand = new Random();
                    xVals[i]=rand.nextInt(100) + 1;
                    yVals[i]=rand.nextInt(100) + 1;
                    zVals[i]=rand.nextInt(100) + 1;
                }
                slopex = GetSlope(xVals, yVals);
                double conf1=slopex-(1.96*Math.sqrt(Getvarb1(xVals,yVals)));
                double conf2=slopex+(1.96*Math.sqrt(Getvarb1(xVals,yVals)));
                interception = GetArrMean(yVals) - slopex * GetArrMean(xVals);
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
        public static double Multi2LinearRegression(double[] xVals, double[] zVals, double[] yVals)
            {
                double corxy=GetCorrelation(xVals, yVals);
                double corxz=GetCorrelation(xVals, zVals);
                double corzy=GetCorrelation(zVals, yVals);
                double sdy=GetStandardDeviation(yVals);
                double sdx=GetStandardDeviation(xVals);
                double sdz=GetStandardDeviation(zVals);
                double b1=((corxy-corzy*corxz)/1-(corxz*corxz))*(sdy/sdx);
                double b2=((corzy-corxy*corxz)/1-(corxz*corxz))*(sdy/sdz);
                double hinterception = GetArrMean(yVals) - b1 * GetArrMean(xVals) - b2 * GetArrMean(zVals);
                System.out.println("-----");
                System.out.print("slopex: ");
                System.out.println(b1);
                System.out.print("slopez: ");
                System.out.println(b2);
                System.out.print("interception: ");
                System.out.println(hinterception);
                return 1;
            }

        public Regression(String title) 
            {
                super(title);
                XYDataset dataset = createDataset();
                JFreeChart chart = ChartFactory.createScatterPlot(
                    "Av.R.Grade and Av.V.Grade vs Grade", 
                    "Score", "Grade", dataset,PlotOrientation.VERTICAL,
                    true,true,false);
                final XYSeriesCollection dataset2 = new XYSeriesCollection( );
                final XYSeries line = new XYSeries( "LineAv.R." ); 
                line.add(0 , interception);
                line.add(100 , interception + 100*slopex);
                dataset2.addSeries(line);
                XYPlot plot = (XYPlot) chart.getPlot();
                plot.setBackgroundPaint(new Color(255,228,196));
                int dataSetIndx = plot.getDatasetCount();
                plot.setDataset(dataSetIndx, dataset2);
                XYLineAndShapeRenderer lineRenderer = new XYLineAndShapeRenderer(true, false);
                plot.setRenderer(dataSetIndx, lineRenderer);
                ChartPanel panel = new ChartPanel(chart); 
                setContentPane(panel);
            }

        public XYDataset createDataset() 
            {
                XYSeriesCollection dataset = new XYSeriesCollection();
                int l=xVals.length;
                XYSeries series1 = new XYSeries("Av.R.Grade");
                XYSeries series2 = new XYSeries("Av.V.Grade");
                for (int i=1;i<l;i++)
                    {
                        series1.add(xVals[i],yVals[i]);
                        series2.add(zVals[i],yVals[i]);
                    }
                dataset.addSeries(series1);
                dataset.addSeries(series2);
                return dataset;
            }
    }