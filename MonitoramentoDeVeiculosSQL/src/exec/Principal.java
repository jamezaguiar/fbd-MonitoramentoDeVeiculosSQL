package exec;

import java.sql.SQLException;

public class Principal {
    public static void main(String[] args) throws SQLException {
        InterfaceMain execucao = new InterfaceMain();
        execucao.start();
    }
}
