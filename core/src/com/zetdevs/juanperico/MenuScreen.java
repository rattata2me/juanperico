package com.zetdevs.juanperico;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zetdevs.juanperico.Actor.button;

public class MenuScreen implements Screen{

	SpriteBatch batch;
	Texture tex, tit;
	Music loop;
	boolean done = false;
	boolean started = false;
	Menu men;
	Preferences prefs;
	BitmapFont bf;
	public MenuScreen(Menu men) {
		prefs = Gdx.app.getPreferences("pref");
		this.men = men;
		 if(prefs.contains("hs")){
			 hs = prefs.getInteger("hs");
		 }
	}
	button play, hiscore,trash, back;
	int hs = 0;
	@Override
	public void show() {
		 if(prefs.contains("hs")){
			 hs = prefs.getInteger("hs");
		 }
		bf = new BitmapFont(Gdx.files.internal("bph.fnt"));
		tex = new Texture("title.png");
		tit = new Texture("aent.png");
		play = new button("button.png");
		play.rect.set(Gdx.graphics.getWidth()*0.6f, 
				Gdx.graphics.getHeight()*0.4f, Gdx.graphics.getHeight()*0.4f, Gdx.graphics.getHeight()*0.2f);
		hiscore = new button("play.png");
		hiscore.rect.set(Gdx.graphics.getWidth()*0.6f, 
				Gdx.graphics.getHeight()*0.25f, Gdx.graphics.getHeight()*0.4f, Gdx.graphics.getHeight()*0.2f);
		trash = new button("trash.png");
		trash.rect.set(Gdx.graphics.getWidth()*0.6f, 
				Gdx.graphics.getHeight()*0.10f, Gdx.graphics.getHeight()*0.15f, Gdx.graphics.getHeight()*0.15f);
		back = new button("back.png");
		back.rect.set(Gdx.graphics.getWidth()*0.8f, 
				Gdx.graphics.getHeight()*0.10f, Gdx.graphics.getHeight()*0.15f, Gdx.graphics.getHeight()*0.15f);
		batch = new SpriteBatch();
		loop = Gdx.audio.newMusic(Gdx.files.internal("loop.mp3"));
		loop.setLooping(true);
		loop.play();
	}
	boolean hscored = false;
	@Override
	public void render(float delta) {
		if(!started){
			batch.begin();
			batch.draw(tex, 0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			batch.draw(tit, Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
			play.draw(batch);
			if(!hscored){
				
				hiscore.draw(batch);
			}else{
				trash.draw(batch);
				back.draw(batch);
				bf.draw(batch, "Mejor Score: "+ Integer.toString(hs), Gdx.graphics.getWidth()*0.6f, Gdx.graphics.getHeight()*0.35f);
			}
			batch.end();
		}
		if(started && !done){
			
			done = true;
			loop.stop();
			men.setScreen(new MyGdxGame(men));
		}
		if(play.isTouched()) started = true;
		
		if(hscored){
			if(back.isTouched()) hscored = false;
			if(trash.isTouched()){
				prefs.putInteger("hs", 0);
				prefs.flush();
				hs = 0;
				hscored = false;
			}
		}else{
			if(hiscore.isTouched()) hscored = true;
		}
		
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
		
	}

}
