### Installation
npm install react-native-maps-direction-uri --save

### Installation (Android)
- settings.gradle `
include ':react-native-maps-direction-uri'
project(':react-native-maps-direction-uri').projectDir = new File(settingsDir, '../node_modules/@remobile/react-native-maps-direction-uri/android')`

- build.gradle `compile project(':react-native-maps-direction-uri')`

- MainApplication`new MapsDirectionUriPackage()`

### Installation (iOS)
- Project navigator->Libraries->Add Files to 选择 @remobile/react-native-maps-direction-uri/ios/RCTMapsDirectionUri.xcodeproj
- Project navigator->Build Phases->Link Binary With Libraries 加入 libRCTMapsDirectionUri.a

### Usage 使用方法
    const MapsDirectionUri =  require('react-native-maps-direction-uri');
    mapsDirection.startDirection({
            startLatitude:26.207149,
            startLongitude:114.593086,
            endLatitude:31.207149,
            endLongitude:118.593086,
            type(baidu,apple,iosa,google,qq)
        }, (data)=>{
            console.log('---------------data',data);
        });
