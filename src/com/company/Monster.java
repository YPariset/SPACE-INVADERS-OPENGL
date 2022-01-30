package com.company;
import com.company.Objects3D.Color;
import com.company.Objects3D.Cube;
import com.jogamp.opengl.GL2;

public class Monster extends Cube {
	private GLHandler events;
	private float streamX;
	private float streamY;
	private boolean isMoving;
	private boolean isMovingOnRight;
	private final static float SPEED = 0.02f;

	public Monster(float x, float y, float z, float size, boolean isMovingOnRight, GLHandler events)
	{
		super(x, y, z, size);
		this.events = events;
		this.color = new Color(0, 1, 0);
		this.size = 0.05f;

		this.streamY = y;

		if (isMovingOnRight) {
			this.streamX = 2;
		} else {
			this.streamX = -2;
		}

		this.isMoving = true;
		this.isMovingOnRight = isMovingOnRight;
	}

	public void display(GL2 gl)
	{
		super.display(gl);

		if ( this.y <= this.streamY) {
			if ( this.x <= this.streamX) {
				if ( this.isMoving && !this.isMovingOnRight) {
					this.streamY -= 0.5f;
					this.isMoving = false;
				} else {
					this.x += Monster.SPEED;
				}
			} else if ( this.x >= this.streamX) {
				if ( this.isMoving && this.isMovingOnRight) {
					this.streamY -= 0.5f;
					this.isMoving = false;
				} else {
					this.x -= Monster.SPEED;
				}
			}
		} else if ( this.y >= this.streamY) {
			this.y -= Monster.SPEED;

			if (this.y <= y && !this.isMoving) {
				this.streamX = -this.x;
				this.isMoving = true;
				this.isMovingOnRight = !this.isMovingOnRight;
			}
		}

		if (this.y <= -1.7f) {
			System.out.println("Game over !");
			this.events.endGame();
		}
	}
}
