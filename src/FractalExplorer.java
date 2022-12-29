import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

public class FractalExplorer {
    private int width;
    private int heigth;
    private JImageDisplay image;
    private Rectangle2D.Double range;
    private JFrame frame;
    private JButton button;
    private Mandelbrot mandelbrot;

    public FractalExplorer() {
        this(800);
    }

    public FractalExplorer(int size) {
        this(size, size);
    }

    public FractalExplorer(int width, int heigth) {
        this.width = width;
        this.heigth = heigth;
        this.range = new Rectangle2D.Double();
        this.mandelbrot = new Mandelbrot();
        mandelbrot.getInitialRange(range);
    }

    private class resetButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            //image.clearImage();
            mandelbrot.getInitialRange(range);
            FractalExplorer.this.drawFractal();
        }
    }

    private class mouseClickListener implements MouseListener {
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, image.getWidth(), x);
            double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, image.getHeight(), y);

            if (e.getButton() == MouseEvent.BUTTON1) {
                mandelbrot.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            }
            if (e.getButton() == MouseEvent.BUTTON3) {
                mandelbrot.recenterAndZoomRange(range, xCoord, yCoord, 1.5);
            }
            FractalExplorer.this.drawFractal();
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {

        }
    }

    public void createAndShowGUI() {
        this.frame = new JFrame("Fractal Generator");
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setSize(this.width, this.heigth);


        this.image = new JImageDisplay(heigth, width);
        frame.getContentPane().add(BorderLayout.CENTER, this.image);
        image.addMouseListener(new mouseClickListener());


        this.button = new JButton("Reset Display");
        frame.getContentPane().add(BorderLayout.SOUTH, this.button);
        button.addActionListener(new resetButtonListener());

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void drawFractal() {
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.heigth; y++) {
                double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, image.getWidth(), x);
                double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, image.getHeight(), y);
                int iterations = mandelbrot.numIterations(xCoord, yCoord);
                int rgbColor;
                if (iterations != -1) {
                    float hue = 0.7f + (float) iterations / 200f;
                    rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                } else {
                    rgbColor = Color.HSBtoRGB(0, 0, 0);

                }
                image.drawPixel(x, y, new Color(rgbColor));
            }
        }


    }

    public static void main(String[] args) {
        FractalExplorer fractal = new FractalExplorer(800);
        fractal.createAndShowGUI();
        fractal.drawFractal();

    }

}
