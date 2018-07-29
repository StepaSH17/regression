package regression;

import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import static regression.Regression.xVals;
import static regression.Regression.yVals;
import static regression.Regression.zVals;

public class SimpleRPlot extends ApplicationFrame
    {
        public SimpleRPlot(String title) 
            {
                super(title);
                
                XYDataset dataset = createDataset();
                
                JFreeChart chart = ChartFactory.createScatterPlot(
                    "Av.R.Grade and Av.V.Grade vs Grade", 
                    "Score", "Grade", dataset,PlotOrientation.VERTICAL,
                    true,true,false);
                
                final XYSeriesCollection dataset2 = new XYSeriesCollection( );
                final XYSeries line = new XYSeries( "LineAv.R." ); 
                
                line.add(0 , SimpleRegression.interception);
                line.add(100 , SimpleRegression.interception + 100*SimpleRegression.slopex);
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
