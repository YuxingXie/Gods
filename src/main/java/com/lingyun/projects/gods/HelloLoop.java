package com.lingyun.projects.gods;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/** Sample 4 - how to trigger repeating actions from the main event loop.
 * In this example, you use the loop to make the player character
 * rotate continuously. */
public class HelloLoop extends SimpleApplication {

    public static void main(String[] args){
        HelloLoop app = new HelloLoop();
        app.start();
    }

    private Geometry player;

    @Override
    public void simpleInitApp() {
        /** this blue box is our player character */
        Box b = new Box(1, 1, 1);
        player = new Geometry("blue cube", b);
        Material mat = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");//并不能控制player，它只是个简单的几何体空间对象
        mat.setColor("Color", ColorRGBA.Blue);
        player.setMaterial(mat);
        rootNode.attachChild(player);
    }

    /* Use the main event loop to trigger repeating actions. */
    @Override
    public void simpleUpdate(float tpf) {//fpf:每帧时间
        // make the player rotate:
        player.rotate(0, 2*tpf, 0);
//        player.rotate(2*tpf,0, 0);//配合上一句代码这会让盒子斜着转
        beat(tpf);
//        move(tpf);

    }
    //沿z轴远离
    private void move(float tpf) {
        player.move(0,0,-0.03f);
    }

    /**
     * 心跳效果，目前收缩得太快^_^
     * @param tpf
     */
    private void beat(float tpf) {
        Integer big = player.getUserData("big");
        if (big==null){
            big=0;
        }

        if (big<10){
            player.scale(1.05f);
            System.out.println("放大++++++++++++++++++++++++++++++++++++++");
        }else {
            player.scale((float) Math.pow(1/1.05f,2));
            System.out.println("缩小----------------------------------");
        }
        big++;
        if (big==15) big=0;
        player.setUserData("big",big);
    }
}
