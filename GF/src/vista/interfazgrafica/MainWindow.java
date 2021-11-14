/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.interfazgrafica;

import controlador.controller;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author mario
 */
public class MainWindow extends JFrame{
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	controller ctr;

    public MainWindow(controller c) {
       ctr = c;
       initGUI();
    }

    private void initGUI() {
        Inicializar dialogo = new Inicializar(ctr);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        this.setContentPane(mainPanel);
        mainPanel.add(new Paneldecontrol(ctr), BorderLayout.PAGE_START);
		
        JPanel views = new JPanel(new GridLayout(1, 2));
        mainPanel.add(views, BorderLayout.CENTER);
        
        views.add(new Tablero_IG(ctr));
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        
        dialogo.open();
    }
}
