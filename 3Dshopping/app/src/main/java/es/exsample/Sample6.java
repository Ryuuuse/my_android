package es.exsample;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.PixelFormat;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.*;
import android.opengl.GLSurfaceView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;

import android.util.Size;
import android.util.SparseIntArray;
import android.view.Gravity;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sample6 extends AppCompatActivity {

	private GLSurfaceView glView;
	private Button button1,button2,button3,button4;
	private TextureView textureView;
	static final SparseIntArray ORIENTATIONS = new SparseIntArray();
	static {
		ORIENTATIONS.append(Surface.ROTATION_0, 90);
		ORIENTATIONS.append(Surface.ROTATION_90, 0);
		ORIENTATIONS.append(Surface.ROTATION_180, 270);
		ORIENTATIONS.append(Surface.ROTATION_270, 180);
	}
	String cameraId;
	CameraDevice cameraDevice;
	CameraCaptureSession cameraCaptureSessions;
	CaptureRequest.Builder captureRequestBuilder;
	Size imageDimension;
	ImageReader imageReader;
	static final int REQUEST_CAMERA_PERMISSION = 200;
	Handler mBackgroundHandler;
	HandlerThread mBackgroundThread;


	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		Context context = getApplicationContext();
		CharSequence text = "縦回転・横回転を選択してください！";
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.setGravity(Gravity.TOP,0,0);
		toast.show();


		RelativeLayout rl = new RelativeLayout(this);
		setContentView(rl);

		textureView = new TextureView(this);

		rl.addView(textureView);


		glView = new GLSurfaceView(this);
		glView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
		glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
		glView.setZOrderOnTop(true);
		addContentView(glView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

		Drawable btn_color1 = ResourcesCompat.getDrawable(getResources(), R.drawable.custom_button, null);
		Drawable btn_color2 = ResourcesCompat.getDrawable(getResources(), R.drawable.custom_button2, null);
		Drawable btn_color3 = ResourcesCompat.getDrawable(getResources(), R.drawable.custom_button4, null);
		Drawable btn_color4 = ResourcesCompat.getDrawable(getResources(), R.drawable.custom_button8, null);


		button1 = new Button(this);
		button1.setText("戻る");
		button1.setBackground(btn_color3);
		button1.setTranslationX(660);
		button1.setTranslationY(2200);
		addContentView(button1, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

		button2 = new Button(this);
		button2.setText("横回転");
		button2.setBackground(btn_color2);
		button2.setTextColor(0xffffffff);
		button2.setTranslationX(660);
		button2.setTranslationY(1800);
		button2.setVisibility(View.VISIBLE);
		addContentView(button2, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		button2.setOnClickListener(new Sample6.button2ClickListener());

		button3 = new Button(this);
		button3.setText("縦回転");
		button3.setBackground(btn_color4);
		button3.setTextColor(0xffffffff);
		button3.setTranslationX(660);
		button3.setTranslationY(2000);
		button3.setVisibility(View.VISIBLE);
		addContentView(button3, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		button3.setOnClickListener(new Sample6.button3ClickListener());

		button4 = new Button(this);
		button4.setText("正面に戻す");
		button4.setBackground(btn_color1);
		button4.setTextColor(0xffffffff);
		button4.setTranslationX(660);
		button4.setTranslationY(1900);
		button4.setVisibility(View.INVISIBLE);
		addContentView(button4, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		button4.setOnClickListener(new Sample6.button4ClickListener());


		Intent intent = this.getIntent();
		String num = intent.getStringExtra("num");

		GLRenderer1.Angle = 0;
		GLRenderer2.Angle = 0;
		GLRenderer3.Angle = 0;

		if (num.equals("1")) {
			glView.setRenderer(new GLRenderer1());
			button1.setOnClickListener(new Sample6.button1_1ClickListener());
		} else if (num.equals("2")) {
			glView.setRenderer(new GLRenderer2());
			button1.setOnClickListener(new Sample6.button1_2ClickListener());
		} else if (num.equals("3")) {
			glView.setRenderer(new GLRenderer3());
			button1.setOnClickListener(new Sample6.button1_3ClickListener());
		}


	}

	private class button1_1ClickListener implements Button.OnClickListener {

		public void onClick(View v){
			Intent intent = new Intent(Sample6.this, Sample2.class);
			intent.putExtra("image","isu");
			intent.putExtra("name","座り心地の良い椅子");
			intent.putExtra("price","¥3,980");
			startActivity(intent);
		}
	}


	private class button1_2ClickListener implements Button.OnClickListener {

		public void onClick(View v){
			Intent intent = new Intent(Sample6.this, Sample2.class);
			intent.putExtra("image","table");
			intent.putExtra("name","広々と使いやすい机");
			intent.putExtra("price","¥7,980");
			intent.putExtra("num","2");
			startActivity(intent);
		}
	}



	private class button1_3ClickListener implements Button.OnClickListener {

		public void onClick(View v){
			Intent intent = new Intent(Sample6.this, Sample2.class);
			intent.putExtra("image","bed");
			intent.putExtra("name","ぐっすり睡眠ベッド");
			intent.putExtra("price","¥29,980");
			intent.putExtra("num","3");
			startActivity(intent);
		}
	}

	private class button2ClickListener implements Button.OnClickListener {

		public void onClick(View v) {

			button2.setVisibility(View.INVISIBLE);
			button3.setVisibility(View.INVISIBLE);
			button4.setVisibility(View.VISIBLE);
			GLRenderer1.x = 0;
			GLRenderer1.y = 1;
			GLRenderer2.x = 0;
			GLRenderer2.y = 1;
			GLRenderer3.x = 0;
			GLRenderer3.y = 1;

			Context context = getApplicationContext();
			CharSequence text = "横スワイプで横回転できます！";
			int duration = Toast.LENGTH_LONG;
			Toast toast = Toast.makeText(context, text, duration);
			toast.setGravity(Gravity.TOP,0,0);
			toast.show();


			View flickView = getWindow().getDecorView(); // Activity画面
			float adjustX = 150.0f;
			float adjustY = 150.0f;

			new FlickCheck(flickView, adjustX, adjustY) {

				@Override
				public void getFlick(int flickData) {
					switch (flickData) {
						case FlickCheck.LEFT_FLICK:
							GLRenderer1.Angle -= 10;
							GLRenderer2.Angle -= 10;
							GLRenderer3.Angle -= 10;
							break;

						case FlickCheck.RIGHT_FLICK:
							GLRenderer1.Angle += 10;
							GLRenderer2.Angle += 10;
							GLRenderer3.Angle += 10;
							break;

					}

				}
			};

		}
	}

	private class button3ClickListener implements Button.OnClickListener {

		public void onClick(View v){

			button2.setVisibility(View.INVISIBLE);
			button3.setVisibility(View.INVISIBLE);
			button4.setVisibility(View.VISIBLE);
			GLRenderer1.x = 1;
			GLRenderer1.y = 0;
			GLRenderer2.x = 1;
			GLRenderer2.y = 0;
			GLRenderer3.x = 1;
			GLRenderer3.y = 0;

			Context context = getApplicationContext();
			CharSequence text = "縦スワイプで縦回転できます！";
			int duration = Toast.LENGTH_LONG;
			Toast toast = Toast.makeText(context, text, duration);
			toast.setGravity(Gravity.TOP,0,0);
			toast.show();

			View flickView = getWindow().getDecorView(); // Activity画面
			float adjustX = 150.0f;
			float adjustY = 150.0f;

			new FlickCheck(flickView, adjustX, adjustY) {

				@Override
				public void getFlick(int flickData) {
					switch (flickData) {
						case FlickCheck.UP_FLICK:
							GLRenderer1.Angle -= 10;
							GLRenderer2.Angle -= 10;
							GLRenderer3.Angle -= 10;
							break;

						case FlickCheck.DOWN_FLICK:
							GLRenderer1.Angle += 10;
							GLRenderer2.Angle += 10;
							GLRenderer3.Angle += 10;
							break;



					}

				}
			};


		}
	}

	private class button4ClickListener implements Button.OnClickListener {

		public void onClick(View v){

			button2.setVisibility(View.VISIBLE);
			button3.setVisibility(View.VISIBLE);
			button4.setVisibility(View.INVISIBLE);

			Context context = getApplicationContext();
			CharSequence text = "縦回転・横回転を選択してください！";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.setGravity(Gravity.TOP,0,0);
			toast.show();


			GLRenderer1.x = 0;
			GLRenderer1.y = 0;
			GLRenderer2.x = 0;
			GLRenderer2.y = 0;
			GLRenderer3.x = 0;
			GLRenderer3.y = 0;
			GLRenderer1.Angle = 0;
			GLRenderer2.Angle = 0;
			GLRenderer3.Angle = 0;

			View flickView = getWindow().getDecorView(); // Activity画面
			float adjustX = 150.0f;
			float adjustY = 150.0f;

			new FlickCheck(flickView, adjustX, adjustY) {

				@Override
				public void getFlick(int flickData) {
					switch (flickData) {
					}

				}
			};


		}
	}



	protected void onResume() {
		super.onResume();
		startBackgroundThread();
		if (textureView.isAvailable()) {
			openCamera();
		} else {
			textureView.setSurfaceTextureListener(textureListener);
		}
	}
	protected void onPause() {
		stopBackgroundThread();
		super.onPause();
	}

	protected void startBackgroundThread() {
		mBackgroundThread = new HandlerThread("Camera Background");
		mBackgroundThread.start();
		mBackgroundHandler = new Handler(mBackgroundThread.getLooper());
	}
	protected void stopBackgroundThread() {
		mBackgroundThread.quitSafely();
		try {
			mBackgroundThread.join();
			mBackgroundThread = null;
			mBackgroundHandler = null;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void openCamera() {
		CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
		try {
			cameraId = manager.getCameraIdList()[0];
			CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
			StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
			assert map != null;
			imageDimension = map.getOutputSizes(SurfaceTexture.class)[0];
			if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
				ActivityCompat.requestPermissions(Sample6.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CAMERA_PERMISSION);
				return;
			}
			manager.openCamera(cameraId, stateCallback, null);
		} catch (CameraAccessException e) {
			e.printStackTrace();
		}
	}

	protected void createCameraPreview() {
		try {
			SurfaceTexture texture = textureView.getSurfaceTexture();
			assert texture != null;
			texture.setDefaultBufferSize(imageDimension.getWidth(), imageDimension.getHeight());
			Surface surface = new Surface(texture);
			captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
			captureRequestBuilder.addTarget(surface);
			cameraDevice.createCaptureSession(Arrays.asList(surface), new CameraCaptureSession.StateCallback(){
				public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
					if (null == cameraDevice) {
						return;
					}
					cameraCaptureSessions = cameraCaptureSession;
					updatePreview();
				}
				public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {}
			}, null);
		} catch (CameraAccessException e) {
			e.printStackTrace();
		}
	}

	protected void updatePreview() {
		captureRequestBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);
		try {
			cameraCaptureSessions.setRepeatingRequest(captureRequestBuilder.build(), null, mBackgroundHandler);
		} catch (CameraAccessException e) {
			e.printStackTrace();
		}
	}

	private final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
		public void onOpened(CameraDevice camera) {
			cameraDevice = camera;
			createCameraPreview();
		}
		public void onDisconnected(CameraDevice camera) {
			cameraDevice.close();
		}
		public void onError(CameraDevice camera, int error) {
			cameraDevice.close();
			cameraDevice = null;
		}
	};

	TextureView.SurfaceTextureListener textureListener = new TextureView.SurfaceTextureListener() {
		public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
			openCamera();
		}
		public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {}
		public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
			return false;
		}
		public void onSurfaceTextureUpdated(SurfaceTexture surface) {}
	};

	final CameraCaptureSession.CaptureCallback captureCallbackListener = new CameraCaptureSession.CaptureCallback() {
		@Override
		public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
			super.onCaptureCompleted(session, request, result);
			Toast.makeText(Sample6.this, "Saved:", Toast.LENGTH_SHORT).show();
			createCameraPreview();
		}
	};

	protected void takePicture() {
		CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
		try {
			CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraDevice.getId());
			Size[] jpegSizes = null;
			if (characteristics != null) {
				jpegSizes = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP).getOutputSizes(ImageFormat.JPEG);
			}
			int width = 640;
			int height = 480;
			if (jpegSizes != null && 0 < jpegSizes.length) {
				width = jpegSizes[0].getWidth();
				height = jpegSizes[0].getHeight();
			}
			ImageReader reader = ImageReader.newInstance(width, height, ImageFormat.JPEG, 1);
			List<Surface> outputSurfaces = new ArrayList<Surface>(2);
			outputSurfaces.add(reader.getSurface());
			outputSurfaces.add(new Surface(textureView.getSurfaceTexture()));
			final CaptureRequest.Builder captureBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
			captureBuilder.addTarget(reader.getSurface());
			captureBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);

			int rotation = getWindowManager().getDefaultDisplay().getRotation();
			captureBuilder.set(CaptureRequest.JPEG_ORIENTATION, ORIENTATIONS.get(rotation));
			final File file = new File(Environment.getExternalStorageDirectory()+"/pic.jpg");
			ImageReader.OnImageAvailableListener readerListener = new ImageReader.OnImageAvailableListener() {
				public void onImageAvailable(ImageReader reader) {
					Image image = null;
					try {
						image = reader.acquireLatestImage();
						ByteBuffer buffer = image.getPlanes()[0].getBuffer();
						byte[] bytes = new byte[buffer.capacity()];
						buffer.get(bytes);
						save(bytes);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if (image != null) {
							image.close();
						}
					}
				}
				private void save(byte[] bytes) throws IOException {
					OutputStream output = null;
					try {
						output = new FileOutputStream(file);
						output.write(bytes);
					} finally {
						if (null != output) {
							output.close();
						}
					}
				}
			};
			reader.setOnImageAvailableListener(readerListener, mBackgroundHandler);
			final CameraCaptureSession.CaptureCallback captureListener = new CameraCaptureSession.CaptureCallback() {
				@Override
				public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
					super.onCaptureCompleted(session, request, result);
					Toast.makeText(Sample6.this, "Saved:" + file, Toast.LENGTH_SHORT).show();
					createCameraPreview();
				}
			};
			cameraDevice.createCaptureSession(outputSurfaces, new CameraCaptureSession.StateCallback() {
				public void onConfigured(CameraCaptureSession session) {
					try {
						session.capture(captureBuilder.build(), captureListener, mBackgroundHandler);
					} catch (CameraAccessException e) {
						e.printStackTrace();
					}
				}
				public void onConfigureFailed(CameraCaptureSession session) {
				}
			}, mBackgroundHandler);
		} catch (CameraAccessException e) {
			e.printStackTrace();
		}
	}

	private void closeCamera() {
		if (null != cameraDevice) {
			cameraDevice.close();
			cameraDevice = null;
		}
		if (null != imageReader) {
			imageReader.close();
			imageReader = null;
		}
	}
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		if (requestCode == REQUEST_CAMERA_PERMISSION) {
			if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
				finish();
			}
		}
	}

};

