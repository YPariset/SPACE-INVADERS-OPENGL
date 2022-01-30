package com.company;
import java.util.ArrayList;

import javax.swing.*;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

public class GLHandler implements GLEventListener
{
	private JFrame frame;
	private FPSAnimator animator;
	private GLU glu;

	private ArrayList<Bullet> bullets;
	private Player player;
	private ArrayList<Monster> monsters;

	private boolean isMovingOnLeft;
	private boolean isMovingOnRight;
	
	public GLHandler(JFrame frame, FPSAnimator animator)
	{
		this.frame = frame;
		this.animator = animator;
		this.glu = new GLU();
		this.monsters = new ArrayList<Monster>();

		float baseX = -2;
		float baseY = 1.8f;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 9; j++) {
				if ( i%2 == 0 ) {
					this.monsters.add(new Monster(baseX + (0.5f * j), baseY - (0.5f * i), -5, 0.2f, true, this));
				} else {
					this.monsters.add(new Monster(-baseX - (0.5f * j), baseY - (0.5f * i), -5, 0.2f, false, this));
				}
			}
		}
		
		this.bullets = new ArrayList<Bullet>();
		
		this.player = new Player(0, -1.8f, -5, 0.18f);
	}

	@Override
	public void init(GLAutoDrawable draw) {
		JOptionPane.showMessageDialog(null, "Ready ? Click OK to start");
	}
	
	@Override
	public void reshape(GLAutoDrawable draw, int x, int y, int width, int height) {
		GL2 gl = draw.getGL().getGL2();

		// SCREEN
		float aspect = (float)width / height;
		gl.glViewport(0, 0, width, height);

		// CAMERA
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(100.0, 300.0, 0.0, 0.0, -1.0, 1.0);

		// FOCAL, ASPECT, Zmin, Zmax
		this.glu.gluPerspective(50.0, aspect, 0.1, 80);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	@Override
	public void display(GLAutoDrawable draw) 
	{
		GL2 gl = draw.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();

		if (this.isMovingOnLeft) {
			this.goLeft();
		}
		
		if (this.isMovingOnRight) {
			this.goRight();
		}

		this.player.display(gl);
		
		for (Monster monster : this.monsters)
			monster.display(gl);
		
		for (int i = 0; i < this.bullets.size(); i++) {
			Bullet bullet = this.bullets.get(i);
			if (bullet.getY() < 2) {
				bullet.display(gl);
			} else {
				this.bullets.remove(i);
				continue;
			}

			for (int j = 0; j < this.monsters.size(); j++) {
				Monster monster = this.monsters.get(j);
				if ((bullet.getX() >= monster.getX() - monster.getSize() && bullet.getX() <= monster.getX() + monster.getSize()) && (bullet.getY() >= monster.getY() - monster.getSize() && bullet.getY() <= monster.getY() + monster.getSize())) {
					this.monsters.remove(j);
				}
			}
		}

		if (this.monsters.size() == 0) {
			System.out.println("Congrats !! You win 🥳");
			this.endGame();
		}
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		if (this.monsters.size() == 0) {
			JOptionPane.showMessageDialog(null, " Congrats !! You win 🥳");
		} else {
			JOptionPane.showMessageDialog(null, " Game Over !");
		}
	}

	public void goRight() {
		this.player.moveOnRight();
	}

	public void goLeft() {
		this.player.moveOnLeft();
	}

	public void shoot() {
		this.bullets.add(new Bullet(this.player.getX(), this.player.getY(), -5, 0.1f));
	}

	public void setMovingOnLeft(boolean isPlayerMovingLeft) {
		this.isMovingOnLeft = isPlayerMovingLeft;
	}

	public void setMovingOnRight(boolean isPlayerMovingRight) {
		this.isMovingOnRight = isPlayerMovingRight;
	}

	public void endGame() {
		this.frame.dispose();
		this.animator.stop();
		System.exit(0);
	}
}
