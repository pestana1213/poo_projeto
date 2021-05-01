import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


//Ainda em desenvolvimento, possivelmente pode nao constar no projeto final mas tem como objetivo simular uma liga
public class Liga {
    Map<String,Equipa> equipas;
    Map<LocalDate,UmJogo> jogos;

    public Liga() {
        this.equipas = new LinkedHashMap<>();
        this.jogos = new LinkedHashMap<>();
    }

    public Liga(Map<String,Equipa> equipas,Map<LocalDate,UmJogo> jogos){
        this.equipas = new LinkedHashMap<>(equipas);
        this.jogos = new LinkedHashMap<>(jogos);
    }

    public Liga (Liga e){
        this.equipas = e.getEquipas();
        this.jogos = e.getJogos();
    }

    public Map<String,Equipa> getEquipas(){
        return this.equipas.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e->e.getValue().clone()));
    }

    public Map<LocalDate,UmJogo> getJogos(){
        return this.jogos.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e->e.getValue()));
    }

    public void setEquipas (Map<String, Equipa> eq){
        this.equipas = eq.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,e->e.getValue()));
    }

    public void setJogos(Map<LocalDate,UmJogo> jogos){
        this.jogos = jogos.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void calendario(){

        List<Equipa> eq = this.equipas.values().stream().map(Equipa::clone).collect(Collectors.toList());
        Collections.shuffle(eq);
        LocalDate agora = LocalDate.now();
        for(int i=0;i<eq.size();i++) {
            Equipa a = eq.get(i);
            for (int n=0; n<eq.size(); n++) {
                Equipa b = eq.get(n);
                if(!a.equals(b)) {
                    UmJogo jogo = new UmJogo(a, b, 0, 0);
                    this.jogos.put(agora,jogo);
                    agora = agora.plusWeeks(1);
                }
                agora = agora.plusWeeks(1);
            }
        }
    }

    public List<Equipa> ordenaequipasporpontos(List<Equipa> eq) {
        Comparator<Equipa> comp = (e1, e2) -> (int) e2.getpontos() - e1.getpontos();
        return eq.stream().sorted(comp).collect(Collectors.toList());
    }

    public String simulaliga() throws ExcecaoPos, InterruptedException {
        StringBuilder sb = new StringBuilder();
        Equipa vencedora = new Equipa();
        List<Equipa> eq = new ArrayList<>();
        calendario();

        for(LocalDate data: this.jogos.keySet()){
            System.out.println(data+"\n\n");
            UmJogo jogo = this.jogos.get(data);
            Equipa a = this.equipas.get(jogo.getcasa().getId());
            Equipa b = this.equipas.get(jogo.getVisita().getId());
            System.out.println(a.getNome() +" Vs " + b.getNome());
            jogo.simulajogo();

            if (jogo.getGoloC() == jogo.getGoloF()) {a.pontos(1); b.pontos(1);}else{
                if (jogo.getGoloC() > jogo.getGoloF()) {a.pontos(3);}else{
                    if (jogo.getGoloC() < jogo.getGoloF()) {b.pontos(3);}}}

        }

            List<Equipa> porpontos = ordenaequipasporpontos(this.equipas.values().stream().collect(Collectors.toList()));
            vencedora = porpontos.get(0);
            for(Equipa e : eq){
                e.setPontos(0);
            }

            return sb.append("Equipa vencedora da liga: " + vencedora.getNome()).toString();
        }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Calendario de jogos: \n");
        for(LocalDate data : this.jogos.keySet()){
            sb.append("\nDia: " + data + "\nJogo: " + this.jogos.get(data).getcasa().getNome() +" VS " + this.jogos.get(data).getVisita().getNome());
        }
        return sb.toString();
    }

}
