package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen implements Screen {
    final Drop game;
    private Texture backgroundTexture;
    private OrthographicCamera camera;
    private BitmapFont font;
    private SpriteBatch batch;
    private int score;

    public GameOverScreen(final Drop game, int score) {
        this.game = game;
        this.score = score;

        // Load background texture
        backgroundTexture = new Texture(Gdx.files.internal("gameover_background.png"));

        // Setup camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // Setup font
        font = new BitmapFont();

        // Setup sprite batch
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Set the camera's projection matrix
        batch.setProjectionMatrix(camera.combined);

        // Begin batch
        batch.begin();

        // Draw background scaled to fit the screen
        batch.draw(backgroundTexture, 0, 0, 800, 480);

        /// Draw text
        font.draw(batch, "Game Over", 350, 300);
        font.draw(batch, "Score: " + score, 350, 250);
        font.draw(batch, "Tap to restart", 350, 200);

        // End batch
        batch.end();

        // Check for tap input to restart game
        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        font.dispose();
        batch.dispose();
    }
}
