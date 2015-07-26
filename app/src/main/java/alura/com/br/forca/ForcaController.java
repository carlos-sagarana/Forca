package alura.com.br.forca;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by carloseduardo on 25/07/15.
 */
public class ForcaController {

    private String palavraParaAdivinhar;
    private Set<Character> letrasUsadas;

    private int quantidadeErros = -1;

    public ForcaController(String palavra) {
        palavraParaAdivinhar = palavra;
        letrasUsadas = new HashSet<>();
    }

    public int getQuantidadeErros() {
        return quantidadeErros;
    }

    public void setQuantidadeErros(int quantidadeErros) {
        this.quantidadeErros = quantidadeErros;
    }

    public void joga(Character letra) {
        if (letrasUsadas.contains(letra)) {
            return;
        }

        letrasUsadas.add(letra);

        if (palavraParaAdivinhar.contains(letra.toString())) {
            return;
        }

        quantidadeErros++;
    }

    public String getPalavraAteAgora() {
        StringBuilder visualizacao = new StringBuilder();

        for (char c : palavraParaAdivinhar.toCharArray()) {
            if(letrasUsadas.contains(c)) {
                visualizacao.append(c);
            } else {
                visualizacao.append(" ");
            }
        }

        return visualizacao.toString();
    }

    public boolean isMorreu() {
        return getQuantidadeErros() == 5;
    }

    public boolean isGanhou() {
        return !getPalavraAteAgora().contains(" ");
    }

    public boolean isTerminou() {
        return isMorreu() || isGanhou();
    }
}
