import java.util.*;

//Class que herda da classe Geral o nome e o Id
public class Jogador extends Geral implements Habilidades{

    private Posicao posicao;
    private int habilidade;
    private int habilidadeTit;
    private ArrayList<Equipa> historico;

    public Jogador(){
        super();
        this.posicao = new Posicao();
        this.habilidade = 0;
        this.habilidadeTit = 0;
        this.historico = new ArrayList<>();
    }

    public Jogador(String id,String nome, String pos, int hab, ArrayList<Equipa> a) throws ExcecaoPos{
        super(nome,id);
        this.posicao = new Posicao(pos);
        this.habilidade = hab;
        this.habilidadeTit = 0;
        this.historico = new ArrayList<>(a);
    }

    public Jogador(String id,String nome, String pos, int hab) throws ExcecaoPos{
        super(nome,id);
        this.posicao = new Posicao(pos);
        this.habilidade = hab;
        this.habilidadeTit = 0;
        this.historico = new ArrayList<>();
    }

    public Jogador(String id,String nome, String pos, int hab,int habtit) throws ExcecaoPos{
        super(nome,id);
        this.posicao = new Posicao(pos);
        this.habilidade = hab;
        this.habilidadeTit = habtit;
        this.historico = new ArrayList<>();
    }

    public Jogador(String id, String nome, Posicao pos, int hab){
        super(nome,id);
        this.posicao = pos;
        this.habilidade = hab;
        this.habilidadeTit = 0;
        this.historico = new ArrayList<>();
    }

    public Jogador(String id, String nome, Posicao pos, int hab,ArrayList<Equipa>a){
        super(nome,id);
        this.posicao = pos;
        this.habilidade = hab;
        this.habilidadeTit = 0;
        this.historico = new ArrayList<>(a);
    }

    public Jogador(String id, String nome, Posicao pos, int hab,int habtit, ArrayList<Equipa>a){
        super(nome,id);
        this.posicao = pos;
        this.habilidade = hab;
        this.habilidadeTit = habtit;
        this.historico = new ArrayList<>(a);
    }

    public Jogador (Jogador a){
        super(a);
        this.posicao = a.getPosicao();
        this.habilidade = a.getHabilidade();
        this.habilidadeTit = a.getHabilidadeTit();
        this.historico = a.getHistorico();
    }

    public String getId() {
        return super.getId();
    }

    public String getNome() {
        return super.getNome();
    }

    public Posicao getPosicao(){
        return this.posicao.clone();
    }

    public String getposicaostr(){
        return this.posicao.getpos();
    }

    public int getHabilidade() {
        return this.habilidade;
    }

    public int getHabilidadeTit(){ return this.habilidadeTit;}

    public ArrayList<Equipa> getHistorico() {
        return new ArrayList<>(this.historico);
        // this.historico.stream().map(Equipa::clone).collect(Collectors.toCollection(ArrayList::new));
    }

    public void setId(String id) {
        super.setId(id);
    }

    public void setNome(String nome) throws ExcecaoPos {
        super.setNome(nome);
    }

    public void setHistorico(ArrayList<Equipa> a){
        this.historico = new ArrayList<>(a);
    }

    public void setPosicao(Posicao pos) {
        this.posicao = pos;
    }

    public void setHabilidade(int habilidade) {
        this.habilidade = habilidade;
    }

    public void setHabilidadeTit(int hab) { this.habilidade = hab;}

    //Adiciona uma equipa ao historico, metodo utilizado na classe Equipa
    public void addhist (Equipa a){
        this.historico.add(a);
    }

    //Calculo das habilidades em funçao da posiçao do jogador
    public void velocidade(int x) {
        if(this.posicao.getpos().equals(AVANCADO) && x>=0 && x <=100){
            this.habilidade += (0.15 * x);
        }
        if(this.posicao.getpos().equals(DEFESA)&& x>=0 && x <=100){
            this.habilidade += (0.15 * x);
        }
        if(this.posicao.getpos().equals(MEDIO)&& x>=0 && x <=100){
            this.habilidade += (0.13 * x);
        }
        if(this.posicao.getpos().equals(LATERAL)&& x>=0 && x <=100){
            this.habilidade += (0.16 * x);
        }
        if(this.posicao.getpos().equals(REDES)&& x>=0 && x <=100){
            this.habilidade += (0.12 * x);
        }
    }

    public double getvelocidade(){
        if(this.posicao.getpos().equals(AVANCADO)){
            return this.habilidade*0.15;
        }
        if(this.posicao.getpos().equals(DEFESA)){
            return this.habilidade*0.15 ;
        }
        if(this.posicao.getpos().equals(MEDIO)){
            return this.habilidade *0.13 ;
        }
        if(this.posicao.getpos().equals(LATERAL)){
            return this.habilidade *0.16 ;
        }
        if(this.posicao.getpos().equals(REDES)){
            return this.habilidade * 0.12;
        }
        return 0;
        }

    public void resistencia(int x) {
        if(this.posicao.getpos().equals(AVANCADO) && x>=0 && x <=100){
            this.habilidade += (0.12 * x);
        }
        if(this.posicao.getpos().equals(DEFESA)&& x>=0 && x <=100){
            this.habilidade += (0.15 * x);
        }
        if(this.posicao.getpos().equals(MEDIO)&& x>=0 && x <=100){
            this.habilidade += (0.15 * x);
        }
        if(this.posicao.getpos().equals(LATERAL)&& x>=0 && x <=100){
            this.habilidade += (0.18 * x);
        }
        if(this.posicao.getpos().equals(REDES)&& x>=0 && x <=100){
            this.habilidade += (0.15 * x);
        }
    }

    public double getresistencia(){
        if(this.posicao.getpos().equals(AVANCADO)){
            return this.habilidade*0.12;
        }
        if(this.posicao.getpos().equals(DEFESA)){
            return this.habilidade*0.15 ;
        }
        if(this.posicao.getpos().equals(MEDIO)){
            return this.habilidade *0.15 ;
        }
        if(this.posicao.getpos().equals(LATERAL)){
            return this.habilidade *0.18 ;
        }
        if(this.posicao.getpos().equals(REDES)){
            return this.habilidade * 0.15;
        }
        return 0;
    }

    public void destreza(int x) {
        if(this.posicao.getpos().equals(AVANCADO) && x>=0 && x <=100){
            this.habilidade += (0.15 * x);
        }
        if(this.posicao.getpos().equals(DEFESA)&& x>=0 && x <=100){
            this.habilidade += (0.18 * x);
        }
        if(this.posicao.getpos().equals(MEDIO)&& x>=0 && x <=100){
            this.habilidade += (0.15 * x);
        }
        if(this.posicao.getpos().equals(LATERAL)&& x>=0 && x <=100){
            this.habilidade += (0.15 * x);
        }
        if(this.posicao.getpos().equals(REDES)&& x>=0 && x <=100){
            this.habilidade += (0.15 * x);
        }
    }

    public double getdestreza(){
        if(this.posicao.getpos().equals(AVANCADO)){
            return this.habilidade*0.15;
        }
        if(this.posicao.getpos().equals(DEFESA)){
            return this.habilidade*0.18 ;
        }
        if(this.posicao.getpos().equals(MEDIO)){
            return this.habilidade *0.15;
        }
        if(this.posicao.getpos().equals(LATERAL)){
            return this.habilidade *0.15;
        }
        if(this.posicao.getpos().equals(REDES)){
            return this.habilidade * 0.15;
        }
        return 0;
    }

    public void impulsao(int x) {
        if(this.posicao.getpos().equals(AVANCADO) && x>=0 && x <=100){
            this.habilidade += (0.13 * x);
        }
        if(this.posicao.getpos().equals(DEFESA)&& x>=0 && x <=100){
            this.habilidade += (0.15 * x);
        }
        if(this.posicao.getpos().equals(MEDIO)&& x>=0 && x <=100){
            this.habilidade += (0.12 * x);
        }
        if(this.posicao.getpos().equals(LATERAL)&& x>=0 && x <=100){
            this.habilidade += (0.11 * x);
        }
        if(this.posicao.getpos().equals(REDES)&& x>=0 && x <=100){
            this.habilidade += (0.19 * x);
        }
    }

    public double getimpulsao(){
        if(this.posicao.getpos().equals(AVANCADO)){
            return this.habilidade*0.13;
        }
        if(this.posicao.getpos().equals(DEFESA)){
            return this.habilidade*0.15 ;
        }
        if(this.posicao.getpos().equals(MEDIO)){
            return this.habilidade *0.12 ;
        }
        if(this.posicao.getpos().equals(LATERAL)){
            return this.habilidade *0.11 ;
        }
        if(this.posicao.getpos().equals(REDES)){
            return this.habilidade * 0.19;
        }
        return 0;
    }

    public void cabeca(int x) {
        if(this.posicao.getpos().equals(AVANCADO) && x>=0 && x <=100){
            this.habilidade += (0.15 * x);
        }
        if(this.posicao.getpos().equals(DEFESA)&& x>=0 && x <=100){
            this.habilidade += (0.14 * x);
        }
        if(this.posicao.getpos().equals(MEDIO)&& x>=0 && x <=100){
            this.habilidade += (0.12 * x);
        }
        if(this.posicao.getpos().equals(LATERAL)&& x>=0 && x <=100){
            this.habilidade += (0.10 * x);
        }
        if(this.posicao.getpos().equals(REDES)&& x>=0 && x <=100){
            this.habilidade += (0.02 * x);
        }
    }

    public double getcabeca(){
        if(this.posicao.getpos().equals(AVANCADO)){
            return this.habilidade*0.15;
        }
        if(this.posicao.getpos().equals(DEFESA)){
            return this.habilidade*0.14 ;
        }
        if(this.posicao.getpos().equals(MEDIO)){
            return this.habilidade *0.12 ;
        }
        if(this.posicao.getpos().equals(LATERAL)){
            return this.habilidade *0.10 ;
        }
        if(this.posicao.getpos().equals(REDES)){
            return this.habilidade * 0.02;
        }
        return 0;
    }

    public void remate(int x) {
        if(this.posicao.getpos().equals(AVANCADO) && x>=0 && x <=100){
            this.habilidade += (0.2 * x);
        }
        if(this.posicao.getpos().equals(DEFESA)&& x>=0 && x <=100){
            this.habilidade += (0.08 * x);
        }
        if(this.posicao.getpos().equals(MEDIO)&& x>=0 && x <=100){
            this.habilidade += (0.12 * x);
        }
        if(this.posicao.getpos().equals(LATERAL)&& x>=0 && x <=100){
            this.habilidade += (0.12 * x);
        }
        if(this.posicao.getpos().equals(REDES)&& x>=0 && x <=100){
            this.habilidade += (0.03 * x);
        }
    }

    public double getremate(){
        if(this.posicao.getpos().equals(AVANCADO)){
            return this.habilidade*0.2;
        }
        if(this.posicao.getpos().equals(DEFESA)){
            return this.habilidade*0.08 ;
        }
        if(this.posicao.getpos().equals(MEDIO)){
            return this.habilidade*0.12 ;
        }
        if(this.posicao.getpos().equals(LATERAL)){
            return this.habilidade*0.12 ;
        }
        if(this.posicao.getpos().equals(REDES)){
            return this.habilidade*0.03;
        }
        return 0;
    }

    public void passe(int x) {
        if(this.posicao.getpos().equals(AVANCADO) && x>=0 && x <=100){
            this.habilidade += (0.1 * x);
        }
        if(this.posicao.getpos().equals(DEFESA)&& x>=0 && x <=100){
            this.habilidade += (0.15 * x);
        }
        if(this.posicao.getpos().equals(MEDIO)&& x>=0 && x <=100){
            this.habilidade += (0.21 * x);
        }
        if(this.posicao.getpos().equals(LATERAL)&& x>=0 && x <=100){
            this.habilidade += (0.18 * x);
        }
        if(this.posicao.getpos().equals(REDES)&& x>=0 && x <=100){
            this.habilidade += (0.14 * x);
        }
    }

    public double getpasse(){
        if(this.posicao.getpos().equals(AVANCADO)){
            return this.habilidade*0.1;
        }
        if(this.posicao.getpos().equals(DEFESA)){
            return this.habilidade*0.15 ;
        }
        if(this.posicao.getpos().equals(MEDIO)){
            return this.habilidade *0.21 ;
        }
        if(this.posicao.getpos().equals(LATERAL)){
            return this.habilidade *0.18 ;
        }
        if(this.posicao.getpos().equals(REDES)){
            return this.habilidade * 0.14;
        }
        return 0;
    }

    public void elasticidade(int x ){
        if(this.posicao.getpos().equals(REDES)&& x>=0 && x <=100){
            this.habilidade += (0.20 * x);
        }
    }

    public double getelasticidade(){
        if(this.posicao.getpos().equals(REDES)){
            return this.habilidade*0.2;
        }
        return 0;
    }

    //Ainda faltam adicionar habilidades "extra" aos laterias etc

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Jogador jogador = (Jogador) o;
        return habilidade == jogador.habilidade && posicao.equals(jogador.posicao) && historico.equals(jogador.historico);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), posicao, habilidade);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("\nJogador: ").append(super.toString());
        sb.append(this.posicao.toString());
        sb.append("\nHabilidade geral: ").append(this.habilidade);
        if (!this.posicao.getposTit().equals("")){
        sb.append("\nHabilidade na posicao titular: ").append(this.habilidadeTit);}
        sb.append("\nHistorico: ");
        for(Equipa a: this.historico){
            sb.append(a.getNome()).append(" ");
        }
        return sb.toString();
    }

    public String toStringcomid(){
        StringBuilder sb = new StringBuilder();

        sb.append("\nJogador: ").append(super.toString());
        sb.append("\nId: ").append(super.getId());
        sb.append(this.posicao.toString());
        sb.append("\nHabilidade geral: ").append(this.habilidade);
        if (!this.posicao.getposTit().equals("")){
            sb.append("\nHabilidade na posicao titular: ").append(this.habilidadeTit);
        }
        sb.append("\nHistorico: ");
        for(Equipa a: this.historico){
            sb.append(a.getNome()).append(" ");
        }
        return sb.toString();
    }
    public String toStringcomhab(){
        StringBuilder sb = new StringBuilder();

        sb.append("\nJogador: ").append(super.toString());
        sb.append("\nId: ").append(super.getId());
        sb.append(this.posicao.toString());
        sb.append("\nHabilidade geral: ").append(this.habilidade);
        if (!this.posicao.getposTit().equals("")){
            sb.append("\nHabilidade na posicao titular: ").append(this.habilidadeTit);}
        sb.append("\nRemate: ").append(getremate());
        sb.append("\nDestreza: ").append(getdestreza());
        sb.append("\nCabeceamento: ").append(getcabeca());
        sb.append("\nImpulsao: ").append(getimpulsao());
        sb.append("\nVelocidade: ").append(getvelocidade());
        sb.append("\nResistencia: ").append(getresistencia());
        sb.append("\nPasse: ").append(getpasse());
        if(getposicaostr().equals(REDES)){
            sb.append("\nElasticidade: ").append(getelasticidade());
        }
        sb.append("\nHistorico: ");
        for(Equipa a: this.historico){
            sb.append(a.getNome()).append(" ");
        }
        return sb.toString();
    }

    public Jogador clone(){
        return new Jogador(this);
    }

    //Metodo para ver em que equipa é que o jogador se encontra! A ultima equipa no historico é a equipa em que o jogador se encontra
    public Equipa ultimo(){
        Equipa res = new Equipa();
        if(this.historico.size()!=0) {
            ArrayList<Equipa> hm = new ArrayList<>(this.historico);
            res = hm.get(this.historico.size()-1);
        }

        return res;
    }

    //Metodo que vê a habilidade do jogador em certa posicao! Caso seja a sua posiçao "favorita" entao a habilidade na equipa titular vai ser igual à sua habilidade
    //Caso contrario vai ter "penalizacoes"
    public void sethabtit(String pos) throws ExcecaoPos {
        this.posicao.setposTit(pos);
        String posicao = this.posicao.getpos();

        if (pos.equals(posicao)){
            this.habilidadeTit = this.getHabilidade();
        }

        switch (pos) {
            case (DEFESA) -> {
                if (posicao.equals(MEDIO)) {
                    this.habilidadeTit = getHabilidade() - (int) (0.40 * getHabilidade());
                }
                if (posicao.equals(LATERAL)) {
                    this.habilidadeTit = getHabilidade() - (int) (0.20 * getHabilidade());
                }
                if (posicao.equals(AVANCADO)) {
                    this.habilidadeTit = getHabilidade() - (int) (0.60 * getHabilidade());
                }
                if (posicao.equals(REDES)) {
                    this.habilidadeTit = getHabilidade() - (int) (0.30 * getHabilidade());
                }
            }
            case (MEDIO) -> {
                if (posicao.equals(DEFESA)) {
                    this.habilidadeTit = getHabilidade() - (int) (0.60 * getHabilidade());
                }
                if (posicao.equals(LATERAL)) {
                    this.habilidadeTit = getHabilidade() - (int) (0.40 * getHabilidade());
                }
                if (posicao.equals(AVANCADO)) {
                    this.habilidadeTit = getHabilidade() - (int) (0.40 * getHabilidade());
                }
                if (posicao.equals(REDES)) {
                    this.habilidadeTit = getHabilidade() - (int) (0.50 * getHabilidade());
                }
            }
            case (AVANCADO) -> {
                if (posicao.equals(MEDIO)) {
                    this.habilidadeTit = getHabilidade() - (int) (0.30 * getHabilidade());
                }
                if (posicao.equals(LATERAL)) {
                    this.habilidadeTit = getHabilidade() - (int) (0.40 * getHabilidade());
                }
                if (posicao.equals(DEFESA)) {
                    this.habilidadeTit = getHabilidade() - (int) (0.60 * getHabilidade());
                }
                if (posicao.equals(REDES)) {
                    this.habilidadeTit = getHabilidade() - (int) (0.70 * getHabilidade());
                }
            }
            case (LATERAL) -> {
                if (posicao.equals(MEDIO)) {
                    this.habilidadeTit = getHabilidade() - (int) (0.30 * getHabilidade());
                }
                if (posicao.equals(DEFESA)) {
                    this.habilidadeTit = getHabilidade() - (int) (0.00 * getHabilidade());
                }
                if (posicao.equals(AVANCADO)) {
                    this.habilidadeTit = getHabilidade() - (int) (0.40 * getHabilidade());
                }
                if (posicao.equals(REDES)) {
                    this.habilidadeTit = getHabilidade() - (int) (0.40 * getHabilidade());
                }
            }
            case (REDES) -> {
                if (posicao.equals(MEDIO)) {
                    this.habilidadeTit = getHabilidade() - (int) (0.40 * getHabilidade());
                }
                if (posicao.equals(DEFESA)) {
                    this.habilidadeTit = getHabilidade() - (int) (0.30 * getHabilidade());
                }
                if (posicao.equals(AVANCADO)) {
                    this.habilidadeTit = getHabilidade() - (int) (0.60 * getHabilidade());
                }
                if (posicao.equals(LATERAL)) {
                    this.habilidadeTit = getHabilidade() - (int) (0.40 * getHabilidade());
                }
            }
        }
    }

}
