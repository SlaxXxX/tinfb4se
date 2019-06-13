package de.codecrunch.model.tower;

import com.badlogic.gdx.graphics.g3d.ModelInstance;

import de.codecrunch.Model3DFactory;

public class M_MediumTower extends MA_Tower {

    public M_MediumTower() {
        super(3, 100, 100, 2);
    }

    @Override
    public ModelInstance getModel() {
        if (model == null)
            model = Model3DFactory.instantiate(Model3DFactory.loadModel("towers/mediumTower.g3db"));
        return model;
    }
}
