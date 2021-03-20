import java.util.*;

public class Jogador extends Geral implements Habilidades{

    private Posicao posicao;
    private int habilidade;

    public Jogador(){
    super();
    this.posicao = new Posicao();
    this.habilidade = 0;
    }

    public Jogador(String id, String nome, String pos, int hab) throws ExcecaoPos {
    super(nome,id);
    Posicao poos = new Posicao(pos);
    this.posicao = poos;
    this.habilidade = hab;
    }

    public Jogador(String id, String nome, Posicao pos, int hab){
    super(nome,id);
    this.posicao = pos;
    this.habilidade = hab;
    }

    public Jogador (Jogador a){
        super(a);
        this.posicao = getPosicao();
        this.habilidade = getHabilidade();
    }

    public String getId() {
        return super.getId();
    }

    public String getNome() {
        return super.getNome();
    }

    public Posicao getPosicao() {
        return this.posicao;
    }

    public String getposicaostr(){
        return this.posicao.getpos();
    }

    public int getHabilidade() {
        return this.habilidade;
    }

    public void setId(String id) {
        super.setId(id);
    }

    public void setNome(String nome) throws ExcecaoPos {
        super.setNome(nome);
    }

    public void setPosicao(Posicao pos) {
        this.posicao = pos;
    }

    public void setHabilidade(int habilidade) {
        this.habilidade = habilidade;
    }


    @Override
    public void velocidade(int x) {
        if(posicao.equals(AVANCADO) && x>=0 && x <=100){
            this.habilidade += (0.15 * x);
        }
        if(posicao.equals(DEFESA)&& x>=0 && x <=100){
            this.habilidade += (0.15 * x);
        }
        if(posicao.getpos().equals(MEDIO)&& x>=0 && x <=100){
            this.habilidade += (0.13 * x);
        }
        if(posicao.getpos().equals(LATERAL)&& x>=0 && x <=100){
            this.habilidade += (0.16 * x);
        }
        if(posicao.getpos().equals(REDES)&& x>=0 && x <=100){
            this.habilidade += (0.12 * x);
        }
    }

    @Override
    public void resistencia(int x) {
        if(posicao.getpos().equals(AVANCADO) && x>=0 && x <=100){
            this.habilidade += (0.12 * x);
        }
        if(posicao.getpos().equals(DEFESA)&& x>=0 && x <=100){
            this.habilidade += (0.15 * x);
        }
        if(posicao.getpos().equals(MEDIO)&& x>=0 && x <=100){
            this.habilidade += (0.15 * x);
        }
        if(posicao.getpos().equals(LATERAL)&& x>=0 && x <=100){
            this.habilidade += (0.18 * x);
        }
        if(posicao.getpos().equals(REDES)&& x>=0 && x <=100){
            this.habilidade += (0.15 * x);
        }
    }

    @Override
    public void destreza(int x) {
        if(posicao.getpos().equals(AVANCADO) && x>=0 && x <=100){
            this.habilidade += (0.15 * x);
        }
        if(posicao.getpos().equals(DEFESA)&& x>=0 && x <=100){
            this.habilidade += (0.18 * x);
        }
        if(posicao.getpos().equals(MEDIO)&& x>=0 && x <=100){
            this.habilidade += (0.15 * x);
        }
        if(posicao.getpos().equals(LATERAL)&& x>=0 && x <=100){
            this.habilidade += (0.15 * x);
        }
        if(posicao.getpos().equals(REDES)&& x>=0 && x <=100){
            this.habilidade += (0.15 * x);
        }
    }

    @Override
    public void impulsao(int x) {
        if(posicao.getpos().equals(AVANCADO) && x>=0 && x <=100){
            this.habilidade += (0.13 * x);
        }
        if(posicao.getpos().equals(DEFESA)&& x>=0 && x <=100){
            this.habilidade += (0.15 * x);
        }
        if(posicao.getpos().equals(MEDIO)&& x>=0 && x <=100){
            this.habilidade += (0.12 * x);
        }
        if(posicao.getpos().equals(LATERAL)&& x>=0 && x <=100){
            this.habilidade += (0.11 * x);
        }
        if(posicao.getpos().equals(REDES)&& x>=0 && x <=100){
            this.habilidade += (0.19 * x);
        }
    }

    @Override
    public void cabeca(int x) {
        if(posicao.getpos().equals(AVANCADO) && x>=0 && x <=100){
            this.habilidade += (0.15 * x);
        }
        if(posicao.getpos().equals(DEFESA)&& x>=0 && x <=100){
            this.habilidade += (0.14 * x);
        }
        if(posicao.getpos().equals(MEDIO)&& x>=0 && x <=100){
            this.habilidade += (0.12 * x);
        }
        if(posicao.getpos().equals(LATERAL)&& x>=0 && x <=100){
            this.habilidade += (0.10 * x);
        }
        if(posicao.getpos().equals(REDES)&& x>=0 && x <=100){
            this.habilidade += (0.02 * x);
        }
    }

    @Override
    public void remate(int x) {
        if(posicao.getpos().equals(AVANCADO) && x>=0 && x <=100){
            this.habilidade += (0.2 * x);
        }
        if(posicao.getpos().equals(DEFESA)&& x>=0 && x <=100){
            this.habilidade += (0.08 * x);
        }
        if(posicao.getpos().equals(MEDIO)&& x>=0 && x <=100){
            this.habilidade += (0.12 * x);
        }
        if(posicao.getpos().equals(LATERAL)&& x>=0 && x <=100){
            this.habilidade += (0.12 * x);
        }
        if(posicao.getpos().equals(REDES)&& x>=0 && x <=100){
            this.habilidade += (0.03 * x);
        }
    }

    @Override
    public void passe(int x) {
        if(posicao.getpos().equals(AVANCADO) && x>=0 && x <=100){
            this.habilidade += (0.1 * x);
        }
        if(posicao.getpos().equals(DEFESA)&& x>=0 && x <=100){
            this.habilidade += (0.15 * x);
        }
        if(posicao.getpos().equals(MEDIO)&& x>=0 && x <=100){
            this.habilidade += (0.21 * x);
        }
        if(posicao.getpos().equals(LATERAL)&& x>=0 && x <=100){
            this.habilidade += (0.18 * x);
        }
        if(posicao.getpos().equals(REDES)&& x>=0 && x <=100){
            this.habilidade += (0.14 * x);
        }
    }

    @Override
    public void elasticidade(int x ){
        if(posicao.getpos().equals(REDES)&& x>=0 && x <=100){
            this.habilidade += (0.20 * x);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Jogador jogador = (Jogador) o;
        return habilidade == jogador.habilidade && Objects.equals(posicao, jogador.posicao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), posicao, habilidade);
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();

        sb.append("\nJogador: " + super.toString());
        sb.append(this.posicao.toString());
        sb.append("\nHabilidade geral: " + habilidade);
        return sb.toString();
    }

    public Jogador clone(){
        return new Jogador(this);
    }
}
