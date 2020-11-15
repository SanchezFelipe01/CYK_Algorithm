package view;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.CYK_algorithm;
import model.Grammar;

public class Controller {
	
	private CYK_algorithm cyk;

    @FXML
    private TextArea grammar_textArea;

    @FXML
    private TextField cad_TextField;

    @FXML
    private Label infoLabel;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    void addCad_Button(ActionEvent event) {
    	
    	String cad = cad_TextField.getText();
    	
    	cyk.setCad(cad);
    	
    	
    	if (cyk.cyk()) {
			infoLabel.setText("Genera la cadena");
		}else {
			infoLabel.setText("No genera la cadena");
		}
    	
    	generateTable();
    	
    }

    @FXML
    void addGrammar_Button(ActionEvent event) {
    	
    	cyk.setGrammar(readGrammar());
    	
    	
    }
    
    public void initialize() {
    	cyk = new CYK_algorithm();
    }
    
    private Grammar readGrammar() {
    	
    	Grammar g = null;
    	String initial = null;
    	Hashtable<String, List<String>> table = new Hashtable<String, List<String>>();
    	
    	for (String line : grammar_textArea.getText().split("\n")) {
			
    		if (initial == null) {
				initial = line.substring(0, 1);
			}
    		
    		String variable = line.substring(0, 1);
    		List<String> prod = new ArrayList<String>();
    		
    		String line2 = line.substring(2);
    		StringTokenizer st = new StringTokenizer(line2, "|");
    		
    		int i = 0;
    		while(i <= st.countTokens()) {
    			String s = st.nextToken();
    			
    			prod.add(s);
    			i++;
    		}
    			
    		table.put(variable, prod);
    		

		}
    	
    	g = new Grammar(initial, table);
    	
    	
    	return g;
    	
    }
    
    private void generateTable() {
    	
    	GridPane grid = new GridPane();
    	HashSet<String>[][] m = cyk.getHS();
    	
    	for (int i = 1; i < m.length; i++) {

    		for (int j = 1; j < m.length; j++) {
    			if (m[i][j] != null) {
    				String s = m[i][j] + "";
    				System.out.println(s);
    				grid.add(new Label(m[i][j] + ""), j-1, i-1);
				}
				
    			
    			
			}
    		
		}
    	
    	scrollPane.setContent(grid);
    	

    }

}

