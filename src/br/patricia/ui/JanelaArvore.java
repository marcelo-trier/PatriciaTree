package br.patricia.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import br.patricia.ElementoArvore;

public class JanelaArvore extends JFrame {

	private JPanel contentPane = new JPanel();
	private JScrollPane scrollPane = new JScrollPane();
	private TreePanel treePanel;

	public JanelaArvore( ElementoArvore raiz ) {
		this();
		init( raiz );
	}
	
	public JanelaArvore() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 358);
		setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		scrollPane.setBorder( new EmptyBorder( 5,5,5,5 ) );
		
		contentPane.add( scrollPane, BorderLayout.CENTER );
	}

	public void init( ElementoArvore raiz ) {
		treePanel = new TreePanel( raiz );
		scrollPane.setViewportView( treePanel );
		this.setSize( treePanel.getWidth(), treePanel.getHeight() );
	}
	/*
	 * public void initTela( String titulo, BufferedImage img ) { 
	 * String oTitulo = "[JanelaId: " + contadorJanela + "] :: "; oTitulo += titulo;
	 * this.setTitle( oTitulo ); 
	 * id = contadorJanela; 
	 * cantoX = 50 * id; 
	 * cantoY = 50 * id; 
	 * if( cantoY > 300 ) { cantoY %= 300; }
	 * 
	 * setBounds(cantoX, cantoY, img.getWidth() + 20, img.getHeight() + 50 );
	 * getContentPane().setLayout(new BorderLayout(0, 0)); 
	 * JScrollPane scrollPane = new JScrollPane();
	 * scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	 * scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
	 * getContentPane().add( scrollPane, BorderLayout.CENTER );
	 * 
	 * 
	 * imagem = img;
	 * panel = new ImagePanel( img );
	 * panel.setPreferredSize( new Dimension(img.getWidth(), img.getHeight() ) );
	 * scrollPane.setViewportView( panel );
	 * 
	 * contadorJanela++; }
	 */
	
	
}
