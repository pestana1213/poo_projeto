import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Medio extends Jogador {

    private int recuperacao;

    public Medio() throws ExcecaoPos {
        super();
        super.setPosicao(new Posicao(Posicao.MEDIO));

    }

    public Medio(String id,String nome, ArrayList<Equipa> a
            ,int v, int r,int d, int i, int c, int remate, int p, int recuperacao) throws ExcecaoPos {
        super(id,nome,new Posicao(MEDIO),v,r,d,i,c,remate,p,a);
        this.recuperacao = recuperacao;
        super.sethab(calculahabilidade(v,r,d,i,c,remate,p,recuperacao));
    }

    public Medio(String id,String nome
            ,int v, int r,int d, int i, int c, int remate, int p, int recuperacao) throws ExcecaoPos {
        super(id,nome,new Posicao(MEDIO),v,r,d,i,c,remate,p);
        this.recuperacao = recuperacao;
        super.sethab(calculahabilidade(v,r,d,i,c,remate,p,recuperacao));
    }

    public Medio(Medio e) {
        super(e);
        this.recuperacao = e.getRecuperacao();

    }

    public void setRecuperacao(int recuperacao) {
        this.recuperacao = recuperacao;
    }

    public int getRecuperacao() {
        return recuperacao;
    }

    public int calculahabilidade(int v, int r,int d, int i, int c, int remate, int p,int recuperacao){
        return (int) (0.15 * v + 0.15 * r + 0.13 * d +  0.12 * i
                + 0.1 * c + 0.09 * remate + 0.21 * p + 0.1 * recuperacao);
    }

    public boolean equals(Medio e){
        return super.equals(e);
    }

    public Medio clone(){
        return new Medio(this);
    }

    public void guarda() throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter("C:\\Users\\Pestana\\Desktop\\POO\\Projeto\\src\\output.txt",true));
        escritor.write("\nMedio:");
        escritor.flush();
        super.guarda();
        escritor.flush();
        escritor.write("," + getRecuperacao());
        escritor.flush();
        escritor.close();
    }

    public static Medio parse(String input) throws ExcecaoPos {
        String[] campos = input.split(",");
        return new Medio(campos[1], campos[0],
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