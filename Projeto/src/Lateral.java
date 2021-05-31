import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Lateral extends Jogador {

    private int cruzamento;

    public Lateral() throws ExcecaoPos {
        super();
        super.setPosicao(new Posicao(Posicao.LATERAL));
        this.cruzamento = 0;
    }

    public Lateral(String id,String nome, ArrayList<Equipa> a
            ,int v, int r,int d, int i, int c, int remate, int p,int cruzamento) throws ExcecaoPos {
        super(id,nome,new Posicao(Posicao.LATERAL),v,r,d,i,c,remate,p,a);
        this.cruzamento = cruzamento;
        super.sethab(calculahabilidade(v,r,d,i,c,remate,p,cruzamento));
     }

    public Lateral(String id,String nome
            ,int v, int r,int d, int i, int c, int remate, int p,int cruzamento) throws ExcecaoPos {
        super(id,nome,new Posicao(Posicao.LATERAL),v,r,d,i,c,remate,p);
        this.cruzamento = cruzamento;
        super.sethab(calculahabilidade(v,r,d,i,c,remate,p,cruzamento));
    }

    public Lateral(Lateral e) {
        super(e);
        this.cruzamento = e.getCruzamento();
    }

    public int getCruzamento(){
        return this.cruzamento;
    }

    public void setCruzamento(int cruza){
        this.cruzamento = cruza;
    }

    public int calculahabilidade(int v, int r,int d, int i, int c, int remate, int p,int cruza){
        return (int) (0.13 * v + 0.16 * r + 0.12 * d +  0.11 * i
                + 0.15 * c + 0.1 * remate + 0.18 * p + 0.1 * cruza);
    }

    public boolean equals(Lateral e){
        return super.equals(e);
    }

    public Lateral clone(){
        return new Lateral(this);
    }

    public void guarda() throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter("C:\\Users\\Pestana\\Desktop\\POO\\Projeto\\src\\output.txt",true));
        escritor.write("\nLateral:");
        escritor.flush();
        super.guarda();
        escritor.flush();
        escritor.write("," + getCruzamento());
        escritor.flush();
        escritor.close();
    }

    public static Lateral parse(String input) throws ExcecaoPos {
        String[] campos = input.split(",");
        return new Lateral(campos[1], campos[0],
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