package tieorange.com.allolikebutton;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import in.championswimmer.sfg.lib.SimpleFingerGestures;

/**
 * Created by tieorange on 08/09/16.
 */

public class AlloButton extends RelativeLayout {
  private static final String TAG = AlloButton.class.getCanonicalName();
  View mRootView;
  private AlloButton mImageButton;
  private SimpleFingerGestures mSwipeListener = new SimpleFingerGestures();
  private VerticalSeekBar mVerticalSeekBar;
  private TextView mProgressText;

  public AlloButton(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public AlloButton(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context) {
    mRootView = inflate(context, R.layout.allo_button_layout, this);
    mImageButton = (AlloButton) findViewById(R.id.imageButton);
    mVerticalSeekBar = (VerticalSeekBar) findViewById(R.id.vertical_Seekbar);
    mProgressText = (TextView) findViewById(R.id.vertical_sb_progresstext);
    mSwipeListener.setOnFingerGestureListener(getSwipeListener());

    initButton();
    initSeekBar();
  }

  private void initSeekBar() {
    mVerticalSeekBar.setProgress(0);
    final int progressStep = 90;
    mVerticalSeekBar.incrementProgressBy(progressStep);
    mVerticalSeekBar.setMax(100);
    mVerticalSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        /*progress = progress / progressStep;
        progress = progress * progressStep;
        */

        int stepSize = 50;

        progress = (progress/stepSize)*stepSize;
        seekBar.setProgress(progress);
        mProgressText.setText(progress + "");
      }

      @Override public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });
  }

  @NonNull private SimpleFingerGestures.OnFingerGestureListener getSwipeListener() {
    return new SimpleFingerGestures.OnFingerGestureListener() {
      @Override public boolean onSwipeUp(int fingers, long duration, double distance) {
        Log.d(TAG, "onSwipeUp() called with: fingers = [" + fingers + "], duration = [" + duration + "], distance = [" + distance + "]");
        return true;
      }

      @Override public boolean onSwipeDown(int fingers, long duration, double distance) {
        Log.d(TAG, "onSwipeUp() called with: fingers = [" + fingers + "], duration = [" + duration + "], distance = [" + distance + "]");
        return false;
      }

      @Override public boolean onSwipeLeft(int i, long l, double v) {
        return false;
      }

      @Override public boolean onSwipeRight(int i, long l, double v) {
        return false;
      }

      @Override public boolean onPinch(int i, long l, double v) {
        return false;
      }

      @Override public boolean onUnpinch(int i, long l, double v) {
        return false;
      }

      @Override public boolean onDoubleTap(int i) {
        return false;
      }
    };
  }

  private void initButton() {
    mImageButton.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        Log.d(TAG, "onClick() called with: v = [" + v + "]");
      }
    });
    //mImageButton.setOnTouchListener(mSwipeListener);
    mImageButton.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
      public void onSwipeTop() {
        Log.d(TAG, "onSwipeTop() called");
      }

      public void onSwipeRight() {
      }

      public void onSwipeLeft() {
      }

      public void onSwipeBottom() {
        Log.d(TAG, "onSwipeBottom() called");
      }
    });
  }
}