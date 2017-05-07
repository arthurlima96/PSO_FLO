package pso_flo;

/* author: gandhi - gandhi.mtm [at] gmail [dot] com - Depok, Indonesia */

// this is the problem to be solved
// to find an x and a y that minimize the function below:
// f(x, y) = (2.8125 - x + x * y^4)^2 + (2.25 - x + x * y^2)^2 + (1.5 - x + x*y)^2
// where 1 <= x <= 4, and -1 <= y <= 1

// you can modify the function depends on your needs
// if your problem space is greater than 2-dimensional space
// you need to introduce a new variable (other than x and y)

public class ProblemSet {
	public static final double LOC_X_LOW = 1;
	public static final double LOC_X_HIGH = 99;
//	public static final double LOC_Y_LOW = -1;
//	public static final double LOC_Y_HIGH = 1;
	public static final double VEL_LOW = -1;
	public static final double VEL_HIGH = 1;
	
	//public static final double ERR_TOLERANCE = 1E-20; // the smaller the tolerance, the more accurate the result, 
	                                                  // but the number of iteration is increased
	
	public static double evaluate(Location location) {
		double result = 0; 
                
                result += dimensaoAfinidade(location.getLoc()[0],location.getLoc()[1]); 
                result += dimensaoAfinidade(location.getLoc()[2],location.getLoc()[3]);
                result += dimensaoAfinidade(location.getLoc()[4],location.getLoc()[5]);
                result += dimensaoAfinidade(location.getLoc()[6],location.getLoc()[7]);		
		
		return result;
	}
        
        public static double dimensaoAfinidade(double d1,double d2){
            return d1 > d2 ? 1 * d1 : 2 * d2; 
        }
}
