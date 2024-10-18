package lyc.compiler.model;

import java.io.Serial;

public class IdentifierAlreadyExistsException extends CompilerException {

    @Serial
    private static final long serialVersionUID = -6649278000190971816L;

    public IdentifierAlreadyExistsException(String message) {
        super(message);
    }

}
