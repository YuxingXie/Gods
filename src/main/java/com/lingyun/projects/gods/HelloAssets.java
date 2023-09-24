package com.lingyun.projects.gods;


import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

/** Sample 3 - how to load an OBJ model, and OgreXML model,
 * a material/texture, or text.
 * https://wiki.jmonkeyengine.org/docs/3.4/tutorials/beginner/hello_asset.html
 *
 * */
public class HelloAssets extends SimpleApplication {

    public static void main(String[] args) {
        HelloAssets app = new HelloAssets();
        app.start();
    }

    @Override
    public void simpleInitApp() {

        /* Load a teapot model (OBJ file from jme3-testdata) */
        Spatial teapot = assetManager.loadModel("Models/Teapot/Teapot.obj");
        Material mat_default = new Material( assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");
        teapot.setMaterial(mat_default);
        teapot.setLocalTranslation(2.0f,2.5f,0.0f);
        rootNode.attachChild(teapot);

        /* Create a wall (Box with material and texture from jme3-testdata) */
        Box box = new Box(2.5f,2.5f,1.0f);
        Spatial wall = new Geometry("Box", box );
        Material mat_brick = new Material( assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_brick.setTexture("ColorMap", assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));//纹理
//        mat_brick.setTexture("ColorMap", assetManager.loadTexture("Textures/ninja.jpg"));//纹理
        wall.setMaterial(mat_brick);
//        wall.setLocalTranslation(2.0f,-2.5f,0.0f);
        wall.setLocalTranslation(2.0f,0,0.0f);
        rootNode.attachChild(wall);

        /* Display a line of text (default font from jme3-testdata) */
        setDisplayStatView(false);//停用HUD显示统计信息
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");//BitmapFont类型的一种jme字体
        BitmapText helloText = new BitmapText(guiFont);
        helloText.setSize(guiFont.getCharSet().getRenderedSize());
        helloText.setText("Hello World");
        helloText.setLocalTranslation(300, helloText.getLineHeight(), 0);
        guiNode.attachChild(helloText);

        /* Load a Ninja model (OgreXML + material + texture from test_data) */
        Spatial ninja = assetManager.loadModel("Models/Ninja/Ninja.mesh.xml");
        ninja.scale(0.05f, 0.05f, 0.05f);
        ninja.rotate(0.0f, -3.0f, 0.0f);
        ninja.setLocalTranslation(0.0f, 0, -2.0f);
        rootNode.attachChild(ninja);
        /* You must add a light to make the model visible. */
        DirectionalLight sun = new DirectionalLight();//光源？
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f).normalizeLocal());
        rootNode.addLight(sun);//尝试注释此句看看没有光源模型是否显示
        Spatial scene = assetManager.loadModel("Scenes/Town/main.scene");
        rootNode.attachChild(scene);
    }
}
