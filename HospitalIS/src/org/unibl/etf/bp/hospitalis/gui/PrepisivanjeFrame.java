package org.unibl.etf.bp.hospitalis.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.List;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.unibl.etf.bp.hospitalis.entity.Prepisivanje;
import org.unibl.etf.bp.hospitalis.util.Utilities;

@SuppressWarnings("serial")
public class PrepisivanjeFrame extends JFrame{
	
	private PrepisivanjeFrame ovaj;
	private List<Prepisivanje> prepisivanja;

	private Prepisivanje odabranoPrepisivanje;

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panelPodaci;
	private JPanel panelOpcije;
	private JPanel panelPretraga;
	private JButton btnDodati;
	private JButton btnIzmeniti;
	private JButton btnObrisati;
	private JButton btnPrihvatiti;
	// private JLabel lblNazivOdjela;
	// private JTextField tfNazivOdjela;
	private JButton btnPretraziti;
	private JButton btnPrikazatiSve;
	private JScrollPane scrollPane;
	private JTable table;
	// @SuppressWarnings("rawtypes")
	// private JComboBox cbDoktori;

	/**
	 * Create the frame.
	 */
	public PrepisivanjeFrame(boolean odabirPrepisivanja) {
		ovaj = this;
		prepisivanja = Utilities.getDataAccessFactory().getPrepisivanjeDataAccess()
				.svaPrepisivanja();

		initialize();

		if (!odabirPrepisivanja)
			btnPrihvatiti.setVisible(false);
	}

	public Prepisivanje getOdabranoPrepisivanje() {
		return odabranoPrepisivanje;
	}

	/*private void osveziTabelu() {
		if (Utilities.isSearchPatternValid(tfNazivOdjela.getText())) {
			odjeli = Utilities.getDataAccessFactory().getOdjelDataAccess()
					.odjeli(tfNazivOdjela.getText());

			OdjelTableModel ftm = (OdjelTableModel) table.getModel();
			ftm.setPodaci(odjeli);
			ftm.fireTableDataChanged();
		}
	}*/
		
	private void osveziTabeluZaSve() {
		
		prepisivanja = Utilities.getDataAccessFactory().getPrepisivanjeDataAccess()
					.svaPrepisivanja();

		PrepisivanjeTableModel ftm = (PrepisivanjeTableModel) table.getModel();
			ftm.setPodaci(prepisivanja);
			ftm.fireTableDataChanged();
		
	}

	private void initialize() {
		setTitle("Prepisivanja");
		setBounds(100, 100, 815, 420);
		setLocationRelativeTo(null);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);
		this.contentPane.add(getPanel(), BorderLayout.NORTH);
		this.contentPane.add(getPanelPodaci(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getPanelOpcije(), BorderLayout.NORTH);
			panel.add(getPanelPretraga(), BorderLayout.SOUTH);
		}
		return panel;
	}

	private JPanel getPanelPodaci() {
		if (panelPodaci == null) {
			panelPodaci = new JPanel();
			panelPodaci.setLayout(new BorderLayout(0, 0));
			panelPodaci.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panelPodaci;
	}

	private JPanel getPanelOpcije() {
		if (panelOpcije == null) {
			panelOpcije = new JPanel();
			panelOpcije.setLayout(null);
			panelOpcije.setPreferredSize(new Dimension(200, 68));
			panelOpcije.add(getBtnDodati());
			panelOpcije.add(getBtnIzmeniti());
			panelOpcije.add(getBtnObrisati());
			panelOpcije.add(getBtnPrihvatiti());
		}
		return panelOpcije;
	}

	private JPanel getPanelPretraga() {
		if (panelPretraga == null) {
			panelPretraga = new JPanel();
			panelPretraga.setBorder(new TitledBorder(null, "Pretraga",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelPretraga.setLayout(null);
			panelPretraga.setPreferredSize(new Dimension(200, 70));
			//panelPretraga.add(getCbZaposleni());
	       // panelPretraga.add(getLblDoktori());
			//panelPretraga.add(getLblNazivOdjela());
			//panelPretraga.add(getTfNazivOdjela());
			//panelPretraga.add(getBtnPretraziti());
			panelPretraga.add(getBtnPrikazatiSve());
		}
		return panelPretraga;
	}

	private JButton getBtnDodati() {
		if (btnDodati == null) {
			btnDodati = new JButton("");
			btnDodati.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					PrepisivanjeDialog pd = new PrepisivanjeDialog();
					pd.setVisible(true);
					if (pd.getDialogResult().equalsIgnoreCase("OK")) {
						osveziTabeluZaSve();
						JOptionPane.showMessageDialog(ovaj,
								"Novo prepisivanje je uspješno dodano!", "Poruka",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			btnDodati.setIcon(new ImageIcon(PrepisivanjeFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Add_32.png")));
			btnDodati.setToolTipText("Dodati novo prepisivanje");
			btnDodati.setBounds(0, 0, 58, 58);
		}
		return btnDodati;
	}

	private JButton getBtnIzmeniti() {
		if (btnIzmeniti == null) {
			btnIzmeniti = new JButton("");
			btnIzmeniti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (table.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(ovaj,
								"Prepisivanje nije odabrano!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Prepisivanje odabranoPrepisivanje = ((PrepisivanjeTableModel) table
								.getModel()).getPrepisivanjeAtRow(table
								.getSelectedRow());
						PrepisivanjeDialog pd = new PrepisivanjeDialog(odabranoPrepisivanje);
						pd.setVisible(true);
						if (pd.getDialogResult().equalsIgnoreCase("OK")) {
							osveziTabeluZaSve();
							JOptionPane.showMessageDialog(ovaj,
									"Prepisivanje je uspješno ažurirano!", "Poruka",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			btnIzmeniti.setIcon(new ImageIcon(PrepisivanjeFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Edit_32.png")));
			btnIzmeniti.setToolTipText("Izmijeniti odabrano prepisivanje");
			btnIzmeniti.setBounds(68, 0, 58, 58);
		}
		return btnIzmeniti;
	}

	private JButton getBtnObrisati() {
		if (btnObrisati == null) {
			btnObrisati = new JButton("");
			btnObrisati.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (table.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(ovaj,
								"Prepisivanje nije odabrano!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Prepisivanje odabranoPrepisivanje = ((PrepisivanjeTableModel) table
								.getModel()).getPrepisivanjeAtRow(table
								.getSelectedRow());
						int odabir = JOptionPane
								.showOptionDialog(
										ovaj,
										"Da li ste sigurni da želite obrisati odabrano prepisivanje?",
										"Potvrda brisanja",
										JOptionPane.YES_NO_OPTION,
										JOptionPane.QUESTION_MESSAGE, null,
										Utilities.YES_NO_OPTIONS,
										Utilities.YES_NO_OPTIONS[1]);
						if (odabir == JOptionPane.YES_OPTION) {
							if (Utilities
									.getDataAccessFactory().getPrepisivanjeDataAccess()
									.obrisiPrepisivanje(
											odabranoPrepisivanje.getDatumPrepisivanja(), 
											odabranoPrepisivanje.getDoktor().getZaposleni().getJmb(),
											odabranoPrepisivanje.getPacijent().getJmbPacijenta(), 
											odabranoPrepisivanje.getLijek().getIdLijeka())) {
								osveziTabeluZaSve();
								JOptionPane.showMessageDialog(ovaj,
										"Prepisivanje je uspješno obrisano!",
										"Poruka",
										JOptionPane.INFORMATION_MESSAGE);
							} else
								JOptionPane.showMessageDialog(ovaj,
										"Prepisivanje nije uspješno obrisano!",
										"Poruka",
										JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			btnObrisati.setIcon(new ImageIcon(PrepisivanjeFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Delete_32.png")));
			btnObrisati.setToolTipText("Obrisati odabrano prepisivanje");
			btnObrisati.setBounds(136, 0, 58, 58);
		}
		return btnObrisati;
	}

	 private JButton getBtnPrihvatiti() {
		if (btnPrihvatiti == null) {
			btnPrihvatiti = new JButton("");
			btnPrihvatiti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (table.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(ovaj,
								"Prepisivanje nije odabrano!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} else {
						odabranoPrepisivanje = ((PrepisivanjeTableModel) table.getModel())
								.getPrepisivanjeAtRow(table.getSelectedRow());
						ovaj.getToolkit()
								.getSystemEventQueue()
								.postEvent(
										new WindowEvent(ovaj,
												WindowEvent.WINDOW_CLOSING));
					}
				}
			});
			btnPrihvatiti.setIcon(new ImageIcon(PrepisivanjeFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Check_32.png")));
			btnPrihvatiti.setToolTipText("Prihvatiti odabrano prepisivanje");
			btnPrihvatiti.setBounds(204, 0, 58, 58);
		}
		return btnPrihvatiti;
	}

	/*private JLabel getLblDoktori() {
		if (lblDoktori == null) {
			lblDoktori = new JLabel("Doktori:");
			lblDoktori.setBounds(10, 20, 254, 14);
		}
		return lblDoktori;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox getCbZaposleni() {
		if (cbDoktori == null) {
			cbDoktori = new JComboBox(Utilities.getDataAccessFactory()
					.getDoktorDataAccess().sviDoktori()
					.toArray(new Doktor[] {}));
			cbDoktori.setBounds(10, 37, 254, 20);
			cbDoktori.setSelectedIndex(-1);
		}
		return cbDoktori;
	}*/
	
	/*private JLabel getLblNazivOdjela() {
		if (lblNazivOdjela == null) {
			lblNazivOdjela = new JLabel("Naziv odjela:");
			lblNazivOdjela.setBounds(10, 20, 254, 14);
		}
		return lblNazivOdjela;
	}

	private JTextField getTfNazivOdjela() {
		if (tfNazivOdjela == null) {
			tfNazivOdjela = new JTextField();
			tfNazivOdjela.setText("*");
			tfNazivOdjela.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
						btnPretraziti.doClick();
				}
			});
			tfNazivOdjela.setColumns(10);
			tfNazivOdjela.setBounds(10, 37, 254, 20);
		}
		return tfNazivOdjela;
	}*/

	
	/*private JButton getBtnPretraziti() {
		if (btnPretraziti == null) {
			btnPretraziti = new JButton("Pretražiti");
			btnPretraziti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (Utilities.isSearchPatternValid(tfNazivOdjela
							.getText()))
						osveziTabelu();
					else
						JOptionPane.showMessageDialog(ovaj,
								"Naziv odjela nije pravilno popunjen!",
								"Greška", JOptionPane.ERROR_MESSAGE);
				}
			});
			btnPretraziti.setBounds(538, 36, 100, 23);
		}
		return btnPretraziti;
	}*/

	private JButton getBtnPrikazatiSve() {
		if (btnPrikazatiSve == null) {
			btnPrikazatiSve = new JButton("Prikazati sve");
			btnPrikazatiSve.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					osveziTabeluZaSve();
				}
			});
			// btnPrikazatiSve.setBounds(648, 36, 100, 23);
			btnPrikazatiSve.setBounds(10, 30, 254, 23);
		}
		return btnPrikazatiSve;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane
					.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable(new PrepisivanjeTableModel(prepisivanja));
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setFillsViewportHeight(true);
			table.getColumnModel().getColumn(0).setPreferredWidth(150);
			table.getColumnModel().getColumn(1).setPreferredWidth(150);	
			table.getColumnModel().getColumn(2).setPreferredWidth(150);
			table.getColumnModel().getColumn(3).setPreferredWidth(150);	
			table.getColumnModel().getColumn(4).setPreferredWidth(150);
			table.getColumnModel().getColumn(5).setPreferredWidth(150);
			table.getColumnModel().getColumn(6).setPreferredWidth(150);
		}
		return table;
	}
}
