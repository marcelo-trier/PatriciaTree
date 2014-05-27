package br.patricia.v2;

public class FolhaV2 extends ElementoArvore {
	String chave = null;

	public FolhaV2( String s ) {
		chave = s;
	}
	
	public ElementoArvore getDireita() { return null; }
	public ElementoArvore getEsquerda() { return null; }
	public String toString() { return chave; }
}
