package br.patricia;

public abstract class ElementoArvore {
	ElementoArvore oPai = null;
	
	public abstract ElementoArvore getDireita();
	public abstract ElementoArvore getEsquerda();
	public abstract String toString();
	public ElementoArvore getPai() {
		return oPai;
	}
}
