package com.mygdx.Spritess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class BodyPhysic {
	
	public World world;
	public Body b2body;
	public int speed = 1;
	
	public BodyPhysic() {
	}
	
	public BodyPhysic(World world) {
        this.world = world;
	}
	
	
	public BodyPhysic(World world, int x, int y) 
	{
		this.world = world;
		
	}

	public void defineBody() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(30, 448);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);

		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(14);

		fdef.shape = shape;
		b2body.createFixture(fdef);
		shape.dispose();
	}
	
	public void defineAgent(float posX, float posY) {
		BodyDef bdef = new BodyDef();
		bdef.position.set(posX, posY);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);

		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(14);

		fdef.shape = shape;
		b2body.createFixture(fdef);
		shape.dispose();
	}
	
	public void HandleInput() {

		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			if (this.b2body.getLinearVelocity().y <= 99)

				this.b2body.applyLinearImpulse(new Vector2(0, 10f), this.b2body.getWorldCenter(), true);

		} else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			if (this.b2body.getLinearVelocity().y >= -99)

				this.b2body.applyLinearImpulse(new Vector2(0, -10f), this.b2body.getWorldCenter(), true);
		}

		else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			if (this.b2body.getLinearVelocity().x >= -99)

				this.b2body.applyLinearImpulse(new Vector2(-10f, 0), this.b2body.getWorldCenter(), true);
		}

		else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			if (this.b2body.getLinearVelocity().x <= 99)

				this.b2body.applyLinearImpulse(new Vector2(10f, 0), this.b2body.getWorldCenter(), true);
		} else {
			this.b2body.setLinearVelocity(0f, 0f);
		}

	}


}
