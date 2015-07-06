//
//  ViewController.m
//  H2 Tech Drive
//
//  Created by Macbook Pro on 17/06/2015.
//  Copyright (c) 2015 UnitSol. All rights reserved.
//

#import "ViewController.h"

@interface ViewController (){
    
    CLLocationManager *locationManager;
    CLLocation *currentLocation;
    NSMutableArray *arrLocationCoordinates;
}

@end

@implementation ViewController
@synthesize cmdStartDriving;

- (void)viewDidLoad {
    [super viewDidLoad];
    
    //Setting Button Views
    cmdStartDriving.layer.cornerRadius = 4.0;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
}

@end
