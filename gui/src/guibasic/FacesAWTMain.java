//FacesAWTMain.java
//FacesAWTMain 目標 インナークラスのFaceObjクラスを作ってみよう。描画処理を移譲してください。
//3x3　の顔を描画してください。色などもぬってオリジナルな楽しい顔にしてください。

package guibasic;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FacesAWTMain {

    public static void main(String[] args) {
        new FacesAWTMain();
    }

    FacesAWTMain() {
        FaceFrame f = new FaceFrame();
        f.setSize(800, 800);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setVisible(true);
    }

    // インナークラスを定義
    class FaceFrame extends Frame {

        private FaceObj[] fobjs;

        FaceFrame() {
            fobjs = new FaceObj[9];
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 3; i++) {
                    fobjs[i + 3 * j] = new FaceObj();
                    fobjs[i + 3 * j].setPosition(200 * i + 50, 200 * j + 50);
                    fobjs[i + 3 * j].setEmotionLevel(i, j);
                }
            }
        }

        public void paint(Graphics g) {
            for (FaceObj fobj : fobjs) {
                fobj.drawFace(g);
            }
        }
    }

    private class FaceObj {
        private int xStart;
        private int yStart;
        private int w;
        private int h;
        private Color faceColor;
        private int emotionLevel;

        FaceObj() {
            w = 150;
            h = 150;
            faceColor = new Color((int) (Math.random() * 0x1000000)); // ランダムな顔色
        }

        public void setPosition(int x, int y) {
            xStart = x;
            yStart = y;
        }

        public void setEmotionLevel(int i, int j) {
            emotionLevel = (i + j) % 3; // 位置に基づいて表情を変える
        }

        public void drawFace(Graphics g) {
            g.setColor(faceColor);
            g.fillRoundRect(xStart, yStart, w, h, 40, 40);

            g.setColor(Color.BLACK);
            drawRim(g);
            drawBrow(g);
            drawEye(g);
            drawNose(g);
            drawMouth(g);
        }

        public void drawRim(Graphics g) {
            g.drawRoundRect(xStart, yStart, w, h, 40, 40);
        }

        public void drawBrow(Graphics g) {
            if (emotionLevel == 0) {
                // 通常の眉
                g.drawLine(xStart + 35, yStart + 50, xStart + 65, yStart + 50);
                g.drawLine(xStart + 85, yStart + 50, xStart + 115, yStart + 50);
            } else if (emotionLevel == 1) {
                // 怒っている眉
                g.drawLine(xStart + 35, yStart + 45, xStart + 65, yStart + 55);
                g.drawLine(xStart + 85, yStart + 55, xStart + 115, yStart + 45);
            } else {
                // 驚いた眉
                g.drawLine(xStart + 35, yStart + 55, xStart + 65, yStart + 45);
                g.drawLine(xStart + 85, yStart + 45, xStart + 115, yStart + 55);
            }
        }

        public void drawEye(Graphics g) {
            g.drawOval(xStart + 35, yStart + 65, 20, 20);
            g.drawOval(xStart + 85, yStart + 65, 20, 20);
        }

        public void drawNose(Graphics g) {
            g.drawLine(xStart + 75, yStart + 80, xStart + 75, yStart + 100);
        }

       public void drawMouth(Graphics g) {
            int mouthY = yStart + 120;
            if (emotionLevel == 0) {
                // 通常の口
                g.drawLine(xStart + 60, mouthY, xStart + 90, mouthY);
            } else if (emotionLevel == 1) {
                // 怒っている口
                g.drawLine(xStart + 60, mouthY + 10, xStart + 75, mouthY);
                g.drawLine(xStart + 75, mouthY, xStart + 90, mouthY + 10);
            } else {
                // 驚いた口
                g.drawLine(xStart + 60, mouthY, xStart + 75, mouthY + 10);
                g.drawLine(xStart + 75, mouthY + 10, xStart + 90, mouthY);
            }
        }
    }
}
