package dev.bkrk;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.Instant;

public class Window extends JFrame implements Runnable {
    public boolean isRunning;
    public static Window window = null;
    public int currentState;
    public Scene currentScene;

    public KL keyListener = new KL();
    public ML mouseListener = new ML();

    public Window(int width, int height, String title) {
        setSize(width, height);
        setTitle(title);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(keyListener);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);

        isRunning = true;
        changeState(0);
    }

    public static Window getWindow() {
        if (Window.window == null) {
            Window.window = new Window(Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT, Constant.SCREEN_TITLE);
        }
        return Window.window;
    }

    public void close() {
        isRunning = false;
    }

    public void changeState(int newState) {
        currentState = newState;
        switch (currentState) {
            case 0:
                currentScene = new MenuScene(keyListener, mouseListener);
                break;
            case 1:
                currentScene = new GameScene(keyListener);
                break;
            default:
                System.out.println("Unknown Scene.");
                currentScene = null;
                break;
        }
    }

    public void update(double dt) {
        Image dbImage = createImage(getWidth(), getHeight()); // dbImage --> double buffer image
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        getGraphics().drawImage(dbImage, 0, 0, this);

        currentScene.update(dt);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        currentScene.draw(g);
    }

    @Override
    public void run() {
        Instant lastFrameTime = Instant.now();
        try {
            while (isRunning) {
                Instant time = Instant.now();
                double deltaTime = Duration.between(lastFrameTime, time).toNanos() * 10E-10;
                lastFrameTime = Instant.now();

                double deltaWanted = 0.0167;
                update(deltaWanted);
                long msToSleep = (long)((deltaWanted - deltaTime) * 1000);
                if (msToSleep > 0) {
                    Thread.sleep(msToSleep);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        this.dispose();
    }
}
