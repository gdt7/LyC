package lyc.compiler.model;

import java.io.Serial;

public class IdentifierNotDeclaredException extends CompilerException {

    @Serial
    private static final long serialVersionUID = -1123581321345589000L;

    public IdentifierNotDeclaredException(String message) {
        super(message);
    }
}
