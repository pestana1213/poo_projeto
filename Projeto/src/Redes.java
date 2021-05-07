import java.util.ArrayList;

public class Redes extends Jogador {

    private int elasticidade;

    public Redes() throws ExcecaoPos {
        super();
        super.setPosicao(new Posicao(Posicao.REDES));

        this.elasticidade = 0;
    }

    public Redes(String id, String nome, ArrayList<Equipa> a
            , int v, int r, int d, int i, int c, int remate, int p, int e) throws ExcecaoPos {
        super(id,nome,new Posicao(Posicao.REDES),v,r,d,i,c,remate,p,a);
        int hab = calculahabilidade(v,r,d,i,c,remate,p,e);
        super.sethab(hab);
        this.elasticidade = e;
    }

    public Redes(String id, String nome
            , int v, int r, int d, int i, int c, int remate, int p, int e) throws ExcecaoPos {
        super(id,nome,new Posicao(Posicao.REDES),v,r,d,i,c,remate,p);
        int hab = calculahabilidade(v,r,d,i,c,remate,p,e);
        super.sethab(hab);
        this.elasticidade = e;
    }

    public Redes(Redes e) {
        super(e);
        this.elasticidade = e.getElasticidade();

    }

    public int calculahabilidade(int v, int r,int d, int i, int c, int remate, int p,int e){
       return (int) (0.12 * v + 0.15 * r + 0.15 * d +  0.19 * i
                + 0.02 * c + 0.03 * remate + 0.14 * p + 0.2 * e);
         }

    public Posicao getPosicao(){
        return super.getPosicao();
    }

    public String getposicaostr(){
        return super.getPosicao().getpos();
    }

    public int getElasticidade() {
        return elasticidade;
    }

    public Redes clone(){
        return new Redes(this);
    }

    public static Redes parse(String input) throws ExcecaoPos {
        String[] campos = input.split(",");
        return new Redes(campos[1], campos[0],
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]));
    }
}