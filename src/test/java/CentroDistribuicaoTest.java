import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CentroDistribuicaoTest {

    @ParameterizedTest
    @ArgumentsSource(ConstructorArgumentsProvider.class)
    void construtorDeveAtribuirSituacao(int aditivo, int gasolina, int alcool1, int alcool2, SITUACAO situacao) {
        CentroDistribuicao centroDistribuicao = new CentroDistribuicao(aditivo, gasolina, alcool1, alcool2);
        assertEquals(situacao, centroDistribuicao.getSituacao());
    }

    @org.junit.jupiter.api.Test
    void defineSituacao() {
    }

    @org.junit.jupiter.api.Test
    void getSituacao() {
    }

    @org.junit.jupiter.api.Test
    void gettGasolina() {
    }

    @org.junit.jupiter.api.Test
    void gettAditivo() {
    }

    @org.junit.jupiter.api.Test
    void gettAlcool1() {
    }

    @org.junit.jupiter.api.Test
    void gettAlcool2() {
    }

    @org.junit.jupiter.api.Test
    void recebeAditivo() {
    }

    @org.junit.jupiter.api.Test
    void recebeGasolina() {
    }

    @org.junit.jupiter.api.Test
    void recebeAlcool() {
    }

    @org.junit.jupiter.api.Test
    void encomendaCombustivel() {
    }
}