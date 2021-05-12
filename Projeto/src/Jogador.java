import java.util.ArrayList;
import java.util.Objects;

//Class que herda da classe Geral o nome e o Id
public class Jogador extends Geral{

    private Posicao posicao;
    private int velocidade;
    private int resistencia;
    private int destreza;
    private int impulsao;
    private int cabeca;
    private int remate;
    private int passe;
    private int habilidade;
    private int habilidadeTit;
    private ArrayList<Equipa> historico;

    public Jogador(){

        super();
        this.posicao = new Posicao();
        this.velocidade = 0;
        this.resistencia = 0;
        this.destreza = 0;
        this.impulsao = 0;
        this.cabeca = 0;
        this.remate = 0;
        this.habilidade = 0;
        this.habilidadeTit = 0;
        this.historico = new ArrayList<>();
    }

    public Jogador(String id, String nome, Posicao pos,
                   int v, int r, int d, int i, int c, int remate, int passe, ArrayList<Equipa>a){
        super(nome,id);
        this.posicao = pos;
        this.velocidade = v;
        this.resistencia = r;
        this.destreza = d;
        this.impulsao = i;
        this.cabeca = c;
        this.remate = remate;
        this.passe = passe;
        this.habilidade = 0;
        this.habilidadeTit = 0;
        this.historico = new ArrayList<>(a);
    }
    public Jogador(String id, String nome, Posicao pos,
                   int v, int r, int d, int i, int c, int remate, int passe){
        super(nome,id);
        this.posicao = pos;
        this.velocidade = v;
        this.resistencia = r;
        this.destreza = d;
        this.impulsao = i;
        this.cabeca = c;
        this.remate = remate;
        this.passe = passe;
        this.habilidade = 0;
        this.habilidadeTit = 0;
        this.historico = new ArrayList<>();
    }

    public Jogador (Jogador e){
        super(e);
        this.posicao = e.getPosicao();
        this.historico = e.getHistorico();
        this.velocidade = e.getVelocidade();
        this.resistencia = e.getResistencia();
        this.destreza = e.getDestreza();
        this.impulsao = e.getImpulsao();
        this.cabeca = e.getCabeca();
        this.remate = e.getRemate();
        this.passe = e.getPasse();
        this.habilidade = e.getHabilidade();
        this.habilidadeTit = e.getHabilidadeTit();
    }

    public int getCabeca() {
        return cabeca;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public int getResistencia() {
        return resistencia;
    }

    public int getDestreza() {
        return destreza;
    }

    public int getImpulsao() {
        return impulsao;
    }

    public int getRemate() {
        return remate;
    }

    public int getPasse() {
        return passe;
    }

    public String getId () {
        return super.getId();
    }

    public String getNome() {
        return super.getNome();
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public void setImpulsao(int impulsao) {
        this.impulsao = impulsao;
    }

    public void sethab(int hab){
        this.habilidade = hab;
    }
    public void setCabeca(int cabeca) {
        this.cabeca = cabeca;
    }

    public void setRemate(int remate) {
        this.remate = remate;
    }

    public void setPasse(int passe) {
        this.passe = passe;
    }

    public Posicao getPosicao(){
        return this.posicao.clone();
    }

    public String getposicaostr(){
        return this.posicao.getpos();
    }

    public int getHabilidade(){
        return this.habilidade;
    }

    public int getHabilidadeTit(){
        return this.habilidadeTit;
    }

    public ArrayList<Equipa> getHistorico() {
        return new ArrayList<>(this.historico);
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

    public void setHabilidadeTit(int hab){
        this.habilidadeTit = hab;
    }

    public void setHabilidade(int hab){
        this.habilidade = hab;
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

    //Adiciona uma equipa ao historico, metodo utilizado na classe Equipa
    public void addhist (Equipa a){
        if (!ultimo().equals(a)){
        this.historico.add(a);
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Jogador jogador = (Jogador) o;
        return posicao.equals(jogador.posicao) && historico.equals(jogador.historico);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), posicao);
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
        sb.append("\nRemate: ").append(getRemate());
        sb.append("\nDestreza: ").append(getDestreza());
        sb.append("\nCabeceamento: ").append(getCabeca());
        sb.append("\nImpulsao: ").append(getImpulsao());
        sb.append("\nVelocidade: ").append(getVelocidade());
        sb.append("\nResistencia: ").append(getResistencia());
        sb.append("\nPasse: ").append(getPasse());
        sb.append("\nHistorico: ");
        for(Equipa a: this.historico){
            sb.append(a.getNome()).append(" ");
        }
        return sb.toString();
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

    public Jogador clone(){
        return new Jogador(this);
    }

}
