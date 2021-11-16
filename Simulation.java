package tp1;

import java.util.Vector;

public class Simulation {
	double lambda, mu;
	ListeEvents listeE;
	QueueClient queueC;
	double dS,T=0;
	int loop=1;
	
	public Simulation(double lambda, double mu) {
		this.lambda=lambda;
		this.mu=mu;
		queueC=new QueueClient();
		listeE=new ListeEvents();
		
	}
	
	public double expo(double taux) {
		T=-Math.log(Math.random())/taux;
		return T;
	}
	public void printLoop(int nbClient, double dS, double T) {
		System.out.println(loop++ +"/" +"Client numéro :"+ nbClient +", arrivé à l'instant :"+T+",durée de service :"+dS );
	}
	public void printLoopA(int eventID,double T) {
		System.out.println("Evenement de type arrivé du client: "+eventID+" à l'instant:"+ T );
	}
	public void printLoopD(int eventID, double T) {
		System.out.println("Evenement de type départ du client: "+eventID+" à l'instant:"+ T );
	}
	public void Simulate(double simLength) {
		final int max=60;
		int nbClient=0;
		double waitT,startT,arrvT=0;
		Event s1=new Event(nbClient,Event.arrv, T);
		listeE.addEvent(s1);
		do { 
		Event e= (Event) listeE.getListe().elementAt(0);
		listeE.getListe().removeElementAt(0);
		T=e.getEventT();
		if(e.getType()== Event.arrv) {
			nbClient++;
			if(nbClient <= max) {
				T= T+ expo(lambda);
				arrvT=T;
				Event nextE= new Event(nbClient ,Event.arrv,T);
				listeE.addEvent(nextE);	
				dS=expo(mu);
				Client c=new Client(nextE.getId(),nextE.getEventT(),dS);
				queueC.AddClient(c);
	    	
		    		if(queueC.getListe().size()==1) {
		    		T=arrvT+c.getServD();
		    		Event nextE2= new Event(c.getId(),Event.dep, T);
		    		listeE.addEvent(nextE2);	
		    	
		    		}
		    	printLoopA(nextE.getId() ,nextE.getEventT());
		    	printLoop(c.getId(), c.getServD(), c.getArrvT());
		    	
		    
			}
		    			}
		    else if(!(queueC.isEmpty())) {
		    	 startT= T - queueC.getListe().elementAt(0).getServD() ;
 		 		 waitT=startT-queueC.getListe().elementAt(0).getArrvT();
 		 		 printLoopD(e.getId(),e.getEventT());
    		   	 System.out.println("Temps d'attente:"+waitT);
	    		 queueC.getListe().remove(0);
	    		 	if(!queueC.isEmpty()) {
	    		 		T= arrvT+queueC.getListe().firstElement().getServD();
	    		 		Event nextE3= new Event(queueC.getListe().firstElement().getId(),Event.dep,T);
	    		 		listeE.addEvent(nextE3);	
	    		 }
	    		 else
	    			 break;	    		 
				}
		}while(!( T< simLength && nbClient <= max && listeE.isEmpty()));
		
			
	} 
	


public static void main(String[] arg) {
	Simulation S=new Simulation(0.5,1);
	S.Simulate(10000000);
}
}