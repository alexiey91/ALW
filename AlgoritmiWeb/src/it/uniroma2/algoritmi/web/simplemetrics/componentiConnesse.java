package it.uniroma2.algoritmi.web.simplemetrics;

import it.unimi.dsi.logging.ProgressLogger;
import it.unimi.dsi.webgraph.ArcListASCIIGraph;
import it.unimi.dsi.webgraph.ImmutableGraph;
import it.unimi.dsi.webgraph.Stats;
import it.unimi.dsi.webgraph.algo.ConnectedComponents;
import it.unimi.dsi.webgraph.algo.NeighbourhoodFunction;
import it.unimi.dsi.webgraph.algo.ParallelBreadthFirstVisit;
import it.unimi.dsi.webgraph.algo.StronglyConnectedComponents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class componentiConnesse {

	
	public componentiConnesse(){
		
	}
	
	public int computeWeakConnectedComponents(ImmutableGraph graph){
		int number = ConnectedComponents.compute(graph, 0, new ProgressLogger()).numberOfComponents;
	return number;}
	
	public int computeStrongConnectedComponents(ImmutableGraph graph){
		int number = StronglyConnectedComponents.compute(graph, false, new ProgressLogger()).numberOfComponents;
	
	return number;}
	
	
	

}
