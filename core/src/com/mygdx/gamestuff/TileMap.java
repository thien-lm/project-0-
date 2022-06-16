package com.mygdx.gamestuff;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TileMap {
	
	 TmxMapLoader maploader;
	 public TiledMap map;
	 OrthogonalTiledMapRenderer renderer;
	 
	 public void init()
	 {
		 maploader = new TmxMapLoader();
	     map = maploader.load("map.tmx");
	     renderer = new OrthogonalTiledMapRenderer(map);
	 }

}