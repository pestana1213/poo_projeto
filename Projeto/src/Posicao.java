import java.lang.String;
import java.util.Objects;

public class Posicao extends Geral{

    private String posicao;

    public Posicao(){
        this.posicao = new String();
    }

    public Posicao(String pos) throws ExcecaoPos{
        if (pos.equals(REDES) || pos.equals(DEFESA) || pos.equals(MEDIO) || pos.equals(AVANCADO) || pos.equals(LATERAL)){
            this.posicao = pos;
        }
        else {
            throw new ExcecaoPos("Posicao desconhecida");
        }
        }

    public Posicao (Posicao pos){
        this.posicao = pos.getpos();
    }

    public String getpos(){
        return this.posicao;
    }

    public void setpos(String pos) throws ExcecaoPos{
        if (pos.equals(REDES) || pos.equals(DEFESA) || pos.equals(MEDIO) || pos.equals(AVANCADO) || pos.equals(LATERAL)){
            this.posicao = pos;
        }
        else {
            throw new ExcecaoPos("Posicao desconhecida");
        }
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("\nPosicao: " + getpos());
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
