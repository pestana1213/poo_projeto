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
}
