package it.uniroma2.algoritmi.web;

import it.unimi.dsi.logging.ProgressLogger;
import it.unimi.dsi.webgraph.ArrayListMutableGraph;
import it.unimi.dsi.webgraph.ImmutableGraph;
import it.unimi.dsi.webgraph.algo.BetweennessCentrality;

import java.util.ArrayList;
import java.util.List;

public class betweenness {

	public betweenness() {
		// TODO Auto-generated constructor stub
	}

	public List <Double> computeBetweenness(ImmutableGraph graph) throws InterruptedException{
		
		
		BetweennessCentrality centrality = new BetweennessCentrality(graph);
		centrality.compute();
		List<Double> listOfCentrality = new ArrayList<Double>() ;
		for(int i =0 ; i< centrality.betweenness.length;i++)
		listOfCentrality.add(i,centrality.betweenness[i]);
		
		//listOfCentrality=elaborate(listOfCentrality);
		//listOfCentrality = normalize(listOfCentrality,graph.numNodes());
		return listOfCentrality;
		
	}
	public List<Double> elaborate(List<Double> centrality){
		List <Double> test = new ArrayList<Double>();
		double min = Min(centrality);
		System.out.println("MIN:"+min);
		double max = Max(centrality);
		System.out.println("MAX:"+max);
		for(int i =0 ; i< centrality.size();i++){
		
			test.add((centrality.get(i)-min)/(max-centrality.get(i)));
		}
		
		return test;
	}
	public List<Double> normalize(List<Double> centrality, double nodi){
		List <Double> test = new ArrayList<Double>();
		double temp =0;
		for(int i= 0 ; i< centrality.size() ; i++){
			temp = centrality.get(i)/((nodi-1)*(nodi-2)/2);
			
			test.add(i, temp);
		}
		
		return test;
	}
	public double Max(List<Double> list){
		double max=0;
		for(int i =0 ;i<list.size();i++){
			if(list.get(i)>max) max = list.get(i);
		}
		return max;
	}
	
	public double Min(List<Double> list){
		double min=0;
		for(int i =0 ;i<list.size();i++){
			if(list.get(i)< min) min = list.get(i);
		}
		return min;
	}
}
