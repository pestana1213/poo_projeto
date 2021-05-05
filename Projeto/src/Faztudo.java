import java.util.*;
import java.util.stream.Collectors;

//Classe "mestra"
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

        if (this.equipas.containsKey(e.getId())) throw new ExcecaoPos("Equipa ja registada");
        else
            this.equipas.putIfAbsent(e.getId(), e);
    }

    public void removeEquipa(Equipa e) throws ExcecaoPos {
        if (this.equipas.containsValue(e)) this.equipas.remove(e.getId(), e);
        else throw new ExcecaoPos("Equipa nao se encontra registada");
    }

    //Faz transferencias de jogadores entre equipas
    public void tranfere(Jogador jogador, Equipa sai, Equipa entra) throws ExcecaoPos{

        for(Equipa a : this.equipas.values()){
            if(sai.equals(a)){
                sai.removeJogador(jogador);
            }
            if(entra.equals(a)){
                entra.addJogador(jogador);
            }
        }
    }

    private boolean contida (Equipa e) {
        return this.equipas.keySet().stream().anyMatch(a -> a.equals(e.getId()));
    }

    public void simulaumjogo(Equipa casa, Equipa visita) throws ExcecaoPos, InterruptedException {

        if (contida(casa) && contida(visita)) {
            UmJogo jogo = new UmJogo(casa,visita,0,0);
            jogo.simulajogo();
        }
        else throw new ExcecaoPos("Equipa nao registada");
    }

    //Gera um codigo novo para uma Equipa! Cada Equipa tem um id diferente, nao ha Equipas com o mesmo id
    //Pomos todos os ids das Equipas numa lista e ordenamos essa lista! Pegamos no ultimo valor da lista e adicionamos 1! Esse valor vai ser o novo id
    public int newCodeNumberequipa(){
        if (this.equipas.size()==0){
            return 1;
        }
    else {
            List<Integer> l = this.equipas.keySet().stream()
                    .map(Integer::valueOf)
                    .sorted()
                    .collect(Collectors.toList());
            if (l.isEmpty()) return 1;
            Integer i = l.get(0);
            int aux = i + 1;
            while (l.contains(aux)) aux++;
            return aux;
        }
    }

    //Gera um codigo novo para um jogador! Cada jogador tem um id diferente, nao ha jogadores com o mesmo id
    //Pomos todos os ids dos jogadores numa lista e ordenamos essa lista! Pegamos no ultimo valor da lista e adicionamos 1! Esse valor vai ser o novo id
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
       return this.equipas.values().stream().anyMatch(e-> e.getNome().equalsIgnoreCase(nome));
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
            sb.append("\n" + a.toString());
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
                sb.append("\n" + jo.toString());
            }
        }

        return sb.toString();
    }

    public boolean jaexistenome(String nome){
        return this.equipas.values().stream().anyMatch(a->a.getNome().equalsIgnoreCase(nome));
    }

    public boolean jaexitenomejogador(String nome){
        return this.equipas.values().stream().anyMatch(a->a.getJogadores().stream().anyMatch(b->b.getNome().equalsIgnoreCase(nome)));
    }

    //Identifica uma equipa pelo nome
    public Equipa identificaEquipa(String nome) throws ExcecaoPos {
        Equipa res = new Equipa();
        for(Equipa e : this.equipas.values()){
            if(e.getNome().equalsIgnoreCase(nome)){
                res = e.clone();
            }
        }
        return res;
    }

    //Identifica um jogador numa equipa
    public Jogador identificaJogador(String nome, Equipa e){
       return e.identificaJogador(nome);
    }

    public Jogador identificaJogadorId(String id, Equipa e){
        return e.identificaJogadorId(id);
    }
    //Faz o update de uma equipa
    public void update(Equipa a){

        for(Equipa e: this.equipas.values()){
            if(e.getId().equals(a.getId())){
                e.update(a);
            }
        }
    }

    //Devolve todas as equipas que têm o mesmo nome
    public ArrayList<Equipa> equipasmmnome(String nome){
        return this.equipas.values().stream().filter(a->a.getNome().equalsIgnoreCase(nome)).map(Equipa::clone).collect(Collectors.toCollection(ArrayList::new));
    }

    //Devolve todos os jogadores registados com o mesmo nome
    public ArrayList<Jogador> jogadoresmmnome(String nome){
        ArrayList<Jogador> todos = new ArrayList<>();
        this.equipas.values().stream().map(Equipa::getJogadores).forEach(todos::addAll);
        return todos.stream().filter(a->a.getNome().equalsIgnoreCase(nome)).collect(Collectors.toCollection(ArrayList::new));
    }

    //Devolve todos os jogadores com o mesmo nome numa equipa
    public ArrayList<Jogador> jogadoresmmnomenaequipa(String nome, Equipa e){
        ArrayList<Jogador> todos = new ArrayList<>();
        for(Equipa k : this.equipas.values()){
            if(e.equals(k)){
                todos.addAll(e.getJogadores());
            }
        }
        return todos.stream().filter(a->a.getNome().equalsIgnoreCase(nome)).collect(Collectors.toCollection(ArrayList::new));
    }

    //Ordena as equipas por habilidade, das que tem mais habilidade para a que tem menos
    public List<Equipa> ordenaporhabilidade(){
        Comparator<Equipa> comp = (e1,e2) -> (int) e2.habgeral()-e1.habgeral();
        return this.equipas.values().stream().sorted(comp).collect(Collectors.toList());
    }
}
