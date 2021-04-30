import java.util.Objects;

//Classe "pai" da classe equipa e jogador, uma vez que estas duas vao ter um id e um nome
public class Geral {

    public static final String REDES = "guarda-redes";
    public static final String DEFESA = "defesa";
    public static final String MEDIO = "medio";
    public static final String AVANCADO= "avancado";
    public static final String LATERAL = "lateral";

    private String nome;
    private String id;

    public Geral(){
        this.nome = new String();
        this.id = new String();
    }
    public Geral(String nome, String id) {
        this.nome = nome;
        this.id = id;
    }

    public Geral (Geral a){
        this.nome= a.getNome();
        this.id = a.getId();
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) throws ExcecaoPos {
        this.nome = nome;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Geral geral = (Geral) o;
        return Objects.equals(nome, geral.nome) && Objects.equals(id, geral.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, id);
    }

    @Override
    public String toString() {
        return  nome;
    }

    public int compareTo(Object o){
        Geral u = (Geral) o;
        return this.id.compareTo(u.getId());
    }

}
