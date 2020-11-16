//______________________________________________________PACKAGE___________________________________________________________

/**
 *This package contains all the classes required to execute the CYK algorithm 
 */

package model;

//______________________________________________________IMPORTS___________________________________________________________

import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

//______________________________________________________THE CLASS__________________________________________________________

/**
* This class defines the basic attributes and methods of a Grammar 
* @author Luis Felipe Sanchez Vega
*/

public class Grammar {

//______________________________________________________ATTRIBUTES___________________________________________________________

	private String initialVariable;
	private Hashtable<String, List<String>> productions;
	private boolean lambda;

//______________________________________________________METHODS___________________________________________________________

	/**
	 * The constructor of a Grammar <br><br>
	 * @param initialVariable The initial variable of the grammar
	 * @param productions A hashtable with all the variables of the Grammar and their productions
	 * @param lambda A boolean that indicates if the initial variable produces lambda
	 */
	public Grammar(String initialVariable, Hashtable<String, List<String>> productions, boolean lambda) {

		this.initialVariable = initialVariable;
		this.productions = productions;
		this.lambda = lambda;

	}
	
	/**
   	 * This method returns the initial variable of the grammar <br><br> 
   	 * <b>Pre: </b> The grammar must not be null <br><br>
   	 * @return A string that represents the initial variable of the grammar <br><br>
   	 */
	public String getInitialVariable(){
		return initialVariable;
	}
	
	/**
   	 * This method returns a table with all the variables and productions of the grammar <br><br> 
   	 * <b>Pre: </b> The grammar must not be null <br><br>
   	 * @return A table with the productions of the grammar <br><br>
   	 */
	public Hashtable<String, List<String>> getProductions(){
		return productions;
	}
	
	/**
   	 * This method returns a boolean that tells if the initial variable produce lambda <br><br> 
   	 * <b>Pre: </b> The grammar must not be null <br><br>
   	 * @return A boolean that represents if the initial variable of the grammar produces lambda <br><br>
   	 */
	public boolean getLambda() {
		return lambda;
	}
	
	/**
	 * This method tells what variables produce the given elements <br><br>
	 * <b>Pre: </b> The grammar must not be null <br><br>
	 * @param p A list with all the productions <br><br>
	 * @return A set that contains the variables that produce at least one of the elements of the list <br><br>
	 */
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
