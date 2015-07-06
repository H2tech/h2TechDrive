//
//  RecordDriveViewController.m
//  H2 Tech Drive
//
//  Created by Macbook Pro on 19/06/2015.
//  Copyright (c) 2015 UnitSol. All rights reserved.
//

#import "RecordDriveViewController.h"
#import "MathController.h"
#import "RDHelper.h"
#import "TripInfoViewController.h"

#define BASE_RADIUS 0.0144927536

@interface RecordDriveViewController (){
    CLLocationManager *locationManager;
    CLLocation *currentLocation;
    NSMutableArray *arrLocationCoordinates;
    double avgSpeed;
    NSMutableArray *arrAvgSpeed;
}

@end

@implementation RecordDriveViewController
@synthesize currenDrive;
@synthesize cmdStopDriving;
@synthesize lblSpeedTest;

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.distance = 0;
    
    //Show UserCurrentLocation
    currenDrive.showsUserLocation = YES;
    currenDrive.delegate = self;
    [currenDrive setUserTrackingMode:MKUserTrackingModeFollow];
    
    //Setting Button Views
    cmdStopDriving.layer.cornerRadius = 4.0;
    
    //Initializing MutableArray of Location
    arrLocationCoordinates = [[NSMutableArray alloc] init];
    arrAvgSpeed = [[NSMutableArray alloc] init];
    
    // Initializing Location Manager
    //LocationManager Settings
    locationManager = [[CLLocationManager alloc] init];
    locationManager.delegate = self;
    locationManager.distanceFilter = 5;
    locationManager.desiredAccuracy = kCLLocationAccuracyBest;
    
    
    if ([[[UIDevice currentDevice] systemVersion] floatValue] >= 7.1){
        [locationManager requestWhenInUseAuthorization];
        [locationManager requestAlwaysAuthorization];
        
    }else{
        [locationManager startUpdatingLocation];
    }
    
    [cmdStopDriving addTarget:self
                       action:@selector(stopDrivingTrip)
             forControlEvents:UIControlEventTouchUpInside];
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)stopDrivingTrip{
    
    [locationManager stopUpdatingLocation];
    [self performSegueWithIdentifier:@"segueTripInfo"
                              sender:self];
    
}

#pragma mark - LocationManager Delegates
- (void)locationManager:(CLLocationManager *)manager didFailWithError:(NSError *)error
{
    NSLog(@"didFailWithError: %@", error);
    UIAlertView *errorAlert = [[UIAlertView alloc]
                               initWithTitle:@"Error"
                               message:@"Failed to Get Your Location"
                               delegate:nil
                               cancelButtonTitle:@"OK"
                               otherButtonTitles:nil];
    [errorAlert show];
}
- (void)locationManager:(CLLocationManager *)manager didUpdateToLocation:(CLLocation *)newLocation fromLocation:(CLLocation *)oldLocation
{
    NSLog(@"didUpdateToLocation: %@", newLocation);
    currentLocation = newLocation;
    
    if (currentLocation != nil)
    {
        avgSpeed = newLocation.speed * 2.236936284;
        [arrAvgSpeed addObject:[NSNumber numberWithDouble:avgSpeed]];
        
        self.distance = [newLocation distanceFromLocation:arrLocationCoordinates.lastObject];
        
        [arrLocationCoordinates addObject:currentLocation];
        lblSpeedTest.text = [NSString stringWithFormat:@"Speed:%f-Distance: %@", currentLocation.speed, [MathController stringifyDistance:self.distance]];
        NSLog(@"DISTANCE: %@", [MathController stringifyDistance:self.distance]);
        
    }
    //[locationManager stopUpdatingLocation];
}

#pragma mark - Navigations
-(void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
    
    TripInfoViewController *nextView = segue.destinationViewController;
    nextView.disptanse      = self.distance;
    nextView.arrLocations   = arrLocationCoordinates;
    nextView.arrSpeed       = arrAvgSpeed;
    
}
@end
