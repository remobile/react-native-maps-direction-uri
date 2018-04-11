//
//  GoelocationModule.m
//  RCTMapsDirectionUri
//
//  Created by honggao on 2016/10/28.
//  Copyright © 2016年 honggao.org. All rights reserved.
//

#import "MapsDirectionUriModule.h"


@implementation MapsDirectionUriModule
{

}

RCT_EXPORT_MODULE(MapsDirectionUri);

RCT_EXPORT_METHOD(startDirection: (nonnull NSDictionary*)dic :(RCTResponseSenderBlock)callback) {
    NSLog(@"--------------startDirection----------%@",dic);
    double slat = [RCTConvert double:dic[@"startLatitude"]];
    double slng = [RCTConvert double:dic[@"startLongitude"]];
    double elat = [RCTConvert double:dic[@"endLatitude"]];
    double elng = [RCTConvert double:dic[@"endLongitude"]];
    NSString *type = dic[@"type"];
    if ([type isEqualToString:@"baidu"]) {
        if ([[UIApplication sharedApplication] canOpenURL:[NSURL URLWithString:@"baidumap://"]]){
            NSString *urlString = [[NSString stringWithFormat:@"baidumap://map/direction?origin={{我的位置}}&destination=name:目的地|latlng:%f,%f&mode=driving&coord_type=gcj02",elat, elng] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
            
            NSLog(@"%@",urlString);
            [[UIApplication sharedApplication] openURL:[NSURL URLWithString:urlString]];
            callback(@[@"true"]);
        } else {
            NSLog(@"--------------can't open baidu map----------");
            callback(@[@"false"]);
        }
    } else if ([type isEqualToString:@"apple"]) {
        if ([[UIApplication sharedApplication] canOpenURL:[NSURL URLWithString:@"http://maps.apple.com/"]]){
            NSString *urlString = [[NSString stringWithFormat:@"http://maps.apple.com/?daddr=%f,%f&saddr=",elat, elng] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
            NSLog(@"%@",urlString);
            [[UIApplication sharedApplication] openURL:[NSURL URLWithString:urlString]];
            callback(@[@"true"]);
        } else {
            NSLog(@"--------------can't open apple map----------");
            callback(@[@"false"]);
        }
        
    } else if ([type isEqualToString:@"iosa"]) {
        if ([[UIApplication sharedApplication] canOpenURL:[NSURL URLWithString:@"iosamap://"]]){
            NSString *urlString = [[NSString stringWithFormat:@"iosamap://navi?sourceApplication=%@&backScheme=%@&lat=%f&lon=%f&dev=0&style=2",@"appName",@"urlScheme",elat, elng] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
            
            NSLog(@"%@",urlString);
            
            [[UIApplication sharedApplication] openURL:[NSURL URLWithString:urlString]];
            callback(@[@"true"]);
        } else {
            NSLog(@"--------------can't open iosa map----------");
            callback(@[@"false"]);
        }
    } else if ([type isEqualToString:@"google"]) {
        if ([[UIApplication sharedApplication] canOpenURL:[NSURL URLWithString:@"comgooglemaps://"]]){
            
            NSString *urlString = [[NSString stringWithFormat:@"comgooglemaps://?x-source=%@&x-success=%@&saddr=&daddr=%f,%f&directionsmode=driving",@"appName",@"urlScheme",elat, elng] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
            
            NSLog(@"%@",urlString);
            
            [[UIApplication sharedApplication] openURL:[NSURL URLWithString:urlString]];
            
            callback(@[@"true"]);
        } else {
            NSLog(@"--------------can't open google map----------");
            callback(@[@"false"]);
        }
    } else if ([type isEqualToString:@"qq"]) {
        if ([[UIApplication sharedApplication] canOpenURL:[NSURL URLWithString:@"qqmap://map/"]]){
            
            NSString *QQParameterFormat = @"qqmap://map/routeplan?type=drive&fromcoord=%f, %f&tocoord=%f,%f&coord_type=1&policy=0&refer=%@";
            
            NSString *urlString = [[NSString stringWithFormat:QQParameterFormat, slat, slng, elat, elng, @"yourAppName"] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
            
            NSLog(@"%@",urlString);
            
            [[UIApplication sharedApplication] openURL:[NSURL URLWithString:urlString]];
            callback(@[@"true"]);
        } else {
            NSLog(@"--------------can't open qq map----------");
            callback(@[@"false"]);
        }
    }
}

@end
