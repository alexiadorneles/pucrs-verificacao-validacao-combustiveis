import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class GasolinaAbasteceSituacaoProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(8000, 1000, SITUACAO.NORMAL),
                Arguments.of(4000, 999, SITUACAO.SOBRAVISO),
                Arguments.of(4000, 1200, SITUACAO.NORMAL),
                Arguments.of(2000, 300, SITUACAO.EMERGENCIA),
                Arguments.of(2000, 2000, SITUACAO.SOBRAVISO),
                Arguments.of(2000, 5000, SITUACAO.NORMAL)
        );
    }
}