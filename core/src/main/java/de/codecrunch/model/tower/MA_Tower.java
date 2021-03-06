package de.codecrunch.model.tower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

import de.codecrunch.controller.C_Computer;
import de.codecrunch.model.ME_TileState;
import de.codecrunch.model.unit.MA_Unit;

public abstract class MA_Tower {

    protected int xPos;
    protected int yPos;
    protected ModelInstance model;
    protected ME_TowerState state = ME_TowerState.RELOAD;
    protected MA_Unit unitAimingAt;
    private C_Computer owner;
    protected float currentWaitTime = getReloadTime();
    protected LineCoordinates laserLine = new LineCoordinates();

    protected final float reloadTime;
    protected final int damage, price, range;

    public MA_Tower(float preloadTime, int pdamage, int pprice, int prange) {
        reloadTime = preloadTime;
        damage = pdamage;
        price = pprice;
        range = prange;
    }

    public static List<MA_Tower> getAllTowers() {
        List<MA_Tower> list = new ArrayList<>();
        list.addAll(Arrays.asList(new M_SmallTower(), new M_MediumTower(), new M_BigTower(), new M_GatlingTower()));
        return list;
    }

    public void setPos(int x, int y) {
        if (x < 0 || y < 0) return;
        xPos = x;
        yPos = y;
        laserLine.setStart(new Vector3(x * ME_TileState.TILE_DISTANCE, 6f, y * ME_TileState.TILE_DISTANCE));
    }

    public void setOwner(C_Computer computer) {
        owner = computer;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public float getReloadTime() {
        return reloadTime;
    }

    public int getDamage() {
        return damage;
    }

    public int getPrice() {
        return price;
    }

    public int getRange() {
        return range;
    }

    public abstract ModelInstance getModel();

    public void tick(float delta) {
        switch (state) {
            case IDLE:
                break;
            case RELOAD:
                currentWaitTime -= delta;
                if (currentWaitTime <= 0)
                    state = ME_TowerState.SHOOT;
                break;
            case SHOOT:
                if (unitAimingAt != null && (unitAimingAt.isDead()
                        || Math.abs(xPos - unitAimingAt.getXTile()) > getRange()
                        || Math.abs(yPos - unitAimingAt.getYTile()) > getRange()))
                    unitAimingAt = null;

                if (unitAimingAt == null)
                    state = ME_TowerState.IDLE;
                else
                    shoot();
                break;
            case WAITFORBULLET:
                currentWaitTime -= delta;
                setLaserEndOnUnit();
                if (currentWaitTime <= 0) {
                    laserLine.setRender(false);
                    currentWaitTime = getReloadTime();
                    state = ME_TowerState.RELOAD;
                }
                break;
        }
    }

    private void shoot() {
        unitAimingAt.takeDamage(this.getDamage());
        if (unitAimingAt.isDead()){
            owner.addMoney(unitAimingAt.getCost() / 3);
        }
        laserLine.setRender(true);
        setLaserEndOnUnit();
        state = ME_TowerState.WAITFORBULLET;
        currentWaitTime = getReloadTime() / 10;
    }

    private void setLaserEndOnUnit() {
        if (unitAimingAt == null)
            return;
        laserLine.setEnd(unitAimingAt.getModel().transform.getTranslation(laserLine.getEnd()));
    }

    public void onUnitInRange(MA_Unit unit) {
        if (unitAimingAt != null)
            return;
        unitAimingAt = unit;
        if (state == ME_TowerState.IDLE)
            state = ME_TowerState.SHOOT;
    }

    protected enum ME_TowerState {
        IDLE, RELOAD, SHOOT, WAITFORBULLET;
    }

    public LineCoordinates getLaserLine() {
        return laserLine;
    }

    public class LineCoordinates {
        private boolean render = false;
        private Vector3 start = new Vector3();
        private Vector3 end = new Vector3();

        protected void setStart(Vector3 pos) {
            start = pos;
        }

        protected void setEnd(Vector3 pos) {
            end = pos;
        }

        public Vector3 getStart() {
            return start;
        }

        public Vector3 getEnd() {
            return end;
        }

        public boolean isRender() {
            return render;
        }

        public void setRender(boolean render) {
            this.render = render;
        }
    }
}
