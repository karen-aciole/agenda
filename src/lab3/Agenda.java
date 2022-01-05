package lab3;
/**
 * Uma agenda que mant�m uma lista de contatos com posi��es. Podem existir 100 contatos. 
 * Dentro da agenda possui uma lista de favoritos. Podem existir 10 contatos na lista de favoritos.
 * 
 * @author nazareno
 *
 */
public class Agenda {
	
	/**
	 * Tamanho da agenda.
	 */
	private static final int TAMANHO_AGENDA = 100;
	
	/**
	 * Array para armazenar os dados do contato.
	 */
	private Contato[] contato;
	
	/**
	 * Array para armazenar os favoritos.
	 */
	private Contato[] favorito;
	
	/**
	 * Cria uma agenda.
	 */
	public Agenda() {
		contato = new Contato[TAMANHO_AGENDA];
		favorito = new Contato[10];
	}
	
	/**
	 * Acessa a lista de contatos mantida. Seguindo a formata��o abaixo:
	 * 1 - Matheus Gaud�ncio
	 * 2 - L�via Sampaio 
	 * 3 - F�bio Morais
	 * 4 - Nazareno Andrade
	 * 
	 * @return O array de contatos.
	 */
	public String getContatos() {
		String listaContatos = ""; 
		
		for (int i = 0; i < this.contato.length; i++) {
			if (this.contato[i] != null) {			
				listaContatos += "\n" + ((i+1) + " - " + contato[i].getNomeCompleto());
			}
		}
		return listaContatos;
	}
	

	/**
	 * Acessa os dados de um contato espec�fico. Caso o contato esteja favoritado, um cora��o aparece ao lado do nome. 
	 * A exibi��o de um contato segue a formata��o abaixo: 
	 * L�via Sampaio
	 * 9873-7383 (Priorit�rio) 
	 * 3213-3313 (Whatsapp)
	 * 9887-80987 (Adicional)
	 * 
	 * @param posicao Posi��o do contato na agenda.
	 * @return Dados do contato. Null se n�o h� contato na posi��o.
	 * @throws NullPointerException Caso n�o exista contato na posi��o escolhida.
	 */	
	public String getContato(int posicao) {	
		
		String dados = "";
		int cont = 0;
		
		posicao--; 
		
		if(this.contato[posicao] == null)
			throw new NullPointerException("Sem contato na posi��o!");	
		
		for (int i = 0; i < favorito.length; i++) {
			if (contato[posicao] == favorito[i]) {
				cont+=1;
			}
		}
			
		if (cont == 0) {
			dados += this.contato[posicao].toString();
		}else {
			dados += "<3" + " " + this.contato[posicao].toString();
		}
		
		return dados;
	}
	
	/**
	 * Cadastra um contato em uma posi��o. Um cadastro em uma posi��o que j� existe sobrescreve o anterior. 
	 * 
	 * @param posicao Posi��o do contato.
	 * @param nome Nome do contato.
	 * @param sobrenome Sobrenome do contato.
	 * @param telefonePrincipal Telefone principal do contato. 
	 * @param telefoneZap Telefone whatsapp do contato.
	 * @param telefoneAdicional Telefone adicional do contato.  
	 * @return Um valor booleano para verificar se o cadastro foi realizado. 
	 * @throws IllegalArgumentException Caso o nome seja null ou preenchido apenas com espa�os. 
	 * @throws IllegalArgumentException Caso o sobrenome seja null ou preenchido apenas com espa�os. 
	 * @throws IllegalArgumentException Caso o telefonePrincipal seja null ou preenchido apenas com espa�os. 
	 * @throws IllegalArgumentException Caso o telefoneZap seja null ou preenchido apenas com espa�os. 
	 */
	public boolean cadastraContato(int posicao, String nome, String sobrenome, String telefonePrincipal, String telefoneZap, String telefoneAdicional){	
		
		contato[--posicao] = new Contato(nome, sobrenome, telefonePrincipal, telefoneZap, telefoneAdicional);
		
		if (nome == null || nome.isBlank())
			throw new IllegalArgumentException("Nome nulo");
		
		if (sobrenome == null || sobrenome.isBlank())
			throw new IllegalArgumentException("Sobrenome nulo.");
		
		if (telefonePrincipal == null || telefonePrincipal.isBlank())
			throw new IllegalArgumentException("Telefone nulo. � necess�rio ter um telefone principal.");
		
		if (telefoneZap == null || telefoneZap.isBlank())
			throw new IllegalArgumentException("Telefone nulo. � necess�rio ter um telefone Whatsapp.");
		
		return true; 		
	}		 
	
	/**
	 * Adiciona um contato na lista de favoritos da agenda. 
	 * 
	 * @param posicao Posi��o do contato na agenda.
	 * @param posicaoFavorito Posi��o que deseja colocar o contato na lista de favoritos. 
	 * @return Um valor boolean indicando se o contato foi favoritado ou n�o.
	 * @throws IndexOutOfBoundsException Se for escolhida uma posi��o menor que 1 ou maior que 100. 
	 * @throws NullPointerException Se escolher um contato que n�o est� cadastrado na agenda.	 
	 * @throws IndexOutOfBoundsException Se for escolhida uma posi��o menor que 1 ou maior que 10. 
	 */
	public boolean adicionaFavorito(int posicao, int posicaoFavorito) {
		
		
		if(posicao < 1 || posicao > 100)
			throw new IndexOutOfBoundsException("Posi��o Inv�lida!");
		
		posicao--; 
		
		if(contato[posicao] == null)
			throw new NullPointerException("Contato " + (posicao+1) + " n�o existe!");	
			
		
		if(posicaoFavorito < 1 || posicaoFavorito > 10)
			throw new IndexOutOfBoundsException("Posi��o Inv�lida!"); 
		
		this.favorito[--posicaoFavorito] = contato[posicao];
			
		return true;
	}
	
	/**
	 * Lista os contatos que est�o na lista de favoritos. 
	 * A posi��o dos favoritos na lista segue a posi��o escolhida pelo usu�rio. Exemplo:
	 * 1 - Ian Curtis
	 * 2 - David Bowie
	 * 7 - Liam Gallagher 
	 * 
	 * @return Uma lista com o nome dos contatos favoritados.
	 */
	public String getFavoritos() {
		String listaFavoritos = "";
		
		for (int j = 0; j < this.favorito.length; j++) 
			if (this.favorito[j] != null) 
				listaFavoritos += "\n" + ((j+1) + " - " + favorito[j].getNomeCompleto());
		
		return listaFavoritos;
	}
	
}