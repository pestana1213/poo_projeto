import java.io.IOException;
import java.lang.module.ModuleDescriptor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    private static int menuinicial() {
        clearWindow();
        StringBuilder sb = new StringBuilder("-----------Futebol Manager-----------\n\n");
        //load a partir de ficheiro
        sb.append("1) Registar Equipas.\n");
        sb.append("2) Ver todas as equipas.\n");
        sb.append("3) Ver todos os jogadores. \n");
        sb.append("4) Adicionar jogador associado a equipa. \n");
        sb.append("5) Fazer transferencia de jogadores entre equipas. \n");
        sb.append("6) Jogos. \n");
        sb.append("7) Nova liga. \n");
        sb.append("0) Sair. \n");
        sb.append("Nota: PARA A REALIZACAO DA OPCAO 6) E 7) AS EQUIPAS EM QUESTAO JA PRECISAM DE ESTAR REGISTADAS. \n");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static void registarequipa(Faztudo a) throws ExcecaoPos, InterruptedException, IOException {
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
        novo.guardaequipa();
        }
        else {
            System.out.println("\nNome de equipa ja existente\n");
            System.out.println("\nDeseja continuar?\n\n1)Sim\n2)Nao\n");
            int op = scanner.nextInt();

            switch (op) {
                case (1):
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
                    novo.guardaequipa();
                    break;
                case (2):
                    volta(a);
                    break;
                default:
                    System.out.println("\nOpcao desconhecida \n");
                    menu(a);
                    break;
            }
        }
    }

    private static void verequipas(Faztudo a) throws ExcecaoPos, InterruptedException, IOException {
        clearWindow();
        Scanner ler = new Scanner(System.in);
        System.out.println("-----------Todas as Equipas-----------\n\n");
        System.out.println(a.toString());

        System.out.println("\nDeseja ver uma equipa em especifico?\n1)Sim\n2)Nao");
        int op = ler.nextInt();
        switch (op){
            case(1):
                System.out.println("Introduza o nome da equipa que deseja ver:");
                ler.nextLine();
                String nome = ler.nextLine();
                System.out.println(nome);
                Equipa especifica = equipasmmnome(a,nome);
                System.out.println(especifica);
                System.out.println("\nDeseja fazer alguma acao em especifico na equipa?\n1)Sim\n2)Nao");
                acoesequipa(a,especifica);
                break;
            case(2):
                volta(a);
                break;
            default:
                break;
        }
    }

    private static void acoesequipa(Faztudo a, Equipa equipa) throws ExcecaoPos, InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        int op = scanner.nextInt();
        switch (op){
            case(1):
                System.out.println("Acoes:\n1)Retirar jogador da equipa titular\n2)Adicionar jogador a equipa titular");
                int caso = scanner.nextInt();
                switch (caso){
                    case(1):
                        System.out.println("Que jogador pretende retirar da equipa titular?");
                        scanner.nextLine();
                        String nome = scanner.nextLine();
                        ArrayList<Jogador> jogadores = equipa.jogadoresnome(nome);
                        Jogador jog = selecionajogador(a,jogadores);
                        equipa.removeJogadorTitular(jog);
                        a.update(equipa);
                        facilita(a,equipa);
                    case(2):
                        System.out.println("Que jogador pretende adicionar a equipa titular?");
                        scanner.nextLine();
                        String n = scanner.nextLine();
                        Jogador j = jogadormmnome(a,n);
                        equipa.addjogequipatitular(j);
                        a.update(equipa);
                        System.out.println("\nJogador adicionado a equipa titular com sucesso");
                        facilita(a,equipa);
                        break;
                    default:
                        break;
                }
                break;
            case(2):
                volta(a);
                break;
            default:
                break;
        }
    }

    private static void facilita(Faztudo a, Equipa equipa) throws ExcecaoPos, InterruptedException, IOException {      //previne a repeticao do ciclo na funcao de cima
        Scanner scanner = new Scanner(System.in);
        System.out.println("Deseja fazer mais alguma acao na equipa? " + equipa.getNome() + "\n1)Sim\n2)Nao");
        int opcao = scanner.nextInt();
        switch (opcao){
            case(1):
                acoesequipa(a,equipa);
                break;
            case(2):
                volta(a);
                break;
            default:
                break;
        }
    }

    private static Jogador selecionajogador(Faztudo a,ArrayList<Jogador> jogs){
        Jogador res = null;
        Scanner scanner = new Scanner(System.in);
        if(jogs.size()==1){
            res = jogs.get(0);
        }
        else{
            if(jogs.size()>1){
                System.out.println("Jogadores com o mesmo nome: ");
                jogs.stream().map(Jogador::toStringcomid).forEach(System.out::println);

                System.out.println("\nA que jogador se refere?\nIdentificar pelo id\n");
                String id = scanner.nextLine();
                for (Jogador jo : jogs) {
                    if (id.equalsIgnoreCase(jo.getId())) {
                        res = jo;
                    }
                }
            }
            else{
                System.out.println("Jogador desconhecido");
            }
        }
        return res;
    }

    private static ArrayList<Jogador> adicionarjogadores(Faztudo a) throws ExcecaoPos {

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
            System.out.println("Opcao desconhecida");
        }
        }
    return res;
    }

    private static Defesa registaDefesa(Faztudo a) throws ExcecaoPos{
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nDigite o nome do jogador: ");
        String nome = scanner.nextLine();

        Defesa jog = new Defesa();

        if(!a.jaexitenomejogador(nome)){
            System.out.println("\nQuanta resistencia tem o jogador?\n");
            int resistencia = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta velocidade tem o jogador?\n");
            int velocidade = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta destreza tem o jogador?\n");
            int destreza = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta impulsao tem o jogador?\n");
            int impulssao = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta habilidade para cabecear tem o jogador?\n");
            int cabeceamento = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta habilidade para rematar tem o jogador?\n");
            int remate = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta habilidade para passar tem o jogador?\n");
            int passe = scanner.nextInt();
            scanner.nextLine();
            int cod = a.newCodeNumberjogador();
            String codi = String.valueOf(cod);
            jog = new Defesa(codi,nome,velocidade,resistencia,destreza,impulssao,cabeceamento,remate,passe);
        }
        else {
            System.out.println("Nome de jogador ja existente\n\nDeseja continuar?\n1)Sim\n2)Nao");
            int op = scanner.nextInt();

            switch (op){
                case(1):
                    System.out.println("\nQuanta resistencia tem o jogador?\n");
                    int resistencia = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta velocidade tem o jogador?\n");
                    int velocidade = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta destreza tem o jogador?\n");
                    int destreza = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta impulsao tem o jogador?\n");
                    int impulssao = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta habilidade para cabecear tem o jogador?\n");
                    int cabeceamento = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta habilidade para rematar tem o jogador?\n");
                    int remate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta habilidade para passar tem o jogador?\n");
                    int passe = scanner.nextInt();
                    scanner.nextLine();;
                    int cod = a.newCodeNumberjogador();
                    String codi = String.valueOf(cod);
                    jog = new Defesa(codi,nome,velocidade,resistencia,destreza,impulssao
                    ,cabeceamento,remate,passe);

                    break;
                case(2):
                    registajogador(a);
                    break;
                default:
                    break;
            }

        }
        return jog;
    }

    private static Medio registaMedio(Faztudo a) throws ExcecaoPos{
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nDigite o nome do jogador: ");
        String nome = scanner.nextLine();

        Medio jog = new Medio();

        if(!a.jaexitenomejogador(nome)){
            System.out.println("\nQuanta resistencia tem o jogador?\n");
            int resistencia = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta velocidade tem o jogador?\n");
            int velocidade = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta destreza tem o jogador?\n");
            int destreza = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta impulsao tem o jogador?\n");
            int impulssao = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta habilidade para cabecear tem o jogador?\n");
            int cabeceamento = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta habilidade para rematar tem o jogador?\n");
            int remate = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta habilidade para passar tem o jogador?\n");
            int passe = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta habilidade para recuperar a bola tem o jogador?\n");
            int recuperacao = scanner.nextInt();
            scanner.nextLine();
            int cod = a.newCodeNumberjogador();
            String codi = String.valueOf(cod);
            jog = new Medio(codi,nome,velocidade,resistencia,destreza,impulssao
                    ,cabeceamento,remate,passe,recuperacao);
        }
        else {
            System.out.println("Nome de jogador ja existente\n\nDeseja continuar?\n1)Sim\n2)Nao");
            int op = scanner.nextInt();

            switch (op){
                case(1):
                    System.out.println("\nQuanta resistencia tem o jogador?\n");
                    int resistencia = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta velocidade tem o jogador?\n");
                    int velocidade = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta destreza tem o jogador?\n");
                    int destreza = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta impulsao tem o jogador?\n");
                    int impulssao = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta habilidade para cabecear tem o jogador?\n");
                    int cabeceamento = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta habilidade para rematar tem o jogador?\n");
                    int remate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta habilidade para passar tem o jogador?\n");
                    int passe = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta habilidade para recuperar a bola tem o jogador?\n");
                    int recuperacao = scanner.nextInt();
                    scanner.nextLine();
                    int cod = a.newCodeNumberjogador();
                    String codi = String.valueOf(cod);
                    jog = new Medio(codi,nome,velocidade,resistencia,destreza,impulssao
                            ,cabeceamento,remate,passe,recuperacao);
                    break;
                case(2):
                    registajogador(a);
                    break;
                default:
                    break;
            }

        }
        return jog;
    }

    private static Lateral registaLateral(Faztudo a) throws ExcecaoPos{
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nDigite o nome do jogador: ");
        String nome = scanner.nextLine();

        Lateral jog = new Lateral();

        if(!a.jaexitenomejogador(nome)){
            System.out.println("\nQuanta resistencia tem o jogador?\n");
            int resistencia = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta velocidade tem o jogador?\n");
            int velocidade = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta destreza tem o jogador?\n");
            int destreza = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta impulsao tem o jogador?\n");
            int impulssao = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta habilidade para cabecear tem o jogador?\n");
            int cabeceamento = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta habilidade para rematar tem o jogador?\n");
            int remate = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta habilidade para passar tem o jogador?\n");
            int passe = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta habilidade para cruzar a bola tem o jogador?\n");
            int cruzamento = scanner.nextInt();
            scanner.nextLine();
            int cod = a.newCodeNumberjogador();
            String codi = String.valueOf(cod);
            jog = new Lateral(codi,nome,velocidade,resistencia,destreza,impulssao
                    ,cabeceamento,remate,passe,cruzamento);
        }
        else {
            System.out.println("Nome de jogador ja existente\n\nDeseja continuar?\n1)Sim\n2)Nao");
            int op = scanner.nextInt();

            switch (op){
                case(1):
                    System.out.println("\nQuanta resistencia tem o jogador?\n");
                    int resistencia = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta velocidade tem o jogador?\n");
                    int velocidade = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta destreza tem o jogador?\n");
                    int destreza = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta impulsao tem o jogador?\n");
                    int impulssao = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta habilidade para cabecear tem o jogador?\n");
                    int cabeceamento = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta habilidade para rematar tem o jogador?\n");
                    int remate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta habilidade para passar tem o jogador?\n");
                    int passe = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta habilidade para cruzar a bola tem o jogador?\n");
                    int cruzamento = scanner.nextInt();
                    scanner.nextLine();
                    int cod = a.newCodeNumberjogador();
                    String codi = String.valueOf(cod);
                    jog = new Lateral(codi,nome,velocidade,resistencia,destreza,impulssao
                            ,cabeceamento,remate,passe,cruzamento);
                    break;
                case(2):
                    registajogador(a);
                    break;
                default:
                    break;
            }

        }
        return jog;
    }

    private static Avancado registaAvancado(Faztudo a) throws ExcecaoPos{
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nDigite o nome do jogador: ");
        String nome = scanner.nextLine();

        Avancado jog = new Avancado();

        if(!a.jaexitenomejogador(nome)){
            System.out.println("\nQuanta resistencia tem o jogador?\n");
            int resistencia = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta velocidade tem o jogador?\n");
            int velocidade = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta destreza tem o jogador?\n");
            int destreza = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta impulsao tem o jogador?\n");
            int impulssao = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta habilidade para cabecear tem o jogador?\n");
            int cabeceamento = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta habilidade para rematar tem o jogador?\n");
            int remate = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta habilidade para passar tem o jogador?\n");
            int passe = scanner.nextInt();
            scanner.nextLine();;
            int cod = a.newCodeNumberjogador();
            String codi = String.valueOf(cod);
            jog = new Avancado(codi,nome,velocidade,resistencia,destreza,impulssao
                    ,cabeceamento,remate,passe);

        }
        else {
            System.out.println("Nome de jogador ja existente\n\nDeseja continuar?\n1)Sim\n2)Nao");
            int op = scanner.nextInt();

            switch (op){
                case(1):
                    System.out.println("\nQuanta resistencia tem o jogador?\n");
                    int resistencia = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta velocidade tem o jogador?\n");
                    int velocidade = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta destreza tem o jogador?\n");
                    int destreza = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta impulsao tem o jogador?\n");
                    int impulssao = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta habilidade para cabecear tem o jogador?\n");
                    int cabeceamento = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta habilidade para rematar tem o jogador?\n");
                    int remate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta habilidade para passar tem o jogador?\n");
                    int passe = scanner.nextInt();
                    scanner.nextLine();;
                    int cod = a.newCodeNumberjogador();
                    String codi = String.valueOf(cod);
                    jog = new Avancado(codi,nome,velocidade,resistencia,destreza,impulssao
                            ,cabeceamento,remate,passe);
                    break;
                case(2):
                    registajogador(a);
                    break;
                default:
                    break;
            }

        }
        return jog;
    }

    private static Redes registaRedes(Faztudo a) throws ExcecaoPos{
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nDigite o nome do jogador: ");
        String nome = scanner.nextLine();

        Redes jog = new Redes();

        if(!a.jaexitenomejogador(nome)){
            System.out.println("\nQuanta resistencia tem o jogador?\n");
            int resistencia = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta velocidade tem o jogador?\n");
            int velocidade = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta destreza tem o jogador?\n");
            int destreza = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta impulsao tem o jogador?\n");
            int impulssao = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta habilidade para cabecear tem o jogador?\n");
            int cabeceamento = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta habilidade para rematar tem o jogador?\n");
            int remate = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta habilidade para passar tem o jogador?\n");
            int passe = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nQuanta elasticidade tem o jogador?\n");
            int elast = scanner.nextInt();
            scanner.nextLine();
            int cod = a.newCodeNumberjogador();
            String codi = String.valueOf(cod);
            jog = new Redes(codi,nome,velocidade,resistencia,destreza,impulssao
                    ,cabeceamento,remate,passe,elast);

        }
        else {
            System.out.println("Nome de jogador ja existente\n\nDeseja continuar?\n1)Sim\n2)Nao");
            int op = scanner.nextInt();

            switch (op){
                case(1):
                    System.out.println("\nQuanta resistencia tem o jogador?\n");
                    int resistencia = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta velocidade tem o jogador?\n");
                    int velocidade = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta destreza tem o jogador?\n");
                    int destreza = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta impulsao tem o jogador?\n");
                    int impulssao = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta habilidade para cabecear tem o jogador?\n");
                    int cabeceamento = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta habilidade para rematar tem o jogador?\n");
                    int remate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta habilidade para passar tem o jogador?\n");
                    int passe = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\nQuanta elasticidade tem o jogador?\n");
                    int elast = scanner.nextInt();
                    scanner.nextLine();
                    int cod = a.newCodeNumberjogador();
                    String codi = String.valueOf(cod);
                    jog = new Redes(codi,nome,velocidade,resistencia,destreza,impulssao
                            ,cabeceamento,remate,passe,elast);
                    break;
                case(2):
                    registajogador(a);
                    break;
                default:
                    break;
            }

        }
        return jog;
    }

    private static Jogador registajogador(Faztudo a) throws ExcecaoPos {
        Jogador res = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nDeseja criar:\n1)Defesa\n2)Medio\n3)Lateral\n4)Avancado\n5)Guarda-Redes\n ");
        int op = scanner.nextInt();
        switch (op){
            case(1):
                res = registaDefesa(a);
                break;
            case(2):
                res = registaMedio(a);
                break;
            case(3):
                res = registaLateral(a);
                break;
            case(4):
                res = registaAvancado(a);
                break;
            case(5):
                res = registaRedes(a);
                break;
            default:
                break;
        }
        return res;
    }
    private static void todosjogadores(Faztudo a) throws ExcecaoPos, InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(a.toStringjogadores());
        System.out.println("\n\nDeseja ver algum jogador em especifico?\n1)Sim\n2)Nao");
        int op = scanner.nextInt();

        switch (op){
            case(1):
                System.out.println("Qual o nome do jogador que pretende ver? ");
                scanner.nextLine();
                String nome = scanner.nextLine();
                Jogador jog =  jogadormmnome(a,nome);
                System.out.println(jog.toStringcomhab());
                break;
            case(2):
                volta(a);
                break;
            default:
                break;
        }
    }


    private static void clearWindow() {
        for (int i = 0;i<100;i++){
            System.out.println();
        }
    }

    private static void faztransferencia(Faztudo a) throws ExcecaoPos, InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nA que equipa pertence o jogador a ser transferido");
        String nome = scanner.nextLine();
        Equipa sai = equipasmmnome(a,nome);


        System.out.println("\nPara que equipa e que o jogador vai");
        String outro = scanner.nextLine();
        Equipa entra = equipasmmnome(a,outro);

        System.out.println("\nNome do jogador: ");
        String nomejogador = scanner.nextLine();
        Jogador jog = jogadormmnomenaequipa(a,nomejogador,sai);

        a.tranfere(jog,sai,entra);

        ArrayList<Jogador> teste = new ArrayList<>();
        teste.add(jog);
        if(pertenceequipatitular(teste).contains(jog)){
            entra.addjogequipatitular(jog);
        }
        a.update(entra);
        a.update(sai);
    }
//^
//|
private static Jogador jogadormmnomenaequipa(Faztudo a, String nome,Equipa e) throws ExcecaoPos, InterruptedException, IOException {
    Scanner scanner = new Scanner(System.in);
    Jogador res = null;
    ArrayList<Jogador> jogadoresmmnome = a.jogadoresmmnomenaequipa(nome,e);

    if(jogadoresmmnome.size()==1){
        res=jogadoresmmnome.get(0);
    }
    else{
        if(jogadoresmmnome.size()>1){
            System.out.println("Jogadores com o mesmo nome: ");
            jogadoresmmnome.stream().map(Jogador::toStringcomid).forEach(System.out::println);

            System.out.println("\nA que jogador se refere?\nIdentificar pelo numero na camisola:\n");
            String id = scanner.nextLine();
            int i =0;
            for (Jogador jo : jogadoresmmnome) {
                if (id.equalsIgnoreCase(jo.getId())) {
                    res = jo;
                    i = 1;
                }
            }

            if(i==0){
                System.out.println("Jogador desconhecido\n");
                volta(a);

            }
        }
        else {
            System.out.println("Jogador desconhecido\n");
            volta(a);
        }
    }
    return res;
}


    private static Jogador jogadormmnome(Faztudo a, String nome) throws ExcecaoPos, InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        Jogador res = null;
        ArrayList<Jogador> jogadoresmmnome = a.jogadoresmmnome(nome);

        if(jogadoresmmnome.size()==1){
            res=jogadoresmmnome.get(0);
        }
        else{
            if(jogadoresmmnome.size()>1){
                System.out.println("Jogadores com o mesmo nome: ");
                jogadoresmmnome.stream().map(Jogador::toStringcomid).forEach(System.out::println);

                System.out.println("\nA que jogador se refere?\nIdentificar pelo id\n");
                String id = scanner.nextLine();
                int i =0;
                for (Jogador jo : jogadoresmmnome) {
                    if (id.equalsIgnoreCase(jo.getId())) {
                        res = jo;
                        i = 1;
                    }
                }

                if(i==0){
                    System.out.println("Jogador desconhecido\n");
                    volta(a);

                }
            }
            else {
                System.out.println("Jogador desconhecido\n");
                volta(a);
            }
        }
        return res;
    }

    private static Equipa equipasmmnome(Faztudo a, String nome) throws ExcecaoPos, InterruptedException, IOException {

        Scanner scanner = new Scanner(System.in);
        Equipa res = new Equipa();
        ArrayList<Equipa> equipasmmnome = a.equipasmmnome(nome);

        if(equipasmmnome.size()==1) {
        res = a.identificaEquipa(nome);
        }
        else {
            if (equipasmmnome.size() > 1) {
                System.out.println("\nEquipas com o mesmo nome: \n\n");
                equipasmmnome.stream().map(Equipa::toString).forEach(System.out::println);

                System.out.println("\nA que equipa se refere?\nIdentificar pelo id\n");
                String id = scanner.nextLine();
                int i = 0;
                for (Equipa eq : equipasmmnome) {
                    if (id.equalsIgnoreCase(eq.getId())) {
                        res = eq;
                        i = 1;
                    }
                }
                if(i==0){
                        System.out.println("Equipa desconhecida\n");
                    volta(a);

                }
            } else {
                System.out.println("Equipa desconhecida\n");
                volta(a);
            }
        }
        return res;
    }

    private static ArrayList<Jogador> pertenceequipatitular(ArrayList<Jogador> jog) throws ExcecaoPos {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Jogador> titulares = new ArrayList<>();

        for(Jogador j : jog){
        System.out.println("O jogador pertence a equipa titular?" + j.getNome() +"\n\n1)Sim\n2)Nao");
        int op = scanner.nextInt();
        if(op == 1){
             for(Jogador k:jog){
                 k.sethabtit(""); titulares.add(j);
             }
            if(jog.size()>1){
            jog.remove(j);
            titulares.addAll(pertenceequipatitular(jog));
            }
        }
        else {
            if(op == 2){
            for(Jogador k:jog){
                k.sethabtit("");
            }
            }
            else{
                pertenceequipatitular(jog);
                System.out.println("Opcao desconhecida");
            }
        }
        }
        return titulares;
    }

    private static void simularJogoRegistado(Faztudo a) throws ExcecaoPos, InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Jogos Registados\n\n");
        System.out.println(a.jogosRegistados());
        if(a.jogosRegistados().equals("Jogos Registados: \n")){
            System.out.println("Ainda nao ha jogos registados");
            volta(a);
        }
        System.out.println("Nome da equipa da equipa da casa: ");
        String casa = scanner.nextLine();
        Equipa dacasa = equipasmmnome(a,casa);
        System.out.println("\nNome da equipa da equipa visitante: ");
        String visita = scanner.nextLine();
        Equipa fora = equipasmmnome(a,visita);
        System.out.println("\nData do jogo: ");
        System.out.println("\nDia: ");
        int dia = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\nMes:");
        int mes = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\nAno");
        int ano = scanner.nextInt();
        scanner.nextLine();
        LocalDate data = LocalDate.of(ano,mes,dia);
        UmJogo jogo = a.identificaJogo(data);
        System.out.println("\nDeseja ver a simulacao?\n1)Sim\n2)Nao");
        int op = scanner.nextInt();
        scanner.nextLine();
        switch (op){
            case(1):
                a.simulaJogoEspecifico(dacasa,fora,data);
                break;
            case(2):
                a.simulaJogoEspecificosemprint(dacasa,fora,data);
                System.out.println(a.resultadojogorealizado(dacasa,fora,data));
                break;
            default:
                menu(a);
        }
    }

    private static void simulajogoagora(Faztudo a) throws ExcecaoPos, IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nome da equipa da equipa da casa: ");
        String casa = scanner.nextLine();
        Equipa dacasa = equipasmmnome(a,casa);
        equipatitular(a,dacasa);
        System.out.println(dacasa.toStringJogadores());
        Map<String,Jogador> sc = substituicoes(a,dacasa);
        System.out.println("\nNome da equipa da equipa visitante: ");
        String visita = scanner.nextLine();
        Equipa fora = equipasmmnome(a,visita);
        equipatitular(a,fora);
        System.out.println(fora.toStringJogadores());
        Map<String,Jogador> sf = substituicoes(a,fora);
        LocalDate data = LocalDate.now();

        System.out.println("\nDeseja ver a simulacao?\n1)Sim\n2)Nao");
        int op = scanner.nextInt();
        scanner.nextLine();
        UmJogo jogo = new UmJogo(data,dacasa,fora,0,0,sc,sf);
        a.addJogo(jogo);
        switch (op){
            case(1):
                a.simulaJogoEspecifico(dacasa,fora,data);
                break;
            case(2):
                a.simulaJogoEspecificosemprint(dacasa,fora,data);
                System.out.println(a.resultadojogorealizado(dacasa,fora,data));
                break;
            default:
                menu(a);
        }

    }


    private static void simulajogo(Faztudo a) throws ExcecaoPos, InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        clearWindow();
        System.out.println("------------Simular jogo amigavel------------");
        System.out.println("\n1)Registar um jogo");
        System.out.println("\n2)Realizar um jogo ja registado");
        System.out.println("\n3)Historico de jogos realizados");
        System.out.println("\n4)Realizar um jogo amigavel agora");
        int op = scanner.nextInt();
        switch (op){
            case(1):
                a.addJogo(registarJogo(a));
                break;
            case(2):
                simularJogoRegistado(a);
                break;
            case(3):
                clearWindow();
                System.out.println(a.historicoJogos());
                volta(a);
                break;
            case(4):
                clearWindow();
                simulajogoagora(a);
                break;
            default:
                menu(a);
                break;

        }
    }


    private static void volta(Faztudo a) throws ExcecaoPos, InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nDeseja voltar ao menu?\n\n1)Sim\n2)Nao");

        int op = scanner.nextInt();
        switch (op){
            case(1):
                menu(a);
                break;
            case(2):
                Thread.sleep(5000);
                volta(a);
                break;
            default:
                System.out.println("\nOpcao desconhecida :(\n");
                volta(a);
        }
    }

    private static ArrayList<Jogador> jogadorestitulares(Faztudo a, Equipa equipa) throws ExcecaoPos, InterruptedException, IOException {
        ArrayList<Jogador> jogadores = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        for (int n=0;n<11;n++){
            System.out.println(equipa.todosJogadores());
            System.out.println("Nome do jogador: ");
            String nome = scanner.nextLine();
            Jogador j = jogadormmnomenaequipa(a,nome,equipa);
            jogadores.add(j);
        }
        return jogadores;
    }

    private static void equipatitular(Faztudo a, Equipa equipa) throws ExcecaoPos, InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nIdentifique os jogadores que irao jogar na equipa " + equipa.getNome());
        System.out.println("\nDeseja \n1)Identificar os jogadores\n2)Fazer a escolha automaticamente");
        int op = scanner.nextInt();
        scanner.nextLine();

        switch (op){
            case(1):
                equipa.setEquipatitular(jogadorestitulares(a,equipa));
                break;
            case(2):
                System.out.println("\nQual tatica pretende utilizar no jogo:\n1)4-3-3\n2)4-4-2\n3)3-5-2");
                int escolha = scanner.nextInt();
                scanner.nextLine();
                switch (escolha){
                    case(1):
                        equipa.setequipatittat(4,3,3,equipa.getJogadores());
                        break;
                    case(2):
                        equipa.setequipatittat(4,4,2,equipa.getJogadores());
                        break;
                    case(3):
                        equipa.setequipatittat(3,5,2,equipa.getJogadores());
                        break;
                    default:
                        System.out.println("\nOpcao desconhecida");
                        menu(a);
                }
                break;
            default:
                System.out.println("\nOpcao desconhecida");
                menu(a);
        }
    }

    private static Map<String,Jogador> substituicoes(Faztudo a, Equipa equipa) throws ExcecaoPos, InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        Map<String,Jogador> ret = new LinkedHashMap<>();
        System.out.println("\nQuantas substituicoes pretende fazer na equipa " + equipa.getNome() + "\n0)Zero\n1)Uma\n2)Duas\n3)Tres\n(Recomenda-se 3 substituicoes senao vai dar erro no parser)");
        int op = scanner.nextInt();
        scanner.nextLine();
        if(op>=0 && op<=3) {
            if(op==0){
                return ret;
            }
            for (int n = 0; n < op; n++) {
                System.out.println("\nIdentifique o nome do jogador a ser substituido: ");
                String nome = scanner.nextLine();
                Jogador j = jogadormmnomenaequipa(a, nome, equipa);
                System.out.println("\nIdentifique o nome do jogador que vai entrar: ");
                String entra = scanner.nextLine();
                Jogador e = jogadormmnomenaequipa(a, entra, equipa);

                if(equipa.getEquipatitular().contains(j) && equipa.getJogadores().contains(e)){
                    ret.put(j.getId(),e);
                }
                else{
                    throw new ExcecaoPos("Substituicao impossivel");
                }
            }
        }
        else{
            System.out.println("\nOpcao desconhecida");
            menu(a);
        }
        return ret;
    }

    private static UmJogo registarJogo(Faztudo a) throws ExcecaoPos, InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\nRegistar Jogo\n\n");
        System.out.println("Nome da equipa da casa: ");
        String nome = scanner.nextLine();
        Equipa casa = equipasmmnome(a,nome);
        equipatitular(a,casa);
        System.out.println(casa.toStringJogadores());
        Map<String,Jogador> sc = substituicoes(a,casa);

        System.out.println("\nNome da equipa visitante: ");
        ArrayList<Jogador> jogadoresfora = new ArrayList<>();
        String outro = scanner.nextLine();
        Equipa fora = equipasmmnome(a,outro);
        equipatitular(a,fora);
        System.out.println(fora.toStringJogadores());
        Map<String,Jogador> sf = substituicoes(a,fora);

        System.out.println("\nEm que data se ira realizar o jogo: ");
        System.out.println("\nDia: ");
        int dia = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\nMes: ");
        int mes = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\nAno: ");
        int ano = scanner.nextInt();
        scanner.nextLine();
        LocalDate data = LocalDate.of(ano,mes,dia);
        return new UmJogo(data,casa,fora,0,0,sc,sf);
    }

    private static void adicionarjogequipa(Faztudo a) throws ExcecaoPos, InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        Jogador jog = registajogador(a);
        System.out.println("A que equipa deseja associar o jogador " + jog.getNome());
        String nome = scanner.nextLine();
        Equipa equipa = equipasmmnome(a,nome);
        ArrayList<Jogador> teste = new ArrayList<>();
        teste.add(jog);
        ArrayList<Jogador> titular = pertenceequipatitular(teste);
        equipa.addJogador(jog);
        for(Jogador j : titular){
            equipa.addjogequipatitular(j);
        }

        System.out.println("Deseja adicionar mais jogadores?\n1)Sim\n2)Nao");
        int op = scanner.nextInt();
        switch (op){
            case(1):
                addjogequipa(a,equipa);
                break;
            case(2):
                a.update(equipa);
                volta(a);
                break;
            default:
                break;
        }
    }

    private static void addjogequipa(Faztudo a, Equipa equipa) throws ExcecaoPos, InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        Jogador jog = registajogador(a);
        ArrayList<Jogador> teste = new ArrayList<>();
        teste.add(jog);
        ArrayList<Jogador> titular = pertenceequipatitular(teste);
        equipa.addJogador(jog);
        for(Jogador j : titular){
            equipa.addjogequipatitular(j);
        }

        System.out.println("Deseja adicionar mais jogadores?\n1)Sim\n2)Nao");
        int op = scanner.nextInt();
        switch (op){
            case(1):
                addjogequipa(a,equipa);
                break;
            case(2):
                a.update(equipa);
                volta(a);
                break;
            default:
                break;
        }
    }

    private static ArrayList<Equipa> liga(Faztudo a) throws ExcecaoPos, InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Equipa> equipas = new ArrayList<>();
        System.out.println("\nDeseja registar mais equipas?\n1)Sim\n2)Nao");
        int op = scanner.nextInt();
        scanner.nextLine();
        switch (op){
            case(1):
                System.out.println("\nIdentifique o nome da equipa: ");
                String equipa = scanner.nextLine();
                Equipa e = equipasmmnome(a,equipa);
                equipas.add(e);
                liga(a);
                break;
            case(2):

                break;
            default:
                System.out.println("\nOpcao desconhecida");
                menu(a);

        }
        return equipas;
    }


    public static void menu(Faztudo a) throws ExcecaoPos, InterruptedException, IOException {
        int op = menuinicial();

        switch (op){
            case(1):
                registarequipa(a);
                volta(a);
                break;
            case(2):
                verequipas(a);
                volta(a);
            case(3):
                todosjogadores(a);
                volta(a);
                break;
            case(4):
                adicionarjogequipa(a);
                break;
            case(5):
                faztransferencia(a);
                volta(a);
                break;
            case(6):
                simulajogo(a);
                volta(a);
                break;
            case(7):
                a.simulaLiga(liga(a));
                volta(a);
                break;
            case(0):
                System.exit(1);
                break;
            default:
                System.out.println("Opcao desconhecida :(");
                volta(a);
        }
    }
}
