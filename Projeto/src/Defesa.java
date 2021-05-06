import java.util.ArrayList;

public class Defesa extends Jogador {

    public Defesa() {
        super();
    }

    public Defesa(String id,String nome, ArrayList<Equipa> a
            ,int v, int r,int d, int i, int c, int remate, int p) throws ExcecaoPos {
        super(id,nome,new Posicao(Posicao.DEFESA),v,r,d,i,c,remate,p,a);
        super.sethab(calculahabilidade(v,r,d,i,c,remate,p));
    }
    public Defesa(String id,String nome
            ,int v, int r,int d, int i, int c, int remate, int p) throws ExcecaoPos {
        super(id,nome,new Posicao(Posicao.DEFESA),v,r,d,i,c,remate,p);
        super.sethab(calculahabilidade(v,r,d,i,c,remate,p));
    }

    public Defesa(Defesa e) {
        super(e);
    }
    public int calculahabilidade(int v, int r,int d, int i, int c, int remate, int p){
        return (int) (0.15 * v + 0.15 * r + 0.18 * d +  0.15 * i
                + 0.14 * c + 0.08 * remate + 0.15 * p);
    }

    public Defesa clone(){
        return new Defesa(this);
    }

    public static Defesa parse(String input) throws ExcecaoPos {
        String[] campos = input.split(",");
        return new Defesa(campos[1], campos[0],
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]));
    }
}