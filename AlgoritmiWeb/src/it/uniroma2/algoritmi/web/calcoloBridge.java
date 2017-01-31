package it.uniroma2.algoritmi.web;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.AllDirectedPaths;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.alg.KosarajuStrongConnectivityInspector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class calcoloBridge {
	private static List<Integer> Listanodi = new ArrayList<Integer>();

	//private static String path = "C:\\Users\\alessandro\\Desktop\\ALW\\Kapferer-Tailor-Shop_Multiplex_Social\\Dataset\\TailorShop.edges";
	private static String path = "C:\\Users\\alessandro\\Desktop\\bridge.edges";
	//private static String path = "C:\\Users\\alessandro\\Desktop\\ALW\\CKM-Physicians-Innovation_Multiplex_Social\\Dataset\\CKM.edges";
	//private static String path ="C:\\Users\\alessandro\\Desktop\\ALW\\Lazega-Law-Firm_Multiplex_Social\\Dataset\\Lazega.edges";
	//private static String path ="C:\\Users\\alessandro\\Desktop\\ALW\\Krackhardt-High-Tech_Multiplex_Social\\Dataset\\Krackhardt.edges";
	//private static String path = "C:\\Users\\alessandro\\Desktop\\clustering.edges";
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = null;
		FileReader fr = null;
		fr = new FileReader(path);
		br = new BufferedReader(fr);

		String sCurrentLine;
		String temp;

		br = new BufferedReader(new FileReader(path));
		int countLine = 0;
		while ((sCurrentLine = br.readLine()) != null) {
			//System.out.println(sCurrentLine);
			countLine = countLine + 1;
			temp = sCurrentLine.replaceAll("[^0-9]+", " ");
			//System.out.println(Arrays.asList(temp.trim().split(" ")));
			for (String s : Arrays.asList(temp.trim().split(" ")))
				Listanodi.add(Integer.valueOf(s));

		}
	//	System.out.println("ListaNodi:"+ Listanodi);

		if (br != null)
			br.close();

		if (fr != null)
			fr.close();
		DirectedGraph<String, DefaultEdge> grafo = createStringGraph(Listanodi);
		//System.out.println("grafo: " + grafo.toString());
		ConnectivityInspector<String, DefaultEdge> inspector = new ConnectivityInspector<String, DefaultEdge>(
				grafo);
		
		
		KosarajuStrongConnectivityInspector<String,DefaultEdge>finder = new KosarajuStrongConnectivityInspector<String,DefaultEdge>( grafo);
			
		System.out.println("numero componenti connesse finder: "+	finder.stronglyConnectedSets().size());
		int componentiConnesse = inspector.connectedSets().size();
		System.out.println("numero componenti connesse inspector: "+componentiConnesse);

		/*List<String> bridge = numberOfBridge(grafo, Listanodi,
				finder.stronglyConnectedSets().size());*/
		Date  prima = new Date();
		List<String> weaklyBridge = numberOfWeaklyBridge(grafo, Listanodi,
				componentiConnesse);
		System.out.println("set of weak bridge :" + weaklyBridge);
		Date dopo = new Date();
		long timer = dopo.getTime()-prima.getTime();
		System.out.println("Tempo di esecuzione weak bridge: "+timer+"ms");
		Date  primaStrong = new Date();
		List<String> strongBridge =  numberStrongBridge(grafo, Listanodi,
				finder.stronglyConnectedSets().size());
		
		System.out.println("set of strength bridge :" + strongBridge);
		Date  dopoStrong = new Date();
		long timerStrong = dopoStrong.getTime()-primaStrong.getTime();
		System.out.println("Tempo di esecuzione strong bridge: "+ timerStrong+"ms");
		
		Date primaArtweak = new Date();
		List<String> ArticulationPoint =  TestnumberOfWeaklyArticulationPoint(grafo, Listanodi,
				componentiConnesse);
		System.out.println("Numero di Punti di articolazione debole:"+ArticulationPoint);
		Date dopoArtweak = new Date();
		long timerArtweak = dopoArtweak.getTime()-primaArtweak.getTime();
		System.out.println("Tempo di esecuzionw weak Articulation: "+timerArtweak+"ms");
		Date primaArtstrong = new Date();
		List<String> StrongArticulationPoint =  TestnumberOfStrongArticulationPoint(grafo, Listanodi,
				finder.stronglyConnectedSets().size());
		System.out.println("Numero di Punti di articolazione debole:"+StrongArticulationPoint);
		Date dopoArtstrong = new Date();
		long timerArtstrong = dopoArtstrong.getTime()-primaArtstrong.getTime();
		System.out.println("Tempo di esecuzione strong Articulation Point: "+ timerArtstrong+"ms");
		
		
		
		
		
		
	}

	private static List<String> numberOfWeaklyBridge(
			DirectedGraph<String, DefaultEdge> grafo, List<Integer> nodi,
			int start_componentiConnesse) {
		List<String> setOfBridge = new ArrayList<String>();
		String probablyBridge;
		nodi = new ArrayList<Integer>(new HashSet<Integer>(nodi));

		for (int i = 0; i < nodi.size(); i++) {
			for (int j = 0; j < nodi.size(); j++) {
				probablyBridge = null;
				//System.out.println("containsEdge i="+ i+" j: "+ j+ " edge:"+grafo.containsEdge(nodi.get(i).toString(), nodi.get(j).toString()));
				if (grafo.containsEdge(nodi.get(i).toString(), nodi.get(j)
						.toString())) {
					probablyBridge = grafo.getEdge(nodi.get(i).toString(),
							nodi.get(j).toString()).toString();
			//		System.out.println("probablyBridge" + probablyBridge);
					grafo.removeEdge(nodi.get(i).toString(), nodi.get(j)
							.toString());
					ConnectivityInspector<String, DefaultEdge> inspector = new ConnectivityInspector<String, DefaultEdge>(
							grafo);
				//	System.out.println("GRAFO senza Nodo: " + grafo);
			
					
				//	System.out.println("Lista COMPONENTI CONNESSE:"+ inspector.connectedSets());
				//		System.out.println("# componenti connesse:"+ inspector.connectedSets().size());
					if (inspector.connectedSets().size() > start_componentiConnesse) {
						System.out.println("True weakbridge position:"+i);
						grafo.addEdge(nodi.get(i).toString(), nodi.get(j)
								.toString());
					/*	System.out.println("GRAFO con Nodo RIaggiunto: "
								+ grafo);
						
						System.out.println("dentro if 2: "
								+ inspector.connectedSets().size());*/
						setOfBridge.add(probablyBridge);
					}else  grafo.addEdge(nodi.get(i).toString(), nodi.get(j)
							.toString());
				}

			}
		}

		return setOfBridge;
	}
	
	private static List<String> numberStrongBridge(
			DirectedGraph<String, DefaultEdge> grafo, List<Integer> nodi,
			int start_componentiConnesse) {
		List<String> setOfBridge = new ArrayList<String>();
		String probablyBridge;
		nodi = new ArrayList<Integer>(new HashSet<Integer>(nodi));

		for (int i = 0; i < nodi.size(); i++) {
			for (int j = 0; j < nodi.size(); j++) {
				probablyBridge = null;
				//System.out.println("containsEdge i="+ i+" j: "+ j+ " edge:"+grafo.containsEdge(nodi.get(i).toString(), nodi.get(j).toString()));
				if (grafo.containsEdge(nodi.get(i).toString(), nodi.get(j)
						.toString())) {
					probablyBridge = grafo.getEdge(nodi.get(i).toString(),
							nodi.get(j).toString()).toString();
				//	System.out.println("probablyBridge" + probablyBridge);
					grafo.removeEdge(nodi.get(i).toString(), nodi.get(j)
							.toString());
					KosarajuStrongConnectivityInspector<String,DefaultEdge>finder = new KosarajuStrongConnectivityInspector<String,DefaultEdge>( grafo);

				/*	System.out.println("GRAFO senza Nodo: " + grafo);
					System.out.println("Lista COMPONENTI CONNESSE:"+ finder.stronglyConnectedSets());
					System.out.println("# componenti connesse:"+finder.stronglyConnectedSets().size());*/
					
					
						if (finder.stronglyConnectedSets().size() > start_componentiConnesse) {
							System.out.println("True strongbridge position:"+i);
						grafo.addEdge(nodi.get(i).toString(), nodi.get(j)
								.toString());
					/*	System.out.println("GRAFO con Nodo RIaggiunto: "
								+ grafo);
						System.out.println("dentro if 2: "
								+ finder.stronglyConnectedSets().size());*/
						
						setOfBridge.add(probablyBridge);
					}else  grafo.addEdge(nodi.get(i).toString(), nodi.get(j)
							.toString());
				}

			}
		}

		return setOfBridge;
	}

	
//	private static List<String> numberOfWeaklyArticulationPoint(
//			DirectedGraph<String, DefaultEdge> grafo, List<Integer> nodi,
//			int start_componentiConnesse) {
//		
//		List<String> setOfArticulationPoint = new ArrayList<String>();
//		
//		String probablyPoint;
//		
//		nodi = new ArrayList<Integer>(new HashSet<Integer>(nodi));
//		
//		DirectedGraph<String, DefaultEdge> tempo = new DefaultDirectedGraph<String, DefaultEdge>(
//				DefaultEdge.class);
//		
//		for(int i=0 ; i< nodi.size();i++){
//		//	System.out.println("Grafo prima del for: "+grafo);
//		//	System.out.println("Vertice : "+nodi.get(i).toString());
//			tempo = grafo;
//		//	System.out.println("Temp prima del for: "+tempo);
//			if(tempo.containsVertex(nodi.get(i).toString())){
//				probablyPoint= nodi.get(i).toString();
//				
//				
//				tempo.removeVertex(nodi.get(i).toString());
//
//				ConnectivityInspector<String, DefaultEdge> inspector = new ConnectivityInspector<String, DefaultEdge>(
//						tempo);
//			//	System.out.println("GRAFO Temp senza Nodo: " + tempo );
//		
//				
//			//	System.out.println("Lista COMPONENTI CONNESSE senza Il Vertice rimosso :"+ inspector.connectedSets());
//			//		System.out.println("# componenti connesse:"+ inspector.connectedSets().size());
//				if (inspector.connectedSets().size() > start_componentiConnesse) {
//					tempo =grafo;
//					//grafo.addVertex(nodi.get(i).toString());
//					/*System.out.println("GRAFO con Nodo RIaggiunto: "
//							+ grafo);*/
//					
//		//			System.out.println("dentro if 2: "	+ inspector.connectedSets().size());
//					setOfArticulationPoint.add(probablyPoint);
//				}else {//System.out.println("No Articolation point at position:"+i);
//					tempo= new  DefaultDirectedGraph<String, DefaultEdge>(
//							DefaultEdge.class);
//					//System.out.println("TEMP:"+tempo);
//					}
//			}
//
//			
//			}
//		
//			
//		return setOfArticulationPoint;	}

	
	
	
	private static List<String> TestnumberOfWeaklyArticulationPoint(
			DirectedGraph<String, DefaultEdge> grafo, List<Integer> nodi,
			int start_componentiConnesse) {
		
		List<String> setOfArticulationPoint = new ArrayList<String>();
		List<String> setOfEdges = new ArrayList<String>();
		List<String> ListofEdges = new ArrayList<String>();
		String probablyPoint;
		
		nodi = new ArrayList<Integer>(new HashSet<Integer>(nodi));
		
		for( int i=0 ; i<nodi.size();i++){
						
				if(grafo.containsVertex(nodi.get(i).toString())){
				setOfEdges.add(grafo.edgesOf(nodi.get(i).toString()).toString());
			//	System.out.println("setof edges:"+setOfEdges);
			//	System.out.println("set of edges in pos:"+i+"= "+ setOfEdges.get(i).trim().substring(1,setOfEdges.get(i).trim().length()-1 ));
				ListofEdges=parsedListEdges(setOfEdges.get(i).trim().substring(1,setOfEdges.get(i).trim().length()-1 ));
			//	System.out.println("LISTOFEDGES"+ListofEdges);
				probablyPoint = nodi.get(i).toString();
				grafo.removeVertex(nodi.get(i).toString());
				
				
				ConnectivityInspector<String, DefaultEdge> inspector = new ConnectivityInspector<String, DefaultEdge>(
						grafo);
			//	System.out.println("inspector:"+inspector.connectedSets());
				if (inspector.connectedSets().size() > start_componentiConnesse) {
			//	System.out.println("dentro if ispector at time: "+i);
					System.out.println("True wealpoint position:"+i);
					grafo.addVertex(probablyPoint);
					for(int j=0; j< ListofEdges.size();j++){
						if(j%2!=0){
							grafo.addEdge(ListofEdges.get(j - 1).toString(), ListofEdges.get(j)
									.toString());
						}
					}
				//	grafo.addEdge(setOfEdges.get(i), setOfEdges.get(i+1));
					//grafo.addVertex(nodi.get(i).toString());
					/*System.out.println("GRAFO con Nodo RIaggiunto: "
							+ grafo);*/
					
		//			System.out.println("dentro if 2: "							+ inspector.connectedSets().size());
					setOfArticulationPoint.add(probablyPoint);
				}else {//System.out.println("No Articolation point at position:"+i);
				grafo.addVertex(probablyPoint);
				
				for(int j=0; j< ListofEdges.size();j++){
					if(j%2!=0){
					
						grafo.addEdge(ListofEdges.get(j - 1), ListofEdges.get(j)
								.toString());
					}
				}
				
				}
		
				}
				
		}
		
			
		return setOfArticulationPoint;	}
	
	
	private static List<String> TestnumberOfStrongArticulationPoint(
			DirectedGraph<String, DefaultEdge> grafo, List<Integer> nodi,
			int start_componentiConnesse) {
		
		List<String> setOfArticulationPoint = new ArrayList<String>();
		List<String> setOfEdges = new ArrayList<String>();
		List<String> ListofEdges = new ArrayList<String>();
		String probablyPoint;
		
		nodi = new ArrayList<Integer>(new HashSet<Integer>(nodi));
		
		for( int i=0 ; i<nodi.size();i++){
						
				if(grafo.containsVertex(nodi.get(i).toString())){
				setOfEdges.add(grafo.edgesOf(nodi.get(i).toString()).toString());
			//	System.out.println("setof edges:"+setOfEdges);
			//	System.out.println("set of edges in pos:"+i+"= "+ setOfEdges.get(i).trim().substring(1,setOfEdges.get(i).trim().length()-1 ));
				ListofEdges=parsedListEdges(setOfEdges.get(i).trim().substring(1,setOfEdges.get(i).trim().length()-1 ));
				//System.out.println("LISTOFEDGES"+ListofEdges);
				probablyPoint = nodi.get(i).toString();
				grafo.removeVertex(nodi.get(i).toString());
				
				
				KosarajuStrongConnectivityInspector<String,DefaultEdge>finder = new KosarajuStrongConnectivityInspector<String,DefaultEdge>( grafo);

			//	System.out.println("finder:"+finder.stronglyConnectedSets());
				if (finder.stronglyConnectedSets().size() > start_componentiConnesse) {
			//	System.out.println("dentro if finder at time: "+i);
					System.out.println("True strongpoint position:"+i);
					grafo.addVertex(probablyPoint);
					for(int j=0; j< ListofEdges.size();j++){
						if(j%2!=0){
							grafo.addEdge(ListofEdges.get(j - 1).toString(), ListofEdges.get(j)
									.toString());
						}
					}
				//	grafo.addEdge(setOfEdges.get(i), setOfEdges.get(i+1));
					//grafo.addVertex(nodi.get(i).toString());
					/*System.out.println("GRAFO con Nodo RIaggiunto: "
							+ grafo);*/
					
				//	System.out.println("dentro if 2 finder: "	+ finder.stronglyConnectedSets().size());
					setOfArticulationPoint.add(probablyPoint);
				}else {//System.out.println("No Strong Articolation point at position:"+i);
				grafo.addVertex(probablyPoint);
				
				for(int j=0; j< ListofEdges.size();j++){
					if(j%2!=0){
					
						grafo.addEdge(ListofEdges.get(j - 1), ListofEdges.get(j)
								.toString());
					}
				}
				
				}
		
				}
				
		}
		
			
		return setOfArticulationPoint;	}
	
	
	public static ArrayList<String> parsedListEdges(String s){
		
		if(s!= ""){
		String replace = s.replace("(","");
		String replace1 = replace.replace(")","");
		String replace2 = replace1.replace(" ","");
		String replace3 = replace2.replace(":", ",");
		
		List<String> arrayList = new ArrayList<String>    (Arrays.asList(replace3.split(",")));
		List<String> favList = new ArrayList<String>();
		for(String fav:arrayList){
		    favList.add(fav.trim());
		}
		
		
		return (ArrayList<String>) favList;
		}
		else return null;
		
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
