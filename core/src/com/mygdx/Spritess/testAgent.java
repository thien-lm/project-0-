package com.mygdx.Spritess;

import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.box2d.Body;
//import com.badlogic.gdx.physics.box2d.BodyDef;
//import com.badlogic.gdx.physics.box2d.CircleShape;
//import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class testAgent extends BodyPhysic {
	//add object of this class to physic world
	private int counter = 0;

	public testAgent(World world, int x, int y) {
		super(world);
		super.defineAgent(x, y);
	}

	int xRand = 0;
	//agent left-right
	public void setDisturbTypeLR()
	{
		
		if(counter == 60) 
		    {
			xRand =  (int) (Math.random()* ((1 - 0) + 1)) ;
			counter = 0;
			}
			counter++;
		 if(this.xRand % 2 == 1) 
		 {
			left();
			
		 }
		else if(this.xRand%2 == 0) 
		{
			right();
		}
		System.out.println(xRand);
		 
		if(this.b2body.getPosition().y != 476*32/26)
		{
			b2body.setTransform(this.b2body.getPosition().x, 476*32/26+6, 0);
		}
		  
	}
	//agent up-down
	public void setDisturbTypeUD()
	{
		
		if(counter == 60) 
		    {
			xRand =  (int) (Math.random()* ((1 - 0) + 1)) ;
			counter = 0;
			}
			counter++;
		 if(this.xRand % 2 == 1) 
		 {
			up();
			
		 }
		else if(this.xRand%2 == 0) 
		{
			down();
		}
		
		 
		if(this.b2body.getPosition().x != 476*32/26)
		{
			b2body.setTransform(this.b2body.getPosition().x, 476*32/26+6, 0);
		}
		  
	}
	
	private void up()
	{
		  this.b2body.applyLinearImpulse(new Vector2(0, 130f), this.b2body.getWorldCenter(), true);
		  
	}
	
	private void down()
	{
		this.b2body.applyLinearImpulse(new Vector2(0, -13f), this.b2body.getWorldCenter(), true);
	}
	
	private void left()
	{
		this.b2body.applyLinearImpulse(new Vector2(-13f, 0), this.b2body.getWorldCenter(), true);
	}
	
	private void right()
	{
		this.b2body.applyLinearImpulse(new Vector2(130f, 0), this.b2body.getWorldCenter(), true);
	}

}


