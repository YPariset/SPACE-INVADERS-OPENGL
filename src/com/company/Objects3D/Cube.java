package com.company.Objects3D;
import com.jogamp.opengl.GL2;

public class Cube extends Shape
{
	protected Color color;
	private double angle;
	
	public Cube(float x, float y, float z, float size)
	{
		this.x = x; this.y = y; this.z = z;
		this.size = size;
		this.color = new Color(1, 1, 1);
		this.angle = 0;
	}
	
	public void display(GL2 gl)
	{
		gl.glPushMatrix();
		gl.glTranslatef(x, y, z);
		gl.glScalef(size, size, size);
		gl.glRotated(angle, 1, 1, 1);
		gl.glBegin(GL2.GL_QUADS);
		// Front
		gl.glColor3d(color.getRed(), color.getGreen(), color.getBlue());
		gl.glVertex3d(-1, -1, 1);
		gl.glVertex3d(1, -1, 1);
		gl.glVertex3d(1, 1, 1);
		gl.glVertex3d(-1, 1, 1);
		// Rear
		gl.glColor3d(color.getRed(), color.getGreen(), color.getBlue());
		gl.glVertex3d(-1, -1, -1);
		gl.glVertex3d(1, -1, -1);
		gl.glVertex3d(1, 1, -1);
		gl.glVertex3d(-1, 1, -1);
		// Left
		gl.glColor3d(color.getRed(), color.getGreen(), color.getBlue());
		gl.glVertex3d(-1, -1, -1);
		gl.glVertex3d(-1, -1, 1);
		gl.glVertex3d(-1, 1,  1);
		gl.glVertex3d(-1, 1, -1);
		// Right
		gl.glColor3d(color.getRed(), color.getGreen(), color.getBlue());
		gl.glVertex3d(1, -1, -1);
		gl.glVertex3d(1, -1, 1);
		gl.glVertex3d(1, 1,  1);
		gl.glVertex3d(1, 1, -1);
		// Bottom
		gl.glColor3d(color.getRed(), color.getGreen(), color.getBlue());
		gl.glVertex3d(-1, -1, 1);
		gl.glVertex3d(1, -1, 1);
		gl.glVertex3d(1, -1, -1);
		gl.glVertex3d(-1, -1, -1);
		// Up
		gl.glColor3d(color.getRed(), color.getGreen(), color.getBlue());
		gl.glVertex3d(-1, 1, 1);
		gl.glVertex3d(1, 1, 1);
		gl.glVertex3d(1, 1, -1);
		gl.glVertex3d(-1, 1, -1);
		gl.glEnd();	
		gl.glPopMatrix();
		
		this.angle += 0.2;
	}
}
