package br.com.m3Tech.geradorCnab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class Scroller extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Scroller() throws HeadlessException {
		
		final JPanel panel2 = new JPanel();
		panel2.setSize(new Dimension(800, 600));
		panel2.setLayout(new BorderLayout());
		
        final JPanel panel = new JPanel();
//        panel.setBorder(BorderFactory.createLineBorder(Color.red));
        panel.setPreferredSize(new Dimension(800, 600));
        panel.setBounds(0, 0, 1000, 1000);
        
        panel.setLayout(null);

        final JScrollPane scroll = new JScrollPane(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
        
        panel2.add(scroll, BorderLayout.CENTER);
        
        add(panel2);
        setSize(1000, 1000);
        setVisible(true);
    }

    public static void main(final String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Scroller().setVisible(true);
            }
        });
    }
}
