import java.util.Map;
import java.util.Random;

//Interface utilizada na simulaçao de jogos! Usada para calcular as probabilidades usadas na simulaçao de um jogo
public interface ProbJogos {
    Random rand = new Random();

    default double probmarcar(double frente){
        int r = 0;
        if(frente > 0){ if(frente > 80) {r = (rand.nextInt((int) frente-25));}
        if(frente > 50 && frente <= 80) {r = (rand.nextInt((int) frente-20));}
        if(frente > 30 && frente <= 50) {r = (rand.nextInt((int) frente-15));}}
        else{r = (rand.nextInt((int) frente-4));}
        return r;
    }

    default double probdefender(double defesa){
        int r = 0;
        if(defesa > 0){
            if (defesa>80){ r = (rand.nextInt((int) defesa + 5));}
            if (defesa>50 && defesa<=80){ r = (rand.nextInt((int) defesa + 7));}
            if (defesa>30 && defesa <=50){ r = (rand.nextInt((int) defesa + 8));}
            else{r = (rand.nextInt((int) defesa+9));}
        }
        return r;
    }

    default double probmeio(double medio){
        int r = 0;
        if(medio > 0){
            if (medio>80){ r = (rand.nextInt((int) medio + 5));}
            if (medio>50 && medio<=80){ r = (rand.nextInt((int) medio + 7));}
            if (medio>30 && medio <=50){ r = (rand.nextInt((int) medio + 8));}
            else{r = (rand.nextInt((int) medio+9));}
        }
        return r;
    }

    default double probredes(double redes){
        int r = 0;
        if(redes > 0){
            if (redes>80){ r = (rand.nextInt((int) redes + 5));}
            if (redes>50 && redes<=80){ r = (rand.nextInt((int) redes + 7));}
            if (redes>30 && redes <=50){ r = (rand.nextInt((int) redes + 8));}
            else{r = (rand.nextInt((int) redes+9));}}
        return r;
    }

    default double problateral(double lateral){
        int r = 0;
        if(lateral > 0){
            if (lateral>80){ r = (rand.nextInt((int) lateral + 5));}
            if (lateral>50 && lateral<=80){ r = (rand.nextInt((int) lateral + 7));}
            if (lateral>30 && lateral <=50){ r = (rand.nextInt((int) lateral + 8));}
            else{r = (rand.nextInt((int) lateral+9));}}
        return r;
    }

}
