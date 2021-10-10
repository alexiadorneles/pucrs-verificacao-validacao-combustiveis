import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class AlcoolAbasteceSituacaoProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(2300, 200, SITUACAO.NORMAL),
                Arguments.of(1000, 200, SITUACAO.SOBRAVISO),
                Arguments.of(1000, 1000, SITUACAO.NORMAL),
                Arguments.of(400, 200, SITUACAO.EMERGENCIA),
                Arguments.of(600, 100, SITUACAO.SOBRAVISO),
                Arguments.of(600, 1000, SITUACAO.NORMAL)
        );
    }
}