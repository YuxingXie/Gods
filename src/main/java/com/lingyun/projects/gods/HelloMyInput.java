package com.lingyun.projects.gods;


import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 * Sample 5 - how to map keys and mouse buttons to actions
 */

public class HelloMyInput extends SimpleApplication {

    public static void main(String[] args) {
        HelloMyInput app = new HelloMyInput();
        app.start();
    }

    private Geometry player;
    private Boolean isRunning = true;

    @Override
    public void simpleInitApp() {
        Box b = new Box(1, 1, 1);
        player = new Geometry("Player", b);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        player.setMaterial(mat);
        rootNode.attachChild(player);
        flyCam.setEnabled(false);//不禁用flyCam，W A S D键设置会出问题
        
        initKeys(); // load my custom keybinding
    }

    /**
     * w a s d 四个键控制方向.
     */
    private void initKeys() {
        /* You can map one or several inputs to one named mapping. */
        inputManager.addMapping("Forward", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Backward", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
//        inputManager.addMapping("Rotate", new KeyTrigger(KeyInput.KEY_SPACE),new MouseButtonTrigger(MouseInput.BUTTON_LEFT));//鼠标左键转动
        /* Add the named mappings to the action listeners. */
//        inputManager.addListener(actionListener, "Rotate");
        inputManager.addListener(analogListener, "Forward", "Backward", "Left", "Right");

    }

    /** Use this listener for KeyDown/KeyUp events */
//    final private ActionListener actionListener = new ActionListener() {
//        @Override
//        public void onAction(String name, boolean keyPressed, float tpf) {
//            if (name.equals("Pause") && !keyPressed) {
//                isRunning = !isRunning;
//            }
//        }
//    };

    /**
     * Use this listener for continuous events（连续事件）
     */
    final private AnalogListener analogListener = new AnalogListener() {
        @Override
        public void onAnalog(String name, float value, float tpf) {

            if (name.equals("Forward")) {
                player.move((new Vector3f(0, 0, value)));
            }
            if (name.equals("Backward")) {
                player.move((new Vector3f(0, 0, -value)));
            }
            if (name.equals("Left")) {
                System.out.println("move left");
                player.move(new Vector3f(-value, 0, 0));
            }
            if (name.equals("Right")) {
                System.out.println("move right");
                player.move(new Vector3f(value, 0, 0));
            }
            System.out.println(player.getLocalTranslation().x+" , "+player.getLocalTranslation().y+" , "+player.getLocalTranslation().z);

        }
    };
}
