package pso_flo;

import java.util.Random;

/* author: gandhi - gandhi.mtm [at] gmail [dot] com - Depok, Indonesia */

// just a simple utility class to find a minimum position on a list

public class PSOUtility {
	public static int getMaxPos(double[] list) {
		Random generator = new Random(); 
                int pos = generator.nextInt(list.length);
		//double maxValue = list[0];
		
//		for(int i=0; i<list.length; i++) {
//                    if(list[i] > 660 && list[i] < 670) {
//                        pos = i;
//                        maxValue = list[i];
//                    }
//		}		
		return pos;
	}
}
