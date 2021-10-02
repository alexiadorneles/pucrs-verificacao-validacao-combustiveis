import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ConstructorArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(500, 10000, 1250, 1250, SITUACAO.NORMAL),
                Arguments.of(450, 9000, 1000, 1000, SITUACAO.NORMAL),
                Arguments.of(249, 9000, 1000, 1000, SITUACAO.SOBRAVISO),
                Arguments.of(450, 4999, 1000, 1000, SITUACAO.SOBRAVISO),
                Arguments.of(450, 9000, 624, 1000, SITUACAO.SOBRAVISO),
                Arguments.of(450, 4999, 1000, 1000, SITUACAO.SOBRAVISO),
                Arguments.of(450, 9000, 624, 1000, SITUACAO.SOBRAVISO),
                Arguments.of(450, 9000, 1000, 624, SITUACAO.SOBRAVISO),
                Arguments.of(124, 9000, 1000, 1000, SITUACAO.EMERGENCIA),
                Arguments.of(450, 249, 1000, 1000, SITUACAO.EMERGENCIA),
                Arguments.of(450, 9000, 312, 1000, SITUACAO.EMERGENCIA),
                Arguments.of(450, 9000, 1000, 312, SITUACAO.EMERGENCIA)
        );
    }
}