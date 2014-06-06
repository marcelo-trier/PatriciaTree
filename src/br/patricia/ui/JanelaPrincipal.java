package br.patricia.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import br.patricia.PatriciaTreeV2;

public class JanelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField txtConsultar;
	private JTextArea txtArea;

	public String[] capturaDicionario() {
		ArrayList<String> lista = new ArrayList<String>();
		String linhas[] = txtArea.getText().split("\n");
		return linhas;
	}

	final String variasListas[][] = { 
			{ "aaa", "aaa" }, // 0
			{ "aaa", "aa" }, // 1
			{ "aaa", "aaaa" }, // 2
			{ "aaa", "aab" },  // 3
			{ "aaa", "baa" }, // 4
			{ "aaa", "ba" },  // 5
			{ "ba", "aaa" },  // 6
			{ "baaa", "aa" },
			{ "aaa", "aba" }, 
			{ "aab", "aaa" }, 
			{ "aaa", "aa", "a" },
			{ "aaa", "aa", "b" }, 
			{ "aaa", "a", "aa" }, 
			{ "aa", "a", "aaa" },
			{ "aaa", "b" }, 
			{ "b", "aaa", "c" }, 
			{ "aaa", "ba", "a" },
			{ "aaa", "a", "ba" }, 
			{ "a", "ba", "aaa" }, 
			{ "a", "aaa", "ba" },
			{ "ba", "aaa", "a" }, 
			{ "ba", "a", "aaa" }, 
			{ "aaa", "ab", "a" },
			{ "aaa", "a", "ab" }, 
			{ "aaa", "ab", "a" }, 
			{ "aaa", "a", "ab" },
			{ "ab", "aaa", "a" }, 
			{ "ab", "a", "aaa" }, 
			{ "a", "aaa", "ab" },
			{ "a", "ab", "aaa" }, 
			{ "bb", "b", "aaa" },
			{ "aaaa", "aab", "aa", "aaa" },
			{ "ccc", "bbb", "bba", "bb", "cca", "abb", "ccb", "cbc" },
	// { "", "", "" },
	};

	public void clickGera() throws Exception {
		//String umTeste[] = variasListas[ 32 ];
		String umTeste[] = capturaDicionario();
		PatriciaTreeV2 pt;

		pt = new PatriciaTreeV2();
		// pt.treina( variasListas[ 11 ] );
		pt.treina(umTeste);

		JanelaArvore ja = new JanelaArvore( pt.raiz );
		ja.setVisible(true);
	}

	public void clickConsultar() {
		String str = txtConsultar.getText();
		int index = 1;
		int i = str.indexOf('b');
		JOptionPane.showMessageDialog(this, "i = " + i);
	}

	public JanelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(12, 51, 156, 203);
		contentPane.add(scrollPane);

		txtArea = new JTextArea();
		txtArea.setForeground(Color.BLUE);
		txtArea.setFont(new Font("Verdana", Font.BOLD, 12));
		txtArea.setText("foi\nfora\nfui\nvai\nver\nviu\nvimos\nveremos");
		scrollPane.setViewportView(txtArea);

		JLabel lblConjPalavras = new JLabel("Dicionário:");
		lblConjPalavras.setBounds(12, 28, 101, 15);
		contentPane.add(lblConjPalavras);

		JButton btnGeraArvore = new JButton("Gera Arvore");
		btnGeraArvore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					clickGera();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(JanelaPrincipal.this,
							"Excessão: " + ex.toString());
				}
			}
		});
		btnGeraArvore.setBounds(183, 49, 153, 51);
		contentPane.add(btnGeraArvore);

		txtConsultar = new JTextField();
		txtConsultar.setText("abcdef");
		txtConsultar.setBounds(183, 154, 153, 35);
		contentPane.add(txtConsultar);
		txtConsultar.setColumns(10);

		JButton btnNewButton = new JButton("Consultar...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickConsultar();
			}
		});
		btnNewButton.setBounds(183, 201, 153, 25);
		contentPane.add(btnNewButton);
	}
}
