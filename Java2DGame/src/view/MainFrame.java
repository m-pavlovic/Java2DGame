package view;

import javax.swing.*;

public class MainFrame extends JFrame {

    GamePanel gamePanel;


    public MainFrame() {
        super("Nintendo Don't Sue Me");
        setSize(1000, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        init();
        layoutComps();
        activateApp();
        pack();
    }


    private void init() {
        gamePanel = new GamePanel();

    }

    private void layoutComps() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(gamePanel);

    }

    private void activateApp() {

    }


}
