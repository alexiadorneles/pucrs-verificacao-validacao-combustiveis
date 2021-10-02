import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CentroDistribuicaoTest {

    @ParameterizedTest
    @ArgumentsSource(ConstructorArgumentsProvider.class)
    void construtorDeveAtribuirSituacao(int aditivo, int gasolina, int alcool1, int alcool2, SITUACAO situacao) {
        CentroDistribuicao centroDistribuicao = new CentroDistribuicao(aditivo, gasolina, alcool1, alcool2);
        assertEquals(situacao, centroDistribuicao.getSituacao());
    }

    @ParameterizedTest
    @CsvSource({
            "501,10000,1250,1250",
            "700,10000,1250,1250",
            "500,10001,1250,1250",
            "500,11231,1250,1250",
            "500,10000,1251,1250",
            "500,10000,1350,1250",
            "500,10000,1350,1251",
            "500,10000,1350,1350",
    })
    void construtorDeveDarErroQuandoLimiteAtingido(int aditivo, int gasolina, int alcool1, int alcool2) {
        assertThrows(IllegalArgumentException.class, () -> new CentroDistribuicao(aditivo, gasolina, alcool1, alcool2));
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