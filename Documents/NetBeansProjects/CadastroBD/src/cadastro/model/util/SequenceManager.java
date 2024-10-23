package cadastro.model.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceManager {
    
    public static int getValue(String sequence) {
        try {
            Connection conexao = ConectorBD.getConnection();
            if (conexao == null) {
                return -1;
            }
            String sql = "SELECT NEXT VALUE FOR dbo." + sequence;
            PreparedStatement consulta = ConectorBD.getPrepared(conexao, sql);
            ResultSet resultado = ConectorBD.getSelect(consulta);
            if (resultado == null || !resultado.next()) {
                ConectorBD.close(conexao);
                return -1;
            }
            int value = resultado.getInt(1);
            ConectorBD.close(resultado);
            ConectorBD.close(consulta);
            ConectorBD.close(conexao);
            return value;
        } catch (SQLException e) {
            System.out.println("Erro ao obter o valor da sequência: " + e.getMessage());
            return -1;
        }
    }
}