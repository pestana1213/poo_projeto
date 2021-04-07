
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class Equipa extends Geral {

    private ArrayList<Jogador> jogadores;
    private ArrayList<Jogador> equipatitular;

    public Equipa(){
        super();
        this.jogadores = new ArrayList<>();
        this.equipatitular = new ArrayList<>();
        hist();
    }

    public Equipa (String nome, String id,ArrayList<Jogador> a, ArrayList<Jogador> b){
        super(nome,id);
        this.jogadores = new ArrayList<>(a);
        this.equipatitular = new ArrayList<>(b);
        hist();
        }

    public Equipa (Equipa b){
        super(b);
        this.jogadores = b.getJogadores();
        this.equipatitular = b.getEquipatitular();
        hist();
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
        hist();
    }

    public void setId(String id) {
        super.setId(id);
    }

    public void setEquipatitular(ArrayList<Jogador> titular) throws ExcecaoPos{
        if(titular.size() <= 11){
                for(Jogador f : titular){
                    if(this.jogadores.contains(f)){
                        this.equipatitular.add(f);
                        }
                    else {throw new ExcecaoPos("Jogador nao pertence a equipa");}
                }

        hist();}
        else {throw new ExcecaoPos("Equipa titular com demasiados elementos");}
    }

    public void setNome(String nome) throws ExcecaoPos{
        super.setNome(nome);
        hist();

    }

    public void addJogador (Jogador a) throws ExcecaoPos{

            if (this.jogadores.stream().anyMatch(j ->j.equals(a))) {
                throw new ExcecaoPos("Jogador ja na equipa");
            }
        a.addhist(this);
        this.jogadores.add(a.clone());
    }

    public void removeJogador (Jogador a) throws ExcecaoPos{
        this.jogadores.remove(a);
        this.equipatitular.remove(a);
    }

    public int habfrente (){

        return (int) (this.jogadores.stream().filter(a->a.getposicaostr().equals(AVANCADO)).mapToDouble(Jogador::getHabilidade).sum()/
                this.jogadores.stream().filter(a->a.getposicaostr().equals(AVANCADO)).count());
    }

    public void addjogequipatitular (Jogador a) throws ExcecaoPos{
        for(Jogador e : this.equipatitular){
            if(e.equals(a)){
                throw new ExcecaoPos("Jogador ja esta na equipa titular");
            }
        }
        this.equipatitular.add(a);
    }

    public void substitui(Jogador entra, Jogador sai) throws ExcecaoPos {
        int i = 0;
        for (Jogador y : this.equipatitular) {
            for (Jogador x : this.jogadores) {
                if (y.equals(sai) && x.equals(entra)) {
                    addjogequipatitular(entra);
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

        return (int) (this.jogadores.stream().filter(a->a.getposicaostr().equals(LATERAL)).mapToDouble(Jogador::getHabilidade).sum()/
                this.jogadores.stream().filter(a->a.getposicaostr().equals(LATERAL)).count());
    }

    public int habmedio(){
        return (int) (this.jogadores.stream().filter(a->a.getposicaostr().equals(MEDIO)).mapToDouble(Jogador::getHabilidade).sum()/
                this.jogadores.stream().filter(a->a.getposicaostr().equals(MEDIO)).count());
    }

    public int habdefesa(){
        return (int) (this.jogadores.stream().filter(a->a.getposicaostr().equals(DEFESA)).mapToDouble(Jogador::getHabilidade).sum()/
                this.jogadores.stream().filter(a->a.getposicaostr().equals(DEFESA)).count());
    }

    public int habredes(){
        return (int) (this.jogadores.stream().filter(a->a.getposicaostr().equals(REDES)).mapToDouble(Jogador::getHabilidade).sum()/
                this.jogadores.stream().filter(a->a.getposicaostr().equals(REDES)).count());
    }

    public int habgeral(){
        if(this.jogadores.size() > 0)
        return (habdefesa() + habfrente() + hablateral() + habmedio() + habredes() ) / 5;
        else return 0;
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
        sb.append("-----------Equipa-----------\n\n");
        sb.append("Nome da equipa: " + super.getNome());
        sb.append("\nId: " + super.getId());
        sb.append("\nHabilidade geral: " + habgeral());
        sb.append("\n-----------Jogadores-----------\n\n");
        for (Jogador e : this.jogadores) {
            sb.append(e.toString());
        }
        sb.append("\n-----------Jogadores titulares-----------\n\n");
        if(this.equipatitular.size() > 0) {
            sb.append("\nEquipa Titular: ");
            for (Jogador x : this.equipatitular) {
                sb.append(x.toString());
            }
        }
        sb.append("\n-------------------------\n\n");
        return sb.toString();
    }

    public Equipa clone(){
        return new Equipa(this);
    }

    private void hist(){
        for (Jogador e : this.jogadores){
            for(Jogador k : this.equipatitular){
                if(!k.ultimo().equals(this)){
                    k.addhist(this);
                }
            }
            if(!e.ultimo().equals(this)){
            e.addhist(this);
            }
        }
    }

    public boolean temjogador(String nome){
        return getJogadores().stream().anyMatch(a->a.getNome().equalsIgnoreCase(nome));
    }

    public Jogador identificaJogador(String nome){

        Jogador res = new Jogador();
        for(Jogador j : this.jogadores){
            if(j.getNome().equalsIgnoreCase(nome))
               return j.clone();
        }
        return res;
    }

    public void update(Equipa a){
        this.equipatitular = a.getEquipatitular();
        this.jogadores = a.getJogadores();

    }
}