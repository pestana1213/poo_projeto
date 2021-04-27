import java.lang.String;
import java.util.Objects;

public class Posicao extends Geral{

    private String posicao;
    private String posicaoTitular;

    public Posicao(){
        this.posicao = new String();
        this.posicaoTitular = new String();
    }

    public Posicao(String pos) throws ExcecaoPos{
        if (pos.equals(REDES) || pos.equals(DEFESA) || pos.equals(MEDIO) || pos.equals(AVANCADO) || pos.equals(LATERAL)){
            this.posicao = pos;
            this.posicaoTitular = new String();
        }
        else {
            throw new ExcecaoPos("Posicao desconhecida");
        }
        }

    public Posicao(String pos,String titu) throws ExcecaoPos{
        if (pos.equals(REDES) || pos.equals(DEFESA) || pos.equals(MEDIO) || pos.equals(AVANCADO) || pos.equals(LATERAL)){
            this.posicao = pos;
            if (titu.equals(REDES) || titu.equals(DEFESA) || titu.equals(MEDIO) || titu.equals(AVANCADO) || titu.equals(LATERAL)) {
                this.posicaoTitular = titu;

            }
            }
        else {
            throw new ExcecaoPos("Posicao desconhecida");
        }
    }

    public Posicao (Posicao pos){
        this.posicao = pos.getpos();
        this.posicaoTitular = pos.getposTit();
    }

    public String getpos(){
        return this.posicao;
    }

    public String getposTit(){
        return this.posicaoTitular;
    }

    public void setpos(String pos) throws ExcecaoPos{
        if (pos.equals(REDES) || pos.equals(DEFESA) || pos.equals(MEDIO) || pos.equals(AVANCADO) || pos.equals(LATERAL)){
            this.posicao = pos;
        }
        else {
            throw new ExcecaoPos("Posicao desconhecida");
        }
    }

    public void setposTit(String pos) throws ExcecaoPos{
        if (pos.equals(REDES) || pos.equals(DEFESA) || pos.equals(MEDIO) || pos.equals(AVANCADO) || pos.equals(LATERAL)){
            this.posicaoTitular = pos;
        }
        else {
            throw new ExcecaoPos("Posicao desconhecida");
        }
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("\nPosicao: " + getpos());
        if(!this.posicaoTitular.equals("")){
        sb.append("\nPosicao na equipa titular: " + getposTit());}
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Posicao posicao1 = (Posicao) o;
        return Objects.equals(posicao, posicao1.posicao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), posicao);
    }

    public Posicao clone(){
        return new Posicao(this);
    }
}
