import java.util.AbstractMap;
import java.util.Scanner;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

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
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static void clearWindow() {

        for (int i = 0;i<100;i++){
            System.out.println();
        }
    }

}
