package com.company;
import java.awt.Dimension;

import javax.swing.*;

import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class Main
{

	private static String TITLE = "Space Invaders";  // window's title
	private static final int CANVAS_WIDTH = 800;  // width of the drawable
	private static final int CANVAS_HEIGHT = 480; // height of the drawable
	private static final int FPS = 60; // animator's target frames per second
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		GLCanvas canvas = new GLCanvas();
		FPSAnimator fpsAnimator = new FPSAnimator(canvas, FPS);
		GLHandler events = new GLHandler(frame, fpsAnimator);

		canvas.addKeyListener(new UserCommand(events));
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		canvas.addGLEventListener(events);

		frame.getContentPane().add(canvas);
		frame.setTitle(TITLE);
		frame.pack();
		frame.setVisible(true);

		fpsAnimator.start();
	}

}

