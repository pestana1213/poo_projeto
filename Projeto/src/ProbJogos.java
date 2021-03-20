import java.util.Random;

public interface ProbJogos {
    Random rand = new Random();

    default double probmarcar(double frente){
        int r = 0;
        if(frente > 0){ if(frente > 80) {r = (rand.nextInt((int) frente-15));}}
        if(frente > 50 && frente <= 80) {r = (rand.nextInt((int) frente-20));}
        if(frente > 30 && frente <= 50) {r = (rand.nextInt((int) frente-25));}
        else{r = (rand.nextInt((int) frente-4));}
        return r;
    }


    default double probdefender(double defesa){
        int r = 0;
        if(defesa > 0){ r = (rand.nextInt((int) defesa));}
        return r;
    }

    default double probmeio(double medio){
        int r = 0;
        if(medio > 0){ r = (rand.nextInt((int) medio));}
        return r;
    }

    default double probredes(double redes){
        int r = 0;
        if(redes > 0){ r = (rand.nextInt((int) redes));}
        return r;
    }

    default double problateral(double lateral){
        int r = 0;
        if(lateral > 0){ r = (rand.nextInt((int) lateral));}
        return r;
    }
}
