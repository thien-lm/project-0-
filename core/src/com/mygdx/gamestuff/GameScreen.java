package com.mygdx.gamestuff;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.Spritess.testAgent;
import com.mygdx.Spritess.testMainPlayer;
import com.mygdx.Spritess.testAGV;
import com.mygdx.forAstar.City;
import com.mygdx.forAstar.CityGraph;
import com.mygdx.forAstar.Street;
import com.mygdx.game.MyGdxGame;
import com.mygdx.tools.B2WorldCreator;


public class GameScreen implements Screen {
    //basic element
	MyGdxGame mygame;
	OrthographicCamera camera;
	SpriteBatch batch;
	TileMap tileMap;
	//agents and player
	testAGV test;
	testAGV test2;
	testMainPlayer testMain;
	testAgent testA;
	testAgent testB;
    //physics world
	private World world;
	private Box2DDebugRenderer b2dr;
    //graph
	public  CityGraph cityGraph;
	public GraphPath<City> cityPath;
	public GraphPath<City> cityPath2;
	public  ShapeRenderer shapeRenderer;
	public  BitmapFont font;
	public int currentRank = 0;
	

	public GameScreen(MyGdxGame mygame) {
		
		this.mygame = mygame;
		this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(false, 1670, 900);//TRUE = VE CHUAN, FALSE = VE NGUOC
		
		batch = new SpriteBatch();
		tileMap = new TileMap();
		tileMap.init();
		
		world = new World(new Vector2(0, 0), true);
		b2dr = new Box2DDebugRenderer(
				/*drawBodies*/         true,
                /*drawJoints*/         false,
                /*drawAABBs*/          false,
                /*drawInactiveBodies*/ false,
                /*drawVelocities*/     false,
                /*drawContacts*/       true) ;
        new B2WorldCreator(world, tileMap);
       // client.sendData("ping".getBytes());
        testMain = new testMainPlayer(world);
		testA = new testAgent(world,900, 476*32/26+6);
		testB = new testAgent(world,300, 476*32/26+6);
		
		shapeRenderer = new ShapeRenderer();
	    batch = new SpriteBatch();
	    font = new BitmapFont();
        cityGraph = new CityGraph();
  
		createNode();
		
	}

	@Override
	public void render(float delta) {
	
		world.step(1 / 60f, 6, 2);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//draw cities
		for (Street street : cityGraph.streets) {
			street.render(shapeRenderer);
		}
		
		for (City city : cityGraph.cities) {
			city.render(shapeRenderer, batch, font, false);// Draw all cities blue
		}

		for (City city : cityPath) {
			city.render(shapeRenderer, batch, font, true);// Draw cities in path green
		}
		
		camera.update();
		tileMap.renderer.setView(camera);
		tileMap.renderer.render();
		cityPath.get(2).render(shapeRenderer, batch, font, true);
	    
		b2dr.render(world, camera.combined);
        testMain.HandleInput();
     
       
		
		
		// Rendering code
        batch.begin();
    	
		
		testA.setDisturbTypeLR();
		testB.setDisturbTypeLR();

		test.move();
		if(test.reachend == true) 
		{
		test2.move();
		currentRank = testAGV.count;
		}
		font.draw(batch, "your current rank is " + currentRank , 1150, 690);
	
		batch.end();
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

		tileMap.map.dispose();
		world.dispose();
		b2dr.dispose();
	}
	
	public float caly(int i)
	{
		float scale = 26;
		return i*scale - scale/2;
	}
	
	public float calx(int i)
	{
		float scale = 26;
		return i*scale + scale/2;
	}
	public void createNode()
	{   
		City startCity = new City(calx(0), caly(15), "S");
		City bCity = new City(calx(0), caly(14), "B");
		City aCity = new City(200, 350, "A");
		City cCity = new City(400, caly(13), "C");
		City dCity = new City(200, 250, "D");
		City fCity = new City(100, 250, "F");
		City eCity = new City(calx(4), caly(15), "E");
		City hCity = new City(calx(4), caly(14), "H");
		City gCity = new City(200, 150, "G");
		City iCity = new City(200, 50, "I");
		City jCity = new City(300, 50, "J");
		City kCity = new City(calx(14), caly(19), "K");
		City goalCity = new City(calx(4), caly(19), "Z");

		cityGraph.addCity(startCity);
		cityGraph.addCity(bCity);
		cityGraph.addCity(aCity);
		cityGraph.addCity(cCity);
		cityGraph.addCity(dCity);
		cityGraph.addCity(fCity);
		cityGraph.addCity(eCity);
		cityGraph.addCity(hCity);
		cityGraph.addCity(gCity);
		cityGraph.addCity(iCity);
		cityGraph.addCity(jCity);
		cityGraph.addCity(kCity);
		cityGraph.addCity(goalCity);

		cityGraph.connectCities(startCity, bCity);
		cityGraph.connectCities(bCity, aCity);
		cityGraph.connectCities(bCity, cCity);
		cityGraph.connectCities(startCity, dCity);
		cityGraph.connectCities(dCity, fCity);
		cityGraph.connectCities(startCity, hCity);
		cityGraph.connectCities(hCity, gCity);
		cityGraph.connectCities(gCity, iCity);
		cityGraph.connectCities(iCity, jCity);
		cityGraph.connectCities(jCity, kCity);
		cityGraph.connectCities(kCity, goalCity);
		cityGraph.connectCities(startCity, eCity);
		cityGraph.connectCities(eCity, goalCity);
		cityGraph.connectCities(startCity, cCity);
		cityGraph.connectCities(goalCity, kCity);
		cityGraph.connectCities(kCity, eCity);
		cityGraph.connectCities(bCity, eCity);
		
        goalCity = kCity;
		cityPath = cityGraph.findPath(startCity, goalCity);
		test = new testAGV(world, cityGraph, startCity,this.testMain);
		test.setGoal(goalCity);
		//cityPath.clear();
		
		startCity = bCity;
		goalCity = kCity;
		cityPath2 = cityGraph.findPath(startCity, goalCity);
		test2 = new testAGV(world, cityGraph, startCity,this.testMain);
	    test2.setGoal(goalCity);
			

	}

}
