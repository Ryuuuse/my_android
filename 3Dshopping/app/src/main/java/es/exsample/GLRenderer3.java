package es.exsample;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static javax.microedition.khronos.opengles.GL10.GL_AMBIENT_AND_DIFFUSE;
import static javax.microedition.khronos.opengles.GL10.GL_COLOR_BUFFER_BIT;
import static javax.microedition.khronos.opengles.GL10.GL_FRONT_AND_BACK;


public class GLRenderer3 implements GLSurfaceView.Renderer {

    private FloatBuffer mVertexBuffer;
    static float Angle = 0f;
    static int x,y = 0;

    public void onSurfaceCreated(GL10 gl10,EGLConfig eglConfig) {
        gl10.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl10.glEnable(GL10.GL_DEPTH_TEST);
        gl10.glDepthFunc(GL10.GL_LEQUAL);
        gl10.glEnable(GL10.GL_LIGHTING);
        gl10.glEnable(GL10.GL_LIGHT0);
        float[] vertices= {
                // 前
                -0.5f, -0.25f, 0.5f,
                0.5f, -0.25f, 0.5f,
                -0.5f, 0f, 0.5f,
                0.5f, 0f, 0.5f,

                // 後
                -0.5f, -0.25f, -1f,
                0.5f, -0.25f, -1f,
                -0.5f, 0f, -1f,
                0.5f, 0f, -1f,

                // 左
                -0.5f, -0.25f, 0.5f,
                -0.5f, -0.25f, -1f,
                -0.5f, 0f, 0.5f,
                -0.5f, 0f, -1f,

                // 右
                0.5f, -0.25f, 0.5f,
                0.5f, -0.25f, -1f,
                0.5f, 0f, 0.5f,
                0.5f, 0f, -1f,

                // 上
                -0.5f, 0f, 0.5f,
                0.5f, 0f, 0.5f,
                -0.5f, 0f, -1f,
                0.5f, 0f, -1f,

                // 底
                -0.5f, -0.25f, 0.5f,
                0.5f, -0.25f, 0.5f,
                -0.5f, -0.25f, -1f,
                0.5f, -0.25f, -1f,

                //背もたれ
                -0.5f, 0f, -0.75f,
                0.5f, 0f, -0.75f,
                -0.5f, 0.5f, -0.75f,
                0.5f, 0.5f, -0.75f,

                -0.5f, 0f, -1f,
                0.5f, 0f, -1f,
                -0.5f, 0.5f, -1f,
                0.5f, 0.5f, -1f,

                -0.5f, 0f, -0.75f,
                -0.5f, 0f, -1f,
                -0.5f, 0.5f, -0.75f,
                -0.5f, 0.5f, -1f,

                0.5f, 0f, -0.75f,
                0.5f, 0f, -1f,
                0.5f, 0.5f, -0.75f,
                0.5f, 0.5f, -1f,


                //クッション

                // 前
                -0.5f, 0f, 0.5f,
                0.5f, 0f, 0.5f,
                -0.5f, 0.25f, 0.5f,
                0.5f, 0.25f, 0.5f,

                // 後
                -0.5f, 0f, -0.75f,
                0.5f, 0f, -0.75f,
                -0.5f, 0.25f, -0.75f,
                0.5f, 0.25f, -0.75f,

                // 左
                -0.5f, 0f, 0.5f,
                -0.5f, 0f, -0.75f,
                -0.5f, 0.25f, 0.5f,
                -0.5f, 0.25f, -0.75f,

                // 右
                0.5f, 0f, 0.5f,
                0.5f, 0f, -0.75f,
                0.5f, 0.25f, 0.5f,
                0.5f, 0.25f, -0.75f,

                // 上
                -0.5f, 0.25f, 0.5f,
                0.5f, 0.25f, 0.5f,
                -0.5f, 0.25f, -0.75f,
                0.5f, 0.25f, -0.75f,


                //枕
                // 前
                -0.2f, 0.25f, -0.49f,
                0.2f, 0.25f, -0.49f,
                -0.2f, 0.35f, -0.49f,
                0.2f, 0.35f, -0.49f,

                // 後
                -0.2f, 0.25f, -0.74f,
                0.2f, 0.25f, -0.74f,
                -0.2f, 0.35f, -0.74f,
                0.2f, 0.35f, -0.74f,

                // 左
                -0.2f, 0.25f, -0.49f,
                -0.2f, 0.25f, -0.74f,
                -0.2f, 0.35f, -0.49f,
                -0.2f, 0.35f, -0.74f,

                // 右
                0.2f, 0.25f, -0.49f,
                0.2f, 0.25f, -0.74f,
                0.2f, 0.35f, -0.49f,
                0.2f, 0.35f, -0.74f,

                //かけ布団


                // 前
                -0.51f, -0.1f, 0.51f,
                0.51f, -0.1f, 0.51f,
                -0.51f, 0.26f, 0.51f,
                0.51f, 0.26f, 0.51f,

                // 左
                -0.51f, -0.1f, 0.51f,
                -0.51f, -0.1f, -0.45f,
                -0.51f, 0.26f, 0.51f,
                -0.51f, 0.26f, -0.45f,

                // 右
                0.51f, -0.1f, 0.51f,
                0.51f, -0.1f, -0.45f,
                0.51f, 0.26f, 0.51f,
                0.51f, 0.26f, -0.45f,

                // 上
                -0.51f, 0.26f, 0.51f,
                0.51f, 0.26f, 0.51f,
                -0.51f, 0.26f, -0.45f,
                0.51f, 0.26f, -0.45f,

                -0.2f, 0.35f, -0.49f,
                0.2f, 0.35f, -0.49f,
                -0.2f, 0.35f, -0.74f,
                0.2f, 0.35f, -0.74f,

                -0.5f, 0.5f, -0.75f,
                0.5f, 0.5f, -0.75f,
                -0.5f, 0.5f, -1.0f,
                0.5f, 0.5f, -1.0f,

        };
        ByteBuffer vbb =
                ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        mVertexBuffer = vbb.asFloatBuffer();
        mVertexBuffer.put(vertices);
        mVertexBuffer.position(0);
    }

    public void onSurfaceChanged(GL10 gl10,int w,int h) {

        gl10.glViewport(0,0,w,h);  //ビューポートの設定
        gl10.glMatrixMode(GL10.GL_PROJECTION);
        gl10.glLoadIdentity();
        GLU.gluPerspective(gl10, 45f,(float) w / h, 1f, 50f);
    }

    public void onDrawFrame(GL10 gl10) {

        gl10.glClear(GL_COLOR_BUFFER_BIT
                | GL10.GL_DEPTH_BUFFER_BIT);

        gl10.glMatrixMode(GL10.GL_MODELVIEW);
        gl10.glLoadIdentity();
        gl10.glTranslatef(0, 0, -5.5f);

        gl10.glRotatef(Angle, x, y, 0);

        float white[] = {1.0f, 1.0f, 1.0f, 1.0f};

        float blown[] = {0.4f, 0.2f, 0.0f,1.0f};

        float pink[] = {0.8f, 0.4f, 0.4f,1.0f};

        gl10.glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE, FloatBuffer.wrap(blown));


        gl10.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl10.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);


        //Front
        gl10.glNormal3f(0, 0, 1.0f);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

        // Back
        gl10.glNormal3f(0, 0, -1.0f);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);

        // Left
        gl10.glNormal3f(-1.0f, 0, 0);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);

        // Right
        gl10.glNormal3f(1.0f, 0, 0);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);

        // Top
        gl10.glNormal3f(0, 1.0f, 0);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);

        // Bottom
        gl10.glNormal3f(0, -1.0f, 0);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);



        // Front
        gl10.glNormal3f(0, 0, 1.0f);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 24, 4);


        // Back
        gl10.glNormal3f(0, 0, -1.0f);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 28, 4);

        // Left
        gl10.glNormal3f(-1.0f, 0, 0);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 32, 4);

        // Right
        gl10.glNormal3f(1.0f, 0, 0);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 36, 4);


        gl10.glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE, FloatBuffer.wrap(white));

        //クッション
        //Front
        gl10.glNormal3f(0, 0, 1.0f);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 40, 4);

        // Back
        gl10.glNormal3f(0, 0, -1.0f);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 44, 4);

        // Left
        gl10.glNormal3f(-1.0f, 0, 0);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 48, 4);

        // Right
        gl10.glNormal3f(1.0f, 0, 0);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 52, 4);

        // Top
        gl10.glNormal3f(0, 1.0f, 0);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 56, 4);

        gl10.glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE, FloatBuffer.wrap(pink));

        //Front
        gl10.glNormal3f(0, 0, 1.0f);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 60, 4);

        // Back
        gl10.glNormal3f(0, 0, -1.0f);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 64, 4);

        // Left
        gl10.glNormal3f(-1.0f, 0, 0);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 68, 4);

        // Right
        gl10.glNormal3f(1.0f, 0, 0);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 72, 4);

        //Front
        gl10.glNormal3f(0, 0, 1.0f);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 76, 4);

        // Left
        gl10.glNormal3f(-1.0f, 0, 0);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 80, 4);

        // Right
        gl10.glNormal3f(1.0f, 0, 0);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 84, 4);

        // Top
        gl10.glNormal3f(0, 1.0f, 0);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 88, 4);

        // Top
        gl10.glNormal3f(0, 1.0f, 0);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 92, 4);


        gl10.glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE, FloatBuffer.wrap(blown));

        // Top
        gl10.glNormal3f(0, 1.0f, 0);
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 96, 4);







    }

    private FloatBuffer makeFloatBuffer(float[] array) {  //頂点データ格納のためのバッファ
        FloatBuffer fb=ByteBuffer.allocateDirect(array.length*4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        fb.put(array).position(0);
        return fb;
    }
}