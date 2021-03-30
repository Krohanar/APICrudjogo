package VIEW;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

import DAO.PersonagemDAO;
import DTO.PersonagemDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class frnJogo extends JFrame {
	
	
	

	private JPanel contentPane;
	private JTextField txtNome;
	private ButtonGroup grupoVocacao = new ButtonGroup();
	private JTable tabela;
	private JTextField txtCode;

	/**
	 * Launch the application.
         */
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frnJogo frame = new frnJogo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public frnJogo() {  
		setTitle("Criar Personagem");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 428, 295);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 392, 234);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Criar personagem", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(26, 68, 46, 14);
		panel.add(lblNewLabel);
		
		txtNome = new JTextField();
		txtNome.setBounds(67, 65, 288, 20);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		final JButton btnCriar = new JButton("Criar");
		btnCriar.setBounds(57, 132, 91, 23);
		panel.add(btnCriar);
		
		JLabel lblNewLabel_1 = new JLabel("Vocacao");
		lblNewLabel_1.setBounds(26, 107, 60, 14);
		panel.add(lblNewLabel_1);
			
		final JRadioButton rbnMago = new JRadioButton("Mago");
		rbnMago.setSelected(true);
		rbnMago.setBounds(96, 102, 71, 23);
		panel.add(rbnMago);
		grupoVocacao.add(rbnMago);
		
		final JRadioButton rbnCav = new JRadioButton("Cavaleiro");
		rbnCav.setBounds(165, 102, 94, 23);
		panel.add(rbnCav);		
		grupoVocacao.add(rbnCav);
		
		JButton btnCancelar = new JButton("Limpar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Limpar();
				ListarValores();
				rbnCav.setEnabled(true);
				rbnMago.setEnabled(true);
			}
		});
		btnCancelar.setBounds(230, 132, 89, 23);
		panel.add(btnCancelar);
		
		JLabel lblcod = new JLabel("C\u00F3digo");
		lblcod.setBounds(26, 32, 46, 14);
		panel.add(lblcod);
		
		txtCode = new JTextField();
		txtCode.setEditable(false);
		txtCode.setBounds(67, 29, 46, 20);
		panel.add(txtCode);
		txtCode.setColumns(10);
		
		final JButton btnalterar = new JButton("Alterar");
		btnalterar.setEnabled(false);
		btnalterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlterarJogador();
				ListarValores();
				Limpar();
				btnCriar.setEnabled(true);
				btnalterar.setEnabled(false);	
			}
		});
		btnalterar.setBounds(55, 172, 93, 23);
		panel.add(btnalterar);
		
		final JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExcluirJogador();
				Limpar();
				ListarValores();
				rbnCav.setEnabled(true);
				rbnMago.setEnabled(true);
				btnalterar.setEnabled(false);
				btnExcluir.setEnabled(false);
				btnCriar.setEnabled(true);
			}
		});
		btnExcluir.setBounds(230, 172, 89, 23);
		panel.add(btnExcluir);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Consultar Personagens", null, panel_1, null);
		panel_1.setLayout(null);
		
		JButton btnEditar = new JButton("Consultar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CarregarCampos();
				rbnCav.setEnabled(false);
				rbnMago.setEnabled(false);
				btnCriar.setEnabled(false);
				btnalterar.setEnabled(true);
				tabbedPane.setSelectedIndex(0);
				btnExcluir.setEnabled(true);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 367, 128);
		panel_1.add(scrollPane);
		
		tabela = new JTable();
		scrollPane.setViewportView(tabela);
		tabela.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Vocação"
			}
		));
		btnEditar.setBounds(10, 150, 89, 23);
		panel_1.add(btnEditar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ListarValores();
			}
		});
		btnAtualizar.setBounds(288, 150, 89, 23);
		panel_1.add(btnAtualizar);
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rbnMago.setActionCommand("Mago");
				rbnCav.setActionCommand("Cavaleiro");

				CadastrarPersonagem();
				ListarValores();
				Limpar();
				btnalterar.setEnabled(false);
			}
			
		});
	}
	
	private void ListarValores() {
	try {
		PersonagemDAO objPersonagemDAO = new PersonagemDAO();
		
		DefaultTableModel model = (DefaultTableModel) tabela.getModel();
		model.setNumRows(0);
		ArrayList<PersonagemDTO> lista = objPersonagemDAO.PesquisarPersonagem();
		
		for(int num = 0 ; num <lista.size(); num++) {
			model.addRow(new Object[] {
				lista.get(num).getID(),
				lista.get(num).getNome(),
				lista.get(num).getVocacao()
				
			});
		}
		
	} catch (Exception erro) {
		JOptionPane.showMessageDialog(null,"Listar Valores"+erro);
		
	}
	}
	
	private void CadastrarPersonagem() {
		String nome, vocacao;
		
		nome = txtNome.getText();
		vocacao = grupoVocacao.getSelection().getActionCommand();
				
		if (nome.isEmpty()) {
			JOptionPane.showMessageDialog(null,"Tente novamente, agora inserindo um nome");
		} else {
		
		PersonagemDTO objPersonagem = new PersonagemDTO();
		objPersonagem.setNome(nome);
		objPersonagem.setVocacao(vocacao);
		
		PersonagemDAO objPersonagemDAO = new PersonagemDAO();
		objPersonagemDAO.cadastrarPersonagem(objPersonagem);}}

	private void Limpar() {
	
		txtNome.setText("");
		txtNome.requestFocus();
		txtCode.setText("");
		
		
	}
	
		
	private void CarregarCampos() {
		int setar = tabela.getSelectedRow();
		
		if (setar < 0) {
			JOptionPane.showMessageDialog(null,"Por favor, selecione um campo","Erro",JOptionPane.WARNING_MESSAGE);
		} else {

			txtCode.setText(tabela.getModel().getValueAt(setar, 0).toString());
			txtNome.setText(tabela.getModel().getValueAt(setar, 1).toString());
					}
	}
	
	
	private void AlterarJogador() {
		int idj;
		String nome;
		 
		idj = Integer.parseInt(txtCode.getText());
		nome = txtNome.getText();
		
		if (nome.isBlank()) {
			JOptionPane.showMessageDialog(null,"Tente novamente, agora inserindo um nome");
		} else {
		
		PersonagemDTO objPersonagemDTO = new PersonagemDTO();
		objPersonagemDTO.setID(idj);
		objPersonagemDTO.setNome(nome);
		
		PersonagemDAO objPersonagemDAO = new PersonagemDAO();
		objPersonagemDAO.alterarPersonagem(objPersonagemDTO);
		}
}
	
	private void ExcluirJogador() {
		
		int idj;
		String nome;
		
		idj = Integer.parseInt(txtCode.getText());
		nome = txtNome.getText();
		
		
		if (nome.isBlank()) {
			JOptionPane.showMessageDialog(null,"Tente novamente");
		} else {
	 	
		PersonagemDTO objPersonagemDTO = new PersonagemDTO();
		objPersonagemDTO.setID(idj);
		objPersonagemDTO.setNome(nome);
		
		PersonagemDAO objPersonagemDAO = new PersonagemDAO();
		objPersonagemDAO.excluirPersonagem(objPersonagemDTO);
		
	}
	}
}

