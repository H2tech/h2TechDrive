//
//  TripInfoViewController.h
//  H2 Tech Drive
//
//  Created by Macbook Pro on 19/06/2015.
//  Copyright (c) 2015 UnitSol. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface TripInfoViewController : UIViewController

@property NSArray *arrSpeed;
@property NSArray *arrLocations;
@property double disptanse;
@property (weak, nonatomic) IBOutlet UILabel *lblAVGSpeed;
@property (weak, nonatomic) IBOutlet UILabel *lblDistanceTravelled;

@end
