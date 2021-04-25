
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Equipa extends Geral{

    private ArrayList<Jogador> jogadores;
    private ArrayList<Jogador> equipatitular;

    public Equipa() {
        super();
        this.jogadores = new ArrayList<>();
        this.equipatitular = new ArrayList<>();
        hist();
    }

    public Equipa(String nome, String id, ArrayList<Jogador> a, ArrayList<Jogador> b) {
        super(nome, id);
        this.jogadores = new ArrayList<>(a);
        this.equipatitular = new ArrayList<>(b);
        hist();
    }

    public Equipa(Equipa b) {
        super(b);
        this.jogadores = b.getJogadores();
        this.equipatitular = b.getEquipatitular();
        hist();
    }

    public String getId() {
        return super.getId();
    }

    public String getNome() {
        return super.getNome();
    }

    public ArrayList<Jogador> getEquipatitular() {
        return this.equipatitular.stream().map(Jogador::clone).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Jogador> getJogadores() {
        return this.jogadores.stream().map(Jogador::clone).collect(Collectors.toCollection(ArrayList::new));
    }

    public void setJogadores(ArrayList<Jogador> a) {
        this.jogadores = new ArrayList<>(a);
        hist();
    }

    public void setId(String id) {
        super.setId(id);
    }

    public void setEquipatitular(ArrayList<Jogador> titular) throws ExcecaoPos {
        if (titular.size() <= 11) {
            for (Jogador f : titular) {
                if (this.jogadores.contains(f)) {
                    this.equipatitular.add(f.clone());
                } else {
                    throw new ExcecaoPos("Jogador nao pertence a equipa");
                }
            }

            hist();
        } else {
            throw new ExcecaoPos("Equipa titular com demasiados elementos");
        }
    }

    public void setNome(String nome) throws ExcecaoPos {
        super.setNome(nome);
        hist();

    }

    public void addJogador(Jogador a) throws ExcecaoPos {

        if (this.jogadores.stream().anyMatch(j -> j.equals(a))) {
            throw new ExcecaoPos("Jogador ja na equipa");
        }
        a.addhist(this);
        this.jogadores.add(a.clone());
    }

    public void removeJogador(Jogador a) throws ExcecaoPos {
        this.jogadores.remove(a);
        this.equipatitular.remove(a);
    }

    public void removeJogadorTitular(Jogador a) {
        this.equipatitular.remove(a);
    }

    public int habfrente() {

        return (int) (this.jogadores.stream().filter(a -> a.getposicaostr().equals(AVANCADO)).mapToDouble(Jogador::getHabilidade).sum() /
                this.jogadores.stream().filter(a -> a.getposicaostr().equals(AVANCADO)).count());
    }

    public void addjogequipatitular(Jogador a) throws ExcecaoPos {
        for (Jogador e : this.equipatitular) {
            if (e.equals(a)) {
                throw new ExcecaoPos("Jogador ja esta na equipa titular");
            }
        }
        this.equipatitular.add(a.clone());
    }

    public void substitui(Jogador entra, Jogador sai) throws ExcecaoPos {
        int i = 0;
        for (Jogador y : this.equipatitular) {
            for (Jogador x : this.jogadores) {
                if (y.equals(sai) && x.equals(entra)) {
                    addjogequipatitular(entra.clone());
                    this.equipatitular.remove(sai);
                    i = 1;
                }
            }
        }
        if (i == 0) {
            throw new ExcecaoPos("Substituicao nao efetuada");
        }
    }

    public int hablateral() {

        return (int) (this.jogadores.stream().filter(a -> a.getposicaostr().equals(LATERAL)).mapToDouble(Jogador::getHabilidade).sum() /
                this.jogadores.stream().filter(a -> a.getposicaostr().equals(LATERAL)).count());
    }

    public int habmedio() {
        return (int) (this.jogadores.stream().filter(a -> a.getposicaostr().equals(MEDIO)).mapToDouble(Jogador::getHabilidade).sum() /
                this.jogadores.stream().filter(a -> a.getposicaostr().equals(MEDIO)).count());
    }

    public int habdefesa() {
        return (int) (this.jogadores.stream().filter(a -> a.getposicaostr().equals(DEFESA)).mapToDouble(Jogador::getHabilidade).sum() /
                this.jogadores.stream().filter(a -> a.getposicaostr().equals(DEFESA)).count());
    }

    public int habredes() {
        return (int) (this.jogadores.stream().filter(a -> a.getposicaostr().equals(REDES)).mapToDouble(Jogador::getHabilidade).sum() /
                this.jogadores.stream().filter(a -> a.getposicaostr().equals(REDES)).count());
    }

    public int habgeral() {
        if (this.jogadores.size() > 0)
            return (habdefesa() + habfrente() + hablateral() + habmedio() + habredes()) / 5;
        else return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Equipa equipa = (Equipa) o;
        return jogadores.equals(equipa.jogadores) && equipatitular.equals(equipa.equipatitular);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), jogadores, equipatitular);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("-----------Equipa-----------\n\n");
        sb.append("Nome da equipa: " + super.getNome());
        sb.append("\nId: " + super.getId());
        sb.append("\nHabilidade geral: " + habgeral());
        sb.append("\n-----------Jogadores-----------\n\n");
        for (Jogador e : this.jogadores) {
            sb.append(e.toString());
        }
        sb.append("\n-----------Jogadores titulares-----------\n\n");
        if (this.equipatitular.size() > 0) {
            sb.append("\nEquipa Titular: ");
            for (Jogador x : this.equipatitular) {
                sb.append(x.toString());
            }
        }
        sb.append("\n-------------------------\n\n");
        return sb.toString();
    }

    public Equipa clone() {
        return new Equipa(this);
    }

    private void hist() {
        for (Jogador e : this.jogadores) {
            for (Jogador k : this.equipatitular) {
                if (!k.ultimo().equals(this)) {
                    k.addhist(this);
                }
            }
            if (!e.ultimo().equals(this)) {
                e.addhist(this);
            }
        }
    }

    public boolean temjogador(String nome) {
        return getJogadores().stream().anyMatch(a -> a.getNome().equalsIgnoreCase(nome));
    }

    public Jogador identificaJogador(String nome) {
        return this.jogadores.stream().filter(j -> j.getNome().equalsIgnoreCase(nome)).findFirst().map(Jogador::clone).orElse(new Jogador());
    }

    public ArrayList<Jogador> jogadoresnome(String nome) {
        return this.jogadores.stream().filter(j -> j.getNome().equalsIgnoreCase(nome)).collect(Collectors.toCollection(ArrayList::new));
    }

    public void update(Equipa a) {
        this.equipatitular = a.getEquipatitular();
        this.jogadores = a.getJogadores();
    }

    public boolean taticavalida(int nrdefesas, int nrmedios, int nravancados) {
        boolean res = false;
        if (nrdefesas + nrmedios + nravancados == 10) {
            if (nrdefesas == 4 && nrmedios == 4 && nravancados == 2) res = true;
            if (nrdefesas == 4 && nrmedios == 3 && nravancados == 3) res = true;
            if (nrdefesas == 3 && nrmedios == 5 && nravancados == 2) res = true;
        }
        return res;
    }

    public List<Jogador> ordenajogpos(Posicao pos) {
        Comparator<Jogador> comp = (e1, e2) -> (int) e2.getHabilidade() - e1.getHabilidade();
        return this.jogadores.stream().filter(e -> e.getPosicao().equals(pos)).sorted(comp).collect(Collectors.toList());
    }

    public void setequipatittat(int nrdefesas, int nrmedios, int nravancados) throws ExcecaoPos {
        ArrayList<Jogador> eqtitular = new ArrayList<>();
        List<Jogador> defesas = ordenajogpos(new Posicao(DEFESA));
        List<Jogador> laterais = ordenajogpos(new Posicao(LATERAL));
        List<Jogador> medios = ordenajogpos(new Posicao(MEDIO));
        List<Jogador> avancados = ordenajogpos(new Posicao(AVANCADO));
        List<Jogador> guardaredes = ordenajogpos(new Posicao(REDES));
        int defs = (int) this.jogadores.stream().filter(e -> e.getposicaostr().equals(DEFESA)).count();
        int lats = (int) this.jogadores.stream().filter(e -> e.getposicaostr().equals(LATERAL)).count();
        int meds = (int) this.jogadores.stream().filter(e -> e.getposicaostr().equals(MEDIO)).count();
        int avn = (int) this.jogadores.stream().filter(e -> e.getposicaostr().equals(AVANCADO)).count();

        if (taticavalida(nrdefesas, nrmedios, nravancados)) {
            if (defs <= nrdefesas-2 && lats <=2) {
                for (int n = 0; n < 2; n++) {
                    eqtitular.add(laterais.get(n));
                }
                for (int n = 0; n < nrdefesas - 2; n++) {
                    eqtitular.add(defesas.get(n));
                }
            }
            else{
                if(defs == 0){
                    if(lats<=nrdefesas){
                        for (int n = 0; n < nrdefesas; n++) {
                            eqtitular.add(laterais.get(n));
                            //tirar alguma habilidade por terem mudado de posicao menos em dois porque esses vao jogar na posicao que é suposto
                        }
                        }
                    else{
                        if(meds<=nrdefesas){

                        }
                    }
                }
                int defesasquefaltam = defs - nrdefesas;


            }
            if (meds <= nrmedios) {
                for (int n = 0; n < nrmedios; n++) {
                    eqtitular.add(medios.get(n));
                }
            }
            if (avn <= nravancados) {
                for (int n = 0; n < nravancados; n++) {
                    eqtitular.add(avancados.get(n));
                }
            }
            eqtitular.add(guardaredes.get(0));
            setEquipatitular(eqtitular);
        }
    }
}
