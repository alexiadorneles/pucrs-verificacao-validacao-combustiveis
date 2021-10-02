import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class CentroDistribuicao {
    private final int aditivo;
    private final int gasolina;
    private final int alcool1;
    private final int acool2;
    private SITUACAO situacao;

    public enum TIPOPOSTO {COMUM, ESTRATEGICO}

    public static final int MAX_ADITIVO = 500;
    public static final int MAX_ALCOOL = 2500;
    public static final int MAX_GASOLINA = 10000;

    public CentroDistribuicao(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) {
        this.aditivo = tAditivo;
        this.gasolina = tGasolina;
        this.alcool1 = tAlcool1;
        this.acool2 = tAlcool2;

        if (this.valoresEstaoInvalidos(tAditivo, tGasolina, tAlcool1, tAlcool2)) {
            throw new InvalidParameterException();
        }

        this.defineSituacao();
    }

    public void defineSituacao() {
        this.situacao = SITUACAO.NORMAL;
    }

    public SITUACAO getSituacao() {
        return SITUACAO.NORMAL;
    }

    public int gettGasolina() {
        return this.gasolina;
    }

    public int gettAditivo() {
        return this.aditivo;
    }

    public int gettAlcool1() {
        return this.alcool1;
    }

    public int gettAlcool2() {
        return this.acool2;
    }

    public int recebeAditivo(int qtdade) {
        return 0;
    }

    public int recebeGasolina(int qtdade) {
        return 0;
    }

    public int recebeAlcool(int qtdade) {
        return 0;
    }

    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) {
        return new int[3];
    }

    private boolean valoresEstaoInvalidos(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) {
        boolean valorNegativo = Stream.of(tAditivo, tGasolina, tAlcool1, tAlcool2)
                .map(number -> number < 0)
                .reduce((bool, otherBool) -> bool || otherBool).orElse(false);
        Map<Integer, Integer> a = Map.of(tAditivo, MAX_ADITIVO, tGasolina, MAX_GASOLINA, tAlcool1, MAX_ALCOOL / 2, tAlcool2, MAX_ALCOOL / 2);
        Boolean valorMax = a.entrySet()
                .stream()
                .map(entry -> entry.getKey() > entry.getValue())
                .reduce((acc, value) -> acc || value)
                .orElse(false);
        return valorNegativo || valorMax;
    }
}

