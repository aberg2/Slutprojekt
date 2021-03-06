import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.LinkedList;

/**
 * Created 2021-04-27
 *
 * @author
 */
public class Main {
    public static void main(String[] args) {
        // Här startas ditt program
        // Design av framen
        final MyPanel panel = new MyPanel();
        final JFrame frame = new JFrame("Vecka.nu");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("src/a.png").getImage());
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Delar av framen

        JLabel vecka = new JLabel();

        LocalDate datum = LocalDate.now();
        vecka.setPreferredSize(new Dimension(350, 350));
        vecka.setFont(new Font("Arial, serif", Font.BOLD, 260));
        vecka.setForeground(new Color(28, 123, 183));

        int veckanu = datum.get(ChronoField.ALIGNED_WEEK_OF_YEAR) - 1; //ibland behövs -1 och ibland inte??

        vecka.setText(String.valueOf(veckanu));
        panel.add(vecka);
        frame.add(panel);
        // frame.add(vecka);
        panel.getContentPane();
        frame.pack();
        frame.setVisible(true);
    }

    public static class MyPanel extends JPanel {
        private final LinkedList<grafik> stars;
        private final grafik gubben;
        private int xv, xy;

        public MyPanel() {
            stars = new LinkedList<>();
            gubben = new grafik(xv, xy);
            xv = 1;
            xy = 1;
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyChar() == 'd') ;
                    xv = +10;
                }
            });
            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    stars.add(new grafik(e.getX() - 20, e.getY() - 20));
                    MyPanel.this.repaint();
                }
            });
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent k) {
                    stars.add(new grafik(k.getX() - 10, k.getY() - 10));
                    MyPanel.this.repaint();
                }
            });

        }

        // Funkar typ men väldigt lång delay
        @Override
        public void paintComponent(final Graphics g) {
            super.paintComponent(g);
            stars.forEach((e) -> e.staren(g));
            try {
                gubben.gubben(g);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public Dimension getPreferredSize() {
            return new Dimension(400, 400);
        }

        public void getContentPane() {
            setBackground(new Color(144, 192, 222));
        }

    }

}
