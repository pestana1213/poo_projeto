import java.util.*;
import java.util.stream.Collectors;


public class Jogador extends Geral implements Habilidades{

    private Posicao posicao;
    private int habilidade;
    private Map<String,Equipa> historico;

    public Jogador(){
    super();
    this.posicao = new Posicao();
    this.habilidade = 0;
    this.historico = new LinkedHashMap<>();
    }

    public Jogador(String id,String nome, String pos, int hab, Map<String,Equipa> a) throws ExcecaoPos{
        super(nome,id);
        this.posicao = new Posicao(pos);
        this.habilidade = hab;
        this.historico = new LinkedHashMap<>(a);
    }

    public Jogador(String id,String nome, String pos, int hab) throws ExcecaoPos{
        super(nome,id);
        this.posicao = new Posicao(pos);
        this.habilidade = hab;
        this.historico = new LinkedHashMap<>();
    }

    public Jogador(String id, String nome, Posicao pos, int hab){
        super(nome,id);
        this.posicao = pos;
        this.habilidade = hab;
        this.historico = new LinkedHashMap<>();
    }

    public Jogador(String id, String nome, Posicao pos, int hab,HashMap<String,Equipa>a){
        super(nome,id);
        this.posicao = pos;
        this.habilidade = hab;
        this.historico = new LinkedHashMap<>(a);
    }

    public Jogador (Jogador a){
        super(a);
        this.posicao = a.getPosicao();
        this.habilidade = a.getHabilidade();
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

    public Map<String, Equipa> getHistorico() {
        return this.historico.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void setId(String id) {
        super.setId(id);
    }

    public void setNome(String nome) throws ExcecaoPos {
        super.setNome(nome);
    }

    public void setHistorico(Map<String,Equipa> a){
        this.historico = new LinkedHashMap<>(a);
    }

    public void setPosicao(Posicao pos) {
        this.posicao = pos;
    }

    public void setHabilidade(int habilidade) {
        this.habilidade = habilidade;
    }

    public void addhist (Equipa a){
        this.historico.put(a.getId(), a);
    }

    @Override
    public void velocidade(int x) {
        if(posicao.getpos().equals(AVANCADO) && x>=0 && x <=100){
            this.habilidade += (0.15 * x);
        }
        if(posicao.getpos().equals(DEFESA)&& x>=0 && x <=100){
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

    public double getvelocidade(){
        if(posicao.getpos().equals(AVANCADO)){
            return this.habilidade/0.15;
        }
        if(posicao.getpos().equals(DEFESA)){
            return this.habilidade/0.15 ;
        }
        if(posicao.getpos().equals(MEDIO)){
            return this.habilidade /0.13 ;
        }
        if(posicao.getpos().equals(LATERAL)){
            return this.habilidade /0.16 ;
        }
        if(posicao.getpos().equals(REDES)){
            return this.habilidade / 0.12;
        }
        return 0;
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

    public double getresistencia(){
        if(posicao.getpos().equals(AVANCADO)){
            return this.habilidade/0.12;
        }
        if(posicao.getpos().equals(DEFESA)){
            return this.habilidade/0.15 ;
        }
        if(posicao.getpos().equals(MEDIO)){
            return this.habilidade /0.15 ;
        }
        if(posicao.getpos().equals(LATERAL)){
            return this.habilidade /0.18 ;
        }
        if(posicao.getpos().equals(REDES)){
            return this.habilidade / 0.15;
        }
        return 0;
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

    public double getdestreza(){
        if(posicao.getpos().equals(AVANCADO)){
            return this.habilidade/0.15;
        }
        if(posicao.getpos().equals(DEFESA)){
            return this.habilidade/0.18 ;
        }
        if(posicao.getpos().equals(MEDIO)){
            return this.habilidade /0.15;
        }
        if(posicao.getpos().equals(LATERAL)){
            return this.habilidade /0.15;
        }
        if(posicao.getpos().equals(REDES)){
            return this.habilidade / 0.15;
        }
        return 0;
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

    public double getimpulsao(){
        if(posicao.getpos().equals(AVANCADO)){
            return this.habilidade/0.13;
        }
        if(posicao.getpos().equals(DEFESA)){
            return this.habilidade/0.15 ;
        }
        if(posicao.getpos().equals(MEDIO)){
            return this.habilidade /0.12 ;
        }
        if(posicao.getpos().equals(LATERAL)){
            return this.habilidade /0.11 ;
        }
        if(posicao.getpos().equals(REDES)){
            return this.habilidade / 0.19;
        }
        return 0;
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

    public double getcabeca(){
        if(posicao.getpos().equals(AVANCADO)){
            return this.habilidade/0.15;
        }
        if(posicao.getpos().equals(DEFESA)){
            return this.habilidade/0.14 ;
        }
        if(posicao.getpos().equals(MEDIO)){
            return this.habilidade /0.12 ;
        }
        if(posicao.getpos().equals(LATERAL)){
            return this.habilidade /0.10 ;
        }
        if(posicao.getpos().equals(REDES)){
            return this.habilidade / 0.02;
        }
        return 0;
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

    public double getremate(){
        if(posicao.getpos().equals(AVANCADO)){
            return this.habilidade/0.2;
        }
        if(posicao.getpos().equals(DEFESA)){
            return this.habilidade/0.98 ;
        }
        if(posicao.getpos().equals(MEDIO)){
            return this.habilidade /0.12 ;
        }
        if(posicao.getpos().equals(LATERAL)){
            return this.habilidade /0.12 ;
        }
        if(posicao.getpos().equals(REDES)){
            return this.habilidade / 0.03;
        }
        return 0;
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

    public double getpasse(){
        if(posicao.getpos().equals(AVANCADO)){
            return this.habilidade/0.1;
        }
        if(posicao.getpos().equals(DEFESA)){
            return this.habilidade/0.15 ;
        }
        if(posicao.getpos().equals(MEDIO)){
            return this.habilidade /0.21 ;
        }
        if(posicao.getpos().equals(LATERAL)){
            return this.habilidade /0.18 ;
        }
        if(posicao.getpos().equals(REDES)){
            return this.habilidade / 0.14;
        }
        return 0;
    }

    @Override
    public void elasticidade(int x ){
        if(posicao.getpos().equals(REDES)&& x>=0 && x <=100){
            this.habilidade += (0.20 * x);
        }
    }

    public double getelasticidade(){
        if(posicao.getpos().equals(REDES)){
            return this.habilidade/0.2;
        }
        return 0;
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
        sb.append("\nHistorico: ");
        for(Equipa a: this.historico.values()){
            sb.append( a.getNome() + " " );
        }
        return sb.toString();
    }

    public Jogador clone(){
        return new Jogador(this);
    }

    public Equipa ultimo(){

        Equipa res = new Equipa();
        if(this.historico.size()!=0) {
            ArrayList<Equipa> hm = new ArrayList<>(this.historico.values());
            res = hm.get(this.historico.size()-1);
        }

        return res;
    }
}
