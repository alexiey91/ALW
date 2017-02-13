package it.uniroma2.algoritmi.web.index;

import it.unimi.dsi.logging.ProgressLogger;
import it.unimi.dsi.webgraph.ImmutableGraph;
import it.unimi.dsi.webgraph.algo.GeometricCentralities;

import java.util.ArrayList;
import java.util.List;

public class closeness {

	public closeness() {
		// TODO Auto-generated constructor stub
	}

	public List<Double> computeCloseness(ImmutableGraph graph){
		List<Double> setOfCloseness = new ArrayList<Double>();
		
GeometricCentralities g= new	GeometricCentralities(graph, new ProgressLogger());
		
		try {
			 g.compute() ;
			 double[] closeness = g.closeness;
			 for(int i =0 ; i< closeness.length;i++)
				 setOfCloseness.add(i,closeness[i]);
			 //System.out.println("closeness: "+closeness[i]);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return setOfCloseness;
	}
}
