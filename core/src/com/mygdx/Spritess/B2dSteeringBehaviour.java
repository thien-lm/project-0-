package com.mygdx.Spritess;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.tools.SteeringUtils;

public class B2dSteeringBehaviour implements Steerable<Vector2>{

	Body body;
	boolean tagged;
	float boundingRadius;
	float maxLinearSpeed, maxLinearAcceleration;
	float maxAngularSpeed, maxAngularAcceleration;
	
	SteeringBehavior<Vector2> behavior;
	SteeringAcceleration<Vector2> steerOutput;
	
	public B2dSteeringBehaviour(Body body, float boundingRadius) {
		this.body = body;
		this.boundingRadius = boundingRadius;
		this.steerOutput = new SteeringAcceleration<Vector2>(new Vector2()); 
	}
	
	public void update(float delta)
	{
		if(behavior!=null)
		{
			behavior.calculateSteering(steerOutput);
			applySteering(delta);
		}
	}
	public void applySteering(float delta) {
		boolean anyAccelerations = false;
		if(!steerOutput.linear.isZero()) {
			Vector2 force = steerOutput.linear.scl(delta);
			body.applyForceToCenter(force, true);
			anyAccelerations = true;
		}
		if(anyAccelerations) {
			Vector2 velocity = body.getLinearVelocity();
			float currentSpeedSquare = velocity.len2();
		if(currentSpeedSquare > maxLinearSpeed*maxLinearSpeed) {
			body.setLinearVelocity(velocity.scl(maxLinearSpeed/(float)Math.sqrt(currentSpeedSquare)));
		}
		}
	}
	@Override
	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return body.getPosition();
	}

	@Override
	public float getOrientation() {
		// TODO Auto-generated method stub
		return body.getAngle();
	}

	@Override
	public void setOrientation(float orientation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float vectorToAngle(Vector2 vector) {
		// TODO Auto-generated method stub
		return SteeringUtils.vectorToAngle(vector);
	}

	@Override
	public Vector2 angleToVector(Vector2 outVector, float angle) {
		// TODO Auto-generated method stub
		return SteeringUtils.angleToVector(outVector, angle);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Location<Vector2> newLocation() {
		// TODO Auto-generated method stub
		return (Location<Vector2>) new Vector2();
	}

	@Override
	public float getZeroLinearSpeedThreshold() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setZeroLinearSpeedThreshold(float value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getMaxLinearSpeed() {
		// TODO Auto-generated method stub
		return this.maxLinearSpeed;
	}

	@Override
	public void setMaxLinearSpeed(float maxLinearSpeed) {
		// TODO Auto-generated method stub
		this.maxLinearSpeed = maxLinearSpeed;
	}

	@Override
	public float getMaxLinearAcceleration() {
		// TODO Auto-generated method stub
		return this.getMaxLinearAcceleration();
	}

	@Override
	public void setMaxLinearAcceleration(float maxLinearAcceleration) {
		// TODO Auto-generated method stub
		this.maxLinearAcceleration = maxLinearAcceleration;
	}

	@Override
	public float getMaxAngularSpeed() {
		// TODO Auto-generated method stub
		return this.maxAngularSpeed;
	}

	@Override
	public void setMaxAngularSpeed(float maxAngularSpeed) {
		// TODO Auto-generated method stub
		this.maxAngularSpeed = maxAngularSpeed;
	}

	@Override
	public float getMaxAngularAcceleration() {
		// TODO Auto-generated method stub
		return this.maxAngularAcceleration;
	}

	@Override
	public void setMaxAngularAcceleration(float maxAngularAcceleration) {
		// TODO Auto-generated method stub
		this.maxAngularAcceleration = maxAngularAcceleration;
	}

	@Override
	public Vector2 getLinearVelocity() {
		// TODO Auto-generated method stub
		return body.getLinearVelocity();
	}

	@Override
	public float getAngularVelocity() {
		// TODO Auto-generated method stub
		return body.getAngularVelocity();
	}

	@Override
	public float getBoundingRadius() {
		// TODO Auto-generated method stub
		return this.boundingRadius;
	}

	@Override
	public boolean isTagged() {
		// TODO Auto-generated method stub
		return tagged;
	}

	@Override
	public void setTagged(boolean tagged) {
		// TODO Auto-generated method stub
		this.tagged = tagged;
	}
	
	public Body getBody() {
		return body;
	}
	
	public void setBehavior(SteeringBehavior<Vector2> behavior) {
		this.behavior = behavior;
	}
	
	public SteeringBehavior<Vector2> getBehavior(){
		return behavior;
	}
}
