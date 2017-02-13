package it.uniroma2.algoritmi.web;

import it.unimi.dsi.logging.ProgressLogger;
import it.unimi.dsi.webgraph.ArcListASCIIGraph;
import it.unimi.dsi.webgraph.ImmutableGraph;
import it.unimi.dsi.webgraph.algo.NeighbourhoodFunction;
import it.unimi.dsi.webgraph.algo.ParallelBreadthFirstVisit;
import it.uniroma2.algoritmi.web.index.betweenness;
import it.uniroma2.algoritmi.web.index.closeness;
import it.uniroma2.algoritmi.web.index.clusteringIndex;
import it.uniroma2.algoritmi.web.index.degreeCentrality;
import it.uniroma2.algoritmi.web.index.dispersionCentrality;
import it.uniroma2.algoritmi.web.index.embeddednessIndex;
import it.uniroma2.algoritmi.web.index.pageRankCentrality;
import it.uniroma2.algoritmi.web.simplemetrics.Bridge;
import it.uniroma2.algoritmi.web.simplemetrics.articulationPoint;
import it.uniroma2.algoritmi.web.simplemetrics.componentiConnesse;
import it.uniroma2.algoritmi.web.simplemetrics.diameter;
import it.uniroma2.algoritmi.web.simplemetrics.neighbourhood;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.alg.KosarajuStrongConnectivityInspector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class main {

	private static List<Integer> Listanodi = new ArrayList<Integer>();
	
	public static void main(String arg[]) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		String fileName = new File(arg[0]).getName();
	 final String path = "C:\\Users\\alessandro\\Desktop\\testing\\"+fileName+".txt";

	 File file = new File(path);
	 
	 
	 double archi, nodi,densità;

		ImmutableGraph graph1 = ArcListASCIIGraph.loadOffline(arg[0]);
			
			if(file.exists()){
				System.out.println("dentro if");
				BufferedWriter bw = null;
					FileWriter fw = null;
					fw = new FileWriter(file.getAbsoluteFile(), true);
					bw = new BufferedWriter(fw);
					 dispersionCentrality disp = new dispersionCentrality();
					 Date primaDisp = new Date();
						bw.write("Dispersion Index:" +disp.dispersionCentrality(graph1)+" tempo di esecuzione: ");
						Date dopoDisp = new Date();
						bw.write(dopoDisp.getTime()-primaDisp.getTime()+" ms \r\n");
					
//					 embeddednessIndex embe = new embeddednessIndex();	
//
//					 Date primaEmb = new Date();
//						bw.write("Embeddedness Index: "+embe.computeEmbeddedness(graph1)+" tempo di esecuzione: ");
//						Date dopoEmb = new Date();
//						bw.write(dopoEmb.getTime()-primaEmb.getTime()+" ms \r\n");
						 System.out.println("DONE");
					bw.close();
					
					 
					
			}
			
			else{
			archi=graph1.numArcs();
			nodi =graph1.numNodes();
			densità = archi/(nodi*(nodi-1));
			
		BufferedReader br = null;
		FileReader fr = null;
		fr = new FileReader(arg[0]);
		br = new BufferedReader(fr);
		
		String sCurrentLine;
		String app;

		br = new BufferedReader(new FileReader(arg[0]));
		int countLine = 0;
		while ((sCurrentLine = br.readLine()) != null) {
			countLine = countLine + 1;
			app = sCurrentLine.replaceAll("[^0-9]+", " ");
		for (String s : Arrays.asList(app.trim().split(" ")))
				Listanodi.add(Integer.valueOf(s));

		}
		if (br != null)
			br.close();

		if (fr != null)
			fr.close();
		DirectedGraph<String, DefaultEdge> graph = createStringGraph(Listanodi);
		ConnectivityInspector<String, DefaultEdge> inspector = new ConnectivityInspector<String, DefaultEdge>(
				graph);
				
		KosarajuStrongConnectivityInspector<String,DefaultEdge>finder = new KosarajuStrongConnectivityInspector<String,DefaultEdge>( graph);
		
		
		Bridge bridge= new Bridge();
		List<String> weaklyBridge =bridge.numberOfWeaklyBridge(graph, Listanodi, inspector.connectedSets().size());
		List<String> strongBridge =bridge.numberStrongBridge(graph, Listanodi, finder.stronglyConnectedSets().size());
		
		articulationPoint artPoint = new articulationPoint();
		List<String> weaklyArtPoit = artPoint.numberOfWeaklyArticulationPoint(graph, Listanodi, inspector.connectedSets().size());
		List<String> strengthArtPoint = artPoint.numberOfStrongArticulationPoint(graph, Listanodi, finder.stronglyConnectedSets().size());
		
		BufferedWriter bw = null;
		FileWriter fw = null;

		componentiConnesse componenti = new componentiConnesse();
		
		
		 double sum=0,average;
		 List<Integer> temp = new ArrayList<Integer>();
		 for(int i = 0; i<nodi ;i++){
			temp.add(graph1.outdegree(i));
			sum= sum+graph1.outdegree(i); 
		 }
		 average = sum/nodi;
		
		 
		
		 List<Double> ListOfneighbourdhood = new ArrayList<Double>();
		 neighbourhood prova = new neighbourhood();
		 ListOfneighbourdhood = prova.computeNeighbour(graph1, 10);
		
		 diameter d = new diameter();
		 double diameter = d.effectiveDiamete(0.9, ListOfneighbourdhood);
		
		 NeighbourhoodFunction n = new NeighbourhoodFunction();
		long [] x = n.computeExact(graph1, 0, new ProgressLogger());
	
		double  diametro =n.effectiveDiameter(0.9, n.compute(graph1,0,new ProgressLogger()));
		
		closeness closeness = new closeness();
		betweenness between = new betweenness();
		degreeCentrality degree = new degreeCentrality();
		pageRankCentrality page = new pageRankCentrality();
		 clusteringIndex cluster = new clusteringIndex();
		 embeddednessIndex embe = new embeddednessIndex();	
		 dispersionCentrality disp = new dispersionCentrality();		

		
		try {


			fw = new FileWriter(path);
			
						
			bw = new BufferedWriter(fw);
			
			
			bw.write("Numero nodi: "+nodi+"\r\n");
			bw.write("Numero archi: "+archi+"\r\n");
			bw.write("Dimensione grafo: "+ archi+"\r\n");
			bw.write("Densità: "+densità+"\r\n");
			bw.write("Numero Componenti Connesse di un grafo: "+componenti.computeWeakConnectedComponents(graph1)+"\r\n");
			bw.write("Numero Componenti Fortemente Connesse: "+ componenti.computeStrongConnectedComponents(graph1)+"\r\n");
			
			 bw.write("Numero Medio Grado Nodo: "+ average+"\r\n");
			 bw.write("Massimo: "+ Collections.max(temp)+"\r\n");
			 bw.write("Minimo:"+ Collections.min(temp)+"\r\n");
			
			 ParallelBreadthFirstVisit grafo = new ParallelBreadthFirstVisit(graph1, 0,false,null);
			 for (int i=0 ; i< nodi ; i++){
				 bw.write("Nodi visitati: "+grafo.visit(i)+"\r\n");

			 }
			 
			 bw.write("Lista vicinanza: "+ListOfneighbourdhood+"\r\n");
			 bw.write("effective diameter: "+ diameter+"\r\n");
				for ( int i=0 ; i< x.length;i++)
					 bw.write("visitati i nodi x["+i+"]: "+x[i]+"\r\n" );
			 bw.write("Diametro: "+diametro+"\r\n");
			 Date primaWBridge = new Date();
			 bw.write("WeakBridge: "+weaklyBridge+" tempo di esecuzione: ");
			 Date dopoWBridge = new Date();
			 
			bw.write(dopoWBridge.getTime()-primaWBridge.getTime()+" ms \r\n");
			
			Date primaSBridge = new Date();
			 bw.write("StrengtBridge: "+strongBridge+" tempo di esecuzione: ");
			 Date dopoSBridge = new Date();
				bw.write(dopoSBridge.getTime()-primaSBridge.getTime()+" ms \r\n");
				
				Date primaWArt = new Date();
			 bw.write("Weak ArticulationPoint: "+weaklyArtPoit+" tempo di esecuzione: ");
			 Date dopoWArt = new Date();
				bw.write(dopoWArt.getTime()-primaWArt.getTime()+" ms \r\n");

				Date primaSArt = new Date();
			 bw.write("Strengt ArticulationPoint: "+strengthArtPoint+" tempo di esecuzione: ");
			 Date dopoSArt = new Date();
				bw.write(dopoSArt.getTime()-primaSArt.getTime()+" ms \r\n");

			 
			 bw.write("\r\n\r\n\r\n Indici di centralità:\r\n");
				Date primaCloseness = new Date();
				bw.write("Set Of Closeness: "+closeness.computeCloseness(graph1)+" tempo di esecuzione: ");
				Date dopoCloseness = new Date();
				bw.write(dopoCloseness.getTime()-primaCloseness.getTime()+"ms \r\n");
				Date primaBetweenness = new Date();
				bw.write("List of Betweenness: "+ between.computeBetweenness(graph1)+" tempo di esecuzione: ");
				Date dopoBetweenness = new Date();
				bw.write(dopoBetweenness.getTime()-primaBetweenness.getTime()+"ms \r\n");
				
				Date primaNBetweenness = new Date();
				
				bw.write("List of Normalized Betweenness: "+between.normalize(between.computeBetweenness(graph1),nodi)+" tempodiesecuzione: ");
				Date dopoNBetweenness= new Date();
				bw.write(dopoNBetweenness.getTime()-primaNBetweenness.getTime()+"ms \r\n");

				Date primaDegree = new Date();
				bw.write("Index of degree:"+degree.computeDegreeCentrality(graph1)+"\r\n");
				Date dopoDegree = new Date();
				bw.write(dopoDegree.getTime()-primaDegree.getTime()+"ms \r\n");

				Date primaPage = new Date();
				bw.write("Indice di Autovettore:"+page.getPageRankIntex(page.computePageRankCentrality(graph1))+" tempo di esecuzione: ");
				Date dopoPage = new Date();
				bw.write(dopoPage.getTime()-primaPage.getTime()+"ms \r\n");

				for(int i=0 ; i<graph1.numNodes();i++)
				bw.write("Per il nodo:"+i+" il valore di Clustering è pari a :"+cluster.clusterCoefficent(graph1, i)+"\r\n");
				
				Date primaCluster =new Date();
				bw.write("L'Indice di Clustering è pari a: "+cluster.clusterIndex(graph1)+" tempo di esecuzione: ");
				Date dopoCluster = new Date();
				bw.write(dopoCluster.getTime()-primaCluster.getTime()+" ms \r\n");

				Date primaEmb = new Date();
				bw.write("Embeddedness Index: "+embe.computeEmbeddedness(graph1)+" tempo di esecuzione: ");
				Date dopoEmb = new Date();
				bw.write(dopoEmb.getTime()-primaEmb.getTime()+" ms \r\n");
				
				Date primaDisp = new Date();
				bw.write("Dispersion Index:" +disp.dispersionCentrality(graph1)+" tempo di esecuzione: ");
				Date dopoDisp = new Date();
				bw.write(dopoDisp.getTime()-primaDisp.getTime()+" ms \r\n");

				System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		}
	}
	//Creo il grafo diretto a partire dalla lista dei nodi
		private static DirectedGraph<String, DefaultEdge> createStringGraph(
				List<Integer> nodi) {
			DirectedGraph<String, DefaultEdge> grafo = new DefaultDirectedGraph<String, DefaultEdge>(
					DefaultEdge.class);
			//System.out.println("NODI SIZE: " + nodi);
			for (int i = 0; i < nodi.size(); i++) {
				grafo.addVertex(nodi.get(i).toString());
				if (i % 2 != 0) {
					grafo.addEdge(nodi.get(i - 1).toString(), nodi.get(i)
							.toString());
				}

			}

			return grafo;
		}
}
