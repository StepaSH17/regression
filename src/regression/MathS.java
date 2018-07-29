package regression;

public class MathS 
    {
        public static double GetArrMean(double[] arr)
            {
                double sum = 0;
                int len = arr.length;
                
                for(int i =0; i < arr.length; i++)
                    {
                         sum+= arr[i];
                    }
                
                return sum / len;
            }

        public static double Sum(double[] array)
            {
                double sumarr = 0;
                
                for(int i =0; i < array.length; i++)
                    {
                         sumarr+= array[i];
                    }
                
                return sumarr;
            }

        public static double GetSlope(double[] X, double[] Y)
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
        
        public static double Getvarb1(double[] X, double[] Y)
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
        
        public static double GetStandardDeviation(double[] arr)
            {
                double mean = GetArrMean(arr);
                double[] devs = new double[arr.length]; 
                
                for (int i = 0; i < arr.length; i++)
                    {
                        devs[i] = Math.pow(arr[i] - mean, 2);
                    }
                
                return Math.sqrt(Sum(devs) / (devs.length - 1));
            }

        public static double GetCorrelation(double[] X, double[] Y)
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
    }
