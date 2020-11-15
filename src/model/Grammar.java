package model;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

public class Grammar {

	private String initialVariable;
	private Hashtable<String, List<String>> productions;
	private boolean lambda;

	public Grammar(String initialVariable, Hashtable<String, List<String>> productions, boolean lambda) {

		this.initialVariable = initialVariable;
		this.productions = productions;
		this.lambda = lambda;

	}

	public String getInitialVariable(){
		return initialVariable;
	}

	public Hashtable<String, List<String>> getProductions(){
		return productions;
	}
	
	public boolean getLambda() {
		return lambda;
	}
	
	public HashSet<String> isProduceFor(List<String> p){

		HashSet<String> variables = new HashSet<String>();

		for (String c : productions.keySet()){
			
			for (String s : p) {
				
				if (productions.get(c).contains(s)) {
					
					variables.add(c);
					
				}
			}
			
		}
		

		if (variables.size() == 0) {
			variables = null;
		}

		return variables;

	}
	
}
