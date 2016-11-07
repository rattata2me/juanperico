package com.zetdevs.juanperico;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Actor {

	Rectangle rect = new Rectangle();
	
	public Actor() {
		rect.y = 200*(Gdx.graphics.getHeight()/640f);
	}
	
	
	public void draw(SpriteBatch batch){
	}
	
	
	public static class buttonhom extends Actor{
		
		Texture tex;
		public buttonhom() {
			tex = new Texture("home.png");
		}
		@Override
		public void draw(SpriteBatch batch) {
			batch.draw(tex, (rect.x), rect.y, rect.width, rect.height);
		}
		public boolean isTouched(){
			return (Gdx.input.isTouched() && rect.contains(Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY()));
		}
	}
	public static class button extends Actor{
		
		Texture tex;
		public button(String text) {
			tex = new Texture(text);
		}
		@Override
		public void draw(SpriteBatch batch) {
			batch.draw(tex, (rect.x), rect.y, rect.width, rect.height);
		}
		public boolean isTouched(){
			return (Gdx.input.isTouched() && rect.contains(Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY()));
		}
	}
}
