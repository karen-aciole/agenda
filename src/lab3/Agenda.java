package lab3;
/**
 * Uma agenda que mantém uma lista de contatos com posições. Podem existir 100 contatos. 
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
	 * Acessa a lista de contatos mantida. Seguindo a formatação abaixo:
	 * 1 - Matheus Gaudêncio
	 * 2 - Lívia Sampaio 
	 * 3 - Fábio Morais
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
	 * Acessa os dados de um contato específico. Caso o contato esteja favoritado, um coração aparece ao lado do nome. 
	 * A exibição de um contato segue a formatação abaixo: 
	 * Lívia Sampaio
	 * 9873-7383 (Prioritário) 
	 * 3213-3313 (Whatsapp)
	 * 9887-80987 (Adicional)
	 * 
	 * @param posicao Posição do contato na agenda.
	 * @return Dados do contato. Null se não há contato na posição.
	 * @throws NullPointerException Caso não exista contato na posição escolhida.
	 */	
	public String getContato(int posicao) {	
		
		String dados = "";
		int cont = 0;
		
		posicao--; 
		
		if(this.contato[posicao] == null)
			throw new NullPointerException("Sem contato na posição!");	
		
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
	 * Cadastra um contato em uma posição. Um cadastro em uma posição que já existe sobrescreve o anterior. 
	 * 
	 * @param posicao Posição do contato.
	 * @param nome Nome do contato.
	 * @param sobrenome Sobrenome do contato.
	 * @param telefonePrincipal Telefone principal do contato. 
	 * @param telefoneZap Telefone whatsapp do contato.
	 * @param telefoneAdicional Telefone adicional do contato.  
	 * @return Um valor booleano para verificar se o cadastro foi realizado. 
	 * @throws IllegalArgumentException Caso o nome seja null ou preenchido apenas com espaços. 
	 * @throws IllegalArgumentException Caso o sobrenome seja null ou preenchido apenas com espaços. 
	 * @throws IllegalArgumentException Caso o telefonePrincipal seja null ou preenchido apenas com espaços. 
	 * @throws IllegalArgumentException Caso o telefoneZap seja null ou preenchido apenas com espaços. 
	 */
	public boolean cadastraContato(int posicao, String nome, String sobrenome, String telefonePrincipal, String telefoneZap, String telefoneAdicional){	
		
		contato[--posicao] = new Contato(nome, sobrenome, telefonePrincipal, telefoneZap, telefoneAdicional);
		
		if (nome == null || nome.isBlank())
			throw new IllegalArgumentException("Nome nulo");
		
		if (sobrenome == null || sobrenome.isBlank())
			throw new IllegalArgumentException("Sobrenome nulo.");
		
		if (telefonePrincipal == null || telefonePrincipal.isBlank())
			throw new IllegalArgumentException("Telefone nulo. É necessário ter um telefone principal.");
		
		if (telefoneZap == null || telefoneZap.isBlank())
			throw new IllegalArgumentException("Telefone nulo. É necessário ter um telefone Whatsapp.");
		
		return true; 		
	}		 
	
	/**
	 * Adiciona um contato na lista de favoritos da agenda. 
	 * 
	 * @param posicao Posição do contato na agenda.
	 * @param posicaoFavorito Posição que deseja colocar o contato na lista de favoritos. 
	 * @return Um valor boolean indicando se o contato foi favoritado ou não.
	 * @throws IndexOutOfBoundsException Se for escolhida uma posição menor que 1 ou maior que 100. 
	 * @throws NullPointerException Se escolher um contato que não está cadastrado na agenda.	 
	 * @throws IndexOutOfBoundsException Se for escolhida uma posição menor que 1 ou maior que 10. 
	 */
	public boolean adicionaFavorito(int posicao, int posicaoFavorito) {
		
		
		if(posicao < 1 || posicao > 100)
			throw new IndexOutOfBoundsException("Posição Inválida!");
		
		posicao--; 
		
		if(contato[posicao] == null)
			throw new NullPointerException("Contato " + (posicao+1) + " não existe!");	
			
		
		if(posicaoFavorito < 1 || posicaoFavorito > 10)
			throw new IndexOutOfBoundsException("Posição Inválida!"); 
		
		this.favorito[--posicaoFavorito] = contato[posicao];
			
		return true;
	}
	
	/**
	 * Lista os contatos que estão na lista de favoritos. 
	 * A posição dos favoritos na lista segue a posição escolhida pelo usuário. Exemplo:
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