package com.phone.kashyap.touchscreengestures;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawingView extends SurfaceView
{
	private static final String LOG_TAG = DrawingView.class.getSimpleName();
	private final SurfaceHolder surfaceHolder;
	private static final Paint _paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private static final Paint _textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

	//Different Colors
	private static int[] COLORS = { Color.BLUE, Color.GREEN, Color.MAGENTA,
			Color.YELLOW, Color.CYAN, Color.GRAY, Color.RED, Color.DKGRAY,
			Color.LTGRAY, Color.WHITE };

	public DrawingView(Context context)
	{
		super(context);
		surfaceHolder = getHolder();
		_paint.setStyle(Paint.Style.FILL);

		//Text Color Setup
		_textPaint.setColor(Color.rgb(132, 255, 255));
		_textPaint.setStyle(Paint.Style.FILL);
		_textPaint.setTextSize(_textPaint.getTextSize() * 3);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		switch(MotionEventCompat.getActionMasked(event))
		{
			case MotionEvent.ACTION_MOVE:
			{
				//Touch Move Event
				Canvas canvas = surfaceHolder.lockCanvas();
				canvas.drawColor(Color.BLACK);


				//For drawing all the touch circles
				for(int i = 0; i < event.getPointerCount(); i++)
				{
					_paint.setColor(COLORS[i]);
					int xPos = (int)MotionEventCompat.getX(event, i);
					int yPos = (int)MotionEventCompat.getY(event, i);
					canvas.drawCircle(xPos, yPos, 145, _paint);
				}

				//Main Touch Information on screen
				canvas.drawText("X-Coordinates: " + String.valueOf(event.getX()), 30, 190, _textPaint);
				canvas.drawText("Y-Coordinates: " + String.valueOf(event.getY()), 30, 250, _textPaint);
				canvas.drawText("Pointer ID: " + String.valueOf(event.getPointerId(event.getActionIndex())), 30, 310, _textPaint);
				surfaceHolder.unlockCanvasAndPost(canvas);
				break;
			}

			case MotionEvent.ACTION_DOWN:
			{
				Log.i(LOG_TAG, "Main Touch");
				break;
			}

			case MotionEvent.ACTION_UP:
			{
				Log.i(LOG_TAG, "Main Touch Lifted");
				Canvas canvas = surfaceHolder.lockCanvas();
				canvas.drawColor(0, PorterDuff.Mode.CLEAR);
				surfaceHolder.unlockCanvasAndPost(canvas);
				break;
			}

			case MotionEvent.ACTION_POINTER_DOWN:
			{
				int actionIndex = event.getActionIndex();
				Log.i(LOG_TAG, "Touch ID: " + String.valueOf(actionIndex));
				break;
			}

			case MotionEvent.ACTION_POINTER_UP:
			{
				int actionIndex = event.getActionIndex();
				Log.i(LOG_TAG, "Touch Lifted with ID: " + String.valueOf(actionIndex));
				break;
			}
		}
		invalidate();
		return true;
	}
}