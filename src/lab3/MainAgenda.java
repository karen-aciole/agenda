package lab3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interface com menus texto para manipular uma agenda de contatos.
 * 
 * @author nazarenoandrade
 *
 */
public class MainAgenda {

	public static void main(String[] args) {
		Agenda agenda = new Agenda();

		System.out.println("Carregando agenda inicial");
		try {
			/*
			 * Essa � a maneira de lidar com poss�veis erros por falta do arquivo. 
			 */
			carregaAgenda("agenda_inicial.csv", agenda);
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo n�o encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Erro lendo arquivo: " + e.getMessage());
		}

		Scanner scanner = new Scanner(System.in);
		String escolha = "";
		while (true) {
			escolha = menu(scanner);
			comando(escolha, agenda, scanner);
		}

	}

	/**
	 * Exibe o menu e captura a escolha do/a usu�rio/a.
	 * 
	 * @param scanner Para captura da op��oo do usu�rio.
	 * @return O comando escolhido.
	 */
	private static String menu(Scanner scanner) {
		System.out.println(
				"\n---\nMENU\n" + 
						"(C)adastrar Contato\n" + 
						"(L)istar Contatos\n" + 
						"(E)xibir Contato\n" + 
						"(F)avoritos\n" + 
						"(A)dicionar Favorito\n" +
						"(S)air\n" + 
						"\n" + 
						"Op��o> ");
		return scanner.next().toUpperCase();
	}

	/**
	 * Interpreta a op��o escolhida por quem est�o usando o sistema.
	 * 
	 * @param opcao   Op��o digitada.
	 * @param agenda  A agenda que estamos manipulando.
	 * @param scanner Objeto scanner para o caso do comando precisar de mais input.
	 */
	private static void comando(String opcao, Agenda agenda, Scanner scanner) {
		switch (opcao) {
		case "C":
			cadastraContato(agenda, scanner);
			break;
		case "L":
			listaContatos(agenda);
			break;
		case "E":
			exibeContato(agenda, scanner);
			break;
		case "F": 
			listaFavoritos(agenda);
			break; 
		case "A":
			adicionaFavorito(agenda, scanner);
			break;
		case "S":
			sai();
			break;
		default:
			System.out.println("OP��O INV�LIDA!");
		}
	}

	/**
	 * Imprime lista de contatos da agenda.
	 * 
	 * @param agenda A agenda sendo manipulada.
	 */
	private static void listaContatos(Agenda agenda) {
		System.out.println("Lista de contatos: ");
		String contatos = agenda.getContatos();
		if (contatos.isEmpty()) {
			System.out.println("Nenhum contato cadastrado");	
		}else {
			System.out.println(contatos);
		}
	}
	/**
	 * Imprime lista de favoritos da agenda.
	 * 
	 * @param agenda A agenda sendo manipulada.
	 */
	private static void listaFavoritos(Agenda agenda) {
		System.out.println("Lista de favoritos: ");
		String favoritos = agenda.getFavoritos();
		if (favoritos.isEmpty()) {
			System.out.println("Nenhum contato favoritado. :(");
		}else {
			System.out.println(favoritos);
		}
	}

	/**
	 * Imprime os detalhes de um dos contatos da agenda. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para capturar qual contato.
	 */
	private static void exibeContato(Agenda agenda, Scanner scanner) {
		System.out.print("\nQual contato> ");
		int posicao = scanner.nextInt();
		if (posicao < 1 || posicao > 100) {
			System.out.println("POSI��O INV�LIDA");
		}else {
			String contato = agenda.getContato(posicao);		
			System.out.println(contato);
		}
	}


	/**
	 * Cadastra um contato na agenda. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para pedir informa��es do contato.
	 */
	private static void cadastraContato(Agenda agenda, Scanner scanner) {
		System.out.println("\nPosi��o> ");
		int posicao = scanner.nextInt();
		if (posicao < 1 || posicao > 100) {
			System.out.println("POSI��O INV�LIDA");
		}else {
			scanner.nextLine();
			System.out.print("Nome: ");	
			String nome = scanner.nextLine();
			if (nome == null ||nome.isBlank())
				throw new IllegalArgumentException("Nome nulo.");
			
			System.out.print("Sobrenome: ");	
			String sobrenome = scanner.nextLine();
			if (sobrenome == null || sobrenome.isBlank())
				throw new NullPointerException("Sobrenome nulo.");
			
			System.out.print("Telefone priorit�rio: ");	
			String telefonePrincipal = scanner.nextLine();
			if (telefonePrincipal == null || telefonePrincipal.isBlank())
				throw new NullPointerException("Telefone nulo. � necess�rio ter um telefone principal.");
			
			System.out.print("Telefone Whatsapp: ");	
			String telefoneZap = scanner.nextLine();
			
			if (telefoneZap == null || telefoneZap.isBlank())
				throw new NullPointerException("Telefone nulo. � necess�rio ter um telefone Whatsapp.");
			
			System.out.print("Telefone Adicional: ");	
			String telefoneAdicional = scanner.nextLine();
		
		
			if (telefoneAdicional.equals(telefonePrincipal) || telefoneAdicional.equals(telefoneZap))
				telefoneAdicional = null;
		
			agenda.cadastraContato(posicao, nome, sobrenome, telefonePrincipal, telefoneZap, telefoneAdicional);
			System.out.println("CADASTRO REALIZADO");
		}
	}
	/**
	 * Adiciona um contato como favorito.
	 * 
	 * @param agenda
	 * @param scanner
	 */
	private static void adicionaFavorito(Agenda agenda, Scanner scanner) {
		System.out.println("\nContato> ");
		int posicao = scanner.nextInt();
		
		if (posicao < 1 || posicao > 100) {
			System.out.println("CONTATO INV�LIDO!");
		}else { 
			System.out.println("Posicao> ");
			int posicaoFavorito = scanner.nextInt(); 
			agenda.adicionaFavorito(posicao, posicaoFavorito);
			System.out.println("CONTATO FAVORITADO NA POSI��O " + posicaoFavorito + "!");
					
		}
	}

	/**
	 * Sai da aplica��o.
	 */
	private static void sai() {
		System.out.println("\nVlw flw o/");
		System.exit(0);
	}

	/**
	 * L� uma agenda de um arquivo csv. 
	 * 
	 * @param arquivoContatos O caminho para o arquivo.
	 * @param agenda A agenda que deve ser populada com os dados. 
	 * @throws IOException Caso o arquivo n�o exista ou n�oe possa ser lido.
	 */
	private static void carregaAgenda(String arquivoContatos, Agenda agenda) throws FileNotFoundException, IOException {
		LeitorDeAgenda leitor = new LeitorDeAgenda();
		
		int carregados =  leitor.carregaContatos(arquivoContatos, agenda);
		System.out.println("Carregamos " + carregados + " registros.");
	}
}
