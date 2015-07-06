//
//  ViewController.h
//  H2 Tech Drive
//
//  Created by Macbook Pro on 17/06/2015.
//  Copyright (c) 2015 UnitSol. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <MapKit/MapKit.h>

@interface ViewController : UIViewController<MKMapViewDelegate, CLLocationManagerDelegate>

@property (weak, nonatomic) IBOutlet MKMapView *currenDrive;
@property (weak, nonatomic) IBOutlet UIButton *cmdStartDriving;
@property (weak, nonatomic) IBOutlet UIButton *cmdStopDriving;

@end

