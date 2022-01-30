package com.company;
import com.company.Objects3D.Color;
import com.company.Objects3D.Cube;
import com.jogamp.opengl.GL2;


public class Player extends Cube {
	private final static float SPEED = 0.03f;
	
	public Player(float x, float y, float z, float size)
	{
		super(x, y, z, size);
		this.color = new Color(0, 126, 250);
		this.size = 0.08f;
	}
	
	public void display(GL2 gl)
	{
		super.display(gl);
	}
	
	public void moveOnRight() {
		this.x += Player.SPEED;
	}
	
	public void moveOnLeft() {
		this.x -= Player.SPEED;
	}
}
