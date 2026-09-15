package loim;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GraphicalUI {

	//Kérdések adatstruktúra
	protected List<Kerdes> sorsoltTestek;
	protected List<Kerdes> sorsoltSorbarakos;

	//Általános adatok
	protected int szint=0;
	protected int garantalt;
	protected List<Integer> nyeremenyekSzam;
	protected int eddigiNyeremeny;
	protected boolean gepSegitsegVan;
	protected boolean kozonsegSegitsegVan;
	protected int valasztottNehezseg;

	//Menüelemek:
	protected JFrame menuFrame;
	protected JPanel menuPanel;
	protected JButton newgameButton;
	protected JButton leaderboardButton;
	protected JPanel menuGamemodesPanel;
	protected JRadioButton menuGamemodeClassic;
	protected JRadioButton menuGamemodeOrdering;

	//Játék vége elemek:
	protected JFrame endGameFrame;
	protected long elapsedTime;
	protected long startTime;
	protected long endTime;

	//Közös játékelemek:
	protected DefaultListModel<String> gamePrizeModel;
	protected JList<String> gamePrizeList;
	protected boolean jatekVege;
	protected JPanel helpPanel;
	protected JButton computerHelp;
	protected JButton crowdHelp;
	protected JButton stopping;
	protected String gepSegitett;

	//Csak muszáj:
	protected JLabel gameQuestionLabelTest;
	protected List<JButton> gameAnswerButtonsTest;
	protected JLabel gameQuestionLabelOrdering;
	protected List<JLabel> gameAnswersOrdering;
	protected JPanel gameAnswerPanelTest;
	protected JFrame gameFrameTest;
	protected JPanel gameAnswersPanelOrdering;
	protected JFrame gameFrameOrdering;
	protected JTextField gameAnswerOrdering;
	protected JPanel gameAnswerPanelOrdering;
	protected String input;
	protected Dicsoseglista dicsoseglista;
	protected JFrame scoreBoardFrame;
	protected JFrame nehezsegFrame;
	protected JComboBox<String> nehezsegComboBox;
	protected JButton inditasGomb;
	protected JFrame adatokFrame;


	//Kész
	public GraphicalUI(){
		dicsoseglista=new Dicsoseglista();
		dicsoseglista.beolvas();
		initializeMenu();
		initializeEndGame();
	}

	//Kész
	public void start(List<Kerdes> feladatok1, List<Kerdes> feladatok2) {
		menuFrame.setVisible(true);

		newgameButton.addActionListener(e->{
			menuFrame.setVisible(false);
			nehezsegFrame=new JFrame("Nehézségválasztó");
			nehezsegFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			nehezsegFrame.setSize(400, 200);
			nehezsegFrame.setResizable(false);
			nehezsegFrame.setEnabled(true);
			nehezsegFrame.setLocation(500, 350);
			nehezsegFrame.setAlwaysOnTop(true);

			JPanel nehezsegPanel=new JPanel(new GridLayout(3, 1));
			nehezsegPanel.setVisible(true);
			JLabel nehezsegLabel=new JLabel("Válassz nehézséget!", SwingConstants.CENTER);
			nehezsegLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
			nehezsegPanel.add(nehezsegLabel);
			String[] difficulties = {"1", "2", "3"};
			nehezsegComboBox=new JComboBox<>(difficulties);
			nehezsegComboBox.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
			nehezsegPanel.add(nehezsegComboBox);
	
			inditasGomb=new JButton("Kezdődhet a játék!");
			inditasGomb.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
			nehezsegPanel.add(inditasGomb);

			nehezsegFrame.add(nehezsegPanel);
			nehezsegFrame.setVisible(true);
	
			inditasGomb.addActionListener(ae->{
				String nehezsegString=(String)nehezsegComboBox.getSelectedItem();
				valasztottNehezseg=Integer.parseInt(nehezsegString);
				nehezsegFrame.setVisible(false);
				if (menuGamemodeClassic.isSelected()){
					sorsoltTestek=new ArrayList<>(15);
					sorsoltTestek=Sorsolo.sorsol("Tesztkerdes", valasztottNehezseg, feladatok1);
					initializeTestFrame();
					showQuestionTest(sorsoltTestek.get(szint));
				}else{
					sorsoltSorbarakos=new ArrayList<>(15);
					sorsoltSorbarakos=Sorsolo.sorsol("Sorbarakas", valasztottNehezseg, feladatok2);
					initializeOrderingFrame();
					showQuestionOrdering(sorsoltSorbarakos.get(szint));
				}
			});
		});

		leaderboardButton.addActionListener(e->{
			menuFrame.setVisible(false);
			if (menuGamemodeClassic.isSelected()){
				initializeScoreBoard(dicsoseglista.getTKtop10());
				if (!dicsoseglista.getTKtop10().isEmpty()){
					scoreBoardFrame.setVisible(true);
				}else{
					System.exit(0);
				}
			}else{
				initializeScoreBoard(dicsoseglista.getSRtop10());
				if (!dicsoseglista.getSRtop10().isEmpty()){
					scoreBoardFrame.setVisible(true);
				}else{
					System.exit(0);
				}
			}
		});
	}

	//Kész
	protected void initializeMenu(){
		//Menü:
		menuFrame=new JFrame("Legyen Ön is Milliomos!");
		menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		menuFrame.setSize(1366, 768);
		menuFrame.setResizable(false);
		//Menügombok:
		menuPanel=new JPanel(new GridLayout(3, 1));
		newgameButton=new JButton("Új játék kezdése");
		newgameButton.setHorizontalAlignment(SwingConstants.CENTER);
		newgameButton.setFocusPainted(false);
		newgameButton.setBackground(Color.GREEN);
		newgameButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 65));
		newgameButton.setForeground(Color.BLACK);
		leaderboardButton=new JButton("Dicsőséglista");
		leaderboardButton.setHorizontalAlignment(SwingConstants.CENTER);
		leaderboardButton.setBackground(Color.ORANGE);
		leaderboardButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 65));
		leaderboardButton.setForeground(Color.BLACK);
		//Játékmódok:
		menuGamemodesPanel=new JPanel(new FlowLayout());
		menuGamemodesPanel.setBackground(Color.YELLOW);
		menuGamemodeClassic=new JRadioButton("Klasszikus");
		menuGamemodeClassic.setSelected(true);
		menuGamemodeClassic.setBackground(Color.YELLOW);
		menuGamemodeClassic.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
		menuGamemodeClassic.setForeground(Color.DARK_GRAY);
		menuGamemodeOrdering=new JRadioButton("Sorbarakás");
		menuGamemodeOrdering.setBackground(Color.YELLOW);
		menuGamemodeOrdering.setSelected(false);
		menuGamemodeOrdering.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
		menuGamemodeOrdering.setForeground(Color.DARK_GRAY);
		menuGamemodesPanel.add(menuGamemodeClassic);
		menuGamemodesPanel.add(menuGamemodeOrdering);
		menuGamemodesPanel.setVisible(true);
		//Összerakni a menuPanelt:
		menuPanel.add(newgameButton);
		menuPanel.add(leaderboardButton);
		menuPanel.add(menuGamemodesPanel);
		menuPanel.setVisible(true);
		menuFrame.add(menuPanel);

		menuFrame.setVisible(false);
		menuFrame.setLocation(80, 50);

		//Vegyes dolgok inicializálása
		gamePrizeModel=new DefaultListModel<>();
		gamePrizeList=new JList<>(gamePrizeModel);
		initializePrizeList();
		jatekVege=false;
		gepSegitett="";
		gepSegitsegVan=true;
		kozonsegSegitsegVan=true;

		//ActionListener a Játékmódgomboknak:
		menuGamemodeClassic.addActionListener(e1->{
			menuGamemodeOrdering.setSelected(false);
			menuGamemodeClassic.setSelected(true);
		});
		menuGamemodeOrdering.addActionListener(e2->{
			menuGamemodeClassic.setSelected(false);
			menuGamemodeOrdering.setSelected(true);
		});	
	}

	//Kész
	protected void initializeEndGame(){
		endGameFrame=new JFrame("Játék vége!");
		endGameFrame.setVisible(false);
		endGameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		endGameFrame.setSize(1366, 768);
		endGameFrame.setLocation(80, 50);
		JPanel endPanel=new JPanel(new GridLayout(2, 1));
		endPanel.setVisible(true);
		JLabel endLabel=new JLabel("Remélem tetszett a játék! Szeretnéd elmenteni az eredményedet?", SwingConstants.CENTER);
		endLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		endPanel.add(endLabel);
		JPanel endGombokPanel=new JPanel(new GridLayout(2, 2));
		endGombokPanel.setVisible(true);
		endPanel.add(endGombokPanel);
		JButton endIgen=new JButton("Igen");
		endIgen.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 35));
		endIgen.setFocusPainted(false);
		endIgen.setHorizontalAlignment(SwingConstants.CENTER);
		endIgen.setBackground(Color.BLUE);
		endIgen.setForeground(Color.CYAN);
		endIgen.setBorderPainted(true);
		endIgen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		endGombokPanel.add(endIgen);

		JButton endNem=new JButton("Nem");
		endNem.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 35));
		endNem.setFocusPainted(false);
		endNem.setHorizontalAlignment(SwingConstants.CENTER);
		endNem.setBackground(Color.CYAN);
		endNem.setForeground(Color.BLUE);
		endNem.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		endGombokPanel.add(endNem);

		JTextField nevnekTextField=new JTextField("<Ide írd a neved>");
		nevnekTextField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		nevnekTextField.setHorizontalAlignment(SwingConstants.CENTER);
		endGombokPanel.add(nevnekTextField);
		
		endGameFrame.add(endPanel);

		endIgen.addActionListener(igenae->{
			String name=nevnekTextField.getText().trim();
			name=name.replaceAll("\\s+", " ");
			if (name.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Kérlek írj be egy létező nevet!");
			} else {
				JFrame menteniFrame=new JFrame("Játékos adatai");
				menteniFrame.setVisible(false);
				menteniFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				menteniFrame.setSize(1366, 768);
				menteniFrame.setResizable(false);
				menteniFrame.setLocation(80, 50);

				JPanel menteniPanel=new JPanel(new GridLayout(3, 1));
				menteniPanel.setVisible(true);
				JLabel menteninevLabel=new JLabel("Neved: "+name);
				menteninevLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 65));
				menteninevLabel.setHorizontalAlignment(SwingConstants.CENTER);
				menteninevLabel.setForeground(Color.BLACK);
				menteniPanel.add(menteninevLabel);
				if (eddigiNyeremeny!=0){
					if (menuGamemodeClassic.isSelected() && kozonsegSegitsegVan){
						eddigiNyeremeny=eddigiNyeremeny+500;
					}
					if(gepSegitsegVan){
						eddigiNyeremeny=eddigiNyeremeny+500;
					}
				}
				JLabel menteninyeremenyLabel=new JLabel("Nyereményed: "+eddigiNyeremeny+" Ft");
				menteninyeremenyLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 65));
				menteninyeremenyLabel.setHorizontalAlignment(SwingConstants.CENTER);
				menteninyeremenyLabel.setForeground(Color.DARK_GRAY);
				menteniPanel.add(menteninyeremenyLabel);

				JLabel menteniidoLabel=new JLabel("Játékban töltött idő: "+elapsedTime+" másodperc");
				menteniidoLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 65));
				menteniidoLabel.setHorizontalAlignment(SwingConstants.CENTER);
				menteniidoLabel.setForeground(Color.BLACK);
				menteniPanel.add(menteniidoLabel);
	
				menteniFrame.add(menteniPanel);
				endGameFrame.setVisible(false);
				menteniFrame.setVisible(true);
				menteniFrame.setAlwaysOnTop(true);
				Jatekos player=new Jatekos(name, elapsedTime, eddigiNyeremeny);
				if (menuGamemodeClassic.isSelected()){
					dicsoseglista.ujrekordhozzaad(player, "Tesztkerdes");
				}else{
					dicsoseglista.ujrekordhozzaad(player, "Sorbarakas");
				}
				dicsoseglista.elment();
				menteniFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				Timer svtmr=new Timer(3000, svae->{
					menteniFrame.dispose();
					menteniFrame.setEnabled(false);
					System.exit(0);
				});
				svtmr.setRepeats(false);
				svtmr.start();
			}
		});

		endNem.addActionListener(nemae->{
			adatokFrame=new JFrame("Játékos adatai");
			adatokFrame.setVisible(false);
			adatokFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			adatokFrame.setSize(1366, 768);
			adatokFrame.setResizable(false);
			adatokFrame.setLocation(80, 50);

			JPanel adatokPanel=new JPanel(new GridLayout(2, 1));
			adatokPanel.setVisible(true);
			if (eddigiNyeremeny!=0){
				if (menuGamemodeClassic.isSelected() && kozonsegSegitsegVan){
					eddigiNyeremeny=eddigiNyeremeny+500;
				}
				if(gepSegitsegVan){
					eddigiNyeremeny=eddigiNyeremeny+500;
				}
			}
			JLabel adatoknyeremenyLabel=new JLabel("Nyereményed: "+eddigiNyeremeny+" Ft");
			adatoknyeremenyLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 70));
			adatoknyeremenyLabel.setHorizontalAlignment(SwingConstants.CENTER);
			adatoknyeremenyLabel.setForeground(Color.BLACK);
			adatokPanel.add(adatoknyeremenyLabel);

			JLabel adatokidoLabel=new JLabel("Játékban töltött idő: "+elapsedTime+" másodperc");
			adatokidoLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 70));
			adatokidoLabel.setHorizontalAlignment(SwingConstants.CENTER);
			adatokidoLabel.setForeground(Color.DARK_GRAY);
			adatokPanel.add(adatokidoLabel);
	
			adatokFrame.add(adatokPanel);
			endGameFrame.setVisible(false);
			adatokFrame.setVisible(true);
			adatokFrame.setAlwaysOnTop(true);
			adatokFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			Timer dttmr=new Timer(3000, dtae->{
				adatokFrame.dispose();
				adatokFrame.setEnabled(false);
				System.exit(0);
			});
			dttmr.setRepeats(false);
			dttmr.start();
		});
	}

	//Kész
	public void initializeScoreBoard(List<Jatekos> toplista) {
		scoreBoardFrame=new JFrame("Dicsőséglista");
		scoreBoardFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		scoreBoardFrame.setSize(1366, 768);
		scoreBoardFrame.setLocation(80, 50);
		
		if (toplista.isEmpty()) {
			JOptionPane.showMessageDialog(scoreBoardFrame, "A dicsőséglista jelenleg üres!", "Hiba", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
	
		JPanel scoreboardPanel=new JPanel(new BorderLayout());
		scoreboardPanel.setVisible(true);

		DefaultListModel<String> listModel=new DefaultListModel<>();
		for (int i=0; i<toplista.size(); i++) {
			Jatekos player=toplista.get(i);
			String item=String.format("%d. Név: %s | Nyeremény: %d Ft | Idő: %d másodperc",
					i+1, player.getNev(), player.getNyeremeny(), player.getIdo());
			listModel.addElement(item);
		}
		JList<String> scoreList=new JList<>(listModel);
		scoreList.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 42));
		scoreList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scoreboardPanel.add(scoreList, BorderLayout.CENTER);
	
		scoreBoardFrame.add(scoreboardPanel);
		scoreBoardFrame.setVisible(false);
	}

	//Kész
	protected void initializePrizeList(){
		HashMap<Integer, String> nyeremenyek=new HashMap<>();
		nyeremenyek.put(15, "40 000 000");
		nyeremenyek.put(14, "20 000 000");
		nyeremenyek.put(13, "10 000 000");
		nyeremenyek.put(12, "5 000 000");
		nyeremenyek.put(11, "2 500 000");
		nyeremenyek.put(10, "1 500 000");
		nyeremenyek.put(9, "800 000");
		nyeremenyek.put(8, "500 000");
		nyeremenyek.put(7, "250 000");
		nyeremenyek.put(6, "125 000");
		nyeremenyek.put(5, "100 000");
		nyeremenyek.put(4, "50 000");
		nyeremenyek.put(3, "20 000");
		nyeremenyek.put(2, "10 000");
		nyeremenyek.put(1, "5000");

		nyeremenyekSzam=new ArrayList<>(15);
		nyeremenyekSzam.add(5000);
		nyeremenyekSzam.add(10000);
		nyeremenyekSzam.add(20000);
		nyeremenyekSzam.add(50000);
		nyeremenyekSzam.add(100000);
		nyeremenyekSzam.add(125000);
		nyeremenyekSzam.add(250000);
		nyeremenyekSzam.add(500000);
		nyeremenyekSzam.add(800000);
		nyeremenyekSzam.add(1500000);
		nyeremenyekSzam.add(2500000);
		nyeremenyekSzam.add(5000000);
		nyeremenyekSzam.add(10000000);
		nyeremenyekSzam.add(20000000);
		nyeremenyekSzam.add(40000000);

		for (int i=15; i>0; i--){
			gamePrizeModel.addElement(i+". "+nyeremenyek.get(i)+" Ft");
		}

		gamePrizeList.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		gamePrizeList.setBackground(Color.DARK_GRAY);
		gamePrizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gamePrizeList.setEnabled(false);
		gamePrizeList.setSelectionBackground(Color.YELLOW);
		gamePrizeList.setSelectionForeground(Color.BLACK);
		gamePrizeList.setPreferredSize(new Dimension(135, 0));
	}

	//Kész
	protected void initializeHelpPanel(){
		helpPanel=new JPanel(new GridLayout(3, 1));
		helpPanel.setVisible(true);
		computerHelp=new JButton("Gép segítsége");
		computerHelp.setHorizontalAlignment(SwingConstants.CENTER);
		computerHelp.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
		computerHelp.setFocusPainted(false);
		computerHelp.setBackground(Color.CYAN);
		helpPanel.add(computerHelp);
		
		crowdHelp=new JButton("Közönség segítsége");
		crowdHelp.setHorizontalAlignment(SwingConstants.CENTER);
		crowdHelp.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		crowdHelp.setBackground(Color.CYAN);
		crowdHelp.setFocusPainted(false);
		crowdHelp.setEnabled(true);
		if (menuGamemodeOrdering.isSelected()){
			crowdHelp.setText("");
			crowdHelp.setBackground(Color.GRAY);
			crowdHelp.setEnabled(false);
		}
		helpPanel.add(crowdHelp);
		stopping=new JButton("Megállni");
		stopping.setHorizontalAlignment(SwingConstants.CENTER);
		stopping.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
		stopping.setForeground(Color.WHITE);
		stopping.setBackground(Color.BLACK);
		stopping.setFocusPainted(false);
		helpPanel.add(stopping);

		stopping.addActionListener(stop->{
			if (menuGamemodeOrdering.isSelected()){
				gameFrameOrdering.setAlwaysOnTop(false);
				Sorbarakas jelenlegiSorbarak=(Sorbarakas)sorsoltSorbarakos.get(szint);
				JOptionPane.showMessageDialog(null, "A helyes sorrend: "+jelenlegiSorbarak.getHelyesSorrend());
				gameFrameOrdering.setAlwaysOnTop(true);
			}else{
				Tesztkerdes jelenlegiTesztkerdes=(Tesztkerdes)sorsoltTestek.get(szint);
				for (int iter=0; iter<4; iter++){
					if (gameAnswerButtonsTest.get(iter).getText().startsWith(jelenlegiTesztkerdes.getHelyesBetu())){
						gameAnswerButtonsTest.get(iter).setBackground(Color.YELLOW);
					}
				}
			}
			garantalt=eddigiNyeremeny;
			JFrame megallFrame=new JFrame("Információ");
			megallFrame.setAlwaysOnTop(true);
			megallFrame.setSize(350, 150);
			megallFrame.setResizable(false);
			megallFrame.setLocation(560, 320);
			JPanel megallPanel=new JPanel(new BorderLayout());
			megallPanel.setVisible(true);
			JLabel megallLabel=new JLabel("Úgy döntöttél, hogy megállsz!", SwingConstants.CENTER);
			megallLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
			megallLabel.setVisible(true);
			megallPanel.add(megallLabel, BorderLayout.CENTER);
			megallFrame.add(megallPanel);
			megallFrame.setVisible(true);
			megallFrame.setEnabled(false);
			
			if (menuGamemodeClassic.isSelected()){
				gameFrameTest.setAlwaysOnTop(false);
				gameFrameTest.setEnabled(false);
			}else{
				gameFrameOrdering.setAlwaysOnTop(false);
				gameFrameOrdering.setEnabled(false);
			}

			Timer tmrStop=new Timer(3000, stopae->{
				megallFrame.dispose();
				if (menuGamemodeClassic.isSelected()){
					gameFrameTest.setVisible(false);
				}else{
					gameFrameOrdering.setVisible(false);
				}
				endGameFrame.setVisible(true);
				stopGameTimer();
			});
			tmrStop.setRepeats(false);
			tmrStop.start();
		});
		computerHelp.addActionListener(coH->{
			if (menuGamemodeClassic.isSelected()){
				Tesztkerdes jelenlegiTesztkerdes=(Tesztkerdes)sorsoltTestek.get(szint);
				gepSegitett=Segitsegek.gepSegitseg(jelenlegiTesztkerdes, gameFrameTest);
				if (!gepSegitett.isEmpty()){
					applyComputerHelpTest(jelenlegiTesztkerdes.getHelyesBetu(), gepSegitett);
				}
			}else{
				Sorbarakas jelenlegiSorbarakas=(Sorbarakas)sorsoltSorbarakos.get(szint);
				applyComputerHelpOrdering(jelenlegiSorbarakas, gameFrameOrdering);
			}
		});
		crowdHelp.addActionListener(crH->{
			if (menuGamemodeClassic.isSelected()){
				Tesztkerdes jelenlegiTesztkerdes=(Tesztkerdes)sorsoltTestek.get(szint);
				applyCrowdHelpTest(jelenlegiTesztkerdes, gameFrameTest);
			}else{
				JOptionPane.showMessageDialog(null, "Ez a segítség itt nem használható");
			}
		});
	}

	//Kész
	public void applyComputerHelpOrdering(Sorbarakas jelenlegiSorbarak, JFrame gameFrameOrder){
		gepSegitsegVan=false;
		Segitsegek.gepSegitseg(jelenlegiSorbarak, gameFrameOrder);
	}

	//Kész
	public void applyCrowdHelpTest(Tesztkerdes jelenlegiTest, JFrame gameTestFrame){
		kozonsegSegitsegVan=false;
		Segitsegek.kozonsegSegitseg(jelenlegiTest, gameTestFrame);
	}

	//Kész
	public void applyComputerHelpTest(String correctAnswer, String assistedAnswer) {
		for (int i=0; i<gameAnswerButtonsTest.size(); i++) {
			String buttonAnswer=String.valueOf((char) ('A'+i));
			if (!buttonAnswer.equals(correctAnswer) && !buttonAnswer.equals(assistedAnswer)) {
				gameAnswerButtonsTest.get(i).setEnabled(false);
				gameAnswerButtonsTest.get(i).setBackground(Color.GRAY);
				//gameAnswerButtonsTest.get(i).setForeground(Color.GRAY);
				gameAnswerButtonsTest.get(i).setText("");
			}
		}
		gepSegitsegVan=false;
		gameAnswerPanelTest.revalidate();
		gameAnswerPanelTest.repaint();
	}

	//Kész
	protected void initializeTestFrame(){
		
		gameFrameTest=new JFrame("Legyen Ön is Milliomos!");
		gameFrameTest.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		gameFrameTest.setSize(1366, 768);
		gameFrameTest.setLocation(80, 50);

		JPanel gamePanelTest=new JPanel(new BorderLayout());
		gamePanelTest.setVisible(true);
		gamePanelTest.add(gamePrizeList, BorderLayout.WEST);
		JPanel gameTaskPanelTest=new JPanel(new GridLayout(2, 1));
		gameTaskPanelTest.setVisible(true);
		gamePanelTest.add(gameTaskPanelTest, BorderLayout.CENTER);

		gameQuestionLabelTest=new JLabel("", SwingConstants.CENTER);
		gameQuestionLabelTest.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		gameQuestionLabelTest.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));				
		gameTaskPanelTest.add(gameQuestionLabelTest);

		gameAnswerPanelTest=new JPanel(new GridLayout(2, 2, 5, 10));
		gameAnswerPanelTest.setVisible(true);
		
		gameAnswerButtonsTest=new ArrayList<>(4);
		gameTaskPanelTest.add(gameAnswerPanelTest);

		gamePanelTest.add(gameTaskPanelTest, BorderLayout.CENTER);

		initializeHelpPanel();
		gamePanelTest.add(helpPanel, BorderLayout.EAST);

		gameFrameTest.add(gamePanelTest);
		gameFrameTest.setVisible(true);
	}

	//Kész
	protected void initializeOrderingFrame(){

		gameFrameOrdering=new JFrame("Legyen Ön is milliomos!");
		gameFrameOrdering.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		gameFrameOrdering.setSize(1366, 768);
		gameFrameOrdering.setLocation(80, 50);

		JPanel gamePanelOrdering=new JPanel(new BorderLayout());
		gamePanelOrdering.setVisible(true);
		gamePanelOrdering.add(gamePrizeList, BorderLayout.WEST);
		JPanel gameTaskPanelOrdering=new JPanel(new GridLayout(2, 1));
		gameTaskPanelOrdering.setVisible(true);
		gamePanelOrdering.add(gameTaskPanelOrdering, BorderLayout.CENTER);

		gameQuestionLabelOrdering=new JLabel("", SwingConstants.CENTER);
		gameQuestionLabelOrdering.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 23));
		gameQuestionLabelOrdering.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));	
		gameTaskPanelOrdering.add(gameQuestionLabelOrdering);

		gameAnswerPanelOrdering=new JPanel(new GridLayout(2, 1));
		gameAnswerPanelOrdering.setVisible(true);
		gameAnswersPanelOrdering=new JPanel(new FlowLayout());
		gameAnswersPanelOrdering.setVisible(true);
		gameAnswerPanelOrdering.add(gameAnswersPanelOrdering);
		
		gameAnswersOrdering=new ArrayList<>(4);
		
		gameAnswerOrdering=new JTextField("<Ide írj!>");
		gameAnswerPanelOrdering.add(gameAnswerOrdering);
		initializeJTextField();

		gameTaskPanelOrdering.add(gameAnswerPanelOrdering);
		
		initializeHelpPanel();
		gamePanelOrdering.add(helpPanel, BorderLayout.EAST);

		gameFrameOrdering.add(gamePanelOrdering);
		gameFrameOrdering.setVisible(true);

		
	}

	//Kész
	protected void setNewAnswerButtonsTest(Kerdes qstnTest){
		gameAnswerPanelTest.removeAll();
		gameAnswerButtonsTest.clear();
		for (int k=0; k<4; k++){
			String answerLetter=String.valueOf((char)('A'+k));
			JButton tempButton=new JButton();
			tempButton.setHorizontalAlignment(SwingConstants.LEFT);
			tempButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
			tempButton.setFocusPainted(false);
			tempButton.setBackground(Color.LIGHT_GRAY);
			gameAnswerButtonsTest.add(tempButton);
			gameAnswerPanelTest.add(tempButton);
			tempButton.setText(answerLetter+": "+qstnTest.getValaszok().get(k));
			tempButton.addActionListener(e->{
					if (qstnTest.helyesValasz(answerLetter)) {
						eddigiNyeremeny=nyeremenyekSzam.get(szint);
						if (szint==0 || szint==4 || szint==9 || szint==14){
							garantalt=nyeremenyekSzam.get(szint);
						}
						szint++;
						if (szint==15){
							jatekVege=true;
						}
						tempButton.setBackground(Color.GREEN);

						JFrame helyesesetTest=new JFrame("Helyes válasz");
						helyesesetTest.setResizable(false);
						helyesesetTest.setAlwaysOnTop(true);
						helyesesetTest.setSize(350, 150);
						helyesesetTest.setEnabled(false);
						helyesesetTest.setLocation(560, 320);
						
						JPanel helyespanelTest=new JPanel(new BorderLayout());
						JLabel helyeslabelTest=new JLabel("Helyes válasz!", SwingConstants.CENTER);
						helyeslabelTest.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
						helyespanelTest.add(helyeslabelTest, BorderLayout.CENTER);

						helyesesetTest.add(helyespanelTest);
						helyesesetTest.setVisible(true);

						gameFrameTest.setEnabled(false);
						gameFrameTest.setAlwaysOnTop(false);

						Timer timer1=new Timer(3000, ev1->{
							helyesesetTest.dispose();
							gameFrameTest.setEnabled(true);
							gameFrameTest.setAlwaysOnTop(true);
							if (!jatekVege){
								showQuestionTest(sorsoltTestek.get(szint));
							}else{
								gameFrameTest.setAlwaysOnTop(false);
								stopGameTimer();
								gameFrameTest.setVisible(false);
								endGameFrame.setVisible(true);
							}
						});
						timer1.setRepeats(false);
						timer1.start();
					} else {
						eddigiNyeremeny=garantalt;
						tempButton.setBackground(Color.RED);
						for (int iter=0; iter<4; iter++){
							if (!gameAnswerButtonsTest.get(iter).getText().equals("")
							&& qstnTest.helyesValasz(gameAnswerButtonsTest.get(iter).getText().substring(0, 1))){
								gameAnswerButtonsTest.get(iter).setBackground(Color.GREEN);
							}
						}
						jatekVege=true;

						JFrame helytelenesetTest=new JFrame("Helytelen válasz");
						helytelenesetTest.setResizable(false);
						helytelenesetTest.setAlwaysOnTop(true);
						helytelenesetTest.setSize(400, 200);
						helytelenesetTest.setEnabled(false);
						helytelenesetTest.setLocation(535, 300);
						
						JPanel helytelenpanelTest=new JPanel(new BorderLayout());
						JLabel helytelenlabelTest=new JLabel("Helytelen válasz! A játéknak vége!", SwingConstants.CENTER);
						helytelenlabelTest.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
						helytelenlabelTest.setVisible(true);
						helytelenpanelTest.add(helytelenlabelTest, BorderLayout.CENTER);

						helytelenesetTest.add(helytelenpanelTest);
						helytelenesetTest.setVisible(true);
						gameFrameTest.setEnabled(false);
						gameFrameTest.setAlwaysOnTop(false);

						Timer timer2=new Timer(3000, ev2->{
							helytelenesetTest.dispose();
							gameFrameTest.setVisible(false);
							endGameFrame.setVisible(true);
							stopGameTimer();
						});
						timer2.setRepeats(false);
						timer2.start();
					}
			});
		}
		gameAnswerPanelTest.revalidate();
		gameAnswerPanelTest.repaint();
	}

	//Kész
	protected void setNewAnswerLabelsOrdering(Kerdes qstnOrder){
		gameAnswersPanelOrdering.removeAll();
		gameAnswersOrdering.clear();
		for (int k=0; k<4; k++){
			String answerletter=String.valueOf((char)('A'+k));
			JLabel tempLabel=new JLabel();
			tempLabel.setHorizontalAlignment(SwingConstants.CENTER);
			tempLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
			gameAnswersOrdering.add(tempLabel);
			gameAnswersPanelOrdering.add(tempLabel);
			tempLabel.setText(answerletter+": "+qstnOrder.getValaszok().get(k));
		}
		gameAnswersPanelOrdering.revalidate();
		gameAnswersPanelOrdering.repaint();
	}

	//Kész
	protected void initializeJTextField(){
		gameAnswerPanelOrdering.remove(gameAnswerOrdering);
		gameAnswerOrdering=new JTextField("<Ide írj!>");
		gameAnswerOrdering.setHorizontalAlignment(SwingConstants.CENTER);
		gameAnswerOrdering.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
		gameAnswerOrdering.setBackground(Color.LIGHT_GRAY);
		gameAnswerOrdering.setForeground(Color.BLACK);
		gameAnswerPanelOrdering.add(gameAnswerOrdering);

		gameAnswerOrdering.addActionListener(ae->{
			input=gameAnswerOrdering.getText();
			if (!GUISegedfuggvenyek.joInputTextField(input)){
				gameFrameOrdering.setAlwaysOnTop(false);
				JOptionPane.showMessageDialog(null, "Kérlek adj meg egy érvényes sorrendet!");
				gameFrameOrdering.setAlwaysOnTop(true);
				input=gameAnswerOrdering.getText();
			}else{
				input=input.replace(" ", "");
				input=input.toUpperCase();
				Sorbarakas jelenlegiSorbarakas=(Sorbarakas)sorsoltSorbarakos.get(szint);
				if (input.equals(jelenlegiSorbarakas.getHelyesSorrend())){
					eddigiNyeremeny=nyeremenyekSzam.get(szint);
					if (szint==0 || szint==4 || szint==9 || szint==14){
						garantalt=nyeremenyekSzam.get(szint);
					}
					szint++;
					if (szint==15){
						jatekVege=true;
					}
					gameAnswerOrdering.setBackground(Color.GREEN);
					JFrame helyesesetOrdering=new JFrame("Helyes válasz");
					helyesesetOrdering.setResizable(false);
					helyesesetOrdering.setAlwaysOnTop(true);
					helyesesetOrdering.setSize(350, 150);
					helyesesetOrdering.setEnabled(false);
					helyesesetOrdering.setLocation(560, 320);
					
					JPanel helyespanelOrdering=new JPanel(new BorderLayout());
					JLabel helyeslabelOrdering=new JLabel("Helyes válasz!", SwingConstants.CENTER);
					helyeslabelOrdering.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
					helyespanelOrdering.add(helyeslabelOrdering, BorderLayout.CENTER);

					helyesesetOrdering.add(helyespanelOrdering);
					helyesesetOrdering.setVisible(true);

					gameFrameOrdering.setEnabled(false);
					gameFrameOrdering.setAlwaysOnTop(false);
					
					Timer timer3=new Timer(3000, ev3->{
						helyesesetOrdering.dispose();
						gameFrameOrdering.setEnabled(true);
						gameFrameOrdering.setAlwaysOnTop(true);
						if (!jatekVege){
							showQuestionOrdering(sorsoltSorbarakos.get(szint));
						}else{
							gameFrameOrdering.setAlwaysOnTop(false);
							stopGameTimer();
							gameFrameOrdering.setVisible(false);
							endGameFrame.setVisible(true);
						}
					});
					timer3.setRepeats(false);
					timer3.start();
				}else{
					Sorbarakas jelenlegiSorbarak=(Sorbarakas)sorsoltSorbarakos.get(szint);
					eddigiNyeremeny=garantalt;
					gameAnswerOrdering.setBackground(Color.RED);
					jatekVege=true;

					JFrame helytelenesetOrdering=new JFrame("Helytelen válasz");
					helytelenesetOrdering.setResizable(false);
					helytelenesetOrdering.setAlwaysOnTop(true);
					helytelenesetOrdering.setSize(400, 200);
					helytelenesetOrdering.setEnabled(false);
					helytelenesetOrdering.setLocation(535, 300);
					
					JPanel helytelenpanelOrdering=new JPanel(new BorderLayout());
					JLabel helytelenlabelOrdering=new JLabel("Helytelen válasz! A játéknak vége!", SwingConstants.CENTER);
					helytelenlabelOrdering.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
					helytelenlabelOrdering.setVisible(true);
					helytelenpanelOrdering.add(helytelenlabelOrdering, BorderLayout.CENTER);

					helytelenesetOrdering.add(helytelenpanelOrdering);
					helytelenesetOrdering.setVisible(true);
					gameFrameOrdering.setEnabled(false);
					gameFrameOrdering.setAlwaysOnTop(false);

					Timer timer4=new Timer(3000, ev4->{
						helytelenesetOrdering.dispose();
						JOptionPane.showMessageDialog(null, "A helyes sorrend: "+jelenlegiSorbarak.getHelyesSorrend());
						gameFrameOrdering.setVisible(false);
						endGameFrame.setVisible(true);
						stopGameTimer();
					});
					timer4.setRepeats(false);
					timer4.start();
				}
			}
			gameAnswerPanelOrdering.revalidate();
			gameAnswerPanelOrdering.repaint();
		});
	}

	//Kész
	public void showQuestionTest(Kerdes currentQuestion1) {
		if (szint==0){
			startGameTimer();
		}
		// Kérdés megjelenítése
		gameQuestionLabelTest.setText(currentQuestion1.getKerdes());
		
		//Új helyes és helytelen gombok előállítása
		setNewAnswerButtonsTest(currentQuestion1);

		// Aktuális nyeremény kiemelése
		gamePrizeList.setSelectedIndex(15-szint-1);
	}

	//Kész
	public void showQuestionOrdering(Kerdes currentQuestion2) {
		if (szint==0){
			startGameTimer();
		}

		initializeJTextField();

		// Kérdés megjelenítése
		gameQuestionLabelOrdering.setText(currentQuestion2.getKerdes());
		
		// Válaszok frissítése
		setNewAnswerLabelsOrdering(currentQuestion2);

		// Aktuális nyeremény kiemelése
		gamePrizeList.setSelectedIndex(15-szint-1);
	}

	//Kész
	public void startGameTimer() {
		startTime=System.currentTimeMillis();
	}

	//Kész
    public void stopGameTimer() {
		endTime=System.currentTimeMillis();
		elapsedTime=endTime-startTime;
		showElapsedTime(elapsedTime);
        elapsedTime=elapsedTime/1000;
	}

	//Kész
	public void showElapsedTime(long elapsedTime) {
		long seconds=elapsedTime/1000;
		long minutes=seconds/60;
		seconds=seconds%60;
		JOptionPane.showMessageDialog(null, 
			String.format("A játékidőd: %d perc %d másodperc", minutes, seconds), 
			"Játékidő", JOptionPane.INFORMATION_MESSAGE);
	}

}
