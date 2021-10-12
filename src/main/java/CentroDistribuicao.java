import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class CentroDistribuicao {
    private int aditivo;
    private int gasolina;
    private int alcool1;
    private int alcool2;
    private SITUACAO situacao;

    public static final int MAX_ADITIVO = 500;
    public static final int MAX_ALCOOL = 2500;
    public static final int MAX_GASOLINA = 10000;
    public static final int PORCENTAGEM_SOBRAVISO = 50;
    public static final int PORCENTAGEM_EMERGENCIA = 25;
    public static final double PORCENTAGEM_ALCOOL = 0.25;
    public static final double PORCENTAGEM_GASOLINA = 0.7;
    public static final double PORCENTAGEM_ADITIVO = 0.05;

    public CentroDistribuicao(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) {
        this.aditivo = tAditivo;
        this.gasolina = tGasolina;
        this.alcool1 = tAlcool1;
        this.alcool2 = tAlcool2;

        if (this.valoresEstaoInvalidos()) {
            throw new InvalidParameterException();
        }

        this.defineSituacao();
    }

    public void defineSituacao() {
        boolean situacaoVirouEmergencia = false;
        boolean situacaoVirouSobraviso = false;
        for (Entry<Integer, Integer> entry : this.getParesDeValorEMaximo()) {
            int porcentagem = this.calculaPorcentagem(entry.getValue(), entry.getKey());
            if (porcentagem < PORCENTAGEM_EMERGENCIA) {
                this.situacao = SITUACAO.EMERGENCIA;
                situacaoVirouEmergencia = true;
                return;
            }

            if (!situacaoVirouEmergencia && porcentagem < PORCENTAGEM_SOBRAVISO) {
                this.situacao = SITUACAO.SOBRAVISO;
                situacaoVirouSobraviso = true;
            }
        }

        this.situacao = situacaoVirouSobraviso || situacaoVirouEmergencia ? this.situacao : SITUACAO.NORMAL;
    }

    public SITUACAO getSituacao() {
        return this.situacao;
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
        return this.alcool2;
    }

    public int recebeAditivo(int qtdade) {
        if (qtdade < 0) return -1;
        if (this.aditivo + qtdade > MAX_ADITIVO) {
            int result = qtdade + (MAX_ADITIVO - (this.aditivo + qtdade));
            this.setAditivo(MAX_ADITIVO);
            return result;
        }
        this.setAditivo(this.aditivo + qtdade);
        return qtdade;
    }

    public int recebeGasolina(int qtdade) {
        if (qtdade < 0) return -1;
        if (this.gasolina + qtdade > MAX_GASOLINA) {
            int result = qtdade + (MAX_GASOLINA - (this.gasolina + qtdade));
            this.setGasolina(MAX_GASOLINA);
            return result;
        }
        this.setGasolina(this.gasolina + qtdade);
        return qtdade;
    }

    public int recebeAlcool(int qtdade) {
        if (qtdade < 0) return -1;
        if (this.alcool1 + this.alcool2 + qtdade > MAX_ALCOOL) {
            int result = qtdade + (MAX_ALCOOL - (this.alcool1 + this.alcool2 + qtdade));
            this.setAlcool(MAX_ALCOOL);
            return result;
        }
        this.somaAlcool(qtdade);
        return qtdade;
    }

    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) {
        if (qtdade < 1) {
            return new int[]{-7};
        }
        int quantidadeGasolina = (int) (qtdade * PORCENTAGEM_GASOLINA);
        int quantidadeAlcool = (int) (qtdade * PORCENTAGEM_ALCOOL);
        int quantidadeAditivo = (int) (qtdade * PORCENTAGEM_ADITIVO);
        if (!possuiComponentes(quantidadeAditivo, quantidadeGasolina, quantidadeAlcool, tipoPosto))
            return new int[]{-21};

        if (this.getSituacao().equals(SITUACAO.NORMAL) || tipoPosto == TIPOPOSTO.ESTRATEGICO) {
            this.reduzirDoTanque(quantidadeGasolina, quantidadeAlcool, quantidadeAditivo);
        } else if (this.getSituacao().equals(SITUACAO.SOBRAVISO)) {
            this.reduzirDoTanque(quantidadeGasolina / 2, quantidadeAlcool / 2, quantidadeAditivo / 2);
        } else if (this.getSituacao().equals(SITUACAO.EMERGENCIA)) {
            return new int[]{-14};
        }

        return new int[]{this.aditivo, this.gasolina, this.alcool1, this.alcool2};
    }

    private void reduzirDoTanque(int quantidadeGasolina, int quantidadeAlcool, int quantidadeAditivo) {
        this.setAlcool(this.alcool1 + this.alcool2 - quantidadeAlcool);
        this.setGasolina(this.gasolina - quantidadeGasolina);
        this.setAditivo(this.aditivo - quantidadeAditivo);
    }

    private boolean valoresEstaoInvalidos() {
        boolean valorNegativo = Stream.of(this.aditivo, this.gasolina, this.alcool1, this.alcool2)
                .map(number -> number < 0)
                .reduce((bool, otherBool) -> bool || otherBool).orElse(false);
        Boolean valorMax = this.getParesDeValorEMaximo()
                .stream()
                .map(entry -> entry.getKey() > entry.getValue())
                .reduce((acc, value) -> acc || value)
                .orElse(false);
        return valorNegativo || valorMax;
    }

    private int calculaPorcentagem(int total, int valor) {
        return (valor * 100) / total;
    }

    private List<Entry<Integer, Integer>> getParesDeValorEMaximo() {
        return List.of(
                new SimpleEntry(this.aditivo, MAX_ADITIVO),
                new SimpleEntry(this.gasolina, MAX_GASOLINA),
                new SimpleEntry(this.alcool1, MAX_ALCOOL / 2),
                new SimpleEntry(this.alcool2, MAX_ALCOOL / 2)
        );
    }

    private boolean possuiComponentes(int aditivo, int gasolina, int alcool, TIPOPOSTO tipoPosto) {
        boolean temGasolinaEAlcool = gasolina <= this.gasolina && alcool <= (this.alcool1 + this.alcool2);
        boolean temAditivo = aditivo <= this.aditivo;
        if (!temGasolinaEAlcool) return false;
        if (temAditivo) return true;
        return this.getSituacao() == SITUACAO.EMERGENCIA && tipoPosto == TIPOPOSTO.ESTRATEGICO;
    }

    private void setAditivo(int value) {
        this.aditivo = Math.max(value, 0);
        this.defineSituacao();
    }

    private void setGasolina(int value) {
        this.gasolina = Math.max(value, 0);
        this.defineSituacao();
    }

    private void setAlcool(int value) {
        this.alcool1 = Math.max(value, 0) / 2;
        this.alcool2 = Math.max(value, 0) / 2;
        this.defineSituacao();
    }

    private void somaAlcool(int value) {
        this.alcool1 += value / 2;
        this.alcool2 += value / 2;
        this.defineSituacao();
    }
}

