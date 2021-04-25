import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Liga {
    Map<String,Equipa> equipas;

    public Liga() {
        this.equipas = new LinkedHashMap<>();
    }

    public Liga(Map<String,Equipa> equipas){
        this.equipas = new LinkedHashMap<>(equipas);
    }

    public Liga (Liga e){
        this.equipas = e.getEquipas();
    }

    public Map<String,Equipa> getEquipas(){
        return this.equipas.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e->e.getValue().clone()));
    }

    public void setEquipas (Map<String, Equipa> eq){
        this.equipas = eq.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,e->e.getValue().clone()));
    }

    public void simulaliga(){
        int contaequipas = this.equipas.values().size();
        if(contaequipas%2==0){ //numero de equipas é par, vai fazer fazer um random para simular os jogos entre as equipas

        }
        else{   //o numero de equipas é impar, uma equipa vai passar logo à fase seguinte

        }
    }
}
