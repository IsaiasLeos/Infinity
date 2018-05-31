import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
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
        String gameName = "Infinity";
        JFrame gameWindow = new JFrame(gameName);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameCanvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        gameCanvas.addKeyListener(this);
        gameCanvas.addMouseListener(this);
        gameCanvas.setFocusable(true);
        gameCanvas.requestFocus();
        gameWindow.add(gameCanvas);
        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        boolean running = true;
        
        game.onCreate();
        gameWindow.setVisible(true);
        
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                update();
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                gameWindow.setTitle(gameName + " FPS: " + frames);
                timer += 1000;
                frames = 0;
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

    public void keyReleased(KeyEvent event){
        game.keyReleased(event);
    }

    public void keyTyped(KeyEvent event){
        game.keyTyped(event);
    }

    public static void main(String[] args){
        GraphicalEngine engine = new GraphicalEngine();
        engine.start();
    }
}