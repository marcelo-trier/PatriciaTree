package br.patricia;

import br.patricia.ui.ElementoUI;
import br.patricia.util.StringUtil;

enum TIPO {
	FIM, MEIO;
}

enum FILHOS {
	DIR, ESQ;
}

class Nodo implements ElementoUI {
	Nodo filhos[] = { null, null }; // filhos da direita ou esquerda
	Nodo pai = null;
	int index = 0; // claro, aqui eh o indice da diferenca...
	char c = 0; // o caracter do nodo
	String chave = null; // a chave que o nodo terá, caso seja terminal
	TIPO tipo = TIPO.FIM; // aqui uma informação dizendo se o nodo é terminal

	public Nodo(TIPO t) {
		tipo = t;
	}

	public void adicionaFilho(Nodo umNoh) {

	}

	Nodo osDois[] = { null, null };
	int tamanho[] = { 0, 0 };
	char oChar[] = { 0, 0 };
	public void adicionaFilhos(Nodo umNoh, Nodo outroNoh) throws Exception {
		osDois[0]=umNoh; osDois[1]=outroNoh;

		tipo = TIPO.MEIO;
		chave = null;

		index = StringUtil.indiceDaDiferenca(umNoh.chave, outroNoh.chave);
		for( int j=0; j<osDois.length; j++ ) {
			tamanho[j]=osDois[j].chave.length();
			oChar[j] = ( tamanho[j] > index ) ? osDois[j].chave.charAt( index ) : 0;
			osDois[j].pai = this;
		}

		int menor = 0; // considera q o primeiro eh o menor
		if( oChar[0] > oChar[1] )
			menor = 1;
		else if( oChar[0] == oChar[1] )// aqui, os caracteres sao iguais
		{
			oChar[menor] = 0;
			index++;
		}

		this.c = oChar[ menor ];
		int maior = ( menor + 1) % 2; // esta eh a maneira de pegar o numero invertido... ou [0] ou [1]
			
		filhos[FILHOS.ESQ.ordinal()] = osDois[ menor ];
		filhos[FILHOS.DIR.ordinal()] = osDois[ maior ];
	}

	// soh adiciona filhos quem eh do meio... =)
	public void adicionaFilhos__OLD(Nodo umNoh, Nodo outroNoh) throws Exception {
		tipo = TIPO.MEIO;
		chave = null;

		index = StringUtil.indiceDaDiferenca(umNoh.chave, outroNoh.chave);

		// if( umNoh.chave.charAt( index ) == outroNoh.chave.charAt( index ) )
		// throw new Exception(
		// "O indice da diferenca dos dois nodos eh iguall... verificar!" );

		// String base;
		Nodo dir, esq;
		int l1 = umNoh.chave.length();
		int l2 = outroNoh.chave.length();
		int delta = l1 - l2;
		char oChar = 0;
		if (delta != 0) { // o tamanho deles nao eh igual
			if (l1 < l2) {
				// base = umNoh.chave;
				dir = outroNoh;
				esq = umNoh;
			} else {
				// base = outroNoh.chave;
				dir = umNoh;
				esq = outroNoh;
			}
		} else { // aqui o delta == zero
			if (umNoh.chave.charAt(index) < outroNoh.chave.charAt(index)) {
				oChar = umNoh.getChar(index);
				dir = outroNoh;
				esq = umNoh;
			} else {
				oChar = outroNoh.getChar(index);
				dir = umNoh;
				esq = outroNoh;
			}
		}

		this.c = oChar;
		filhos[FILHOS.ESQ.ordinal()] = esq;
		filhos[FILHOS.DIR.ordinal()] = dir;

		for (int i = 0; i < filhos.length; i++) {
			filhos[i].pai = this;
		}
	}

	public void trocaFilho(Nodo oFilho, Nodo outroNoh) throws Exception {

		for (int i = 0; i < filhos.length; i++) {
			if (filhos[i] != oFilho)
				continue;
			filhos[i].pai = null; // apaga a relacao q o filho tem com o pai
			outroNoh.pai = this; // o novo filho aponta para o pai..
			filhos[i] = outroNoh; // o pai reconhece o novo filho
			return;
		}

		throw new Exception("Nao encontrou nenhum filho... estranhooo");
	}

	public void trocaFilho__OLD(Nodo oFilho, Nodo outroNoh) throws Exception {

		for (int i = 0; i < filhos.length; i++) {
			if (filhos[i] != oFilho)
				continue;
			filhos[i].pai = null; // apaga a relacao q o filho tem com o pai
			outroNoh.pai = this; // o novo filho aponta para o pai..
			filhos[i] = outroNoh; // o pai reconhece o novo filho
			return;
		}

		throw new Exception("Nao encontrou nenhum filho... estranhooo");
	}

	public static char getChar(String s, int i) {
		if (s.length() <= i)
			return 0;
		return s.charAt(i);
	}

	public char getChar(int i) {
		return getChar(chave, i);
	}

	public boolean ehIgual(String s) {
		if (chave == null)
			return false;
		if (s == null)
			return false;

		return chave.equalsIgnoreCase(s);
	}

	public boolean ehFim() throws Exception {
		if (tipo == TIPO.FIM && chave == null)
			throw new Exception("ERROOO! Existe um noh fim sem chaveee!!");
		return (tipo == TIPO.FIM);
	}

	public boolean ehMenor(int dif, String s) throws Exception {
		if (chave.charAt(dif) == s.charAt(dif))
			throw new Exception(
					"Erro! Os caracteres não deveriam ser iguais... :(");

		return (getChar(dif) < getChar(s, dif));
	}

	@Override
	public ElementoUI getDireita() {
		return filhos[FILHOS.DIR.ordinal()];
	}

	@Override
	public ElementoUI getEsquerda() {
		return filhos[FILHOS.ESQ.ordinal()];
	}

	@Override
	public String toString() {
		String str;

		if (tipo == TIPO.FIM)
			str = chave;
		else {
			char outroC = (this.c == 0) ? '_' : this.c;
			str = "" + index + "/" + outroC;
		}
		return str;
	}
}
