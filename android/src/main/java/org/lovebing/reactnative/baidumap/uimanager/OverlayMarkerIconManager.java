/*
 * Copyright (c) 2016-present, lovebing.net.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package org.lovebing.reactnative.baidumap.uimanager;

import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

import org.lovebing.reactnative.baidumap.model.IconInfo;
import org.lovebing.reactnative.baidumap.util.LatLngUtil;
import org.lovebing.reactnative.baidumap.view.OverlayMarker;
import org.lovebing.reactnative.baidumap.view.OverlayMarkerIcon;

/**
 * @author lovebing
 * @date 2020-06-07
 */
public class OverlayMarkerIconManager extends ViewGroupManager<OverlayMarkerIcon> {

    @NonNull
    @Override
    public String getName() {
        return "BaiduMapOverlayMarkerIcon";
    }

    @NonNull
    @Override
    protected OverlayMarkerIcon createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new OverlayMarkerIcon(reactContext);
    }

  @ReactProp(name = "icon")
  public void setIcon(OverlayMarkerIcon overlayMarkerIcon, ReadableMap icon) {
    if (icon != null && icon.hasKey("uri")) {
      IconInfo iconInfo = new IconInfo();
      iconInfo.setUri(icon.getString("uri"));
      if (icon.hasKey("width")) {
        iconInfo.setWidth(icon.getInt("width"));
      }
      if (icon.hasKey("height")) {
        iconInfo.setHeight(icon.getInt("height"));
      }
      Log.i("iconInfo", iconInfo.getUri());
      overlayMarkerIcon.setIcon(iconInfo);
    }
  }

  @ReactProp(name = "sort")
  public void setSort(OverlayMarkerIcon overlayMarkerIcon, String sort) {
    overlayMarkerIcon.setSort(sort);
  }

  @ReactProp(name = "title")
  public void setTitle(OverlayMarkerIcon overlayMarkerIcon, String title) {
    overlayMarkerIcon.setTitle(title);
  }

  @ReactProp(name = "width")
  public void setWidth(OverlayMarkerIcon overlayMarkerIcon, int width) {
      overlayMarkerIcon.setWidth(width);
  }

  @ReactProp(name = "height")
  public void setHeight(OverlayMarkerIcon overlayMarkerIcon, int height) {
    overlayMarkerIcon.setHeight(height);
  }

}
