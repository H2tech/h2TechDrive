//
//  RecordDriveViewController.h
//  H2 Tech Drive
//
//  Created by Macbook Pro on 19/06/2015.
//  Copyright (c) 2015 UnitSol. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <MapKit/MapKit.h>

@interface RecordDriveViewController : UIViewController<MKMapViewDelegate, CLLocationManagerDelegate>

@property (weak, nonatomic) IBOutlet MKMapView *currenDrive;
@property (weak, nonatomic) IBOutlet UIButton *cmdStopDriving;
@property (weak, nonatomic) IBOutlet UILabel *lblSpeedTest;
@property float distance;

@end
