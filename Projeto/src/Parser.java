
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {

    public static void parse(Faztudo tudo) throws ExcecaoPos {
        Liga liga = new Liga();
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
                    j = Redes.parse(linhaPartida[1]);
                    jogadores.put(j.getId(), j);
                    if (ultima == null) throw new ExcecaoPos("err"); //we need to insert the player into the team
                    ultima.addJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Defesa":
                    j = Defesa.parse(linhaPartida[1]);
                    jogadores.put(j.getId(), j);
                    if (ultima == null) throw new ExcecaoPos("err"); //we need to insert the player into the team
                    ultima.addJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Medio":
                    j = Medio.parse(linhaPartida[1]);
                    jogadores.put(j.getId(), j);
                    if (ultima == null) throw new ExcecaoPos("err"); //we need to insert the player into the team
                    ultima.addJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Lateral":
                    j = Lateral.parse(linhaPartida[1]);
                    jogadores.put(j.getId(), j);
                    if (ultima == null) throw new ExcecaoPos("err"); //we need to insert the player into the team
                    ultima.addJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Avancado":
                    j = Avancado.parse(linhaPartida[1]);
                    jogadores.put(j.getId(), j);
                    if (ultima == null) throw new ExcecaoPos("err"); //we need to insert the player into the team
                    ultima.addJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Jogo":
                    UmJogo jo = UmJogo.parse(linhaPartida[1],tudo);
                    jogos.add(jo);
                    tudo.addJogo(jo);
                    break;
                default:
                    throw new ExcecaoPos("err");

            }
        }
        //debug
    }

    public static List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }


}
