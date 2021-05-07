import java.util.Objects;

//Classe utilizada na classe jogador
public class Posicao{

    public static final String REDES = "Guarda-Redes";
    public static final String DEFESA = "Defesa";
    public static final String MEDIO = "Medio";
    public static final String AVANCADO= "Avancado";
    public static final String LATERAL = "Lateral";

    private String posicao;      //posicao favorita
    private String posicaoTitular;  //posicao que vai ter na equipa titular, caso pertença a essa

    public Posicao(){
        this.posicao = new String();
        this.posicaoTitular = new String();
    }

    public Posicao(String pos) throws ExcecaoPos{
        if (pos.equalsIgnoreCase(REDES) || pos.equalsIgnoreCase(DEFESA) || pos.equalsIgnoreCase(MEDIO) || pos.equalsIgnoreCase(AVANCADO) || pos.equalsIgnoreCase(LATERAL)){
            this.posicao = pos;
            this.posicaoTitular = new String();
        }
        else {
            throw new ExcecaoPos("Posicao desconhecida");
        }
        }

    //O jogador se nao pertencer à equipa titular nao vai ter posiçao titular, daí termos posto titu.equals("")
    public Posicao(String pos,String titu) throws ExcecaoPos{
        if (pos.equals(REDES) || pos.equals(DEFESA) || pos.equals(MEDIO) || pos.equals(AVANCADO) || pos.equals(LATERAL)){
            this.posicao = pos;
            if (titu.equals("") || titu.equals(REDES) || titu.equals(DEFESA) || titu.equals(MEDIO) || titu.equals(AVANCADO) || titu.equals(LATERAL)) {
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

    //O jogador tem sempre de ter uma posicao favorita
    public void setpos(String pos) throws ExcecaoPos{
        if (pos.equals(REDES) || pos.equals(DEFESA) || pos.equals(MEDIO) || pos.equals(AVANCADO) || pos.equals(LATERAL)){
            this.posicao = pos;
        }
        else {
            throw new ExcecaoPos("Posicao desconhecida");
        }
    }


    public void setposTit(String pos) throws ExcecaoPos{
        if (pos.equals("") || pos.equals(REDES) || pos.equals(DEFESA) || pos.equals(MEDIO) || pos.equals(AVANCADO) || pos.equals(LATERAL)){
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
