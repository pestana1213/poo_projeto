
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {

    public static Faztudo parse(Faztudo tudo) throws ExcecaoPos {
        List<String> linhas = lerFicheiro("C:\\Users\\Pestana\\Desktop\\POO\\Projeto\\src\\output.txt");
        Map<String, Equipa> equipas = new HashMap<>(); //nome, equipa
        Map<String, Jogador> jogadores = new HashMap<>(); //numero, jogador
        List<UmJogo> jogos = new ArrayList<>();
        Equipa ultima = null; Jogador j = null;
        String[] linhaPartida;
        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch(linhaPartida[0]){
                case "Equipa":
                    String id = String.valueOf(tudo.newCodeNumberequipa());
                    Equipa e = Equipa.parse(linhaPartida[1],id);
                    equipas.put(e.getId(), e);
                    tudo.addEquipa(e);
                    ultima = e;
                    break;
                case "Guarda-Redes":
                case "Avancado":
                case "Lateral":
                case "Medio":
                case "Defesa":
                    Posicao pos = new Posicao(linhaPartida[0]);
                    j = Jogador.parse(linhaPartida[1],pos);
                    jogadores.put(j.getId(), j);
                    if (ultima == null) throw new ExcecaoPos("err"); //we need to insert the player into the team
                    ultima.addJogador(j); //if no team was parsed previously, file is not well-formed;
                    System.out.println(ultima);
                    break;
                case "Jogo":
                    UmJogo jo = UmJogo.parse(linhaPartida[1],tudo);
                    jogos.add(jo);
                    break;
                default:
                    throw new ExcecaoPos("err");

            }
        }

        //debug
        for (Equipa e: equipas.values()){
            System.out.println(e.toString());
        }
        for (UmJogo jog: jogos){
            System.out.println(jog.toString());
        }

        return tudo;
    }

    public static List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }


}
