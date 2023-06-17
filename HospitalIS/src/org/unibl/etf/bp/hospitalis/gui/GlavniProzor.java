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
	// private JMenu mnSifarnici;
    private JMenu mnTelefoniOdjela;
    private JMenuItem mntmTelefoniOdjela;
	private JMenu mnOsobljeIOdjeli;
	private JMenuItem mntmZaposleni;
	private JMenuItem mntmDoktori;
	private JMenuItem mntmMedicinskiTehnicari;
	private JMenuItem mntmOdjeli;
	private JMenuItem mntmZaposleniNaOdjelima;;
    private JMenu mnPacijenti;
    private JMenuItem mntmZdravstvenaOsiguranja;
    private JMenuItem mntmKontaktOsobe;
    private JMenuItem mntmSviPacijenti;
    private JMenuItem mntmNezadrzaniPacijenti;
    private JMenuItem mntmZadrzaniPacijenti;
    private JMenu mnSobe;
    private JMenuItem mntmSobe;
    private JMenu mnDijagnostika;
    private JMenuItem mntmLijekovi;
    private JMenuItem mntmDijagnostickiPregledi;
    private JMenuItem mntmPrepisivanje;
    private JMenuItem mntmPregledanje;
	private JMenu mnIzvestaji;
	private JMenuItem mntmRacuniPacijenata;
	private JMenuItem mntmUkupanIznosRacuna2022;
	private JMenuItem mntmUkupanIznosRacunaTekucaGodina;
	private JMenuItem mntmProsjecnePlateDoktoraOdjeli;
	private JMenuItem mntmProsjecnePlateMedicinskihTehnicaraOdjeli;
	private JMenuItem mntmUkupanBrojZaposlenihOdjeli;
	private JMenuItem mntmUkupnoEvidentiranihPacijenataTekucaGodina;

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
			menuBar.add(getMnTelefoniOdjela());
			menuBar.add(getMnOsobljeIOdjeli());
			menuBar.add(getMnPacijenti());
			menuBar.add(getMnSobe());
			menuBar.add(getMnDijagnostika());
			menuBar.add(getMnIzvestaji());
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
	
	private JMenu getMnTelefoniOdjela() {
		if (mnTelefoniOdjela == null) {
			mnTelefoniOdjela = new JMenu("Kontakt telefoni");
			mnTelefoniOdjela.setMnemonic('T');
			mnTelefoniOdjela.add(getMntmTelefoniOdjela());
		}
		return mnTelefoniOdjela;
	}

	private JMenuItem getMntmTelefoniOdjela() {
		if (mntmTelefoniOdjela == null) {
			mntmTelefoniOdjela = new JMenuItem("Kontakt telefoni odjela");
			mntmTelefoniOdjela.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new TelefoniOdjelaFrame(false).setVisible(true);
				}
			});
			mntmTelefoniOdjela.setMnemonic('T');
		}
		return mntmTelefoniOdjela;
	}

	private JMenu getMnOsobljeIOdjeli() {
		if (mnOsobljeIOdjeli == null) {
			mnOsobljeIOdjeli = new JMenu("Osoblje i odjeli");
			mnOsobljeIOdjeli.setMnemonic('T');
			mnOsobljeIOdjeli.add(getMntmZaposleni());
			mnOsobljeIOdjeli.add(getMntmDoktori());
			mnOsobljeIOdjeli.add(getMntmMedicinskiTehnicari());
			mnOsobljeIOdjeli.add(getMntmOdjeli());
			mnOsobljeIOdjeli.add(getMntmZaposleniNaOdjelima());
		}
		return mnOsobljeIOdjeli;
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
	
	private JMenuItem getMntmZaposleniNaOdjelima(){
		if (mntmZaposleniNaOdjelima == null) {
			mntmZaposleniNaOdjelima = new JMenuItem("Zaposleni na odjelima");
			mntmZaposleniNaOdjelima.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ZaposleniNaOdjelimaFrame().setVisible(true);
				}
			});
			mntmZaposleniNaOdjelima.setMnemonic('Z');
		}
		return mntmZaposleniNaOdjelima;
	}
	
	private JMenu getMnPacijenti() {
		if (mnPacijenti == null) {
			mnPacijenti = new JMenu("Pacijenti");
			mnPacijenti.setMnemonic('P');
			mnPacijenti.add(getMntmZdravstvenaOsiguranja());
			mnPacijenti.add(getMntmKontaktOsobe());
			mnPacijenti.add(getSviPacijenti());
			mnPacijenti.add(getNezadrzaniPacijenti());
			mnPacijenti.add(getZadrzaniPacijenti());
		}
		return mnPacijenti;
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
	
	private JMenuItem getSviPacijenti() {
		if (mntmSviPacijenti == null) {
			mntmSviPacijenti = new JMenuItem("Svi pacijenti");
			mntmSviPacijenti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new PacijentiFrame(false).setVisible(true);
				}
			});
			mntmSviPacijenti.setMnemonic('P');
		}
		return mntmSviPacijenti;
	}
	
	private JMenuItem getNezadrzaniPacijenti() {
		if (mntmNezadrzaniPacijenti == null) {
			mntmNezadrzaniPacijenti = new JMenuItem("Nezadržani pacijenti");
			mntmNezadrzaniPacijenti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new PacijentNijeZadrzanFrame(false).setVisible(true);
				}
			});
			mntmNezadrzaniPacijenti.setMnemonic('N');
		}
		return mntmNezadrzaniPacijenti;
	}
	
	private JMenuItem getZadrzaniPacijenti() {
		if (mntmZadrzaniPacijenti == null) {
			mntmZadrzaniPacijenti = new JMenuItem("Zadržani pacijenti");
			mntmZadrzaniPacijenti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new PacijentZadrzanFrame(false).setVisible(true);
				}
			});
			mntmZadrzaniPacijenti.setMnemonic('Z');
		}
		return mntmZadrzaniPacijenti;
	}
	
	private JMenu getMnSobe() {
		if (mnSobe == null) {
			mnSobe = new JMenu("Sobe");
			mnSobe.setMnemonic('S');
			mnSobe.add(getMntmSobe());
		}
		return mnSobe;
	}
	
	private JMenuItem getMntmSobe() {
		if (mntmSobe == null) {
			mntmSobe = new JMenuItem("Sobe");
			mntmSobe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new SobeFrame(false).setVisible(true);
				}
			});
			mntmSobe.setMnemonic('S');
		}
		return mntmSobe;
	}
	
	private JMenu getMnDijagnostika() {
		if (mnDijagnostika == null) {
			mnDijagnostika = new JMenu("Dijagnostika");
			mnDijagnostika.setMnemonic('D');
			mnDijagnostika.add(getMntmLijekovi());
			mnDijagnostika.add(getMntmDijagnostickiPregledi());
			mnDijagnostika.add(getMntmPrepisivanje());
			mnDijagnostika.add(getMntmPregledanje());
		}
		return mnDijagnostika;
	}
	
	private JMenuItem getMntmLijekovi() {
		if (mntmLijekovi == null) {
			mntmLijekovi = new JMenuItem("Lijekovi");
			mntmLijekovi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new LijekoviFrame(false).setVisible(true);
				}
			});
			mntmLijekovi.setMnemonic('L');
		}
		return mntmLijekovi;
	}
	
	private JMenuItem getMntmDijagnostickiPregledi() {
		if (mntmDijagnostickiPregledi == null) {
			mntmDijagnostickiPregledi = new JMenuItem("Dijagnostički pregledi");
			mntmDijagnostickiPregledi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new DijagnostickiPreglediFrame(false).setVisible(true);
				}
			});
			mntmDijagnostickiPregledi.setMnemonic('D');
		}
		return mntmDijagnostickiPregledi;
	}
	
	private JMenuItem getMntmPrepisivanje() {
		if (mntmPrepisivanje == null) {
			mntmPrepisivanje = new JMenuItem("Prepisivanje lijekova");
			mntmPrepisivanje.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new PrepisivanjeFrame(false).setVisible(true);
				}
			});
			mntmPrepisivanje.setMnemonic('P');
		}
		return mntmPrepisivanje;
	}
	
	private JMenuItem getMntmPregledanje() {
		if (mntmPregledanje == null) {
			mntmPregledanje = new JMenuItem("Pregledanja pacijenata");
			mntmPregledanje.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new PregledanjeFrame(false).setVisible(true);
				}
			});
			mntmPregledanje.setMnemonic('P');
		}
		return mntmPregledanje;
	}
	
	private JMenu getMnIzvestaji() {
		if (mnIzvestaji == null) {
			mnIzvestaji = new JMenu("Izveštaji");
			mnIzvestaji.setMnemonic('I');
			mnIzvestaji.add(getMntmRacuniPacijenata());
			mnIzvestaji.add(getMntmUkupanIznosRacuna2022());
			mnIzvestaji.add(getMntmUkupanIznosRacunaTekucaGodina());
			mnIzvestaji.add(getMntmProsjecnePlateDoktoraOdjeli());
			mnIzvestaji.add(getMntmProsjecnePlateMedicinskihTehnicaraOdjeli());
			mnIzvestaji.add(getMntmUkupanBrojZaposlenihOdjeli());
			mnIzvestaji.add(getMntmUkupnoEvidentiranihPacijenataTekucaGodina());
		}
		return mnIzvestaji;
	}

	private JMenuItem getMntmRacuniPacijenata() {
		if (mntmRacuniPacijenata == null) {
			mntmRacuniPacijenata = new JMenuItem(
					"Računi pacijenata");
			mntmRacuniPacijenata.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new IzvjestajiRacuniFrame().setVisible(true);
				}
			});
			mntmRacuniPacijenata.setMnemonic('R');
		}
		return mntmRacuniPacijenata;
	}
	
	private JMenuItem getMntmUkupanIznosRacuna2022() {
		if (mntmUkupanIznosRacuna2022 == null) {
			mntmUkupanIznosRacuna2022 = new JMenuItem(
					"Ukupan iznos računa za 2022. godinu");
			mntmUkupanIznosRacuna2022.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new IzvjestajiUkupanIznosRacuna2022Frame().setVisible(true);
				}
			});
			mntmUkupanIznosRacuna2022.setMnemonic('U');
		}
		return mntmUkupanIznosRacuna2022;
	}
	
	private JMenuItem getMntmUkupanIznosRacunaTekucaGodina() {
		if (mntmUkupanIznosRacunaTekucaGodina == null) {
			mntmUkupanIznosRacunaTekucaGodina = new JMenuItem(
					"Ukupan iznos računa za tekuću godinu");
			mntmUkupanIznosRacunaTekucaGodina.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new IzvjestajiUkupanIznosRacunaTekucaGodinaFrame().setVisible(true);
				}
			});
			mntmUkupanIznosRacunaTekucaGodina.setMnemonic('T');
		}
		return mntmUkupanIznosRacunaTekucaGodina;
	}
	
	private JMenuItem getMntmProsjecnePlateDoktoraOdjeli() {
		if (mntmProsjecnePlateDoktoraOdjeli == null) {
			mntmProsjecnePlateDoktoraOdjeli = new JMenuItem(
					"Prosječne plate doktora po odjelima");
			mntmProsjecnePlateDoktoraOdjeli.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new IzvjestajiProsjecnePlateDoktoraOdjeliFrame().setVisible(true);
				}
			});
			mntmProsjecnePlateDoktoraOdjeli.setMnemonic('P');
		}
		return mntmProsjecnePlateDoktoraOdjeli;
	}
	
	private JMenuItem getMntmProsjecnePlateMedicinskihTehnicaraOdjeli() {
		if (mntmProsjecnePlateMedicinskihTehnicaraOdjeli == null) {
			mntmProsjecnePlateMedicinskihTehnicaraOdjeli = new JMenuItem(
					"Prosječne plate medicinskih tehničara po odjelima");
			mntmProsjecnePlateMedicinskihTehnicaraOdjeli.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new IzvjestajiProsjecnePlateMedicinskihTehnicaraOdjeliFrame().setVisible(true);
				}
			});
			mntmProsjecnePlateMedicinskihTehnicaraOdjeli.setMnemonic('P');
		}
		return mntmProsjecnePlateMedicinskihTehnicaraOdjeli;
	}
	
	private JMenuItem getMntmUkupanBrojZaposlenihOdjeli() {
		if (mntmUkupanBrojZaposlenihOdjeli == null) {
			mntmUkupanBrojZaposlenihOdjeli = new JMenuItem(
					"Ukupan broj zaposlenih po odjelima");
			mntmUkupanBrojZaposlenihOdjeli.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new IzvjestajiUkupanBrojZaposlenihOdjeliFrame().setVisible(true);
				}
			});
			mntmUkupanBrojZaposlenihOdjeli.setMnemonic('U');
		}
		return mntmUkupanBrojZaposlenihOdjeli;
	}
	
	private JMenuItem getMntmUkupnoEvidentiranihPacijenataTekucaGodina() {
		if (mntmUkupnoEvidentiranihPacijenataTekucaGodina == null) {
			mntmUkupnoEvidentiranihPacijenataTekucaGodina = new JMenuItem(
					"Ukupan broj evidentiranih pacijenata u tekućoj godini");
			mntmUkupnoEvidentiranihPacijenataTekucaGodina.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new IzvjestajiUkupnoEvidentiranihPacijenataTekucaGodinaFrame().setVisible(true);
				}
			});
			mntmUkupnoEvidentiranihPacijenataTekucaGodina.setMnemonic('U');
		}
		return mntmUkupnoEvidentiranihPacijenataTekucaGodina;
	}
	
}
