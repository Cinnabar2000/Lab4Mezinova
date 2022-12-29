import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JImageDisplay extends JComponent {
    private BufferedImage buffImage;
    private Graphics g;

    public JImageDisplay(){
       this(100, 100);
    }
    public JImageDisplay(int width, int height){
        buffImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        g=buffImage.getGraphics();
        setPreferredSize(new Dimension(width,height));
    }
    @Override
    protected void paintComponent (Graphics g){
        super.paintComponent(g);
        g.drawImage(buffImage, 0,0, buffImage.getWidth(), buffImage.getHeight(), null);

    }
    public void clearImage(){
        //g.setColor(Color.black);
        //g.fillRect(0,0,buffImage.getWidth(), buffImage.getHeight());
        g.clearRect(0,0,buffImage.getWidth(), buffImage.getHeight());
        this.repaint();
    }
    public void drawPixel(int x, int y, Color rgbColor){
        g.setColor(rgbColor);
        g.fillRect(x, y, 1, 1);
        this.repaint();
    }
}
