import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

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
            "-2,10000,1350,1350",
            "500,-1000,1350,1350",
            "500,10000,-1350,1350",
            "500,10000,1350,-1350",
    })
    void construtorDeveDarErroQuandoLimiteAtingido(int aditivo, int gasolina, int alcool1, int alcool2) {
        assertThrows(IllegalArgumentException.class, () -> new CentroDistribuicao(aditivo, gasolina, alcool1, alcool2));
    }


    @ParameterizedTest
    @CsvSource({
            "0,500,500,500",
            "0,501,500,500",
            "50,470,450,500",
            "0,400,400,400",
            "100,300,300,400",
            "0,-10,-1,0",
            "0,0,0,0",
            "500,20,0,500",
    })
    void recebeAditivo(int quantidadeExistente, int valorEntrada, int esperado, int tanque) {
        CentroDistribuicao centroDistribuicao = new CentroDistribuicao(quantidadeExistente, 0, 0, 0);
        int resultado = centroDistribuicao.recebeAditivo(valorEntrada);
        assertEquals(esperado, resultado);
        assertEquals(tanque, centroDistribuicao.gettAditivo());
    }

    @ParameterizedTest
    @CsvSource({
            "0,10000,10000,10000",
            "0,10001,10000,10000",
            "7000,4700,3000,10000",
            "0,4700,4700,4700",
            "1000,3000,3000,4000",
            "0,-10,-1,0",
            "0,0,0,0",
            "10000,20,0,10000",
    })
    void recebeGasolina(int quantidadeExistente, int valorEntrada, int esperado, int tanque) {
        CentroDistribuicao centroDistribuicao = new CentroDistribuicao(0, quantidadeExistente, 0, 0);
        int resultado = centroDistribuicao.recebeGasolina(valorEntrada);
        assertEquals(esperado, resultado);
        assertEquals(tanque, centroDistribuicao.gettGasolina());
    }

    @ParameterizedTest
    @CsvSource({
            "0,2500,2500,2500",
            "0,2501,2500,2500",
            "2000,1000,500,2500",
            "0,2000,2000,2000",
            "1000,1000,1000,2000",
            "0,-10,-1,0",
            "0,0,0,0",
            "2500,20,0,2500",
    })
    void recebeAlcool(int quantidadeExistente, int valorEntrada, int esperado, int tanque) {
        CentroDistribuicao centroDistribuicao = new CentroDistribuicao(0, 0, quantidadeExistente / 2, quantidadeExistente / 2);
        int resultado = centroDistribuicao.recebeAlcool(valorEntrada);
        assertEquals(esperado, resultado);
        assertEquals(tanque / 2, centroDistribuicao.gettAlcool1());
        assertEquals(tanque / 2, centroDistribuicao.gettAlcool2());
    }

    @ParameterizedTest
    @ArgumentsSource(AditivoAbasteceSituacaoProvider.class)
    void recebeAditivoMudaSituacao(int valorInicial, int valorAbastecimento, SITUACAO esperada) {
        CentroDistribuicao centro = new CentroDistribuicao(valorInicial, 8000, 1200, 1200);
        centro.recebeAditivo(valorAbastecimento);
        assertEquals(centro.getSituacao(), esperada);
    }

    @ParameterizedTest
    @ArgumentsSource(GasolinaAbasteceSituacaoProvider.class)
    void recebeGasolinaMudaSituacao(int valorInicial, int valorAbastecimento, SITUACAO esperada) {
        CentroDistribuicao centro = new CentroDistribuicao(400, valorInicial, 1200, 1200);
        centro.recebeGasolina(valorAbastecimento);
        assertEquals(centro.getSituacao(), esperada);
    }

    @ParameterizedTest
    @ArgumentsSource(AlcoolAbasteceSituacaoProvider.class)
    void recebeAlcoolMudaSituacao(int valorInicial, int valorAbastecimento, SITUACAO esperada) {
        CentroDistribuicao centro = new CentroDistribuicao(400, 8000, valorInicial / 2, valorInicial / 2);
        centro.recebeAlcool(valorAbastecimento);
        assertEquals(centro.getSituacao(), esperada);
    }


    @ParameterizedTest
    @ArgumentsSource(EncomendaCombustivelProvider.class)
    void encomendaCombustivel(
            int aditivo,
            int gasolina,
            int alcool1,
            int alcool2,
            SITUACAO inicial,
            int quantidade,
            TIPOPOSTO tipoPosto,
            List<Integer> esperado,
            SITUACAO situacao
    ) {
        CentroDistribuicao centroDistribuicao = new CentroDistribuicao(aditivo, gasolina, alcool1, alcool2);
        assertEquals(inicial, centroDistribuicao.getSituacao());
        int[] resultado = centroDistribuicao.encomendaCombustivel(quantidade, tipoPosto);
        for (int i = 0; i < resultado.length; i++) {
            assertEquals(esperado.get(i), resultado[i]);
        }
        assertEquals(situacao, centroDistribuicao.getSituacao());
    }

}