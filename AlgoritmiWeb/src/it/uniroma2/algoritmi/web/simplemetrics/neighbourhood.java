package it.uniroma2.algoritmi.web.simplemetrics;

import it.unimi.dsi.logging.ProgressLogger;
import it.unimi.dsi.webgraph.ImmutableGraph;
import it.unimi.dsi.webgraph.Transform;
import it.unimi.dsi.webgraph.algo.HyperBall;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class neighbourhood {

	public neighbourhood() {
		// TODO Auto-generated constructor stub
	}

	public List<Double> computeNeighbour(ImmutableGraph graph,int numberOfRegister) throws IOException{
		// @SuppressWarnings("unused")
		int rand = (int)(Math.random()*10);
	//	 @SuppressWarnings("resource")
		HyperBall ball = new HyperBall(graph, Transform.transpose(graph),numberOfRegister, new ProgressLogger(),0,0,0,false);
	//	ball.init();
		 ball.run(graph.numNodes());
		List<Double> Testneighbourhood = new ArrayList<Double>() ;
		 Testneighbourhood.addAll(ball.neighbourhoodFunction);
		 
		
		return Testneighbourhood;
	}
}
