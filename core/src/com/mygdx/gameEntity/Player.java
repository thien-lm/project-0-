package  com.mygdx.gameEntity;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Player extends Sprite {
	
	public int posX = 30;
	public int posY = 340;
	public float speed = 0.5f;
	public Rectangle rectangle = new Rectangle(0,0,10,10);
	
//	public Rectangle getRectangle() {
//		return rectangle;
//	}
	public static Texture texture_player;
	public static Sprite sprite_player;
	
	public static void load()
	{
	texture_player = new Texture(Gdx.files.internal("agv.png"));
	texture_player.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	sprite_player = new Sprite(texture_player);
	sprite_player.flip(false, false);
	   
	}
	
	public void updatePos() {
	 if(Gdx.input.isKeyPressed(Input.Keys.W)){
         posY+= speed;
     }
     else if(Gdx.input.isKeyPressed(Input.Keys.S)){
         posY-= speed;
     }

     if(Gdx.input.isKeyPressed(Input.Keys.A)){
         posX-= speed;
     }
     else if(Gdx.input.isKeyPressed(Input.Keys.D)){
         posX+= speed;
     }
	    
	}
	
	
}