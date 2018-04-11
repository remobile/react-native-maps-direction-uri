import {
    requireNativeComponent,
    NativeModules,
    Platform,
    DeviceEventEmitter
} from 'react-native';

import React, {
    Component,
    PropTypes
} from 'react';

console.log('-------------NativeModules',NativeModules);
const _module = NativeModules.MapsDirectionUri;

export default {
    startDirection(dict, callback) {
        return _module.startDirection(dict, callback);
    },
};
