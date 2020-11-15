package view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.CYK_algorithm;
import model.Grammar;

public class Controller {
	
	private CYK_algorithm cyk;

    @FXML
    private TextArea grammar_textArea;

    @FXML
    private TextField cad_TextField;
    
    @FXML
    private Button addCad_ButtonId;

    @FXML
    private Label infoLabel;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    void addCad_Button(ActionEvent event) {
    	
    	String cad = cad_TextField.getText();
    	
    	cyk.setCad(cad);
    	
    	
    	if (cad.length() == 0 && cyk.getGrammar().getLambda()) {
    		infoLabel.setText("La gramatica genera la cadena " + "'" + cad + "'");
		}else if (cad.length() == 0 && !cyk.getGrammar().getLambda()) {
			infoLabel.setText("La gramatica no genera la cadena " + "'" + cad + "'");
		}else {
			
			if (cyk.cyk()) {
				infoLabel.setText("La gramatica genera la cadena " + "'" + cad + "'");
			}else {
				infoLabel.setText("La gramatica no genera la cadena " + "'" + cad + "'");
			}
			
			generateTable();
	    	cad_TextField.setText("");
			
		}
    	
    }

    @FXML
    void addGrammar_Button(ActionEvent event) {
    	
    	scrollPane.setContent(null);
    	
    	try {
    		infoLabel.setTextFill(Color.BLACK);
    		cyk.setGrammar(readGrammar());
        	
        	cad_TextField.setDisable(false);
        	addCad_ButtonId.setDisable(false);
        	
		} catch (Exception e) {
			infoLabel.setText("Error en la gramatica. No FNC");
			infoLabel.setTextFill(Color.RED);
			
			grammar_textArea.setText("");
			
			cad_TextField.setText("");
			cad_TextField.setDisable(true);
			addCad_ButtonId.setDisable(true);
			
		}
    	
    	
    	
    	
    }
    
    public void initialize() {
    	cyk = new CYK_algorithm();
    }
    
    private Grammar readGrammar() throws Exception {
    	
    	Grammar g = null;
    	String initial = null;
    	Hashtable<String, List<String>> table = new Hashtable<String, List<String>>();
    	boolean lambda = false;
    	int vacios = 0;
    	
    	for (String line : grammar_textArea.getText().split("\n")) {
	
    		
    		if (initial == null) {
				initial = line.substring(0, 1);
			}
    		
    		
    		String variable = line.substring(0, 1);
    		List<String> prod = new ArrayList<String>();
    		
    		String line2 = line.substring(2);
    		
    		StringTokenizer st = new StringTokenizer(line2, "|");
    		
    		int i = 0;
    		int count = st.countTokens();
    		while(i < count) {
    			String s = st.nextToken();
    			
    			if (s.length() > 2 || s.length() == 0) {
					throw new Exception();
				}
    			
    			if (s.equals("#")) {
					vacios++;
				}
    			
    			prod.add(s);
    			i++;
    			
    		}
    			
    		table.put(variable, prod);
    		
    		
    		
    		
		}
    	
    	if (vacios > 1) {
			throw new Exception();
		}
    	
    	if (table.get(initial).contains("#")) {
			lambda = true;
		}
		
    	
    	g = new Grammar(initial, table, lambda);
    	
    	
    	return g;
    	
    }
    
    private void generateTable() {
    	
    	GridPane grid = new GridPane();
    	HashSet<String>[][] m = cyk.getHS();
    	
    	for (int k = 1; k < m.length; k++) {
			grid.add(new Button("i = " + k), 0, k);
			grid.add(new Button("j = " + k), k, 0);
		}
    	
    	for (int i = 1; i < m.length; i++) {

    		for (int j = 1; j < m.length; j++) {
    			if (m[i][j] != null) {
    				
    				grid.add(new Button(m[i][j] + ""), j, i);
				}
				
    			
    			
			}
    		
		}
    	
    	scrollPane.setContent(grid);
    	

    }

}

