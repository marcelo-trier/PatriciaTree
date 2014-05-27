package br.patricia.v2;

import br.patricia.util.IndexUtils;

enum FILHOS {
	ESQ, DIR;
}

public class TroncoV2 extends ElementoArvore {
	ElementoArvore filhos[] = { null, null }; // filhos da direita ou esquerda
	int oIndice = 0; // claro, aqui eh o indice da diferenca...
	char oChar = 0; // o caracter do nodo

	public TroncoV2(int idx, char umC, FolhaV2 umFilho) {
		oIndice = idx;
		oChar = umC;
		if (IndexUtils.verificaMenorChar(umFilho.chave, umC, idx))
			filhos[FILHOS.ESQ.ordinal()] = umFilho;
		else
			filhos[FILHOS.DIR.ordinal()] = umFilho;
	}

	public void adicionaFilho(ElementoArvore elemento) throws Exception {
		filhos[filhoIndex(null)] = elemento;
	}

	public int filhoIndex(Object o) throws Exception {
		int idxF = IndexUtils.indexOf(filhos, o);
		if (idxF == -1)
			throw new Exception(
					"Foi pedido um indice de filho, mas não existe!");
		return idxF;
	}

	public void trocaFilho(TroncoV2 filhoAntigo, TroncoV2 novoFilho)
			throws Exception {
		filhos[filhoIndex(filhoAntigo)] = novoFilho;
	}

	public ElementoArvore getDireita() {
		return filhos[FILHOS.DIR.ordinal()];
	}

	public ElementoArvore getEsquerda() {
		return filhos[FILHOS.ESQ.ordinal()];
	}

	public String toString() {
		String str;
		char outroC = (oChar == 0) ? '_' : oChar;
		str = "" + oIndice + "/" + outroC;
		return str;
	}
}