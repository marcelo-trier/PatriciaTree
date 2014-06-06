package br.patricia;

import br.patricia.util.IndexUtils;

public class PatriciaTreeV2 {

	public ElementoArvore raiz = null;

	public void treina(String[] umaLista) throws Exception {
		for (String vlr : umaLista) {
			add(vlr);
		}
	}

	public FolhaV2 buscaSemelhante(ElementoArvore umElem, String compara) {
		if (umElem == null)
			return null;

		if (umElem instanceof FolhaV2) {
			return (FolhaV2) umElem;
		}

		TroncoV2 umTronc = (TroncoV2) umElem;

		if (IndexUtils.verificaMenorChar(compara, umTronc.oChar, umTronc.oIndice))
			return buscaSemelhante(umElem.getEsquerda(), compara);
		else
			return buscaSemelhante(umElem.getDireita(), compara);
	}

	public void posicionaElemento( ElementoArvore umEl, TroncoV2 novoT ) throws Exception {
		novoT.adicionaFilho( umEl );
		if ( umEl == raiz ) {
			raiz = novoT;
		}
	}


	// funcao recursivaaa...
	public void adicionaTronco(ElementoArvore umElem, TroncoV2 novoTronco)
			throws Exception {

		if (umElem instanceof FolhaV2) {
			posicionaElemento( umElem, novoTronco );
			return;
		}

		if ( !(umElem instanceof TroncoV2) )
			throw new Exception( "existem tipos demais por aqui! Melhor VERIFICAR!!");

		TroncoV2 umT = (TroncoV2) umElem;
		int i1 = novoTronco.oIndice;
		char c1 = novoTronco.oChar;

		int i2 = umT.oIndice;
		char c2 = umT.oChar;

		char charFilho = IndexUtils.getChar( novoTronco.getOnlyChild().toString(), umT.oIndice );
				
		// eh AQUIIII OOO EEERRRROOOOO!!!!!
		// TODO: arrumarrrrr

		// pelo jeito aqui tem q verificar se o indice dentro de umT eh igual ao do novoTronco
		if ((i1 < i2) || (i1 == i2 && c1 < c2) ) {
			posicionaElemento( umElem, novoTronco );
			return;
		}

		if ( ( i1 == i2 && c1 == c2 ) || ( charFilho <= c2 ) ) {
			adicionaTronco(umT.getEsquerda(), novoTronco);
			return;
		}

		// TODO: compara o indice do tronco atual da arvore com o indice do filho...
		
		adicionaTronco(umT.getDireita(), novoTronco);
	}

	public void add(String s) throws Exception {

		if (raiz == null) {
			raiz = new FolhaV2(s);
			return;
		}

		FolhaV2 novaFolha = new FolhaV2(s);
		FolhaV2 umaF = buscaSemelhante(raiz, s);
		int idx = IndexUtils.indiceDaDiferenca(umaF.chave, novaFolha.chave);
		char umC;
		if (IndexUtils.verificaMenorChar(novaFolha.chave, umaF.chave, idx))
			umC = IndexUtils.getChar(novaFolha.chave, idx);
		else
			umC = IndexUtils.getChar(umaF.chave, idx);

		TroncoV2 novoTronco = new TroncoV2(idx, umC, novaFolha);
		adicionaTronco(raiz, novoTronco);
	}
}
