import java.util.Map;

public class UmJogo extends Equipa implements ProbJogos {

    private Equipa casa;
    private Equipa visita;
    private int goloC;
    private int goloF;

    public UmJogo() {
        this.casa = new Equipa();
        this.visita = new Equipa();
        this.goloC = 0;
        this.goloF = 0;
    }

    public UmJogo(Equipa a, Equipa b, int gc, int gf) {
        this.casa = new Equipa(a);
        this.visita = new Equipa(b);
        this.goloC = gc;
        this.goloF = gf;
    }

    public UmJogo(UmJogo a) {
        this.casa = a.getcasa();
        this.visita = a.getVisita();
        this.goloC = a.getGoloC();
        this.goloF = a.getGoloF();
    }


    public Equipa getcasa() {
        return new Equipa(this.casa);
    }

    public Equipa getVisita() {
        return new Equipa(this.visita);
    }

    public int getGoloC() {
        return this.goloC;
    }

    public int getGoloF() {
        return this.goloF;
    }

    public void setCasa(Equipa a) {
        this.casa = new Equipa(a);
    }

    public void setVisita(Equipa a) {
        this.visita = new Equipa(a);
    }

    public void setGoloC(int gc) {
        this.goloC = gc;
    }

    public void setGoloF(int gf) {
        this.goloF = gf;
    }

    public int simulaataquecasa() throws ExcecaoPos{
        int a = 0;
        if (ataquecasa() == 1) {goloC += 1; a =1;
        } else {
            if (problateralcasa() > problateralvisita()) {
                if (ataquecasa() == 1) goloC += 1; a=1;
            }
        }
        return a;
    }
    public int simulaataquevisita() throws ExcecaoPos{
        int a = 0;
        if (ataquevisita() == 1) {goloF += 1; a = 1;
        } else {
            if (problateralvisita() > problateralcasa()) {
                if (ataquevisita() == 1) goloF += 1;a=1;
            }
        }
        return a;
    }

    public void simulasegundaparte() throws ExcecaoPos, InterruptedException{
        if (probmediovisita() > probmediocasa()) { // primeiro ataque da equipa da casa, que começa com a bola
            Thread.sleep(500);
            System.out.println("Equipa da casa esta a fazer um ataque");
            if (simulaataquevisita() == 1) {
                simulaprimeiraparte();
            }
        }
    }

    public void simulaprimeiraparte() throws ExcecaoPos, InterruptedException{
        if (probmediocasa() > probmediovisita()) { // primeiro ataque da equipa da casa, que começa com a bola
            Thread.sleep(500);
            System.out.println("Equipa visitante esta a fazer um ataque");
            if (simulaataquecasa() == 1) {
                simulasegundaparte();
            }
        }
    }



    public void simulajogo() throws ExcecaoPos, InterruptedException {
        int time = 90;

        if(this.casa.getEquipatitular().size() == 11 && this.visita.getEquipatitular().size() == 11) {
            for (int i = 0; i <= time; i++) {
                System.out.println("\nO jogo esta a decorrer \nMinuto: "+i);
                simulaprimeiraparte();
                    Thread.sleep(500);
                    i+=3;
                if(i==45){
                    System.out.println("\nSegunda parte:");
                    simulasegundaparte();
                    Thread.sleep(500);
                }
            }
            System.out.println("Golos casa: " + this.goloC + "\nGolos visita: " + this.goloF);
        } else throw new ExcecaoPos("Numero de jogadores titulares invalido");
    }

    public int ataquecasa() {
        int marca = 0;
        if (probmarcar(this.casa.habfrente()) > probdefender(this.visita.habdefesa())) {
            if (probmarcar(this.casa.habfrente()) > probredes(this.visita.habredes())) {
                System.out.println("A equipa da casa marca golo");
                marca = 1;
            } else {System.out.println("Grande defesa");}
        } else {
            System.out.println("Bem tirada pelos defesas da visita" );}
        return marca;
    }

    private int ataquevisita() {
        int marca = 0;
        if (probataquevisita() > probdefesacasa()) {
            if (probataquevisita() > probredescasa()) {
                System.out.println("A equipa da casa marca golo");
                marca = 1;
            } else {System.out.println("Grande defesa");}
        } else {
            System.out.println("Bem tirada pelos defesas da casa");}
        return marca;
    }

    public double problateralvisita(){
        return(problateral(this.visita.hablateral()));
    }

    public double problateralcasa(){
        return(problateral(this.casa.hablateral()));
    }

    public double probdefesasvisita(){
        return(probdefender(this.visita.habdefesa()));
    }

    public double probdefesacasa(){
        return(probdefender(this.casa.habdefesa()));
    }

    public double probmediovisita(){
        return(probmeio(this.visita.habmedio()));
    }

    public double probmediocasa(){
        return(probmeio(this.casa.habmedio()));
    }

    public double probataquevisita(){
        return(probmarcar(this.visita.habfrente()));
    }

    public double probataquecasa(){
        return(probmarcar(this.casa.habfrente()));
    }

    public double probredesvisita(){
        return(probredes(this.visita.habredes()));
    }

    public double probredescasa(){
        return(probredes(this.casa.habredes()));
    }
}