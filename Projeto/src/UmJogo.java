import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

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
        this.casa = a;
        this.visita = b;
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
        this.casa = a;
    }

    public void setVisita(Equipa a) {
        this.visita = a;
    }

    public void setGoloC(int gc) {
        this.goloC = gc;
    }

    public void setGoloF(int gf) {
        this.goloF = gf;
    }

    public void simulajogo() throws ExcecaoPos, InterruptedException {

        if(this.casa.getEquipatitular().size() == 11 && this.visita.getEquipatitular().size() == 11) {

            for (int time=0; time<=90;time ++){

                System.out.println("\nO jogo esta a decorrer \nMinuto: "+ time);
                //Na primeira parte a bola vai começar na equipa da casa

                if (time<45) {

                    if (probmeio(this.casa.habmedio()) > probmeio(this.visita.habmedio())) {
                        System.out.println("Equipa da casa esta a fazer um ataque");
                        time += simulaataquecasa(time);
                    } else {
                        System.out.println("Equipa visitante esta a fazer um ataque");
                        time += simulaataquevista(time);
                    }
                }
                else {
                    if(time == 45) {
                        System.out.println("Inicio da segunda parte");
                    }
                    if (probmeio(this.visita.habmedio()) > probmeio(this.casa.habmedio())) {
                        System.out.println("Equipa visitante esta a fazer um ataque");
                        time += simulaataquevista(time);
                    } else {
                        System.out.println("Equipa da casa esta a fazer um ataque");
                        time += simulaataquecasa(time);
                    }
                }
                Thread.sleep(500);
            }

            System.out.println("Golos casa: " + this.goloC + "\nGolos visita: " + this.goloF);
        }
        else throw new ExcecaoPos("Numero de jogadores titulares invalido");
        }


    public int simulaataquecasa(int time){
        int minutos = 0;
        Random rand = new Random();
        boolean marca = false;

        if (time>45){
            time = time/2;
        }

        if (probmarcar(this.casa.habfrente()) > probdefender(this.visita.habdefesa())) {
            minutos += rand.nextInt(4);
            System.out.println("Os avançados da casa conseguem passar pelos defesas");
            if (probmarcar(this.casa.habfrente()) > probredes(this.visita.habredes())) {
                ArrayList<Jogador> jog = this.casa.getEquipatitular();
                double remate = 0;
                for (Jogador j : jog){
                    if(j.getposicaostr().equals(AVANCADO)){
                        remate = j.getremate();
                    }
                }
                if(rand.nextInt((int)remate) < remate-5){
                System.out.println("A equipa da casa marca golo");
                marca = true;
                this.goloC ++;
                minutos += rand.nextInt(6);}

                else {System.out.println("A bola foi ao poste infelizmente, bela jogada da equipa da casa");
                    minutos += rand.nextInt(6);
                }

            } else {System.out.println("Grande defesa");minutos += rand.nextInt(3); }
        } else  {
            minutos += rand.nextInt(3);
            System.out.println("Bem tirada pelos defesas da visita" );}

        if (time+minutos > 45){
            int aux = 0;
            aux = (time + minutos) - 46;
            minutos = aux;
        }

        if (marca){
            System.out.println("Bola ao meio para a equipa visitante");
            minutos += simulaataquevista(time+minutos);
        }
        return minutos;
    }


    public int simulaataquevista (int time){
        int minutos = 0;
        Random rand = new Random();
        boolean marca = false;

        if (time==90){
            time = time/2;
        }

        if (probmarcar(this.visita.habfrente()) > probdefender(this.casa.habdefesa())) {
            System.out.println("Os avançados da visitante conseguem passar pelos defesas");
            minutos += rand.nextInt(4);
            if (probmarcar(this.visita.habfrente()) > probredes(this.casa.habredes())) {
                ArrayList<Jogador> jog = this.visita.getEquipatitular();
                double remate = 0;
                for (Jogador j : jog){
                    if(j.getposicaostr().equals(AVANCADO)){
                        remate = j.getremate();
                    }
                }
                if(rand.nextInt((int)remate) < remate-5){

                System.out.println("A equipa visitante marca golo");
                marca = true;
                this.goloF ++;
                minutos += rand.nextInt(6);}

                else {System.out.println("A bola foi ao poste infelizmente, bela jogada da equipa visitante");
                minutos += rand.nextInt(6);
                }
            } else {System.out.println("Grande defesa");minutos += rand.nextInt(3); }
        } else  {
            minutos += rand.nextInt(3);
            System.out.println("Bem tirada pelos defesas da casa" );}

        if (time+minutos > 45){
            int aux = 0;
            aux = (time + minutos) - 46;
            minutos = aux;
        }

        if (marca){
            System.out.println("Bola ao meio para a equipa da casa");
            minutos += simulaataquecasa(time+minutos);
        }
        return minutos;
    }
}