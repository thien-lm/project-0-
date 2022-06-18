package com.mygdx.Spritess;

import com.badlogic.gdx.physics.box2d.World;

public class testMainPlayer extends BodyPhysic{
	//re-define world and b2body make bugs here
	//public World world;
	//public Body b2body;
	public int speed = 1;
	
	public testMainPlayer(World world) {
       super(world);
       super.defineBody();
	}
	//để đây kẻo bọn m éo hiểu
	public void HandleInput() {
    super.HandleInput();
	}

	

}
