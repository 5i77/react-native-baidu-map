/**
 * Copyright (c) 2016-present, lovebing.org.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

import {
  requireNativeComponent,
  View
} from 'react-native';

import React, { Component } from 'react';
import PropTypes from 'prop-types';
import resolveAssetSource from 'react-native/Libraries/Image/resolveAssetSource';

export default class MarkerIcon extends Component {
  static propTypes = {
    ...View.propTypes,
    sort: PropTypes.string,
    title: PropTypes.string,
    icon: PropTypes.any
  };

  constructor() {
    super();
  }

  render() {
    const icon = resolveAssetSource(this.props.icon);
    return <BaiduMapOverlayMarkerIcon {...this.props} icon={icon} />;
  }
}
const BaiduMapOverlayMarkerIcon = requireNativeComponent('BaiduMapOverlayMarkerIcon', MarkerIcon);