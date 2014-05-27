package br.patricia;

import br.patricia.ui.ElementoUI;
import br.patricia.util.IndexUtils;

enum TIPO {
	FIM, MEIO;
}

enum FILHOS {
	ESQ, DIR;
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

	public void adicionaFilho(Nodo umNoh) throws Exception {
		int filhoI;
		if( filhos[ FILHOS.ESQ.ordinal() ] == null )
			filhoI = 0;
		else if( filhos[ FILHOS.DIR.ordinal() ] == null )
			filhoI = 1;
		else
			throw new Exception( "esta rotina deveria adicionar um filho em um espaço existente..." );
		
		filhos[ filhoI ] = umNoh;
	}

	Nodo osDois[] = { null, null };
	int tamanho[] = { 0, 0 };
	char oChar[] = { 0, 0 };

	public FILHOS adicionaFilho( Nodo umFilho, String compara ) throws Exception {
		// antes de tudo, diz de quem o Nodo eh filhooo..
		umFilho.pai = this;

		index = IndexUtils.indiceDaDiferenca( umFilho.chave,  compara );
		char c1 = IndexUtils.getChar( umFilho.chave, index );
		char c2 = IndexUtils.getChar( compara, index );
		FILHOS f = FILHOS.ESQ;
		this.c = c1;

		if( c2 < c1 ) {
			f = FILHOS.DIR;
			this.c = c2;
		}

		filhos[ f.ordinal() ] = umFilho;
		return f;
	}

	public void adicionaFilhos(Nodo umNoh, Nodo outroNoh) throws Exception {
		osDois[0]=umNoh; osDois[1]=outroNoh;

		tipo = TIPO.MEIO;
		chave = null;

		index = IndexUtils.indiceDaDiferenca(umNoh.chave, outroNoh.chave);
		for( int j=0; j<osDois.length; j++ ) {
			tamanho[j]=osDois[j].chave.length();
			oChar[j] = IndexUtils.getChar( osDois[j].chave, index );
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

	public void trocaFilho(Nodo filhoAntigo, Nodo novoFilho) throws Exception {

		for (int i = 0; i < filhos.length; i++) {
			if (filhos[i] != filhoAntigo)
				continue;
			filhos[i].pai = null; // apaga a relacao q o filho tem com o pai
			novoFilho.pai = this; // o novo filho aponta para o pai..
			filhos[i] = novoFilho; // o pai reconhece o novo filho
			return;
		}

		throw new Exception("Nao encontrou nenhum filho... estranhooo");
	}

/*	
	public static char getChar(String s, int i) {
		if (s.length() <= i)
			return 0;
		return s.charAt(i);
	}

	public char getChar(int i) {
		return getChar(chave, i);
	}
*/
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

/*
	public boolean ehMenor(int dif, String s) throws Exception {
		if (chave.charAt(dif) == s.charAt(dif))
			throw new Exception(
					"Erro! Os caracteres não deveriam ser iguais... :(");

		return (getChar(dif) < getChar(s, dif));
	}
*/
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
