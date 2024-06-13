package guibasic;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//配列で5つのボールを動かしてみよう

public class MovingBallAWT {
	public static void main(String[] args) {
		FFrame f = new FFrame();
		f.setSize(500, 500);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.show();
	}

	static class FFrame extends Frame implements Runnable {

		Thread th;
		Ball[] balls;

		private boolean enable = true;
		private int counter = 0;

		FFrame() {
			th = new Thread(this);
			th.start();
		}

		public void run() {
			balls = new Ball[5];
			for (int i = 0; i < balls.length; i++) {
				balls[i] = new Ball();
				balls[i].setPosition(120 + i * 70, 150 + i * 50);
				balls[i].setR(15 + i * 5);
				balls[i].setColor(new Color((int) (Math.random() * 0x1000000)));
				balls[i].setSpeed(5 + i * 5); // 速度を設定
			}
			while (enable) {
				try {
					th.sleep(100);
					counter++;
					if (counter >= 200)
						enable = false;
				} catch (InterruptedException e) {
				}

				for (Ball ball : balls) {
					ball.move();
				}
				repaint(); // paint()メソッドが呼び出される
			}
		}

		public void paint(Graphics g) {
			for (Ball ball : balls) {
				ball.draw(g);
			}
		}

		// Ball というインナークラスを作る
		class Ball {
			int x;
			int y;
			int r; // 半径
			Color c = Color.RED;
			int speed =1; // 速度

			int xDir = 1; // 1:+方向 -1: -方向
			int yDir = 1;

			void setColor(Color c) {
				this.c = c;
			}
			void setSpeed(int speed) {
                this.speed = speed;
            }

			void move() {

				if ((xDir == 1) && (x >= 300)) {
					xDir = -1;
				}
				if ((xDir == -1) && (x <= 100)) {
					xDir = 1;
				}

				if (xDir == 1) {
					x = x + speed;
				} else {
					x = x - speed;
				}

				if ((yDir == 1) && (y >= 300)) {
					yDir = -1;
				}
				if ((yDir == -1) && (y <= 100)) {
					yDir = 1;
				}

				if (yDir == 1) {
					y = y + speed;
				} else {
					y = y - speed;
				}

			}

			void setPosition(int x, int y) {
				this.x = x;
				this.y = y;
			}

			void setR(int r) {
				this.r = r;
			}

			void draw(Graphics g) {
				g.setColor(c);
				g.fillOval(x, y, 2 * r, 2 * r); // rは半径なので2倍にする
			}

		}// innner class Ball end

	}

}
