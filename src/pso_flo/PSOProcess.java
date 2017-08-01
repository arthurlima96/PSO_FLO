package pso_flo;

/* author: gandhi - gandhi.mtm [at] gmail [dot] com - Depok, Indonesia */

// this is the heart of the PSO program
// the code is for 2-dimensional space problem
// but you can easily modify it to solve higher dimensional space problem

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PSOProcess implements PSOConstants {
    
	private List<Particle> swarm = new ArrayList<Particle>();
	private double[] pBest = new double[SWARM_SIZE];
	private List<Location> pBestLocation = new ArrayList<Location>();
	private double gBest = 0;
	private Location gBestLocation;
	private double[] fitnessValueList = new double[SWARM_SIZE];
        
	private Map<Integer,Double> chaves = new HashMap<>();
    //
	Random generator = new Random(); 
	
	public void execute(){
		initializeSwarm();
		updateFitnessList(1);
		
		for(int i=0; i<SWARM_SIZE; i++){
			pBest[i] = fitnessValueList[i];
			pBestLocation.add(swarm.get(i).getLocation());   
                        chaves.put(i, fitnessValueList[i]);
		} 
                
                
                /*
                long inicio = System.currentTimeMillis();
                
                int ind =  PSOUtility.getMaxPos(fitnessValueList);
                System.out.println("Melhor "+ind+" : "+fitnessValueList[ind]);
                
//                for(int i=0; i<SWARM_SIZE; i++) {
//                    System.out.print("\n Print ["+fitnessValueList[i]+"]"+"["+pBest[i]+"]");
//                    System.out.print("\n Print ["+fitnessValueList[i]+"]"+"["+pBest[i]+"]");
//		}
                        gBestLocation = swarm.get(ind).getLocation();
			System.out.println("     Best D1: " + gBestLocation.getLoc()[0]);
			System.out.println("     Best D2: " + gBestLocation.getLoc()[1]);
			System.out.println("     Best D3: " + gBestLocation.getLoc()[2]);
			System.out.println("     Best D4: " + gBestLocation.getLoc()[3]);
			System.out.println("     Best D5: " + gBestLocation.getLoc()[4]);
			System.out.println("     Best D6: " + gBestLocation.getLoc()[5]);
			System.out.println("     Best D7: " + gBestLocation.getLoc()[6]);
			System.out.println("     Best D8: " + gBestLocation.getLoc()[7]);
			System.out.println("     Value: " + ProblemSet.evaluate(gBestLocation));
                        System.out.println("_________________________"+ind+"____________________________");
                        
                        long fim = System.currentTimeMillis();
		
                        System.out.println("Tempo foi de "+(fim-inicio)+" ms");
                        */
                        
                long inicio = System.currentTimeMillis();
		int t = 0;
		double w;
		while(t < MAX_ITERATION ) {
			// Atualizar Melhor Local
			for(int i=0; i<SWARM_SIZE; i++) {
				if(fitnessValueList[i] > pBest[i]) {
                                        //System.out.println("T = "+i+"["+fitnessValueList[i]+"] + ["+pBest[i]+"]");
					pBest[i] = fitnessValueList[i];
					pBestLocation.set(i, swarm.get(i).getLocation());
				}
			}
				
			// Atualizar Melhor Global
			int bestParticleIndex = PSOUtility.getMaxPos(fitnessValueList);
			if(fitnessValueList[bestParticleIndex] > gBest) {
				gBest = fitnessValueList[bestParticleIndex];
				gBestLocation = swarm.get(bestParticleIndex).getLocation();
			}
			
                        //Descremento do W
			w = W_UPPERBOUND - (((double) t) / MAX_ITERATION) * (W_UPPERBOUND - W_LOWERBOUND);
                        
			for(int i=0; i<SWARM_SIZE; i++) {
				double r1 = generator.nextDouble();
				double r2 = generator.nextDouble();
				
				Particle p = swarm.get(i);
				
				// step 3 - update velocity
				double[] newVel = new double[PROBLEM_DIMENSION];
                                
                                newVel[0] =  calcularVelocidade(p,0,w,r1,r2);
                                newVel[1] =  calcularVelocidade(p,1,w,r1,r2);
                                
                                newVel[2] =  calcularVelocidade(p,2,w,r1,r2);
                                newVel[3] =  calcularVelocidade(p,3,w,r1,r2);
                                
                                newVel[4] =  calcularVelocidade(p,4,w,r1,r2);
                                newVel[5] =  calcularVelocidade(p,5,w,r1,r2);
                                
                                newVel[6] =  calcularVelocidade(p,6,w,r1,r2);
                                newVel[7] =  calcularVelocidade(p,7,w,r1,r2);
                                
				Velocity vel = new Velocity(newVel);
				p.setVelocity(vel);
				
				// step 4 - update location
				double[] newLoc = new double[PROBLEM_DIMENSION];
				newLoc[0] = manterLimites(p.getLocation().getLoc()[0] + newVel[0]);
				newLoc[1] = ProblemSet.LOC_X_HIGH - newLoc[0];
                                
				newLoc[2] = manterLimites(p.getLocation().getLoc()[2] + newVel[2]);
				newLoc[3] = ProblemSet.LOC_X_HIGH - newLoc[2];
				
                                newLoc[4] = manterLimites(p.getLocation().getLoc()[4] + newVel[4]);
				newLoc[5] = ProblemSet.LOC_X_HIGH - newLoc[4];
				
                                newLoc[6] = manterLimites(p.getLocation().getLoc()[6] + newVel[6]);
				newLoc[7] = ProblemSet.LOC_X_HIGH - newLoc[6];
                                
				Location loc = new Location(newLoc);
				p.setLocation(loc);
			}
			
			//err = ProblemSet.evaluate(gBestLocation) - 0; // minimizing the functions means it's getting closer to 0
			
			
//			System.out.println("ITERATION " + t + ": ");
//			System.out.println("     Best D1: " + gBestLocation.getLoc()[0]);
//			System.out.println("     Best D2: " + gBestLocation.getLoc()[1]);
//			System.out.println("     Best D3: " + gBestLocation.getLoc()[2]);
//			System.out.println("     Best D4: " + gBestLocation.getLoc()[3]);
//			System.out.println("     Best D5: " + gBestLocation.getLoc()[4]);
//			System.out.println("     Best D6: " + gBestLocation.getLoc()[5]);
//			System.out.println("     Best D7: " + gBestLocation.getLoc()[6]);
//			System.out.println("     Best D8: " + gBestLocation.getLoc()[7]);
//			System.out.println("     Value: " + ProblemSet.evaluate(gBestLocation));
			
			t++;
			updateFitnessList(t);
		}
		
                if(gBestLocation != null){
                    System.out.println("\nSolution found at iteration " + (t - 1) + ", the solutions is:");
                    System.out.println("     Best D1: " + gBestLocation.getLoc()[0]);
                    System.out.println("     Best D2: " + gBestLocation.getLoc()[1]);
                    System.out.println("     Best D3: " + gBestLocation.getLoc()[2]);
                    System.out.println("     Best D4: " + gBestLocation.getLoc()[3]);
                    System.out.println("     Best D5: " + gBestLocation.getLoc()[4]);
                    System.out.println("     Best D6: " + gBestLocation.getLoc()[5]);
                    System.out.println("     Best D7: " + gBestLocation.getLoc()[6]);
                    System.out.println("     Best D8: " + gBestLocation.getLoc()[7]);
                    System.out.println("     Value: " + ProblemSet.evaluate(gBestLocation));
                }
                
                long fim = System.currentTimeMillis();
		
                System.out.println("Tempo foi de "+(fim-inicio)+" ms");
	}
	//Inicialização do Enxame
	public void initializeSwarm(){
		Particle p;
		for(int i=0; i<SWARM_SIZE; i++){
			p = new Particle();
			
			// Cada par representa uma Dimensão e cada indice da dimensão é uma particula
			double[] loc = new double[PROBLEM_DIMENSION];
			loc[0] = ProblemSet.LOC_X_LOW + generator.nextDouble() * (ProblemSet.LOC_X_HIGH - ProblemSet.LOC_X_LOW);
			loc[1] = ProblemSet.LOC_X_HIGH - loc[0];
			
                        loc[2] = ProblemSet.LOC_X_LOW + generator.nextDouble() * (ProblemSet.LOC_X_HIGH - ProblemSet.LOC_X_LOW);
			loc[3] = ProblemSet.LOC_X_HIGH - loc[2];
			
                        loc[4] = ProblemSet.LOC_X_LOW + generator.nextDouble() * (ProblemSet.LOC_X_HIGH - ProblemSet.LOC_X_LOW);
			loc[5] = ProblemSet.LOC_X_HIGH - loc[4];
			
                        loc[6] = ProblemSet.LOC_X_LOW + generator.nextDouble() * (ProblemSet.LOC_X_HIGH - ProblemSet.LOC_X_LOW);
			loc[7] = ProblemSet.LOC_X_HIGH - loc[6];
                        
			Location location = new Location(loc);
			
			// Velocidade no qual cada particula vai se locomover
			double[] vel = new double[PROBLEM_DIMENSION];
			vel[0] = ProblemSet.VEL_LOW + generator.nextDouble() * (ProblemSet.VEL_HIGH - ProblemSet.VEL_LOW);
			vel[1] = ProblemSet.VEL_LOW + generator.nextDouble() * (ProblemSet.VEL_HIGH - ProblemSet.VEL_LOW);
			
                        vel[2] = ProblemSet.VEL_LOW + generator.nextDouble() * (ProblemSet.VEL_HIGH - ProblemSet.VEL_LOW);
			vel[3] = ProblemSet.VEL_LOW + generator.nextDouble() * (ProblemSet.VEL_HIGH - ProblemSet.VEL_LOW);
			
                        vel[4] = ProblemSet.VEL_LOW + generator.nextDouble() * (ProblemSet.VEL_HIGH - ProblemSet.VEL_LOW);
			vel[5] = ProblemSet.VEL_LOW + generator.nextDouble() * (ProblemSet.VEL_HIGH - ProblemSet.VEL_LOW);
			
                        vel[6] = ProblemSet.VEL_LOW + generator.nextDouble() * (ProblemSet.VEL_HIGH - ProblemSet.VEL_LOW);
			vel[7] = ProblemSet.VEL_LOW + generator.nextDouble() * (ProblemSet.VEL_HIGH - ProblemSet.VEL_LOW);
			Velocity velocity = new Velocity(vel);
			
			p.setLocation(location);
			p.setVelocity(velocity);
			swarm.add(p);
		}
	}
	//Fitness de cada Particula Inicialmente
	public void updateFitnessList(int t) {
                System.out.println("Iteração "+t);
		for(int i=0; i<SWARM_SIZE; i++) {
                            fitnessValueList[i] = swarm.get(i).getFitnessValue();
                        System.out.println("Fitness: "+fitnessValueList[i]);
		}
	}

    private double calcularVelocidade(Particle p, int indice,double w, double r1, double r2) {
        return (w * p.getVelocity().getPos()[indice]) +                
                (r1 * C1) * (pBestLocation.get(indice).getLoc()[indice] - p.getLocation().getLoc()[indice]) +    
                (r2 * C2) * (gBestLocation.getLoc()[indice] - p.getLocation().getLoc()[indice]);
    }

    private double manterLimites(double valor) {
        if (valor > ProblemSet.LOC_X_HIGH)
		return ProblemSet.LOC_X_HIGH;
	else if (valor < ProblemSet.LOC_X_LOW)
		return ProblemSet.LOC_X_LOW;

	return valor;
    }
}
    