package br.patricia.v2;

public abstract class ElementoArvore {
	ElementoArvore oPai = null;
	
	public abstract ElementoArvore getDireita();
	public abstract ElementoArvore getEsquerda();
	public abstract String toString();
}
