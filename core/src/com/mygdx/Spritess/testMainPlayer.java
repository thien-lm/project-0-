package com.mygdx.Spritess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
//import com.badlogic.gdx.utils.Queue;
//import com.mygdx.forAstar.City;
//import com.mygdx.forAstar.CityGraph;

public class testMainPlayer extends BodyPhysic{
	
	public World world;
	public Body b2body;
	public int speed = 1;
	
	public testMainPlayer(World world) {
        super(world);
	}
	
	public void HandleInput() 
	{
		super.HandleInput();
	}
	
}
