package de.codecrunch.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

import de.codecrunch.TowerAttackGame;
import de.codecrunch.controller.C_Game;
import de.codecrunch.model.M_RenderBatch;

public class V_Game extends VA_Screen {

	private final float CAM_DISTANCE = 26.3f;//26.3
	private final float CAM_LOWER = 20.3f;//20.3
	private final float CAM_UPPER = 169.7f;//169.7
	private final float MAP_MIDDLE = 40f;//40
	private final float MAP_BRIGHTNESS = 1f;

	private C_Game controller;

	private PerspectiveCamera camera;
	private SpriteBatch hudBatch = new SpriteBatch();
	V_HUD v_hud = new V_HUD(hudBatch);
	private M_RenderBatch mapBatch = new M_RenderBatch();
	private M_RenderBatch towerBatch = new M_RenderBatch();
	private Environment environment = new Environment();
	private PointLight mouseLight = new PointLight().set(1f, 1f, 1f, 30f, 1f, 40f, 100f);

	private float cameraMotion = 0f;

	public V_Game(TowerAttackGame game) {
		super(game);

	}

	public void setup() {

		camera = new PerspectiveCamera(
				90,
				Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());

		camera.direction.set(0.0001f, -1f, 0f);
		camera.position.set(CAM_LOWER, CAM_DISTANCE, MAP_MIDDLE);
		camera.near = 0.1f;
		camera.far = 120.0f;
		camera.update();

		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, MAP_BRIGHTNESS, MAP_BRIGHTNESS, MAP_BRIGHTNESS, 1.0f));
		//environment.add(mouseLight);
	}

	public void setController(C_Game game) {
		controller = game;
	}

	public M_RenderBatch getMapBatch() {
		return mapBatch;
	}
	
	public M_RenderBatch getTowerBatch() {
		return towerBatch;
	}

	@Override
	public void dispose() {
		mapBatch.dispose();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(120, 120, 120, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		camera.translate(cameraMotion, 0, 0);
		limitCamera();
		if (cameraMotion != 0)
			cameraMotion *= 0.95f;
		camera.update();
		mapBatch.begin(camera);
		mapBatch.renderAll(environment);
		mapBatch.end();
		towerBatch.begin(camera);
		towerBatch.renderAll(environment);
		towerBatch.end();
		hudBatch.setProjectionMatrix(v_hud.stage.getCamera().combined);
		v_hud.stage.draw();

	}

	private boolean limitCamera() {
		if (camera.position.x > CAM_UPPER) {
			camera.position.set(CAM_UPPER, camera.position.y, camera.position.z);
			cameraMotion = 0f;
			return false;
		}
		if (camera.position.x < CAM_LOWER) {
			camera.position.set(CAM_LOWER, camera.position.y, camera.position.z);
			cameraMotion = 0f;
			return false;
		}
		return true;
	}

	public class GameInputProcessor implements InputProcessor {

		@Override
		public boolean keyDown(int keycode) {
			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			return false;
		}

		@Override
		public boolean keyTyped(char character) {
			return false;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			cameraMotion = Gdx.input.getDeltaY() * 0.088f;
			return true;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			Vector3 tmpVector = new Vector3();
			Ray ray = camera.getPickRay(screenX, screenY);
			final float distance = -ray.origin.y / ray.direction.y - 1f;
			tmpVector.set(ray.direction).scl(distance).add(ray.origin);
			mouseLight.setPosition(tmpVector);
			return true;
		}

		@Override
		public boolean scrolled(int amount) {
			cameraMotion -= amount * 0.5f;
			return true;
		}

	}
}
