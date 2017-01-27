package it.uniroma2.algoritmi.web;

import it.unimi.dsi.webgraph.ImmutableGraph;
import it.unimi.dsi.webgraph.LazyIntIterator;
import it.unimi.dsi.webgraph.NodeIterator;

import java.util.ArrayList;
import java.util.List;

public class intermediationCentrality {

	public intermediationCentrality() {
		// TODO Auto-generated constructor stub
	}

	
	/*Questo indice di centralità calcola 
	 * la beetweenness dell'attore k che sarà pari alla sommatoria 
	 * di tutte le beetweenness parziali calcolate per ogni coppia di nodi
	 *
	 *N.B. questo algoritmo funziona soltanto nel caso di grafi non orientati
	 * */
	
	public List<Double> computeIntermediationCentrality(ImmutableGraph graph) throws InterruptedException{
		double interCentrality=0;
		List<Double> listIntermediation = new ArrayList<Double>();
		List<successorObject> listOfsuccessorNode = new ArrayList<successorObject>();
		listOfsuccessorNode= listOfSuccessor(graph);
		
		//calcolo la betweenness di ogni nodo
		betweenness bet = new betweenness();
		List<Double> listBetweenness= new ArrayList<Double>();
		listBetweenness = bet.computeBetweenness(graph);
		
		for(int i =0 ; i< listOfsuccessorNode.size();i++){
			double sum = 0;
			if(listOfsuccessorNode.get(i).getsuccessor().length >= 2 ){
				for( int j=0 ; j<listOfsuccessorNode.get(i).getsuccessor().length; j++){
					
					sum = sum + listBetweenness.get(listOfsuccessorNode.get(i).getsuccessor()[j]);
				}
				listIntermediation.add(i,sum);
			}
			else listIntermediation.add(i,0.0);
		}
			
		System.out.println("listIntermedition: "+listIntermediation);
		
		return listIntermediation;
	}
public double manageIntermediationCentrality(List<Double> list){
	double max = 0.0;
	for(int i =0;i< list.size();i++){
		if(list.get(i)>max)max=list.get(i);
	}
	return max;
}
	public List<successorObject> listOfSuccessor(ImmutableGraph graph){
		List<successorObject> successor = new ArrayList<successorObject>();
	
				
		for(int i=0 ; i< graph.numNodes();i++){
			//LazyIntIterator successors = graph.successors( i );
			 successorObject temp = new successorObject();
			System.out.println("graph.successors per il nodo ["+i+"] ="+graph.successorArray(i));
			int [] x = new int[graph.successorArray(i).length];
			x=graph.successorArray(i);
			for(int j=0 ; j< graph.successorArray(i).length;j++)System.out.println("x["+j+"] "+x[j]);

			
			 temp.setNode(i);
			 temp.setsuccessor(x);
			 successor.add(temp);
		}
		
		
		
		return successor;
	}
	
	
	
	
}
