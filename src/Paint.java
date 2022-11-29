import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/** ART!!!!
 *
 * @author Rami Hage
 * @version 11/16/2022
 */
public class Paint extends JComponent implements Runnable {
    Image image; // the canvas
    Graphics2D graphics2D;  // this will enable drawing
    int curX; // current mouse x coordinate
    int curY; // current mouse y coordinate
    int oldX; // previous mouse x coordinate
    int oldY; // previous mouse y coordinate

    //top
    JButton clrButton;
    JButton fillButton;
    JButton eraseButton;
    JButton randomButton;

    //bottom
    JButton hexButton;
    JButton rgbButton;
    JTextField hexText = new JTextField(10);
    JTextField rText = new JTextField(5);
    JTextField gText = new JTextField(5);
    JTextField bText = new JTextField(5);

    Color background;
    Color pen;
    Paint paint;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clrButton) {
                paint.clear();
                hexText.setText("#");
                rText.setText("");
                gText.setText("");
                bText.setText("");
            }
            if (e.getSource() == fillButton) {
                paint.fill();
                hexText.setText("#");
                rText.setText("");
                gText.setText("");
                bText.setText("");
            }
            if (e.getSource() == eraseButton) {
                paint.erase();
            }
            if (e.getSource() == randomButton) {
                String[] clr = paint.random();
                hexText.setText(clr[0]);
                rText.setText(clr[1]);
                gText.setText(clr[2]);
                bText.setText(clr[3]);
            }
            if (e.getSource() == hexButton) {
                String[] clr = paint.hex(hexText.getText());
                rText.setText(clr[0]);
                gText.setText(clr[1]);
                bText.setText(clr[2]);
            }
            if (e.getSource() == rgbButton) {
                String[] rgbCode = {rText.getText(), gText.getText(), bText.getText()};
                hexText.setText(paint.rgb(rgbCode));
            }

        }
    };
    public void clear() {
        background = Color.white;
        pen = Color.black;
        graphics2D.setPaint(background);
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        graphics2D.setPaint(pen);
        repaint();
    }
    public void fill() {
        background = pen;
        graphics2D.setPaint(background);
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        pen = Color.black;
        graphics2D.setPaint(pen);
        repaint();
    }
    public void erase() {
        graphics2D.setPaint(background);
        repaint();
    }
    public String[] random() {
        Random rng = new Random();
        String[] clr = new String[4];
        pen = new Color(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));
        graphics2D.setPaint(pen);
        clr[0] = String.format("#%02x%02x%02x", pen.getRed(), pen.getGreen(), pen.getBlue());
        clr[1] = "" + pen.getRed();
        clr[2] = "" + pen.getGreen();
        clr[3] = "" + pen.getBlue();
        return clr;
    }
    public String[] hex(String hexCode) {
        String[] clr = new String[3];
        try {
            pen = Color.decode(hexCode);
            graphics2D.setPaint(pen);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Not a valid Hex Value", "Error", JOptionPane.ERROR_MESSAGE);
        }
        clr[0] = "" + pen.getRed();
        clr[1] = "" + pen.getGreen();
        clr[2] = "" + pen.getBlue();
        return clr;
    }
    public String rgb(String[] rgbCode) {
        for (int i = 0; i < rgbCode.length; i++) {
            if (rgbCode[i].equals("")) rgbCode[i] = "0";
        }
        try {
            int r = Integer.parseInt(rgbCode[0]);
            int g = Integer.parseInt(rgbCode[1]);
            int b = Integer.parseInt(rgbCode[2]);
            pen = new Color(r, g, b);
            graphics2D.setPaint(pen);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Not a valid RGB  Value", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return String.format("#%02x%02x%02x", pen.getRed(), pen.getGreen(), pen.getBlue());
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Paint());
    }
    public Paint() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // set oldX and oldY coordinates to beginning mouse press
                oldX = e.getX();
                oldY = e.getY();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // set current coordinates to where mouse is being dragged
                curX = e.getX();
                curY = e.getY();
                // draw the line between old coordinates and new ones
                graphics2D.drawLine(oldX, oldY, curX, curY);
                // refresh frame and reset old coordinates
                repaint();
                oldX = curX;
                oldY = curY;
            }
        });
    }

    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);

            // this lets us draw on the image (ie. the canvas)
            graphics2D = (Graphics2D) image.getGraphics();

            // gives us better rendering quality for the drawing lines
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setStroke(new BasicStroke(5));
            // set canvas to white with default paint color
            background = Color.white;
            pen = Color.black;
            graphics2D.setPaint(background);
            graphics2D.fillRect(0, 0, getSize().width, getSize().height);
            graphics2D.setPaint(pen);
            repaint();
        }
        g.drawImage(image, 0, 0, null);

    }


    @Override
    public void run() {
        JFrame frame = new JFrame("Artwork");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        paint = new Paint();
        content.add(paint, BorderLayout.CENTER);


        JPanel bottomPanel = new JPanel();
        hexText.setText("#");
        rText.setText("");
        gText.setText("");
        bText.setText("");
        hexButton = new JButton("Hex");
        hexButton.addActionListener(actionListener);
        rgbButton = new JButton("RGB");
        rgbButton.addActionListener(actionListener);

        bottomPanel.add(hexText);
        bottomPanel.add(hexButton);
        bottomPanel.add(rText);
        bottomPanel.add(gText);
        bottomPanel.add(bText);
        bottomPanel.add(rgbButton);
        content.add(bottomPanel, BorderLayout.SOUTH);

        JPanel topPanel = new JPanel();
        clrButton = new JButton("Clear");
        clrButton.addActionListener(actionListener);
        fillButton = new JButton("Fill");
        fillButton.addActionListener(actionListener);
        eraseButton = new JButton("Erase");
        eraseButton.addActionListener(actionListener);
        randomButton = new JButton("Random");
        randomButton.addActionListener(actionListener);

        topPanel.add(clrButton);
        topPanel.add(fillButton);
        topPanel.add(eraseButton);
        topPanel.add(randomButton);
        content.add(topPanel, BorderLayout.NORTH);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

}

