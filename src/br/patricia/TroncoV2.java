package br.patricia;

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
		
		umFilho.oPai = this;
	}

	public FolhaV2 getOnlyChild() throws Exception {
		if( ( filhos[0] != null && filhos[1] != null ) || 
				( filhos[0] == null && filhos[1] == null ) )
			throw new Exception( "tentativa de pegar apenas um filho, mas não deu certo!");

		ElementoArvore elem = ( filhos[0] != null ) ? filhos[0] : filhos[1];
		if( !( elem instanceof FolhaV2 ) )
			throw new Exception( "tentativa de pegar um filho tronco, não dará certooo!!" );
		
		return ( FolhaV2 )elem;
	}

	
	public void adicionaFilho(ElementoArvore elemento) throws Exception {
		// salva o pai do elemento
		ElementoArvore umPai = elemento.oPai;

		// faz o elemento ser filho dele
		filhos[filhoIndex(null)] = elemento;
		// faz este tronco ser pai do elemento
		elemento.oPai = this;

		// faz o pai deste ter o pai do outro
		this.oPai = umPai;

		// faz o pai atualizar a referencia do outro filho para este de agora
		if( umPai != null && umPai instanceof TroncoV2 ) {
			TroncoV2 umT = ( TroncoV2 )umPai;
			umT.trocaFilho( elemento, this );
		}
	}

	public int filhoIndex(Object o) throws Exception {
		int idxF = IndexUtils.indexOf(filhos, o);
		if (idxF == -1)
			throw new Exception(
					"Foi pedido um indice de filho, mas não existe!");
		return idxF;
	}

	public void trocaFilho(ElementoArvore filhoAntigo, TroncoV2 novoFilho)
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
