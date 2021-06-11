import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

//Class que herda da classe Geral o nome e o Id
public class Equipa extends Geral implements Pontuacao{

    private ArrayList<Jogador> jogadores;
    private ArrayList<Jogador> equipatitular;
    private int pontos;

    public Equipa() {
        super();
        this.jogadores = new ArrayList<>();
        this.equipatitular = new ArrayList<>();
        this.pontos = 0;
        hist();
    }

    public Equipa(String nome, String id){
        super(nome,id);
        this.jogadores = new ArrayList<>();
        this.equipatitular = new ArrayList<>();
        this.pontos = 0;
    }

    public Equipa(String nome, String id, ArrayList<Jogador> a, ArrayList<Jogador> b) {
        super(nome, id);
        this.jogadores = new ArrayList<>(a);
        this.equipatitular = new ArrayList<>(b);
        this.pontos = 0;
        hist();
    }

    //Neste construtor nao é preciso dar update ao historico uma vez que a equipa ja vai receber os jogadores com um historico
    public Equipa(Equipa b) {
        super(b);
        this.jogadores = b.getJogadores();
        this.equipatitular = b.getEquipatitular();
        this.pontos = 0;
    }

    public String getId() {
        return super.getId();
    }

    public String getNome() {
        return super.getNome();
    }

    public ArrayList<Jogador> getEquipatitular() {
        return this.equipatitular.stream().map(Jogador::clone).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Jogador> getJogadores() {
        return this.jogadores.stream().map(Jogador::clone).collect(Collectors.toCollection(ArrayList::new));
    }

    public void setJogadores(ArrayList<Jogador> a) {
        this.jogadores = new ArrayList<>(a);
        hist();
    }

    public void setId(String id) {
        super.setId(id);
    }

    //Poe jogadores na equipa titular organizados por uma tatica
    public void setEquipatitular(ArrayList<Jogador> titular) throws ExcecaoPos {

        int defs = (int) this.jogadores.stream().filter(e -> e.getposicaostr().equals(DEFESA)).count();
        int lats = (int) this.jogadores.stream().filter(e -> e.getposicaostr().equals(LATERAL)).count();
        int meds = (int) this.jogadores.stream().filter(e -> e.getposicaostr().equals(MEDIO)).count();
        int avn = (int) this.jogadores.stream().filter(e -> e.getposicaostr().equals(AVANCADO)).count();

        ArrayList<Jogador> jogadores = new ArrayList<>();
        if (titular.size() == 11){
            if(taticavalida(defs+lats,meds,avn)) {
                setequipatittat(defs + lats, meds, avn, titular);
                hist();
            }
            else{
                setequipatittat(4,3,3,titular);
            }
            }
        else {
            if(titular.size()>11){
                throw new ExcecaoPos("Demasiados elementos");
            }
            else{
                this.equipatitular = titular.stream().collect(Collectors.toCollection(ArrayList::new));
            }
        }
    }

    public void setNome(String nome) throws ExcecaoPos {
        super.setNome(nome);
        hist();
    }

    //Se o jogador ja estiver na equipa nao vai ser possivel adiciona lo porque uma equipa nao pode ter dois jogadores iguais
    public void addJogador(Jogador a) throws ExcecaoPos {

        if (this.jogadores.stream().anyMatch(j -> j.equals(a))) {
            throw new ExcecaoPos("Jogador ja na equipa");
        }
        a.addhist(this);
        this.jogadores.add(a.clone());
    }

    public void removeJogador(Jogador a) throws ExcecaoPos {
        this.jogadores.remove(a);
        this.equipatitular.remove(a);
    }

    public void removeJogadorTitular(Jogador a) throws ExcecaoPos {
        if(this.equipatitular.contains(a)){
            a.sethabtit("");
            this.equipatitular.remove(a);
        }
        else{
            throw new ExcecaoPos("Jogador nao pertence a equipa titular");
        }
    }

    public void addjogequipatitular(Jogador a) throws ExcecaoPos {
        for (Jogador e : this.equipatitular) {
            if (e.equals(a)) {
                throw new ExcecaoPos("Jogador ja esta na equipa titular");
            }
        }
        a.sethabtit(a.getposicaostr());
        this.equipatitular.add(a.clone());
    }

    //Faz uma substituiçao entre jogadores que nao estao na equipa titular por jogadores que estao na equipa titular! Ainda falta implementar o metodo no jogo
    public void substitui(Jogador entra, Jogador sai) throws ExcecaoPos {
        String titular = new String();
        if (this.equipatitular.contains(sai)) {
            if (this.jogadores.contains(entra)) {
                for(Jogador j : this.equipatitular) {
                    if (j.equals(sai)) {
                        titular = j.getPosicao().getposTit();
                        j.sethabtit("");
                    }
                }

                this.equipatitular.remove(sai);
                entra.sethabtit(titular);
                this.equipatitular.add(entra);
            }
            else{
                throw new ExcecaoPos("O jogador " + sai.getNome() + " nao pertence a equipa de: " + getNome());
            }
        }
        else{
            throw new ExcecaoPos("O jogador " + sai.getNome() + " nao pertence a equipa titular de: " + getNome());
        }
    }

    //calcula a habilidade em cada posiçao da equipa
    public int habfrente() {

        return (int) (this.equipatitular.stream().filter(a -> a.getPosicao().getposTit().equals(AVANCADO)).mapToDouble(Jogador::getHabilidadeTit).sum() /
                this.equipatitular.stream().filter(a -> a.getPosicao().getposTit().equals(AVANCADO)).count());
    }

    public int hablateral() {

        return (int) (this.equipatitular.stream().filter(a -> a.getPosicao().getposTit().equals(LATERAL)).mapToDouble(Jogador::getHabilidadeTit).sum() /
                this.equipatitular.stream().filter(a -> a.getPosicao().getposTit().equals(LATERAL)).count());
    }

    public int habmedio() {
        return (int) (this.equipatitular.stream().filter(a -> a.getPosicao().getposTit().equals(MEDIO)).mapToDouble(Jogador::getHabilidadeTit).sum() /
                this.equipatitular.stream().filter(a -> a.getPosicao().getposTit().equals(MEDIO)).count());
    }

    public int habdefesa() {
        return (int) (this.equipatitular.stream().filter(a -> a.getPosicao().getposTit().equals(DEFESA)).mapToDouble(Jogador::getHabilidadeTit).sum() /
                this.equipatitular.stream().filter(a -> a.getPosicao().getposTit().equals(DEFESA)).count());
    }

    public int habredes() {
        return (int) (this.equipatitular.stream().filter(a -> a.getPosicao().getposTit().equals(REDES)).mapToDouble(Jogador::getHabilidadeTit).sum() /
                this.equipatitular.stream().filter(a -> a.getPosicao().getposTit().equals(REDES)).count());
    }

    public int habgeral(ArrayList<Jogador> jogadores) {
    if(jogadores.size()==0){return 0;}
    return (int) (jogadores.stream().mapToInt(Jogador::getHabilidade).sum() / (jogadores.stream().count()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Equipa equipa = (Equipa) o;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), jogadores, equipatitular);
    }

    public String toStringJogadores(){
        StringBuffer sb = new StringBuffer();
        sb.append("\nEquipa Titular");
        for(Jogador j : this.equipatitular){
            sb.append("\n" + j.toString());
        }
        sb.append("\n\nJogadores Substitutos");
        for(Jogador i : this.jogadores){
            if(!this.equipatitular.contains(i)){
                sb.append("\n" + i.toString());
            }
        }
        return sb.toString();
    }

    public String todosJogadores(){
        StringBuffer sb = new StringBuffer();
        sb.append("\n\nJogadores");
        for(Jogador j : this.jogadores){
            sb.append("\n" + j.toString());
        }
        return sb.toString();
    }
    public String toStringSimples(){
        StringBuffer sb = new StringBuffer();
        sb.append("-----------Equipa-----------\n\n");
        sb.append("Nome da equipa: " + super.getNome());
        sb.append("\nHabilidade geral: ").append(habgeral(this.jogadores));
        sb.append("\nId: " + super.getId());
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("-----------Equipa-----------\n\n");
        sb.append("Nome da equipa: " + super.getNome());
        sb.append("\nId: " + super.getId());
        sb.append("\nHabilidade geral: " + habgeral(this.jogadores));
        sb.append("\n-----------Jogadores-----------\n\n");
        for (Jogador e : this.jogadores) {
            sb.append("\n----------------------\n\n");
            sb.append(e.toString());
        }
        sb.append("\n-----------Jogadores titulares-----------\n\n");
        if (this.equipatitular.size() > 0) {
            sb.append("\nEquipa Titular: ");
            for (Jogador x : this.equipatitular) {
                sb.append("\n----------------------\n\n");
                sb.append(x.toString());
            }
        }
        sb.append("\n-------------------------\n\n");
        return sb.toString();
    }

    public Equipa clone() {
        return new Equipa(this);
    }

    //Faz update do historico de equipas de um jogador!
    //Como funciona? Se a ultima equipa no historico de equipas de um jogador for igual à equipa em questao, entao nao se adiciona, caso contrario adiciona-se
    //Sendo que a ultima equipa é a equipa em que o jogador se encontra
    private void hist() {
        for (Jogador e : this.jogadores) {
            for (Jogador k : this.equipatitular) {
                if (!k.ultimo().getNome().equals(this.getNome())) {
                    k.addhist(this);
                }
            }
            if (!e.ultimo().getNome().equals(this.getNome())) {
                e.addhist(this);
            }
        }
    }

    public Jogador identificaJogadorId(String id){
        Jogador ret = null;
        for (Jogador j : this.jogadores){
            if (j.getId().equals(id)) ret = j;
        }
        return ret;
    }

    public boolean temjogador(String nome) {
        return getJogadores().stream().anyMatch(a -> a.getNome().equalsIgnoreCase(nome));
    }

    public Jogador identificaJogador(String nome) {
        return this.jogadores.stream().filter(j -> j.getNome().equalsIgnoreCase(nome)).findFirst().map(Jogador::clone).get();
    }

    public ArrayList<Jogador> jogadoresnome(String nome) {
        return this.jogadores.stream().filter(j -> j.getNome().equalsIgnoreCase(nome)).collect(Collectors.toCollection(ArrayList::new));
    }

    //Faz update dos jogadores numa equipa, metodo chamado no menu quando se faz transferencias de jogadores entre equipas
    //Ou quando se poe um jogador a titular ou nao
    public void update(Equipa a) {
        this.equipatitular = a.getEquipatitular();
        this.jogadores = a.getJogadores();
    }

    //verifica se a tatica é valida ou nao, futuramente podemos adicionar mais
    public boolean taticavalida(int nrdefesas, int nrmedios, int nravancados) {
        boolean res = false;
        if (nrdefesas + nrmedios + nravancados == 10) {
            if (nrdefesas == 4 && nrmedios == 4 && nravancados == 2) res = true;
            if (nrdefesas == 4 && nrmedios == 3 && nravancados == 3) res = true;
            if (nrdefesas == 3 && nrmedios == 5 && nravancados == 2) res = true;
        }
        return res;
    }

    //Metodo para ordenar os jogadores por posiçao, em funcao da habilidade! Ordena do que tem mais habilidade para o que tem menos
    public List<Jogador> ordenajogpos(Posicao pos) {
        Comparator<Jogador> comp = (e1, e2) -> (int) e2.getHabilidade() - e1.getHabilidade();
        return this.jogadores.stream().filter(e -> e.getPosicao().equals(pos)).sorted(comp).collect(Collectors.toList());
    }

    //ordena um conjunto de jogadores em funçao da sua habilidade numa posicao
    public List<Jogador> ordenajogposjogadores(Posicao pos,ArrayList<Jogador> jogadores) {
        Comparator<Jogador> comp = (e1, e2) -> (int) e2.getHabilidade() - e1.getHabilidade();
        return jogadores.stream().filter(e -> e.getPosicao().equals(pos)).sorted(comp).collect(Collectors.toList());
    }

    //ordena um conjunto de jogadores em funçao da sua habilidade na posicao titular
    public List<Jogador> ordena(ArrayList<Jogador> jogadores){
        ArrayList<Jogador> clones = jogadores.stream().map(Jogador::clone).collect(Collectors.toCollection(ArrayList::new));
        Comparator<Jogador> comp = (e1,e2) -> (int) e2.getHabilidadeTit() - e1.getHabilidadeTit();
        return clones.stream().sorted(comp).collect(Collectors.toList());
    }

    //Objetivo? Pegar nos jogadores e definir as suas posiçoes em funçao da tatica. Caso nao existam jogadores com a posiçao "favorita" suficientes
    //Para satisfazer os jogadores necessarios para essa posiçao na tatica, entao vai buscar jogadores de outras posiçoes!
    public void setequipatittat(int nrdefesas, int nrmedios, int nravancados,ArrayList<Jogador> jogadores) throws ExcecaoPos {
        for(Jogador o : jogadores){
            if (!this.jogadores.contains(o)){
                throw new ExcecaoPos("Jogador nao esta na equipa");
            }
        }

        ArrayList<Jogador> aux = jogadores.stream().map(Jogador::clone).collect(Collectors.toCollection(ArrayList::new));
        if(taticavalida(nrdefesas,nrmedios,nravancados)){

        for(Jogador j : aux){
            j.sethabtit(Geral.DEFESA);
        }
        List<Jogador> defesas = ordena(aux);
        for(int n=0;n<nrdefesas-2;n++){
            this.equipatitular.add(defesas.get(n).clone());
            aux.remove(defesas.get(n));

        }

        for(Jogador j : aux){
            j.sethabtit(Geral.MEDIO);
        }

        List<Jogador> medios = ordena(aux);

        for(int n=0;n<nrmedios;n++){
            this.equipatitular.add(medios.get(n).clone());
            aux.remove(medios.get(n));
        }

        for(Jogador j : aux){
            j.sethabtit(Geral.LATERAL);
        }
        List<Jogador> laterais = ordena(aux);

        for(int n=0;n<2;n++){
            this.equipatitular.add(laterais.get(n).clone());
            aux.remove(laterais.get(n));
        }

        for(Jogador j : aux){
            j.sethabtit(Geral.AVANCADO);
        }
        List<Jogador> avancados = ordena(aux);

        for(int n=0;n<nravancados;n++){
            this.equipatitular.add(avancados.get(n).clone());
            aux.remove(avancados.get(n));
        }

        for(Jogador j : aux){
            j.sethabtit(Geral.REDES);
        }
        List<Jogador> redes = ordena(aux);
        this.equipatitular.add(redes.get(0));
        aux.remove(redes.get(0));
        }
    }

    //metodos uteis para a liga
    public void pontos(int pontos) {
        this.pontos += pontos;
    }

    public int getpontos(){
        return this.pontos;
    }

    public void setPontos(int k){
        this.pontos = k;
    }

    public void guardaequipa() throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter("C:\\Users\\Pestana\\Desktop\\POO\\Projeto\\src\\output.txt",true));
        escritor.write("\nEquipa:"+ getNome());
        escritor.flush();
        for(Jogador j : this.jogadores){
            j.guarda();
            escritor.flush();
        }
        escritor.flush();
        escritor.close();
    }

    public static Equipa parse(String input,String id){
        String[] campos = input.split(",");
        return new Equipa(campos[0],id);
    }
}
