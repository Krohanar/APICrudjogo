package VIEW;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DAO.PersonagemDAO;
import DTO.PersonagemDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;


public class teste extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					teste frame = new teste();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public teste() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 5, 419, 157);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		
		JButton btncriar = new JButton("New button");
		btncriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarValores();
			}
		});
		btncriar.setBounds(144, 182, 89, 23);
		contentPane.add(btncriar);
	}

	private void ListarValores() {
	try {
		PersonagemDAO objPersonagemDAO = new PersonagemDAO();
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
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
}
