package com.company;
import com.company.Objects3D.Cube;
import com.jogamp.opengl.GL2;


public class Bullet extends Cube {
	private final static float SPEED = 0.08f;
	
	public Bullet(float x, float y, float z, float size)
	{
		super(x, y, z, size);
		this.size = 0.05f;
	}
	
	public void display(GL2 gl)
	{
		super.display(gl);
		
		this.y += Bullet.SPEED;
	}
}
