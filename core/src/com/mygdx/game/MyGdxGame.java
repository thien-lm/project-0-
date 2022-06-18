package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.gamestuff.GameScreen;

public class MyGdxGame extends Game {
	
	public GameScreen gameScreen;
	Music music;

	@Override
	public void create() {
        gameScreen = new GameScreen(this);
		setScreen(gameScreen);
		music = Gdx.audio.newMusic(Gdx.files.internal("backgroundsound.mp3"));
		music.play();
		music.setVolume(10);
		music.setLooping(true);
	}

}
