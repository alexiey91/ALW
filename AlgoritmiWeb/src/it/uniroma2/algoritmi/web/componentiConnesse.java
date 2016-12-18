package it.uniroma2.algoritmi.web;

import it.unimi.dsi.webgraph.ArcListASCIIGraph;
import it.unimi.dsi.webgraph.ImmutableGraph;
import it.unimi.dsi.webgraph.algo.ConnectedComponents;
import it.unimi.dsi.webgraph.algo.StronglyConnectedComponents;
import it.unimi.dsi.webgraph.jung.JungAdapter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class componentiConnesse {

	public static void main(String arg[]) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = null;
		FileReader fr = null;
		fr = new FileReader(arg[0]);
		br = new BufferedReader(fr);
		String sCurrentLine;

		br = new BufferedReader(new FileReader(arg[0]));

		while ((sCurrentLine = br.readLine()) != null) {
			System.out.println(sCurrentLine);
			int test = 
		}
		br.close();
		double archi, nodi,densità;

		ImmutableGraph graph1 = ArcListASCIIGraph.loadOffline(arg[0]);
		ImmutableGraph graph2 = graph1;
			archi=graph1.numArcs();
			nodi =graph1.numNodes();
			
			
			System.out.println("ciao :"+graph1.toString());
			System.out.println("Numero nodi di un grafo: "+nodi);

			densità = archi/(nodi*(nodi-1));
		System.out.println("Dimensione di un grafo: "+ archi);
		System.out.println("Densità di un grafo orientato:"+densità );
		//System.out.println(graph1.outdegree(0));
		 System.out.println("Numero Componenti Connesse di un grafo: "+ConnectedComponents.compute(graph1, 0, null).numberOfComponents);
		 System.out.println("Numero Componenti fortemente connesse: "+ StronglyConnectedComponents.compute(graph1, false, null).numberOfComponents);
		 JungAdapter j = new JungAdapter(graph1, graph2);
		 System.out.println("Numero nodi??:"+j.getVertexCount());
		 double sum=0 ,media;
		 List<Integer> temp = new ArrayList<Integer>();
		 for(int i = 0; i<nodi ;i++){
			temp.add(j.degree(i));
			 sum= sum+j.degree(i);
		 }
		 media = sum/nodi;
		 System.out.println("Numero medio gradi: "+ media);
		 System.out.println("Massimo: "+ Collections.max(temp));
		 System.out.println("Minimo:"+ Collections.min(temp));
		 System.out.println("lista"+temp);
	}

}
