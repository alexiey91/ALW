package it.uniroma2.algoritmi.web.simplemetrics;

import it.unimi.dsi.webgraph.ImmutableGraph;
import it.unimi.dsi.webgraph.algo.SumSweepDirectedDiameterRadius;

import java.util.ArrayList;
import java.util.List;

public class center {

	public center() {
		// TODO Auto-generated constructor stub
	}

	public List<Integer> getcenter( ImmutableGraph graph) {
		SumSweepDirectedDiameterRadius calculator = new SumSweepDirectedDiameterRadius(graph, SumSweepDirectedDiameterRadius.OutputLevel.ALL, null, null);
		calculator.compute();
		ArrayList<Integer> centers = new ArrayList<Integer>();
		int radius = calculator.getRadius();
		
		for(int i=0; i<graph.numNodes(); i++) {
			if(calculator.getEccentricity(i, true) == radius) {
				centers.add(i);
			}
		}
		

		
		return centers;
	}
	
}
