package de.codecrunch.view;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import de.codecrunch.TowerAttackGame;
import de.codecrunch.controller.C_LevelSelect;
import de.codecrunch.model.M_Map;

public abstract class VA_LevelSelect extends VA_Screen {

    C_LevelSelect controller = new C_LevelSelect();

    public VA_LevelSelect(TowerAttackGame game) {
        super(game);
        controller.setup(this);
    }

    public void addLevelButtons(Table table) {
        TextButton up = new TextButton("^", uiSkin);
        TextButton down = new TextButton("v", uiSkin);
        int rowcnt = 0;

        table.add(up).colspan(2);
        table.row();

        for (TextButton level : controller.getLevelButtons(uiSkin)) {
            table.add(level).fillX().uniformX();
            level.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    controller.selected(level.getText().toString());
                }
            });
            rowcnt++;
            if (rowcnt == 2) {
                table.row();
            }
        }
        table.row();
        table.add(down).colspan(2);
        table.row();

        up.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.move(C_LevelSelect.UP);
            }
        });

        down.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.move(C_LevelSelect.DOWN);
            }
        });
    }

    @Override
    public void show(){
        controller.updateButtons();
        super.render(0);
    }

    public abstract void startMap(M_Map map, String level);
}
