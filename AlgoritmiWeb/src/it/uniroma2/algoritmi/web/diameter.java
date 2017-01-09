package it.uniroma2.algoritmi.web;

import java.util.List;

public class diameter {

	public diameter() {
		// TODO Auto-generated constructor stub
	}

	public static double  effectiveDiamete(double alpha , List<Double> neighbourhood){
	
		double finalFraction = neighbourhood.get(neighbourhood.size() - 1 );
		int d;
		for ( d = 0; neighbourhood.get( d ) / finalFraction < alpha; d++ );
		
		if ( d == 0 ) // In this case we assume the previous ordinate to be zero
			return d + ( alpha * finalFraction - neighbourhood.get( d )) / ( neighbourhood.get( d ) );
		else			
			return d + ( alpha * finalFraction - neighbourhood.get(d ) ) / ( neighbourhood.get( d ) - neighbourhood.get( d - 1 ) );
	
	}
}
