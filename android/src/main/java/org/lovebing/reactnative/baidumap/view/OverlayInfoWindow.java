/*
 * Copyright (c) 2016-present, lovebing.net.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package org.lovebing.reactnative.baidumap.view;

import android.content.Context;
import android.view.ViewParent;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.model.LatLng;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.view.ReactViewGroup;

import org.lovebing.reactnative.baidumap.util.BitmapUtil;

/**
 * @author lovebing Created on Dec 09, 2018
 */
public class OverlayInfoWindow extends ReactViewGroup {



    private InfoWindow infoWindow;
    private int width;
    private int height;
    private int offsetY = 0;

    public OverlayInfoWindow(Context context) {
        super(context);
    }

    public InfoWindow getInfoWindow(LatLng location) {
        updateInfoWindow(location);
        return infoWindow;
    }

    public void clearInfoWindow() {
        infoWindow = null;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    private void updateInfoWindow(LatLng location) {
        if (infoWindow == null) {
            BitmapDescriptor bitmapDescriptor = BitmapUtil.createBitmapDescriptor(this, width, height);
            if (bitmapDescriptor == null) {
                return;
            }
            InfoWindow.OnInfoWindowClickListener listener = new InfoWindow.OnInfoWindowClickListener() {
              @Override
              public void onInfoWindowClick() {
                ReactContext reactContext = (ReactContext) getContext();
                reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "topChange", null);
              }
            };
            infoWindow = new InfoWindow(bitmapDescriptor, location, offsetY, listener);
          ViewParent parent = this.getParent();
        } else {
            infoWindow.setPosition(location);
            BitmapDescriptor bitmapDescriptor = BitmapUtil.createBitmapDescriptor(this, width, height);
            if (bitmapDescriptor != null) {
              infoWindow.setBitmapDescriptor(bitmapDescriptor);
            }
        }
    }
}
