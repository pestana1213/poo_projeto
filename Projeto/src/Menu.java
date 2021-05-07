import java.lang.module.ModuleDescriptor;
import java.util.ArrayList;
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
        sb.append("6) Realizar um jogo amigavel entre duas equipas. \n");
        sb.append("7) Nova liga. \n");
        sb.append("Nota: PARA A REALIZACAO DA OPCAO 6) E 7) AS EQUIPAS EM QUESTAO JA PRECISAM DE ESTAR REGISTADAS. \n");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static void registarequipa(Faztudo a) throws ExcecaoPos, InterruptedException {
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

    private static void verequipas(Faztudo a) throws ExcecaoPos, InterruptedException {
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

    private static void acoesequipa(Faztudo a, Equipa equipa) throws ExcecaoPos, InterruptedException {
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
                        if(equipa.getJogadores().contains(j)){
                            equipa.addjogequipatitular(j);
                            a.update(equipa);
                        }
                        else{
                            System.out.println("Jogador nao pertence a equipa");
                        }
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

    private static void facilita(Faztudo a, Equipa equipa) throws ExcecaoPos, InterruptedException {      //previne a repeticao do ciclo na funcao de cima
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
        Jogador res = new Jogador();
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

    private static Jogador registajogaux(Faztudo a, Jogador jog) throws ExcecaoPos{
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nQual a habilidade geral do jogador? \n");
        int hab = scanner.nextInt();
        jog.setHabilidade(hab);
        int cod = a.newCodeNumberjogador();
        String codi = String.valueOf(cod);
        jog.setId(codi);

        return jog;
    }

    private static Defesa registaDefesa(Faztudo a) throws ExcecaoPos{
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nDigite o nome do jogador: ");
        String nome = scanner.nextLine();

        Defesa jog = new Defesa();

        if(!a.jaexitenomejogador(nome)){
            jog.setNome(nome);
            System.out.println("\nQual a habilidade geral do jogador? \n");
            int hab = scanner.nextInt();
            jog.setHabilidade(hab);
            int cod = a.newCodeNumberjogador();
            String codi = String.valueOf(cod);
            jog.setId(codi);
        }
        else {
            System.out.println("Nome de jogador ja existente\n\nDeseja continuar?\n1)Sim\n2)Nao");
            int op = scanner.nextInt();

            switch (op){
                case(1):
                    jog.setNome(nome);
                    System.out.println("\nQual a habilidade geral do jogador? \n");
                    int hab = scanner.nextInt();
                    jog.setHabilidade(hab);
                    int cod = a.newCodeNumberjogador();
                    String codi = String.valueOf(cod);
                    jog.setId(codi);

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
            jog.setNome(nome);
            System.out.println("\nQual a habilidade geral do jogador? \n");
            int hab = scanner.nextInt();
            jog.setHabilidade(hab);
            int cod = a.newCodeNumberjogador();
            String codi = String.valueOf(cod);
            jog.setId(codi);
        }
        else {
            System.out.println("Nome de jogador ja existente\n\nDeseja continuar?\n1)Sim\n2)Nao");
            int op = scanner.nextInt();

            switch (op){
                case(1):
                    jog.setNome(nome);
                    System.out.println("\nQual a habilidade geral do jogador? \n");
                    int hab = scanner.nextInt();
                    jog.setHabilidade(hab);
                    int cod = a.newCodeNumberjogador();
                    String codi = String.valueOf(cod);
                    jog.setId(codi);

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
            jog.setNome(nome);
            System.out.println("\nQual a habilidade geral do jogador? \n");
            int hab = scanner.nextInt();
            jog.setHabilidade(hab);
            int cod = a.newCodeNumberjogador();
            String codi = String.valueOf(cod);
            jog.setId(codi);
        }
        else {
            System.out.println("Nome de jogador ja existente\n\nDeseja continuar?\n1)Sim\n2)Nao");
            int op = scanner.nextInt();

            switch (op){
                case(1):
                    jog.setNome(nome);
                    System.out.println("\nQual a habilidade geral do jogador? \n");
                    int hab = scanner.nextInt();
                    jog.setHabilidade(hab);
                    int cod = a.newCodeNumberjogador();
                    String codi = String.valueOf(cod);
                    jog.setId(codi);

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
            jog.setNome(nome);
            System.out.println("\nQual a habilidade geral do jogador? \n");
            int hab = scanner.nextInt();
            jog.setHabilidade(hab);
            int cod = a.newCodeNumberjogador();
            String codi = String.valueOf(cod);
            jog.setId(codi);
        }
        else {
            System.out.println("Nome de jogador ja existente\n\nDeseja continuar?\n1)Sim\n2)Nao");
            int op = scanner.nextInt();

            switch (op){
                case(1):
                    jog.setNome(nome);
                    System.out.println("\nQual a habilidade geral do jogador? \n");
                    int hab = scanner.nextInt();
                    jog.setHabilidade(hab);
                    int cod = a.newCodeNumberjogador();
                    String codi = String.valueOf(cod);
                    jog.setId(codi);

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
            jog.setNome(nome);
            System.out.println("\nQual a habilidade geral do jogador? \n");
            int hab = scanner.nextInt();
            jog.setHabilidade(hab);
            int cod = a.newCodeNumberjogador();
            String codi = String.valueOf(cod);
            jog.setId(codi);
        }
        else {
            System.out.println("Nome de jogador ja existente\n\nDeseja continuar?\n1)Sim\n2)Nao");
            int op = scanner.nextInt();

            switch (op){
                case(1):
                    jog.setNome(nome);
                    System.out.println("\nQual a habilidade geral do jogador? \n");
                    int hab = scanner.nextInt();
                    jog.setHabilidade(hab);
                    int cod = a.newCodeNumberjogador();
                    String codi = String.valueOf(cod);
                    jog.setId(codi);

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
        Jogador res = new Jogador();
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
    private static void todosjogadores(Faztudo a) throws ExcecaoPos, InterruptedException {
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

    private static void faztransferencia(Faztudo a) throws ExcecaoPos, InterruptedException {
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
private static Jogador jogadormmnomenaequipa(Faztudo a, String nome,Equipa e) throws ExcecaoPos, InterruptedException{
    Scanner scanner = new Scanner(System.in);
    Jogador res = new Jogador();
    ArrayList<Jogador> jogadoresmmnome = a.jogadoresmmnomenaequipa(nome,e);

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


    private static Jogador jogadormmnome(Faztudo a, String nome) throws ExcecaoPos, InterruptedException{
        Scanner scanner = new Scanner(System.in);
        Jogador res = new Jogador();
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

    private static Equipa equipasmmnome(Faztudo a, String nome) throws ExcecaoPos, InterruptedException {

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
                System.out.println("Opcao desconhecida");
            }
        }
        }
        return titulares;
    }

    private static void simulajogo(Faztudo a) throws ExcecaoPos, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        clearWindow();
        System.out.println("------------Simular jogo amigavel------------");
        System.out.println("Qual a equipa da casa? \n");
        String casa = scanner.nextLine();
        Equipa equipacasa = equipasmmnome(a,casa);
        System.out.println("Qual a equipa que vai jogar contra " + equipacasa.getNome() + "? \n");
        String visita = scanner.nextLine();
        Equipa equipavisita = equipasmmnome(a,visita);
        a.simulaumjogo(equipacasa,equipavisita);

    }


    private static void volta(Faztudo a) throws ExcecaoPos, InterruptedException {
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

    private static void adicionarjogequipa(Faztudo a) throws ExcecaoPos, InterruptedException {
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

    private static void addjogequipa(Faztudo a, Equipa equipa) throws ExcecaoPos, InterruptedException {
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

    public static void menu(Faztudo a) throws ExcecaoPos, InterruptedException {
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
                System.out.println("\nAinda em desenvolvimento :)\n");
                volta(a);
                break;
            default:
                System.out.println("Opcao desconhecida :(");
                volta(a);
        }
    }
}
