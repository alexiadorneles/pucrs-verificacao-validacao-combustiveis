import java.util.Arrays;

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
}

