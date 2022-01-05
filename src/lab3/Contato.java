package lab3;

/**
 * Armazena os dados de um contato. 
 * Todo contato tem um nome, sobrenome, telefone principal, telefone whatsapp e um telefone adicional. 
 * 
 * @author Karen Anne Aciole Alves - 119210934
 *
 */
public class Contato {
	
	/**
	 * Nome do contato. 
	 */
	private String nome;
	
	/**
	 * Sobrenome do contato. 
	 */
	private String sobrenome;
	
	/**
	 * Telefone principal do contato. 
	 */
	private String telefonePrincipal;
	
	/**
	 * Telefone Whatsapp do contato. 
	 */
	private String telefoneZap;
	
	/**
	 * Telefone adicional do contato. 
	 */
	private String telefoneAdicional; 
	
	
	/**
	 * Constrói o contato. 
	 * @param nome Nome do contato
	 * @param sobrenome Sobrenome do contato
	 * @param telefonePrincipal Telefone prioritário
	 * @param telefoneZap Telefone whatsapp
	 * @param telefoneAdicional Telefone adicional
	 */
	public Contato(String nome, String sobrenome, String telefonePrincipal, String telefoneZap, String telefoneAdicional) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.telefonePrincipal = telefonePrincipal;
		this.telefoneZap = telefoneZap;
		this.telefoneAdicional = telefoneAdicional;
	}
	
	/**
	 * Formata a exibição dos dados de um contato. Caso o contato não possua um telefone adicional, o telefone adicional não é mostrado.
	 * Exemplo: 
	 * Freddy Mercury 
	 * 9123-39123 (Prioritário)
	 * 3920-1234 (Whatsapp)
	 * 1222-1222 (Adicional
	 * 
	 * @return String formatada com os detalhes do contato.
	 */
	@Override
	public String toString() {
		if ((this.telefoneAdicional == null) || (this.telefoneAdicional.isBlank())) {
			return this.nome + " " + this.sobrenome + "\n" + this.telefonePrincipal + " (Prioritário) \n" + this.telefoneZap + " (Whatsapp)";
		}else {
		return this.nome + " " + this.sobrenome + "\n" + this.telefonePrincipal + " (Prioritário) \n" + this.telefoneZap + " (Whatsapp) \n" + this.telefoneAdicional + " (Adicional)";		
		}
	}
	
	/**
	 * String formatada do nome e sobrenome. Exemplo: Michael Jackson. 
	 * @return Nome completo do contato.
	 */
	public String getNomeCompleto() {
		return this.nome + " " + this.sobrenome;
	}
	
	/**
	 * Verifica se dois contatos são iguais. Eles são iguais se possuem o mesmo nome e sobrenome.
	 * @param other Outro contato.
	 * @return Um valor booleano.
	 */

	@Override
	public boolean equals(Object other)
	{
	    if (other == null) {return false; }

	    if (other == this) { return true; }

	    if (!(other instanceof Contato)) {return false; }

	    Contato o = (Contato) other;

	    if ((o.nome == this.nome) && (o.sobrenome == this.sobrenome)) {return true; } 
	    else {return false; }
	}
}
	
	
