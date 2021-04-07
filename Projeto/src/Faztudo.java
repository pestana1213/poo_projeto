import java.util.*;
import java.util.stream.Collectors;

public class Faztudo {

    private Map<String, Equipa> equipas;

    public Faztudo() {
        this.equipas = new LinkedHashMap<>();
    }

    public Faztudo(Map<String, Equipa> equipas) {
        this.equipas = new LinkedHashMap<>(equipas);
    }

    public Faztudo(Faztudo e) {
        this.equipas = e.getEquipas();
    }

    public Map<String, Equipa> getEquipas() {
        return this.equipas.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
    }

    public void setEquipas(Map<String, Equipa> e) {
        this.equipas = new LinkedHashMap<>(e);
    }

    public void addEquipa(Equipa e) throws ExcecaoPos {

        if (this.equipas.containsValue(e)) throw new ExcecaoPos("Equipa ja registada");
        else
            this.equipas.put(e.getId(), e);
    }

    public void removeEquipa(Equipa e) throws ExcecaoPos {
        if (this.equipas.containsValue(e)) this.equipas.remove(e.getId(), e);
        else throw new ExcecaoPos("Equipa nao se encontra registada");
    }

    public void tranfere(Jogador jogador, Equipa sai, Equipa entra) throws ExcecaoPos{

        if (contida(sai)) {
            if (contida(entra)) {
                entra.addJogador(jogador);
            }
            sai.removeJogador(jogador);
        }
        else {throw new ExcecaoPos("Equipa nao registada");}

    }

    private boolean contida (Equipa e) {
        return this.equipas.values().stream().anyMatch(a -> a.equals(e));
    }

    public void simulaumjogo(Equipa casa, Equipa visita) throws ExcecaoPos, InterruptedException {

        if (contida(casa) && contida(visita)) {
            UmJogo jogo = new UmJogo(casa,visita,0,0);
            jogo.simulajogo();
        }
        else throw new ExcecaoPos("Equipa nao registada");
    }

    public int newCodeNumberequipa(){
        List<Integer> l = this.equipas.keySet().stream()
                .map(Integer::valueOf)
                .sorted()
                .collect(Collectors.toList());
        if (l.isEmpty()) return 1;
        Integer i = l.get(0);
        int aux = i+1;
        while (l.contains(aux)) aux++;
        return aux;
    }

    public int newCodeNumberjogador(){
        ArrayList<Jogador> todosjogadores = new ArrayList<>();

        for(Equipa e : this.equipas.values()){
            ArrayList<Jogador> jequipa = e.getJogadores();
            todosjogadores.addAll(jequipa);
        }

        Map<String, Jogador> mapjog = todosjogadores.stream().collect(Collectors.toMap(Jogador::getId, Jogador::new));
        List<Integer> l = mapjog.keySet().stream()
                .map(Integer::valueOf)
                .sorted()
                .collect(Collectors.toList());
        if (l.isEmpty()) return 1;
        Integer i = l.get(0);
        int aux = i+1;
        while (l.contains(aux)) aux++;
        return aux;
    }

    public boolean nomejaregistado(String nome){
       return this.equipas.values().stream().anyMatch(e->e.getNome().equals(nome));
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        for(Equipa e : this.equipas.values()){
            sb.append("\n" + e.toString());
        }
        return sb.toString();
    }

    public String toStringEquipa(Equipa a){
        StringBuilder sb = new StringBuilder("---------------Equipa---------------\n\n");

        if(this.equipas.containsValue(a)){
            sb.append(a.toString());
        }
        else{sb.append("Equipa nao encontrada"); }
        return sb.toString();
    }

    public String toStringjogadores(){
        StringBuilder sb = new StringBuilder("---------------Jogadores---------------\n\n");
        ArrayList<Jogador> jog = new ArrayList<>();
        for(Equipa e : this.equipas.values()){
            jog.addAll(e.getJogadores());
            for(Jogador jo : jog){
                sb.append(jo.toString());
            }
        }

        return sb.toString();
    }

    public boolean jaexistenome(String nome){
        return this.equipas.values().stream().anyMatch(a->a.getNome().equals(nome));
    }

    public boolean jaexitenomejogador(String nome){
        return this.equipas.values().stream().anyMatch(a->a.getJogadores().stream().anyMatch(b->b.getNome().equals(nome)));
    }

    public Equipa identificaEquipa(String nome) throws ExcecaoPos {
        Equipa res = new Equipa();
        for(Equipa e : this.equipas.values()){
            if(e.getNome().equals(nome)){
                res =  e.clone();
            }
        }
        return res;
    }

    public Jogador identificaJogador(String nome){
        Jogador res = new Jogador();
        for(Equipa e : this.equipas.values()){
            if(e.temjogador(nome)){
                res = e.identificaJogador(nome);
            }
        }
        return res;
    }
}
