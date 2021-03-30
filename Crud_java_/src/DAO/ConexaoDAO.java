package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexaoDAO {

	public Connection conectaBD() {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "projeto_crudd";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		
		try {
			conn = DriverManager.getConnection(url+dbName,userName,password);
			
		}catch(SQLException erro) {
			JOptionPane.showMessageDialog(null, "ConexãoDao" + erro.getMessage());
		}
		return conn;
	}
	
	
}
