import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public static int MenuInicial() {

        clearWindow();
        StringBuilder sb = new StringBuilder("-----------Futebol Manager-----------\n\n");
        sb.append("1) Registar Equipas.\n");
        sb.append("2) Ver todas as equipas.\n");
        sb.append("3) Ver todos os jogadores. \n");
        sb.append("4) Fazer transferencia de jogadores entre equipas. \n");
        sb.append("5) Realizar um jogo amigavel entre duas equipas. \n");
        sb.append("6) Nova liga. \n");
        sb.append("Nota: PARA A REALIZACAO DA OPCAO 5) E 6) AS EQUIPAS EM QUESTAO JA PRECISAM DE ESTAR REGISTADAS. \n");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static void registarequipa(Faztudo a) throws ExcecaoPos {
        clearWindow();
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder("-----------Registar Equipa-----------\n\n");
        sb.append("Digite o nome da equipa: ");
        System.out.println(sb.toString());
        String nome = scanner.nextLine();

        if(!a.jaexistenome(nome))
        {
        int codnro = a.newCodeNumberequipa();
        String cod = String.valueOf(codnro);

        Equipa novo = new Equipa();
        novo.setId(cod);
        novo.setNome(nome);

        System.out.println("\n Deseja associar jogadores a equipa? \n\n1)Sim\n2)Nao");
        ArrayList<Jogador> jogadores = adicionarjogadores(a);
        novo.setJogadores(jogadores);

        ArrayList<Jogador> titulares = pertenceequipatitular(jogadores);
        novo.setEquipatitular(titulares);

        a.addEquipa(novo);

        }
        else throw new ExcecaoPos("Nome ja existe");
    }

    public static void verequipas(Faztudo a) {
        clearWindow();
        StringBuilder sb = new StringBuilder("-----------Todas as Equipas-----------\n\n");
        sb.append(a.toString());
        System.out.println(sb.toString());
    }

    public static ArrayList<Jogador> adicionarjogadores(Faztudo a) throws ExcecaoPos {

        ArrayList<Jogador> res = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int op = scanner.nextInt();

        if(op == 1){
            res.add(registajogador(a));
            System.out.println("\nDeseja registar mais jogadores? \n\n1)Sim\n2)Nao");
            res.addAll(adicionarjogadores(a));

        }
        else {
        if(op == 2){
            return res;
        }
        else {

            adicionarjogadores(a);
            throw new ExcecaoPos("Opcao desconhecida");
        }
        }
    return res;
    }

    public static Jogador registajogador(Faztudo a) throws ExcecaoPos {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n Digite o nome do jogador: ");
        String nome = scanner.nextLine();
        Jogador jog = new Jogador();

        if(!a.jaexitenomejogador(nome)){
            jog.setNome(nome);
            System.out.println("\nQual a posicao do jogador? \n");
            String pos = scanner.nextLine();
            Posicao p = new Posicao(pos);
            jog.setPosicao(p);
            System.out.println("\nQual a habilidade geral do jogador? \n");
            int hab = scanner.nextInt();
            jog.setHabilidade(hab);
            int cod = a.newCodeNumberjogador();
            String codi = String.valueOf(cod);
            jog.setId(codi);
            return jog;
        }
        else throw new ExcecaoPos("Nome de jogador ja registado");
    }

    public static void todosjogadores(Faztudo a){
        System.out.println(a.toStringjogadores());
    }
    public static void clearWindow() {
        for (int i = 0;i<100;i++){
            System.out.println();
        }
    }

    public static void faztransferencia(Faztudo a) throws ExcecaoPos {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nA que equipa pertence o jogador a ser transferido");
        String nome = scanner.nextLine();
        Equipa sai = a.identificaEquipa(nome);
        System.out.println("\nPara que equipa e que o jogador vai");
        String outro = scanner.nextLine();
        Equipa entra = a.identificaEquipa(outro);
        System.out.println("\nNome do jogador: ");
        String nomejogador = scanner.nextLine();
        Jogador jog = a.identificaJogador(nomejogador);
        a.tranfere(jog,sai,entra);
        ArrayList<Jogador> teste = new ArrayList<>();
        teste.set(0,jog);
        if(pertenceequipatitular(teste).contains(jog)){
            entra.addjogequipatitular(jog);
        }

    }

    public static ArrayList<Jogador> pertenceequipatitular(ArrayList<Jogador> jog) throws ExcecaoPos {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Jogador> titulares = new ArrayList<>();

        for(Jogador j : jog){
        System.out.println("O jogador pertence a equipa titular?" + j.getNome() +"\n\n1)Sim\n2)Nao");
        int op = scanner.nextInt();
        if(op == 1){
            titulares.add(j);
            if(jog.size()>1){
            jog.remove(j);
            titulares.addAll(pertenceequipatitular(jog));
            }
        }
        else {
            if(op == 2){

            }
            else{
                pertenceequipatitular(jog);
                throw new ExcecaoPos("Opcao desconhecida");
            }
        }
        }
        return titulares;
    }
}
