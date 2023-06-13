package org.unibl.etf.bp.hospitalis.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.bp.hospitalis.util.Utilities;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GlavniProzor extends JFrame {
	
	private JFrame ovaj;

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnAplikacija;
	private JMenuItem mntmIzlaz;
	private JMenu mnSifarnici;
	private JMenuItem mntmLijekovi;
	private JMenuItem mntmZdravstvenaOsiguranja;
	private JMenuItem mntmKontaktOsobe;
	private JMenuItem mntmZaposleni;
	private JMenuItem mntmDoktori;
	private JMenuItem mntmMedicinskiTehnicari;
	private JMenuItem mntmOdjeli;
//	private JMenuItem mntmFakulteti;
//	private JMenuItem mntmPredmeti;
//	private JMenuItem mntmStudijskiProgrami;
//	private JMenu mnPlanIProgram;
//	private JMenuItem mntmPlanIProgram;
//	private JMenu mnIzvestaji;
//	private JMenuItem mntmProsecneOceneStudenata;

	/**
	 * Create the frame.
	 */
	public GlavniProzor() {
		initialize();
	}

	private void initialize() {
		ovaj = this;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				int rezultat = JOptionPane.showOptionDialog(ovaj,
						"Da li ste sigurni da želite zatvoriti aplikaciju?",
						"Potvrda zatvaranja", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null,
						Utilities.YES_NO_OPTIONS,
						Utilities.YES_NO_OPTIONS[1]);
				if (rezultat == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		setTitle("HOSPITALIS - Informacioni sistem bolnice");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 815, 420);
		setLocationRelativeTo(null);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setJMenuBar(getMenuBar_1());

		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnAplikacija());
			menuBar.add(getMnSifarnici());
		}
		return menuBar;
	}

	private JMenu getMnAplikacija() {
		if (mnAplikacija == null) {
			mnAplikacija = new JMenu("Aplikacija");
			mnAplikacija.setMnemonic('A');
			mnAplikacija.add(getMntmIzlaz());
		}
		return mnAplikacija;
	}

	private JMenuItem getMntmIzlaz() {
		if (mntmIzlaz == null) {
			mntmIzlaz = new JMenuItem("Izlaz");
			mntmIzlaz.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ovaj.getToolkit()
							.getSystemEventQueue()
							.postEvent(
									new WindowEvent(ovaj,
											WindowEvent.WINDOW_CLOSING));
				}
			});
			mntmIzlaz.setMnemonic('I');
			mntmIzlaz.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
					InputEvent.ALT_MASK));
		}
		return mntmIzlaz;
	}

	private JMenu getMnSifarnici() {
		if (mnSifarnici == null) {
			mnSifarnici = new JMenu("Šifarnici");
			mnSifarnici.setMnemonic('F');
			mnSifarnici.add(getMntmLijekovi());
			mnSifarnici.add(getMntmZdravstvenaOsiguranja());
			mnSifarnici.add(getMntmKontaktOsobe());
			mnSifarnici.add(getMntmZaposleni());
			mnSifarnici.add(getMntmDoktori());
			mnSifarnici.add(getMntmMedicinskiTehnicari());
			mnSifarnici.add(getMntmOdjeli());
		}
		return mnSifarnici;
	}

	private JMenuItem getMntmLijekovi() {
		if (mntmLijekovi == null) {
			mntmLijekovi = new JMenuItem("Lijekovi");
			mntmLijekovi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new LijekoviFrame(false).setVisible(true);
				}
			});
			mntmLijekovi.setMnemonic('F');
		}
		return mntmLijekovi;
	}
	
	private JMenuItem getMntmZdravstvenaOsiguranja() {
		if (mntmZdravstvenaOsiguranja == null) {
			mntmZdravstvenaOsiguranja = new JMenuItem("Zdravstvena osiguranja");
			mntmZdravstvenaOsiguranja.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new ZdravstvenaOsiguranjaFrame(false).setVisible(true);
				}
			});
			mntmZdravstvenaOsiguranja.setMnemonic('F');
		}
		return mntmZdravstvenaOsiguranja;
	}
	
	private JMenuItem getMntmKontaktOsobe() {
		if (mntmKontaktOsobe == null) {
			mntmKontaktOsobe = new JMenuItem("Kontakt osobe pacijenata");
			mntmKontaktOsobe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new KontaktOsobeFrame(false).setVisible(true);
				}
			});
			mntmKontaktOsobe.setMnemonic('K');
		}
		return mntmKontaktOsobe;
	}
	
	private JMenuItem getMntmZaposleni() {
		if (mntmZaposleni == null) {
			mntmZaposleni = new JMenuItem("Zaposleni");
			mntmZaposleni.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ZaposleniFrame(false).setVisible(true);
				}
			});
			mntmZaposleni.setMnemonic('Z');
		}
		return mntmZaposleni;
	}
	
	private JMenuItem getMntmDoktori(){
		if (mntmDoktori == null) {
			mntmDoktori = new JMenuItem("Doktori");
			mntmDoktori.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new DoktoriFrame(false).setVisible(true);
				}
			});
			mntmDoktori.setMnemonic('D');
		}
		return mntmDoktori;
	}
	
	private JMenuItem getMntmMedicinskiTehnicari(){
		if (mntmMedicinskiTehnicari == null) {
			mntmMedicinskiTehnicari = new JMenuItem("Medicinski tehnicari");
			mntmMedicinskiTehnicari.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new MedicinskiTehnicariFrame(false).setVisible(true);
				}
			});
			mntmMedicinskiTehnicari.setMnemonic('M');
		}
		return mntmMedicinskiTehnicari;
	}
	
	private JMenuItem getMntmOdjeli(){
		if (mntmOdjeli == null) {
			mntmOdjeli = new JMenuItem("Odjeli");
			mntmOdjeli.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new OdjeliFrame(false).setVisible(true);
				}
			});
			mntmOdjeli.setMnemonic('O');
		}
		return mntmOdjeli;
	}
	
}
