package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DTO.PersonagemDTO;

public class PersonagemDAO {

	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	ArrayList<PersonagemDTO> lista = new ArrayList<>();
	
	
	public void cadastrarPersonagem(PersonagemDTO objPersonagemDTO) {
		String sql = "insert into crud (Nome, Vocacao) values (?, ?)";
		conn = new ConexaoDAO().conectaBD();
		
		try {
			
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, objPersonagemDTO.getNome());
			pstm.setString(2,objPersonagemDTO.getVocacao());
			
			pstm.execute();
			pstm.close();
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "FuncionarioDAO" +e);
		}
	}
	
 public ArrayList<PersonagemDTO> PesquisarPersonagem(){

		conn = new ConexaoDAO().conectaBD();
		String sql = "select * from crud";
		//Até aqui funciona por algum motivo, já foi testado.
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				PersonagemDTO objPersonagemDTO = new PersonagemDTO();
				objPersonagemDTO.setID(rs.getInt("ID"));
				objPersonagemDTO.setNome(rs.getString("Nome"));
				objPersonagemDTO.setVocacao(rs.getString("Vocacao"));
				
				lista.add(objPersonagemDTO);
								
			}
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "PersonagemDAO" + erro);
			
			// TODO: handle exception
		}
		return lista;
	}
 
 	public void alterarPersonagem(PersonagemDTO objPersonagemDTO) {
 		conn = new ConexaoDAO().conectaBD();
 		String sql = "UPDATE CRUD SET NOME = ? where id = ?";
 		
try {
			
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, objPersonagemDTO.getNome());
			pstm.setInt(2,objPersonagemDTO.getID());
			
			
			pstm.execute();
			pstm.close();
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Alterar DAO" +e);
		}
	}
 
 	public void excluirPersonagem(PersonagemDTO objPersonagemDTO) {

	 			conn = new ConexaoDAO().conectaBD();
	 			String sql = "DELETE FROM CRUD WHERE ID = ?";
 	 		
 	try {
 				
 				pstm = conn.prepareStatement(sql);
 				pstm.setInt(1,objPersonagemDTO.getID());
 				
 				
 				pstm.execute();
 				pstm.close();
 				
 			} catch (Exception e) {
 				
 				JOptionPane.showMessageDialog(null, "Excluir DAO" +e);
 			}
 		
 	}
 	
 	
}
