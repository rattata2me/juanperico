package com.zetdevs.juanperico;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.nio.file.SensitivityWatchEventModifier;

public class Menu extends Game{

	SpriteBatch batch;
	Texture tex;
	Sound loop;
	boolean done = false;
	boolean started = false;
	@Override
	public void create() {
		setScreen(new MenuScreen(this));
	}
	@Override
	public void render() {
		super.render();
	}
}
