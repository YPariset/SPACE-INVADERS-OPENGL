package com.company;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserCommand implements KeyListener
{
	
	private GLHandler events;
	
	public UserCommand(GLHandler events)
	{
		this.events = events;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_SPACE)
			this.events.shoot();
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		int key = e.getKeyCode();
		switch( key ) {
			case KeyEvent.VK_LEFT:
				this.events.setMovingOnLeft(true);
				break;
			case KeyEvent.VK_RIGHT :
				this.events.setMovingOnRight(true);
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
			case KeyEvent.VK_LEFT:
				this.events.setMovingOnLeft(false);
				break;
			case KeyEvent.VK_RIGHT:
				this.events.setMovingOnRight(false);
				break;
		}
	}
}