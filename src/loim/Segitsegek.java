package loim;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class Segitsegek {
    private static boolean gepSegitsegVan=true;
    private static boolean kozonsegSegitsegVan=true;
    private static Random r=new Random();
    
    public static String gepSegitseg(Tesztkerdes tk1, JFrame gameTestFrame){
        if (gepSegitsegVan){
            gepSegitsegVan=false;
            String betuk="ABCD";
            int rand=Math.abs(r.nextInt()%4);
            while (tk1.helyesValasz(betuk.substring(rand, rand+1))){
                rand=Math.abs(r.nextInt()%4);
            }
            return betuk.substring(rand, rand+1); 
        }else{
            JFrame nincsgepFrameTest=new JFrame("Hiba!");
            nincsgepFrameTest.setSize(750, 200);
            JPanel nincsgepPanelTest=new JPanel(new BorderLayout());
            JLabel nincsgepLabelTest=new JLabel("Ezt a segítséget már elhasználtad, ezért ez a művelet nem végezhető el!", SwingConstants.CENTER);
            nincsgepLabelTest.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            nincsgepPanelTest.add(nincsgepLabelTest, BorderLayout.CENTER);
            nincsgepFrameTest.add(nincsgepPanelTest);
            nincsgepPanelTest.setVisible(true);
            nincsgepFrameTest.setAlwaysOnTop(true);
            nincsgepFrameTest.setResizable(false);
            nincsgepFrameTest.setVisible(true);
            nincsgepFrameTest.setEnabled(false);
            nincsgepFrameTest.setAlwaysOnTop(true);
            gameTestFrame.setEnabled(false);
            nincsgepFrameTest.setLocation(360, 293);
            Timer tmr1=new Timer(2500, event1->{
                nincsgepFrameTest.dispose();
                gameTestFrame.setEnabled(true);
                gameTestFrame.setAlwaysOnTop(true);
            });
            tmr1.setRepeats(false);
            tmr1.start();
            return "";
        }
    }

    public static void gepSegitseg(Sorbarakas sr, JFrame gameFrameOrder){
        if (gepSegitsegVan){
            gepSegitsegVan=false;
            gameFrameOrder.setAlwaysOnTop(false);
            JOptionPane.showMessageDialog(null, "A helyes sorrend első 2 betűje: "+sr.getHelyesSorrend().substring(0, 2));
            gameFrameOrder.setAlwaysOnTop(true);
        }else{
            JFrame nincsgepFrameOrdering=new JFrame("Hiba!");
            nincsgepFrameOrdering.setSize(750, 200);
            JPanel nincsgepPanelOrdering=new JPanel(new BorderLayout());
            nincsgepPanelOrdering.setVisible(true);
            JLabel nincsgepLabelOrdering=new JLabel("Ezt a segítséget már elhasználtad, ezért ez a művelet nem végezhető el!", SwingConstants.CENTER);
            nincsgepLabelOrdering.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            nincsgepPanelOrdering.add(nincsgepLabelOrdering, BorderLayout.CENTER);
            nincsgepFrameOrdering.add(nincsgepPanelOrdering);
            nincsgepFrameOrdering.setAlwaysOnTop(true);
            nincsgepFrameOrdering.setResizable(false);
            nincsgepFrameOrdering.setVisible(true);
            nincsgepFrameOrdering.setEnabled(false);
            nincsgepFrameOrdering.setAlwaysOnTop(true);
            gameFrameOrder.setEnabled(false);
            nincsgepFrameOrdering.setLocation(360, 293);
            Timer tmr2=new Timer(2500, event2->{
                nincsgepFrameOrdering.dispose();
                gameFrameOrder.setEnabled(true);
            });
            tmr2.setRepeats(false);
            tmr2.start();
        }
    }

    public static void kozonsegSegitseg(Tesztkerdes tk2, JFrame gameTestFrame){
        if (kozonsegSegitsegVan){
            kozonsegSegitsegVan=false;
            List<Integer> randomok=new ArrayList<>(4);
            int rand0=r.nextInt(35, 44);
            int rand1=r.nextInt(15, 26);
            int rand2=r.nextInt(22, 26);
            randomok.add(rand0);
            randomok.add(rand1);
            randomok.add(rand2);
            randomok.add(100-rand0-rand1-rand2);
            int p=1;
            JFrame kozonsegFrame=new JFrame("Közönség segítsége");
            kozonsegFrame.setSize(300, 200);
            JPanel kozonsegPanelFo=new JPanel(new GridLayout(2,1));
            kozonsegPanelFo.setVisible(true);
            JPanel kozonsegPanel=new JPanel(new FlowLayout());
            kozonsegPanel.setVisible(true);
            kozonsegPanelFo.add(kozonsegPanel);
            kozonsegFrame.add(kozonsegPanelFo);
            for (int i=0; i<4; i++){
                JLabel valseg=new JLabel();
                if (tk2.getHelyesBetu().equals(String.valueOf((char)('A'+i)))){
                    valseg.setText((char)('A'+i)+": "+String.valueOf(randomok.get(0))+"%");
                }
                else{
                    valseg.setText((char)('A'+i)+": "+String.valueOf(randomok.get(p))+"%");
                    p++;
                }
                valseg.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
                kozonsegPanel.add(valseg);
            }
            JButton okButton=new JButton("OK");
            kozonsegPanelFo.add(okButton);
            kozonsegFrame.setAlwaysOnTop(true);
            kozonsegFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            kozonsegFrame.setVisible(true);
            gameTestFrame.setEnabled(false);
            kozonsegFrame.setLocation(587, 300);
            okButton.addActionListener(ae->{
                kozonsegFrame.dispose();
                gameTestFrame.setEnabled(true);
                gameTestFrame.setAlwaysOnTop(true);
            });
        }else{
            JFrame nincskozonsegFrame=new JFrame("Hiba!");
            nincskozonsegFrame.setSize(750, 200);
            JPanel nincskozonsegPanel=new JPanel(new BorderLayout());
            nincskozonsegPanel.setVisible(true);
            JLabel nincskozonsegLabel=new JLabel("Ezt a segítséget már elhasználtad, ezért ez a művelet nem végezhető el!", SwingConstants.CENTER);
            nincskozonsegLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            nincskozonsegPanel.add(nincskozonsegLabel, BorderLayout.CENTER);
            nincskozonsegFrame.add(nincskozonsegPanel);
            nincskozonsegFrame.setAlwaysOnTop(true);
            nincskozonsegFrame.setResizable(false);
            nincskozonsegFrame.setVisible(true);
            nincskozonsegFrame.setEnabled(false);
            nincskozonsegFrame.setAlwaysOnTop(true);
            gameTestFrame.setEnabled(false);
            nincskozonsegFrame.setLocation(360, 293);
            Timer tmr3=new Timer(2500, event3->{
                nincskozonsegFrame.dispose();
                gameTestFrame.setEnabled(true);
            });
            tmr3.setRepeats(false);
            tmr3.start();
        }
    }
    
}
