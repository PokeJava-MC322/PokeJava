package application.jogador;

public abstract class Personagem {
    private final String nome;
    private int nivel;
    private int experiencia;

    // CONSTRUTOR
    public Personagem(String nome, int nivel, int experiencia) {
        this.nome = nome;
        this.nivel = nivel;
        this.experiencia = experiencia;
    }

    // GETTERS
    public String getNome() {
        return nome;
    }

    public int getNivel() {
        return nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }

    // SETTERS
    public void setExperiencia(int novaExperiencia) {
        experiencia = novaExperiencia;
    }

    // OUTROS MÉTODOS
    public void aumentarExperiencia(int aumento) {
        experiencia += aumento;
    }

    public void subirDeNivel() {
        if (experiencia >= 1000) {
            nivel += 1;
            System.out.println("O jogador subiu para o nível " + nivel + ".");
            setExperiencia(0); // Reseta a experiência
        }
    }

    // IMPRESSÃO
    @Override
    public String toString() {
        return "Nome: " + nome +
               ", Nível: " + nivel +
               ", Experiência: " + experiencia + " / 1000";
    }
}
