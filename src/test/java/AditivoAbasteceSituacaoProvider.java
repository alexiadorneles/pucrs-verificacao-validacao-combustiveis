import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class AditivoAbasteceSituacaoProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(400, 200, SITUACAO.NORMAL),
                Arguments.of(220, 29, SITUACAO.SOBRAVISO),
                Arguments.of(220, 50, SITUACAO.NORMAL),
                Arguments.of(120, 4, SITUACAO.EMERGENCIA),
                Arguments.of(100, 100, SITUACAO.SOBRAVISO),
                Arguments.of(10, 450, SITUACAO.NORMAL)
        );
    }
}