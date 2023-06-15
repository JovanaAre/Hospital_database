package org.unibl.etf.bp.hospitalis.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.List;

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

import org.unibl.etf.bp.hospitalis.entity.PacijentNijeZadrzan;
import org.unibl.etf.bp.hospitalis.util.Utilities;

@SuppressWarnings("serial")
public class PacijentNijeZadrzanFrame extends JFrame{
	private PacijentNijeZadrzanFrame ovaj;
	private List<PacijentNijeZadrzan> nezadrzaniPacijenti;

	private PacijentNijeZadrzan odabraniNezadrzaniPacijent;

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panelPodaci;
	private JPanel panelOpcije;
	private JPanel panelPretraga;
	private JButton btnDodati;
	private JButton btnIzmeniti;
	private JButton btnObrisati;
	private JButton btnPrihvatiti;
	// private JLabel lblJmb;
	// private JTextField tfJmb;
	private JLabel lblNezadrzaniPacijenti;
	private JButton btnPretraziti;
	private JButton btnPrikazatiSve;
	private JScrollPane scrollPane;
	private JTable table;
	@SuppressWarnings("rawtypes")
	private JComboBox cbNezadrzaniPacijenti;

	/**
	 * Create the frame.
	 */
	public PacijentNijeZadrzanFrame(boolean odabirNezadrzanogPacijenta) {
		ovaj = this;
		nezadrzaniPacijenti = Utilities.getDataAccessFactory().getPacijentNijeZadrzanDataAccess()
				.nezadrzaniPacijenti("*");

		initialize();

		if (!odabirNezadrzanogPacijenta)
			btnPrihvatiti.setVisible(false);
	}

	public  PacijentNijeZadrzan getOdabraniPacijentNijeZadrzan() {
		return odabraniNezadrzaniPacijent;
	}

	private void osveziTabelu() {
		
			String jmbPacijenta = null;
			if (cbNezadrzaniPacijenti.getSelectedItem() != null)
				jmbPacijenta = ((PacijentNijeZadrzan) cbNezadrzaniPacijenti
						.getSelectedItem()).getPacijent().getJmbPacijenta();

			nezadrzaniPacijenti = Utilities.getDataAccessFactory().getPacijentNijeZadrzanDataAccess()
					.nezadrzaniPacijenti(jmbPacijenta);

			PacijentNijeZadrzanTableModel ftm = (PacijentNijeZadrzanTableModel) table.getModel();
			ftm.setPodaci(nezadrzaniPacijenti);
			ftm.fireTableDataChanged();
		
	}
	
	/*private void osveziTabelu() {
		if (Utilities.isSearchPatternValid(tfJmb.getText())) {

			doktori = Utilities.getDataAccessFactory().getDoktorDataAccess()
					.doktori(tfJmb.getText());

			DoktorTableModel ftm = (DoktorTableModel) table.getModel();
			ftm.setPodaci(doktori);
			ftm.fireTableDataChanged();
		}
	}*/
	
	private void osveziTabeluZaSve() {
		
		nezadrzaniPacijenti = Utilities.getDataAccessFactory().getPacijentNijeZadrzanDataAccess()
					.sviNezadrzaniPacijenti();

			PacijentNijeZadrzanTableModel ftm = (PacijentNijeZadrzanTableModel) table.getModel();
			ftm.setPodaci(nezadrzaniPacijenti);
			ftm.fireTableDataChanged();
		
	}

	private void initialize() {
		setTitle("Nezadržani pacijenti");
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
			panelPretraga.add(getCbPacijenti());
	        panelPretraga.add(getLblNezadrzaniPacijenti());
			panelPretraga.add(getBtnPretraziti());
			panelPretraga.add(getBtnPrikazatiSve());
		}
		return panelPretraga;
	}

	private JButton getBtnDodati() {
		if (btnDodati == null) {
			btnDodati = new JButton("");
			btnDodati.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					PacijentNijeZadrzanDialog pnzd = new PacijentNijeZadrzanDialog();
					pnzd.setVisible(true);
					if (pnzd.getDialogResult().equalsIgnoreCase("OK")) {
						osveziTabeluZaSve();
						JOptionPane.showMessageDialog(ovaj,
								"Novi nezadržani pacijent je uspješno dodan!", "Poruka",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			btnDodati.setIcon(new ImageIcon(PacijentNijeZadrzanFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Add_32.png")));
			btnDodati.setToolTipText("Dodati novog nezadržanog pacijenta");
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
								"Nezadržani pacijent nije odabran!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} else {
						PacijentNijeZadrzan odabraniNezadrzaniPacijent = ((PacijentNijeZadrzanTableModel) table
								.getModel()).getPacijentNijeZadrzanAtRow(table
								.getSelectedRow());
						PacijentNijeZadrzanDialog pnzd = new PacijentNijeZadrzanDialog(odabraniNezadrzaniPacijent);
						pnzd.setVisible(true);
						if (pnzd.getDialogResult().equalsIgnoreCase("OK")) {
							osveziTabeluZaSve();
							JOptionPane.showMessageDialog(ovaj,
									"Nezadržani pacijent je uspješno ažuriran!", "Poruka",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			btnIzmeniti.setIcon(new ImageIcon(PacijentNijeZadrzanFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Edit_32.png")));
			btnIzmeniti.setToolTipText("Izmijeniti nezadržanog pacijenta");
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
								"Nezadržani pacijent nije odabran!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} else {
						PacijentNijeZadrzan odabraniNezadrzaniPacijent = ((PacijentNijeZadrzanTableModel) table
								.getModel()).getPacijentNijeZadrzanAtRow(table
								.getSelectedRow());
						int odabir = JOptionPane
								.showOptionDialog(
										ovaj,
										"Da li ste sigurni da želite obrisati odabranog pacijenta?",
										"Potvrda brisanja",
										JOptionPane.YES_NO_OPTION,
										JOptionPane.QUESTION_MESSAGE, null,
										Utilities.YES_NO_OPTIONS,
										Utilities.YES_NO_OPTIONS[1]);
						if (odabir == JOptionPane.YES_OPTION) {
							if (Utilities
									.getDataAccessFactory().getPacijentNijeZadrzanDataAccess()
									.obrisiNezadrzanogPacijenta(
											odabraniNezadrzaniPacijent.getPacijent().getJmbPacijenta())) {
								osveziTabeluZaSve();
								JOptionPane.showMessageDialog(ovaj,
										"Nezadržani pacijent je uspješno obrisan!",
										"Poruka",
										JOptionPane.INFORMATION_MESSAGE);
							} else
								JOptionPane.showMessageDialog(ovaj,
										"Nezadržani pacijent nije uspješno obrisan!",
										"Poruka",
										JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			btnObrisati.setIcon(new ImageIcon(PacijentNijeZadrzanFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Delete_32.png")));
			btnObrisati.setToolTipText("Obrisati odabranog nezadrzanog pacijenta");
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
								"Nezadržani pacijent nije odabran!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} else {
						odabraniNezadrzaniPacijent = ((PacijentNijeZadrzanTableModel) table.getModel())
								.getPacijentNijeZadrzanAtRow(table.getSelectedRow());
						ovaj.getToolkit()
								.getSystemEventQueue()
								.postEvent(
										new WindowEvent(ovaj,
												WindowEvent.WINDOW_CLOSING));
					}
				}
			});
			btnPrihvatiti.setIcon(new ImageIcon(PacijentNijeZadrzanFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Check_32.png")));
			btnPrihvatiti.setToolTipText("Prihvatiti odabranog nezadržanog pacijenta");
			btnPrihvatiti.setBounds(204, 0, 58, 58);
		}
		return btnPrihvatiti;
	}
/*	private JLabel getLblJmb() {
		if (lblJmb == null) {
			lblJmb = new JLabel("JMB doktora:");
			lblJmb.setBounds(10, 21, 254, 14);
		}
		return lblJmb;
	}

	private JTextField getTfJmb() {
		if (tfJmb == null) {
			tfJmb = new JTextField();
			tfJmb.setText("*");
			tfJmb.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
						btnPretraziti.doClick();
				}
			});
			tfJmb.setBounds(10, 38, 254, 20);
			tfJmb.setColumns(10);
		}
		return tfJmb;
	}*/

	private JLabel getLblNezadrzaniPacijenti() {
		if (lblNezadrzaniPacijenti == null) {
			lblNezadrzaniPacijenti = new JLabel("Nezadržani pacijenti:");
			lblNezadrzaniPacijenti.setBounds(10, 20, 254, 14);
		}
		return lblNezadrzaniPacijenti;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox getCbPacijenti() {
		if (cbNezadrzaniPacijenti == null) {
			cbNezadrzaniPacijenti = new JComboBox(Utilities.getDataAccessFactory()
					.getPacijentNijeZadrzanDataAccess().sviNezadrzaniPacijenti()
					.toArray(new PacijentNijeZadrzan[] {}));
			cbNezadrzaniPacijenti.setBounds(10, 37, 254, 20);
			cbNezadrzaniPacijenti.setSelectedIndex(-1);
		}
		return cbNezadrzaniPacijenti;
	}
	
	private JButton getBtnPretraziti() {
		if (btnPretraziti == null) {
			btnPretraziti = new JButton("Pretražiti");
			btnPretraziti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
						osveziTabelu();
				}
			});
			btnPretraziti.setBounds(538, 36, 100, 23);
		}
		return btnPretraziti;
	}

	private JButton getBtnPrikazatiSve() {
		if (btnPrikazatiSve == null) {
			btnPrikazatiSve = new JButton("Prikazati sve");
			btnPrikazatiSve.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					osveziTabeluZaSve();
				}
			});
			btnPrikazatiSve.setBounds(648, 36, 100, 23);
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
			table = new JTable(new PacijentNijeZadrzanTableModel(nezadrzaniPacijenti));
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setFillsViewportHeight(true);
			table.getColumnModel().getColumn(0).setPreferredWidth(250);
			table.getColumnModel().getColumn(1).setPreferredWidth(250);	
			table.getColumnModel().getColumn(2).setPreferredWidth(250);
			table.getColumnModel().getColumn(3).setPreferredWidth(250);	
			// table.getColumnModel().getColumn(4).setPreferredWidth(250);	
		}
		return table;
	}
}

