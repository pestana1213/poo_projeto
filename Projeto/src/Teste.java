import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.security.Key;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Teste {
    public static void main(String[] args) throws ExcecaoPos, InterruptedException {
        Posicao pos = new Posicao("defesa");

        Jogador a = new Jogador("2", "Joao", "medio", 100);
        Jogador b = new Jogador("4", "Eduardo", "medio", 100);
        Jogador c = new Jogador("3", "Barbara", "defesa", 100);
        Jogador d = new Jogador("1", "Ivone", "medio", 100);
        Jogador e = new Jogador("5", "Caldas", "lateral", 100);
        Jogador f = new Jogador("6", "Rodrigues", "lateral", 100);
        Jogador g = new Jogador("7", "Nogueira", "guarda-redes", 100);
        Jogador h = new Jogador("8", "Pereira", "defesa", 100);
        Jogador i = new Jogador("9", "mao", "avancado", 100);
        Jogador j = new Jogador("10", "braco", "avancado", 100);
        Jogador k = new Jogador("11", "perna", "medio", 0);
        Jogador iij = new Jogador("25", "tuxa", "defesa", 20);




        ArrayList<Jogador> eqq = new ArrayList<>();
        eqq.add(a);
        eqq.add(b);
        eqq.add(c);
        eqq.add(d);
        eqq.add(e);
        eqq.add(f);
        eqq.add(g);
        eqq.add(h);
        eqq.add(i);
        eqq.add(j);
        eqq.add(k);
        eqq.add(iij);
        
        Jogador aa = new Jogador("12","Joao", "lateral", 100);
        Jogador bb = new Jogador ("13", "Eduardo", "lateral", 100);
        Jogador cc = new Jogador ("14", "Barbara", "defesa", 100);
        Jogador dd = new Jogador ("15", "Ivone", "medio", 100);
        Jogador ee = new Jogador ("16", "Caldas", "defesa", 100);
        Jogador ff = new Jogador ("17", "Rodrigues", "medio", 100);
        Jogador gg = new Jogador ("18","Nogueira", "guarda-redes", 100);
        Jogador hh = new Jogador ("19", "Pereira","medio",100);
        Jogador ii = new Jogador ("20", "mao","medio", 100);
        Jogador jj = new Jogador ("21", "braco", "avancado", 100);
        Jogador kk = new Jogador ("22", "perna", "avancado", 100);
        Jogador ij = new Jogador("24", "tuxa", "defesa", 20);
        ArrayList<Jogador> eq = new ArrayList<>();
        eq.add(aa);
        eq.add(bb);
        eq.add(cc);
        eq.add(dd);
        eq.add(ee);
        eq.add(ff);
        eq.add(gg);
        eq.add(hh);
        eq.add(ii);
        eq.add(jj);
        eq.add(kk);


        Equipa equipa = new Equipa();
        equipa.setJogadores(eqq);
        equipa.setId("2");
        equipa.setNome("ola");


        Equipa equipavis = new Equipa();
        equipavis.setJogadores(eq);
        equipavis.setId("3");
        equipavis.setNome("k");
        equipavis.setEquipatitular(eq);

        Map<String,Equipa> equipas = new LinkedHashMap<>();
        equipas.put(equipavis.getId(),equipavis);
        equipas.put(equipa.getId(),equipa);


        Faztudo skrt = new Faztudo(equipas);
        equipa.setequipatittat(4,4,2);
        System.out.println(equipa);
        Faztudo wq = new Faztudo();
        wq.addEquipa(equipa);
        wq.addEquipa(equipavis);


    }
}
