import java.util.ArrayList;

public class Avancado extends Jogador {

    public Avancado() {
        super();
    }

    public Avancado(String id,String nome, ArrayList<Equipa> a
    ,int v, int r,int d, int i, int c, int remate, int p) throws ExcecaoPos {
        super(id,nome,new Posicao(AVANCADO),v,r,d,i,c,remate,p,a);
        super.sethab(calculahabilidade(v,r,d,i,c,remate,p));
    }

    public Avancado(String id,String nome
            ,int v, int r,int d, int i, int c, int remate, int p) throws ExcecaoPos {
        super(id,nome,new Posicao(AVANCADO),v,r,d,i,c,remate,p);
        super.sethab(calculahabilidade(v,r,d,i,c,remate,p));
    }

    public Avancado(Avancado e) {
        super(e);
    }

    public Posicao getPosicao(){
        return super.getPosicao();
    }

    public String getposicaostr(){
        return super.getPosicao().getpos();
    }

    public int calculahabilidade(int v, int r,int d, int i, int c, int remate, int p){
        return (int) (0.15 * v + 0.12 * r + 0.15 * d +  0.13 * i
                + 0.15 * c + 0.2 * remate + 0.1 * p);
    }

    public Avancado clone(){
        return new Avancado(this);
    }

    public static Avancado parse(String input) throws ExcecaoPos {
        String[] campos = input.split(",");
        return new Avancado(campos[1], campos[0],
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]));
    }

}