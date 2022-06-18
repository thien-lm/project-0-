package com.mygdx.Spritess;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Queue;
import com.mygdx.forAstar.City;
import com.mygdx.forAstar.CityGraph;

public class testAGV extends BodyPhysic {

	// re-define world and b2body make bugs here
	// public World world;
	// public Body b2body;
	// WHY?

	public float PosX = 0, PosY = 0;
	testMainPlayer testMain;

	CityGraph cityGraph;
	float deltaX = 0;
	float deltaY = 0;
	public boolean reachend = false;
	City previousCity;
	Queue<City> pathQueue = new Queue<>();

	public static int count = 0;
	public static boolean canRender = true;

	public testAGV(World world, CityGraph cityGraph, City start, testMainPlayer testMain) {
		super(world);
		this.cityGraph = cityGraph;
		this.PosX = start.x * 32 / 26;
		this.PosY = start.y * 32 / 26;
		this.previousCity = start;
		// this.world = world;

		this.testMain = testMain;
		super.defineAgent(PosX, PosY);
	}

	private void render() {
		if (canRender == true) {
			b2body.setTransform(PosX, PosY, 0);
			b2body.setAwake(true);
		}
	}

	public void move() {
		step();
		render();
	}

	private void step() {
		if (Vector2.dst(PosX, PosY, testMain.b2body.getPosition().x, testMain.b2body.getPosition().y) <= 14 + 13)
			canRender = false;
		else
			canRender = true;
		if (canRender == true) {
			PosX += deltaX;
			PosY += deltaY;
			checkCollision();
		}
	}

	private void checkCollision() {
		if (pathQueue.size > 0) {

			City targetCity = pathQueue.first();

			if (Vector2.dst(PosX, PosY, targetCity.x * 32 / 26, targetCity.y * 32 / 26) <= 4) {
				deltaX = 0;
				deltaY = 0;
				this.b2body.setLinearVelocity(0f, 0f);
				reachNextCity();
			}
		}
	}

	private void reachNextCity() {

		City nextCity = pathQueue.first();

		// Set the position to keep the Agent in the middle of the path

		this.PosX = nextCity.x * 32 / 26;
		this.PosY = nextCity.y * 32 / 26;

		this.previousCity = nextCity;
		pathQueue.removeFirst();

		if (pathQueue.size == 0) {
			deltaX = 0;
			deltaY = 0;
			this.b2body.setLinearVelocity(0f, 0f);
			reachDestination();
		} else {
			deltaX = 0;
			deltaY = 0;
			this.b2body.setLinearVelocity(0f, 0f);
			setSpeedToNextCity();
		}
	}

	@SuppressWarnings("static-access")
	private void reachDestination() {
		reachend = true;
		this.count++;
		world.destroyBody(b2body);
		System.out.println("you reached the goal");
		// setGoal(newGoal);
	}

	public void setGoal(City goal) {
		GraphPath<City> graphPath = cityGraph.findPath(previousCity, goal);
		for (int i = 1; i < graphPath.getCount(); i++) {
			pathQueue.addLast(graphPath.get(i));
		}
		setSpeedToNextCity();
	}

	private void setSpeedToNextCity() {
		City nextCity = pathQueue.first();
		float angle = MathUtils.atan2(nextCity.y * 32 / 26 - previousCity.y * 32 / 26,
				nextCity.x * 32 / 26 - previousCity.x * 32 / 26);
		deltaX = MathUtils.cos(angle) * speed;
		deltaY = MathUtils.sin(angle) * speed;
		System.out.println(deltaX + " and " + deltaY);
	}

}
