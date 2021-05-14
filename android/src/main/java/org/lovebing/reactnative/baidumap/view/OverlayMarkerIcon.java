/*
 * Copyright (c) 2016-present, lovebing.net.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package org.lovebing.reactnative.baidumap.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.facebook.react.views.view.ReactViewGroup;

import org.lovebing.reactnative.baidumap.R;
import org.lovebing.reactnative.baidumap.model.IconInfo;

/**
 * @author lovebing
 * @date 2020-06-07
 */
public class OverlayMarkerIcon extends ReactViewGroup {

  private Paint paint = new Paint();
  public int width;
  public int height;
  private IconInfo iconInfo;
  private String sort;
  private String title;
  private BitmapDescriptor bitmap;

  public OverlayMarkerIcon(Context context) {
    super(context);
    initPaint();
  }

  private void initPaint() {
    paint.setAntiAlias(true);
    paint.setStyle(Paint.Style.FILL);
    paint.setTextAlign(Paint.Align.CENTER);
  }

  /**
   * 画icon
   */
  private void drawIcon(Canvas canvas) {
    Bitmap icon = BitmapDescriptorFactory.fromResource(R.mipmap.icon_mark).getBitmap();
    icon = zoomImg(icon, dp(22), dp(35));
    float left = (width - icon.getWidth()) / 2;
    canvas.drawBitmap(icon, left, 0, paint);
  }

  /**
   * sort
   */
  private void drawSort(Canvas canvas) {
    paint.setTextSize(dp(14));
    paint.setColor(Color.parseColor("#FFFFFF"));
    canvas.drawText(sort, width / 2, dp(16), paint);
  }

  /**
   * title
   */
  private void drawTitle(Canvas canvas) {
    paint.setTextSize(dp(10));
    paint.setColor(Color.parseColor("#000000"));
    drawLine(canvas, title, 2, 1);
  }

  private void drawLine(Canvas canvas, String text, int maxLine, int lineNum) {
    int subIndex = paint.breakText(text, 0, text.length(), true, width, null);
    String line = text.substring(0, subIndex);
    canvas.drawText(line, width / 2, dp(38 + lineNum * 10), paint);
    String ss = text.substring(subIndex, text.length());
    if (ss.length() > 0 && lineNum < maxLine) {
      drawLine(canvas, ss, maxLine, lineNum + 1);
    }
  }

  /**
   * sp转dp
   */
  private float dp(float size) {
    return size * getResources().getDisplayMetrics().scaledDensity;
  }

  /**
   * 图片缩放
   */
  private Bitmap zoomImg(Bitmap bm, float newWidth, float newHeight) {
    int width = bm.getWidth();
    int height = bm.getHeight();
    Matrix matrix = new Matrix();
    matrix.postScale(newWidth / width, newHeight / height);
    return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    drawIcon(canvas);
    drawSort(canvas);
    drawTitle(canvas);
  }

  public void setIcon(IconInfo iconInfo) {
    this.iconInfo = iconInfo;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public void updateDraw() {

  }
}
