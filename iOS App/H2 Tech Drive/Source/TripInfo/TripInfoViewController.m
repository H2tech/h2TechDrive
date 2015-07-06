//
//  TripInfoViewController.m
//  H2 Tech Drive
//
//  Created by Macbook Pro on 19/06/2015.
//  Copyright (c) 2015 UnitSol. All rights reserved.
//

#import "TripInfoViewController.h"
#import "MathController.h"
#import "Reachability.h"
#import "SBJson.h"
#import "SVProgressHUD.h"
#import "AFURLConnectionOperation.h"
#import "AFHTTPRequestOperation.h"
#import "AFHTTPRequestOperationManager.h"
#import "AFNetworking.h"

@interface TripInfoViewController ()
{
    double avgSpeed;
}
@end

@implementation TripInfoViewController
@synthesize arrSpeed, arrLocations;
@synthesize disptanse;
@synthesize lblAVGSpeed, lblDistanceTravelled;

- (void)viewDidLoad {
    [super viewDidLoad];
    
    for (int i=0; i<[arrSpeed count]; i++) {
        avgSpeed += [[arrSpeed objectAtIndex:i] doubleValue];
    }
    
    NSLog(@"AVG Speed: %f", avgSpeed/[arrSpeed count]);
    NSLog(@"Locations: %@", arrLocations);
    NSLog(@"Distance: %@", [MathController stringifyDistance:disptanse]);
    
    lblAVGSpeed.text = [NSString stringWithFormat:@"AVG Seed: %f", avgSpeed/[arrSpeed count]];
    lblDistanceTravelled.text = [NSString stringWithFormat:@"Distance: %@", [MathController stringifyDistance:disptanse]];
    
    [self performSaveMyTrip];
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
}

#pragma mark - Register Action

-(void)performSaveMyTrip{
    
    Reachability *reach = [Reachability reachabilityForInternetConnection];
    NetworkStatus netStatus = [reach currentReachabilityStatus];
    
    if (netStatus == NotReachable) {
        
    }else{
        
        [SVProgressHUD show];
        
        AFHTTPRequestOperationManager *manager = [AFHTTPRequestOperationManager manager];
        manager.responseSerializer = [AFHTTPResponseSerializer serializer];
        
        NSDictionary *params = @ {
            @"distance": lblDistanceTravelled.text,
            @"averageSpeed": lblAVGSpeed.text
        };
        
        [manager POST:@"http://h2api.herokuapp.com/trip" parameters:params success:^(AFHTTPRequestOperation *operation, id responseObject) {
            
            NSString *responseStr = [[NSString alloc] initWithData:responseObject encoding:NSUTF8StringEncoding];
            NSLog(@"Success: %@", responseStr);
            
            SBJsonParser *newParser = [[SBJsonParser alloc]init];
            NSDictionary *dictArray = [newParser objectWithString:responseStr error:nil];
            
            [SVProgressHUD dismiss];
            
        } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
            [SVProgressHUD dismiss];
            NSLog(@"Error: %@", error);
            
        }];
    }
}

@end