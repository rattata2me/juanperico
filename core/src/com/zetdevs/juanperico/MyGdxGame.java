package com.zetdevs.juanperico;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.zetdevs.juanperico.Actor.buttonhom;

public class MyGdxGame implements Screen {
	SpriteBatch batch;
	Texture sky,has, sky2;
	TextureRegion rockt;
	BitmapFont bf;
	List<Rock> rocks = new ArrayList<Rock>();
	Mine mine;
	Sound loop, ost;
	buttonhom hom;
	Menu men;
	Preferences prefs;
	int hs = 0;
	public MyGdxGame(Menu men) {
		this.men = men;
		 prefs = Gdx.app.getPreferences("pref");
		 if(prefs.contains("hs")){
			 hs = prefs.getInteger("hs");
		 }
		 
	}
	
	@Override
	public void show () {
		batch = new SpriteBatch();
		mine = new Mine();
		rockt = new TextureRegion(new Texture("rock.png"));
		sky = new Texture("sky.png");
		sky2 = new Texture("sky2.png");
		has = new Texture("hasperd.png");
		bf = new BitmapFont(Gdx.files.internal("bph.fnt"));
		loop = Gdx.audio.newSound(Gdx.files.internal("loop.mp3"));
		loop.loop();
		ost = Gdx.audio.newSound(Gdx.files.internal("punch.mp3"));

		hom = new buttonhom();
		hom.rect.set(Gdx.graphics.getWidth()-mine.rect.height*1f, 
				Gdx.graphics.getHeight()-mine.rect.height*1f, mine.rect.height*0.8f, mine.rect.height*0.8f);
	}

	int px = 0;
	
	int time = 0;
	
	int rtime = 0;
	
	int puntos = 0;
	
	boolean yulus = false;
	@Override
	public void render (float d) {
		int width = Gdx.graphics.getWidth();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
	
		
		//OIE mi cuerpo pide fondiko
		batch.draw(sky2, -(px/2) % width, 0, width, Gdx.graphics.getHeight());
		batch.draw(sky2, -(px/2) % width+width, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(sky, -px % width, 0, width, Gdx.graphics.getHeight());
		batch.draw(sky, -px % width+width, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		mine.died = yulus;
		mine.draw(batch);
		
		for(Rock rock : rocks.toArray(new Rock[rocks.size()])){
			rock.draw(batch, px);
			Rectangle r = new Rectangle(rock.rect);
			r.x -= px;
			if(r.x < 20){
				if(!rock.done)puntos++;
				rock.done = true;
			}
			if(r.x <-20){
				rocks.remove(rock);
			}
			if(mine.rect.overlaps(r)){
				if(hs < puntos && yulus != true){
					prefs.putInteger("hs", puntos);
					prefs.flush();
				}
				yulus = true;
				
			}
		}
		
		bf.draw(batch, "Puntos: "+ Integer.toString(puntos), 50, 50);
		
		
		if(yulus){
			batch.draw(has, 0, 0, width, Gdx.graphics.getHeight());
			
		}
		
		
		hom.draw(batch);
		
		batch.end();
		
		if(time > rtime){
			Rock r = new Rock(rockt);
			r.rect.x = px+width;
			rocks.add(r);
			rtime = (new Random()).nextInt(200)+100;
			time = 0;
		}
		
		if(!yulus){
			time++;
			px += Gdx.graphics.getWidth()/155;
			mine.update();
			if(Gdx.input.isTouched()){
				if(!mine.jump)ost.play();
				mine.jump();
				
			}
		}else{
			if(Gdx.input.isTouched()){
				rocks.clear();
				px = 0;
				time = 0;
				puntos = 0;
				yulus = false;
			}
		}
		if(hom.isTouched()){
			if(!fi){
				fi = true;
				prefs.putInteger("hs", puntos);
				prefs.flush();
				loop.stop();
				men.setScreen(new MenuScreen(men));
			}
		}
	}
	boolean fi = false;
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
		
	}
}
