import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GraphicalEngine implements KeyListener, MouseListener{

    private Canvas gameCanvas = new Canvas();
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    public Infinity game = new Infinity();

    public void start() {
        Dimension gameSize = new Dimension(WIDTH, HEIGHT);
        String gameName = "Platformer";
        JFrame gameWindow = new JFrame(gameName);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setSize(gameSize);
        gameWindow.setResizable(false);
        gameWindow.setVisible(true);
        gameCanvas.setSize(gameSize);
        gameCanvas.setMaximumSize(gameSize);
        gameCanvas.setMinimumSize(gameSize);
        gameCanvas.setPreferredSize(gameSize);
        gameWindow.add(gameCanvas);
        gameWindow.setLocationRelativeTo(null);
        gameCanvas.addKeyListener(this);
        gameCanvas.addMouseListener(this);

        final int FPS = 60;
        final int SKIP_TICKS = 1000 / FPS;
        final int MAX_FRAMESKIP = 5;
        long nextGameTick = System.currentTimeMillis();
        int loops;
        long timeAtLastFPSCheck = 0;
        int ticks = 0;
        boolean running = true;

        game.onCreate();

        while(running) {
            loops = 0;
            while(System.currentTimeMillis() > nextGameTick && loops < MAX_FRAMESKIP) {
                update();
                ticks++;
                nextGameTick += SKIP_TICKS;
                loops++;
            }
            render();
            if(System.currentTimeMillis() - timeAtLastFPSCheck >= 1000) {
                gameWindow.setTitle(gameName + " -FPS: " + ticks);
                ticks = 0;
                timeAtLastFPSCheck = System.currentTimeMillis();
            }
        }
        
    }
    
    private void update() {
    	game.update();
    }
    
    private void render() {
        BufferStrategy buffer = gameCanvas.getBufferStrategy();
        if(buffer == null) {
           gameCanvas.createBufferStrategy(2);
            return;
        }
        Graphics2D graphicObj = (Graphics2D) buffer.getDrawGraphics();
        graphicObj.clearRect(0, 0,gameCanvas.getWidth(),gameCanvas.getHeight());
        game.draw(graphicObj);
        graphicObj.dispose();
        buffer.show();
    }

    public void mousePressed(MouseEvent event) {
        game.mousePressed(event);
    }

    public void mouseClicked(MouseEvent event) {
    }

    public void mouseEntered(MouseEvent event) {
    }

    public void mouseExited(MouseEvent event) {
    }

    public void mouseReleased(MouseEvent event) {
    }

    public void keyPressed(KeyEvent event){
        game.keyPressed(event);
    }

	public void keyReleased(KeyEvent event){}

	public void keyTyped(KeyEvent event){
        game.keyTyped(event);
    }

	public static void main(String[] args){
		GraphicalEngine engine = new GraphicalEngine();
		engine.start();
	}
}
