package it.uniroma2.algoritmi.web;

import it.unimi.dsi.webgraph.ImmutableGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.ctc.wstx.io.EBCDICCodec;

public class embeddednessIndex {

	public embeddednessIndex() {
		// TODO Auto-generated constructor stub
	}

	
	public List<Integer> computeEmbeddedness(ImmutableGraph graph){
		List<Integer> embeddedness = new ArrayList<Integer>();

		List<successorObject> successorObj = new ArrayList<successorObject>();
		Utils util = new Utils();
		successorObj=util.listOfSuccessor(graph);		
		int cont=0;
		
		for(int i=0 ; i< successorObj.size();i++){
			int node = successorObj.get(i).getNode();
			int [] app = new int[successorObj.get(i).getsuccessor().length];
			app = successorObj.get(i).getsuccessor();
			
			for(int l=0 ; l< app.length; l++){
				
				cont= computeEmb(successorObj.get(i).getsuccessor(), successorObj.get(app[l]).getsuccessor(),node);
				embeddedness.add(cont);
			}
			
			
		}
		
		
		
		return embeddedness;
	}
	
	
	
	public int computeEmb(int []start, int[]succ, int node){
		//List<Integer> emb = new ArrayList<Integer>();
		int counter = 0;
		for(int i=0 ; i< start.length;i++){
			for(int j=0 ; j< succ.length;j++){
				//System.out.println("start["+i+"]:"+start[i]+" =="+"succ["+j+"]:"+succ[j] );
				if(start[i]== succ[j] && node != succ[j])counter++;
			}
		}
		//System.out.println("counter"+counter);
		//emb.add(counter);
		return counter;
	}
	
}
