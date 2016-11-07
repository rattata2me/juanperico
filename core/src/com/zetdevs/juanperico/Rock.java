package com.zetdevs.juanperico;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Rock extends Actor{

	TextureRegion tex;
	
	
	
	public boolean done = false;
	public Rock(TextureRegion tex) {
		super();
		rect.height = Gdx.graphics.getHeight()/5;
		rect.width = Gdx.graphics.getHeight()/5;
		this.tex = tex;
	}
	
	public void draw(SpriteBatch batch, int x) {
		batch.draw(tex, (rect.x -x), rect.y, rect.width, rect.height);
	}
}
