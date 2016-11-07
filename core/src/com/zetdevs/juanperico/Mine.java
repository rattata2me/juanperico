package com.zetdevs.juanperico;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Mine extends Actor{

	TextureRegion tex,face,die;
	
	int w = (int)(Gdx.graphics.getHeight()/5f);
	public Mine(){
		super();
		rect.x = Gdx.graphics.getHeight()/5;
		
		rect.width = w;
		rect.height = w;
		tex = new TextureRegion(new Texture("mine.png"));
		face = new TextureRegion(new Texture("mine-2.png"));
		die = new TextureRegion(new Texture("mine-3.png"));
	}
	
	public void jump(){
		if(jump == false){
			jump = true;
			i = 0;
		}
	}
	
	boolean jump = false;
	int i = 0;
	public void update(){
		if(jump){
			int pos = 0;
			if(i<150)
				pos = (i);
			else
				pos = 150-(i-150);
			rect.y = (pos*2f+(200))*(Gdx.graphics.getHeight()/640f);
			i+=3;
			if(i > 300) jump = false;
		}
	}
	
	int rot = 0;
	int time = 0;
	public boolean died = false;
	@Override
	public void draw(SpriteBatch batch) {
		if(!died){
		if(!jump){
			rot = 0;
			if((((int)(time/10)) % 2) == 1)
				batch.draw(tex, rect.x, rect.y, w/2, w/2, w, w, 1, 1, 0);
			else
				batch.draw(face, rect.x, rect.y, w/2, w/2, w, w, 1, 1, 0);

		}else{
			batch.draw(tex, rect.x, rect.y, w/2, w/2, w, w, 1, 1, rot);
			rot+=3;
		}
		}else{
			batch.draw(die, rect.x, rect.y, w/2, w/2, w, w, 1, 1, rot);
			rot+=3;
		}
		time++;
	}
}
