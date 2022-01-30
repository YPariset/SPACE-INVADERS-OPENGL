package com.company.Objects3D;
import com.jogamp.opengl.GL2;

public class Face extends Shape
{

	@Override
	public void display(GL2 gl) 
	{
		gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(-1, -1, 0);
			gl.glVertex3d(1, -1, 0);
			gl.glVertex3d(1, 1, 0);
			gl.glVertex3d(-1, 1, 0);
		gl.glEnd();
	}
	

}
