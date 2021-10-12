import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.List;
import java.util.stream.Stream;

public class EncomendaCombustivelProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(500, 10000, 1250, 1250, -10, TIPOPOSTO.COMUM, List.of(-7), SITUACAO.NORMAL),
                Arguments.of(500, 10000, 1250, 1250, -10, TIPOPOSTO.ESTRATEGICO, List.of(-7), SITUACAO.NORMAL),
                Arguments.of(500, 10000, 1250, 1250, 100, TIPOPOSTO.COMUM, List.of(495, 9930, 1237, 1237), SITUACAO.NORMAL),
                Arguments.of(500, 10000, 1250, 1250, 100, TIPOPOSTO.ESTRATEGICO, List.of(495, 9930, 1237, 1237), SITUACAO.NORMAL),
                Arguments.of(254, 10000, 1250, 1250, 100, TIPOPOSTO.COMUM, List.of(249, 9930, 1237, 1237), SITUACAO.SOBRAVISO),
                Arguments.of(254, 10000, 1250, 1250, 100, TIPOPOSTO.ESTRATEGICO, List.of(249, 9930, 1237, 1237), SITUACAO.SOBRAVISO),
                Arguments.of(400, 7000, 1000, 1000, 7142, TIPOPOSTO.COMUM, List.of(43, 2001, 107, 107), SITUACAO.EMERGENCIA),
                Arguments.of(400, 7000, 1000, 1000, 7142, TIPOPOSTO.ESTRATEGICO, List.of(43, 2001, 107, 107), SITUACAO.EMERGENCIA),
                Arguments.of(249, 10000, 1250, 1250, 200, TIPOPOSTO.COMUM, List.of(244, 9930, 1237, 1237), SITUACAO.SOBRAVISO),
                Arguments.of(249, 10000, 1250, 1250, 100, TIPOPOSTO.ESTRATEGICO, List.of(244, 9930, 1237, 1237), SITUACAO.SOBRAVISO),
                Arguments.of(129, 10000, 1250, 1250, 200, TIPOPOSTO.COMUM, List.of(124, 9930, 1237, 1237), SITUACAO.EMERGENCIA),
                Arguments.of(129, 10000, 1250, 1250, 100, TIPOPOSTO.ESTRATEGICO, List.of(124, 9930, 1237, 1237), SITUACAO.EMERGENCIA),
                Arguments.of(120, 10000, 1250, 1250, 100, TIPOPOSTO.COMUM, List.of(-14), SITUACAO.EMERGENCIA),
                Arguments.of(120, 10000, 1250, 1250, 100, TIPOPOSTO.ESTRATEGICO, List.of(115, 9930, 1237, 1237), SITUACAO.EMERGENCIA),
                Arguments.of(0, 10000, 1250, 1250, 100, TIPOPOSTO.ESTRATEGICO, List.of(0, 9930, 1237, 1237), SITUACAO.EMERGENCIA),
                Arguments.of(0, 1000, 100, 100, 2000, TIPOPOSTO.ESTRATEGICO, List.of(-21), SITUACAO.EMERGENCIA),
                Arguments.of(500, 10000, 1250, 1250, 100000, TIPOPOSTO.COMUM, List.of(-21), SITUACAO.NORMAL)
        );
    }
}
