
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Collectors;

public class Equipa extends Geral {

    private ArrayList<Jogador> jogadores;
    private ArrayList<Jogador> equipatitular;

    public Equipa(){
        super();
        this.jogadores = new ArrayList<>();
        this.equipatitular = new ArrayList<>();
    }

    public Equipa (String nome, String id,ArrayList<Jogador> a, ArrayList<Jogador> b){
        super(nome,id);
        this.jogadores = new ArrayList<>(a);
        this.equipatitular = new ArrayList<>(b);
    }

    public Equipa (Equipa b){
        super(b);
        this.jogadores = b.getJogadores();
        this.equipatitular = b.getEquipatitular();
    }

    public String getId() {
        return super.getId();
    }

    public String getNome() {
        return super.getNome();
    }

    public ArrayList<Jogador> getEquipatitular(){
       return this.equipatitular.stream().map(Jogador::clone).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Jogador> getJogadores () {
        return this.jogadores.stream().map(Jogador::clone).collect(Collectors.toCollection(ArrayList::new));
    }

    public void setJogadores(ArrayList<Jogador> a){
        this.jogadores = new ArrayList<>(a);
    }

    public void setId(String id) {
        super.setId(id);
    }

    public void setEquipatitular(ArrayList<Jogador> titular) throws ExcecaoPos{
        ArrayList<Jogador> res = new ArrayList<>();
        if(titular.size() == 11){
                for(Jogador f : titular){
                    if(this.jogadores.contains(f)){
                        res.add(f.clone());}
                    else {throw new ExcecaoPos("Jogador nao pertence a equipa");}
                }

        this.equipatitular = new ArrayList<>(res);}
        else {throw new ExcecaoPos("Equipa titular com demasiados elementos");}
    }

    public void setNome(String nome) throws ExcecaoPos{
        super.setNome(nome);
    }

    public void addJogador (Jogador a) throws ExcecaoPos{

        boolean b = this.jogadores.stream().anyMatch(j ->j.equals(a));

            if (b){
                throw new ExcecaoPos("Jogador ja na equipa");
            }
        this.jogadores.add(a.clone());
        }

    public void removeJogador (Jogador a) throws ExcecaoPos{

        boolean b = this.jogadores.stream().anyMatch(j ->j.equals(a));

            if (!b){
                throw new ExcecaoPos("Jogador nao esta na equipa");
            }

        this.jogadores.remove(a);
    }

    public int habfrente (){
        int total = 0;
        int x = 0;
        for (Jogador player : this.jogadores){
            if(player.getposicaostr().equals(AVANCADO)) {
                x++;
                total += player.getHabilidade();
            }
        }
        return (total/x);
    }

    public void addjogequipatitular (Jogador a) throws ExcecaoPos{
        for(Jogador e : this.equipatitular){
            if(e.equals(a)){
                throw new ExcecaoPos("Jogador ja esta na equipa titular");
            }
        }
        this.equipatitular.add(a.clone());
    }

    public void substitui(Jogador entra, Jogador sai) throws ExcecaoPos {
        int i = 0;
        for (Jogador y : this.equipatitular) {
            for (Jogador x : this.jogadores) {
                if (y.equals(sai) && x.equals(entra)) {
                    addjogequipatitular(entra.clone());
                    this.equipatitular.remove(sai);
                    i = 1;
                }
            }
        }
        if (i == 0) {
            throw new ExcecaoPos("Substituicao nao efetuada");
        }
    }

    public int hablateral(){
        int total = 0;
        int x = 0;
        for (Jogador player : this.jogadores){
            if(player.getposicaostr().equals(LATERAL)) {
                x++;
                total += player.getHabilidade();
            }
        }
        return (total/x);
    }

    public int habmedio(){
        int total = 0;
        int x = 0;
        for (Jogador player : this.jogadores){
            if(player.getposicaostr().equals(MEDIO)) {
                x++;
                total += player.getHabilidade();
            }
        }
        return (total/x);
    }

    public int habdefesa(){
        int total = 0;
        int x = 0;
        for (Jogador player : this.jogadores){
            if(player.getposicaostr().equals(DEFESA)) {
                x++;
                total += player.getHabilidade();
            }
        }
        return (total/x);
    }

    public int habredes(){
        int total = 0;
        int x = 0;
        for (Jogador player : this.jogadores){
            if(player.getposicaostr().equals(REDES)) {
                x++;
                total += player.getHabilidade();
            }
        }
        return (total/x);
    }

    public int habgeral(){
        return (habdefesa() + habfrente() + hablateral() + habmedio() + habredes() ) / 5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Equipa equipa = (Equipa) o;
        return Objects.equals(jogadores, equipa.jogadores) && Objects.equals(equipatitular, equipa.equipatitular);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), jogadores, equipatitular);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Nomde da equipa: " + getNome());
        for (Jogador e : this.jogadores) {
            sb.append(e.toString());
        }
        if(this.equipatitular.size() > 0) {
            sb.append("\nEquipa Titular: ");
            for (Jogador x : this.equipatitular) {
                sb.append(x.toString());
            }
        }
        return sb.toString();
    }
}

