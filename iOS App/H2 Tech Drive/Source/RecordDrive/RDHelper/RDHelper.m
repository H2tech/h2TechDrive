//
//  RDHelper.m
//  H2 Tech Drive
//
//  Created by Macbook Pro on 19/06/2015.
//  Copyright (c) 2015 UnitSol. All rights reserved.
//

#import "RDHelper.h"

@implementation RDHelper

+(void)setTripRecordData:(NSString *)trd{
    NSUserDefaults *def=[NSUserDefaults standardUserDefaults];
    [def setObject:trd
            forKey:@"tripData"];
    [def synchronize];
}

+(NSString *)getTripRecordData{
    NSUserDefaults *def=[NSUserDefaults standardUserDefaults];
    return [def objectForKey:@"tripData"];
}
@end
