import java.lang.reflect.Array;
import java.util.ArrayList;

public class Teste {
    public static void main(String[] args) throws ExcecaoPos, InterruptedException {
        Posicao pos = new Posicao("defesa");

        Jogador a = new Jogador("2", "Joao", pos, 50);
        Jogador b = new Jogador("4", "Eduardo", "medio", 50);
        Jogador c = new Jogador("3", "Barbara", pos, 50);
        Jogador d = new Jogador("1", "Ivone", "medio", 50);
        Jogador e = new Jogador("5", "Caldas", "lateral", 50);
        Jogador f = new Jogador("6", "Rodrigues", "lateral", 50);
        Jogador g = new Jogador("7", "Nogueira", "guarda-redes", 50);
        Jogador h = new Jogador("8", "Pereira", "lateral", 50);
        Jogador i = new Jogador("9", "mao", "avancado", 50);
        Jogador j = new Jogador("10", "braco", "avancado", 80);
        Jogador k = new Jogador("11", "perna", "defesa", 54);

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

        Equipa equipa = new Equipa();
        equipa.setJogadores(eqq);

        equipa.setEquipatitular(eqq);
        Jogador aa = new Jogador("12","Joao", pos, 50);
        Jogador bb = new Jogador ("13", "Eduardo", "medio", 50);
        Jogador cc = new Jogador ("14", "Barbara", pos, 50);
        Jogador dd = new Jogador ("15", "Ivone", "medio", 50);
        Jogador ee = new Jogador ("16", "Caldas", "lateral", 50);
        Jogador ff = new Jogador ("17", "Rodrigues", "lateral", 50);
        Jogador gg = new Jogador ("18","Nogueira", "guarda-redes", 50);
        Jogador hh = new Jogador ("19", "Pereira","lateral",50);
        Jogador ii = new Jogador ("20", "mao","avancado", 50);
        Jogador jj = new Jogador ("21", "braco", "avancado", 50);
        Jogador kk = new Jogador ("22", "perna", "defesa", 50);

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

        Equipa equipavis = new Equipa();
        equipavis.setJogadores(eq);
        equipavis.setEquipatitular(eq);
        UmJogo jogo = new UmJogo(equipa,equipavis,0,0);
        jogo.simulajogo();
    }
}
